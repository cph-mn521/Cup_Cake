/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cupcakes.presentation;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Niels (totalt stjålet fra tobias)
 */
public class PageLogin {

    public static void generateLogin(HttpServletResponse response) throws IOException {
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
 /*
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>CupCake Factory - Login</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Please Log in:</h1>");
            out.println("<form action =\"LoggedIn\" method =\"post\">");
            out.println("Name:<input type=\"text\" name=\"Usersame\"/><br/><br/>");
            out.println("Password:<input type =\"password\" name=\"Password\"/><br/><br/>");
            out.println("<input type=\"submit\" value=\"Login\"/>");
            out.println("</form>");
            out.println("<form action=\"Create User\" method =\"post\">");
            out.println("<input type=\"submit\" value=\"Create User\"/>");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
             */
            out.println("<!DOCTYPE html");
            out.println("<html>");
            out.println("<head> <title> Login </title></head>");
            out.println("<body>");
            out.println("<form action =\"FrontController\">");
            out.println("<div class=\"container\">");
            out.println("<label for=\"uname\"><b>Username</b></label>");
            out.println("<input type=\"text\" placeholder=\"Enter Username\" name=\"uname\" required>");
            out.println("<label for=\"psw\"><b>Password</b></label>");
            out.println("<input type=\"password\" placeholder=\"Enter Password\" name=\"psw\" required>");
            out.println("<button type=\"submit\">Login</button>");
            out.println("</form> </div>");
            out.println("<form action =\"Frontcontroller\">");
            out.println("<div class=\"container\">");
            out.println("<button type\"submit\">Create User<value=\"createUser\"/>");
            out.println("</form> </div>");
            out.println("</body></html>");

        }
    }
}
