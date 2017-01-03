<%--
  Created by IntelliJ IDEA.
  User: weina
  Date: 2016/12/27
  Time: 20:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<H1>${msg}</H1>

    <div class="pay_success">
    <h2 style="color:#ee0000;font-weight:bold;font-size:28px;margin:30px auto;text-align: center;">Congratulations!</h2>
    <p style="margin:30px auto;"></p>
    <p style="margin:15px auto;"></p>
    <p id="ShowDiv" style="margin:65px auto;font-size:14px;"></p>
    <style>
        .pay_success{
            display:block;
            width:420px;
            height:280px;
            margin:100px auto;
            text-align:center;
            font-size: 18px;
            font-weight: 500;
        }
        H1{
            text-align: center;
        }
    </style>

    <script language='javascript' type='text/javascript'>
        var secs =4; //倒计时的秒数
        var URL ;
        function Load(url){
            URL =url;
            for(var i=secs;i>=0;i--) {
                window.setTimeout('doUpdate(' + i + ')', (secs-i) * 1000);
            }
        }
        function doUpdate(num) {
            document.getElementById('ShowDiv').innerHTML = 'There will be '+num+' seconds to ParknShop HomePage' ;
            if(num == 0) { window.location=URL; }
        }
    </script>
    <!--<p id="ShowDiv" style="position: relative;top: 30px;font-size: 15px;font-weight: 500;display: inline-block;"></p>-->
    <script language="javascript">
        Load("/");
    </script>
</div>
</body>
</html>
