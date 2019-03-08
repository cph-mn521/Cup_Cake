/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cupcakes.logic;

import com.cupcakes.logic.DTO.BottomDTO;
import com.cupcakes.data.DAO.CupcakeDAO;
import com.cupcakes.data.DAO.InvoiceOrderDAO;
import com.cupcakes.logic.DTO.ShoppingCart;
import com.cupcakes.logic.DTO.ToppingsDTO;
import com.cupcakes.data.DAO.UserDAO;
import com.cupcakes.data.DataException;
import com.cupcakes.logic.DTO.CupcakeDTO;
import com.cupcakes.logic.DTO.Invoice;
import com.cupcakes.logic.DTO.LineItemsDTO;
import com.cupcakes.logic.DTO.UserDTO;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Controls IO from data to presentation
 */
public class Controller {

    /**
     * Pass on list of ToppingsDTO objects
     *
     * @author martin bøgh
     * @return list of BottomDTO objects
     */
    public List<BottomDTO> fetchBottoms() {
        return new CupcakeDAO().getBottoms();
    }

    /**
     * Pass on list of ToppingsDTO objects
     *
     * @author martin bøgh
     * @return list of ToppingsDTO objects
     */
    public List<ToppingsDTO> fetchToppings() {
        return new CupcakeDAO().getToppings();
    }

    /**
     * Method for handling create user requests.
     *
     * @author Niels Bang
     *
     * @param Username Username to create
     * @param Password Password.
     * @param PasswordCheck Password confirmation.
     * @param Email User email.
     * @return String with information about creation.
     * @throws java.lang.Exception
     */
    public static boolean createUser(String Username, String Password, String PasswordCheck, String Email) throws Exception {
        UserDAO db = new UserDAO();
        if (!Password.equals(PasswordCheck)) {
            throw new Exception("Passwords must match!");
        }
        if (!Email.matches(".+@.+\\..+")) {
            throw new Exception("Email error!");
        }
        if (Username.equals(db.getUser(Username))) {
            throw new Exception("Username in use");
        }
        try {
            db.createUser(Username, Email, Password);
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public void cuser(String uname, String mail, String pw) {
        try {
            new UserDAO().createUser(uname, pw, mail);
        } catch (SQLException e) {

        }
    }

    /**
     * Checks if the User with "Username" exists in database and has matching
     * password
     *
     * @author Niels Bang
     * @param Username Username of the user to check.
     * @param Password Password to match with username
     * @return boolean for successful login
     */
    public static boolean loginCheck(String Username, String Password) {
        try {
            return new UserDAO().getUser(Username).getPassword().equals(Password);
        } catch (SQLException e) {
            return false;
        }
    }

    /**
     * Henter et User objekt fra data og sender det videre
     *
     * @param Username
     * @return User objekt
     * @throws SQLException
     */
    public UserDTO fetchUser(String Username) throws SQLException {
        return new UserDAO().getUser(Username);
    }

    
    /**
     * Henter et User objekt fra data og sender det videre
     *
     * @param UserID
     * @return User objekt
     * @throws SQLException
     */
    public UserDTO fetchUser(int User_id) throws SQLException {
        return new UserDAO().getUser(User_id);
    }
    
    /**
     * Henter et ShoppingCart objekt fra data og sender det videre
     *
     * @author martin bøgh
     * @return
     */
    public ShoppingCart fetchCart() {
        return new ShoppingCart();
    }

    /**
     * Henter et ShoppingCart objekt fra data og sender det videre
     *
     * @author martin bøgh
     * @return
     */
    public List<LineItemsDTO> fetchCart(int cartID) {
        return new InvoiceOrderDAO().getShoppingCartFromDB(cartID);

    }

    /**
     * Pull out a list of total invoices
     *
     * @return
     */
    public List<Invoice> fetchInvoiceList() {
        return new InvoiceOrderDAO().retrieveInvoiceList();

    }

    /**
     * Pull out an invoice
     *
     * @param cart_id
     * @return
     */
    public Invoice fetchInvoice(int cart_id) {
        return  new InvoiceOrderDAO().retrieveInvoice(cart_id);
    }
    
    /**
     * Calculates total price of all lineitems
     *
     * @author martin bøgh
     * @return
     */
    public float fetchTotalPrice(ShoppingCart cart) {
        float totalPrice = 0;
        for (LineItemsDTO l : cart.getLineItems()) {
            totalPrice += l.getQuantity() * (l.getCupcake().getTopping().getPrice()
                    + l.getCupcake().getBottom().getPrice());
        }
        return totalPrice;
    }

    /**
     * Check if cupcake is already in list then adds to quantity of the cake
     *
     * @author martin bøgh
     * @param cart
     * @param cake
     * @param quantity
     * @return false is not duplicate, true if duplicate and quantity is updated
     */
    public boolean isCupCakeDuplicate(ShoppingCart cart, CupcakeDTO cake, int quantity) {
        for (LineItemsDTO l : cart.getLineItems()) {
            if (l.getCupcake().getTopping().getType().equals(cake.getTopping().getType())
                    && l.getCupcake().getBottom().getType().equals(cake.getBottom().getType())) {

                l.setQuantity(l.getQuantity() + quantity);
                return true;
            }
        }
        return false;
    }

    /**
     * control talks to DAO layer to save into invoiceDB and getting highest
     * invoice_ID in return
     *
     * @author martin bøgh
     *
     * @param cart
     * @param user
     * @return highest invoice_ID, or -1 if error
     */
    public int putCartInDB(ShoppingCart cart, UserDTO user) {
        InvoiceOrderDAO invoice = new InvoiceOrderDAO(cart, user);
        try {
            invoice.saveOrderToDB();
        } catch (DataException ex) {
            System.out.println("putCartInDB: " + ex);
            return -1;

        }
        try {
            return invoice.retrieveLatestInvoiceID();
        } catch (DataException ex) {
            System.out.println("Controller.putCartInDB error");
            ex.printStackTrace();
        }
        return -1;
    }

    /**
     * Remove temporary cart from DB
     *
     * @author martin bøgh
     */
    public void cancelOrder() {
        InvoiceOrderDAO invoice = new InvoiceOrderDAO();
        invoice.cancelOrder();
    }

    /**
     * Get new invoice ID from DB
     *
     * @author martin bøgh
     * @return invoiceID
     */
    public int getInvoiceID() {
        InvoiceOrderDAO invoice = new InvoiceOrderDAO();
        return invoice.getInvoiceOrderID();

    }

    public static void main(String[] args) {
        try {
            Boolean a = new Controller().createUser("a", "b", "b", "c@d.e");
            System.out.println(a);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());

        }
    }

}
