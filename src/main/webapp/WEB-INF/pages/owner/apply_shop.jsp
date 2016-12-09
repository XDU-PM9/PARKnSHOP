<%--
  Created by IntelliJ IDEA.
  User: fallb
  Date: 2016/12/9
  Time: 15:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <form action="/owner/apply" enctype="multipart/form-data" method="post">
        <div>
            <input type="text" name="realName" id="realName">
        </div>

        <div>
            <input type="text" name="idNumber" id="idNumber">
        </div>

        <div>
            <input type="text" name="phone" id="phone">
        </div>


        <div>
            <input type="file" name="person" id="person" />
        </div>

        <div>
            <input type="text" name="shopName" id="shopName">
        </div>

        <div>
            <input type="text" name="shopDesc" id="shopDesc">
        </div>

        <div>
            <input type="file" name="logo" id="logo" />
        </div>

        <div>
            <input type="file" name="theme" id="theme" />
        </div>


        <div>
            <input type="submit" value="提交" />
        </div>
    </form>

</body>
</html>
