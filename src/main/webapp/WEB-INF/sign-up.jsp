<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Quest</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body class="d-flex flex-column min-vh-100">
<jsp:include page="/WEB-INF/header.jsp"/>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

<section class="flex-grow-1 py-4 py-xl-5 hero " data-bs-theme="dark">
    <div class=" container h-100 ">
        <form action="${pageContext.request.contextPath}/sign-up" method="post">
            <div class="row d-flex justify-content-center">
                <div class="col-md-6 col-xl-4">
                    <div class="card mb-5">
                        <div class="card-body d-flex flex-column align-items-center">
                            <div class="my-4 bs-icon-xl bs-icon-circle bs-icon-primary bs-icon">
                                <svg class="bi bi-person" xmlns="http://www.w3.org/2000/svg" width="1em" height="1em"
                                     fill="currentColor" viewBox="0 0 16 16">
                                    <path d="M8 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6m2-3a2 2 0 1 1-4 0 2 2 0 0 1 4 0m4 8c0 1-1 1-1 1H3s-1 0-1-1 1-4 6-4 6 3 6 4m-1-.004c-.001-.246-.154-.986-.832-1.664C11.516 10.68 10.289 10 8 10c-2.29 0-3.516.68-4.168 1.332-.678.678-.83 1.418-.832 1.664z"></path>
                                </svg>
                            </div>
                            <div class="mb-3"><input class="form-control" type="text" name="name"
                                                     placeholder="Name" required></div>
                            <div class="mb-3"><input class="form-control" type="password" name="password"
                                                     placeholder="Password" required></div>
                            <div class="mb-3">
                                <select class="form-select" name="role">
                                    <option value="Admin">Admin</option>
                                    <option value="User" selected="">User</option>
                                    <option value="Guest">Guest</option>
                                </select></div>

                            <div class="mb-3">
                                <button class="btn btn-primary d-block w-100" type="submit">Sign up</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
</section>

<jsp:include page="/WEB-INF/footer.jsp"/>
</body>
</html>
