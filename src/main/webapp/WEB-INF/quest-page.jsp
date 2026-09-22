
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
</h1>
<div class="quest-wrapper">
    <div class="quest-wrapper-header">
        Current quest ${sessionScope.quest}
    </div>
    <div class="quest-wrapper-body">
        <div>
            Current question - ${sessionScope.question.getQuestion()}
        </div>
        <div>
            <c:forEach var="item" items="${sessionScope.question.getChoices()}">
               <div> <a href="quest-page?quest=${sessionScope.quest}&currentStep=${item.getNextStep()}"> ${item.getAnswer()} <a></div>
            </c:forEach>
        </div>

    </div>
    <button class="quest-answer"> answer</button>
</div>
<div class="test"> this is quest page</div>

<br/>

<button onclick="window.location.href='start-page'">main</button>
</body>
</html>