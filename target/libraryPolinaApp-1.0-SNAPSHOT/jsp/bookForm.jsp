<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>book form</title>
  <div><jsp:include page="/jsp/_header.jsp" /></div>
</head>
<body>

<%@ include file="/jsp/_header.jsp"%>

        <c:if test="${book != null}">
    <form action="/bookServlet/book?action=update" method="post" enctype="multipart/form-data">
        </c:if>
        <c:if test="${book == null}">
            <form action="/bookServlet/book?action=insert" method="post" enctype="multipart/form-data">
        </c:if>
                <table border="1" cellpadding="5">
                    <caption>
                        <h2>
                            <c:if test="${book != null}">
                                Edit book
                            </c:if>
                            <c:if test="${book == null}">
                                Add book
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
                  <tr>
                    <th>Select Genre:&nbsp;</th>
                    <td>
                        <c:forEach items="${genreIdMap}" var="entry">
                            <input type="checkbox" name="selectedGenre"
                                   value="${entry.key}" checked />${entry.value}
                        </c:forEach>
                    </td>
                  </tr>

                  <tr>
                    <th>Images loading:</th>
                    <td>
                    Put an image of book:
                      <input type="file" id="file" name="file" multiple>
                    </td>
                  </tr>



                  <tr>
                    <td colspan="2" align="center">
                      <input type="submit" value="SAVE"/>
                    </td>
                  </tr>
                </table>
            </form>
    </form>

</body>
</html>
