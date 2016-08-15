<%--
  Created by IntelliJ IDEA.
  User: liujinbang
  Date: 15/8/27
  Time: 11:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>


<base href="<%=basePath%>">
<head>

    <title>
        <sitemesh:write property='title'/>
    </title>

    <link rel="stylesheet" type="text/css" href="StaticResource/JS/EasyUI/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="StaticResource/JS/EasyUI/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="StaticResource/JS/EasyUI/demo.css">
    <script type="text/javascript" src="StaticResource/JS/jquery-1.11.3.js"></script>
    <script type="text/javascript" src="StaticResource/JS/EasyUI/jquery.easyui.min.js"></script>



    <!--  自定义的header  用来定义特定页面的JS/CSS  -->
    <sitemesh:write property='frameHeader'/>


</head>
<body>

<header>
    <jsp:include page="header.jsp"></jsp:include>
</header>
<hr/>
<!--此处引用需修饰页面的body-->
<sitemesh:write property='body'/>
<hr/>
<footer>
    <jsp:include page="bottom.jsp"></jsp:include>
</footer>

</body>
</html>