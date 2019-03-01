/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cupcakes.presentation;

import com.cupcakes.data.BottomDTO;
import com.cupcakes.data.IngredientDTO;
import com.cupcakes.data.CupcakeDTO;
import com.cupcakes.data.LineItems;
import com.cupcakes.data.ToppingsDTO;
import com.cupcakes.logic.Controller;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author martin bøgh
 */
public class ShoppingCommand extends Command {

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
//        HttpSession session = request.getSession();
//
//        session.setAttribute("window", window);

        HttpSession session = request.getSession();
        session.setAttribute("cart", cc.fetchCart());

        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Cupcake Creator</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Vælg cupcake indhold: </h1>");
            out.println("<form action=\"cart\">");
            out.println("Topping:");
            out.println("<select id='toppings' name='Toppings' required>");
            out.println("<option></option>");
            for (ToppingsDTO topping : cc.fetchToppings()) {
                out.println("<option value=\"" + topping.getType() + "\">" + topping.getType() + "</option>");
            }
            out.println("/<select>");
            out.println("<br><br>");
            out.println("Bund:");
            out.println("<select id='bottoms' name='Bottoms' required>");
            out.println("<option></option>");
            for (BottomDTO bottom : cc.fetchBottoms()) {
                out.println("<option value=\"" + bottom.getType() + "\">" + bottom.getType() + "</option>");
            }
            out.println("</select>");
            out.println("<br><br>");
            out.println("Antal:");
            out.println("<select id='quantity' name='quantity' required>");
            out.println("<option></option>");
            out.println("<option>1</option>");
            out.println("<option>2</option>");
            out.println("<option>3</option>");
            out.println("<option>4</option>");
            out.println("<option>5</option>");
            out.println("<option>6</option>");
            out.println("</select>");
            out.println("<br><br>");
            out.println("<input type=\"submit\" value=\"Vælg\">");
            out.println("</form>");
            out.println("<br><br>");
            out.println("<body>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}