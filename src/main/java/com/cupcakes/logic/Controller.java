/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cupcakes.logic;

import com.cupcakes.data.BottomDTO;
import com.cupcakes.data.CupcakeDAO;
import com.cupcakes.data.ToppingsDTO;
import com.cupcakes.data.UserDAO;
import com.cupcakes.data.UserDTO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author martin
 */
public class Controller {

    public List<BottomDTO> fetchBottoms() {
        return new CupcakeDAO().getBottoms();
    }
    
    public List<ToppingsDTO> fetchToppings(){
        return new CupcakeDAO().getToppings();
    }
      
    public UserDTO fetchUser(String Username) throws SQLException{
        return new UserDAO().getUser(Username);
    }
}
