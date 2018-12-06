<%-- 
    Document   : insert
    Created on : Dec 6, 2018, 9:05:31 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="emvh"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insert Page</title>
        <style>
            tr {
                padding: 10px;
                height: 30px;
            }
        </style>
    </head>

    <body>
        <h1>Add new User</h1>
        <h5>Create a bran new user and and them to this site</h5>
        <span style="color: red">${requestScope.ERROR}</span>
        <form action="MainController" method="POST" onsubmit="return checkForm(this);">
            <table id="tblTable">
                <tbody>
                    <tr>
                        <td>UserID(required)</td>
                        <td><input type="text" name="txtUserID" required="true" 
                                   pattern="[A-Za-z0-9]{3,30}" 
                                   title="This field must have from 3 to 30 letter and have no special character"/></td>
                
                    </tr>
                    <tr>
                        <td>Email(required)</td>
                        <td><input type="email" name="txtEmail" required="true"/></td>
                    </tr>
                    <tr>
                        <td>First Name</td>
                        <td><input type="text" name="txtFirstName"
                                   pattern="[A-Za-z\s]{0,30}" 
                                   title="First Name does not contain number or special character"/></td>
                    </tr>
                    <tr>
                        <td>Last Name</td>
                        <td>
                            <input type="text" name="txtLastName"
                                   pattern="[A-Za-z]{0,30}" 
                                   title="Last Name does not contain number or special character"/>
                        </td>
                    </tr>
                    <tr>
                        <td>Password</td>
                        <td>
                            <input type="password" name="txtPassword"
                                   pattern="[A-Za-z0-9]{0,30}" title="Password does not special character"/>
                        </td>
                    </tr>
                    <tr>
                        <td>Re-Password</td>
                        <td>
                            <input type="password" name="txtRePassword"
                                   pattern="[A-Za-z0-9]{0axws,30}" title="Re-Password does not special character"/>
                        </td>
                    </tr>
                    <tr>
                        <td>Send User Notification</td>
                        <td>
                            <input type="checkbox" name="chkNotification"/> Send the new user an email about their account
                        </td>
                    </tr>
                    <tr>
                        <td>Role</td>
                        <td>
                            <select name="slRole">
                                <emvh:forEach var="role" items="${sessionScope.listRole}">
                                    <option value="${role.roleID}">${role.roleName}</option>
                                </emvh:forEach>
                            </select>
                        </td>
                    </tr>
                </tbody>
            </table>
            <input type="submit" value="Add new User" name="action"/>
        </form>
    </body>
    <script type="text/javascript">
        function checkForm(form) {
            if (form.txtPassword.value != form.txtRePassword.value) {
                alert("Password and Repassword doesn't match !");
                return false;
            }
            return true;
        }
    </script>
</html>
