/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cupcakes.presentation;

import com.cupcakes.data.IngredientDTO;
import com.cupcakes.data.CupcakeDTO;
import com.cupcakes.logic.Controller;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author martin bøgh
 */
public class LoginCommand extends Command {

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
//        Controller c = new Controller();
        
//        HttpSession session = request.getSession();

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Login</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<br><br>");
            out.println("<form action=\"cakes\" method=\"post\">");
            out.println("	Enter username : <input type=\"text\" name=\"username\"> <BR>");
            out.println("	Enter password : <input type=\"password\" name=\"password\"> <BR>");
            out.println("	<input type=\"submit\" />");
            out.println("</form>");
            out.println("<body>");
            out.println("</body>");
            out.println("</html>");
        }
    }

}
