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
    private UserDTO user;
    public static int shoppingCartIdCounter = 1;

     /**
     * Takes a ShoppingCart and saves all information to the Database. Creates
     * an Invoice and saves this as well.
     * @author Martin Brandstrup
     * @param cart the ShoppingCart with all the Cupcake LineItems to be used
     * @param user the user logged in.
     */
    public InvoiceOrder(ShoppingCart cart, UserDTO user)
    {
        this.cart = cart;
        this.user = user;
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
     * Makes sure the user can afford the order.
     * @author Martin Brandstrup
     * @param
     * @return
     */
    private void calculateTransactionCost()
    {
        double totalCost;
        
        for (LineItems cake : cart.getLineItems())
        {
            float bottomPrice = cake.getBottom().getPrice();
            float toppingPrice = cake.getToppings().getPrice();
            int quantity = cake.getQuantity();
            
            if(bottomPrice <1 || toppingPrice <1 || quantity <1)
            {
                System.out.println("There was a problem with your data");
                return;
            }
        }
        user.getBalance();
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
