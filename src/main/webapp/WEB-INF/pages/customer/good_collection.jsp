<%--
  Created by IntelliJ IDEA.
  User: H
  Date: 2016/12/17
  Time: 20:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>Product Collection</title>
    <link rel="stylesheet" href="../../../resources/css/base.css" type="text/css"/>
    <link rel="stylesheet" href="../../../resources/css/shop_common.css" type="text/css"/>
    <link rel="stylesheet" href="../../../resources/css/shop_header.css" type="text/css"/>
    <link rel="stylesheet" href="../../../resources/css/shop_manager.css" type="text/css"/>
    <link rel="stylesheet" href="../../../resources/css/shop_list.css" type="text/css"/>
    <script type="text/javascript" src="../../../resources/js/jquery.js"></script>
    <script type="text/javascript" src="../../../resources/js/topNav.js"></script>
</head>
<body>
    <%@include file="head.jsp"%>

    <div class="shop_member_bd clearfix">
    <%@include file="customer_left.jsp"%>

    <!-- 右边购物列表 -->
    <div class="shop_member_bd_right clearfix">

        <div class="shop_meber_bd_good_lists clearfix">
            <!-- 商品列表 -->
            <div class="shop_meber_bd_good_lists clearfix">
                <div class="title"><h3>Product collection</h3></div>
                <c:choose>
                <c:when test="${Collects.size()<=0}">
                      Not Collections,let's add a product to your Collection.
                </c:when>
                <c:when test="${Collects.size()>0}">
                <!-- 商品列表 -->
                <div class="shop_bd_list_content clearfix">
                    <ul>
                        <c:forEach var="c" items="${Collects}">
                            <li>
                                <dl>
                                    <dt><a href=""><img src="${c.getPicture()}"/></a>
                                    </dt>
                                    <dd class="title"><a href="">${c.getGoodsByGoodsId().getGoodsName()}</a></dd>
                                    <dd class="content">
                                        <span class="goods_jiage">￥<strong>${c.getGoodsByGoodsId().getPrice() }</strong></span>
                                        <span class="goods_chengjiao"> <a href="/addProduct?goodsId=${c.getGoodsByGoodsId().getGoodsId()}&amount=1">Add to Cart</a></span>
                                    </dd>
                                </dl>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
                <div class="clear"></div>
                <div class="pagination">
                    <ul>
                        <c:choose>
                            <c:when test="${currentPage>1}"> <li><a href="/listCollect?requestPage=${currentPage-1}"> <span>Previous</span></a></li></c:when>
                        </c:choose>
                        <li><span class="currentpage">${currentPage}</span></li>
                        <c:choose>
                            <c:when test="${currentPage<sina}"> <li><a href="/listCollect?requestPage=${currentPage+1}"> <span>Next</span> </a></li></c:when>
                        </c:choose>

                        A total of <c:out value="${sina}"></c:out>  Pages.
                        <form action="/jumpPage" method="post">
                            <input type="hidden" name="ty" value="1">
                            <input type="number"  name="jump"  min="1" max="${sina}" value="1"/>
                            <input type="submit" value="jump">
                        </form>
                    </ul>
                </div>
                <!-- 商品列表 End -->

            </div>
                </c:when>
                </c:choose>


        </div>
        <!-- 右边购物列表 End -->

    </div>
    <!-- 我的个人中心 End -->
    </div>
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
