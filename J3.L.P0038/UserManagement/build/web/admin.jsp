<%-- 
    Document   : admin
    Created on : Dec 3, 2018, 10:58:33 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="emvh" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
    </head>
    <body>
        <div>
            <h1>Hello, ${sessionScope.USERNAME} !</h1>
            <emvh:url var="Logout" value="MainController">
                <emvh:param name="action" value="Logout" />
            </emvh:url>
            <a href="${Logout}">Logout</a>
        </div>
        <emvh:url var="Search" value="MainController">
            <emvh:param name="txtSearch" value="" />
            <emvh:param name="action" value="Search" />
            <emvh:param name="searchRole" value="ALL" />
        </emvh:url>
            <a href="${Search}">| All(${requestScope.TOTAL}) |</a>
        <emvh:if test="${not empty requestScope.listRole}">
            <emvh:forEach var="role" items="${requestScope.listRole}">
                <emvh:url var="Search" value="MainController">
                    <emvh:param name="txtSearch" value="" />
                    <emvh:param name="action" value="Search" />
                    <emvh:param name="searchRole" value="${role.roleID}" />
                </emvh:url>
                <a href="${Search}">${role.roleName}
                    (<emvh:set var="myParam" value="${role.roleID}"/>
                    <emvh:out value="${map[myParam]}"/>) |
                </a>
            </emvh:forEach>
        </emvh:if>
        <form action="MainController" method="POST">
            <input type="hidden" name="searchRole" value="${requestScope.ROLE}"/>
            <input type="text" name="txtSearch"/>
            <input type="submit" name="action" value="Search"/> </br>
            <emvh:if test="${not empty requestScope.listRole}">
                <select id="select" name="slRole">
                    <emvh:forEach var="role" items="${requestScope.listRole}">
                        <option value="${role.roleID}">Change Role to ${role.roleName}</option>
                    </emvh:forEach>
                </select>
            </emvh:if>
            <input type="button" value="Change" onclick="changeToAdmin()"/>
            <span style="font-weight: bold">${fn:length(listUser)} Items</span>
            <emvh:if test="${not empty requestScope.listUser}">
                <table id="tblDisplay" border="1">
                    <thead>
                        <tr>
                            <th>No</th>
                            <th><input onclick="toggle(this)" type="checkbox" name="checkAll" value="checkAll"/> Username</th>
                            <th>Name</th>
                            <th>Email</th>
                            <th>Role</th>
                        </tr>
                    </thead>
                    <tbody>
                        <emvh:forEach var="user" items="${requestScope.listUser}" varStatus="counter">
                            <tr>
                                <td>${counter.count}</td>
                                <td><input class="checkboxlist" type="checkbox" name="checkboxlist" value="${user.userID}"/>${user.userID}</td>
                                <td>${user.lastName} ${user.firstName}</td>
                                <td>${user.email}</td>
                                <td>${user.role}</td>
                            </tr>
                        </emvh:forEach>

                    </tbody>
                    <tfoot>
                        <tr>
                            <th>No</th>
                            <th>Username</th>
                            <th>Name</th>
                            <th>Email</th>
                            <th>Role</th>
                        </tr>
                    </tfoot>
                </table>
            </emvh:if>
        </form>
    </body>
    <script type="text/javascript">
        function itemSelected() {
            var checkedValue = document.querySelector('.checkboxlist:checked').value;
            alert(checkedValue);
        }

        function changeToAdmin() {
            var jsAtt = '<emvh:out value="${listUser}"/>';
            console.log(jsAtt);
            var user = document.forms[0];
            var list = [];
            var i;
            for (i = 0; i < user.length; i++) {
                if (user[i].checked) {
                    if (user[i].value != "checkAll") {
                        list.push(user[i].value);
                    }

                }
            }

            var e = document.getElementById("select");
            var strUser = e.options[e.selectedIndex].value;
            alert(list + strUser);
        }

        function toggle(source) {
            var checkboxes = document.querySelectorAll('input[type="checkbox"]');
            for (var i = 0; i < checkboxes.length; i++) {
                if (checkboxes[i] != source)
                    checkboxes[i].checked = source.checked;
            }
        }

//            function setTotalItem() {
//            //Total row equal with rows minus two (header and footer)
//            var rows = (document.getElementById("tblDisplay").getElementsByTagName("tr").length - 2);
//            var itemSelected = document.getElementById("itemSelected");
//            itemSelected.textContent = rows + " items";
//        }


    </script>
</html>