<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>list of books</title>
</head>
<body>

<table border="1" cellpadding="5">
    <caption><h2>Table of books</h2></caption>
    <tr>
        <th>RussianName</th>
        <th>Genres</th>
        <th>publicationYear</th>
      <th>authors</th>
        <th>Action</th>
    </tr>
    <c:forEach items="${booksFromServer}" var="book">
        <tr>
            <td>${book.nameRu}</td>
            <td>${book.genreList}</td>
            <td>${book.publicationYear}</td>
            <td>${book.authorList}</td>
<%--            <td>${book.amountAvaliable}</td>--%>
            <td>
                <a href="/bookServlet/edit?id=<c:out value='${book.id}' />">Edit</a>
                &nbsp;&nbsp;&nbsp;&nbsp;
                <a href="/bookServlet/delete?id=<c:out value='${book.id}' />">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
