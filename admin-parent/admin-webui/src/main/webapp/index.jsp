<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/12/4
  Time: 15:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <base href="http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath }/">

    <script type="text/javascript" src="jquery/jquery-2.1.1.min.js"></script>
    
    <script type="text/javascript">

        //准备好要发送到服务端的数组
        var array = [5,6,7];
        //再将上面的JSON数组转换为JSON字符串
        var requestBody = JSON.stringify(array);


        
        $(function () {
            $("#btn1").click(function () {
                $.ajax({
                    "url":"send/array.html",    //请求目标资源的地址
                    "type":"post",              //请求方式
                    "data":requestBody,                 //要发送的请求参数
                    "contentType":"application/json;charset=UTF-8",//设置请求体的内容类型，为JSON格式的数据
                    "dataType":"text",          //如何对待服务器端返回的数据
                    "success":function(response){
                        alert(response);
                    },
                    "error":function (response) {
                        alert(response);
                    }
                });

            })

            $("#btn2").click(function () {
                //准备要发送的数据
                var student = {
                        "stuId":5,
                        "stuName":"tom",
                        "address":{
                            "province":"广东",
                            "city":"深圳",
                            "street":"中山大道"
                        },
                        "subjectList":[
                            {
                                "subjectName":"JAVA",
                                "subjectScore":99
                            }
                        ],
                        "map":{
                            "k1":"v1",
                            "k2":"v2"
                        }
                };
                //将JSON对象转换为JSON字符串
                var requestStudent = JSON.stringify(student);

                //发送AJAX请求
                $.ajax({
                    "url":"send/array2.html",    //请求目标资源的地址
                    "type":"post",              //请求方式
                    "data":requestStudent,                 //要发送的请求参数
                    "contentType":"application/json;charset=UTF-8",//设置请求体的内容类型，为JSON格式的数据
                    "dataType":"json",          //如何对待服务器端返回的数据
                    "success":function(response){
                        alert(response);
                    },
                    "error":function (response) {
                        alert(response);
                    }
                });

            })



        })
    </script>
</head>
<body>
<a href="test/ssm.html">测试SSM整合</a>

<button id="btn1"> test request body</button>

<button id="btn2">test request body2222</button>
</body>


</html>
