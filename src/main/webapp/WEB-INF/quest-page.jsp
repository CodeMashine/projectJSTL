<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
</h1>
<div class="test"> this is quest page</div>
<br/>

<div class="quest-wrapper">
    <div class="quest-wrapper-header">
    </div>
    <div class="quest-wrapper-body">
    </div>
    <button class="quest-answer"> answer </button>
</div>

<button onclick="window.location.href='start-page'">main</button>
</body>
</html>