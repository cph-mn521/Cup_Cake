package com.cupcakes.data.DAO;

import com.cupcakes.data.DB;
import com.cupcakes.logic.DTO.BottomDTO;
import com.cupcakes.logic.DTO.ToppingsDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author martin bøgh
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
                        rs.getFloat("price"),
                        rs.getInt("bottom_id")
                ));
            }
        } catch (SQLException ex) {
            System.out.println("Fejl CupcakeDAO.getBottoms " + ex);
        }
        return bottoms;
    }

    /**
     *  Maps single bottom from DB to BottomDTO
     * 
     * @param bottomType
     * @return BottomDTO objekt
     */
    public BottomDTO getBottom(String bottomType) {

        String query = "SELECT * FROM cupcakes.Bottom WHERE `type`='" + bottomType + "';";
        BottomDTO b = null;

        try {
            ResultSet rs = DB.getConnection().createStatement().executeQuery(query);
            while (rs.next()) {
                b = new BottomDTO(
                        rs.getString("type"),
                        rs.getFloat("price"),
                        rs.getInt("bottom_id"));
            }
        } catch (SQLException ex) {
            System.out.println("Fejl CupcakeDAO.getBottom " + ex);
        }
        return b;
    }

    /**
     * Creates list with ToppingsDTO objects mapped from DB
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
                        rs.getFloat("price"),
                        rs.getInt("topping_id")
                ));
            }
        } catch (SQLException ex) {
            System.out.println("Fejl CupcakeDAO.getToppings " + ex);
        }
        return toppings;
    }

    /**
     * Maps single topping from DB to ToppingsDTO
     * 
     * @param toppingType
     * @return enkelt ToppingsDTO objekt
     */
    public ToppingsDTO getTopping(String toppingType) {

        String query = "SELECT * FROM cupcakes.Toppings WHERE `type`='" + toppingType + "';";
        ToppingsDTO t = null;

        try {
            ResultSet rs = DB.getConnection().createStatement().executeQuery(query);
            while (rs.next()) {
                t = new ToppingsDTO(rs.getString("type"),
                        rs.getFloat("price"),
                        rs.getInt("topping_id"));
            }
        } catch (SQLException ex) {
            System.out.println("Fejl CupcakeDAO.getTopping " + ex);
        }
        return t;
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

    /**
     * Helper method for adding things to DB
     * 
     * @param query
     */
    public void addToDB(String query) {
        try {
            int result = DB.getConnection().createStatement().executeUpdate(query);
            System.out.println("result from adding to database: \n" + result);
        } catch (SQLException ex) {
            System.out.println("Fejl recipeDAO addToDB:\n" + ex);
        }
    }

    /**
     * til test af forbindelser
     */
//    public static void main(String[] args) {
////        new CupcakeDAO().addBottom("NonApple2", 16.3);
//        System.out.println("");
////        for (BottomDTO arg : new CupcakeDAO().getBottoms()) {
//        for (ToppingsDTO arg : new CupcakeDAO().getToppings()) {
//            System.out.println("Type: " + arg.getType() + "\tPris: " + arg.getPrice());
//        }
//    }
}
