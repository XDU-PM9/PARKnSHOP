<%@ page import="com.parknshop.bean.AdvertisementDbBean" %>
<%@ page import="com.parknshop.service.IListBean" %>
<%@ page import="com.parknshop.controller.MainController" %>
<%@ page import="java.util.List" %>
<%@ page import="com.parknshop.entity.GoodsEntity" %>
<%@ page import="com.parknshop.bean.GoodsDbBean" %>
<%@ page import="com.parknshop.utils.Log" %>
<%@ page import="com.parknshop.entity.ShopEntity" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
    <title>PARKnSHOP</title>
    <link rel="stylesheet" href="/resources/css/base.css" type="text/css"/>
    <link rel="stylesheet" href="/resources/css/shop_common.css" type="text/css"/>
    <link rel="stylesheet" href="/resources/css/shop_header.css" type="text/css"/>
    <link rel="stylesheet" href="/resources/css/shop_home.css" type="text/css"/>
    <script type="text/javascript" src="/resources/js/jquery.js"></script>
    <script type="text/javascript" src="/resources/js/topNav.js"></script>
    <script type="text/javascript" src="/resources/js/focus.js"></script>
    <script type="text/javascript" src="/resources/js/shop_home_tab.js"></script>
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

                        <%--加载浏览量最高的商品--%>
                        <%
                            IListBean<GoodsDbBean> hot = (IListBean<GoodsDbBean>) request.getAttribute(MainController.HOT);
                            if (hot != null && hot.getShopList()!=null){
                                List<GoodsDbBean> hotGoods = hot.getShopList();
                                for (GoodsDbBean item : hotGoods){
                                    out.println("<dl align=\"center\" class=\"\">");
                                    out.println("<dt>");
                                    out.println("<a href=\"/goods/detail?goodsId="+item.getGoodsId()+"\" title=\""+item.getGoodsName()+"\" target=\"_blank\">");
                                    out.println("<img");
                                    if(item.getPicturePath().size() > 0) {
                                        out.println("src=\"" + item.getPicturePath().get(0).getAddress() + "\"");
                                    }else{
                                        //没有图片的情况
                                        out.println("src=\"\"");
                                    }
                                    out.println("alt=\"New Goods\">");
                                    out.println(" </a>");
                                    out.println("</dt>");
                                    out.println("<dd>");
                                    out.println("<h2>");
                                    out.println("<a href=\"/goods/detail?goodsId="+item.getGoodsId()+"\" target=\"_blank\">"+item.getGoodsName()+"</a>");
                                    out.println("</h2>");
                                    out.println("</dd>");
                                    out.println("</dl>");
                                }
                            }
                        %>

                        <%--<dl class="">--%>
                            <%--<dt>--%>
                                <%--<a href="#" title="" target="_blank">--%>
                                <%--<img--%>
                                    <%--src="../../resources/images/index-example/1.jpg"--%>
                                    <%--alt="New Goods">--%>
                                <%--</a>--%>
                            <%--</dt>--%>
                            <%--<dd>--%>
                                <%--<h2>--%>
                                    <%--<a href="#" target="_blank">New Goods</a>--%>
                                <%--</h2>--%>
                            <%--</dd>--%>
                        <%--</dl>--%>

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
                    <ul class="goodsList">

                        <%
                            IListBean<AdvertisementDbBean> adGoods = (IListBean<AdvertisementDbBean>) request.getAttribute(MainController.AD_GOODS);
                            if (adGoods!=null && adGoods.getShopList()!=null){
                                List<AdvertisementDbBean> goodsList = adGoods.getShopList();

                                for (AdvertisementDbBean item : goodsList){
                                    GoodsDbBean goods = item.getGoodsEntity();
                                    if (goods!=null) {
                                        out.println("<li>");
                                        out.println("<dl>");
                                        out.println("<dt>");
                                        out.println("<a href=\"/goods/detail?goodsId=" + goods.getGoodsId() + "\">");
                                        out.println("<img class='imgContent' src=\"" + goods.getPicturePath().get(0).getAddress() + "\"/>");
                                        out.println("</a>");
                                        out.println("</dt>");
                                        out.println("<dd>");
                                        out.println("<a href=\"/goods/detail?goodsId=" + goods.getGoodsId() + "\">" + goods.getGoodsName() + "</a>");
                                        out.println("</dd>");
                                        out.println("<dd> price：<em>HK$" + goods.getPrice() + "</em>");
                                        out.println("</dd>");
                                        out.println("</dl>");
                                        out.println("</li>");
                                    }
                                }
                            }
                        %>
                        <%--<li>--%>
                            <%--<dl>--%>
                                <%--<dt>--%>
                                    <%--<a href="">--%>
                                        <%--<img class="imgContent" src="../../resources/images/images/365_7d5e08127b8d6799209674ecffbfc624.jpg_small.jpg"/>--%>
                                    <%--</a>--%>
                                <%--</dt>--%>
                                <%--<dd>--%>
                                    <%--<a href="">Hot Goods</a>--%>
                                <%--</dd>--%>
                                <%--<dd> price：<em>HK$0.00</em>--%>
                                <%--</dd>--%>
                            <%--</dl>--%>
                        <%--</li>--%>
                    </ul>
                </div>
               <!--商品推荐:列表-->


                <!--店铺推荐:列表-->
                <div id="tuijian_content_2" class="tuijian_shangpin">
                    <ul class="goodsList">
                        <%
                            IListBean<AdvertisementDbBean> adShop = (IListBean<AdvertisementDbBean>) request.getAttribute(MainController.AD_SHOP);
                            if (adShop!=null && adShop.getShopList()!=null){
                                List<AdvertisementDbBean> shopList = adShop.getShopList();
                                for (AdvertisementDbBean item :  shopList){
                                    ShopEntity shop  = item.getShopEntity();
                                    if (shop!=null) {
                                        out.println("<li>");
                                        out.println("<dl>");
                                        out.println("<dt>");
                                        out.println("<a href=\"/shop/detail?shopId=" + shop.getShopId() + "\">");
                                        out.println("<img class='imgContent' src=\"" + shop.getLogo() + "\"/>");
                                        out.println("</a>");
                                        out.println("</dt>");
                                        out.println("<dd><a href=\"/shop/detail?shopId=" + shop.getShopId() + "\">" + shop.getShopName() + "</a></dd>");
                                        out.println("</dl>");
                                        out.println("</li>");
                                    }
                                }
                            }
                        %>
                        <%--<li>--%>
                            <%--<dl>--%>
                                <%--<dt>--%>
                                    <%--<a href="">--%>
                                    <%--<img class="imgContent" src="../../resources/images/images/365_7d5e08127b8d6799209674ecffbfc624.jpg_small.jpg"/>--%>
                                    <%--</a>--%>
                                <%--</dt>--%>
                                <%--<dd><a href="">Hot Shop</a></dd>--%>
                                <%--&lt;%&ndash;<dd> price：<em>HK$0.00</em></dd>&ndash;%&gt;--%>
                            <%--</dl>--%>
                        <%--</li>--%>

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