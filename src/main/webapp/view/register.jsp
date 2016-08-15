<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <%--<base href="<%=basePath %>"/>--%>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>无限极-注册</title>
    <link href="<%=basePath %>css/register.css" type="text/css" rel="stylesheet"/>

    <script type="text/javascript" src="<%=basePath %>StaticResource/JS/jquery-1.11.3.js"></script>
    <script type="text/javascript" src="<%=basePath %>js/register.js"></script>

</head>
<body>
<div class="header">
    <div class="wid1000">
        <img class="logo" src="../image/logo@3x.png"/>
        <span class="fenge">|</span><span class="reg">注册</span><span class="tishi">已有账号，马上
     <a class="dl" href="login.jsp">登录</a></span>
    </div>
</div>
<div class="wid1000 main">
    <form action="<%=basePath %>c_manageController/cManageReg.do" method="post" onsubmit="return formSubmitCheck(this)" id="reg_form">


        <table class="main_table">
            <tr>
                <td class="lab">公司邮箱：</td>
                <td class="value"><input type="text" name="compEmail" placeholder="输入公司邮箱"/></td>
            </tr>
            <tr>
                <td class="lab">密码：</td>
                <td class="value"><input name="compPasw" type="password"/></td>
            </tr>
            <tr>
                <td class="lab">确认密码：</td>
                <td class="value"><input type="password" name="confirmPasw"/></td>
            </tr>
            <tr>
                <td class="lab">法人姓名：</td>
                <td class="value"><input type="text" name="compLegalPerson" placeholder="输入法人姓名"/></td>
            </tr>
            <tr>
                <td class="lab">公司名称：</td>
                <td class="value"><input type="text" name="compName" placeholder="输入公司名称"/></td>
            </tr>
            <tr>
                <td></td>
                <td>
                    <button type="submit">确定</button>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>