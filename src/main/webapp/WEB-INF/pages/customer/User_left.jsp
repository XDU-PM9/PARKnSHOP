<%--
  Created by IntelliJ IDEA.
  User: H
  Date: 2016/12/5
  Time: 21:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>



    <div class="user_center">
        <div class="left_style">
            <div class="menu_style">
                <div class="user_title">User Center</div>
                <div class="user_Head">

                    <div class="user_portrait">
                        <a href="#" title="修改头像" class="btn_link"></a> <img src="${user.getUserImage()}">
                        <div class="background_img"></div>
                    </div>
                    <div class="user_name">
                        <p><span class="name">${user.getUsername()}</span><a href="/goPassword">[Change Password]</a></p>
                    </div>
                </div>
                <div class="sideMen">



                    <dl class="accountSideOption1">
                        <dt class="transaction_manage"><em class="icon_2"></em>Member Center</dt>
                        <dd>
                            <ul>
                                <li> <a href="/listUserInfo"> User Information</a></li>
                                <li> <a href="/goPassword">Change Password</a></li>
                                <li> <a href="/listAddress"> My Address</a></li>
                            </ul>
                        </dd>
                    </dl>
                    <dl class="accountSideOption1">
                        <dt class="transaction_manage"><em class="icon_4"></em>My Collection</dt>
                        <dd>
                            <ul>
                                <li> <a href="/listCollectShop?requestPage=1"> Collect Shop</a></li>
                                <li> <a href="/listCollect?requestPage=1"> Collect  Product</a></li>
                            </ul>
                        </dd>
                    </dl>

                    <!--
                    <dl class="accountSideOption1">
                        <dt class="transaction_manage"><em class="icon_1"></em>Order Center</dt>
                        <dd>
                            <ul>
                                <li> <a href="#"> 我的订单</a></li>

                            </ul>
                        </dd>
                    </dl>

                    <dl class="accountSideOption1">
                        <dt class="transaction_manage"><em class="icon_3"></em>账户中心</dt>
                        <dd>
                            <ul>
                                <li> <a href="#"> 资金管理</a></li>
                                <li> <a href="#">充值缴费</a>
                            </ul>
                        </dd>
                    </dl>
                    -->
                </div>
                <script>jQuery(".sideMen").slide({titCell:"dt", targetCell:"dd",trigger:"click",defaultIndex:0,effect:"slideDown",delayTime:300,returnDefault:true});</script>
            </div>
        </div>
</div>

