/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cupcakes.presentation;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * command er blevet slanket til et interface i stedet for en abstract klasse
 * @author martin
 */
public interface Command {

    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;

}
