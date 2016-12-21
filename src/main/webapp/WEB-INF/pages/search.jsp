<%--
  Created by IntelliJ IDEA.
  User: wei
  Date: 16-12-16
  Time: 下午5:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
    <title>Search</title>
    <link rel="stylesheet" href="/resources/css/base.css" type="text/css"/>
    <link rel="stylesheet" href="/resources/css/shop_common.css" type="text/css"/>
    <link rel="stylesheet" href="/resources/css/shop_header.css" type="text/css"/>
    <link rel="stylesheet" href="/resources/css/shop_list.css" type="text/css"/>
    <script type="text/javascript" src="/resources/js/jquery.js"></script>
    <script type="text/javascript" src="/resources/js/topNav.js"></script>
    <script src="/resources/js/search.js"></script>
</head>
<%--页面加载完成后立即执行搜索--%>
<body onload="search()">
<!-- Header  -wll-2013/03/24 -->
<div class="shop_hd">
    <!-- Header TopNav -->

      <%@include file="customer/top.jsp"%>


    <!-- TopHeader Center -->
    <div class="shop_hd_header">
        <div class="shop_hd_header_logo"><h1 class="logo"><a href="/"><img
                src="/resources/images/ico/banner.jpg" id="logo" alt="ShopCZ"/></a><span>ShopCZ</span></h1></div>
        <div class="shop_hd_header_search">
            <ul class="shop_hd_header_search_tab">
                        <li id="search" class="current">Product</li>
                        <li id="shop_search">Shop</li>
            </ul>
            <div class="clear"></div>
            <div class="search_form">
                <%--<form method="post" action="/search">--%>
                    <div class="search_formstyle">
                        <input type="text" class="search_form_text" id="searchText" name="search_content"
                               placeholder="Search!" value="${param.name}"/>
                        <input type="button" class="search_form_sub" name="secrch_submit" value="" title="Search"
                               onclick="search()"/>
                    </div>
                <%--</form>--%>
            </div>
            <div class="clear"></div>

        </div>
    </div>
    <div class="clear"></div>
    <!-- TopHeader Center End -->

    <!-- Header Menu -->
    <div class="shop_hd_menu">
        <!-- 所有商品菜单 -->

        <div id="shop_hd_menu_all_category" class="shop_hd_menu_all_category">
            <!-- 首页去掉 id="shop_hd_menu_all_category" 加上clsss shop_hd_menu_hover -->
            <div class="shop_hd_menu_all_category_title"><h2 title="所有商品分类"><a href="javascript:void(0);">Categories</a>
            </h2><i></i></div>
            <div id="shop_hd_menu_all_category_hd" class="shop_hd_menu_all_category_hd">
                <ul class="shop_hd_menu_all_category_hd_menu clearfix">
                    <!-- 单个菜单项 -->
                    <li id="cat_1" class="">
                        <h3><a href="" title="男女服装">TV& Home Theater</a></h3>
                        <!--<div id="cat_1_menu" class="cat_menu clearfix" style="">-->
                        <!--<dl class="clearfix">-->
                        <!--<dt><a href="女装" href="">女装</a></dt>-->
                        <!--<dd>-->
                        <!--<input type="radio" name="sex" value="male" checked> 风衣-->
                        <!--<a href="">长袖连衣裙</a>-->
                        <!--<a href="">毛呢连衣裙</a>-->
                        <!--<a href="">半身裙</a>-->
                        <!--<a href="">小脚裤</a>-->
                        <!--<a href="">加绒打底裤</a>-->
                        <!--<a href="">牛仔裤</a>-->
                        <!--<a href="">打底衫</a>-->
                        <!--<a href="">情侣装</a>-->
                        <!--<a href="">棉衣</a>-->
                        <!--<a href="">毛呢大衣</a>-->
                        <!--<a href="">毛呢短裤</a>-->
                        <!--</dd>-->
                        <!--</dl>-->

                        <!--<dl class="clearfix">-->
                        <!--<dt><a href="男装" href="">男装</a></dt>-->
                        <!--<dd>-->
                        <!--<a href="">风衣</a>-->
                        <!--<a href="">长袖连衣裙</a>-->
                        <!--<a href="">毛呢连衣裙</a>-->
                        <!--<a href="">半身裙</a>-->
                        <!--<a href="">小脚裤</a>-->
                        <!--<a href="">加绒打底裤</a>-->
                        <!--<a href="">牛仔裤</a>-->
                        <!--<a href="">打底衫</a>-->
                        <!--<a href="">情侣装</a>-->
                        <!--<a href="">棉衣</a>-->
                        <!--<a href="">毛呢大衣</a>-->
                        <!--<a href="">毛呢短裤</a>-->
                        <!--</dd>-->
                        <!--</dl>-->
                        <!--</div>-->
                    </li>
                    <!-- 单个菜单项 End -->
                    <li id="cat_2" class="">
                        <h3><a href="" title="鞋包配饰">Computers & Tablets</a></h3>
                        <!--<div id="cat_1_menu" class="cat_menu clearfix" style="">-->
                        <!--<dl class="clearfix">-->
                        <!--<dt><a href="鞋子" href="">鞋子</a></dt>-->
                        <!--<dd>-->
                        <!--<a href="">风衣</a>-->
                        <!--<a href="">长袖连衣裙</a>-->
                        <!--<a href="">毛呢连衣裙</a>-->
                        <!--<a href="">半身裙</a>-->
                        <!--<a href="">小脚裤</a>-->
                        <!--<a href="">加绒打底裤</a>-->
                        <!--<a href="">牛仔裤</a>-->
                        <!--<a href="">打底衫</a>-->
                        <!--<a href="">情侣装</a>-->
                        <!--<a href="">棉衣</a>-->
                        <!--<a href="">毛呢大衣</a>-->
                        <!--<a href="">毛呢短裤</a>-->
                        <!--</dd>-->
                        <!--</dl>-->

                        <!--<dl class="clearfix">-->
                        <!--<dt><a href="包包" href="">包包</a></dt>-->
                        <!--<dd>-->
                        <!--<a href="">风衣</a>-->
                        <!--<a href="">长袖连衣裙</a>-->
                        <!--<a href="">毛呢连衣裙</a>-->
                        <!--<a href="">半身裙</a>-->
                        <!--<a href="">小脚裤</a>-->
                        <!--<a href="">加绒打底裤</a>-->
                        <!--<a href="">牛仔裤</a>-->
                        <!--<a href="">打底衫</a>-->
                        <!--<a href="">情侣装</a>-->
                        <!--<a href="">棉衣</a>-->
                        <!--<a href="">毛呢大衣</a>-->
                        <!--<a href="">毛呢短裤</a>-->
                        <!--</dd>-->
                        <!--</dl>-->
                        <!--</div>-->
                    </li>

                    <li id="cat_3" class="">
                        <h3><a href="" title="美容美妆">Cell Phones</a></h3>
                        <!--<div id="cat_1_menu" class="cat_menu clearfix" style="">-->
                        <!--<dl class="clearfix">-->
                        <!--<dt><a href="美容" href="">美容</a></dt>-->
                        <!--<dd>-->
                        <!--<a href="">风衣</a>-->
                        <!--<a href="">长袖连衣裙</a>-->
                        <!--<a href="">毛呢连衣裙</a>-->
                        <!--<a href="">半身裙</a>-->
                        <!--<a href="">小脚裤</a>-->
                        <!--<a href="">加绒打底裤</a>-->
                        <!--<a href="">牛仔裤</a>-->
                        <!--<a href="">打底衫</a>-->
                        <!--<a href="">情侣装</a>-->
                        <!--<a href="">棉衣</a>-->
                        <!--<a href="">毛呢大衣</a>-->
                        <!--<a href="">毛呢短裤</a>-->
                        <!--</dd>-->
                        <!--</dl>-->

                        <!--<dl class="clearfix">-->
                        <!--<dt><a href="美妆" href="">美妆</a></dt>-->
                        <!--<dd>-->
                        <!--<a href="">风衣</a>-->
                        <!--<a href="">长袖连衣裙</a>-->
                        <!--<a href="">毛呢连衣裙</a>-->
                        <!--<a href="">半身裙</a>-->
                        <!--<a href="">小脚裤</a>-->
                        <!--<a href="">加绒打底裤</a>-->
                        <!--<a href="">牛仔裤</a>-->
                        <!--<a href="">打底衫</a>-->
                        <!--<a href="">情侣装</a>-->
                        <!--<a href="">棉衣</a>-->
                        <!--<a href="">毛呢大衣</a>-->
                        <!--<a href="">毛呢短裤</a>-->
                        <!--</dd>-->
                        <!--</dl>-->
                        <!--</div>-->
                    </li>

                    <li id="cat_4" class="">
                        <h3><a href="" title="美容美妆">Cameras & Camcorders</a></h3>
                        <!--<div id="cat_1_menu" class="cat_menu clearfix" style="">-->
                        <!--<dl class="clearfix">-->
                        <!--<dt><a href="美容" href="">美容</a></dt>-->
                        <!--<dd>-->
                        <!--<a href="">风衣</a>-->
                        <!--<a href="">长袖连衣裙</a>-->
                        <!--<a href="">毛呢连衣裙</a>-->
                        <!--<a href="">半身裙</a>-->
                        <!--<a href="">小脚裤</a>-->
                        <!--<a href="">加绒打底裤</a>-->
                        <!--<a href="">牛仔裤</a>-->
                        <!--<a href="">打底衫</a>-->
                        <!--<a href="">情侣装</a>-->
                        <!--<a href="">棉衣</a>-->
                        <!--<a href="">毛呢大衣</a>-->
                        <!--<a href="">毛呢短裤</a>-->
                        <!--</dd>-->
                        <!--</dl>-->

                        <!--<dl class="clearfix">-->
                        <!--<dt><a href="美妆" href="">美妆</a></dt>-->
                        <!--<dd>-->
                        <!--<a href="">风衣</a>-->
                        <!--<a href="">长袖连衣裙</a>-->
                        <!--<a href="">毛呢连衣裙</a>-->
                        <!--<a href="">半身裙</a>-->
                        <!--<a href="">小脚裤</a>-->
                        <!--<a href="">加绒打底裤</a>-->
                        <!--<a href="">牛仔裤</a>-->
                        <!--<a href="">打底衫</a>-->
                        <!--<a href="">情侣装</a>-->
                        <!--<a href="">棉衣</a>-->
                        <!--<a href="">毛呢大衣</a>-->
                        <!--<a href="">毛呢短裤</a>-->
                        <!--</dd>-->
                        <!--</dl>-->
                        <!--</div>-->
                    </li>

                    <li id="cat_5" class="">
                        <h3><a href="" title="美容美妆">Audio</a></h3>
                        <!--<div id="cat_1_menu" class="cat_menu clearfix" style="">-->
                        <!--<dl class="clearfix">-->
                        <!--<dt><a href="美容" href="">美容</a></dt>-->
                        <!--<dd>-->
                        <!--<a href="">风衣</a>-->
                        <!--<a href="">长袖连衣裙</a>-->
                        <!--<a href="">毛呢连衣裙</a>-->
                        <!--<a href="">半身裙</a>-->
                        <!--<a href="">小脚裤</a>-->
                        <!--<a href="">加绒打底裤</a>-->
                        <!--<a href="">牛仔裤</a>-->
                        <!--<a href="">打底衫</a>-->
                        <!--<a href="">情侣装</a>-->
                        <!--<a href="">棉衣</a>-->
                        <!--<a href="">毛呢大衣</a>-->
                        <!--<a href="">毛呢短裤</a>-->
                        <!--</dd>-->
                        <!--</dl>-->

                        <!--<dl class="clearfix">-->
                        <!--<dt><a href="美妆" href="">美妆</a></dt>-->
                        <!--<dd>-->
                        <!--<a href="">风衣</a>-->
                        <!--<a href="">长袖连衣裙</a>-->
                        <!--<a href="">毛呢连衣裙</a>-->
                        <!--<a href="">半身裙</a>-->
                        <!--<a href="">小脚裤</a>-->
                        <!--<a href="">加绒打底裤</a>-->
                        <!--<a href="">牛仔裤</a>-->
                        <!--<a href="">打底衫</a>-->
                        <!--<a href="">情侣装</a>-->
                        <!--<a href="">棉衣</a>-->
                        <!--<a href="">毛呢大衣</a>-->
                        <!--<a href="">毛呢短裤</a>-->
                        <!--</dd>-->
                        <!--</dl>-->
                        <!--</div>-->
                    </li>

                    <li id="cat_6" class="">
                        <h3><a href="" title="美容美妆">Car Electronics & GPS</a></h3>
                        <!--<div id="cat_1_menu" class="cat_menu clearfix" style="">-->
                        <!--<dl class="clearfix">-->
                        <!--<dt><a href="美容" href="">美容</a></dt>-->
                        <!--<dd>-->
                        <!--<a href="">风衣</a>-->
                        <!--<a href="">长袖连衣裙</a>-->
                        <!--<a href="">毛呢连衣裙</a>-->
                        <!--<a href="">半身裙</a>-->
                        <!--<a href="">小脚裤</a>-->
                        <!--<a href="">加绒打底裤</a>-->
                        <!--<a href="">牛仔裤</a>-->
                        <!--<a href="">打底衫</a>-->
                        <!--<a href="">情侣装</a>-->
                        <!--<a href="">棉衣</a>-->
                        <!--<a href="">毛呢大衣</a>-->
                        <!--<a href="">毛呢短裤</a>-->
                        <!--</dd>-->
                        <!--</dl>-->

                        <!--<dl class="clearfix">-->
                        <!--<dt><a href="美妆" href="">美妆</a></dt>-->
                        <!--<dd>-->
                        <!--<a href="">风衣</a>-->
                        <!--<a href="">长袖连衣裙</a>-->
                        <!--<a href="">毛呢连衣裙</a>-->
                        <!--<a href="">半身裙</a>-->
                        <!--<a href="">小脚裤</a>-->
                        <!--<a href="">加绒打底裤</a>-->
                        <!--<a href="">牛仔裤</a>-->
                        <!--<a href="">打底衫</a>-->
                        <!--<a href="">情侣装</a>-->
                        <!--<a href="">棉衣</a>-->
                        <!--<a href="">毛呢大衣</a>-->
                        <!--<a href="">毛呢短裤</a>-->
                        <!--</dd>-->
                        <!--</dl>-->
                        <!--</div>-->
                    </li>
                    <li id="cat_7" class="">
                        <h3><a href="" title="美容美妆">Video, Games, Movies & Music</a></h3>
                        <!--<div id="cat_1_menu" class="cat_menu clearfix" style="">-->
                        <!--<dl class="clearfix">-->
                        <!--<dt><a href="美容" href="">美容</a></dt>-->
                        <!--<dd>-->
                        <!--<a href="">风衣</a>-->
                        <!--<a href="">长袖连衣裙</a>-->
                        <!--<a href="">毛呢连衣裙</a>-->
                        <!--<a href="">半身裙</a>-->
                        <!--<a href="">小脚裤</a>-->
                        <!--<a href="">加绒打底裤</a>-->
                        <!--<a href="">牛仔裤</a>-->
                        <!--<a href="">打底衫</a>-->
                        <!--<a href="">情侣装</a>-->
                        <!--<a href="">棉衣</a>-->
                        <!--<a href="">毛呢大衣</a>-->
                        <!--<a href="">毛呢短裤</a>-->
                        <!--</dd>-->
                        <!--</dl>-->

                        <!--<dl class="clearfix">-->
                        <!--<dt><a href="美妆" href="">美妆</a></dt>-->
                        <!--<dd>-->
                        <!--<a href="">风衣</a>-->
                        <!--<a href="">长袖连衣裙</a>-->
                        <!--<a href="">毛呢连衣裙</a>-->
                        <!--<a href="">半身裙</a>-->
                        <!--<a href="">小脚裤</a>-->
                        <!--<a href="">加绒打底裤</a>-->
                        <!--<a href="">牛仔裤</a>-->
                        <!--<a href="">打底衫</a>-->
                        <!--<a href="">情侣装</a>-->
                        <!--<a href="">棉衣</a>-->
                        <!--<a href="">毛呢大衣</a>-->
                        <!--<a href="">毛呢短裤</a>-->
                        <!--</dd>-->
                        <!--</dl>-->
                        <!--</div>-->
                    </li>
                    <li id="cat_9" class="">
                        <h3><a href="" title="美容美妆">Health, Fitness & Sports</a></h3>
                        <!--<div id="cat_1_menu" class="cat_menu clearfix" style="">-->
                        <!--<dl class="clearfix">-->
                        <!--<dt><a href="美容" href="">美容</a></dt>-->
                        <!--<dd>-->
                        <!--<a href="">风衣</a>-->
                        <!--<a href="">长袖连衣裙</a>-->
                        <!--<a href="">毛呢连衣裙</a>-->
                        <!--<a href="">半身裙</a>-->
                        <!--<a href="">小脚裤</a>-->
                        <!--<a href="">加绒打底裤</a>-->
                        <!--<a href="">牛仔裤</a>-->
                        <!--<a href="">打底衫</a>-->
                        <!--<a href="">情侣装</a>-->
                        <!--<a href="">棉衣</a>-->
                        <!--<a href="">毛呢大衣</a>-->
                        <!--<a href="">毛呢短裤</a>-->
                        <!--</dd>-->
                        <!--</dl>-->

                        <!--<dl class="clearfix">-->
                        <!--<dt><a href="美妆" href="">美妆</a></dt>-->
                        <!--<dd>-->
                        <!--<a href="">风衣</a>-->
                        <!--<a href="">长袖连衣裙</a>-->
                        <!--<a href="">毛呢连衣裙</a>-->
                        <!--<a href="">半身裙</a>-->
                        <!--<a href="">小脚裤</a>-->
                        <!--<a href="">加绒打底裤</a>-->
                        <!--<a href="">牛仔裤</a>-->
                        <!--<a href="">打底衫</a>-->
                        <!--<a href="">情侣装</a>-->
                        <!--<a href="">棉衣</a>-->
                        <!--<a href="">毛呢大衣</a>-->
                        <!--<a href="">毛呢短裤</a>-->
                        <!--</dd>-->
                        <!--</dl>-->
                        <!--</div>-->
                    </li>
                    <li id="cat_8" class="">
                        <h3><a href="" title="美容美妆">Home & Office</a></h3>
                        <!--<div id="cat_1_menu" class="cat_menu clearfix" style="">-->
                        <!--<dl class="clearfix">-->
                        <!--<dt><a href="美容" href="">美容</a></dt>-->
                        <!--<dd>-->
                        <!--<a href="">风衣</a>-->
                        <!--<a href="">长袖连衣裙</a>-->
                        <!--<a href="">毛呢连衣裙</a>-->
                        <!--<a href="">半身裙</a>-->
                        <!--<a href="">小脚裤</a>-->
                        <!--<a href="">加绒打底裤</a>-->
                        <!--<a href="">牛仔裤</a>-->
                        <!--<a href="">打底衫</a>-->
                        <!--<a href="">情侣装</a>-->
                        <!--<a href="">棉衣</a>-->
                        <!--<a href="">毛呢大衣</a>-->
                        <!--<a href="">毛呢短裤</a>-->
                        <!--</dd>-->
                        <!--</dl>-->

                        <!--<dl class="clearfix">-->
                        <!--<dt><a href="美妆" href="">美妆</a></dt>-->
                        <!--<dd>-->
                        <!--<a href="">风衣</a>-->
                        <!--<a href="">长袖连衣裙</a>-->
                        <!--<a href="">毛呢连衣裙</a>-->
                        <!--<a href="">半身裙</a>-->
                        <!--<a href="">小脚裤</a>-->
                        <!--<a href="">加绒打底裤</a>-->
                        <!--<a href="">牛仔裤</a>-->
                        <!--<a href="">打底衫</a>-->
                        <!--<a href="">情侣装</a>-->
                        <!--<a href="">棉衣</a>-->
                        <!--<a href="">毛呢大衣</a>-->
                        <!--<a href="">毛呢短裤</a>-->
                        <!--</dd>-->
                        <!--</dl>-->
                        <!--</div>-->
                    </li>
                </ul>
            </div>
        </div>
        <!-- 所有商品菜单 END -->

        <!--&lt;!&ndash; 普通导航菜单 &ndash;&gt;-->
        <!--<ul class="shop_hd_menu_nav">-->
        <!--<li class="current_link"><a href=""><span>首页</span></a></li>-->
        <!--<li class="link"><a href=""><span>团购</span></a></li>-->
        <!--<li class="link"><a href=""><span>品牌</span></a></li>-->
        <!--<li class="link"><a href=""><span>优惠卷</span></a></li>-->
        <!--<li class="link"><a href=""><span>积分中心</span></a></li>-->
        <!--<li class="link"><a href=""><span>运动专场</span></a></li>-->
        <!--<li class="link"><a href=""><span>微商城</span></a></li>-->
        <!--</ul>-->
        <!--&lt;!&ndash; 普通导航菜单 End &ndash;&gt;-->
    </div>
    <!-- Header Menu End -->

