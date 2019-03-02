package com.cupcakes.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DB {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://128.199.46.149:3306/cupcakes";
    private static final String USER = "testuser";
    private static final String PASSWORD = "password123";
    private static Connection conn = null;

    
    /**
     * Return connection type for connecting to mysql database
     * 
     * @return Connection
     */
    public static Connection getConnection() {
        if (conn == null) {
            try {
                Class.forName(DRIVER);
                conn = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            } catch (SQLException ex) {
                //se hele sekvenst til det gik galt. Dette kan skrives til logfil.
                ex.printStackTrace();
            }
        }
        return conn;
    }

    /**
     * Main method for testing connections
     * 
     * @param args 
     */
    public static void main(String[] args) {
        //Test connection
        try {
            String sql = "SELECT * FROM Bottom;";
            ResultSet rs = getConnection().prepareStatement(sql).executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("type")+" :\n"+rs.getString("price"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
