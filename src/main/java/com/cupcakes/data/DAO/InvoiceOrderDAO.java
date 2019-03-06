/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cupcakes.data.DAO;

import com.cupcakes.data.DB;
import com.cupcakes.data.DataException;
import com.cupcakes.logic.Controller;
import com.cupcakes.logic.DTO.CupcakeDTO;
import com.cupcakes.logic.DTO.Invoice;
import com.cupcakes.logic.DTO.LineItemsDTO;
import com.cupcakes.logic.DTO.ShoppingCart;
import com.cupcakes.logic.DTO.UserDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Martin Brandstrup
 */
public class InvoiceOrderDAO {

    private ShoppingCart cart;
    private UserDTO user;
    private double totalCost;
    private int invoiceOrderID;
    public static int shoppingCartIdCounter = 1;

    /**
     * Short constructor to acces method not needing objects
     * @author Martin Bøgh
     */
    public InvoiceOrderDAO(){
    }
    
    /**
     * A medium between the session and the database to make sure everything
     * gets persisted properly. Works as both the ShoppingCartDAO and the
     * InvoiceOrderDAO.
     *
     * On object creation, creates a placeholder InvoiceOrder instance in the
     * database, that needs to be canceled in case the order isn't completed.
     *
     * Additionally, this Java class also keeps track of and assigns the IDs for
     * all ShoppingCart entries in the database.
     *
     * @author Martin Brandstrup
     * @param cart the ShoppingCart with all the Cupcake LineItemsDTO
     * @param user the current user logged in the session
     */
    public InvoiceOrderDAO(ShoppingCart cart, UserDTO user) {
        if (cart == null || user == null) {
            throw new IllegalArgumentException();
        }
        this.cart = cart;
        this.user = user;
        this.totalCost = 0;
        this.invoiceOrderID = createPlaceholderInvoiceInDB();
    }

    /**
     * Saves the ShoppingCart to the Database.
     *
     * @author Martin Brandstrup
     */
    private void saveShoppingCartToDB() {
        for (LineItemsDTO cake : cart.getLineItems()) {
            int bottomId = cake.getCupcake().getBottom().getId();
            int toppingId = cake.getCupcake().getTopping().getId();
            int quantity = cake.getQuantity();

            try {
                String query
                        = "INSERT INTO `ShoppingCart`(`cart_id`,`bottom_id`,`topping_id`,`quantity`) VALUES ("
                        + shoppingCartIdCounter + ", "
                        + bottomId + ", "
                        + toppingId + ", "
                        + quantity + ")";

                int result = DB.getConnection().createStatement().executeUpdate(query);
                System.out.println(result + " rows added");
            } catch (SQLException ex) {
                ex.printStackTrace();
                System.out.println(ex);
            }
        }
    }

    
     /**
     * Get the ShoppingCart from the Database.
     *
     * @author Martin Bøgh
     * @return list of all invoices
     */
    public List<LineItemsDTO> getShoppingCartFromDB(int cart_id) {
        String query = "SELECT * FROM cupcakes.ShoppingCart WHERE cart_id="+cart_id+";";
        List<LineItemsDTO> cartList = new ArrayList<>();
        
        CupcakeDAO cup = new CupcakeDAO();
        
        try {
            ResultSet rs = DB.getConnection().createStatement().executeQuery(query);
            while (rs.next()) {
                CupcakeDTO cupcake = new CupcakeDTO(cup.getTopping(rs.getInt("topping_id")), cup.getBottom(rs.getInt("bottom_id")));
                cartList.add(new LineItemsDTO(cupcake, rs.getInt("quantity"), rs.getInt("cart_id")));
            }
        } catch (SQLException ex) {
            System.out.println("Fejl InvoiceOrderDAO.getLatestInvoiceNumber " + ex);
        }
        return cartList;
    }
    
    
    /**
     * Ensures the user can afford the order.
     *
     * @author Martin Brandstrup
     * @return true if the user can afford the order
     */
    private boolean calculateTransactionCost() {
        for (LineItemsDTO cake : cart.getLineItems()) {
            float bottomPrice = cake.getCupcake().getBottom().getPrice();
            float toppingPrice = cake.getCupcake().getTopping().getPrice();
            int quantity = cake.getQuantity();

            //Guard. Er der flere conditions der skal tilføjes? Skal den kaste en exception istedet?
            if (bottomPrice < 1 || toppingPrice < 1 || quantity < 1) {
                System.out.println("There was a problem with your data");
                return false;
            }

            double cakeCost = (bottomPrice + toppingPrice) * quantity;
            totalCost += cakeCost;
        }
        if (totalCost > user.getBalance()) {
            //Skal der kastes en IllegalArguementException her?
            System.out.println("The user cannot afford the order");
            return false;
        } else {
            return true;
        }
    }

    /**
     * Subtracts the total cost of the order from the user's balance account.
     *
     * @author Martin Brandstrup
     */
    private void payForOrder() {
        try {
            String query
                    = "UPDATE User"
                    + "SET User.`balance` = " + (user.getBalance() - totalCost)
                    + "WHERE User.`user_id` = " + user.getUserId() + ";";

            int result = DB.getConnection().createStatement().executeUpdate(query);
            System.out.println(result + " rows changed");
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println(ex);
        }
    }

