<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
</head>
<body>
    <h1>Spring Boot JSP Add Film</h1>
    <form:form method="post" action="films" modelAttribute="film">
     <table style="width:100%">
        <tr>
            <td><form:label path="title">Film</form:label></td>
            <td><input type="text" name="title" value="${film.title}" /></td>
        </tr>
        <tr>
            <td><form:label path="year">Year</form:label></td>
            <td><input type="number" name="year" step="1" value="${film.year}" /></td>
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
            <td><input type="submit" value="Submit"/></td>
        </tr>
    </table>
  </form:form>
</body>
</html>