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
            Current question - ${requestScope.question.getQuestion()}
        </div>
        <div>
            <c:forEach var="item" items="${requestScope.question.getChoices()}">
                <ol>
                    <a href="quest-page?quest=${sessionScope.quest}&currentStep=${item.getNextStep()}"> ${item.getAnswer()} </a>
                </ol>
            </c:forEach>
        </div>

    </div>
</div>

<c:if test="${requestScope.question.isWin()}">
    <div>YOU WIN!!!</div>
</c:if>

<c:if test="${requestScope.question.isTerminal()}">
    <button onclick="window.location.href='start-page'">main</button>
</c:if>

</body>
</html>