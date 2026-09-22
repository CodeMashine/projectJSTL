<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>


<h1><%= "Hello World!" %>
</h1>
<div class="test"> this is test div</div>
<br/>
<a href="hello-servlet">Hello Servlet</a>

<button onclick="window.location.href='quest-page?quest=testQuest'">quest</button>
</body>
</html>