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

//import com.cupcakes.data.UserDTO;
/**
 *
 * @author Martin Wulff
 */
public class UserDAO {

    private DB connector = null;
    private Connection connection;
    private Statement stmt;
    private List<CupcakeDTO> recipes = new ArrayList();
    private List<IngredientDTO> ingredients;

    public UserDAO() throws SQLException {
        this.connector = new DB();
        this.connection = this.connector.getConnection();
        this.stmt = this.connection.createStatement();
    }

    /**
     * Method for extracting a user from the database.
     *
     * @param Username The desired username to be extracted from the database.
     * @return User as a UserDTO.
     * @throws SQLException
     */
    public UserDTO getUser(String Username) throws SQLException {

        String Query = "SELECT * "
                + "FROM cupcakes.`User`"
                + "Where `username`='" + Username
                + "';";

        ResultSet rs = stmt.executeQuery(Query);
        rs.next(); // User er en primary key. Kun 1 resultat bør returnes.
        UserDTO user = new UserDTO(
                rs.getInt("user_id"),
                rs.getString("username"),
                rs.getString("email"),
                rs.getString("password"),
                rs.getDouble("balance")
        );

        if (user.getName() == null) {
            throw new SQLException("No result found.");
        }

        return user;

    }

    /**
     * Method for adding users to the database.
     *
     * @param Username Username of the user.
     * @param Email Email of the user.
     * @param Password Password of the user.
     * @throws SQLException
     *
     * @Author Niels Bang
     */
    public void createUser(String Username, String Email, String Password) throws SQLException {
        try {
            int i = DB.getConnection().createStatement().executeUpdate("INSERT INTO `User`(`username`,`password`,`email`)"
                    + " VALUES('"
                    + Username + "','"
                    + Password + "','"
                    + Email + "');");

            if (i == 0) {
                throw new SQLException();
            }

        } catch (SQLException e) {
            throw new SQLException("Error in query: " + e.getErrorCode());
        }
    }

    /*
    // Main for testing DAO
    public static void main(String[] args) {
        try {
            //       new UserDAO().createUser("This user", "Is created ", "in Java");
            UserDTO a = new UserDAO().getUser("This user");

            System.out.println("This was found: email:" + a.getEmail() + " name: " + a.getName() + " pw :" + a.getPassword() + " balance: " + a.getBalance());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     */
}
