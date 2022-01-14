<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>book form</title>
</head>
<body>

        <c:if test="${book != null}">
    <form action="/bookServlet/update" method="post">
        </c:if>
        <c:if test="${book == null}">
            <form action="/bookServlet/insert" method="post">
        </c:if>
                <table border="1" cellpadding="5">
                    <caption>
                        <h2>
                            <c:if test="${book != null}">
                                Edit User
                            </c:if>
                            <c:if test="${book == null}">
                                Add New User
                            </c:if>
                        </h2>
                    </caption>
                    <c:if test="${book != null}">
                        <input type="hidden" name="id" value="<c:out value='${book.id}' />"/>
                    </c:if>
                    <tr>
                        <th>Russian Name(*):</th>
                        <td>
                            <input type="text" name="nameRu" size="45"
                                   value="<c:out value='${book.nameRu}' />"
                            />
                        </td>
                    </tr>
                    <tr>
                        <th>Original Name(*):</th>
                        <td>
                            <input type="text" name="nameOrigin" size="45"
                                   value="<c:out value='${book.nameOrigin}' />"
                            />
                        </td>
                    </tr>
                    <tr>
                        <th>Cost(*):</th>
                        <td>
                            <input type="text" name="cost" size="5"
                                   value="<c:out value='${book.cost}' />"
                            />
                        </td>
                    </tr>
                    <tr>
                        <th>Price per day(*):</th>
                        <td>
                            <input type="text" name="dayPrice" size="5"
                                   value="<c:out value='${book.dayPrice}' />"
                            />
                        </td>
                    </tr>
                    <tr>
                        <th>Year of publication(*):</th>
                        <td>
                            <input type="text" name="publicationYear" size="5"
                                   value="<c:out value='${book.publicationYear}' />"
                            />
                        </td>
                    </tr>
                    <tr>
                        <th>Number of Pages(*):</th>
                        <td>
                            <input type="text" name="pages" size="5"
                                   value="<c:out value='${book.pages}' />"
                            />
                        </td>
                    </tr>

                    Select Genre:&nbsp;

                        <c:forEach items="${listCategory}" var="genre">
                            <input type="checkbox" name="selected" value="${genre.id}" checked />${genre.name}
                        </c:forEach>
<%--String[] selectedStudentIds = request.getParameterValues("selected");-->


<%--                    <select name="genre">--%>
<%--                        <c:forEach var="genre" items="${genreList}">--%>
<%--                            <option value="<c:out value='${genre}' />"--%>
<%--                                    <c:if test="${param.selectValue == genre})"> selected </c:if>  >--%>
<%--                                <c:out value="${genre}" />--%>
<%--                            </option>--%>
<%--                        </c:forEach>--%>
<%--                    </select>--%>
                </table>
            </form>
    </form>

</body>
</html>
