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
 * @author martin Calculate the price of a window. The user will input the
 * height and width of a window in cm. Should be on a webpage, but for now as
 * query-parameters. Prices are stored in a database, the total price is
 * calculated and presented to the user as HTML. The window price is calculated
 * as Glass price + Frame price Window price  The price of glass is kr. 300,-
 * per m2 .  The price of frame type1 is kr. 100,- per m.  The price of frame
 * type2 is kr. 200,- per m.  The price of frame type3 is kr. 350,- per m.
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
            out.println("<title>Shopping cart</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<br><br>");
            out.println("<h1>Vælg cupcake indhold: </h1>");
            out.println("<form action=\"cart\">");
            out.println("Topping:");
            out.println("<select id='toppings' name='Toppings'\">");
            out.println("<option></option>");
            for (ToppingsDTO topping : cc.fetchToppings()) {
                out.println("<option value=\"" + topping.getType() + "\">" + topping.getType() + "</option>");
            }
            out.println("/<select>");
            out.println("<br><br>");
            out.println("Bund:");
            out.println("<select id='bottoms' name='Bottoms'\">");
            out.println("<option></option>");
            for (BottomDTO bottom : cc.fetchBottoms()) {
                out.println("<option value=\"" + bottom.getType() + "\">" + bottom.getType() + "</option>");
            }
            out.println("</select>");
            out.println("<br><br>");
            out.println("Enter quantity : <input type=\"text\" name=\"quantity\"> <br>");
            out.println("<input type=\"submit\" value=\"Vælg\">");
            out.println("</form>");
            out.println("<br><br>");
            out.println("<body>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}
