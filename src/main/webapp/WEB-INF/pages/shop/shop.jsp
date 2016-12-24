<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>店铺的名字</title>
    <link rel="stylesheet" href="/resources/css/base.css" type="text/css"/>
    <link rel="stylesheet" href="/resources/css/shop_common.css" type="text/css"/>
    <link rel="stylesheet" href="/resources/css/shop_header.css" type="text/css"/>
    <link rel="stylesheet" href="/resources/css/shop_manager.css" type="text/css"/>
    <link rel="stylesheet" href="/resources/css/shop_list.css" type="text/css"/>
    <script type="text/javascript" src="/resources/js/jquery.js"></script>
    <script type="text/javascript" src="/resources/js/topNav.js"></script>
    <script type="text/javascript" src="/resources/js/search.js"></script>
</head>
<%@include file="../customer/head.jsp" %>
<body onload="setShopId(${shopId})">

<!-- shop -->
<div class="shop_member_bd clearfix">

    <img src="/resources/images/test.jpg" alt="" style="width: 1000px; height: 500px;">
    <div>
        <div class="shop_bd_list_left clearfix">
            <!-- 左边商品分类 -->
            <div class="shop_bd_list_bk clearfix">
                <div class="title">Categories</div>
                <div class="contents clearfix">
                    <dl class="shop_bd_list_type_links clearfix">
                        <dd id="productType" style="cursor: pointer">
                            <span id="typeSelectFirst" class="typeSelected" onclick="setType(this,'')">All&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</span>
                            <span onclick="setType(this,'TV& Home Theater')">TV& Home Theater</span>
                            <span onclick="setType(this,'Computers & Tablets')">Computers & Tablets</span>
                            <span onclick="setType(this,'Cell Phones')">Cell Phones</span>
                            <span onclick="setType(this,'Cameras & Camcorders')">Cameras & Camcorders</span>
                            <span onclick="setType(this,'Audio')">Audio</span>
                            <span onclick="setType(this,'Car Electronics & GPS')">Car Electronics & GPS</span>
                            <span onclick="setType(this,'Video, Games, Movies & Music')">Video, Games, Movies & Music</span>
                            <span onclick="setType(this,'Health, Fitness & Sports')">Health, Fitness & Sports</span>
                            <span onclick="setType(this,'Home & Office')">Home & Office&nbsp&nbsp&nbsp&nbsp&nbsp</span>
                            <span onclick="setType(this,'other')">Others</span>
                        </dd>
                        <%--<dd>--%>
                        <%--<span><input type="radio"> TV& Home Theater</span>--%>
                        <%--<span><input type="radio"> Computers & Tablets</span>--%>
                        <%--<span><input type="radio"> Cell Phones</span>--%>
                        <%--<span><input type="radio"> Cameras & Camcorders</span>--%>
                        <%--<span><input type="radio"> Audio</span>--%>
                        <%--<span><input type="radio"> Car Electronics & GPS</span>--%>
                        <%--<span><input type="radio"> Video, Games, Movies & Music</span>--%>
                        <%--<span><input type="radio"> Health, Fitness & Sports</span>--%>
                        <%--<span><input type="radio"> Home & Office</span>--%>
                        <%--</dd>--%>
                    </dl>
                </div>
            </div>
        </div>
        <!-- 左边商品分类 End -->

            <div class="shop_bd_list_right clearfix">

                <!-- 显示菜单 -->
                <div class="sort-bar">
                    <div class="bar-l">
                        <!-- 排序方式S -->
                        <ul class="array">
                            <li class="selected" id='defaultOrder'><a title="默认排序" onclick="orderByDefault()"
                                                                      class="nobg" href="javascript:void(0)">Default</a>
                            </li>
                            <li id="salesHightoLowOrder"><a title="点击按销量从高到低排序"
                                                            onclick="orderBySalesHightoLow()"
                                                            href="javascript:void(0)">Sales</a></li>
                            <li id="viewsHightoLowOrder"><a title="点击按人气从高到低排序"
                                                            onclick="orderByViewsHightoLow()"
                                                            href="javascript:void(0)">Hot</a></li>
                            <li id="priceHightoLowOrder"><a title="点击按价格从高到低排序"
                                                            onclick="orderByPriceHightoLow()"
                                                            href="javascript:void(0)">Price</a></li>
                        </ul>
                        <!-- 排序方式E -->
                    </div>
                </div>
            </div>
        </div>
            <!-- 显示菜单 End -->

            <!-- 商品列表 -->
            <div class="shop_bd_list_content clearfix">
                <ul id="productList">

                </ul>
            </div>
        </div>

        <div class="pagination">
            <ul id="pageList">
            </ul>
        </div>
        <!-- 商品列表 End -->


    </div>
</div>
</div>
</div>
<!-- Footer End -->
</body>
</html>