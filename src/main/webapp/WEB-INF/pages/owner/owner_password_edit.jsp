<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="/resources/css/owner/owner.css">
    <link rel="stylesheet" href="/resources/libs/iconfont.css">
</head>
<body>
    <div class="main">
        <p>Now,modify your password:</p>
        <form name="form" method="post" action="/owner/ownerPasswordEdit">
            <ul class="formInfor">
                <div class="inforBlock">
                    <i class="iconfont">&#xe60d;:</i>
                    <li>
                        <input placeholder="Input your old password" name="old" type="password" id="oldPass" size="18"/>
                    </li><span class="yes">Yes</span><span class="no">No</span>
                </div>
                <div class="inforBlock">
                    <i class="iconfont">&#xe60d;:</i>
                    <li>
                        <input placeholder="Input your new password" name="new" type="password" id="newPass" size="18"/>
                    </li><span class="no"></span>
                </div>
                <div class="inforBlock">
                    <i class="iconfont">&#xe60d;:</i>
                    <li>
                        <input placeholder="Input your new password agian" name="confirm" type="password" id="passAgain" size="18"/>
                    </li>
                </div>
                <input type="submit"  class="butlogin" value="Submit" id="submitBtn"/>
            </ul>
        </form>
        <br><br><br><br>
    <div class="filed">
        <%
            String msg = String.valueOf(request.getAttribute("msg"));
            if (msg.equals("null")){
                msg = "";
            }

        %>

        <p><%=msg%></p>
    </div>
</div>
<script src="/resources/libs/jquery.js"></script>
<script src="/resources/js/owner/modifyPass.js"></script>
</body>
</html>