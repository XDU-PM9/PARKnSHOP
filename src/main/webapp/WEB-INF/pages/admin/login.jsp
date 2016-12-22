<%--
  Created by IntelliJ IDEA.
  User: niewenzhi
  Date: 2016/12/9
  Time: 22:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>Admin Login</title>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1 , user-scalable=no">
    <link rel="stylesheet" href="/resources/css/admin/adminLogin.css">
    <link rel="stylesheet" href="/resources/libs/xcConfirm.css">
</head>

<body>
<div class="head">
    <a href="/"><img src="/resources/images/ico/banner.jpg" alt="" width="270" height="70" id="icoHead"></a>
    <!--这里需要修改-->
</div>
<div class="main">
    <div class="header">
        <h1>Welcome to PARKnSHOP !</h1>
        <p></p>
    </div>
    <form name="form1" method="post">
        <ul class="formInfor">
            <li>
                <input placeholder="Username/Email" name="username" type="text" id="username" size="18" required/>
            </li>
            <li>
                <input placeholder="Password" name="password" type="password" id="password" size="18" required/>
            </li>
            <input  class="butlogin" value="Login" id="loginBtn"/>
        </ul>
    </form>
</div>
<script src="/resources/libs/jquery-1.8.2.min.js"></script>
<script src="/resources/libs/xcConfirm.js"></script>
<script>
    /**
     * Created by niewenzhi on 2016/12/10.
     */
    $("#loginBtn").click(function () {
        var data = {}
        data.userName = $("#username").val();
        data.password = $("#password").val();
        $.ajax({
            type:'post',
            contentType : 'application/json',
            data: JSON.stringify(data),
            url:'/admin/login',
            success:function (data) {

                var response =JSON.parse(data);
                if(response.error== false)
                {
                    /*window.location.href='admin/index';*/
                    window.setTimeout("window.location='/admin/index'",500)
                }
                else {
                    window.setTimeout("window.wxc.xcConfirm('username or password is wrong, Please Login Agian', window.wxc.xcConfirm.typeEnum.error)",500)
                }



            },
           /* error:function (data) {
                alert("ssss");
            }*/
        })
    })
</script>
</body>

<html>