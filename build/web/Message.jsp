<%-- 
    Document   : Message
    Created on : Nov 25, 2019, 10:32:43 PM
    Author     : Tara Ram Goyal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Message</title>
    </head>
    <body>
        <center>
            <h1>
                <%=request.getAttribute("message")%>
                <a href="index.jsp">Go to home</a>
            </h1>
        </center>
    </body>
</html>
