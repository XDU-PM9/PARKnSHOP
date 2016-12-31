<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2016/12/31
  Time: 11:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Comment Management</title>
    <link rel="stylesheet" href="../../../resources/css/base.css" type="text/css"/>
    <link rel="stylesheet" href="../../../resources/css/shop_common.css" type="text/css"/>
    <link rel="stylesheet" href="../../../resources/css/shop_header.css" type="text/css"/>
    <link rel="stylesheet" href="../../../resources/css/shop_manager.css" type="text/css"/>
    <link rel="stylesheet" href="../../../resources/css/shop_shdz_835.css" type="text/css"/>
    <link rel="stylesheet" href="../../../resources/css/shop_gouwuche.css" type="text/css"/>
    <script type="text/javascript" src="/../../../resources/js/jquery.js"></script>
    <script type="text/javascript" src="../../../resources/js/topNav.js"></script>
    <script type="text/javascript" src="../../../resources/js/jquery.goodnums.js"></script>
    <script type="text/javascript" src="../../../resources/js/shop_gouwuche.js"></script>
</head>
<body>
    <%@include file="head.jsp"%>
    <div class="shop_member_bd clearfix">
    <%@include file="customer_left.jsp"%>
    <!-- 右边购物列表 -->
    <div class="shop_member_bd_right clearfix">

        <div class="shop_meber_bd_good_lists clearfix">
            <div class="title"><h3>Comment List</h3></div>
            <table>
                <thead class="tab_title">
                <th style="width:80px;"><span>&nbsp;</span></th>
                <th style="width:320px;"><span>Comment Content</span></th>
                <th style="width:180px;"><span>Commenter</span></th>
                <th style="width:180px;"><span>Goods Information</span></th>
                </thead>
                <tbody>
                <c:forEach var="t" items="${orders}">
                <tr><td colspan="5">
                    <table class="good" style="height:50px">
                        <tbody>
                        <tr>
                            <td class="pingjia_pic">
                                <c:if test="${t.commentType==1}"><span class="pingjia_type pingjia_type_1"></span></td></c:if>
                                <c:if test="${t.commentType==2}"><span class="pingjia_type pingjia_type_2"></span></td></c:if>
                                <c:if test="${t.commentType==3}"><span class="pingjia_type pingjia_type_3"></span></td></c:if>
                            <td class="pingjia_title"><span>${t.comment}</span><br />[${t.commentTime}]</td>
                            <td class="pingjia_danjia"><strong>${t.reciver}</strong></td>
                            <td class="pingjia_shuliang"><a href="/goods/detail?goodsId=${t.goodsByGoodsId.goodsId}">${t.goodsName}</a><br />${t.price*t.amount}</td>
                        </tr>
                        </tbody>
                    </table>
                </td></tr>
                </c:forEach>

                </tbody>
            </table>
        </div>
    </div>
    <!-- 右边购物列表 End -->

    </div>
    <!-- 我的个人中心 End -->
    <%@include file="footer.jsp"%>
</body>
</html>
