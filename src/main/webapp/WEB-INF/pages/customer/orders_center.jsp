<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2016/12/28
  Time: 15:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Calendar,java.util.Date"%>
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
            <c:if test="${nums>0}">
            <table>
                <thead class="tab_title">
                <th style="width:410px;"><span>Product Info</span></th>
                <th style="width:100px;"><span>Price</span></th>
                <th style="width:80px;"><span>Amount</span></th>
                <th style="width:100px;"><span>Summary</span></th>
                <th style="width:115px;"><span>State</span></th>
                </thead>
                <tbody>
                <%int i=0;%>
                <c:forEach var="order" items="${orderList}">

                    <tr><td colspan="5">
                        <table class="good">
                            <thead >
                            <c:if test="${order.state==1}">
                            <tr><th colspan="6">
                                <span><strong>Order Num：</strong>${order.orderNumber}</span>
                            </th>
                                </c:if>
                                <c:if test="${order.state>1&&order.state<=5}">
                            <tr><th colspan="4">
                                <span><strong>Order Num：</strong>${order.orderNumber}</span>
                            </th>
                            <th  colspan="3">
                                <div name="asd"></div>
                                <Script type="text/javascript" language="JavaScript">
                                    function  check(d) {
                                        if(d<10)
                                            return "0"+d;
                                        else
                                            return d;
                                    }
                                    function  getBetweenDate(d) {
                                        var date=new Date(new Date(d)-0+7*86400000);
                                        var da=new Date();
                                        if(date-da<0){
                                            document.getElementsByName("asd")[0].value="";
                                        }else{
                                            var d=(date.getDate()-da.getDate());
                                            var h=(date.getHours()-da.getHours());
                                            var m=(date.getMinutes()-da.getMinutes());
                                            var ds=(date.getSeconds()-da.getSeconds());
                                        if(ds<0){m=m-1;ds+=60;}
                                        if(m<0){h-=1;m+=60;}
                                        if(h<0){d=d-1;h=h+24;}
                                        var s=check(d)+"days:"+check(h)+":"+check(m)+":"+check(ds);
                                        document.getElementsByName("asd")[0].innerHTML="remain "+ s;
                                        window.setTimeout("getBetweenDate(d,index)",1000);
                                    }
                                    }

                                    window.setTimeout("getBetweenDate('${order.paidTime}')",1000);
                                </Script>
                                <%i++;%>
                            </th>
                            </c:if>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td class="dingdan_pic"><img src="${order.photo}" /></td>
                                <td class="dingdan_title"><span>${order.goodsName}</span></td>
                                <td class="dingdan_danjia">$<strong>${order.price/order.amount}</strong></td>
                                <td class="dingdan_shuliang">${order.amount}</td>
                                <td class="dingdan_zongjia">$<strong>${order.price}</strong><br />
                                    <%--(免运费)--%>
                                </td>
                                <td class="digndan_caozuo"><span>
                                    <c:choose>
                                        <c:when test="${order.state==1}"><a href="/order/listCart?OrdersNum=${order.orderNumber}">Processing Orders</a></c:when>
                                        <c:when test="${order.state==2}">Preparing for Shippment

                                        </c:when>
                                        <c:when test="${order.state==3}">Shipped
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
        </c:if>
        <c:if test="${nums==0}">
            <div>
                <img src="/resources/images/customer/shoucang.png"/>
                <div  style="width: 700px;margin-left:280px;margin-top: -160px;padding-bottom:100px;margin-bottom: 100px;">
                    <font style="font-family: 'Microsoft Yahei';font-size:24px;line-height: 48px;"> You have not produced any orders yet!<br/></font>
                    <font  style="font-family:'Microsoft Yahei';font-size:16px;line-height: 18px;">  <a href="/"> Let's go shopping, and choose your favorite.</a>

                    </font>
                </div>
            </div>
        </c:if>
    </div>
    <!-- 右边购物列表 End -->

</div>

</body>
</html>
