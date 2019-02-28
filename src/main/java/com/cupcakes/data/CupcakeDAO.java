/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cupcakes.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author martin
 */
public class CupcakeDAO {

    private List<BottomDTO> bottoms = new ArrayList();
    private List<ToppingsDTO> toppings = new ArrayList();

//    public CupcakeDTO getCupcakes(String recipeName) {
//
//        return recipe;
//    }
    /**
     *
     * @return liste med alle bunde
     */
    public List<BottomDTO> getBottoms() {

        String query = "SELECT * FROM cupcakes.Bottom;";

        try {
            ResultSet rs = DB.getConnection().createStatement().executeQuery(query);
            while (rs.next()) {
                bottoms.add(new BottomDTO(
                        rs.getString("type"),
                        rs.getFloat("price")
                ));
            }
        } catch (SQLException ex) {
            System.out.println("Fejl CupcakeDAO.getBottoms " + ex);
        }
        return bottoms;
    }

    
    /**
     *
     * @return liste med alle toppings
     */
    public List<ToppingsDTO> getToppings() {

        String query = "SELECT * FROM cupcakes.Toppings;";

        try {
            ResultSet rs = DB.getConnection().createStatement().executeQuery(query);
            while (rs.next()) {
                toppings.add(new ToppingsDTO(
                        rs.getString("type"),
                        rs.getFloat("price")
                ));
            }
        } catch (SQLException ex) {
            System.out.println("Fejl CupcakeDAO.getBottoms " + ex);
        }
        return toppings;
    }

    
    /**
     * Der er ikke brug for at add'e kager til databasen men det er lavet for at
     * se om det virker og hvis vi skulle få lyst til at lave nye kager (med gul
     * skimmelost på :-)
     *
     * @param type
     * @param prices
     */
    public void addBottom(String type, double prices) {
        String query = "INSERT INTO `Bottom` (`type`, `price`) "
                + "VALUES ('" + type + "', " + (float) prices + ");";
        addToDB(query);
    }

    public void addToppings(String type, double prices) {
        String query = "INSERT INTO `Toppings` (`type`, `price`) "
                + "VALUES ('" + type + "', " + (float) prices + ");";
        addToDB(query);

    }

    public void addToDB(String query) {
        try {
            int result = DB.getConnection().createStatement().executeUpdate(query);
            System.out.println("result from adding to database: \n" + result);
        } catch (SQLException ex) {
            System.out.println("Fejl recipeDAO addToDB:\n" + ex);
        }
    }

    public static void main(String[] args) {
//        new CupcakeDAO().addBottom("NonApple2", 16.3);
        System.out.println("");
//        for (BottomDTO arg : new CupcakeDAO().getBottoms()) {
        for (ToppingsDTO arg : new CupcakeDAO().getToppings()) {
            System.out.println("Type: " + arg.getType() + "\tPris: " + arg.getPrice());
        }
    }
}
