<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link type="text/css" href="css/main.css" rel="stylesheet" />
</head>
<body>
    <h1>Spring Boot JSP Films</h1>
    <c:if test="${not empty error}">
       <div class="errorblock">${error}</div>
    </c:if>
    <form:form method="post" action="login" modelAttribute="user">
      <form:errors path="*" cssClass="errorblock" element="div" />
      <table>
        <tr>
            <td>
              <input type="text" name="email" value="${user.email}" placeholder="Email" required autofocus />
              <form:errors path="email" cssClass="error" /><br />
            </td>
        </tr>
        <tr>
            <td>
              <input type="password" name="password" value="${user.password}" placeholder="Password" />
              <form:errors path="password" cssClass="error" /><br />
            </td>
        </tr>
        <tr>
            <td>
              <input type="submit" value="Login"/>
            </td>
        </tr>
     </table>
  </form:form>
</body>
</html>