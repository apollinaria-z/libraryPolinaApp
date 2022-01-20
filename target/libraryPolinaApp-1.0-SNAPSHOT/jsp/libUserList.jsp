<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <title>list of library users</title>
</head>
<body>
<div><jsp:include page="/jsp/_header.jsp" /></div>

</br>
</br>
<%--<div>--%>
<%--    <jsp:include page="libUserForm.jsp"/>--%>
<%--</div>--%>
<div>
  <a href="libUser?action=new">New User</a>
</div>

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
                      <a href="libUser?action=edit&id=<c:out value='${libUser.id}' />">Edit</a>
                      &nbsp;&nbsp;
                      <a href="libUserServlet/libUser?action=delete&id=<c:out value='${libUser.id}' />">Delete</a>
<%--                      <a href="edit?id=<c:out value='${libUser.id}' />">Edit</a>--%>


                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>


</body>
</html>
