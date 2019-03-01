/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cupcakes.data;

import java.util.List;

/**
 *
 * @author martin
 */
public class CupcakeDTO {

    private BottomDTO bottom;
    private ToppingsDTO topping;
    double totalPrice;

    public CupcakeDTO(ToppingsDTO topping, BottomDTO bottom) {
        this.bottom = bottom;
        this.topping = topping;
        this.totalPrice = bottom.getPrice()+topping.getPrice();
    }

    public BottomDTO getBottom() {
        return bottom;
    }

    public ToppingsDTO getTopping() {
        return topping;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

}
