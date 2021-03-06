/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cupcakes.logic.DTO;

import com.cupcakes.data.DAO.CupcakeDAO;

/**
 *
 * @author nille
 */
public class BottomDTO {

    String type;
    float price;
    int id;

    public BottomDTO(String type) {
        BottomDTO b = new CupcakeDAO().getBottom(type);
        this.type = type;
        this.price=(float) b.getPrice();
        this.id=b.getId();
    }


    public BottomDTO(String type, float price, int id) {
        this.type = type;
        this.price = price;
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    
}
