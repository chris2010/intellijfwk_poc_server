$(document).ready(function () {


});


function formSubmitCheck(forms) {

    var email = $("input[name=compEmail]").val();
    var compPasw = $("input[name=compPasw]").val(); //forms.compPasw.toString();
    var confirmPasw =$("input[name=confirmPasw]").val(); // forms.confirmPasw.toString();
    var compLegalPerson =$("input[name=compLegalPerson]").val(); // forms.compLegalPerson.toString();
    var compName =$("input[name=compName]").val(); // forms.compName.toString();


    if (email == "") {
        alert("公司邮箱不能为空!");
        return false;
    }
    if (compPasw == "") {
        alert("密码不能为空!");
        return false;
    }
    if (confirmPasw == "") {
        alert("确认密码不能为空!");
        return false;
    }



    if (compPasw != confirmPasw){
        alert(compPasw + "____" +confirmPasw );
        alert("密码与确认密码不一致!");

        return false;
    }

    if (compLegalPerson == "") {
        alert("企业法人不能为空!");
        return false;
    }
    if (compName == "") {
        alert("公司名称不能为空!");
        return false;
    }


    $.post($("#reg_form").attr("action"),$("#reg_form").serialize(),function(data){


        if (data && data.status == 1){
            window.location.href="login.jsp";
        }else{
            alert(data.msg);
        }

    },"JSON");



    return false;

}