<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<c:if test="${empty type}">
    <H1>please pay the money</H1><br>
    <H1>orderNum:${orderNum}</H1><br>
    <H1>addressId:${addressId}</H1><br>
    <form  action="/pay/f" method="get">

        <input  type="hidden" name="addressId" value="${addressId}">
        <input  type="hidden" name="orderNum" value="${orderNum}">
        <button  type="submit" style="height:80px;width:400px;"> submit</button>
    </form>
        <button style="height:80px;width:400px;" onclick="window.location.href='/order/listOrder'">i have already paid</button>
</c:if>
<c:if test="${not empty type}">
    <H1>AdervitismentType: ${typeString}</H1>
    <H1>Id: ${typeId}</H1>

    <form  action="/pay/af" method="get">

        <input  type="hidden" name="type" value="${type}">
        <input  type="hidden" name="typeId" value="${typeId}">
        <button  type="submit" style="height:80px;width:400px;"> submit</button>
    </form>
    <c:if test="${type = 0}">
        <button style="height:80px;width:400px;" onclick="window.location.href='/owner/query'">i have already paid</button>
    </c:if>
    <c:if test="${type = 1}">
        <button style="height:80px;width:400px;" onclick="window.location.href='/owner/goodsPage'">i have already paid</button>
    </c:if>
</c:if>

</body>
</html>
