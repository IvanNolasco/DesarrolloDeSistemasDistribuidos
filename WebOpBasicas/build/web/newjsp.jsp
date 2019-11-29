
<HTML>
    <HEAD>
        <TITLE>Using Buttons</TITLE>
    </HEAD>

    <BODY>
        <% 
            //if(request.getParameter("buttonName") != null) {
            if(request.getParameterNames() != null) {
        %>
            You clicked 
            <%= request.getParameter("buttonName") %>
        <%
            }
        %>

        <FORM NAME="form1" METHOD="POST">
            <INPUT TYPE="HIDDEN" NAME="buttonName">
            
            A: <input type = "number" name = "numa">
            B: <input type = "number" name = "numb">
            
            <INPUT TYPE="BUTTON" VALUE="Suma" ONCLICK="button1()">
            <INPUT TYPE="BUTTON" VALUE="Resta" ONCLICK="button2()">
            <INPUT TYPE="BUTTON" VALUE="Producto" ONCLICK="button3()">
            <INPUT TYPE="BUTTON" VALUE="Cociente" ONCLICK="button4()">
            
        </FORM>

        <SCRIPT LANGUAGE="JavaScript">
            <!--
            function button1()
            {
                document.form1.buttonName.value = "button 1";
                out.println();
                
                form1.submit();
            }    
            function button2()
            {
                document.form1.buttonName.value = "button 2";
                form1.submit();
            }    
            // --> 
        </SCRIPT>
    </BODY>
</HTML>
