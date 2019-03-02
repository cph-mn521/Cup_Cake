package com.cupcakes.logic.DTO;

import com.cupcakes.logic.DTO.LineItemsDTO;
import java.util.ArrayList;
import java.util.List;

/**
 * Entitets klasse til at indholde shopping cart
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

    
    /**
     * Add single lineitem object to list
     * 
     * @param lineitem 
     */
    public void addLineItem(LineItemsDTO lineitem) {
        this.lineItems.add(lineitem);
    }

}
