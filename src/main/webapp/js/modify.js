$(document).ready(function () {


    





});


/**
 * 校验设置页面是否填写完全
 * @returns {boolean}
 */
function checkSubmit() {

    var title = $("input[name=sysTitle]").val();
    var syspic = $("input[name=sys_url_pic]").val();
    var sysUrl = $("input[name=sysUrl]").val();


    if (title == ""){
        alert("标题不能为空!");
        return false;
    }
    if (syspic == ""){
        alert("图片不能为空!");
        return false;
    }
    if (sysUrl == ""){
        alert("链接地址不能为空!");
        return false;
    }

    return true;
}