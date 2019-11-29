/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ind;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class Calculator extends HttpServlet {

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter pw;
        response.setContentType("text/html");
        pw = response.getWriter();

        int num1 = Integer.parseInt(request.getParameter("first"));
        int num2 = Integer.parseInt(request.getParameter("second"));
        String operator = request.getParameter("operator");

        pw.println("<html><body>");
        if (operator.equals("add")) {
            pw.println("" + num1 + " + " + num2 + " = " + (num1 + num2));
        } else if (operator.equals("sub")) {
            pw.println("" + num1 + " - " + num2 + " = " + (num1 - num2));
        } else if (operator.equals("mul")) {
            pw.println("" + num1 + " * " + num2 + " = " + (num1 * num2));
        } else if (operator.equals("div")) {
            pw.println("" + num1 + " / " + num2 + " = " + (num1 / num2));
        } else {
            pw.println("" + num1 + " % " + num2 + " = " + (num1 % num2));
        }

        pw.println("</body></html>");
        pw.close();

    }
}
