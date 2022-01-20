<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>user form</title>
</head>
<body>
<%@ include file="/jsp/_header.jsp" %>
<div align="center">
    <c:if test="${libUser != null}">
    <form action="libUserServlet/libUser?action=update" method="post"> <!-- onsubmit="return validateForm()"-->
        </c:if>
        <c:if test="${libUser == null}">
        <form action="libUserServlet/libUser?action=insert" method="post">
            </c:if>
            <table border="1" cellpadding="5">
                <caption>
                    <h2>
                        <c:if test="${libUser != null}">
                            Edit User
                        </c:if>
                        <c:if test="${libUser == null}">
                            Add New User
                        </c:if>
                    </h2>
                </caption>
                <c:if test="${libUser != null}">
                    <input type="hidden" name="id" value="<c:out value='${libUser.id}' />"/>
                </c:if>
                <tr>
                    <th>First Name(*):</th>
                    <td>
                        <input type="text" name="firstname" size="45"
                               value="<c:out value='${libUser.firstname}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Surname(*):</th>
                    <td>
                        <input type="text" name="surname" size="45"
                               value="<c:out value='${libUser.surname}' />"
                        />
                    </td>
                </tr>
                <tr>
                <tr>
                    <th>Middle Name:</th>
                    <td>
                        <input type="text" name="middlename" size="45"
                               value="<c:out value='${libUser.middlename}' />"
                        />
                    </td>
                </tr>
                <tr>
                <tr>
                    <th>Passport ID:</th>
                    <td>
                        <input type="text" name="passportID" size="45"
                               value="<c:out value='${libUser.passportID}' />"
                        />
                    </td>
                </tr>
                <tr>
                <tr>
                    <th>Address:</th>
                    <td>
                        <input type="text" name="address" size="45"
                               value="<c:out value='${libUser.address}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Date of Birth(*):</th>
                    <td>
                        <input type="text" name="dateOfBirth" size="10"
                               value="<c:out value='${libUser.dateOfBirth}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Email(*):</th>
                    <td>
                        <input type="text" name="email" size="10"
                               value="<c:out value='${libUser.email}' />"
                        />
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
</div>
<script type="text/javascript">
    function validateForm() {
        var x = document.forms["testValid"].value;
        if (x == "") {
            alert("this must be filled out");
            return false;
        }
    }

</script>
</body>
</html>
