/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cupcakes.data;

import java.util.List;

/**
 *
 * @author martin bøgh
 */
public class ShoppingCart {
    private List<LineItems> lineItems;
    private int quantity;

    public ShoppingCart(List<LineItems> lineItems, int quantity) {
        this.lineItems = lineItems;
        this.quantity = quantity;
    }
    
    public List<LineItems> getLineItems() {
        return lineItems;
    }

    public int getQuantity() {
        return quantity;
    }
 
}
