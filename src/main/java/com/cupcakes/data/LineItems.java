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

    private ToppingsDTO toppings;
    private BottomDTO bottom;
    private int invoice_id;

    public LineItems(ToppingsDTO toppings, BottomDTO bottom, int invoice_id) {
        this.toppings = toppings;
        this.bottom = bottom;
        this.invoice_id = invoice_id;
    }

    public BottomDTO getBottom() {
        return bottom;
    }

    public ToppingsDTO getToppings() {
        return toppings;
    }

    public int getInvoice_id() {
        return invoice_id;
    }

}
