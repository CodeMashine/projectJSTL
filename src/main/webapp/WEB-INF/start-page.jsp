<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<form class="form-horizontal" action="${pageContext.request.contextPath}/quest-page" method="get">
    <fieldset>
        <!-- Form Name -->
        <legend>Quest Game</legend>
        <!-- Text input-->
            <div class="form-group">
                <label class="col-md-4 control-label" for="Name">Name</label>
                <div class="col-md-4">
                    <input id="Name" name="playerName" type="text" placeholder="input name"
                           class="form-control input-md" value="${sessionScope.player.getName()}" required>

                </div>
            </div>
        <!-- Select Basic -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="">Select Quest</label>
            <div class="col-md-4">
                <select id="" name="quest" class="form-control">
                    <option value="testQuest">Test quest</option>
                </select>
            </div>
        </div>
        <!-- Button -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="button"></label>
            <div class="col-md-4">
                <button id="button" name="button" class="btn btn-primary">Start</button>
            </div>
        </div>

    </fieldset>
</form>

<c:if test="${sessionScope.player.getName()!=null}">
    <div>
        <ul>
            <p>Statistic</p>
            <li>Player name : "${sessionScope.player.getName()}"</li>
            <li>Quest played : "${sessionScope.player.getQuestPlayed()}"</li>
            <li>Quest win : "${sessionScope.player.getQuestWin()}"</li>
            <li>Quest lose : "${sessionScope.player.getQuestLose()}"</li>
        </ul>
    </div>
</c:if>

<%--<button onclick="window.location.href='quest-page?quest=testQuest'"> test quest</button>--%>
</body>
</html>