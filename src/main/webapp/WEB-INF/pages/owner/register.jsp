<!DOCTYPE HTML>
<html>
<head>
    <%--<base href="<%=basePath%>">--%>

    <title>Owner Register</title>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1 , user-scalable=no">
    <!--<meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">-->
    <link rel="stylesheet" href="/resources/css/owner/owner.css">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->

</head>

<body>
<div class="head">
    <a href="/"><img src="/resources/images/ico/banner.jpg" alt="" width="270" height="70" id="icoHead"></a>
    <!--这里需要修改-->
</div>
<div class="main">
    <div class="header">
        <h1>Welcome to PARKnSHOP !</h1>
    </div>
    <p>Now,you should complete the information below to register a new account.</p>
    <form name="form" method="post" action="">
        <%
            String msg = String.valueOf(request.getParameter("msg"));
            if (msg.equals("null")){
                msg = "";
            }
        %>

        <p><%=msg%></p>
        <ul class="formInfor">
            <li>
                <input placeholder="Please input your email" name="e-mail" type="text" id="e-mail" size="18" required onblur="checkEmail()"/>
                <a href="#" class="icon ticker"> </a>
                <div class="clear"> </div>
            </li>
            <li>
                <input placeholder="Username" name="username" type="text" id="username" size="18" required onblur="checkUser()"/>
                <a href="#" class="icon ticker"> </a>
                <div class="clear"> </div>
            </li>
            <li>
                <input placeholder="Please set your password(the length of password less than 20 and more than 6)"name="password" type="password" id="password" size="18" onblur="checkPass()" required/>
                <a href="#" class="icon ticker"> </a>
                <div class="clear"> </div>
            </li>
            <li>
                <input placeholder="Please input your password agian"name="passwordagain" type="password" id="passwordagain" size="18" onblur="checkPassAgian()" required/>
                <a href="#" class="icon ticker"> </a>
                <div class="clear"> </div>
            </li>
            <label class="checkbox"><input type="checkbox" name="checkbox" checked=""><i> </i>Please insure your information agian</label>
            <br>
            <input type="submit"  class="butlogin" value="Register" id="registerBtn"/>
        </ul>
    </form>
</div>
<script src="/resources/libs/jquery-1.8.2.min.js"></script>
<script src="/resources/js/owner/owner.js"></script>
</body>
</html>