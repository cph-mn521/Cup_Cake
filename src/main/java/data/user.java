/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author Martin Wulff
 */
public class user {

    private String username, password;
    private double balance;

    public user(String username, String password) {
        this.username = username;
        this.password = password;
        balance = 0;
    }

    public user(String username, String password,double balance) {
        this.username = username;
        this.password = password;
        this.balance  = balance;
    }

    
    
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}
