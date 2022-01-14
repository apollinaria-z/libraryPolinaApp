<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link rel="stylesheet" href="css/pageStyle.css">
</head>
<body>

<%@ include file="/jsp/_header.jsp"%>

<h2>Welcome page of library</h2>

<%--<form action="" method="post">--%>
<%--    <button type="submit" name="button">new book</button>--%>
<%--</form>--%>

<h2>
    <a href="/libUserServlet/new">Add New User</a>
    &nbsp;&nbsp;&nbsp;
    <a href="/libUserServlet">User list</a>

</h2>

<div>
    <jsp:include page="jsp/bookList.jsp"/>
</div>
</body>
</html>
