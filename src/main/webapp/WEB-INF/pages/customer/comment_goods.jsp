<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2016/12/31
  Time: 11:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My Comment</title>
    <link rel="stylesheet" href="../../../resources/css/base.css" type="text/css"/>
    <link rel="stylesheet" href="../../../resources/css/shop_common.css" type="text/css"/>
    <link rel="stylesheet" href="../../../resources/css/shop_header.css" type="text/css"/>
    <link rel="stylesheet" href="../../../resources/css/shop_manager.css" type="text/css"/>
    <link rel="stylesheet" href="../../../resources/css/shop_shdz_835.css" type="text/css"/>
    <link rel="stylesheet" href="../../../resources/css/shop_gouwuche.css" type="text/css"/>
    <link rel="stylesheet" href="../../../resources/css/shop_form.css" type="text/css">
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
                <div class="title"><h3>Personal Information</h3></div>
                <div class="clear"></div>
                <dic class="shop_home_form">
                    <form action="/comment/insertComment" name="" class="shop_form" method="post">
                        <ul>
                            <input type="hidden" name="ordersId" value="${oders.ordersId}"/>
                            <li><label>Product Name：</label>${oders.goodsName}</li>
                            <li><label>Product Describle：</label>${oders.goodsDescribe}</li>
                            <li>
                                <label>Choose comment type</label>
                                <select  name="commentType">
                                    <option value="1"><a class="pingjia_pic"><span class="pingjia_type pingjia_type_1"></span>good Comment</a></option>
                                    <option value="2"><span class="pingjia_type pingjia_type_2"></span>middle Comment</option>
                                    <option value="3"><span class="pingjia_type pingjia_type_3"></span>bad Comment</option>
                                </select>
                            </li>
                            <li><label>Comment：</label> <input type="text" name="comment_context" required></li>
                            <input  style="margin-left: 300px;" type="submit" value="commit">
                        </ul>
                    </form>
                </dic>
            </div>
        </div>
    </div>



    <%@include file="footer.jsp"%>
</body>
</html>
