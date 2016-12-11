<%@ page import="com.google.gson.Gson" %>
<%@ page import="com.google.gson.GsonBuilder" %>
<%@ page import="com.parknshop.utils.DateFormat" %>
<%@ page import="com.parknshop.controller.OwnerController" %>
<%@ page import="com.parknshop.service.serviceImpl.ShopListBean" %><%--
  Created by IntelliJ IDEA.
  User: fallb
  Date: 2016/12/11
  Time: 19:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Gson mGson = new GsonBuilder()
            .setDateFormat(DateFormat.getDateFormat())
            .create();
%>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <%
        String listStr = (String) request.getAttribute(OwnerController.GOODS);
        ShopListBean goodsList = mGson.fromJson(listStr,ShopListBean.class);
        if (goodsList.getNumer()==0){
            out.println("<p>no data</p>");
        }
        for (l)
    %>

</body>
</html>
