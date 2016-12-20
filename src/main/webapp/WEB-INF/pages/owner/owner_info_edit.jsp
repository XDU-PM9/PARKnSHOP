<%@ page import="com.parknshop.controller.OwnerController" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="/resources/libs/iconfont.css">
    <link rel="stylesheet" href="/resources/css/owner/owner.css">
</head>
<body>
    <div class="main">
        <p>Now,update your information</p>
        <form name="form" enctype="multipart/form-data" method="post" action="/owner/ownerInfoEdit">
            <ul class="formInfor">
                <div class="inforBlock">
                    <i class="iconfont">&#xe60d;:</i>
                    <li>
                        <input placeholder="Please input your new userName" name="username" type="text" id="userName" size="18"/>
                    </li><span></span>
                </div>

                <div class="inforBlock">
                    <i class="iconfont">&#xe604;:</i>
                    <li>
                        <input placeholder="Please input your new phone" name="phone" type="text" id="userPhone" size="18"/>
                    </li>
                </div>
                <div class="inforBlock">
                    <i class="iconfont">&#xe649;:</i>
                    <li>
                        <input placeholder="Please input your new email" name="email" type="text" id="userEmail" size="18"/>
                    </li>
                </div>
                <input type="submit"  class="butlogin" value="Update" id="updateBtn"/>
            </ul>
        </form>
        <div class="filed">
            <%
                String msg = (String) request.getAttribute(OwnerController.MSG);
                if (msg!=null && !msg.equals("null")){
                    out.println("<p>"+msg+"</p>");
                }
            %>

        </div>
    </div>
    <script src="/resources/libs/jquery.js"></script>
    <script src="/resources/js/owner/updateInfor.js"></script>
</body>
</html>