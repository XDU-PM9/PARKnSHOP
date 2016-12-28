<%@ page import="com.parknshop.entity.OrdersEntity" %>
<%@ page import="com.parknshop.service.IListBean" %>
<%@ page import="com.parknshop.controller.OwnerOrderController" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: SONG
  Date: 2016/12/28
  Time: 13:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <ul>
    <%
        IListBean<OrdersEntity> uncheckedOrder = (IListBean<OrdersEntity>) request.getAttribute(OwnerOrderController.UNCHECKED_ORDER);
        if (uncheckedOrder!=null && uncheckedOrder.getShopList()!=null && uncheckedOrder.getShopList().size() != 0){
            List<OrdersEntity> orderList = uncheckedOrder.getShopList();
            for (OrdersEntity entity :  orderList){
                out.println("<li>");
                out.println("name:"+entity.getGoodsName());
                out.println("</li>");

            }
        }else {
            out.println("<p>empty</p>");
        }
    %>
    </ul>
</body>
</html>
