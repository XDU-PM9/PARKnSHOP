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
    <title><%out.print(goods.getGoodsName());%></title>
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
                <div class=jqzoom id="spec-n1" onClick="window.open('/')">
                    <IMG height="350" src="
                        <%if (photos!=null){
                            out.print(photos.get(0));
                        }%>
                        " jqimg="
                        <%if (photos!=null){
                            out.print(photos.get(0));
                        }%>
                        " width="350">
                </div>
                <div id="spec-n5">
                    <div class=control id="spec-left">
                        <%--<img src="/resources/images/left.gif" />--%>
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
                        <%--<img src="/resources/images/right.gif" />--%>
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
                    <label>Price：</label>
                    <%--<span><strong>200.00</strong>元</span>--%>
                    <span><strong>HK$<%out.print(goods.getPrice());%></strong></span>
                </li>
                <li>
                    <label>Postage：</label>
                    <span><%out.print(goods.getPostWay());%></span>
                </li>
                <li>
                    <label>Sold：</label>
                    <%--<span>99件</span>--%>
                    <span><%out.print(goods.getSales());%> pieces</span>
                </li>
                <li>
                    <label>Comment：</label>
                    <span>
                    <c:if test="${comments.size()>0}">${comments.size()} comments</c:if>
                    <c:if test="${comments.size()==0}">0 comments</c:if> </span></span>


                </li>

                <li class="goods_num">
                    <label>Purchase：</label>
                    <span><a class="good_num_jian" id="good_num_jian" href="javascript:void(0);"></a><input type="text" value="1" id="good_nums" class="good_nums" />
                        <a href="javascript:void(0);" id="good_num_jia" class="good_num_jia"></a>(total <span id="totalAcount"><%out.print(goods.getInventory());%></span> pieces)</span>
                </li>
                <span style="display: none;" id ="hideGoodsId"><%out.print(goods.getGoodsId());%></span>
                <li style="padding:20px 0;">
                    <label>&nbsp;</label>
                    <%--<span><a href="/addProduct?goodsId=<%out.print(goods.getGoodsId());%>&amount=1" class="goods_sub goods_sub_gou" >Add to cart</a></span>--%>
                    <span><a href="/insertCollect?goodsId=<%out.print(goods.getGoodsId());%>" class="goods_sub goods_sub_gou" style="background-position: -261px -110px;">加入收藏</a></span>
                    <span><a href="/addProduct?goodsId=<%out.print(goods.getGoodsId());%>&amount=1" class="goods_sub goods_sub_gou"  id="addToCart">加入购物车</a></span>

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
                <li id="xiangqing_tab_1" onmouseover="shop_goods_easytabs('1', '1');" onfocus="shop_goods_easytabs('1', '1');" onclick="return false;"><a href=""><span>Details</span></a></li>
                <li id="xiangqing_tab_2" onmouseover="shop_goods_easytabs('1', '2');" onfocus="shop_goods_easytabs('1', '2');" onclick="return false;"><a href=""><span>Comment</span></a></li>
                <li id="xiangqing_tab_3" style="display: none" onmouseover="shop_goods_easytabs('1', '3');" onfocus="shop_goods_easytabs('1', '3');" onclick="return false;"><a href=""><span>Advisory</span></a></li>
            </ul>
        </div>
        <div class="shop_goods_bd_xiangqing_content clearfix">
            <div id="xiangqing_content_1" class="xiangqing_contents clearfix">
                <p><%out.print(goods.getIntroduction());%></p>
            </div>
            <div id="xiangqing_content_2" class="xiangqing_contents clearfix">
               <c:if test="${comments.size()==0}">
                   <div>
                       <img src="/resources/images/customer/shoucang.png"/>
                       <div  style="width: 700px;margin-left:280px;margin-top: -160px;padding-bottom:100px;margin-bottom: 100px;">
                           <font style="font-family: 'Microsoft Yahei';font-size:24px;line-height: 48px;"> There are zero comments now!<br/></font>
                       </div>
                   </div>

               </c:if>
                <c:if test="${comments.size()>0}">
                <div class="shop_member_bd_right clearfix">

                    <div class="shop_meber_bd_good_lists clearfix">
                        <div class="title"><h3>Comment List</h3></div>
                        <table>
                            <thead class="tab_title">
                            <th style="width:80px;"><span>&nbsp;</span></th>
                            <th style="width:563px;"><span>Comment Content</span></th>
                            <th style="width:200px;"><span>Commenter</span></th>
                            </thead>
                            <tbody>
                            <c:forEach var="t" items="${comments}">
                                <tr><td colspan="5">
                                    <table class="good" style="height:50px">
                                        <tbody>
                                        <tr>
                                            <td class="pingjia_pic">
                                                <c:if test="${t.commentType==1}"><span class="pingjia_type pingjia_type_1"></span></td></c:if>
                                                <c:if test="${t.commentType==2}"><span class="pingjia_type pingjia_type_2"></span></td></c:if>
                                                <c:if test="${t.commentType==3}"><span class="pingjia_type pingjia_type_3"></span></td></c:if>
                                            <td class="pingjia_title"><span>${t.comment}</span><br />[${t.commentTime}]</td>
                                            <td class="pingjia_danjia"><strong>
                                            ${t.reciverPhone.substring(0,3)}****${t.reciverPhone.substring(7)}
                                            </strong></td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </td></tr>
                            </c:forEach>

                            </tbody>
                        </table>
                    </div>
                </div>
                </c:if>
            </div>

            <div id="xiangqing_content_3" class="xiangqing_contents clearfix">
                <p>advisory---3333</p>
            </div>
        </div>
    </div>
    <!-- 商品详情 End -->
</div>
<!-- Goods Body End -->
 <script src="/resources/js/goods/add_goods.js"></script>
</body>
</html>