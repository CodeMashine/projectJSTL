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
    <div class="container-fluid">
        <div class="row vh-80 ">
            <div class="col-9 pe-2 h-100">
                <div class="graph-container h-100 w-100 border rounded" style="overflow: hidden; background: rgba(255,255,255,0.1);"></div>
<%--                <div id="graph-container" class="h-100 w-100 border rounded" style="overflow: hidden; background: rgba(255,255,255,0.1);"></div>--%>
            </div>

            <div class="col-3 d-flex flex-column h-100">
                <div class="toolbar-container border rounded mb-2 p-2 bg-light bg-opacity-25 border"></div>
                <form class="edit-Form p-3 border rounded bg-light bg-opacity-25 shadow-sm flex-grow-1" style="border-color: #333 !important;">
                    <!-- Текст блока -->
                    <div class="mb-3">
                        <label for="stepName" class="form-label fw-bold">Step name:</label>
                        <input id="stepName" name="stepName" class="form-control stepName" rows="1"
                                  placeholder="Input step name..." required></input>
                    </div> <div class="mb-3">
                        <label for="questionText" class="form-label fw-bold">Text:</label>
                        <textarea id="questionText" name="questionText" class="form-control questionText" rows="3"
                                  placeholder="Input text..." required></textarea>
                    </div>

                    <!-- Статус шага (Радиокнопки) -->
                    <div class="mb-3 flex-box">
                        <label class="form-label fw-bold d-block">Step status:</label>

                        <div class="form-check">
                            <input class="form-check-input isPassable" type="radio" name="blockStatus" id="statusPassable"
                                   value="passable">
                            <label class="form-check-label" for="statusPassable">Passable</label>
                        </div>

                        <div class="form-check">
                            <input class="form-check-input isWin" type="radio" name="blockStatus" id="statusWin" value="win">
                            <label class="form-check-label" for="statusWin">Win</label>
                        </div>

                        <div class="form-check mb-2">
                            <input class="form-check-input isLose" type="radio" name="blockStatus" id="statusLose"
                                   value="lose">
                            <label class="form-check-label" for="statusLose">Lose</label>
                        </div>
                    </div>

                    <!-- Кнопка отправки -->
                    <button type="submit" class="btn btn-primary w-100">Save</button>
                </form>
            </div>

        </div>

    </div>

</section>


<jsp:include page="/WEB-INF/footer.jsp"/>
<script>
    mxBasePath = '${pageContext.request.contextPath}/mxgraph/';
</script>
<script src="${pageContext.request.contextPath}/mxgraph/mxClient.min.js"></script>
<script> window.App = {}; </script>

<script src="${pageContext.request.contextPath}/script/observer.js"></script>
<script src="${pageContext.request.contextPath}/script/cellData.js"></script>
<script src="${pageContext.request.contextPath}/script/editFormManager.js"></script>
<script src="${pageContext.request.contextPath}/script/graphManager.js"></script>
<script src="${pageContext.request.contextPath}/script/toolbarManager.js"></script>
<script src="${pageContext.request.contextPath}/script/main.js"></script>
</body>
</html>