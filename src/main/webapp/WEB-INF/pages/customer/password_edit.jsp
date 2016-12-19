<%--
  Created by IntelliJ IDEA.
  User: H
  Date: 2016/12/17
  Time: 20:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>Change Password</title>
    <link rel="stylesheet" href="../../../resources/css/base.css" type="text/css" />
    <link rel="stylesheet" href="../../../resources/css/shop_common.css" type="text/css" />
    <link rel="stylesheet" href="../../../resources/css/shop_header.css" type="text/css" />
    <link rel="stylesheet" href="../../../resources/css/shop_manager.css" type="text/css" />
    <link rel="stylesheet" href="../../../resources/css/shop_form.css" type="text/css" />
    <script type="text/javascript" src="../../../resources/js/jquery.js" ></script>
    <script type="text/javascript" src="../../../resources/js/topNav.js" ></script>
    <script>
        $(document).ready(function () {
            $('#changPasswordForm').bind('submit',function () {
                if ($('#password').val() != $('#passwordConfirm').val()) {
                    $('#tips').html('Entered passwords differ');
//                    alert("password");
                    return false;
                }
            })
        })
    </script>
</head>
<body>
<%@include file="head.jsp"%>
<div class="shop_member_bd clearfix">
<%@include file="customer_left.jsp"%>

<!-- 右边购物列表 -->
<div class="shop_member_bd_right clearfix">

    <div class="shop_meber_bd_good_lists clearfix">
        <div class="title"><h3>Change Password</h3></div>
        <div class="clear"></div>
        <dic class="shop_home_form">
            <form id="changPasswordForm" action="changePassword"  name="" class="shop_form" method="post">
                <ul>
                    <li class="bn"><label>Old Password：</label><input type="password" name="password" class="truename form-text" required/></li>
                    <li class="bn"><label>New Password：</label><input type="password" id="password" name="pass" class="truename form-text" required/></li>
                    <li class="bn"><label>Confirm Password：</label><input type="password" id="passwordConfirm" name="pass1" class="truename form-text" required/></li>
                    <li><span id="tips" style="color: red;text-align: center">${tips}</span></li>
                    <li class="bn"><label>&nbsp;</label><input type="submit" class="form-submit" value="Confirm the change" /></li>
                </ul>
            </form>
        </dic>
    </div>
</div>
</div>
<!-- 右边购物列表 End -->

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
