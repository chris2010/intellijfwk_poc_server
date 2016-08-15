<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>无限极-登录</title>
<link href="../css/login.css" type="text/css" rel="stylesheet" />

  <script type="text/javascript" src="<%=basePath %>StaticResource/JS/jquery-1.11.3.js"></script>
  <script type="text/javascript" src="<%=basePath %>js/login.js"></script>

</head>
<body>
  <div class="header">
    <div class="wid750">
     <img class="logo" src="../image/logo@3x.png" />
     <span class="fenge">|</span><span class="title">无限极智慧移动框架</span>
     <span class="fr"><button class="btn_reg" id="reg"
      onmouseover="this.style.cursor='pointer'" onclick="javascript: window.location.href='register.jsp'">注册</button></span>
    </div>
  </div>
  <div class="wid660 main">
    <div class="main_fl">
      <img src="../image/dl_1.png" />
    </div>
    <input type="hidden" id="formUrl" value="<%=basePath %>c_manageController/cManageLogin.do">
    <div class="main_fr">
      <form class="main_fr_form" id="#main_fr_form" onsubmit="return false" method="post"
              action="<%=basePath %>c_manageController/mobile_userLogin.do">
        <input type="text" placeholder="输入邮箱"  name = "compEmail"/>
        <input type="password" placeholder="输入密码" name ="compPasw" style="margin-bottom:30px !important;"/>
        <input id="submit" type="submit" class="sub" value="登录"/>
      </form>
    </div>
  </div>
</body>
</html>