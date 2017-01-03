<%--
  Created by IntelliJ IDEA.
  User: H
  Date: 2016/12/17
  Time: 20:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>My Cart</title>
    <link rel="stylesheet" href="../../../resources/css/base.css" type="text/css"/>
    <link rel="stylesheet" href="../../../resources/css/shop_common.css" type="text/css"/>
    <link rel="stylesheet" href="../../../resources/css/shop_header.css" type="text/css"/>
    <link rel="stylesheet" href="../../../resources/css/shop_manager.css" type="text/css"/>
    <link rel="stylesheet" href="../../../resources/css/shop_shdz_835.css" type="text/css"/>
    <link rel="stylesheet" href="../../../resources/css/shop_gouwuche.css" type="text/css"/>
    <script type="text/javascript" src="/resources/js/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="../../../resources/js/topNav.js"></script>
    <script type="text/javascript" src="../../../resources/js/jquery.goodnums.js"></script>
    <script type="text/javascript" src="../../../resources/js/shop_gouwuche.js"></script>
    <script type="text/javascript" src="../../../resources/js/shop.js"></script>
    <script type="text/javascript" src="/resources/js/layer/layer.js"></script>
</head>
<body>
<%@include file="head.jsp" %>
<!-- 购物车 Body -->
<div class="shop_gwc_bd clearfix">

    <div class="shop_gwc_bd_contents clearfix">

        <c:choose>
            <c:when test="${cartList.size()==0}">
                <div>
                    <img src="/resources/images/customer/cart.png"/>
                    <div style="width: 700px;margin-left:280px;margin-top: -380px;padding-bottom:100px;margin-bottom: 100px;">
                        <font style="font-family: 'Microsoft Yahei';font-size:24px;line-height: 48px;"> Your cart is
                            still empty!Let's go shopping!You can<br/>
                            <a href="/listCollect?requestPage=1"> Look at your collects!</a><br/>
                            <a href="/"> Go Shopping!</a>
                        </font>
                    </div>
                </div>
            </c:when>


            <c:when test="${cartList.size()>0}">
                <!-- 购物车列表 -->
                <table>
                    <thead>
                    <tr>
                        <th colspan="2"><span>&nbsp&nbsp&nbsp&nbsp&nbsp&nbspProduct</span></th>
                        <th><span>Price($)</span></th>
                        <th><span>amount</span></th>
                        <th><span>summary</span></th>
                        <th><span>operation</span></th>
                    </tr>
                    </thead>

                    <tbody>
                    <c:forEach var="cart" items="${cartList}">
                        <tr>
                            <td class="checkbox">
                                    <%--<input type="hidden" name="goodsId" value="${cart.getGoodsId()}"/>--%>
                                    <%--<c:if test="${cart.goodsAmount>=cart.amount}">--%>
                                <input name="id1" type="checkbox" id="${cart.cartId}"
                                        <c:if test="${cart.goodsAmount < cart.amount}">
                                            disabled="disabled" style="cursor: not-allowed"
                                        </c:if>
                                       value="${cart.getCartId()}"/>
                                    <%--</c:if>--%>
                            </td>
                            <td class="gwc_list_title"><a href="/goods/detail?goodsId=${cart.goodsId}">${cart.goodsName} </a></td>
                            <td class="gwc_list_danjia"><span>HK$<strong
                                    id="danjia${cart.cartId}">
                                    <fmt:formatNumber value="${cart.price}"  type="number" maxFractionDigits="2"></fmt:formatNumber>
                                    <%--${cart.getPrice()}--%>
                            </strong></span></td>
                            <td class="gwc_list_shuliang"><span>
                                <input type="number" value="${cart.amount}" id="goods_001" name="num1" min="1"
                                       onchange="amountChange(${cart.cartId},$(this).val(),${cart.goodsAmount},${cart.price/cart.amount})"
                                       class="good_nums"/>
                                <p>In stock：${cart.goodsAmount}</p>
                            </span>
                            </td>
                            <td class="gwc_list_xiaoji"><span>HK$<strong id="xiaoji${cart.cartId}"
                                                                       class="good_xiaojis">
                                                                       <fmt:formatNumber value="${cart.price*cart.amount}" type="number" maxFractionDigits="2"></fmt:formatNumber>
                                                                       <%--${cart.getPrice()*cart.getAmount()}--%>
                            </strong></span>
                            </td>
                            <td class="gwc_list_caozuo"><a
                                    href="/insertCollect?goodsId=${cart.getGoodsId()}">collect</a><a
                                    href="/removeProduct?goodsId=${cart.getCartId()}"
                                    <%--class="shop_good_delete"--%>
                            >delete</a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                    <tfoot>

                    <tr>
                        <td colspan="6">
                            <div class="clear"></div>
                            <div class="gwc_foot_links">

                                <div id="tips" style="color: red">
                                </div>
                            </div>
                        </td>
                    </tr>
                    </tfoot>
                </table>
                <%--<form id="cartSubmitForm" method="put">--%>
                    <input id="confirmButton" type="button"
                           style="background: none repeat scroll 0 0 #FE8502; border: 1px solid #FF6633; border-radius: 5px 5px 5px 5px; color: #FFFFFF !important; display: inline-block; font-size: 14px; font-weight: 100; height: 36px; line-height: 36px; padding: 4px 12px;"
                           onclick="selectAll()" value="Select All"></input>
                        <%--<button onclick="selectAll()">select all</button>--%>
                <div style="text-align: right">
                    <%--<a href="" class="go">Go Shopping</a>--%>
                        <input id="confirmButton" type="button"
                               style="background: none repeat scroll 0 0 #082bff; border: 1px solid #082bff; border-radius: 5px 5px 5px 5px; color: #FFFFFF !important; display: inline-block; font-size: 14px; font-weight: 300; height: 36px; line-height: 36px; padding: 4px 12px;"
                               onclick="window.location.href='/'" value="Go Shopping"></input>
                    <input id="confirmButton" type="button"
                           style="background: none repeat scroll 0 0 #FE8502; border: 1px solid #FF6633; border-radius: 5px 5px 5px 5px; color: #FFFFFF !important; display: inline-block; font-size: 14px; font-weight: 600; height: 36px; line-height: 36px; padding: 4px 12px;"
                           onclick="submitCart()" value="Confirm & Fill out the Orders"></input>
                        <%--</form>--%>
                </div>

                <!-- 购物车列表 End -->
                <div name="message" style="font-family: 'Microsoft YaHei';font-size: larger;color: red"></div>

            </c:when>
        </c:choose>

    </div>
    <!-- 购物车有商品 end -->

</div>
<!-- 购物车 Body End -->

<%@include file="footer.jsp" %>

</body>
</html>
