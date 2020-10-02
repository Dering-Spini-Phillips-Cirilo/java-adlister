<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.Objects" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>



<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Create a new Ad" />
    </jsp:include>
</head>
<body>
<div class="container">
    <h1>Update your Ad</h1>
    <form action="/ads/update" method="post">
        <div class="form-group">
            <label for="title">Title</label>
            <input id="title" name="title" class="form-control" type="text" value="${currentAd.title}">
        </div>
        <div class="form-group">
            <label for="description">Description</label>
            <textarea id="description" name="description" class="form-control" type="text">${currentAd.description}</textarea>
        </div>
        <c:forEach var="category" items="${sessionScope.categories}">
            <div class="form-check">
                <input class="form-check-input" type="checkbox" value="${category.id}" id="${category.name}" name="categories" checked="checked">
                <label class="form-check-label" for="${category.name}">
                        ${category.name}
                </label>
            </div>
        </c:forEach>
        <input type="submit" class="btn btn-block btn-primary">
    </form>
</div>
</body>

</html>
