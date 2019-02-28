/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cupcakes.logic;

import com.cupcakes.data.cupcakeDAO;
import com.cupcakes.data.cupcakeDTO;
import java.util.List;

/**
 *
 * @author martin
 */
public class Controller {

//    public List<String> getIngredients();
//
//    public ingredientDAO getIngredient();

    public List<cupcakeDTO> getRecipes() {
        return new cupcakeDAO().getRecipes();
    }
    
    public cupcakeDTO getRecipe(String recipeName)
    {
        return new cupcakeDAO().getRecipe(recipeName);
    }
}
