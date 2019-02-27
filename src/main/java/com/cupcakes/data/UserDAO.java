/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cupcakes.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Martin Wulff
 */
public class UserDAO {
    
    private DB connector = null;
    private Connection connection;
    private Statement stmt;
    private List<recipeDTO> recipes = new ArrayList();
    private List<ingredientDTO> ingredients;
    
    public UserDAO()throws SQLException{
        this.connector = new DB();
        this.connection = this.connector.getConnection();
        this.stmt = this.connection.createStatement();
    }
    
    /**
     * Method for extracting a user from the database.
     * 
     * @param Username  The desired username to be extracted from the database.
     * @return User as a UserDTO.
     */
    public UserDTO getUser(String Username){
        UserDTO user = null;
        String Query = "SELECT * "
                + "FROM CupCakes.Users"
                + "Where Usnername="+Username
                + ";";
        try {
            ResultSet rs =stmt.executeQuery(Query);
            while(rs.next()){ // User er en primary key. Kun 1 resultat bør returnes.
                user = user(
                    rs.getString(Username),
                    rs.getString(email),
                    rs.getString(password),
                    rs.getDouble(balance)
                );
                
            }
                    
            return user;
        }
        catch(SQLException e){
            //Returner et relevant svar. Evt kast fejlen længere op så den kan håndteres i controlleren.
        }
        
        
        
    }
    
    
}
