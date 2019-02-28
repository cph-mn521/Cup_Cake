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
 * @author Niels Bang
 */
public class PageCreateUser {

    static void generateUser(HttpServletResponse response, String msg) throws IOException {
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>CupCake Factory - Login</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Please enter your information:</h1>");
            out.println("<strong>" + msg + "</strong>");
            out.println("<form action =\"PageLoggedIn\" method =\"post\">");
            out.println("Name:<input type=\"text\" name=\"Usersame\"/><br/><br/>");
            out.println("Password:<input type =\"password\" name=\"Password\"/><br/><br/>");
            out.println("Confirm Password:<input type =\"password\" name=\"Password2\"/><br/><br/>");
            out.println("Email: <input type =\"text\" name=\"Email\"/><br/><br/>");
            out.println("<input type=\"submit\" value=\"Create User\"/>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}
