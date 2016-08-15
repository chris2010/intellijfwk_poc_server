$(document).ready(function () {


    /**
     * 登录按钮
     */
    $("#submit").click(function () {

        var compEmail = $("input[name=compEmail]").val();


        var compPasw = $("input[name=compPasw]").val();


        if (compEmail == "") {
            alert("公司邮箱不能为空!");
            return;
        }

        if (compPasw == "") {
            alert("密码不能为空!");
            return;
        }


        $.post($("#formUrl").val(),
            {
                compEmail :compEmail,
                compPasw:compPasw
        }, function (data) {

            if (data && data.status == 1) {
               // alert("登录成功!");
                 window.location.href="index.jsp";//跳转至首页
            } else {
                alert(data.msg);
            }


        }, "json");


    });
});