/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cupcakes.presentation;

import com.cupcakes.data.cupcakeDTO;
import com.cupcakes.logic.Controller;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author brandstrup
 */
public class RecipeCommand extends Command {

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

        Controller cc = new Controller();
        String recipeName = request.getParameter("cake");

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Opskrifter</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Opskrift:</h1>");
            out.println("<br><br>");
            out.println("<li>Navn: " + cc.getRecipe(recipeName).getName() + "</li>");
            out.println("<li>Instruktioner: " + cc.getRecipe(recipeName).getInstructions()+ "</li>");
            out.println("<li>Ratings: " + cc.getRecipe(recipeName).getRatings()+ "</li>");
            out.println("<body>");
            out.println("</body>");
            out.println("</html>");
        }
    }

}
