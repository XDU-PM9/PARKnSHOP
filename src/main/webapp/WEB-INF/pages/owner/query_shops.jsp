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
    <link href="/resources/css/owner/top.css" rel="stylesheet" type="text/css"/>
    <link href="/resources/css/owner/owner-main.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<h1>
    <span class="action-span1"><a href="">Apply List</a> </span>
    <div style="clear:both"></div>
</h1>

<div class="form-div">
    <form action="javascript:search_brand()" name="searchForm">
        <img src="/resources/images/ico/icon_search.gif" width="26" height="22" border="0" alt="SEARCH">
        <input type="text" name="brand_name" size="35" placeholder="Please input the shopId">
        <input type="submit" value="Search" class="button">
    </form>
</div>


<form method="post" action="" name="listForm">
    <!-- start brand list -->
    <div class="list-div" id="listDiv">
        <table cellpadding="3" cellspacing="1">
            <tbody id="tableInfor">
            <tr class="queryHead">
                <th class="logoHead">Logo</th>
                <th class="shopName">Name</th>
                <th class="shopDes">Description</th>
                <th class="shopState">State</th>
                <%
                    String msg = (String) request.getAttribute("msg");
                    if (msg.equals("1")) {
                        out.println("<th><a href=\"/owner/apply\"><img src=\"/resources/images/owner/button_add.png\" alt=\"\" id=\"addBtn1\"></a></th>");
                    } else {
                        out.println("<th></th>");
                    }
                %>
            </tr>
            <%
                String listStr = (String) request.getAttribute("shops");
                if (null == listStr) {
                    out.println("<p>no records</p>");
                } else {
                    ShopBean shopList = mGson.fromJson(listStr, ShopBean.class);
                    for (ShopBean.Shop item : shopList.getShops()) {
                        out.println("<tr class=\"shopInfor\">");
                        out.println("<td class=\"logoHead\"><img src=\"" + item.getLogo() + "\" alt=\"\" style=\"width:120px;height:100px;\" class=\"shopLogo\"/></td>");
                        out.println("<td class=\"shopName\">" + item.getName() + "</td>");
                        out.println("<td class=\"shopDes\">" + item.getDesc() + "</td>");
                        out.println("<td class=\"shopState\">" + item.getState() + "</td>");
                        out.println("</tr>");
                    }
                }
            %>
            </tbody>
        </table>
    </div>
</form>
<script src="/resources/libs/jquery.js"></script>
<script src="/resources/js/owner/main.js"></script>
</body>
</html>
