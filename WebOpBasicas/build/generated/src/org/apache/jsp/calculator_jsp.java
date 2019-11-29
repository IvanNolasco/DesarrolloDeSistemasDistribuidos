package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.ArrayList;
import java.rmi.NotBoundException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.Naming;
import com.ipn.mx.rmi.Operaciones;

public final class calculator_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<html>\n");
      out.write("    <title>Calculadora vía RMI</title>\n");
      out.write("    <head>\n");
      out.write("        <link rel=\"stylesheet\" href=\"estilo.css\">\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("    <center>\n");
      out.write("        <form action=\"calculator.jsp\" method=\"post\">\n");
      out.write("\n");
      out.write("            <label for=\"num1\"><b>Núm. A</b></label>\n");
      out.write("            <input type=\"text\" name =\"num1\"><br><br>\n");
      out.write("            <label for = \"num2\"><b>Núm. B</b></label>\n");
      out.write("            <input type=\"text\" name=\"num2\"><br><br>\n");
      out.write("\n");
      out.write("            <input type =\"radio\" name = \"r1\" value=\"Add\">+ \n");
      out.write("            <input type = \"radio\" name = \"r1\" value=\"Sub\">-<br>\n");
      out.write("            <input type=\"radio\" name=\"r1\" value =\"mul\">* \n");
      out.write("            <input type = \"radio\" name=\"r1\" value=\"div\">/<br><br>\n");
      out.write("            <input type=\"radio\" name=\"r1\" value =\"fact\">!<br> \n");
      out.write("            <input type=\"radio\" name=\"r1\" value =\"fib\">nFibonacci\n");
      out.write("            <input type=\"radio\" name=\"r1\" value =\"desv\">Desviación\n");
      out.write("            <input type=\"radio\" name=\"r1\" value =\"var\">Varianza\n");
      out.write("            <input type=\"radio\" name=\"r1\" value =\"prom\">Promedio\n");
      out.write("\n");
      out.write("\n");
      out.write("            <input type=\"submit\" value=\"submit\">\n");
      out.write("            \n");
      out.write("            \n");
      out.write("            ");

                if (request.getParameter("num1") != null) {
                    String[] cjtoA = request.getParameter("num1").split(",");

                    ArrayList<Double> conjuntoA = new ArrayList<>();
                    for (int i = 0; i < cjtoA.length; i++) {
                        conjuntoA.add(Double.parseDouble(cjtoA[i]));
                    }

                    String[] cjtoB = request.getParameter("num2").split(",");

                    ArrayList<Double> conjuntoB = new ArrayList<>();
                    for (int i = 0; i < cjtoB.length; i++) {
                        conjuntoB.add(Double.parseDouble(cjtoB[i]));
                    }
                    
                    double num1 = conjuntoA.get(0);
                    double num2 = conjuntoB.get(0);

                    String operation = request.getParameter("r1");

                    try {
                        Operaciones ope = (Operaciones) Naming.lookup("rmi://localhost/operaciones");
                        switch (operation) {
                            case "Add":
                                out.println(num1 + "+" + num2 + "=" + ope.sumar(num1, num2));
                                break;
                            case "Sub":
                                out.println(num1 + "-" + num2 + "=" + ope.restar(num1, num2));
                                break;
                            case "mul":
                                out.println(num1 + "*" + num2 + "=" + ope.producto(num1, num2));
                                break;
                            case "div":
                                out.println(num1 + "/" + num2 + "=" + ope.cociente(num1, num2));
                                break;
                            case "fib":
                                out.println(num1 + "/" + num2 + "=" + ope.cociente(num1, num2));
                                break;
                            case "fact":
                                out.println(conjuntoA + "!=" + ope.factorial(num1) +"<br>");
                                out.println(conjuntoB + "!=" + ope.factorial(num2));
                                break;
                            case "var":
                                out.println(conjuntoA + ".varianza()=" + ope.varianza(conjuntoA)+"<br>");
                                out.println(conjuntoB + ".varianza()=" + ope.varianza(conjuntoB));
                                break;
                            case "prom":
                                out.println(conjuntoA + ".promedio()=" + ope.promedio(conjuntoA)+"<br>");
                                out.println(conjuntoB + ".promedio()=" + ope.promedio(conjuntoB));
                                break;
                            case "desv":
                                out.println(conjuntoA + ".desviación()=" + ope.desviacion(conjuntoA)+"<br>");
                                out.println(conjuntoB + ".desviación()=" + ope.desviacion(conjuntoB));
                                break;
                            default:
                                break;

                        }

                    } catch (NotBoundException | MalformedURLException | RemoteException ex) {
                        ex.printStackTrace();
                    }

                }
            
      out.write("\n");
      out.write("            </center>\n");
      out.write("            </body>\n");
      out.write("            </html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
