<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
    <title>PARKnSHOP</title>
    <link rel="stylesheet" href="../../resources/css/base.css" type="text/css"/>
    <link rel="stylesheet" href="../../resources/css/shop_common.css" type="text/css"/>
    <link rel="stylesheet" href="../../resources/css/shop_header.css" type="text/css"/>
    <link rel="stylesheet" href="../../resources/css/shop_home.css" type="text/css"/>
    <script type="text/javascript" src="../../resources/js/jquery.js"></script>
    <script type="text/javascript" src="../../resources/js/topNav.js"></script>
    <script type="text/javascript" src="../../resources/js/focus.js"></script>
    <script type="text/javascript" src="../../resources/js/shop_home_tab.js"></script>
</head>
<body>
<%@include file="customer/head.jsp"%>

<!-- Body -wll-2013/03/24 -->
<div class="shop_bd clearfix">
    <!-- 第一块区域  -->
    <div class="shop_bd_top clearfix">
         <div class="shop_bd_top_center">
            <!-- 图片切换  begin  -->
            <div class="xifan_sub_box">
                <div id="p-select" class="sub_nav">
                    <div class="sub_no" id="xifan_bd1lfsj">
                        <ul></ul>
                    </div>
                </div>
                <div id="xifan_bd1lfimg">
                    <div>
                        <dl class=""></dl>
                        <dl class="">
                            <dt><a href="#" title="" target="_blank"><img
                                    src="../../resources/images/index-example/1.jpg"
                                    alt="New Goods"></a></dt>
                            <dd><h2><a href="#" target="_blank">New Goods</a></h2></dd>
                        </dl>
                        <dl class="">
                            <dt><a href="#" title="" target="_blank"><img
                                    src="../../resources/images/index-example/2.jpg"
                                    alt="New Goods"></a></dt>
                            <dd><h2><a href="#" target="_blank">New Goods</a></h2></dd>
                        </dl>
                        <dl class="">
                            <dt><a href="#" title="" target="_blank"><img
                                    src="../../resources/images/index-example/3.jpg"
                                    alt="New Goods"></a></dt>
                            <dd><h2><a href="#" target="_blank">New Goods</a></h2></dd>
                        </dl>
                        <dl class="">
                            <dt><a href="#" title="" target="_blank"><img
                                    src="../../resources/images/index-example/4.jpg"
                                    alt="New Goods"></a></dt>
                            <dd><h2><a href="#" target="_blank">New Goods</a></h2></dd>
                        </dl>
                    </div>
                </div>
            </div>
            <script type="text/javascript">movec();</script>
            <!-- 图片切换  end -->
            <div class="clear"></div>
         </div>

    </div>
    <div class="clear"></div>
    <!-- 第一块区域 End -->

    <!-- 第二块区域 -->
    <div class="shop_bd_2 clearfix">
        <!-- 商品推荐 -->
        <div class="shop_bd_tuijian">
            <ul class="tuijian_tabs">
                <li class="hover" onmouseover="easytabs('1', '1');" onfocus="easytabs('1', '1');"
                    onclick="return false;" id="tuijian_content_btn_1"><a href="javascript:void(0);">Hot Goods</a></li>
                <li onmouseover="easytabs('1', '2');" onfocus="easytabs('1', '2');" onclick="return false;"
                    id="tuijian_content_btn_2"><a href="javascript:void(0);">Hot Shop</a></li>
                <li style="visibility: hidden;" onmouseover="easytabs('1', '3');" onfocus="easytabs('1', '3');" onclick="return false;"
                    id="tuijian_content_btn_3"><a href="javascript:void(0);">New</a></li>
            </ul>
            <div class="tuijian_content">

                <!--商品推荐:列表-->
                <div id="tuijian_content_1" class="tuijian_shangpin" style="display: block;">
                    <ul>
                        <li>
                            <dl>
                                <dt><a href=""><img
                                        src="../../resources/images/images/365_7d5e08127b8d6799209674ecffbfc624.jpg_small.jpg"/></a>
                                </dt>
                                <dd><a href="">Hot Goods</a></dd>
                                <dd> price：<em>HK$0.00</em></dd>
                            </dl>
                        </li>
                        <li>
                            <dl>
                                <dt><a href=""><img
                                        src="../../resources/images/images/365_7d5e08127b8d6799209674ecffbfc624.jpg_small.jpg"/></a>
                                </dt>
                                <dd><a href="">Hot Goods</a></dd>
                                <dd> price：<em>HK$0.00</em></dd>
                            </dl>
                        </li>

                        <li>
                            <dl>
                                <dt><a href=""><img
                                        src="../../resources/images/images/365_7d5e08127b8d6799209674ecffbfc624.jpg_small.jpg"/></a>
                                </dt>
                                <dd><a href="">Hot Goods</a></dd>
                                <dd> price：<em>HK$0.00</em></dd>
                            </dl>
                        </li>

                        <li>
                            <dl>
                                <dt><a href=""><img
                                        src="../../resources/images/images/365_7d5e08127b8d6799209674ecffbfc624.jpg_small.jpg"/></a>
                                </dt>
                                <dd><a href="">Hot Goods</a></dd>
                                <dd> price：<em>HK$0.00</em></dd>
                            </dl>
                        </li>
                        <li>
                        <dl>
                            <dt><a href=""><img
                                    src="../../resources/images/images/365_7d5e08127b8d6799209674ecffbfc624.jpg_small.jpg"/></a>
                            </dt>
                            <dd><a href="">Hot Goods</a></dd>
                            <dd> price：<em>HK$0.00</em></dd>
                        </dl>
                    </li>

                        <li>
                            <dl>
                                <dt><a href=""><img
                                        src="../../resources/images/images/365_7d5e08127b8d6799209674ecffbfc624.jpg_small.jpg"/></a>
                                </dt>
                                <dd><a href="">Hot Goods</a></dd>
                                <dd> price：<em>HK$0.00</em></dd>
                            </dl>
                        </li>

                        <li>
                            <dl>
                                <dt><a href=""><img
                                        src="../../resources/images/images/365_7d5e08127b8d6799209674ecffbfc624.jpg_small.jpg"/></a>
                                </dt>
                                <dd><a href="">Hot Goods</a></dd>
                                <dd> price：<em>HK$0.00</em></dd>
                            </dl>
                        </li>
                        <li>
                        <dl>
                            <dt><a href=""><img
                                    src="../../resources/images/images/365_7d5e08127b8d6799209674ecffbfc624.jpg_small.jpg"/></a>
                            </dt>
                            <dd><a href="">Hot Goods</a></dd>
                            <dd> price：<em>HK$0.00</em></dd>
                        </dl>
                    </li>

                        <li>
                            <dl>
                                <dt><a href=""><img
                                        src="../../resources/images/images/365_7d5e08127b8d6799209674ecffbfc624.jpg_small.jpg"/></a>
                                </dt>
                                <dd><a href="">Hot Goods</a></dd>
                                <dd> price：<em>HK$0.00</em></dd>
                            </dl>
                        </li>

                        <li>
                            <dl>
                                <dt><a href=""><img
                                        src="../../resources/images/images/365_7d5e08127b8d6799209674ecffbfc624.jpg_small.jpg"/></a>
                                </dt>
                                <dd><a href="">Hot Goods</a></dd>
                                <dd> price：<em>HK$0.00</em></dd>
                            </dl>
                        </li>

                    </ul>
                </div>
               <!--商品推荐:列表-->


                <!--店铺推荐:列表-->
                <div id="tuijian_content_2" class="tuijian_shangpin">
                    <ul>
                        <li>
                            <dl>
                                <dt><a href=""><img
                                        src="../../resources/images/images/365_7d5e08127b8d6799209674ecffbfc624.jpg_small.jpg"/></a>
                                </dt>
                                <dd><a href="">Hot Shop</a></dd>
                                <dd> price：<em>HK$0.00</em></dd>
                            </dl>
                        </li>
                        <li>
                            <dl>
                                <dt><a href=""><img
                                        src="../../resources/images/images/365_7d5e08127b8d6799209674ecffbfc624.jpg_small.jpg"/></a>
                                </dt>
                                <dd><a href="">Hot Shop</a></dd>
                                <dd> price：<em>HK$0.00</em></dd>
                            </dl>
                        </li>
                        <li>
                            <dl>
                                <dt><a href=""><img
                                        src="../../resources/images/images/365_7d5e08127b8d6799209674ecffbfc624.jpg_small.jpg"/></a>
                                </dt>
                                <dd><a href="">Hot Shop</a></dd>
                                <dd> price：<em>HK$0.00</em></dd>
                            </dl>
                        </li>
                        <li>
                            <dl>
                                <dt><a href=""><img
                                        src="../../resources/images/images/365_7d5e08127b8d6799209674ecffbfc624.jpg_small.jpg"/></a>
                                </dt>
                                <dd><a href="">Hot Shop</a></dd>
                                <dd> price：<em>HK$0.00</em></dd>
                            </dl>
                        </li>
                        <li>
                            <dl>
                                <dt><a href=""><img
                                        src="../../resources/images/images/365_7d5e08127b8d6799209674ecffbfc624.jpg_small.jpg"/></a>
                                </dt>
                                <dd><a href="">Hot Shop</a></dd>
                                <dd> price：<em>HK$0.00</em></dd>
                            </dl>
                        </li>
                        <li>
                            <dl>
                                <dt><a href=""><img
                                        src="../../resources/images/images/365_7d5e08127b8d6799209674ecffbfc624.jpg_small.jpg"/></a>
                                </dt>
                                <dd><a href="">Hot Shop</a></dd>
                                <dd> price：<em>HK$0.00</em></dd>
                            </dl>
                        </li>
                        <li>
                            <dl>
                                <dt><a href=""><img
                                        src="../../resources/images/images/365_7d5e08127b8d6799209674ecffbfc624.jpg_small.jpg"/></a>
                                </dt>
                                <dd><a href="">Hot Shop</a></dd>
                                <dd> price：<em>HK$0.00</em></dd>
                            </dl>
                        </li>

                        <li>
                            <dl>
                                <dt><a href=""><img
                                        src="../../resources/images/images/365_7d5e08127b8d6799209674ecffbfc624.jpg_small.jpg"/></a>
                                </dt>
                                <dd><a href="">Hot Shop</a></dd>
                                <dd> price：<em>HK$0.00</em></dd>
                            </dl>
                        </li>
                        <li>
                            <dl>
                                <dt><a href=""><img
                                        src="../../resources/images/images/365_7d5e08127b8d6799209674ecffbfc624.jpg_small.jpg"/></a>
                                </dt>
                                <dd><a href="">Hot Shop</a></dd>
                                <dd> price：<em>HK$0.00</em></dd>
                            </dl>
                        </li>

                        <li>
                            <dl>
                                <dt><a href=""><img
                                        src="../../resources/images/images/365_7d5e08127b8d6799209674ecffbfc624.jpg_small.jpg"/></a>
                                </dt>
                                <dd><a href="">Hot Shop</a></dd>
                                <dd> price：<em>HK$0.00</em></dd>
                            </dl>
                        </li>

                    </ul>
                </div>
                <!--店铺推荐:列表-->



                <div id="tuijian_content_3" class="tuijian_shangpin tuijian_content">
                    <ul>
                        <li>
                            <dl>
                                <dt><a href=""><img
                                        src="../../resources/images/images/365_7d5e08127b8d6799209674ecffbfc624.jpg_small.jpg"/></a>
                                </dt>
                                <dd><a href="">3333333全棉布艺双人沙发垫沙发巾飘窗垫素雅黄花</a></dd>
                                <dd> 商城价：<em>256.00</em>元</dd>
                            </dl>
                        </li>
                        <li>
                            <dl>
                                <dt><a href=""><img
                                        src="../../resources/images/images/365_7d5e08127b8d6799209674ecffbfc624.jpg_small.jpg"/></a>
                                </dt>
                                <dd><a href="">外贸田园绗缝全棉布艺双人沙发垫沙发巾飘窗垫素雅黄花</a></dd>
                                <dd> 商城价：<em>256.00</em>元</dd>
                            </dl>
                        </li>
                        <li>
                            <dl>
                                <dt><a href=""><img
                                        src="../../resources/images/images/365_7d5e08127b8d6799209674ecffbfc624.jpg_small.jpg"/></a>
                                </dt>
                                <dd><a href="">3333333全棉布艺双人沙发垫沙发巾飘窗垫素雅黄花</a></dd>
                                <dd> 商城价：<em>256.00</em>元</dd>
                            </dl>
                        </li>
                        <li>
                            <dl>
                                <dt><a href=""><img
                                        src="../../resources/images/images/365_7d5e08127b8d6799209674ecffbfc624.jpg_small.jpg"/></a>
                                </dt>
                                <dd><a href="">外贸田园绗缝全棉布艺双人沙发垫沙发巾飘窗垫素雅黄花</a></dd>
                                <dd> 商城价：<em>256.00</em>元</dd>
                            </dl>
                        </li>
                        <li>
                            <dl>
                                <dt><a href=""><img
                                        src="../../resources/images/images/365_7d5e08127b8d6799209674ecffbfc624.jpg_small.jpg"/></a>
                                </dt>
                                <dd><a href="">3333333全棉布艺双人沙发垫沙发巾飘窗垫素雅黄花</a></dd>
                                <dd> 商城价：<em>256.00</em>元</dd>
                            </dl>
                        </li>
                        <li>
                            <dl>
                                <dt><a href=""><img
                                        src="../../resources/images/images/365_7d5e08127b8d6799209674ecffbfc624.jpg_small.jpg"/></a>
                                </dt>
                                <dd><a href="">外贸田园绗缝全棉布艺双人沙发垫沙发巾飘窗垫素雅黄花</a></dd>
                                <dd> 商城价：<em>256.00</em>元</dd>
                            </dl>
                        </li>
                        <li>
                            <dl>
                                <dt><a href=""><img
                                        src="../../resources/images/images/365_7d5e08127b8d6799209674ecffbfc624.jpg_small.jpg"/></a>
                                </dt>
                                <dd><a href="">外贸田园绗缝全棉布艺双人沙发垫沙发巾飘窗垫素雅黄花</a></dd>
                                <dd> 商城价：<em>256.00</em>元</dd>
                            </dl>
                        </li>

                        <li>
                            <dl>
                                <dt><a href=""><img
                                        src="../../resources/images/images/365_7d5e08127b8d6799209674ecffbfc624.jpg_small.jpg"/></a>
                                </dt>
                                <dd><a href="">外贸田园绗缝全棉布艺双人沙发垫沙发巾飘窗垫素雅黄花</a></dd>
                                <dd> 商城价：<em>256.00</em>元</dd>
                            </dl>
                        </li>
                        <li>
                            <dl>
                                <dt><a href=""><img
                                        src="../../resources/images/images/365_7d5e08127b8d6799209674ecffbfc624.jpg_small.jpg"/></a>
                                </dt>
                                <dd><a href="">外贸田园绗缝全棉布艺双人沙发垫沙发巾飘窗垫素雅黄花</a></dd>
                                <dd> 商城价：<em>256.00</em>元</dd>
                            </dl>
                        </li>

                        <li>
                            <dl>
                                <dt><a href=""><img
                                        src="../../resources/images/images/365_7d5e08127b8d6799209674ecffbfc624.jpg_small.jpg"/></a>
                                </dt>
                                <dd><a href="">外贸田园绗缝全棉布艺双人沙发垫沙发巾飘窗垫素雅黄花</a></dd>
                                <dd> 商城价：<em>256.00</em>元</dd>
                            </dl>
                        </li>

                    </ul>
                </div>
            </div>

        </div>
        <!-- 商品推荐 End -->


    </div>
    <div class="clear"></div>
    <!-- 第二块区域 End -->


    <div class="clear"></div>
</div>
<!-- Body End -->

<!-- Footer - wll - 2013/3/24 -->
<div class="clear"></div>
<!-- Footer End -->
</body>
</html>