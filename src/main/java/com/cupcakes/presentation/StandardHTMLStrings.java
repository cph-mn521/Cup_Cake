/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cupcakes.presentation;

/**
 *
 * @author martin
 */
public class StandardHTMLStrings {

    /**
     * Header man der skal bruges sammen med body for at få eens udseende sider
     *
     * @return
     */
    public String standardHeader() {
        return "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n"
                + "<style>\n"
                + "    .buttonGreen {\n"
                + "        background-color: #4CAF50;\n"
                + "        border: none;\n"
                + "        color: white;\n"
                + "        padding: 15px 25px;\n"
                + "        text-align: center;\n"
                + "        font-size: 16px;\n"
                + "        cursor: pointer;\n"
                + "    }\n"
                + "    .buttonBlue {\n"
                + "        background-color: #0066cc;\n"
                + "        border: 1px;\n"
                + "        color: white;\n"
                + "        padding: 15px 25px;\n"
                + "        text-align: center;\n"
                + "        font-size: 16px;\n"
                + "        cursor: pointer;\n"
                + "    }\n"
                + "    .buttonGreen:hover {\n"
                + "        background-color: green;\n"
                + "    }\n"
                + "    .buttonBlue:hover {\n"
                + "        background-color: blue;\n"
                + "    }\n"
                + "</style>\n";
    }

    /**
     * body man kan bruges sammen med header for at lave eens udseende sider
     *
     * @return
     */
    public String standardMenu() {
        return "<center>\n"
                + "        <a href=\"index.html\">"
                + "        <canvas id=\"myCanvas\" width=\"540\" height=\"70\" style=\"border:1px solid #d3d3d3;\">\n"
                + "            Your browser does not support the HTML5 canvas tag.</canvas>\n"
                + "\n"
                + "        <script>\n"
                + "            var c = document.getElementById(\"myCanvas\");\n"
                + "            var ctx = c.getContext(\"2d\");\n"
                + "            ctx.font = \"30px Arial\";\n"
                + "            ctx.strokeText(\"Velkommen til en verden af Cupcakes\", 10, 50);\n"
                + "            ;\n"
                + "        </script>\n"
                + "        </a>\n"
                + "\n"
                + "        <div style=\"background-color:black;color:white;padding:20px;\">\n"
                + "\n"
                + "            <img src=\"images/cc1.jpg\" alt=\"1\">\n"
                + "            <img src=\"images/cc2.jpg\" alt=\"2\">\n"
                + "            <img src=\"images/cc3.jpg\" alt=\"3\">\n"
                + "            <img src=\"images/cc4.jpg\" alt=\"4\">\n"
                + "            <img src=\"images/cc5.jpg\" alt=\"5\">\n"
                + "\n"
                + "            <br>  \n"
                + "            <br>\n"
                + "            <br>\n"
                + "            <br>\n"
                + "\n"
                + "\n"
                + "            <button class=\"buttonGreen\" onclick=\"window.location.href = 'control?origin=login'\">Login</button>\n"
                + "\n"
                + "            <button class=\"buttonBlue\" onclick=\"window.location.href = 'control?origin=registration'\">Registrering</button>\n"
                + "\n"
                + "            <button class=\"buttonGreen\" onclick=\"window.location.href = 'control?origin=shop'\">Shop</button>\n"
                + "\n"
                + "            <button class=\"buttonBlue\" onclick=\"window.location.href = 'control?origin=cart'\">Cart</button>\n"
                + "\n"
                + "            <button class=\"buttonGreen\" onclick=\"window.location.href = 'control?origin=admin'\">Admin</button>\n"
                + "\n"
                + "\n"
                + "        </div>\n"
                + "\n"
                + "        <br>       \n"
                + "    </center>";
    }
}
