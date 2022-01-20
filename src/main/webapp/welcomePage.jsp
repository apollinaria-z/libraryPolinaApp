<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>

</head>
<body>

<jsp:include page="/jsp/_header.jsp" />
<h2>Welcome page of library</h2>
<h2>

  <a href="${pageContext.request.contextPath}/bookServlet/book?action=new">Create new book</a>
  &nbsp;
  <a href="${pageContext.request.contextPath}/libUserServlet/libuser?action=list">Reader management</a>

</h2>
<div>
  <jsp:include page="jsp/bookList.jsp"/>
</div>
</body>
</html>
