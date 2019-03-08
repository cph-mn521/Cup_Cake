/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cupcakes.presentation;

import com.cupcakes.logic.Controller;
import java.sql.SQLException;

/**
 *
 * @author martin bøgh
 */
public class Authenticator {

    public static String authenticate(String username, String password) {

        Controller c = new Controller();
        try {
            if ((c.fetchUser(username).getName().equalsIgnoreCase(username))
                    && (c.fetchUser(username).getPassword().equals(password))) {
                return "success";
            } else {
                return "failure";
            }
        } catch (SQLException e) {
            return "failure";
        }
    }
}
