/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cupcakes.data;

/**
 *
 * @author Martin Brandstrup
 */
public class InvoiceOrder
{
    private ShoppingCart cart;
    
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
    public void saveInvoiceToDB()
    {
        
    }
}
