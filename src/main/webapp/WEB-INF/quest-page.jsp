<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Quest page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body class="d-flex flex-column min-vh-100 justify-content-center">
<div class="container">
    <div class="quest-wrapper">
        <div class="quest-wrapper-header">
        </div>
        <div class="quest-wrapper-body">
            <div>
                <h3 class="mb-4">${requestScope.question.getQuestion()}</h3>
            </div>
            <div>
                <ul>
                    <c:forEach var="item" items="${requestScope.question.getChoices()}">
                        <li>
                            <a href="quest-page?quest=${sessionScope.quest}&currentStep=${item.getNextStep()}"
                               class="btn btn-outline-primary btn-lg w-100 mb-2">
                                    ${item.getAnswer()}
                            </a>
                        </li>
                    </c:forEach>
                </ul>
            </div>

        </div>
    </div>


    <c:if test="${requestScope.question.isWin()}">
        <div>YOU WIN!!!</div>
    </c:if>

    <c:if test="${requestScope.question.isTerminal()}">
        <button class="btn btn-outline-primary btn-lg w-20 mb-2" onclick="window.location.href='start-page'">main
        </button>
    </c:if>
</div>
</body>
</html>