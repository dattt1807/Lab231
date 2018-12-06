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
        <span style="color: red">${requestScope.ERROR}</span>
        <form action="MainController" method="POST">
            Username: <input type="text" name="txtUsername" value="sa" 
                             pattern="[A-Za-z0-9]{2,30}"title="30 letter"/> </br>
            Password: <input type="password" name="txtPassword" value="123"
                             pattern="[A-Za-z0-9]{3,30}"title="30 letter"/></br>
            <input type="submit" value="Login" name="action" />   
        </form>
    </body>

    <script type="text/javascript">
        //onsubmit="return checkForm(this);"
        function checkForm(form)
        {
            // validation fails if the input is blank
            if (form.txtUsername.value == "") {
                alert("Error: Input is empty!");
                form.txtUsername.focus();
                return false;
            }

            // regular expression to match only alphanumeric characters and spaces
            var re = /^[\w ]+$/;

            // validation fails if the input doesn't match our regular expression
            if (!re.test(form.txtUsername.value)) {
                alert("Error: Input contains invalid characters!");
                form.txtUsername.focus();
                return false;
            }

            // validation was successful
            return true;
        }

    </script>
</html>