</div>
<div class="clear"></div>

<!-- Header End -->


<!-- List Body 2013/03/27 -->
<div class="shop_bd clearfix">
    <div class="shop_bd_list_left clearfix">
        <!-- 左边商品分类 -->
        <div class="shop_bd_list_bk clearfix">
            <div class="title">Categories</div>
            <div class="contents clearfix">
                <dl class="shop_bd_list_type_links clearfix">
                    <dd>
                        <span onclick="setType('')">All</span>
                        <span onclick="setType('TV& Home Theater')">TV& Home Theater</span>
                        <span onclick="setType('Computers & Tablets')">Computers & Tablets</span>
                        <span onclick="setType('Cell Phones')">Cell Phones</span>
                        <span onclick="setType('Cameras & Camcorders')">Cameras & Camcorders</span>
                        <span onclick="setType('Audio')">Audio</span>
                        <span onclick="setType('Car Electronics & GPS')">Car Electronics & GPS</span>
                        <span onclick="setType('Video, Games, Movies & Music')">Video, Games, Movies & Music</span>
                        <span onclick="setType('Health, Fitness & Sports')">Health, Fitness & Sports</span>
                        <span onclick="setType('Home & Office')">Home & Office</span>
                    </dd>
                </dl>
            </div>
        </div>
        <!-- 左边商品分类 End -->

        <!-- 热卖推荐商品 -->
        <!--<div class="shop_bd_list_bk clearfix">-->
        <!--<div class="title">热卖推荐商品</div>-->
        <!--<div class="contents clearfix">-->
        <!--<ul class="clearfix">-->
        <!---->
        <!--<li class="clearfix">-->
        <!--<div class="goods_name"><a href="">Gap经典弹力纯色长袖T恤|000891347|原价149元</a></div>-->
        <!--<div class="goods_pic"><span class="goods_price">¥ 279.00 </span><a href=""><img src="/resources/images/images/89a6d6466b00ae32d3c826b9ec639084.jpg_small.jpg" /></a></div>-->
        <!--<div class="goods_xiaoliang">-->
        <!--<span class="goods_xiaoliang_link"><a href="">去看看</a></span>-->
        <!--<span class="goods_xiaoliang_nums">已销售<strong>99</strong>笔</span>-->
        <!--</div>-->
        <!--</li>-->

        <!--<li class="clearfix">-->
        <!--<div class="goods_name"><a href="">Gap经典弹力纯色长袖T恤|000891347|原价149元</a></div>-->
        <!--<div class="goods_pic"><span class="goods_price">¥ 279.00 </span><a href=""><img src="/resources/images/images/89a6d6466b00ae32d3c826b9ec639084.jpg_small.jpg" /></a></div>-->
        <!--<div class="goods_xiaoliang">-->
        <!--<span class="goods_xiaoliang_link"><a href="">去看看</a></span>-->
        <!--<span class="goods_xiaoliang_nums">已销售<strong>99</strong>笔</span>-->
        <!--</div>-->
        <!--</li>-->

        <!--<li class="clearfix">-->
        <!--<div class="goods_name"><a href="">Gap经典弹力纯色长袖T恤|000891347|原价149元</a></div>-->
        <!--<div class="goods_pic"><span class="goods_price">¥ 279.00 </span><a href=""><img src="/resources/images/images/89a6d6466b00ae32d3c826b9ec639084.jpg_small.jpg" /></a></div>-->
        <!--<div class="goods_xiaoliang">-->
        <!--<span class="goods_xiaoliang_link"><a href="">去看看</a></span>-->
        <!--<span class="goods_xiaoliang_nums">已销售<strong>99</strong>笔</span>-->
        <!--</div>-->
        <!--</li>-->

        <!--</ul>-->
        <!--</div>-->
        <!--</div>-->
        <!--&lt;!&ndash; 热卖推荐商品 &ndash;&gt;-->
        <div class="clear"></div>

        <!-- 浏览过的商品 -->
        <!--<div class="shop_bd_list_bk clearfix">-->
        <!--<div class="title">浏览过的商品</div>-->
        <!--<div class="contents clearfix">-->
        <!--<ul class="clearfix">-->
        <!---->
        <!--<li class="clearfix">-->
        <!--<div class="goods_name"><a href="">Gap经典弹力纯色长袖T恤|000891347|原价149元</a></div>-->
        <!--<div class="goods_pic"><span class="goods_price">¥ 279.00 </span><a href=""><img src="/resources/images/images/89a6d6466b00ae32d3c826b9ec639084.jpg_small.jpg" /></a></div>-->
        <!--<div class="goods_xiaoliang">-->
        <!--<span class="goods_xiaoliang_link"><a href="">去看看</a></span>-->
        <!--<span class="goods_xiaoliang_nums">已销售<strong>99</strong>笔</span>-->
        <!--</div>-->
        <!--</li>-->

        <!--<li class="clearfix">-->
        <!--<div class="goods_name"><a href="">Gap经典弹力纯色长袖T恤|000891347|原价149元</a></div>-->
        <!--<div class="goods_pic"><span class="goods_price">¥ 279.00 </span><a href=""><img src="/resources/images/images/89a6d6466b00ae32d3c826b9ec639084.jpg_small.jpg" /></a></div>-->
        <!--<div class="goods_xiaoliang">-->
        <!--<span class="goods_xiaoliang_link"><a href="">去看看</a></span>-->
        <!--<span class="goods_xiaoliang_nums">已销售<strong>99</strong>笔</span>-->
        <!--</div>-->
        <!--</li>-->

        <!--<li class="clearfix">-->
        <!--<div class="goods_name"><a href="">Gap经典弹力纯色长袖T恤|000891347|原价149元</a></div>-->
        <!--<div class="goods_pic"><span class="goods_price">¥ 279.00 </span><a href=""><img src="/resources/images/images/89a6d6466b00ae32d3c826b9ec639084.jpg_small.jpg" /></a></div>-->
        <!--<div class="goods_xiaoliang">-->
        <!--<span class="goods_xiaoliang_link"><a href="">去看看</a></span>-->
        <!--<span class="goods_xiaoliang_nums">已销售<strong>99</strong>笔</span>-->
        <!--</div>-->
        <!--</li>-->

        <!--</ul>-->
        <!--</div>-->
        <!--</div>-->
        <!-- 浏览过的商品 -->

    </div>

    <div class="shop_bd_list_right clearfix">

        <!-- 显示菜单 -->
        <div class="sort-bar">
            <div class="bar-l">
                <!-- 查看方式S -->
                <!--<div class="switch"><span class="selected"><a title="以方格显示" ecvalue="squares" nc_type="display_mode" class="pm" href="javascript:void(0)">大图</a></span><span style="border-left:none;"><a title="以列表显示" ecvalue="list" nc_type="display_mode" class="lm" href="javascript:void(0)">列表</a></span></div>-->
                <!-- 查看方式E -->
                <!-- 排序方式S -->
                <ul class="array">
                    <li class="selected"><a title="默认排序" onclick="orderByDefault()"
                                            class="nobg" href="javascript:void(0)">Default</a></li>
                    <li><a title="点击按销量从高到低排序"
                           onclick="orderBySalesHightoLow()"
                           href="javascript:void(0)">Sales</a></li>
                    <li><a title="点击按人气从高到低排序"
                           onclick="orderByViewHightoLow()"
                           href="javascript:void(0)">Hot</a></li>
                    <%--<li><a title="点击按信用从高到低排序"--%>
                           <%--onclick="javascript:replaceParam(['key','order'],['credit','desc'],'array');"--%>
                           <%--href="javascript:void(0)">Credit</a></li>--%>
                    <li><a title="点击按价格从高到低排序"
                           onclick="orderByPriceHightoLow()"
                           href="javascript:void(0)">Price</a></li>
                </ul>
                <!-- 排序方式E -->
                <!-- 价格段S -->
                <!--<div class="prices"> <em>¥</em>-->
                <!--<input type="text" value="" class="w30">-->
                <!--<em>-</em>-->
                <!--<input type="text" value="" class="w30">-->
                <!--<input type="submit" value="确认" id="search_by_price">-->
                <!--</div>-->
                <!-- 价格段E -->
            </div>
        </div>
        <div class="clear"></div>
        <!-- 显示菜单 End -->

        <!-- 商品列表 -->
        <div class="shop_bd_list_content clearfix">
            <ul id="productList">
                <!--<li>-->
                <!--<dl>-->
                <!--<dt><a href=""><img src="/resources/images/images/21151da3bdefc6d9a7120c991fe59800.jpg_small.jpg" /></a></dt>-->
                <!--<dd class="title"><a href="">OCIAIZO春装水洗做旧短外套复古磨白短款牛仔外套春01C1417</a></dd>-->
                <!--<dd class="content">-->
                <!--<span class="goods_jiage">￥<strong>249.00</strong></span>-->
                <!--<span class="goods_chengjiao">最近成交5笔</span>-->
                <!--</dd>-->
                <!--</dl>-->
                <!--</li>-->

                <!--<li>-->
                <!--<dl>-->
                <!--<dt><a href=""><img src="/resources/images/images/21151da3bdefc6d9a7120c991fe59800.jpg_small.jpg" /></a></dt>-->
                <!--<dd class="title"><a href="">OCIAIZO春装水洗做旧短外套复古磨白短款牛仔外套春01C1417</a></dd>-->
                <!--<dd class="content">-->
                <!--<span class="goods_jiage">￥<strong>249.00</strong></span>-->
                <!--<span class="goods_chengjiao">最近成交5笔</span>-->
                <!--</dd>-->
                <!--</dl>-->
                <!--</li>-->

                <!--<li>-->
                <!--<dl>-->
                <!--<dt><a href=""><img src="/resources/images/images/21151da3bdefc6d9a7120c991fe59800.jpg_small.jpg" /></a></dt>-->
                <!--<dd class="title"><a href="">OCIAIZO春装水洗做旧短外套复古磨白短款牛仔外套春01C1417</a></dd>-->
                <!--<dd class="content">-->
                <!--<span class="goods_jiage">￥<strong>249.00</strong></span>-->
                <!--<span class="goods_chengjiao">最近成交5笔</span>-->
                <!--</dd>-->
                <!--</dl>-->
                <!--</li>-->

                <!--<li>-->
                <!--<dl>-->
                <!--<dt><a href=""><img src="/resources/images/images/21151da3bdefc6d9a7120c991fe59800.jpg_small.jpg" /></a></dt>-->
                <!--<dd class="title"><a href="">OCIAIZO春装水洗做旧短外套复古磨白短款牛仔外套春01C1417</a></dd>-->
                <!--<dd class="content">-->
                <!--<span class="goods_jiage">￥<strong>249.00</strong></span>-->
                <!--<span class="goods_chengjiao">最近成交5笔</span>-->
                <!--</dd>-->
                <!--</dl>-->
                <!--</li>-->

                <!--<li>-->
                <!--<dl>-->
                <!--<dt><a href=""><img src="/resources/images/images/21151da3bdefc6d9a7120c991fe59800.jpg_small.jpg" /></a></dt>-->
                <!--<dd class="title"><a href="">OCIAIZO春装水洗做旧短外套复古磨白短款牛仔外套春01C1417</a></dd>-->
                <!--<dd class="content">-->
                <!--<span class="goods_jiage">￥<strong>249.00</strong></span>-->
                <!--<span class="goods_chengjiao">最近成交5笔</span>-->
                <!--</dd>-->
                <!--</dl>-->
                <!--</li>-->

                <!--<li>-->
                <!--<dl>-->
                <!--<dt><a href=""><img src="/resources/images/images/21151da3bdefc6d9a7120c991fe59800.jpg_small.jpg" /></a></dt>-->
                <!--<dd class="title"><a href="">OCIAIZO春装水洗做旧短外套复古磨白短款牛仔外套春01C1417</a></dd>-->
                <!--<dd class="content">-->
                <!--<span class="goods_jiage">￥<strong>249.00</strong></span>-->
                <!--<span class="goods_chengjiao">最近成交5笔</span>-->
                <!--</dd>-->
                <!--</dl>-->
                <!--</li>-->

                <!--<li>-->
                <!--<dl>-->
                <!--<dt><a href=""><img src="/resources/images/images/21151da3bdefc6d9a7120c991fe59800.jpg_small.jpg" /></a></dt>-->
                <!--<dd class="title"><a href="">OCIAIZO春装水洗做旧短外套复古磨白短款牛仔外套春01C1417</a></dd>-->
                <!--<dd class="content">-->
                <!--<span class="goods_jiage">￥<strong>249.00</strong></span>-->
                <!--<span class="goods_chengjiao">最近成交5笔</span>-->
                <!--</dd>-->
                <!--</dl>-->
                <!--</li>-->

                <!--<li>-->
                <!--<dl>-->
                <!--<dt><a href=""><img src="/resources/images/images/21151da3bdefc6d9a7120c991fe59800.jpg_small.jpg" /></a></dt>-->
                <!--<dd class="title"><a href="">OCIAIZO春装水洗做旧短外套复古磨白短款牛仔外套春01C1417</a></dd>-->
                <!--<dd class="content">-->
                <!--<span class="goods_jiage">￥<strong>249.00</strong></span>-->
                <!--<span class="goods_chengjiao">最近成交5笔</span>-->
                <!--</dd>-->
                <!--</dl>-->
                <!--</li>-->

                <!--<li>-->
                <!--<dl>-->
                <!--<dt><a href=""><img src="/resources/images/images/21151da3bdefc6d9a7120c991fe59800.jpg_small.jpg" /></a></dt>-->
                <!--<dd class="title"><a href="">OCIAIZO春装水洗做旧短外套复古磨白短款牛仔外套春01C1417</a></dd>-->
                <!--<dd class="content">-->
                <!--<span class="goods_jiage">￥<strong>249.00</strong></span>-->
                <!--<span class="goods_chengjiao">最近成交5笔</span>-->
                <!--</dd>-->
                <!--</dl>-->
                <!--</li>-->

                <!--<li>-->
                <!--<dl>-->
                <!--<dt><a href=""><img src="/resources/images/images/21151da3bdefc6d9a7120c991fe59800.jpg_small.jpg" /></a></dt>-->
                <!--<dd class="title"><a href="">OCIAIZO春装水洗做旧短外套复古磨白短款牛仔外套春01C1417</a></dd>-->
                <!--<dd class="content">-->
                <!--<span class="goods_jiage">￥<strong>249.00</strong></span>-->
                <!--<span class="goods_chengjiao">最近成交5笔</span>-->
                <!--</dd>-->
                <!--</dl>-->
                <!--</li>-->

                <!--<li>-->
                <!--<dl>-->
                <!--<dt><a href=""><img src="/resources/images/images/21151da3bdefc6d9a7120c991fe59800.jpg_small.jpg" /></a></dt>-->
                <!--<dd class="title"><a href="">OCIAIZO春装水洗做旧短外套复古磨白短款牛仔外套春01C1417</a></dd>-->
                <!--<dd class="content">-->
                <!--<span class="goods_jiage">￥<strong>249.00</strong></span>-->
                <!--<span class="goods_chengjiao">最近成交5笔</span>-->
                <!--</dd>-->
                <!--</dl>-->
                <!--</li>-->

                <!--<li>-->
                <!--<dl>-->
                <!--<dt><a href=""><img src="/resources/images/images/21151da3bdefc6d9a7120c991fe59800.jpg_small.jpg" /></a></dt>-->
                <!--<dd class="title"><a href="">OCIAIZO春装水洗做旧短外套复古磨白短款牛仔外套春01C1417</a></dd>-->
                <!--<dd class="content">-->
                <!--<span class="goods_jiage">￥<strong>249.00</strong></span>-->
                <!--<span class="goods_chengjiao">最近成交5笔</span>-->
                <!--</dd>-->
                <!--</dl>-->
                <!--</li>-->

                <!--<li>-->
                <!--<dl>-->
                <!--<dt><a href=""><img src="/resources/images/images/21151da3bdefc6d9a7120c991fe59800.jpg_small.jpg" /></a></dt>-->
                <!--<dd class="title"><a href="">OCIAIZO春装水洗做旧短外套复古磨白短款牛仔外套春01C1417</a></dd>-->
                <!--<dd class="content">-->
                <!--<span class="goods_jiage">￥<strong>249.00</strong></span>-->
                <!--<span class="goods_chengjiao">最近成交5笔</span>-->
                <!--</dd>-->
                <!--</dl>-->
                <!--</li>-->

                <!--<li>-->
                <!--<dl>-->
                <!--<dt><a href=""><img src="/resources/images/images/21151da3bdefc6d9a7120c991fe59800.jpg_small.jpg" /></a></dt>-->
                <!--<dd class="title"><a href="">OCIAIZO春装水洗做旧短外套复古磨白短款牛仔外套春01C1417</a></dd>-->
                <!--<dd class="content">-->
                <!--<span class="goods_jiage">￥<strong>249.00</strong></span>-->
                <!--<span class="goods_chengjiao">最近成交5笔</span>-->
                <!--</dd>-->
                <!--</dl>-->
                <!--</li>-->

                <!--<li>-->
                <!--<dl>-->
                <!--<dt><a href=""><img src="/resources/images/images/21151da3bdefc6d9a7120c991fe59800.jpg_small.jpg" /></a></dt>-->
                <!--<dd class="title"><a href="">OCIAIZO春装水洗做旧短外套复古磨白短款牛仔外套春01C1417</a></dd>-->
                <!--<dd class="content">-->
                <!--<span class="goods_jiage">￥<strong>249.00</strong></span>-->
                <!--<span class="goods_chengjiao">最近成交5笔</span>-->
                <!--</dd>-->
                <!--</dl>-->
                <!--</li>-->

                <!--<li>-->
                <!--<dl>-->
                <!--<dt><a href=""><img src="/resources/images/images/21151da3bdefc6d9a7120c991fe59800.jpg_small.jpg" /></a></dt>-->
                <!--<dd class="title"><a href="">OCIAIZO春装水洗做旧短外套复古磨白短款牛仔外套春01C1417</a></dd>-->
                <!--<dd class="content">-->
                <!--<span class="goods_jiage">￥<strong>249.00</strong></span>-->
                <!--<span class="goods_chengjiao">最近成交5笔</span>-->
                <!--</dd>-->
                <!--</dl>-->
                <!--</li>-->

                <!--<li>-->
                <!--<dl>-->
                <!--<dt><a href=""><img src="/resources/images/images/21151da3bdefc6d9a7120c991fe59800.jpg_small.jpg" /></a></dt>-->
                <!--<dd class="title"><a href="">OCIAIZO春装水洗做旧短外套复古磨白短款牛仔外套春01C1417</a></dd>-->
                <!--<dd class="content">-->
                <!--<span class="goods_jiage">￥<strong>249.00</strong></span>-->
                <!--<span class="goods_chengjiao">最近成交5笔</span>-->
                <!--</dd>-->
                <!--</dl>-->
                <!--</li>-->

                <!--<li>-->
                <!--<dl>-->
                <!--<dt><a href=""><img src="/resources/images/images/21151da3bdefc6d9a7120c991fe59800.jpg_small.jpg" /></a></dt>-->
                <!--<dd class="title"><a href="">OCIAIZO春装水洗做旧短外套复古磨白短款牛仔外套春01C1417</a></dd>-->
                <!--<dd class="content">-->
                <!--<span class="goods_jiage">￥<strong>249.00</strong></span>-->
                <!--<span class="goods_chengjiao">最近成交5笔</span>-->
                <!--</dd>-->
                <!--</dl>-->
                <!--</li>-->

                <!--<li>-->
                <!--<dl>-->
                <!--<dt><a href=""><img src="/resources/images/images/21151da3bdefc6d9a7120c991fe59800.jpg_small.jpg" /></a></dt>-->
                <!--<dd class="title"><a href="">OCIAIZO春装水洗做旧短外套复古磨白短款牛仔外套春01C1417</a></dd>-->
                <!--<dd class="content">-->
                <!--<span class="goods_jiage">￥<strong>249.00</strong></span>-->
                <!--<span class="goods_chengjiao">最近成交5笔</span>-->
                <!--</dd>-->
                <!--</dl>-->
                <!--</li>-->

                <!--<li>-->
                <!--<dl>-->
                <!--<dt><a href=""><img src="/resources/images/images/21151da3bdefc6d9a7120c991fe59800.jpg_small.jpg" /></a></dt>-->
                <!--<dd class="title"><a href="">OCIAIZO春装水洗做旧短外套复古磨白短款牛仔外套春01C1417</a></dd>-->
                <!--<dd class="content">-->
                <!--<span class="goods_jiage">￥<strong>249.00</strong></span>-->
                <!--<span class="goods_chengjiao">最近成交5笔</span>-->
                <!--</dd>-->
                <!--</dl>-->
                <!--</li>-->

                <!--<li>-->
                <!--<dl>-->
                <!--<dt><a href=""><img src="/resources/images/images/21151da3bdefc6d9a7120c991fe59800.jpg_small.jpg" /></a></dt>-->
                <!--<dd class="title"><a href="">OCIAIZO春装水洗做旧短外套复古磨白短款牛仔外套春01C1417</a></dd>-->
                <!--<dd class="content">-->
                <!--<span class="goods_jiage">￥<strong>249.00</strong></span>-->
                <!--<span class="goods_chengjiao">最近成交5笔</span>-->
                <!--</dd>-->
                <!--</dl>-->
                <!--</li>-->

                <!--<li>-->
                <!--<dl>-->
                <!--<dt><a href=""><img src="/resources/images/images/21151da3bdefc6d9a7120c991fe59800.jpg_small.jpg" /></a></dt>-->
                <!--<dd class="title"><a href="">OCIAIZO春装水洗做旧短外套复古磨白短款牛仔外套春01C1417</a></dd>-->
                <!--<dd class="content">-->
                <!--<span class="goods_jiage">￥<strong>249.00</strong></span>-->
                <!--<span class="goods_chengjiao">最近成交5笔</span>-->
                <!--</dd>-->
                <!--</dl>-->
                <!--</li>-->

                <!--<li>-->
                <!--<dl>-->
                <!--<dt><a href=""><img src="/resources/images/images/21151da3bdefc6d9a7120c991fe59800.jpg_small.jpg" /></a></dt>-->
                <!--<dd class="title"><a href="">OCIAIZO春装水洗做旧短外套复古磨白短款牛仔外套春01C1417</a></dd>-->
                <!--<dd class="content">-->
                <!--<span class="goods_jiage">￥<strong>249.00</strong></span>-->
                <!--<span class="goods_chengjiao">最近成交5笔</span>-->
                <!--</dd>-->
                <!--</dl>-->
                <!--</li>-->

                <!--<li>-->
                <!--<dl>-->
                <!--<dt><a href=""><img src="/resources/images/images/21151da3bdefc6d9a7120c991fe59800.jpg_small.jpg" /></a></dt>-->
                <!--<dd class="title"><a href="">OCIAIZO春装水洗做旧短外套复古磨白短款牛仔外套春01C1417</a></dd>-->
                <!--<dd class="content">-->
                <!--<span class="goods_jiage">￥<strong>249.00</strong></span>-->
                <!--<span class="goods_chengjiao">最近成交5笔</span>-->
                <!--</dd>-->
                <!--</dl>-->
                <!--</li>-->

                <!--<li>-->
                <!--<dl>-->
                <!--<dt><a href=""><img src="/resources/images/images/21151da3bdefc6d9a7120c991fe59800.jpg_small.jpg" /></a></dt>-->
                <!--<dd class="title"><a href="">OCIAIZO春装水洗做旧短外套复古磨白短款牛仔外套春01C1417</a></dd>-->
                <!--<dd class="content">-->
                <!--<span class="goods_jiage">￥<strong>249.00</strong></span>-->
                <!--<span class="goods_chengjiao">最近成交5笔</span>-->
                <!--</dd>-->
                <!--</dl>-->
                <!--</li>-->

                <!--<li>-->
                <!--<dl>-->
                <!--<dt><a href=""><img src="/resources/images/images/21151da3bdefc6d9a7120c991fe59800.jpg_small.jpg" /></a></dt>-->
                <!--<dd class="title"><a href="">OCIAIZO春装水洗做旧短外套复古磨白短款牛仔外套春01C1417</a></dd>-->
                <!--<dd class="content">-->
                <!--<span class="goods_jiage">￥<strong>249.00</strong></span>-->
                <!--<span class="goods_chengjiao">最近成交5笔</span>-->
                <!--</dd>-->
                <!--</dl>-->
                <!--</li>-->

                <!--<li>-->
                <!--<dl>-->
                <!--<dt><a href=""><img src="/resources/images/images/21151da3bdefc6d9a7120c991fe59800.jpg_small.jpg" /></a></dt>-->
                <!--<dd class="title"><a href="">OCIAIZO春装水洗做旧短外套复古磨白短款牛仔外套春01C1417</a></dd>-->
                <!--<dd class="content">-->
                <!--<span class="goods_jiage">￥<strong>249.00</strong></span>-->
                <!--<span class="goods_chengjiao">最近成交5笔</span>-->
                <!--</dd>-->
                <!--</dl>-->
                <!--</li>-->

                <!--<li>-->
                <!--<dl>-->
                <!--<dt><a href=""><img src="/resources/images/images/21151da3bdefc6d9a7120c991fe59800.jpg_small.jpg" /></a></dt>-->
                <!--<dd class="title"><a href="">OCIAIZO春装水洗做旧短外套复古磨白短款牛仔外套春01C1417</a></dd>-->
                <!--<dd class="content">-->
                <!--<span class="goods_jiage">￥<strong>249.00</strong></span>-->
                <!--<span class="goods_chengjiao">最近成交5笔</span>-->
                <!--</dd>-->
                <!--</dl>-->
                <!--</li>-->

            </ul>
        </div>
        <div class="clear"></div>

        <div class="pagination">
            <ul id="pageList">

                <%--<li><span>Previous</span></li>--%>
                <li><span class="currentpage">1 of 1</span></li>
                <%--<li><span>Next</span></li>--%>

                <%--<li>--%>
                    <%--<input type="hidden" name="ty" value="1">--%>
                    <%--<input type="number" name="jump" min="1" max="3" value="1">--%>
                    <%--<input type="submit" value="jump">--%>
                <%--</li>--%>
            </ul>
        </div>

        <%--<div class="pagination">--%>
            <%--<ul id="pageList">--%>
                <%--&lt;%&ndash;<li><span onclick="search()">First page</span></li>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<li><span>Previous</span></li>&ndash;%&gt;--%>
                <%--<li><span class="currentpage" id="currentPage">1</span></li>--%>
                <%--<li><p>A total of 1 page</p></li>--%>
                <%--&lt;%&ndash;<li><span>Last page</span></li>&ndash;%&gt;--%>
            <%--</ul>--%>
        <%--</div>--%>
        <!-- 商品列表 End -->


    </div>
</div>
<!-- List Body End -->

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