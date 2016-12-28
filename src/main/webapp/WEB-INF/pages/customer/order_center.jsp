<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2016/12/28
  Time: 0:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="../../../resources/customer/common.css" type="text/css" />
    <link rel="stylesheet" href="../../../resources/customer/style.css" type="text/css" />
    <link rel="stylesheet" href="../../../resources/customer/iconfont.css" type="text/css" />
    <script src="../../../resources/customer/jquery-1.9.1.min.js" type="text/javascript"></script>
    <script src="../../../resources/customer/jquery.SuperSlide.2.1.1.js" type="text/javascript"></script>
    <script src="../../../resources/customer/common_js.js" type="text/javascript"></script>
    <script type="application/javascript">
        function  display1(i) {
            var qs=document.getElementsByName("order_details");
            if(qs[i].style.display=="none"){
                qs[i].style.display="block";
            }else
            {
                qs[i].style.display="none";
            }
        }

    </script>
</head>
<body>

<%@include file="head.jsp"%>
<div class="shop_member_bd clearfix">
    <%@include file="customer_left.jsp"%>
    <div class="right_style">
        <div class="info_content">
            <div class="title_Section"><span style="border-bottom:3px solid #F30;
             padding:7px 10px;border-bottom:1px solid #ddd; height:40px;
             line-height:40px; font-size:18px;">Order Management</span></div>
            <div class="Order_Sort">
                <ul>
                    <li><a href=""><img src="../../../resources/images/customer/icon-dingdan1.png"><br>待付款（2）</a></li>
                    <li><a href=""><img src="../../../resources/images/customer/icon-dingdan.png"><br>已完成（2）</a></li>
                    <li><a href=""><img src="../../../resources/images/customer/icon-kuaidi.png"><br>运输中（2）</a></li>
                    <li class=""><a href=""><img src="../../../resources/images/customer/icon-weibiaoti101.png"><br>派件中（2）</a></li>
                </ul>
            </div>
            <div class="Order_form_list">
                <table>
                    <thead>
                    <tr><td class="list_name_title0">Product</td>
                        <td class="list_name_title1">Price</td>
                        <td class="list_name_title2">Amount</td>
                        <td class="list_name_title4">Money</td>
                        <td class="list_name_title5">Order state</td>
                        <td class="list_name_title6">Operation</td>
                    </tr></thead>
                    <%int i=0;%>
                    <c:forEach  var="u" items="${orders}">

                        <tbody>
                        <tr class="Order_info"><td colspan="6" class="Order_form_time">createTime：${u.createTime}| OrderNum：${u.orderNumber} <em onclick="display1(<%=i%>)"></em></td></tr>
                        <tr class="Order_Details" name="order_details">
                            <td colspan="3">
                                <table class="Order_product_style">
                                        <tbody>
                                        <tr>
                                            <td>
                                                <div class="product_name clearfix">
                                                    <a href="#" class="product_img"><img src="${u.photo}" width="40px" height="40px"></a>
                                                    <a href="#">${u.goodsName}</a>
                                                    <p class="specification">${u.goodsDescribe}</p>
                                                </div>
                                            </td>
                                            <td>${u.price}</td>
                                            <td>${u.amount}</td>
                                        </tr>
                                        </tbody>

                                </table>
                            </td>
                            <td class="split_line">${u.price*u.amount}</td>
                            <td class="split_line">state state state</td>
                            <td class="operating">
                                <a href="#">Detail</a>

                            </td>
                        </tr>
                        </tbody>
                        <%i++;%>
                    </c:forEach>
                </table>
            </div>

        </div>
        <div class="Paging">
            <div class="Pagination">
                <a href="#">首页</a>
                <a href="#" class="pn-prev disabled">&lt;上一页</a>
                <a href="#" class="on">1</a>
                <a href="#">2</a>
                <a href="#">3</a>
                <a href="#">4</a>
                <a href="#">下一页&gt;</a>
                <a href="#">尾页</a>
            </div>
        </div>
    </div>

</div>
</body>
</html>