    /**
     * Creates a placeholder entry in the database and provides the ID of this
     * placeholder to the current object, so it can be updated at a later time.
     *
     * @author Martin Brandstrup
     * @return the id of the placeholder instance
     */
    private int createPlaceholderInvoiceInDB() {
        int invoiceID = 1;

        try
        {
            String query
                    = "INSERT INTO `Invoice`() VALUES ()";

            int result = DB.getConnection().createStatement().executeUpdate(query);
            System.out.println(result + " rows added");
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println(ex);
        }

        String query = "SELECT MAX(invoice_id) AS highest_invoiceID FROM cupcakes.Invoice;";
        try
        {
            ResultSet rs = DB.getConnection().createStatement().executeQuery(query);
            while (rs.next()) {
                invoiceID = rs.getInt("highest_invoiceID");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println(ex);
        }

        return invoiceID;
    }

    /**
     * Saves the ShoppingCart (carried by the current InvoiceOrderDAO object
     * from which this method is called) as well as the Invoice to the Database.
     * Subtracts the total cost of the order from the user's balance account.
     *
     *
     * Warning: Should only be called once at the end of a transaction to avoid
     * duplicates in the database!
     *
     * @author Martin Brandstrup
     */
    public void saveOrderToDB() throws DataException
    {
        if (calculateTransactionCost() == false)
        {
            throw new DataException();
        }

        shoppingCartIdCounter = retrieveLatestShoppingCartID() +1;
        payForOrder();
        saveShoppingCartToDB();

        try {
            String query
                    = "UPDATE `Invoice` SET"
                    + "Invoice.`user_id` = " + user.getUserId() + ","
                    + "Invoice.`cart_id` = " + shoppingCartIdCounter + ","
                    + "Invoice.`invoice_date` = '" + LocalDateTime.now() + "'"
                    + "WHERE Invoice.`invoice_id` = " + invoiceOrderID + ";";

            int result = DB.getConnection().createStatement().executeUpdate(query);
            System.out.println(result + " rows added");
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println(ex);
        }
    }

    /**
     * Cancels the order and removes the placeholder from the database.
     *
     *
     * Warning: Caution should be used with this method as it makes any
     * subsequent updates to the database on this object's ID impossible. A new
     * object should be made in the worst case.
     *
     * @author Martin Brandstrup
     */
    public void cancelOrder() {
        try {
            String query
                    = "DELETE FROM Invoice"
                    + "WHERE Invoice.invoice_id = " + invoiceOrderID + ";";

            int result = DB.getConnection().createStatement().executeUpdate(query);
            System.out.println(result + " rows removed");
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println(ex);
        }
    }

    public int getInvoiceOrderID() {
        return invoiceOrderID;
    }

    public ShoppingCart getCart() {
        return cart;
    }

    public UserDTO getUser() {
        return user;
    }

    public double getTotalCost() {
        return totalCost;
    }

    /**
     * Retrieves the highest ID of ShoppingCarts from the database. Ie. the
     * latest entry.
     *
     * @author Martin Brandstrup
     * @return the id of the latest ShoppingCart from the database
     * @throws DataException - if the returned ID is lower than 0; most likely
     * due to no entries existing in database.
     */
    public int retrieveLatestShoppingCartID() throws DataException
    {
        int shoppingCardID = -1;
        
        String query = "SELECT MAX(cart_id) AS highest_cartID FROM cupcakes.ShoppingCart;";
        try
        {
            ResultSet rs = DB.getConnection().createStatement().executeQuery(query);
            while (rs.next())
            {
                shoppingCardID = rs.getInt("highest_cartID");
            }
        } catch (SQLException ex)
        {
            ex.printStackTrace();
            System.out.println(ex);
        }

        if(shoppingCardID < 0)
        {
            System.out.println("The retrieved ID is not legal");
            throw new DataException();
        }
        
        return shoppingCardID;
    }

    /**
     * Query for list of all invoices
     *
     * @author Martin Bøgh
     * @return list of all invoices
     */
    public List<Invoice> getAllInvoiceList() {
        String query = "SELECT * FROM cupcakes.Invoice";
        List<Invoice> invoiceList = new ArrayList<>();
        try {
            ResultSet rs = DB.getConnection().createStatement().executeQuery(query);
            while (rs.next()) {
                invoiceList.add(new Invoice(
                        rs.getInt("invoice_id"),
                        rs.getInt("user_id"),
                        rs.getInt("cart_id"),
                        rs.getDate("invoice_date")
                ));
            }
        } catch (SQLException ex) {
            System.out.println("Fejl InvoiceOrderDAO.getLatestInvoiceNumber " + ex);
        }
        return invoiceList;
    }

    
    
    /**
     * Query for highest invoice_id in Invoice table
     *
     * @author Martin Bøgh
     * @return highest invoice_id
     */
    public int getLatestInvoiceNumber() {
        String query = "SELECT MAX(invoice_id) FROM cupcakes.Invoice";
        int b = 0;
        try {
            ResultSet rs = DB.getConnection().createStatement().executeQuery(query);
            while (rs.next()) {
                b = rs.getInt("MAX(invoice_id)");
            }
        } catch (SQLException ex) {
            System.out.println("Fejl InvoiceOrderDAO.getLatestInvoiceNumber " + ex);
        }
        return b;
    }

}
