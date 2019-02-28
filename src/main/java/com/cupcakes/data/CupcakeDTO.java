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

    BottomDTO bottom;
    ToppingsDTO topping;
    double totalPrice;

    public CupcakeDTO(BottomDTO bottom, ToppingsDTO topping) {
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