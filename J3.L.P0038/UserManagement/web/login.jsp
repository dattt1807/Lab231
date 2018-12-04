<%-- 
    Document   : login
    Created on : Dec 3, 2018, 10:58:26 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <h1>Login Page</h1>
        <form action="MainController" method="POST">
            Username: <input type="text" name="txtUsername" value="sa"/> </br>
            Password: <input type="password" name="txtPassword" value="123"/> </br>
            <input type="submit" value="Login" name="action"/>   
        </form>
    </body>
</html>
