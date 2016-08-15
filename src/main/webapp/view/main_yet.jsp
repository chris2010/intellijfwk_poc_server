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
    <script type="text/javascript" src="<%=basePath %>js/lists.js"></script>



    <title>Insert title here</title>
</head>
<body>
<div class="container">
    <p class="title">已关注人</p>
    <div class="head"><input type="text" class="yet_sea" placeholder="搜索"/>
        <input type="button" value="查询" class="yet_chxu"/></div>
    <input type="hidden" id="showUrl" value="<%=basePath %>c_manageController/cGetMarkUsers.do">
    <table class="yet_table" border="0">
        <thead>
        <tr>
            <th>昵称</th>
            <th>邮箱</th>
            <th>电话</th>
            <th>关注时间</th>
        </tr>
        </thead>
        <tbody id="list_data">

        </tbody>
    </table>
</div>
</body>
</html>