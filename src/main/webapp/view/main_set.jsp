<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="../css/index.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript" src="<%=basePath %>StaticResource/JS/jquery-1.11.3.js"></script>

    <script type="text/javascript" src="<%=basePath %>js/modify.js"></script>


    <title>首页</title>
</head>
<body>
<div class="container">
    <p class="title">设置</p>


    <form id="submitForm" action="<%=basePath %>c_manageController/modifyCompInfo.do"  method="post"
          enctype="multipart/form-data" onsubmit="return checkSubmit()">


        <table class="set_table">
            <tr>
                <td class="set_lab">标题：</td>
                <td class="set_val"><input type="text" placeholder="输入标题"  name="sysTitle" /></td>
            </tr>
            <tr>
                <td class="set_lab">图片：</td>
                <td class="set_val mart30"><input type="file" name="sys_url_pic"/></td>
            </tr>
            <tr style="height:60px;"></tr>
            <tr>
                <td class="set_lab">链接地址：</td>
                <td class="set_val"><input type="text" name="sysUrl"/></td>
            </tr>
            <tr>
                <td></td>
                <td style="float:right;margin-right:250px;"><input type="submit" class="sub" value="提交"/></td>
            </tr>
        </table>
    </form>

</div>
</body>
</html>