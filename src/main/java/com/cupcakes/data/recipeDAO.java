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
public class recipeDAO {

    private List<recipeDTO> recipes = new ArrayList();
    private List<ingredientDTO> ingredients;

    public recipeDTO getRecipe(String recipeName) {
        recipeDTO recipe = null;
        String query
                = "SELECT *"
                + "FROM `recipes`"
                + "INNER JOIN `images`"
                + "ON images.`recipe_id` = recipes.`id`"
                + "WHERE recipes.`name` = \"" + recipeName + "\";";
        try {
            ResultSet rs = DB.getConnection().createStatement().executeQuery(query);
            while (rs.next()) {
                ingredients = new ArrayList();
                recipe = new recipeDTO(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("instructions"),
                        rs.getString("ratings"),
                        ingredients,
                        new imageDTO(rs.getString("image"), rs.getInt("recipe_id"))
                );
            }
        } catch (SQLException ex) {

        }
        return recipe;
    }

    public List<recipeDTO> getRecipes() {

        String query = "SELECT * FROM `recipes` INNER JOIN `images` ON `recipes`.`id` = `images`.`recipe_id`;";

        try {
            ResultSet rs = DB.getConnection().createStatement().executeQuery(query);
            while (rs.next()) {
                int s = rs.getInt("id");
                if (s > 0) {
                    String query_ingr = "SELECT * FROM cakes.ingredients where `recipe_id` = " + s + ";";
                    ResultSet rs_ingredients = DB.getConnection().createStatement().executeQuery(query_ingr);
                    ingredients = new ArrayList();
                    while (rs_ingredients.next()) {

                        ingredients.add(new ingredientDTO(
                                rs_ingredients.getInt("ingredients_id"),
                                rs_ingredients.getInt("recipe_id"),
                                rs_ingredients.getString("ingredient"),
                                rs_ingredients.getString("amount")));
                    }
                }
                recipes.add(new recipeDTO(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("instructions"),
                        rs.getString("ratings"),
                        ingredients,
                        new imageDTO(rs.getString("image"), rs.getInt("recipe_id"))
                ));
            }
        } catch (SQLException ex) {
            System.out.println("Fejl recipeDAO " + ex);
        }
        return recipes;
    }

    /**
     * I første omgang er der en metode for hver tabel. Det kan ændres senere
     * så den benytter en enum til at håndtere tabeller og stadig gøre det let at huske 
     * tabelnavn når man kalder metoderne
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
        new recipeDAO().testAdd();
    }
    
    public void testAdd(){
        System.out.println("");
        this.addBottom("NonApple2", 16.3);
        
    }
}
