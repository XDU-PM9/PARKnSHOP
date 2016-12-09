<%@ page import="java.util.List" %>
<%@ page import="com.parknshop.bean.ShopAndOwnerDbBean" %>
<%@ page import="com.google.gson.Gson" %>
<%@ page import="com.parknshop.bean.ShopBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        Gson mGson = new Gson();
    %>
    <title>Title</title>
</head>
<body>

    <%
        String listStr = (String) request.getAttribute("shopList");
        String msg = (String) request.getAttribute("msg");
        if (null != msg){
            out.println("<a href=\"/owner/register\">Click here to apply a new shop</a>");
        }
        if (null == listStr){
            out.println("<p>no records</p>");
        }else {
            ShopBean shopList = mGson.fromJson(listStr, ShopBean.class);
            out.println("<ul>");
            for (ShopBean.Shop item : shopList.getShops()) {
                out.println("<li>" + item.getName() + "</li>");
            }
            out.println("</ul>");
        }
    %>

</body>
</html>
