$(document).ready(function () {


    $.post($("#showUrl").val(), {}, function (data) {


        alert(JSON.stringify(data));

        if (data && data.status == 1) {

            datas = data.t;

            if (datas && datas.length > 0){

                var tableStr = "";

                for (var i = 0 ; i < datas.length ; i++){


                    tableStr += "<tr>";

                    tableStr += "<td>";

                    if ( datas[i].accountName){
                        tableStr += datas[i].accountName;
                    }

                    tableStr += "</td>";

                    tableStr += "<td>";
                    if ( datas[i].account){
                        tableStr += datas[i].account;
                    }
                    tableStr += "</td>";

                    tableStr += "<td>";
                    if ( datas[i].tel){
                        tableStr += datas[i].tel;
                    }
                    tableStr += "</td>";


                    tableStr += "<td>";
                    tableStr += datas[i].c_date;
                    tableStr += "</td>";


                    tableStr += "</tr>";




                }


                $("#list_data").append(tableStr);



            }else{
                alert("无关注用户!");
            }


        } else {

            alert("请求数据失败!");
        }


    }, "JSON");


});