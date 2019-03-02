/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cupcakes.presentation;

import com.cupcakes.logic.DTO.BottomDTO;
import com.cupcakes.logic.DTO.ToppingsDTO;
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
public class ShoppingCommand implements Command {

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
        StandardHTMLStrings html = new StandardHTMLStrings();
        
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Cupcake Creator</title>");
            out.println(html.standardHeader());
            out.println("</head>");
            out.println("<body>");
            out.println(html.standardMenu());
            out.println("<center><h1>Vælg cupcake indhold: </h1>");
            out.println("<form action=\"control\">");
            out.println("<input type=\"hidden\" name=\"origin\" value=\"cart\" />");
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
            out.println("</center><br><br>");
            out.println("<body>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}
