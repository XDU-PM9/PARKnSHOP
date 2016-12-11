<!DOCTYPE html>

<%@ page import="com.google.gson.Gson" %>
<%@ page import="com.parknshop.bean.ShopBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>

    <%
    Gson mGson = new Gson();
    %>

    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="/resources/css/owner/main.css">
</head>
<body bgcolor="#f0f8ff">

<table>
        <tr class="queryHead">
            <th class="logoHead">Logo</th>
            <th class="shopName">Name</th>
            <th class="shopDes">Description</th>
            <th class="shopState">State</th>

            <%
                String msg = (String) request.getAttribute("msg");
                if (msg.equals("1")){
                    out.println("<th><a href=\"/owner/apply\"><img src=\"/resources/images/owner/button_add.png\" alt=\"\" id=\"addBtn\"></a></th>");
                }else {
                    out.println("<th></th>");
                }
            %>

            <%--<th><a href="#"><img src="/resources/images/owner/button_add.png" alt="" id="addBtn"></a></th>--%>
        </tr>
</table>
<ul>
    <%--<li class="shopInfor">--%>
        <%--<p class="logoHead"><img src="test/a.png" alt="" class="shopLogo"/></p>--%>
        <%--<p class="shopName">Android</p>--%>
        <%--<p class="shopDes">Android是一种基于Linux的自由及开放源代码的操作系统，主要使用于移动设备，如智能手机和平板电脑，由Google公司和开放手机联盟领导及开发</p>--%>
        <%--<p class="shopState">Apply</p>--%>
    <%--</li>--%>
        <%--动态加载商店--%>
    <%
        String listStr = (String) request.getAttribute("shops");
        if (null == listStr){
            out.println("<p>no records</p>");
        }else {
            ShopBean shopList = mGson.fromJson(listStr, ShopBean.class);
            for (ShopBean.Shop item : shopList.getShops()) {
                out.println("<li class=\"shopInfor\">");
                out.println("<p class=\"logoHead\"><img src=\""+item.getLogo()+"\" alt=\"\" class=\"shopLogo\"/></p>");
                out.println("<p class=\"shopName\">"+item.getName()+"</p>");
                out.println("<p class=\"shopDes\">"+item.getDesc()+"</p>");
                out.println("<p class=\"shopState\">"+item.getState()+"</p>");
                out.println("</li>");
            }
        }
    %>

</ul>
<script src="/resources/libs/jquery.js"></script>
<script src="/resources/js/owner/main.js"></script>
</body>
</html>

