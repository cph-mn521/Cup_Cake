/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cupcakes.presentation;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Niels (totalt stjålet fra tobias)
 */
public class PageLogin {

    public static void generateLogin(HttpServletResponse response) throws IOException {

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet FrontController - Login</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Login!</h1>");
            out.println("<form action =\"servlet1\" method =\"post\">");
            out.println("Name:<input type=\"text\" name=\"Usersame\"/><br/><br/>");
            out.println("Password:<input type =\"text\" name=\"Password\"/><br/><br/>");
            out.println("<input type=\"submit\" value=\"login\"/>");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}
