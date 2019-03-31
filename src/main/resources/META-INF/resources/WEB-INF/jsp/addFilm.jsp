<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link type="text/css" href="css/main.css" rel="stylesheet" />
</head>
<body>
    <jsp:include page="auth/logout.jsp" />
    <h1>Spring Boot JSP Add Film</h1>
    <form:form method="post" action="films" modelAttribute="film">
      <form:errors path="*" cssClass="errorblock" element="div" />
      <table>
        <input type="hidden" name="id" value="${film.id}" />
        <tr>
            <td><form:label path="title">Film</form:label></td>
            <td>
              <input type="text" name="title" value="${film.title}" />
              <form:errors path="title" cssClass="error" /><br />
            </td>
        </tr>
        <tr>
            <td><form:label path="year">Year</form:label></td>
            <td>
              <input type="number" name="year" step="1" value="${film.year}" />
              <form:errors path="year" cssClass="error" /><br />
            </td>
        </tr>
        <tr>
            <td><form:label path="imdbRating">Imdb Rating</form:label></td>
            <td><input type="number" name="imdbRating" step="0.1" value="${film.imdbRating}" /></td>
        </tr>
        <tr>
            <td><form:label path="director">Director</form:label></td>
            <td><input type="text" name="director" value="${film.director}" /></td>
        </tr>
        <tr>
            <td>
              <input type="submit" value="Submit"/>
              <button onclick="location.href = 'films'; return false;">Cancel</button>
            </td>
        </tr>
     </table>
  </form:form>
</body>
</html>