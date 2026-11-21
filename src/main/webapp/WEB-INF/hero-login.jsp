<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<section class="flex-grow-1 py-4 py-xl-5 hero">
    <div class="container h-100">
        <div class="row mb-5">
            <div class="col-md-8 col-xl-6 text-center mx-auto">
                <h2>Quests</h2>
                <p></p>
            </div>
        </div>
        <div class="row gy-4 row-cols-1 row-cols-md-2">
            <c:forEach var="quest" items="${requestScope.quests.entrySet()}">
                <div class="col">
                    <div class="d-flex flex-column">
                        <div class="text-center px-lg-4 py-4 py-lg-0">
                            <h4>${quest.getValue()}</h4>
                        </div>
                        <a href="${pageContext.request.contextPath}/quest-page?questId=${quest.getKey()}"
                           class="btn btn-primary btn-sm">Play</a>
                        <c:if test="${sessionScope.user.getGamesInProgress().containsKey(quest.getKey())}">
                            <a href="${pageContext.request.contextPath}/quest-page?questId=${quest.getKey()}&nextStep=${sessionScope.user.getGamesInProgress().get(quest.getKey())} "
                            class="btn btn-primary btn-sm">Load</a>
                        </c:if>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</section>