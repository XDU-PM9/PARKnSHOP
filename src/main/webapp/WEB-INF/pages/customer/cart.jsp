<%--
  Created by IntelliJ IDEA.
  User: H
  Date: 2016/12/17
  Time: 20:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>My Cart</title>
    <link rel="stylesheet" href="../../../resources/css/base.css" type="text/css" />
    <link rel="stylesheet" href="../../../resources/css/shop_common.css" type="text/css" />
    <link rel="stylesheet" href="../../../resources/css/shop_header.css" type="text/css" />
    <link rel="stylesheet" href="../../../resources/css/shop_manager.css" type="text/css" />
    <link rel="stylesheet" href="../../../resources/css/shop_shdz_835.css" type="text/css" />
    <link rel="stylesheet" href="../../../resources/css/shop_gouwuche.css" type="text/css" />
    <script type="text/javascript" src="/../../../resources/js/jquery.js" ></script>
    <script type="text/javascript" src="../../../resources/js/topNav.js" ></script>
    <script type="text/javascript" src="../../../resources/js/jquery.goodnums.js" ></script>
    <script type="text/javascript" src="../../../resources/js/shop_gouwuche.js" ></script>
</head>
<body>
<%@include file="head.jsp"%>
<!-- 购物车 Body -->
<div class="shop_gwc_bd clearfix">

    <div class="shop_gwc_bd_contents clearfix">

        <!-- 购物车列表 -->
        <table>
            <thead>
            <tr>
                <th colspan="2"><span>Product</span></th>
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
                    <input type="hidden" name="goodsId" value="${cart.getGoodsId()}"/>
                    <input name="id1" type="checkbox" value="${cart.getCartId()}" />
                </td>
                <td class="gwc_list_title"><a href="">${cart.getGoodsName()} </a></td>
                <td class="gwc_list_danjia"><span>￥<strong id="danjia_001">${cart.getPrice()}</strong></span></td>
                <td class="gwc_list_shuliang"><span><a class="good_num_jian this_good_nums"  ty="-" href="/changeAmount?cartId=${cart.getCartId()}&amount=${cart.getAmount()-1}">-</a>
                    <input type="text" value="${cart.getAmount()}" id="goods_001" class="good_nums" /><a href="/changeAmount?cartId=${cart.getCartId()}&amount=${cart.getAmount()+1}"  ty="+" class="good_num_jia this_good_nums">+</a></span></td>
                <td class="gwc_list_xiaoji"><span>￥<strong id="xiaoji_001" class="good_xiaojis">${cart.getPrice()*cart.getAmount()}</strong></span></td>
                <td class="gwc_list_caozuo"><a href="/insertCollect?goodsId=${cart.getGoodsId()}">collect</a><a href="/removeProduct?goodsId=${cart.getCartId()}" class="shop_good_delete">delete</a></td>
            </tr>
</c:forEach>
            </tbody>
            <tfoot>
            <tr>
                <td colspan="6">
                    <div class="gwc_foot_zongjia">Commodity price<span>$<strong id="good_zongjia"></strong></span></div>
                    <div class="clear"></div>
                    <div class="gwc_foot_links">
                        <a href="" class="go">Go Shopping</a>
                        <a href="" class="op">Confirm & Fill out the Orders</a>
                    </div>
                </td>
            </tr>
            </tfoot>
        </table>
        <!-- 购物车列表 End -->
    </div>
    <!-- 购物车有商品 end -->

</div>
<!-- 购物车 Body End -->

<!-- Footer - wll - 2013/3/24 -->
<div class="clear"></div>
<div class="shop_footer">

    <div class="shop_footer_copy">
        <p>Copyright 2004-2013 itcast Inc.,All rights reserved.</p>
    </div>
</div>
<!-- Footer End -->
</body>
</html>
