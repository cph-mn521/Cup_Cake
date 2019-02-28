/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cupcakes.data;

import java.util.ArrayList;
import java.util.List;

/**
 *	`id` int NOT NULL,
	`name` VARCHAR(45) NOT NULL,
	`instructions` VARCHAR(300) NOT NULL,
	`ratings` VARCHAR(5) NOT NULL DEFAULT '0%',
 * @author martin
 */
public class CupcakeDTO {

    private int id;
    private String name;
    private String instructions;
    private String ratings;
    List<IngredientDTO> ingredients;
    private ImageDTO image;

    public CupcakeDTO(int id, String name, String instructions, String ratings,
            List<IngredientDTO> ingredients, ImageDTO image)
            {
        this.id = id;
        this.name = name;
        this.instructions = instructions;
        this.ratings = ratings;
        this.ingredients = ingredients;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getInstructions() {
        return instructions;
    }

    public String getRatings() {
        return ratings;
    }

    public List<IngredientDTO> getIngredients() {
        return ingredients;
    }

    public ImageDTO getImage() {
        return image;
    }
    
}
