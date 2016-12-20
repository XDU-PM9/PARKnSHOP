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
</head>
<body>
<%@include file="../customer/head.jsp"%>

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
                        <dd>
                            <span><input type="radio"> TV& Home Theater</span>
                            <span><input type="radio"> Computers & Tablets</span>
                            <span><input type="radio"> Cell Phones</span>
                            <span><input type="radio"> Cameras & Camcorders</span>
                            <span><input type="radio"> Audio</span>
                            <span><input type="radio"> Car Electronics & GPS</span>
                            <span><input type="radio"> Video, Games, Movies & Music</span>
                            <span><input type="radio"> Health, Fitness & Sports</span>
                            <span><input type="radio"> Home & Office</span>
                        </dd>
                    </dl>
                </div>
            </div>
            <!-- 左边商品分类 End -->

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
                        <li class="selected"><a title="默认排序" onclick="javascript:dropParam(['key','order'],'','array');" class="nobg" href="javascript:void(0)">Default</a></li>
                        <li><a title="点击按销量从高到低排序" onclick="javascript:replaceParam(['key','order'],['sales','desc'],'array');" href="javascript:void(0)">Sales</a></li>
                        <li><a title="点击按人气从高到低排序" onclick="javascript:replaceParam(['key','order'],['click','desc'],'array');" href="javascript:void(0)">Hot</a></li>
                        <li><a title="点击按信用从高到低排序" onclick="javascript:replaceParam(['key','order'],['credit','desc'],'array');" href="javascript:void(0)">Credit</a></li>
                        <li><a title="点击按价格从高到低排序" onclick="javascript:replaceParam(['key','order'],['price','desc'],'array');" href="javascript:void(0)">Price</a></li>
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
                <ul>
                    <li>
                        <dl>
                            <dt><a href=""><img src="/resources/images/images/21151da3bdefc6d9a7120c991fe59800.jpg_small.jpg" /></a></dt>
                            <dd class="title"><a href="">OCIAIZO春装水洗做旧短外套复古磨白短款牛仔外套春01C1417</a></dd>
                            <dd class="content">
                                <span class="goods_jiage">￥<strong>249.00</strong></span>
                                <span class="goods_chengjiao">最近成交5笔</span>
                            </dd>
                        </dl>
                    </li>

                    <li>
                        <dl>
                            <dt><a href=""><img src="/resources/images/images/21151da3bdefc6d9a7120c991fe59800.jpg_small.jpg" /></a></dt>
                            <dd class="title"><a href="">OCIAIZO春装水洗做旧短外套复古磨白短款牛仔外套春01C1417</a></dd>
                            <dd class="content">
                                <span class="goods_jiage">￥<strong>249.00</strong></span>
                                <span class="goods_chengjiao">最近成交5笔</span>
                            </dd>
                        </dl>
                    </li>

                </ul>
            </div>
            <div class="clear"></div>
            <div class="pagination">
                <ul><li><span>首页</span></li>
                    <li><span>上一页</span></li>
                    <li><span class="currentpage">1</span></li>
                    <li><span>下一页</span></li>
                    <li><span>末页</span></li>
                </ul>
            </div>
            <!-- 商品列表 End -->


        </div>
        </div>

    </div>
    <!-- 我的个人中心 End -->

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