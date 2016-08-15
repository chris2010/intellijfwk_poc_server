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

<input type="text" value="" id="text">
<input type="text" value="http://localhost:8080/clt/webservice/JsmWeb" id="url">

<input type="button" id="button" value="123">


<input id="data" type="text">


</body>
<script type="text/javascript">


    $("#button").click(function () {

        //这是我们在第一步中创建的Web服务的地址
        var URL = $("#url").val();

        //在这处我们拼接
        var data;




      data =  '<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:impl="http://impl.WS.intel.clt.com/">' ;
        data +=  ' <soapenv:Header/> ';
        data +=  '  <soapenv:Body>';
        data +=  '   <impl:sayHello>';
        data +=  '   <arg0>'+$("#text").val()+'</arg0>';
        data +=  '   </impl:sayHello>';
        data +=  '  </soapenv:Body>';
        data +=  '  </soapenv:Envelope>';



        $.post(URL,data,function (datas) {

            alert( datas.toString());



        },'text');


    });




    loadXML = function(xmlString){
        var xmlDoc=null;
//判断浏览器的类型
//支持IE浏览器
        if(!window.DOMParser && window.ActiveXObject){ //window.DOMParser 判断是否是非ie浏览器
            var xmlDomVersions = ['MSXML.2.DOMDocument.6.0','MSXML.2.DOMDocument.3.0','Microsoft.XMLDOM'];
            for(var i=0;i<xmlDomVersions.length;i++){
                try{
                    xmlDoc = new ActiveXObject(xmlDomVersions[i]);
                    xmlDoc.async = false;
                    xmlDoc.loadXML(xmlString); //loadXML方法载入xml字符串
                    break;
                }catch(e){
                }
            }
        }
//支持Mozilla浏览器
        else if(window.DOMParser && document.implementation && document.implementation.createDocument){
            try{
                /* DOMParser 对象解析 XML 文本并返回一个 XML Document 对象。
                 * 要使用 DOMParser，使用不带参数的构造函数来实例化它，然后调用其 parseFromString() 方法
                 * parseFromString(text, contentType) 参数text:要解析的 XML 标记 参数contentType文本的内容类型
                 * 可能是 "text/xml" 、"application/xml" 或 "application/xhtml+xml" 中的一个。注意，不支持 "text/html"。
                 */
                domParser = new DOMParser();
                xmlDoc = domParser.parseFromString(xmlString, 'text/xml');
            }catch(e){
            }
        }
        else{
            return null;
        }
        return xmlDoc;
    }


</script>
</html>