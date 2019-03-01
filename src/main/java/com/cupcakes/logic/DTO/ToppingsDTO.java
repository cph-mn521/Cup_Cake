package com.cupcakes.logic.DTO;

import com.cupcakes.data.DAO.CupcakeDAO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author nille
 */
public class ToppingsDTO {

    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
     */
    String type;
    float price;
    int id;

    public ToppingsDTO(String type) {
       ToppingsDTO t = new CupcakeDAO().getTopping(type);
        this.type = type;
        this.price=(float) t.getPrice();
        this.id=t.getId();
    }

    public ToppingsDTO(String type, float price, int id) {
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
