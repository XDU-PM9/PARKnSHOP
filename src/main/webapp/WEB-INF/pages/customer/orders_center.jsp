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

                <c:forEach var="order" items="${orderList}">
                    <tr><td colspan="5">
                        <table class="good">
                            <thead >
                            <tr><th colspan="6">
                                <span><strong>Order Num：</strong>${order.orderNumber}</span>
                            </th></tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td class="dingdan_pic"><img src="${order.photo}" /></td>
                                <td class="dingdan_title"><span>${order.goodsName}</span></td>
                                <td class="dingdan_danjia">$<strong>${order.price}</strong></td>
                                <td class="dingdan_shuliang">${order.amount}</td>
                                <td class="dingdan_zongjia">$<strong>${order.amount*order.price}</strong><br />(免运费)</td>
                                <td class="digndan_caozuo"><span>
                                    <c:choose>
                                        <c:when test="${orders.getState()==-1}">deleted</c:when>
                                        <c:when test="${orders.getState()==1}">Processing Orders</c:when>
                                        <c:when test="${orders.getState()==2}">Preparing for Shippment</c:when>
                                        <c:when test="${orders.getState()==3}">Shipped</c:when>
                                        <c:when test="${orders.getState()==4}">Complete</c:when>
                                        <c:when test="${orders.getState()==5}">Commented</c:when>
                                    </c:choose>
                                </span></td>
                            </tr>
                            </tbody>
                        </table>
                    </td></tr>
                </c:forEach>



                </tbody>
            </table>
        </div>
        <div class="Paging">
            <div class="Pagination">
                <c:if test="${page>1}">
                    <li><input type='button' onclick='window.location=href="/order/listOrder?page=${page-1}"' value='Previous'></input></li>
                </c:if>

                <li><span class='currentpage' id='currentPage'>${page} of ${pages}</span></li>

                <c:if test="${page<pages}">
                    <li><input type='button' onclick='window.location=href="/order/listOrder?page=${page+1}"' value='Next'></input></li>
                </c:if>

                <li><input id='pageNumber' name='jump' type='number' value='1' min='1' max='${pages}'></li>")
                <li><input type='button' name='jump' value='jump' onclick='window.location.href="/order/listOrder?pag="+$("#pageNumber").val()'></li>
            </div>
        </div>
    </div>
    <!-- 右边购物列表 End -->

</div>
</body>
</html>
