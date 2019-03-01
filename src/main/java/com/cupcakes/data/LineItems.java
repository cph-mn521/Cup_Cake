/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cupcakes.data;

/**
 *
 * @author martin bøgh
 */
public class LineItems {

    private CupcakeDTO cupcake;
    private int quantity;
    private int invoice_id;

    public LineItems(CupcakeDTO cupcake, int quantity, int invoice_id) {
        this.cupcake = cupcake;
        this.quantity = quantity;
        this.invoice_id = invoice_id;
    }

    public CupcakeDTO getCupcake() {
        return cupcake;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public int getInvoice_id() {
        return invoice_id;
    }

}
