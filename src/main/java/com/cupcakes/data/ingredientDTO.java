/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cupcakes.data;

/**
 * `ingredients_id` int NOT NULL, `recipe_id` int NOT NULL, `ingredient`
 * VARCHAR(25) NOT NULL, `amount` VARCHAR(25) NOT NULL,
 *
 * @author martin
 */
public class ingredientDTO {

    private int ingredient_id;
    private int recipe_id;
    private String ingredient;
    private String amount;

    public ingredientDTO(int ingredient_id, int recipe_id, String ingredient, String amount) {
        this.ingredient_id = ingredient_id;
        this.recipe_id = recipe_id;
        this.ingredient = ingredient;
        this.amount = amount;
    }

    public int getIngredient_id() {
        return ingredient_id;
    }

    public int getRecipe_id() {
        return recipe_id;
    }

    public String getIngredient() {
        return ingredient;
    }

    public String getAmount() {
        return amount;
    }

}
