<%@ page import="java.util.Objects" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<<<<<<< HEAD
    <head>
        <jsp:include page="/WEB-INF/partials/head.jsp">
            <jsp:param name="title" value="Create a new Ad" />
        </jsp:include>
        <style>
            #body{
                background-color: lightgray;
            }
            #main_header{
                text-align: center;
                color: blue;
            }
        </style>
    </head>
    <body id="body">
        <div class="container-fluid">
            <h1 id="main_header">Create a new Ad</h1>
            <form action="/ads/create" method="post">
                <div class="form-group">
                    <label for="title">Title</label>
                    <input id="title" name="title" class="form-control" type="text">
                </div>
                <div class="form-group">
                    <label for="description">Description</label>
                    <textarea id="description" name="description" class="form-control" type="text"></textarea>
                </div>
                <input type="submit" class="btn btn-block btn-primary">
            </form>
        </div>
    </body>
=======
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Create a new Ad" />
    </jsp:include>
</head>
<body>
    <div class="container">
        <h1>Create a new Ad</h1>
        <form action="/ads/create" method="post">
            <div class="form-group">
                <label for="title">Title</label>
                <input id="title" name="title" class="form-control" type="text" value="${not empty title ? title : ""}">
            </div>
            <div class="form-group">
                <label for="description">Description</label>
                <textarea id="description" name="description" class="form-control" type="text"><%=Objects.isNull(request.getSession().getAttribute("description")) ? "" : (String) request.getSession().getAttribute("description") %></textarea>
            </div>
            <input type="submit" class="btn btn-block btn-primary">
        </form>
    </div>
</body>
>>>>>>> master
</html>
