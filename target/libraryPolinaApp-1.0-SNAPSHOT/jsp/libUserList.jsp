<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <title>list of library users</title>
</head>
<body>
<%@ include file="/jsp/_header.jsp"%>
</br>
</br>
<div>
    <jsp:include page="libUserForm.jsp"/>
</div>
<%--<form method="post" action="/libUserServlet">--%>
<%--    <label for="firstname">User name--%>
<%--        <input type="text" id="firstname" name="firstname">--%>
<%--    </label>--%>
<%--    <label for="surname">User surname--%>
<%--        <input type="text" id="surname" name="surname">--%>
<%--    </label>--%>
<%--    <label for="middlename">User middlename--%>
<%--        <input type="text" id="middlename" name="middlename">--%>
<%--    </label>--%>
<%--    <label for="passportID">passportID--%>
<%--        <input type="text" id="passportID" name="passportID">--%>
<%--    </label>--%>
<%--    <label for="address">address--%>
<%--        <input type="text" id="address" name="address">--%>
<%--    </label>--%>
<%--    <label for="dateOfBirth">Birthday--%>
<%--        <input type="text" id="dateOfBirth" name="dateOfBirth">--%>
<%--    </label>--%>
<%--    <label for="email">Email--%>
<%--        <input type="text"id="email" name="email">--%>
<%--    </label>--%>
<%--    <input type="submit" value="add library user">--%>
<%--</form>--%>

    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of Users</h2></caption>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Surname</th>
                <th>Date of birth</th>
                <th>Address</th>
                <th>Email</th>
                <th>Action</th>
            </tr>
            <c:forEach var="libUser" items="${libUsersFromServer}">
                <tr>
                    <td><c:out value="${libUser.id}" /></td>
                    <td>${libUser.firstname}</td>
                    <td>${libUser.surname}</td>
                    <td>${libUser.dateOfBirth}</td>
                    <td>${libUser.address}</td>
                    <td>${libUser.email}</td>
                    <td>
                        <a href="/libUserServlet/edit?id=<c:out value='${libUser.id}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="/libUserServlet/delete?id=<c:out value='${libUser.id}' />">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>


</body>
</html>
