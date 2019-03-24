<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
</head>
<body>
    <h1>Spring Boot JSP Films</h1>
    <button onclick="location.href = 'film';">Add film</button>
    <table style="width:100%">
      <tr>
        <th>No</th>
        <th>Film</th>
        <th>Year</th>
        <th>Imdb rating</th>
        <th>Director</th>
      </tr>
      <c:forEach var="film" items="${films}">
        <tr>
          <td>No</td>
          <td>${film.title}</td>
          <td>${film.year}</td>
          <td>${film.imdbRating}</td>
          <td>${film.director}</td>
        </tr>
      </c:forEach>
    </table>
</body>
</html>