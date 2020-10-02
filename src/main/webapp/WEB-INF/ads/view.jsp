<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <title>Show Ad Page</title>

        <jsp:include page="/WEB-INF/partials/head.jsp">
            <jsp:param name="title" value="Viewing All The Ads" />
        </jsp:include>


        <style>
            #body{
                text-align: center;
                background-color: lightgray;
            }
            #main_header{
                text-align: center;
                color: blue;
            }
        </style>
    </head>

    <body id="body">
    <%--NAVBAR LINK--%>
    <jsp:include page="/WEB-INF/partials/navbar.jsp"/>

    <%--MAIN CONTAINER--%>
    <div class="container-fluid">
        <h1 id="main_header">AD INFORMATION</h1>
<%--            <p>${ads}</p>--%>
            <%--USER DISPLAY--%>
            <div>
                <h2>User Information</h2>
                    <div>
                        <p>${currentUser.username}</p>
                    </div>
            </div>

            <%--AD DISPLAY--%>
            <div class="card">
                <h2>Item Description</h2>
                    <div>
                        <p>${currentAd.title}</p>
                    </div>
                    <div>
                        <p>${currentAd.description}</p>
                    </div>
                <a href="/ads/update?adId=${currentAd.id}">Edit This Ad</a>
            </div>
    </body>
</html>
