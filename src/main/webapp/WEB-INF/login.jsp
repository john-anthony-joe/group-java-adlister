<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Please Log In" />
    </jsp:include>
    <style>
        body {
            /*background-image: url("/css/img/wallpaper-pink-with-letter-cape-logo.jpg");*/
            color: yellow;
            background-color: black;
        }

        .navbar {
            color: black;
            background-color: yellow;
        }

    </style>
</head>
<body>
    <jsp:include page="/WEB-INF/partials/navbar.jsp" />
    <div class="container">
        <h1>Bat Login</h1>
        <form action="/login" method="POST">
            <div class="form-group">
                <label for="username">Username</label>
                <input id="username" name="username" class="form-control" type="text">
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input id="password" name="password" class="form-control" type="password">
            </div>
            <input type="submit" class="btn btn-primary btn-block" value="Log In">
            <div class="form-group">
                <a href="/forgotPassword">Forgot Password? Click here.</a>
            </div>
        </form>
    </div>
</body>
</html>
