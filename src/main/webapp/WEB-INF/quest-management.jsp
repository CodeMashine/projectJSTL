<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Quest management</title>
    <link rel="stylesheet" href="assets/css/Navbar-Centered-Brand-Dark-icons.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body class="d-flex flex-column min-vh-100">
<jsp:include page="/WEB-INF/header.jsp"/>
<section class="flex-grow-1 py-4 py-xl-5 hero">
    <div class="container h-100">
        <a href="${pageContext.request.contextPath}/quest-editor"
           class="btn btn-primary btn-sm">Create new quest</a>
        <div class="table-responsive">
            <table class="table">
                <tbody>
                <c:forEach var="quest" items="${requestScope.quests.entrySet()}">
                    <tr>
                        <td>${quest.getValue()}</td>
                        <td><a href="${pageContext.request.contextPath}/quest-editor?questId=${quest.getKey()}"
                               class="btn btn-primary btn-sm">Edit</a></td>
                        <td><a class="btn btn-primary btn-sm delete-quest" data-questId="${quest.getKey()}">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

</section>

<jsp:include page="/WEB-INF/footer.jsp"/>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/script/deleteQuestResponse.js"></script>
</body>
</html>