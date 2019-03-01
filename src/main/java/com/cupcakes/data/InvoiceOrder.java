/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cupcakes.data;

import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 *
 * @author Martin Brandstrup
 */
public class InvoiceOrder
{

    private ShoppingCart cart;
    private UserDTO user;
    private double totalCost;
    public static int shoppingCartIdCounter = 1;

    /**
     * Takes a ShoppingCart and saves all information to the Database. Creates
     * an Invoice and saves this as well.
     *
     * @author Martin Brandstrup
     * @param cart the ShoppingCart with all the Cupcake LineItems to be used
     * @param user the current user logged in the session
     */
    public InvoiceOrder(ShoppingCart cart, UserDTO user)
    {
        if (cart == null || user == null || cart.getLineItems().isEmpty())
        {
            throw new IllegalArgumentException();
        }
        this.cart = cart;
        this.user = user;
        this.totalCost = 0;
    }

    /**
     * Saves the ShoppingCart to the Database.
     *
     * @author Martin Brandstrup
     */
    private void saveShoppingCartToDB()
    {
        for (LineItems cake : cart.getLineItems())
        {
            int bottomId = cake.getCupcake().getBottom().getId();
            int toppingId = cake.getCupcake().getTopping().getId();
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
    }

    /**
     * Makes sure the user can afford the order.
     *
     * @author Martin Brandstrup
     * @return true if the user can afford the order
     */
    private boolean calculateTransactionCost()
    {
        for (LineItems cake : cart.getLineItems())
        {
            float bottomPrice = cake.getCupcake().getBottom().getPrice();
            float toppingPrice = cake.getCupcake().getTopping().getPrice();
            int quantity = cake.getQuantity();

            //Guard. Er der flere conditions der skal tilføjes? Skal den kaste en exception istedet?
            if (bottomPrice < 1 || toppingPrice < 1 || quantity < 1)
            {
                System.out.println("There was a problem with your data");
                return false;
            }

            double cakeCost = (bottomPrice + toppingPrice) * quantity;
            totalCost += cakeCost;
        }
        if (totalCost > user.getBalance())
        {
            System.out.println("The user cannot afford the order");
            return false;
        } else
        {
            return true;
        }
    }

    /**
     * Subtracts the total cost of the order from the user's balance account.
     *
     * @author Martin Brandstrup
     */
    private void payForOrder()
    {
        try
        {
            String query
                    = "UPDATE User\n"
                    + "SET User.`balance` = " + (user.getBalance() - totalCost) + "\n"
                    + "WHERE User.`user_id` = " + user.getUserId() + ";";

            int result = DB.getConnection().createStatement().executeUpdate(query);
            System.out.println(result + " rows changed");
        } catch (SQLException ex)
        {
            System.out.println(ex);
        }
    }

    /**
     * Saves the ShoppingCart (carried by the current InvoiceOrder object from
     * which this method is called) as well as the Invoice to the Database.
     * Subtracts the total cost of the order from the user's balance account.
     *
     * @author Martin Brandstrup
     */
    public void saveOrderToDB()
    {
        if (calculateTransactionCost() == false)
        {
            return;
        }

        payForOrder();
        saveShoppingCartToDB();

        try
        {
            String query
                    = "INSERT INTO `Invoice`(`user_id`,`cart_id`,`invoice_date`)"
                    + "VALUES ("
                    + user.getUserId() + ","
                    + shoppingCartIdCounter + ", '"
                    + LocalDateTime.now() + "')";

            int result = DB.getConnection().createStatement().executeUpdate(query);
            System.out.println(result + " rows added");
        } catch (SQLException ex)
        {
            System.out.println(ex);
        }

        shoppingCartIdCounter++;
    }
}
