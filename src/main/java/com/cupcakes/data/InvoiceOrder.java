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
     *
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
        shoppingCartIdCounter++;
    }

    /**
     *
     * @author Martin Brandstrup
     * @param
     * @return
     */
    public void saveInvoiceToDB()
    {

    }
}
