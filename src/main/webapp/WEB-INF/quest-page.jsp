<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html data-bs-theme="light" lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Quest</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body class="d-flex flex-column min-vh-100">
<jsp:include page="/WEB-INF/header.jsp"/>

<section class="flex-grow-1 py-4 py-xl-5 hero">
    <div class="container h-100">
        <div class="row h-100">
            <div class="col-md-10 col-xl-8 text-center d-flex d-sm-flex d-md-flex justify-content-center align-items-center justify-content-md-start align-items-md-center justify-content-xl-center mx-auto">
                <div class="text-center w-100 ms-0 ps-0 me-0 pe-0">
                    <h2 class="fw-bold mb-3 d-flex flex-column justify-content-end question">${requestScope.question.getQuestion()}</h2>
                    <div class="d-grid gap-2 mt-4">
                        <div class="d-flex flex-column justify-content-center h-100">
                            <c:forEach var="item" items="${requestScope.question.getChoices()}">
                                <div class="mt-4">
                                    <a class="btn btn-primary fs-5 me-2 px-4 py-2 btn" role="button"
                                       href="quest-page?quest=${sessionScope.quest}&currentStep=${item.getNextStep()}">${item.getAnswer()}</a>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>

            <div class="container  text-center">
                <c:if test="${requestScope.question.isWin()}">
                    <h3 class="fw-bold mb-4">YOU WIN!!!</h3>
                </c:if>

                <c:if test="${requestScope.question.isTerminal()}">
                    <a href="${pageContext.request.contextPath}/home"
                       class="btn btn-primary fs-5 me-2 px-4 py-2 btn" role="button">Home</a>
                </c:if>
            </div>
        </div>
    </div>

</section>


<jsp:include page="/WEB-INF/footer.jsp"/>
</body>
</html>