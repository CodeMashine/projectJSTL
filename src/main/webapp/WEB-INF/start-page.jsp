<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">

</head>
<body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

<div class="header"> <h1> Добро пожаловать в квестовую игру</h1></div>

<form action="${pageContext.request.contextPath}/quest-page" method="get"
      class="quest-form mx-auto">

    <div class="mb-3">
        <label for="name" class="form-label">Name</label>
        <input id="name" name="playerName" type="text"
               class="form-control" placeholder="input name"
               value="${sessionScope.player.getName()}" required>
    </div>

    <div class="mb-3">
        <label for="quest" class="form-label">Select Quest</label>
        <select id="quest" name="quest" class="form-select">
            <option value="testQuest">Test quest</option>
            <option value="ufoQuest">Ufo quest</option>
        </select>
    </div>

    <button type="submit" class="btn btn-primary">Start</button>
</form>

<c:if test="${sessionScope.player.getName()!=null}">
    <div class="statistic mt-10 text-center">
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