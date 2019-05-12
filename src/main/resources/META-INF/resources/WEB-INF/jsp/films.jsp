<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link type="text/css" href="css/main.css" rel="stylesheet" />
    <script type="text/javascript" src="js/mySubmit.js"></script>
</head>
<body>
    <jsp:include page="auth/logout.jsp" />
    <h1>Spring Boot JSP Films</h1>
    <c:if test="${not empty error}">
       <div class="errorblock">${error}</div>
    </c:if>
    <button onclick="location.href = 'film';" class="blue">Add film</button>
    <table>
      <tr>
        <th>Id</th>
        <th>Film</th>
        <th>Release date</th>
        <th>Genre</th>
        <th>Imdb rating</th>
        <th>Director</th>
        <th>Action</th>
      </tr>
      <c:forEach var="film" items="${films}">
        <tr class="notform">
          <td>${film.id}</td>
          <td>${film.title}</td>
          <td>${film.releaseDateString}</td>
          <td>${film.genre}</td>
          <td>${film.imdbRating}</td>
          <td>${film.director}</td>
          <td>
            <button onclick="location.href = 'films/${film.id}'">Update</button>
            <button onclick="mySubmit('https://localhost:8018/demo/films/${film.id}', 'post')" class="danger">Delete</button>
          </td>
        </tr>
      </c:forEach>
    </table>
</body>
</html>