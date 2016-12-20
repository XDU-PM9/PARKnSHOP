<%--
  Created by IntelliJ IDEA.
  User: H
  Date: 2016/12/18
  Time: 10:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Header  -wll-2013/03/24 -->

    <!-- Header TopNav -->
    <div class="shop_hd_topNav">
        <div class="shop_hd_topNav_all">
            <!-- Header TopNav Left -->
            <div class="shop_hd_topNav_all_left">
                <p><a href="/"> Welcome to PARKnSHOP</a></p>
            </div>
            <!-- Header TopNav Left End -->

            <!-- Header TopNav Right -->
            <div class="shop_hd_topNav_all_right">
                <ul class="topNav_quick_menu">
                    <c:choose>
                        <c:when test="${user==null}">
                            <li>
                                <div class="topNav_menu">
                                    <a href="/customer/login" class="topNavHover">Sign in</a>
                                </div>
                            </li>

                            <li>
                                <div class="topNav_menu">
                                    <a href="/customer/register" class="topNavHover">Sign up</a>
                                </div>
                            </li>
                            <li>
                                <a href="/owner">Seller entry</a>
                            </li>

                        </c:when>
                        <c:when test="${user!=null}">
                            <li>
                                <div class="topNav_menu">
                                    <a href="/listUserInfo" class="topNavHover">Welcome,${user.getUsername()}</a>
                                </div>
                            </li>

                            <li>
                                <div class="topNav_menu">
                                    <a href="/customer/logout" class="topNavHover">Logout</a>
                                </div>
                            </li>
                            <li>
                                <div class="topNav_menu">
                                    <a href="#" class="topNavHover">My Collection<i></i></a>
                                    <div class="topNav_menu_bd" style="display:none;">
                                        <ul>
                                            <li><a title="My_Collection" target="_top" href="/listCollect?requestPage=1">Product</a></li>
                                            <li><a title="My_Info" target="_top" href="/listCollectShop?requestPage=1">Shop</a></li>
                                        </ul>
                                    </div>
                                </div>
                            </li>

                        </c:when>
                    </c:choose>


                    <li>
                        <div class="topNav_menu">
                            <a href="/listProduct?requestPage=1" class="topNavHover">My Cart</a>
                            <div class="topNav_menu_bd" style="display:none;">
                                <!--
                                <ul>
                                  <li><a title="已售出的商品" target="_top" href="#">已售出的商品</a></li>
                                  <li><a title="销售中的商品" target="_top" href="#">销售中的商品</a></li>
                                </ul>
                                -->
                                <c:if test="${cartList.size()==0}">
                                         <p>No Goods,quickly go shopping!</p>
                                </c:if>
                            </div>
                        </div>
                    </li>

                </ul>
            </div>
            <!-- Header TopNav Right End -->
        </div>
    </div>
