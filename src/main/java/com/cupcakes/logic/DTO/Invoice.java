package com.cupcakes.logic.DTO;

import java.sql.Date;

/**
 *
 * @author martin bøgh
 */
public class Invoice {
    private int invoice_id;
    private int user_id;
    private int cart_id;
    private Date invoice_date;

    public Invoice(int invoice_id, int user_id, int cart_id, Date invoice_date) {
        this.invoice_id = invoice_id;
        this.user_id = user_id;
        this.cart_id = cart_id;
        this.invoice_date = invoice_date;
    }

    public int getInvoice_id() {
        return invoice_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getCart_id() {
        return cart_id;
    }

    public Date getInvoice_date() {
        return invoice_date;
    }
    
}
