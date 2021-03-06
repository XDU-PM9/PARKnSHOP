<%--
  Created by IntelliJ IDEA.
  User: H
  Date: 2016/12/19
  Time: 19:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
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
    <script>
        function search() {
            window.location.href=$('#shop_search').hasClass('current') ? '/searchShop?shopName=' : '/search?name='+$('#searchText').val();
        }
    </script>
</head>
<body>
<%@include file="customer/head.jsp"%>

<!-- Body -wll-2013/03/24 -->
<div class="shop_bd clearfix">
    <!-- 第一块区域  -->
    <div class="shop_bd_top clearfix">
        <div class="shop_bd_top_left"></div>
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
                            <dt><a href="http://www.zztuku.com" title="" target="_blank"><img
                                    src="../../resources/images/images/e2dfe57add8fff66ed0964b1effd39b9.jpg"
                                    alt="2011城市主题公园亲子游"></a></dt>
                            <dd><h2><a href="http://www.zztuku.com" target="_blank">2011城市主题公园亲子游</a></h2></dd>
                        </dl>
                        <dl class="">
                            <dt><a href="http://www.zztuku.com" title="" target="_blank"><img
                                    src="../../resources/images/images/e50b5d398e3b890f08e14defbc71a94d.jpg"
                                    alt="潜入城市周边清幽之地"></a></dt>
                            <dd><h2><a href="http://www.zztuku.com" target="_blank">潜入城市周边清幽之地</a></h2></dd>
                        </dl>
                        <dl class="">
                            <dt><a href="http://www.zztuku.com" title="" target="_blank"><img
                                    src="../../resources/images/images/196b173f15685a2019ab3396cd1851a4.jpg"
                                    alt="盘点中国最美雪山"></a></dt>
                            <dd><h2><a href="http://www.zztuku.com" target="_blank">盘点中国最美雪山</a></h2></dd>
                        </dl>
                        <dl class="">
                            <dt><a href="http://www.zztuku.com" title="" target="_blank"><img
                                    src="../../resources/images/images/e81345cbc3d8a7e11f9a0e09df68221d.jpg"
                                    alt="2011西安世园会攻略"></a></dt>
                            <dd><h2><a href="http://www.zztuku.com" target="_blank">2011西安世园会攻略</a></h2></dd>
                        </dl>
                        <dl class="">
                            <dt><a href="http://www.zztuku.com" title="" target="_blank"><img
                                    src="../../resources/images/images/65662b58848da87812ba371c7ff6c1ad.jpg"
                                    alt="五月乐享懒人天堂塞班岛"></a></dt>
                            <dd><h2><a href="http://www.zztuku.com" target="_blank">五月乐享懒人天堂塞班岛</a></h2></dd>
                        </dl>

                        <dl class="">
                            <dt><a href="http://www.zztuku.com" title="" target="_blank"><img
                                    src="../../resources/images/images/e50b5d398e3b890f08e14defbc71a94d.jpg"
                                    alt="潜入城市周边清幽之地"></a></dt>
                            <dd><h2><a href="http://www.zztuku.com" target="_blank">潜入城市周边清幽之地</a></h2></dd>
                        </dl>
                        <dl class="">
                            <dt><a href="http://www.zztuku.com" title="" target="_blank"><img
                                    src="../../resources/images/images/196b173f15685a2019ab3396cd1851a4.jpg"
                                    alt="盘点中国最美雪山"></a></dt>
                            <dd><h2><a href="http://www.zztuku.com" target="_blank">盘点中国最美雪山</a></h2></dd>
                        </dl>
                        <dl class="">
                            <dt><a href="http://www.zztuku.com" title="" target="_blank"><img
                                    src="../../resources/images/images/e81345cbc3d8a7e11f9a0e09df68221d.jpg"
                                    alt="2011西安世园会攻略"></a></dt>
                            <dd><h2><a href="http://www.zztuku.com" target="_blank">2011西安世园会攻略</a></h2></dd>
                        </dl>
                        <dl class="">
                            <dt><a href="http://www.zztuku.com" title="" target="_blank"><img
                                    src="../../resources/images/images/65662b58848da87812ba371c7ff6c1ad.jpg"
                                    alt="五月乐享懒人天堂塞班岛"></a></dt>
                            <dd><h2><a href="http://www.zztuku.com" target="_blank">五月乐享懒人天堂塞班岛</a></h2></dd>
                        </dl>
                    </div>
                </div>
            </div>
            <script type="text/javascript">movec();</script>
            <!-- 图片切换  end -->
            <div class="clear"></div>
            <div class="shop_bd_top_center_hot"><img src="../../resources/images/images/index.guanggao.png"/></div>
        </div>

        <!-- 右侧 -->
        <div class="shop_bd_top_right clearfix">
            <div class="shop_bd_top_right_quickLink">
                <a href="" class="login" title="会员登录"><i></i>会员登录</a>
                <a href="" class="register" title="免费注册"><i></i>免费注册</a>
                <a href="" class="join" title="商家开店"><i></i>帮助中心</a>
            </div>

            <div class="shop_bd_top_right-style1 nc-home-news">
                <ul class="tabs-nav">
                    <li><a href="javascript:void(0);" class="hover">商城广告</a></li>
                    <li><a href="javascript:void(0);">关于我们</a></li>
                </ul>
                <div class="clear"></div>
                <div class="tabs-panel">
                    <ul class="list-style01">
                        <li><a title="如何申请开店" href="article-15.html">如何申请开店</a><span>(2011-01-11)</span></li>
                        <li><a title="商城商品推荐" href="article-14.html">商城商品推荐</a><span>(2011-01-11)</span></li>
                        <li><a title="如何发货" href="article-13.html">如何发货</a><span>(2011-01-11)</span></li>
                        <li><a title="查看售出商品" href="article-12.html">查看售出商品</a><span>(2011-01-11)</span></li>
                        <li><a title="如何管理店铺" href="article-11.html">如何管理店铺</a><span>(2011-01-11)</span></li>
                        <li><a title="如何申请开店" href="article-15.html">如何申请开店</a><span>(2011-01-11)</span></li>
                        <li><a title="商城商品推荐" href="article-14.html">商城商品推荐</a><span>(2011-01-11)</span></li>
                        <li><a title="如何发货" href="article-13.html">如何发货</a><span>(2011-01-11)</span></li>
                        <li><a title="查看售出商品" href="article-12.html">查看售出商品</a><span>(2011-01-11)</span></li>
                        <li><a title="如何管理店铺" href="article-11.html">如何管理店铺</a><span>(2011-01-11)</span></li>


                    </ul>
                </div>
            </div>


        </div>
        <!-- 右侧 End -->
    </div>
    <div class="clear"></div>
    <!-- 第一块区域 End -->

    <!-- 第二块区域 -->
    <div class="shop_bd_2 clearfix">
        <!-- 特别推荐 -->
        <div class="shop_bd_tuijian">
            <ul class="tuijian_tabs">
                <li class="hover" onmouseover="easytabs('1', '1');" onfocus="easytabs('1', '1');"
                    onclick="return false;" id="tuijian_content_btn_1"><a href="javascript:void(0);">特别推荐</a></li>
                <li onmouseover="easytabs('1', '2');" onfocus="easytabs('1', '2');" onclick="return false;"
                    id="tuijian_content_btn_2"><a href="javascript:void(0);">热门商品</a></li>
                <li onmouseover="easytabs('1', '3');" onfocus="easytabs('1', '3');" onclick="return false;"
                    id="tuijian_content_btn_3"><a href="javascript:void(0);">新品上架</a></li>
            </ul>
            <div class="tuijian_content">
                <div id="tuijian_content_1" class="tuijian_shangpin" style="display: block;">
                    <ul>
                        <li>
                            <dl>
                                <dt><a href=""><img
                                        src="../../resources/images/images/365_7d5e08127b8d6799209674ecffbfc624.jpg_small.jpg"/></a>
                                </dt>
                                <dd><a href="">11111111棉布艺双人沙发垫沙发巾飘窗垫素雅黄花</a></dd>
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

                <div id="tuijian_content_2" class="tuijian_shangpin">
                    <ul>
                        <li>
                            <dl>
                                <dt><a href=""><img
                                        src="../../resources/images/images/365_7d5e08127b8d6799209674ecffbfc624.jpg_small.jpg"/></a>
                                </dt>
                                <dd><a href="">2222222棉布艺双人沙发垫沙发巾飘窗垫素雅黄花</a></dd>
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
        <!-- 特别推荐 End -->

        <!-- 首发 -->
        <div class="shop_bd_shoufa"><img src="../../resources/images/images/shoufa.jpg"/></div>
        <!-- 首发 End -->

    </div>
    <div class="clear"></div>
    <!-- 第二块区域 End -->

    <div class="faq">
        <dl>
            <dt>帮助中心</dt>
            <dd><a href=""><span>积分兑换说明</span></a></dd>
            <dd><a href=""><span>积分明细</span></a></dd>
            <dd><a href=""><span>查看已购买商</span></a></dd>
            <dd><a href=""><span>我要买</span></a></dd>
            <dd><a href=""><span>忘记密码</span></a></dd>
        </dl>

        <dl>
            <dt>店主之家</dt>
            <dd><a href=""><span>如何申请开店</span></a></dd>
            <dd><a href=""><span>商城商品推荐</span></a></dd>
            <dd><a href=""><span>如何发货</span></a></dd>
            <dd><a href=""><span>查看已售商品</span></a></dd>
            <dd><a href=""><span>如何管理店铺</span></a></dd>
        </dl>

        <dl>
            <dt>支付方式</dt>
            <dd><a href=""><span>公司转账</span></a></dd>
            <dd><a href=""><span>邮局汇款</span></a></dd>
            <dd><a href=""><span>分期付款</span></a></dd>
            <dd><a href=""><span>在线支付</span></a></dd>
            <dd><a href=""><span>如何注册支付</span></a></dd>
        </dl>

        <dl>
            <dt>售后服务</dt>
            <dd><a href=""><span>退款申请</span></a></dd>
            <dd><a href=""><span>返修/退换货</span></a></dd>
            <dd><a href=""><span>退换货流程</span></a></dd>
            <dd><a href=""><span>退换货政策</span></a></dd>
            <dd><a href=""><span>联系卖家</span></a></dd>
        </dl>

        <dl>
            <dt>客服中心</dt>
            <dd><a href=""><span>修改收货地址</span></a></dd>
            <dd><a href=""><span>商品发布</span></a></dd>
            <dd><a href=""><span>会员修改个人</span></a></dd>
            <dd><a href=""><span>会员修改密码</span></a></dd>

        </dl>

        <dl>
            <dt>关于我们</dt>
            <dd><a href=""><span>合作及洽谈</span></a></dd>
            <dd><a href=""><span>招聘英才</span></a></dd>
            <dd><a href=""><span>联系我们</span></a></dd>
            <dd><a href=""><span>关于Shop</span></a></dd>
        </dl>


    </div>
    <div class="clear"></div>
</div>
<!-- Body End -->

<!-- Footer - wll - 2013/3/24 -->
<div class="clear"></div>
<div class="shop_footer">
    <div class="shop_footer_link">
        <p>
            <a href="">首页</a>|
            <a href="">招聘英才</a>|
            <a href="">广告合作</a>|
            <a href="">关于ShopCZ</a>|
            <a href="">关于我们</a>
        </p>
    </div>
    <div class="shop_footer_copy">
        <p>Copyright 2004-2013 itcast Inc.,All rights reserved.</p>
    </div>
</div>

</body>
</html>
