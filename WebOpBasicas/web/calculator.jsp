<%-- 
    Document   : calculator
    Created on : 7/09/2018, 08:54:47 AM
    Author     : jonat
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.rmi.NotBoundException"%>
<%@page import="java.net.MalformedURLException"%>
<%@page import="java.rmi.RemoteException"%>
<%@page import="java.rmi.Naming"%>
<%@page import="com.ipn.mx.rmi.Operaciones"%>
<html>
    <title>Calculadora vía RMI</title>
    <head>
        <link rel="stylesheet" href="estilo.css">
    </head>
    <body>
    <center>
        <form action="calculator.jsp" method="post">

            <label for="num1"><b>Núm. A</b></label>
            <input type="text" name ="num1"><br><br>
            <label for = "num2"><b>Núm. B</b></label>
            <input type="text" name="num2"><br><br>

            <input type ="radio" name = "r1" value="Add">+ 
            <input type = "radio" name = "r1" value="Sub">-<br>
            <input type="radio" name="r1" value ="mul">* 
            <input type = "radio" name="r1" value="div">/<br><br>
            <input type="radio" name="r1" value ="fact">!<br> 
            <input type="radio" name="r1" value ="fib">nFibonacci
            <input type="radio" name="r1" value ="desv">Desviación
            <input type="radio" name="r1" value ="var">Varianza
            <input type="radio" name="r1" value ="prom">Promedio


            <input type="submit" value="submit">
            
            <%@page language="java"%>
            <%
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
            %>
            </center>
            </body>
            </html>
