/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cupcakes.data;

import com.cupcakes.logic.DTO.LineItemsDTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author martin bøgh
 */
public class ShoppingCart {
    private List<LineItemsDTO> lineItems;

    public ShoppingCart() {
        lineItems = new ArrayList<>();
    }

    public ShoppingCart(List<LineItemsDTO> lineItems) {
        this.lineItems = lineItems;
    }
    
    public List<LineItemsDTO> getLineItems() {
        return lineItems;
    }
    
    public void addLineItem(LineItemsDTO lineitem){
        this.lineItems.add(lineitem);
    }
 
}
