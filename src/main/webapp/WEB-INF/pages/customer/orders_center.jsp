<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2016/12/28
  Time: 15:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%@include file="head.jsp"%>
<div class="shop_member_bd clearfix">
<%@include file="customer_left.jsp"%>
    <div class="shop_member_bd_right clearfix">

        <div class="shop_meber_bd_good_lists clearfix">
            <div class="title"><h3>Order List</h3></div>
            <table>
                <thead class="tab_title">
                <th style="width:410px;"><span>Product Info</span></th>
                <th style="width:100px;"><span>Price</span></th>
                <th style="width:80px;"><span>Amount</span></th>
                <th style="width:100px;"><span>Summary</span></th>
                <th style="width:115px;"><span>State</span></th>
                </thead>
                <tbody>

                <tr><td colspan="5">
                    <table class="good">
                        <thead >
                        <tr><th colspan="6">
                            <span><strong>Order Num：</strong>2013032905510051</span>
                        </th></tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td class="dingdan_pic"><img src="images/1dbc94fa0d60cba3990b89ccb01f82c2.jpg_tiny.jpg" /></td>
                            <td class="dingdan_title"><span><a href="">李宁 lining 专柜正品 足球鞋 女式运动鞋【演示数据】</a></span><br />鞋码:37 颜色:黑色 </td>
                            <td class="dingdan_danjia">$<strong>25.00</strong></td>
                            <td class="dingdan_shuliang">1</td>
                            <td class="dingdan_zongjia">$<strong>25.00</strong><br />(免运费)</td>
                            <td class="digndan_caozuo"><a href="">等待买家付款</a></td>
                        </tr>
                        </tbody>
                    </table>
                </td></tr>




                </tbody>
            </table>
        </div>
    </div>
    <!-- 右边购物列表 End -->

</div>
</body>
</html>
