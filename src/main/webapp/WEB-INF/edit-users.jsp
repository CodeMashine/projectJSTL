<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html data-bs-theme="light" lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>All Users</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body class="d-flex flex-column min-vh-100">
<jsp:include page="/WEB-INF/header.jsp"/>

<section class="flex-grow-1 py-4 py-xl-5 hero">
    <div class="container h-100">
        <div class="table-responsive">
            <table class="table">
                <thead>
                <tr>
                    <th>Name</th>
                    <th>Edit</th>
                    <th>Delete</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="user" items="${requestScope.editUsersList}">
                    <tr>
                        <td>${user.getName()}</td>
                        <td><a href="${pageContext.request.contextPath}/edit-profile?id=${user.getId()}"
                               class="btn btn-primary btn-sm">Edit</a></td>
<%--                        <td><a href="${pageContext.request.contextPath}/delete-profile?id=${user.getId()}"--%>
                        <td><a class="btn btn-primary btn-sm delete-user" data-userId="${user.getId()}" >Delete</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</section>

<jsp:include page="/WEB-INF/footer.jsp"/>
<script src="${pageContext.request.contextPath}/script/script.js"></script>
</body>
</html>