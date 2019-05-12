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
            <td><form:label path="title">Film *</form:label></td>
            <td>
              <input type="text" name="title" value="${film.title}" />
              <form:errors path="title" cssClass="error" /><br />
            </td>
        </tr>
        <tr>
            <td><form:label path="releaseDateString">Release date *</form:label></td>
            <td>
              <input type="date" name="releaseDateString" value="${film.releaseDateString}" />
              <form:errors path="releaseDateString" cssClass="error" /><br />
            </td>
        </tr>
        <tr>
            <td><form:label path="genre">Genre</form:label></td>
            <td>
              <select name="genre">
                  <option value=""></option>
                  <option value="ACTION" ${film.genre == 'ACTION' ? 'selected' : ''}>Action</option>
                  <option value="MARTIAL_ARTS" ${film.genre == 'MARTIAL_ARTS' ? 'selected' : ''}>Martial arts</option>
                  <option value="ROMANCE" ${film.genre == 'ROMANCE' ? 'selected' : ''}>Romance</option>
                  <option value="COMEDY" ${film.genre == 'COMEDY' ? 'selected' : ''}>Comedy</option>
                  <option value="THRILLER" ${film.genre == 'THRILLER' ? 'selected' : ''}>Thriller</option>
                  <option value="SCI_FICTION" ${film.genre == 'SCI_FICTION' ? 'selected' : ''}>Science fiction</option>
                  <option value="HORROR" ${film.genre == 'HORROR' ? 'selected' : ''}>Horror</option>
                  <option value="DRAMA" ${film.genre == 'DRAMA' ? 'selected' : ''}>Drama</option>
                  <option value="CHILDREN" ${film.genre == 'CHILDREN' ? 'selected' : ''}>Children</option>
                </select>
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
              <input type="submit" class="blue" value="Submit"/>
              <button onclick="location.href = 'films'; return false;">Cancel</button>
            </td>
        </tr>
     </table>
  </form:form>
</body>
</html>