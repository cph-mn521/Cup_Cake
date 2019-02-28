/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cupcakes.logic;

import com.cupcakes.data.CupcakeDAO;
import com.cupcakes.data.CupcakeDTO;
import java.util.List;

/**
 *
 * @author martin
 */
public class Controller {

//    public List<String> getIngredients();
//
//    public ingredientDAO getIngredient();

    public List<CupcakeDTO> getRecipes() {
        return new CupcakeDAO().getRecipes();
    }
    
    public CupcakeDTO getRecipe(String recipeName)
    {
        return new CupcakeDAO().getRecipe(recipeName);
    }
}
