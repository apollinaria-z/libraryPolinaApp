<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>book form</title>
</head>
<body>
<%@ include file="/jsp/_header.jsp" %>

  <form action="/bookServlet/book?action=insert" method="post" enctype="multipart/form-data">
    <table border="1" cellpadding="5">
      <caption>
        <h2>Add book</h2>
      </caption>
      <tr>
        <th>Russian Name(*):</th>
        <td>
          <input type="text" name="nameRu" size="45"/>"
        </td>
      </tr>
      <tr>
        <th>Original Name(*):</th>
        <td>
          <input type="text" name="nameOrigin" size="45"/>
        </td>
      </tr>
      <tr>
        <th>Cost(*):</th>
        <td>
          <input type="number" name="cost" size="5"/>
        </td>
      </tr>
      <tr>
        <th>Price per day(*):</th>
        <td>
          <input type="number" name="dayPrice" size="5"/>
        </td>
      </tr>
      <tr>
        <th>Year of publication(*):</th>
        <td>
          <input type="number" name="publicationYear" size="5"/>
        </td>
      </tr>
      <tr>
        <th>Number of Pages(*):</th>
        <td>
          <input type="number" name="pages" size="5"/>
        </td>
      </tr>
      <tr>
        <th>Select Genre:&nbsp;</th>
        <td>
          <c:forEach items="${genreIdMap}" var="entry">
            <input type="checkbox" name="selectedGenre"
                   value="${entry.value}" checked/>${entry.value}
          </c:forEach>
        </td>
      </tr>

      <tr>
        <th>Images loading:</th>
        <td>
          Put an image of book:
          <input type="file" id="file" name="imageBook" multiple/>
        </td>
      </tr>
      <tr>
        <div>
            <c:forEach var="i" begin="1" end="${authorsNumber}">
<%--            <form action="/authorServlet/author?action=insert" method="post" enctype="multipart/form-data">--%>
              <th>Author form:</th>
              <th>Author name:</th>
              <td>
                <input type="text" name=("firstname"+i) size="25"/>
              </td>
              <th>Author surname:</th>
              <td>
                <input type="text" name=("surname"+i) size="25"/>
              </td>
              <th>Photos:</th>
              <td>
                <input type="file" name=("imageAuthor"+i)/>
              </td>

<%--                <td colspan="2" align="center">--%>
<%--                  <input type="submit" value="SAVE AUTHOR"/>--%>
<%--                </td>--%>
<%--            </form>--%>
            </c:forEach>
        </div>
      </tr>
      <tr>
        <td colspan="2" align="center">
          <input type="submit" value="SAVE BOOK"/>
        </td>
      </tr>
  </table>
</form>


</body>
</html>
