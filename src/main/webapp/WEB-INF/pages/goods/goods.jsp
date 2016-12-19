<%@ page import="com.parknshop.bean.GoodsDbBean" %>
<%@ page import="com.parknshop.controller.GoodsController" %>
<%@ page import="java.util.List" %>
<%@ page import="com.parknshop.entity.PhotoEntity" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.parknshop.utils.Log" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    GoodsDbBean goods = (GoodsDbBean) request.getAttribute(GoodsController.KEY_GOODS);
    List<PhotoEntity> photoList = goods.getPicturePath();
    List<String> photos = new ArrayList<>();
    for (PhotoEntity entity : photoList){
        photos.add(entity.getAddress());
    }
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
    <title>商品详细页面</title>
    <link rel="stylesheet" href="/resources/css/base.css" type="text/css" />
    <link rel="stylesheet" href="/resources/css/shop_common.css" type="text/css" />
    <link rel="stylesheet" href="/resources/css/shop_header.css" type="text/css" />
    <link rel="stylesheet" href="/resources/css/shop_list.css" type="text/css" />
    <link rel="stylesheet" href="/resources/css/shop_goods.css" type="text/css" />
    <script type="text/javascript" src="/resources/js/jquery.js" ></script>
    <script type="text/javascript" src="/resources/js/topNav.js" ></script>
    <script type="text/javascript" src="/resources/js/shop_goods.js" ></script>
</head>
<body>
 <%@include file="../customer/head.jsp"%>
<!-- Goods Body -->
<div class="shop_goods_bd clear">

    <!-- 商品展示 -->
    <div class="shop_goods_show">
        <div class="shop_goods_show_left">
            <!-- 京东商品展示 -->
            <link rel="stylesheet" href="/resources/css/shop_goodPic.css" type="text/css" />
            <script type="text/javascript" src="/resources/js/shop_goodPic_base.js"></script>
            <script type="text/javascript" src="/resources/js/lib.js"></script>
            <script type="text/javascript" src="/resources/js/163css.js"></script>
            <div id="preview">
                <div class=jqzoom id="spec-n1" onClick="window.open('/')"><IMG height="350" src="images/img04.jpg" jqimg="images/img04.jpg" width="350">
                </div>
                <div id="spec-n5">
                    <div class=control id="spec-left">
                        <img src="/resources/images/left.gif" />
                    </div>
                    <div id="spec-list">
                        <ul class="list-h">

                            <%--生成图片--%>
                            <%
                                for (String image : photos){
                                    out.println("<li><img src=\""+image+"\"> </li>");
                                }
                            %>
                        </ul>
                    </div>
                    <div class=control id="spec-right">
                        <img src="/resources/images/right.gif" />
                    </div>

                </div>
            </div>

            <SCRIPT type=text/javascript>
                $(function(){
                    $(".jqzoom").jqueryzoom({
                        xzoom:400,
                        yzoom:400,
                        offset:10,
                        position:"right",
                        preload:1,
                        lens:1
                    });
                    $("#spec-list").jdMarquee({
                        deriction:"left",
                        width:350,
                        height:56,
                        step:2,
                        speed:4,
                        delay:10,
                        control:true,
                        _front:"#spec-right",
                        _back:"#spec-left"
                    });
                    $("#spec-list img").bind("mouseover",function(){
                        var src=$(this).attr("src");
                        $("#spec-n1 img").eq(0).attr({
                            src:src.replace("\/n5\/","\/n1\/"),
                            jqimg:src.replace("\/n5\/","\/n0\/")
                        });
                        $(this).css({
                            "border":"2px solid #ff6600",
                            "padding":"1px"
                        });
                    }).bind("mouseout",function(){
                        $(this).css({
                            "border":"1px solid #ccc",
                            "padding":"2px"
                        });
                    });
                })
            </SCRIPT>
            <!-- 京东商品展示 End -->

        </div>
        <div class="shop_goods_show_right">
            <ul>
                <li>
                    <%--<strong style="font-size:14px; font-weight:bold;">联想 K900 3G手机（炫酷银）WCDMA/GSM</strong>--%>

                        <%--充填商品名字--%>
                        <strong style="font-size:14px; font-weight:bold;"><%out.print(goods.getGoodsName());%></strong>
                </li>
                <li>
                    <label>价格：</label>
                    <%--<span><strong>200.00</strong>元</span>--%>
                    <span><strong><%out.print(goods.getPrice());%></strong>元</span>
                </li>
                <li>
                    <label>运费：</label>
                    <span>卖家承担运费</span>
                </li>
                <li>
                    <label>累计售出：</label>
                    <span>99件</span>
                </li>
                <li>
                    <label>评价：</label>
                    <span>0条评论</span>
                </li>
                <li class="goods_num">
                    <label>购买数量：</label>
                    <span><a class="good_num_jian" id="good_num_jian" href="javascript:void(0);"></a><input type="text" value="1" id="good_nums" class="good_nums" /><a href="javascript:void(0);" id="good_num_jia" class="good_num_jia"></a>(当前库存<%out.print(goods.getInventory());%>件)</span>
                </li>
                <li style="padding:20px 0;">
                    <label>&nbsp;</label>
                    <span><a href="/addProduct?goodsId=<%out.print(goods.getGoodsId());%>&amount=1" class="goods_sub goods_sub_gou" >加入购物车</a></span>
                </li>
            </ul>
        </div>
    </div>
    <!-- 商品展示 End -->

    <!-- 商品详情 -->
    <script type="text/javascript" src="/resources/js/shop_goods_tab.js"></script>
    <div class="shop_goods_bd_xiangqing clearfix">
        <div class="shop_goods_bd_xiangqing_tab">
            <ul>
                <li id="xiangqing_tab_1" onmouseover="shop_goods_easytabs('1', '1');" onfocus="shop_goods_easytabs('1', '1');" onclick="return false;"><a href=""><span>商品详情</span></a></li>
                <li id="xiangqing_tab_2" onmouseover="shop_goods_easytabs('1', '2');" onfocus="shop_goods_easytabs('1', '2');" onclick="return false;"><a href=""><span>商品评论</span></a></li>
                <li id="xiangqing_tab_3" onmouseover="shop_goods_easytabs('1', '3');" onfocus="shop_goods_easytabs('1', '3');" onclick="return false;"><a href=""><span>商品咨询</span></a></li>
            </ul>
        </div>
        <div class="shop_goods_bd_xiangqing_content clearfix">
            <div id="xiangqing_content_1" class="xiangqing_contents clearfix">
                <p>商品详情----11111</p>
            </div>
            <div id="xiangqing_content_2" class="xiangqing_contents clearfix">
                <p>商品评论----22222</p>
            </div>

            <div id="xiangqing_content_3" class="xiangqing_contents clearfix">
                <p>商品自诩---3333</p>
            </div>
        </div>
    </div>
    <!-- 商品详情 End -->

</div>
<!-- Goods Body End -->

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
<!-- Footer End -->

</body>
</html>