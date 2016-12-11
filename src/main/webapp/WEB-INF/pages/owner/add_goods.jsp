<%--
  Created by IntelliJ IDEA.
  User: fallb
  Date: 2016/12/11
  Time: 20:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="/owner/addGoods" enctype="multipart/form-data" method="post">
    <div>
        <input type="text" name="name" id="name">
    </div>

    <div>
        <input type="number" name="price" id="price" />
    </div>

    <div>
        <input type="number" name="inventory" id="inventory">
    </div>

    <div>
        <input type="text" name="desc" id="desc">
    </div>

    <div>
        <input type="file" name="photo" id="photo" />
    </div>

    <div>
        <input type="submit" value="提交" />
    </div>
</form>

</body>
</html>
