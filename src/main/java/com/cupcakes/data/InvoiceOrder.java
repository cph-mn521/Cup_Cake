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
            String bottomType = cake.getBottom().getType();
            String toppingType = cake.getToppings().getType();
            int quantity = cake.getQuantity();
            try
            {
                String query
                        = "INSERT INTO `ShoppingCart`(`cart_id`,`bottom_id`,`topping_id`,`quantity`) VALUES"
                        + "(" + shoppingCartIdCounter + ", " + 1 + ", " + 1 + ", " + quantity + ")";

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
