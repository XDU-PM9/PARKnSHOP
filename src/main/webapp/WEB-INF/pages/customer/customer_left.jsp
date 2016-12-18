<%--
  Created by IntelliJ IDEA.
  User: H
  Date: 2016/12/17
  Time: 10:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="shop_member_bd_left clearfix">

    <div class="shop_member_bd_left_pic">
        <a href="javascript:void(0);"><img src="/resources/images/images/avatar.png"/></a>
    </div>
    <div class="clear"></div>

    <dl>
        <dt>Member Center</dt>
        <dd><span><a href="/listUserInfo">User Information</a></span></dd>
        <dd><span><a href="/goPassword">Change Password</a></span></dd>
        <dd><span><a href="/listAddress">My Address</a></span></dd>
    </dl>

    <dl>
        <dt>My Collection</dt>
        <dd><span><a href="/listCollect?requestPage=1">Good Collection</a></span></dd>
        <dd><span><a href="/listCollectShop?requestPage=1">Shop Collection</a></span></dd>
    </dl>
    <dl>
        <dt>Order Center</dt>
        <dd><span><a href="/listProduct?requestPage=1">My Cart</a></span></dd>
    </dl>
</div>
