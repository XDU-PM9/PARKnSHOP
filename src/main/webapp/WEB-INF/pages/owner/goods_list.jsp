<%@ page import="com.google.gson.Gson" %>
<%@ page import="com.google.gson.GsonBuilder" %>
<%@ page import="com.parknshop.utils.DateFormat" %>
<%@ page import="com.parknshop.controller.OwnerController" %>
<%@ page import="com.parknshop.service.serviceImpl.ShopListBean" %>
<%@ page import="com.parknshop.bean.owner.GoodsListBean" %><%--
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
        String msg = (String) request.getAttribute(OwnerController.MSG);
        if (msg.equals("0")){
            out.println("<p>Shop state error</p>");
        }else {
            out.println("<a href=\"/owner/addGoods\">add goods</a><br>");
            GoodsListBean goodsList = (GoodsListBean) request.getAttribute(OwnerController.GOODS);
            if (goodsList==null || goodsList.getCount() == 0) {
                out.println("<p>no data</p>");
            }else {
                out.println("<ul>");
                for (GoodsListBean.DataBean item : goodsList.getData()){
                    out.println("<li>");
                    out.println("<p>id:"+item.getId()+"</p>");
                    out.println("<p>name:"+item.getName()+"</p>");
                    out.println("<p>desc:"+item.getDesc()+"</p>");
                    out.println("<p>price:"+item.getPrice()+"</p>");
                    out.println("<p>views:"+item.getViews()+"</p>");
                    out.println("<p>discount:"+item.getDiscount()+"</p>");
                    out.print("<p>photos:");
                    String[] photos = item.getPhotos();
                    for (String p : photos){
                        out.print(p+" ");
                    }
                    out.println("</p>");
                    out.println("<p>createTime"+item.getCreateTime()+"</p>");
                    out.println("</li>");

                }
                out.println("</ul>");
            }
        }
    %>

</body>
</html>
