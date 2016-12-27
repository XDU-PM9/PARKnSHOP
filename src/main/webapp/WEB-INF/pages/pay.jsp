<%--
  Created by IntelliJ IDEA.
  User: weina
  Date: 2016/12/27
  Time: 17:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<H1>please pay the money</H1><br>
<H1>orderNum:${orderNum}</H1><br>
<H1>addressId:${addressId}</H1><br>
<form  action="/pay/f" method="get">
    <input  type="hidden" name="orderNum" value="${orderNum}">
    <input  type="hidden" name="addressId" value="${addressId}">
    <button  type="submit" style="height:80px;width:400px;"> submit</button>
</form>
</body>
</html>
