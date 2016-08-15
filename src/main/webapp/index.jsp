<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<header>
    <base href="<%=basePath%>">

<!-- 
    <meta http-equiv="refresh" content="0;<%=basePath%>commetDemo/commetindex.do">
 -->
     <frameHeader>
        <script type="text/javascript">

            $(document).ready(function(){
               // alert(12344666);
            });

        </script>
    </frameHeader>

    <meta >
</header>

<body>
<h2>跳转中,请稍候..</h2>
</body>
</html>
