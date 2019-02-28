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
 * @author martin Calculate the price of a window. The user will input the
 * height and width of a window in cm. Should be on a webpage, but for now as
 * query-parameters. Prices are stored in a database, the total price is
 * calculated and presented to the user as HTML. The window price is calculated
 * as Glass price + Frame price Window price  The price of glass is kr. 300,-
 * per m2 .  The price of frame type1 is kr. 100,- per m.  The price of frame
 * type2 is kr. 200,- per m.  The price of frame type3 is kr. 350,- per m.
 */
public class RecipesCommand extends Command {

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

        String cakename = request.getParameter("cake");
        
        Controller cc = new Controller();
//        boolean valid = g.isValid(height, width);
//        Window window = g.getWindow(height, width);
//        HttpSession session = request.getSession();
//
//        session.setAttribute("window", window);

//          How to add links:
//          "<a href=\"http://abcd.efg.com\" target=\"_blank\">http://abcd.efg.com</a>";


        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Opskrifter</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1><a href=\"http://localhost:8084/cakes2/cakes\" target=\"_self\">Opskrifter:</a></h1>");
            out.println("<br><br>");
//            for (CupcakeDTO recipe : cc.getRecipes()) {
//                if (cakename == null || cakename.equals(recipe.getName())) {
////                    String link = "<a href=\"Control?name=" + recipe.getName() + "\"</a>";
//                    String recipeLink = "<a href=\"http://localhost:8084/cakes2/cakes?cake="
//                            + recipe.getName() 
//                            + "\" target=\"_self\">"
//                            + recipe.getName() + "</a>";
//                    out.println("<h1>" + recipeLink + "</h1><br>"
//                            + "<h3>" + recipe.getInstructions() + "</h3><br>");
//                    for (IngredientDTO ingr : recipe.getIngredients()) {
//                        out.println("<br><li>" + ingr.getAmount() + " " + ingr.getIngredient() + "</li><br>");
//                    }
//                    String urle = "<img src=\"" + recipe.getImage().getImage() + "\"  width=\"250\"/>";
//                    out.println(urle);
//                    out.println("<br><br><br><br>");
//                }
//            }
            out.println("<body>");
            out.println("</body>");
            out.println("</html>");
        }
    }

}
