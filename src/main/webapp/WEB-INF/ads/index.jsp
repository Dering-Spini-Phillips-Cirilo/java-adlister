<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <jsp:include page="/WEB-INF/partials/head.jsp">
            <jsp:param name="title" value="Viewing All The Ads"/>
        </jsp:include>

        <style>
            /* The Modal (background) */
            .modal {
                display: none; /* Hidden by default */
                position: fixed; /* Stay in place */
                z-index: 1; /* Sit on top */
                padding-top: 100px; /* Location of the box */
                left: 0;
                top: 0;
                width: 100%; /* Full width */
                height: 100%; /* Full height */
                overflow: auto; /* Enable scroll if needed */
                background-color: rgb(0, 0, 0); /* Fallback color */
                background-color: rgba(0, 0, 0, 0.4); /* Black w/ opacity */
            }

            /* Modal Content */
            .modal-content {
                background-color: #fefefe;
                margin: auto;
                padding: 20px;
                border: 1px solid #888;
                width: 80%;
            }

            /* The Close Button */
            .close {
                color: #aaaaaa;
                float: right;
                font-size: 28px;
                font-weight: bold;
            }

            .close:hover,
            .close:focus {
                color: #000;
                text-decoration: none;
                cursor: pointer;
            }

            #body{
                background-color: lightgray;
            }
            #main_header{
                text-align: center;
                color: blue;
            }
            #ad_card{
                flex-wrap: wrap;
                margin: 2.5%;
                width: 20%;
                text-align: center;
                text-justify: auto;
                align-content: center;
                background-color: lightblue;
                border: 5px black;
                box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2);
                border-radius: 5px 5px 5px 5px;
            }
        </style>
    </head>
    <body id="body">
    <jsp:include page="/WEB-INF/partials/navbar.jsp"/>
        <div class="container-fluid">
            <h1 id="main_header">Here Are all the ads!</h1>
            <c:forEach var="ad" items="${ads}">
                <div id="ad_card" class="col-md-3">
                    <div class="card-fluid">
                        <h2>${ad.title}</h2>
                        <p>${ad.description}</p>
                        <a id="ad-card" href="/ads/view?adId=${ad.id}">Ad Info</a>
                    </div>
                </div>
            </c:forEach>
        </div>

    </body>
    <script type="text/javascript">
        document.getElementById("ad-card").onclick = function () {
            location.href = "/ads/view";
        };
    </script>
</html>
