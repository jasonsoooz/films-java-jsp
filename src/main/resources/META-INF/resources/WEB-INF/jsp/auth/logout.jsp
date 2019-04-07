<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
</head>
<body>
    <label>Hi user: ${pageContext.request.userPrincipal.name}</label>
    <div class="right">
        <a href="${pageContext.request.contextPath}/logout">logout</a>
    </div>
</body>
</html>