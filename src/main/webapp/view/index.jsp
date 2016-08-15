<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="../css/index.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript" src="<%=basePath %>StaticResource/JS/jquery-1.11.3.js"></script>
    <title>首页</title>
</head>
<body>
<div class="header">
    <img class="logo" src="../image/logo@3x.png"/>
    <span class="fenge">|</span><span class="title">无限极智慧移动框架</span>
    <span class="logout fr"><a href="login.jsp">注销</a></span><span class="userName fr"></span>

</div>
<div class="main">
    <div class="main_fl">
        <ul>
            <li id="emp"></li>
            <li id="set" class="active">设置</li>
            <li id="yet">已关注人</li>
        </ul>
    </div>
    <div class="main_fr">
        <iframe id="main_fr" src="main_set.jsp"></iframe>
    </div>
</div>
</body>
<script type="text/javascript">
    document.getElementById("set").onmousedown = function () {
        document.getElementById("main_fr").src = "main_set.jsp";
    }
    document.getElementById("yet").onmousedown = function () {
        document.getElementById("main_fr").src = "main_yet.jsp";
    }
    $("#set").hover(function () {
        $(this).addClass("active");
        $(this).next().removeClass("active");
    })
    $("#yet").hover(function () {
        $(this).addClass("active");
        $(this).prev().removeClass("active");
    })
</script>
</html>