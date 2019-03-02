/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cupcakes.logic;

import com.cupcakes.logic.DTO.BottomDTO;
import com.cupcakes.data.DAO.CupcakeDAO;
import com.cupcakes.logic.DTO.ShoppingCart;
import com.cupcakes.logic.DTO.ToppingsDTO;
import com.cupcakes.data.DAO.UserDAO;
import com.cupcakes.logic.DTO.UserDTO;
import java.sql.SQLException;
import java.util.List;

/**
 * Controls IO from data to presentation
 * @author martin bøgh, Niels Bang
 */
public class Controller {

    /**
     * Pass on list of ToppingsDTO objects
     * 
     * @return  list of BottomDTO objects
     */
    public List<BottomDTO> fetchBottoms() {
        return new CupcakeDAO().getBottoms();
    }

    
    /**
     * Pass on list of ToppingsDTO objects
     * 
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
    public UserDTO fetchUser(String Username) throws SQLException{
        return new UserDAO().getUser(Username);
    }
    
    
    /**
     * Henter et ShoppingCart objekt fra data og sender det videre
     * 
     * @return 
     */
    public ShoppingCart fetchCart(){
        return new ShoppingCart();
    }
    
}
