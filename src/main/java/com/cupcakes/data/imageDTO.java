/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cupcakes.data;

/**
 *    `image`     VARCHAR(25) NOT NULL,
    `recipe_id` int NOT NULL,%',
 * @author martin
 */
public class imageDTO {

    private String image;
    private int recipe_id;

    public imageDTO(String image, int recipe_id) {
        this.image = image;
        this.recipe_id = recipe_id;
    }

    public String getImage() {
        return image;
    }

    public int getRecipe_id() {
        return recipe_id;
    }

}
