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
                out.println("<li>");
                out.println("<p>"+item.getName()+"</p>");
                out.println("<p>"+item.getDesc()+"</p>");
                out.println("<p>"+item.getLogo()+"</p>");
                out.println("<p>"+item.getState()+"</p>");
                out.println("</li>");
            }
            out.println("</ul>");
        }
    %>

</body>
</html>
