<%-- 
    Document   : index
    Created on : 23-Jun-2024, 12:28:04 am
    Author     : Mohak Chavan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>

            form {
                border: 1px solid black;
                display: grid;
                grid-template-columns: 1fr 1fr;
                grid-gap: 1em 2em;
                padding: 10px;
            }

            #submit {
                grid-column: 1/3;
            }
            
            label {
                justify-self: end;
            }

        </style>
    </head>
    <body>
        <h1>Hello World from jsp.</h1>

        <h1><c:out value="Hello World JSTL"/></h1>

        <p><strong>
                <%=new java.util.Date()%>
            </strong></p>

        <p><strong>
                <%=pageContext.getServletContext().getContextPath()%>
            </strong></p>

        <p><strong>
                ${pageContext.servletContext.contextPath}
            </strong></p>

        <p><strong>
                <c:out value="<h7>${pageContext.servletContext.contextPath}</h7>"/>
            </strong></p>

        <p><strong>
                <c:url value="/testUrl"/>
            </strong></p>


            <form action="${pageContext.servletContext.contextPath}/users" method="POST">

            <label for="name">Name:</label>
            <input type="text" id="name" name="name"/>

            <label for="password">Password:</label>
            <input type="password" id="password" name="password"/>

            <input type="submit" id="submit" value="Submit"/>

        </form>

    </body>
</html>
