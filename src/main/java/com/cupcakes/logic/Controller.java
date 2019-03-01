/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cupcakes.logic;

import com.cupcakes.data.UserDAO;
import com.cupcakes.data.BottomDTO;
import com.cupcakes.data.CupcakeDAO;
import com.cupcakes.data.ShoppingCart;
import com.cupcakes.data.ToppingsDTO;
import com.cupcakes.data.UserDAO;
import com.cupcakes.data.UserDTO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author martin
 */
public class Controller {

    public List<BottomDTO> fetchBottoms() {
        return new CupcakeDAO().getBottoms();
    }

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

    public UserDTO fetchUser(String Username) throws SQLException{
        return new UserDAO().getUser(Username);
    }

    public ShoppingCart fetchCart(){
        return new ShoppingCart();
    }
    
}
