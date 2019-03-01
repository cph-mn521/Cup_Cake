/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cupcakes.data;

import java.sql.SQLException;

/**
 *
 * @author Martin Brandstrup
 */
public class InvoiceOrder
{

    private ShoppingCart cart;
    private static int shoppingCartIdCounter = 1;

    public InvoiceOrder(ShoppingCart cart)
    {
        this.cart = cart;
    }

    /**
     * Saves the ShoppingCart to the Database.
     * @author Martin Brandstrup
     * @param
     * @return
     */
    private void saveShoppingCartToDB()
    {
        for (LineItems cake : cart.getLineItems())
        {
            int bottomId = cake.getBottom().getId();
            int toppingId = cake.getToppings().getId();
            int quantity = cake.getQuantity();
            
            if(bottomId <1 || toppingId <1 || quantity <1)
            {
                System.out.println("There was a problem with your data");
                return;
            }
            
            try
            {
                String query
                        = "INSERT INTO `ShoppingCart`(`cart_id`,`bottom_id`,`topping_id`,`quantity`) VALUES"
                        + "(" + shoppingCartIdCounter + ", " + bottomId + ", " + toppingId + ", " + quantity + ")";

                int result = DB.getConnection().createStatement().executeUpdate(query);
                System.out.println(result + " rows added");
            } catch (SQLException ex)
            {
                System.out.println(ex);
            }
        }
    }

    /**
     * Saves the ShoppingCart (carried by the current InvoiceOrder object from 
     * which this method is called) as well as the Invoice to the Database.
     * @author Martin Brandstrup
     * @param
     * @return
     */
    public void saveOrderToDB()
    {
        
        
        shoppingCartIdCounter++;
    }
}
