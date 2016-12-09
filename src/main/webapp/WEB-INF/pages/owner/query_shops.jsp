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
        String listStr = (String) request.getAttribute("shops");
        String msg = (String) request.getAttribute("msg");
        System.out.println(msg);
        if (msg.equals("1")){
            out.println("<a href=\"/owner/apply\">Click here to apply a new shop</a>");
        }else {
            out.println("<p></p>");
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
