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
    <script type="text/javascript" src="/resources/js/jquery-3.1.1.min.js" ></script>
    <script type="text/javascript" src="/resources/js/layer/layer.js"></script>
    <title>My Orders</title>
    <script>
        function confirmReceiveOrder(orderNumber) {
            $.ajax({
                url:"/order/confirmReceive",
                type:'POST',
                data:{
                    orderNum:orderNumber
                },
                success:function (msg) {
                    if("success"==msg){
                        layer.alert("Confirm receive success");
                        window.location.reload();
                    }else {
                        layer.alert("Confirm receive fail");
                    }
                }
            })
        }
    </script>
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
                                <td class="dingdan_zongjia">$<strong>${order.amount*order.price}</strong><br />
                                    <%--(免运费)--%>
                                </td>
                                <td class="digndan_caozuo"><span>
                                    <c:choose>
                                        <c:when test="${order.state==1}"><a href="listCart?&OrdersNum=${order.orderNumber}">Processing Orders</a></c:when>
                                        <c:when test="${order.state==2}">Preparing for Shippment</c:when>
                                        <c:when test="${order.state==3}">Shipped
                                            <%--<a onclick="confirmReceiveOrder('${order.orderNumber}')">Confirm receive</a>--%>
                                            <button  onclick="confirmReceiveOrder('${order.orderNumber}')">confirm receive</button>
                                        </c:when>
                                        <c:when test="${order.state==4}"><a href="/comment/toComment?ordersId=${order.ordersId}">Complete</a></c:when>
                                        <c:when test="${order.state==5}">Commented</c:when>
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

        <div class="Paging" style="text-align: right">
            <div class="Pagination">
                <c:if test="${page>1}">
                    <input type='button' onclick='window.location=href="/order/listOrder?page=${page-1}"' value='Previous'></input>
                </c:if>

                <span class='currentpage' id='currentPage'>${page} of ${pages}</span>

                <c:if test="${page<pages}">
                    <input type='button' onclick='window.location=href="/order/listOrder?page=${page+1}"' value='Next'></input>
                </c:if>

                <input id='pageNumber' name='jump' type='number' value="${page}" value='1' min='1' max='${pages}'>
                <input type='button' name='jump' value='jump' onclick='window.location.href="/order/listOrder?page="+$("#pageNumber").val()'>
            </div>
        </div>
    </div>
    <!-- 右边购物列表 End -->

</div>

</body>
</html>
