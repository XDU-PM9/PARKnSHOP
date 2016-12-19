<%--
  Created by IntelliJ IDEA.
  User: H
  Date: 2016/12/17
  Time: 10:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="../../../resources/css/base.css" type="text/css" />
    <link rel="stylesheet" href="../../../resources/css/shop_common.css" type="text/css" />
    <link rel="stylesheet" href="../../../resources/css/shop_header.css" type="text/css" />
    <link rel="stylesheet" href="../../../resources/css/shop_manager.css" type="text/css" />
    <link rel="stylesheet" href="../../../resources/css/shop_shdz_835.css" type="text/css" />
    <script type="text/javascript" src="../../resources/js/jquery.js"></script>
    <script type="text/javascript" src="../../resources/js/topNav.js"></script>
    <script type="text/javascript" src="../../resources/js/focus.js"></script>
    <script type="text/javascript" src="../../resources/js/shop_home_tab.js"></script>
    <script>
        function search() {
            window.location.href=$('#shop_search').hasClass('current') ? '/searchShop?shopName=' : '/search?name='+$('#searchText').val();
        }
    </script>
</head>
<body>
<!-- Header  -wll-2013/03/24 -->
<div class="shop_hd">
    <!-- Header TopNav -->

    <%@include file="top.jsp"%>
    <div class="clear"></div>
    <!-- Header TopNav End -->

    <!-- TopHeader Center -->
    <!-- TopHeader Center -->
    <div class="shop_hd_header">
        <div class="shop_hd_header_logo"><h1 class="logo"><a href="/"><img id="logo"
                                                                           src="/resources/images/ico/banner.jpg"
                                                                           alt="ShopCZ"/></a><span>ShopCZ</span></h1>
        </div>
        <div class="shop_hd_header_search">
            <ul class="shop_hd_header_search_tab">
                <li id="search" class="current">Product</li>
                <li id="shop_search">Shop</li>
            </ul>
            <div class="clear"></div>
            <div class="search_form">
                <!--<form method="post" action="/search">-->
                <div class="search_formstyle">
                    <input type="text" class="search_form_text" id="searchText" name="search_content" placeholder="Search!"/>
                    <input type="button" class="search_form_sub" name="secrch_submit" value="" title="搜索"
                           onclick="search()"/>
                </div>
                <!--</form>-->
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
                    </li>
                    <!-- 单个菜单项 End -->
                    <li id="cat_2" class="">
                        <h3><a href="" title="鞋包配饰">Computers & Tablets</a></h3>
                    </li>

                    <li id="cat_3" class="">
                        <h3><a href="" title="美容美妆">Cell Phones</a></h3>
                    </li>

                    <li id="cat_4" class="">
                        <h3><a href="" title="美容美妆">Cameras & Camcorders</a></h3>
                    </li>

                    <li id="cat_5" class="">
                        <h3><a href="" title="美容美妆">Audio</a></h3>
                    </li>

                    <li id="cat_6" class="">
                        <h3><a href="" title="美容美妆">Car Electronics & GPS</a></h3>
                    </li>
                    <li id="cat_7" class="">
                        <h3><a href="" title="美容美妆">Video, Games, Movies</a></h3>
                    </li>
                    <li id="cat_8" class="">
                        <h3><a href="" title="美容美妆">Health, Fitness & Sports</a></h3>
                    </li>
                    <li id="cat_8" class="">
                        <h3><a href="" title="美容美妆">Home & Office</a></h3>
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

</body>
</html>
