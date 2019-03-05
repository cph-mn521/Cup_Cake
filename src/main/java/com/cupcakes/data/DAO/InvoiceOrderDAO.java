/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cupcakes.data.DAO;

import com.cupcakes.data.DB;
import com.cupcakes.logic.DTO.LineItemsDTO;
import com.cupcakes.logic.DTO.ShoppingCart;
import com.cupcakes.logic.DTO.UserDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 *
 * @author Martin Brandstrup
 */
public class InvoiceOrderDAO
{

    private ShoppingCart cart;
    private UserDTO user;
    private double totalCost;
    private int invoiceOrderID;
    public static int shoppingCartIdCounter = 1;

    /**
     * A medium between the session and the database to make sure everything
     * gets persisted properly. Works as both the ShoppingCartDAO and the
     * InvoiceOrderDAO.
     * 
     * On object creation, creates a placeholder InvoiceOrder
     * instance in the database, that needs to be canceled in case the order
     * isn't completed.
     * 
     * Additionally, this Java class also keeps track of and assigns the IDs
     * for all ShoppingCart entries in the database.
     *
     * @author Martin Brandstrup
     * @param cart the ShoppingCart with all the Cupcake LineItemsDTO
     * @param user the current user logged in the session
     */
    public InvoiceOrderDAO(ShoppingCart cart, UserDTO user)
    {
        if (cart == null || user == null)
        {
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
    private void saveShoppingCartToDB()
    {
        for (LineItemsDTO cake : cart.getLineItems())
        {
            int bottomId = cake.getCupcake().getBottom().getId();
            int toppingId = cake.getCupcake().getTopping().getId();
            int quantity = cake.getQuantity();

            try
            {
                String query
                        = "INSERT INTO `ShoppingCart`(`cart_id`,`bottom_id`,`topping_id`,`quantity`) VALUES ("
                        + shoppingCartIdCounter + ", "
                        + bottomId + ", "
                        + toppingId + ", "
                        + quantity + ")";

                int result = DB.getConnection().createStatement().executeUpdate(query);
                System.out.println(result + " rows added");
            } catch (SQLException ex)
            {
                ex.printStackTrace();
                System.out.println(ex);
            }
        }
    }

    /**
     * Ensures the user can afford the order.
     *
     * @author Martin Brandstrup
     * @return true if the user can afford the order
     */
    private boolean calculateTransactionCost()
    {
        for (LineItemsDTO cake : cart.getLineItems())
        {
            float bottomPrice = cake.getCupcake().getBottom().getPrice();
            float toppingPrice = cake.getCupcake().getTopping().getPrice();
            int quantity = cake.getQuantity();

            //Guard. Er der flere conditions der skal tilføjes? Skal den kaste en exception istedet?
            if (bottomPrice < 1 || toppingPrice < 1 || quantity < 1)
            {
                System.out.println("There was a problem with your data");
                return false;
            }

            double cakeCost = (bottomPrice + toppingPrice) * quantity;
            totalCost += cakeCost;
        }
        if (totalCost > user.getBalance())
        {
            //Skal der kastes en IllegalArguementException her?
            System.out.println("The user cannot afford the order");
            return false;
        } else
        {
            return true;
        }
    }

    /**
     * Subtracts the total cost of the order from the user's balance account.
     *
     * @author Martin Brandstrup
     */
    private void payForOrder()
    {
        try
        {
            String query
                    = "UPDATE User"
                    + "SET User.`balance` = " + (user.getBalance() - totalCost)
                    + "WHERE User.`user_id` = " + user.getUserId() + ";";

            int result = DB.getConnection().createStatement().executeUpdate(query);
            System.out.println(result + " rows changed");
        } catch (SQLException ex)
        {
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
    private int createPlaceholderInvoiceInDB()
    {
        int invoiceID = 1;
        
        try
        {
            String query
                    = "INSERT INTO `Invoice`() VALUES ()";

            int result = DB.getConnection().createStatement().executeUpdate(query);
            System.out.println(result + " rows added");
        } catch (SQLException ex)
        {
            ex.printStackTrace();
            System.out.println(ex);
        }
        
        String query = "SELECT MAX(invoice_id) AS highest_invoiceID FROM cupcakes.Invoice";
        try
        {
            ResultSet rs = DB.getConnection().createStatement().executeQuery(query);
            while (rs.next())
            {
                invoiceID = rs.getInt("highest_invoiceID");
            }
        } catch (SQLException ex)
        {
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
     * 
     * Warning: Should only be called once at the end of a transaction to avoid
     * duplicates in the database!
     *
     * @author Martin Brandstrup
     */
    public void saveOrderToDB()
    {
        if (calculateTransactionCost() == false)
        {
            return;
        }

        payForOrder();
        saveShoppingCartToDB();

        try
        {
            String query
                    = "UPDATE `Invoice` SET"
                    + "Invoice.`user_id` = " + user.getUserId() + ","
                    + "Invoice.`cart_id` = " + shoppingCartIdCounter + ","
                    + "Invoice.`invoice_date` = '" + LocalDateTime.now() + "'"
                    + "WHERE Invoice.`invoice_id` = " + invoiceOrderID + ";";
            
//                    = "INSERT INTO `Invoice`(`user_id`,`cart_id`,`invoice_date`) VALUES ("
//                    + user.getUserId() + ","
//                    + shoppingCartIdCounter + ", '"
//                    + LocalDateTime.now() + "')";

            int result = DB.getConnection().createStatement().executeUpdate(query);
            System.out.println(result + " rows added");
        } catch (SQLException ex)
        {
            ex.printStackTrace();
            System.out.println(ex);
        }

        shoppingCartIdCounter++;
    }

    public int getInvoiceOrderID()
    {
        return invoiceOrderID;
    }

    /**
     * Query for highest invoice_id in Invoice table
     *
     * @author Martin Bøgh
     * @return highest invoice_id
     */
    public int getLatestInvoiceNumber()
    {
        String query = "SELECT MAX(invoice_id) FROM cupcakes.Invoice";
        int b = 0;
        try
        {
            ResultSet rs = DB.getConnection().createStatement().executeQuery(query);
            while (rs.next())
            {
                b = rs.getInt("MAX(invoice_id)");
            }
        } catch (SQLException ex)
        {
            System.out.println("Fejl InvoiceOrderDAO.getLatestInvoiceNumber " + ex);
        }
        return b;
    }

}
