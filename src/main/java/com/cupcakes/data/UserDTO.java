/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cupcakes.data;

/**
 *
 * @author Martin Wulff
 */
public class UserDTO {

    private int userId;
    private String name, email, password;
    private double balance;

    UserDTO(int userId, String name, String email, String password, double balance) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public double getBalance() {
        return balance;
    }

}
