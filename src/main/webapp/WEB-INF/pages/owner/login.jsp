<!DOCTYPE html>
<html>
<head>
    <%--<base href="<%=basePath%>">--%>

    <title>Owner Login</title>
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
    <form name="form1" method="post" action="/owner/login">
        <ul class="formInfor">
            <li>
                <input placeholder="Username/Email" name="username" type="text" id="username" size="18" required/>
            </li>
            <li>
                <input placeholder="Password" name="password" type="password" id="password" size="18" required/>
            </li>
            <input type="submit"  class="butlogin" value="Login" id="loginBtn"/>
        </ul>
    </form>
    <%
        String msg = String.valueOf(request.getAttribute("msg"));
        if(msg.equals("null")){
            msg="";
        }
    %>
    <p><%=msg%></p>

    <div class="forAndRe">
        <a href="/owner/register">Click here to register a new account</a>
    </div>
</div>

</body>
</html>