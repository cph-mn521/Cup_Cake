/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cupcakes.data;

/**
 *
 * @author nille
 */
public class BottomDTO {

    String type;
    float price;
    int id;

    public BottomDTO(String type) {
        this.type = type;
        this.price=(float)0.0;
        this.id=13; //tilfældigt tal
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
