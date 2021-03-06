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
import javax.servlet.http.HttpServletRequest;

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
     * @param req
     * @param Email User email.
     * @return String with information about creation.
     */
    public static boolean createUser(String Username, String Password, String Email, HttpServletRequest req) {
        try {
            UserDAO db = new UserDAO(); // Include request så vi kan Skrive til usern.
            /*
            if (!Password.equals(PasswordCheck)) {
                req.setAttribute("registrationMessage", "Passwords must match!");
                return false;
            }
             */
            try {
                db.getUser(Username);
                req.setAttribute("registrationMessage", "Username in use");
                return false;
            } catch (SQLException e) {
                db.createUser(Username, Email, Password);
                req.getSession().setAttribute("user", db.getUser(Username));
                req.setAttribute("registrationMessage", "Gz! registered as user!");
                return true;
            }
        } catch (SQLException e) {
            req.setAttribute("registrationMessage", "No connection to database.");
            return false;
        }

    }

    /**
     * Checks if the User with "Username" exists in database and has matching
     * password
     *
     * @author Niels Bang
     * @param Username Username of the user to check.
     * @param Password Password to match with username
     * @param req
     * @return boolean for successful login
     */
    public static boolean loginCheck(String Username, String Password, HttpServletRequest req) {

        try {

            UserDTO user = new UserDAO().getUser(Username);

            if (req.getSession().getAttribute("user") != null) {
                req.getSession().setAttribute("user", null);
                return false;
            }

            if (user.getPassword().equals(Password)) {
                req.getSession().setAttribute("user", user);
                return true;
            } else {
                req.setAttribute("loginMessage", "Failed to login, invalid password");
                return false;
            }
        } catch (SQLException e) {
            req.setAttribute("loginMessage", "Failed to login, invalid username");
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
     * @param User_id
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
        return InvoiceOrderDAO.getShoppingCartFromDB(cartID);

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
     * @param invoice_id
     * @return
     */
    public Invoice fetchInvoice(int invoice_id) {
        return new InvoiceOrderDAO().retrieveInvoice(invoice_id);
    }

    /**
     * Calculates total price of all lineitems
     *
     * @author martin bøgh
     * @param cart
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
            return InvoiceOrderDAO.retrieveLatestInvoiceID();
        } catch (DataException ex) {
            System.out.println("Controller.putCartInDB error");
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
    /*
    public static void main(String[] args) {
        try {
            Boolean a = new Controller().createUser("a", "b", "b", "c@d.e");
            System.out.println(a);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());

        }
    }
     */

}
