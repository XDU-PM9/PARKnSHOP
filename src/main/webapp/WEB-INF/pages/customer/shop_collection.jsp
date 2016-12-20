<%--
  Created by IntelliJ IDEA.
  User: H
  Date: 2016/12/17
  Time: 20:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>Shop Collect</title>
    <link rel="stylesheet" href="../../../resources/css/base.css" type="text/css"/>
    <link rel="stylesheet" href="../../../resources/css/shop_common.css" type="text/css"/>
    <link rel="stylesheet" href="../../../resources/css/shop_header.css" type="text/css"/>
    <link rel="stylesheet" href="../../../resources/css/shop_manager.css" type="text/css"/>
    <link rel="stylesheet" href="../../../resources/css/shop_list.css" type="text/css"/>
    <script type="text/javascript" src="../../../resources/js/jquery.js"></script>
    <script type="text/javascript" src="../../../resources/js/topNav.js"></script>
</head>
<body>
<%@include file="head.jsp"%>
<div class="shop_member_bd clearfix">
<%@include file="customer_left.jsp"%>

<!-- 右边购物列表 -->
<div class="shop_member_bd_right clearfix" >

    <div class="shop_meber_bd_good_lists clearfix">
        <!-- 商品列表 -->
        <div class="shop_meber_bd_good_lists clearfix">
            <div class="title"><h3>Shop collection</h3></div>
<c:choose>
    <c:when test="${Collects.size()<=0}">
        <div>
            <img src="/resources/images/customer/shoucang.png"/>
            <div  style="width: 700px;margin-left:280px;margin-top: -160px;padding-bottom:100px;margin-bottom: 100px;">
                <font style="font-family: 'Microsoft Yahei';font-size:24px;line-height: 48px;"> You have not owned any shops yet!<br/></font>
                <font  style="font-family:'Microsoft Yahei';font-size:16px;line-height: 18px;">  <a href="/"> Let's go shopping, and choose your favorite.</a>

                </font>
            </div>
        </div>
    </c:when>
    <c:when test="${Collects.size()>0}">
            <!-- 商品列表 -->
            <div class="shop_bd_list_content clearfix">
                <ul>
                    <c:forEach var="c" items="${Collects}">
                        <li>
                            <dl>
                                <dt><a href=""><img src="${c.getPicture()}"/></a>
                                </dt>
                                <dd class="title"><a href="">${c.getShopByShopId().getShopName()}</a></dd>
                                <dd class="content">
                                    <span class="goods_chengjiao">${c.getShopByShopId().getIntroduction() }</span>
                                </dd>
                            </dl>
                        </li>
                    </c:forEach>

                </ul>
            </div>
            <div class="clear"></div>
            <div class="pagination">
                <ul>
                    <c:choose>
                        <c:when test="${currentPage>1}"> <li><a href="/listCollectShop?requestPage=${currentPage-1}"> <span>Previous</span></a></li></c:when>
                    </c:choose>
                    <li><span class="currentpage">${currentPage} of ${sina}</span></li>
                    <c:choose>
                        <c:when test="${currentPage<sina}"> <li><a href="/listCollectShop?requestPage=${currentPage+1}"> <span>Next</span> </a></li></c:when>
                    </c:choose>
                    <li>
                    <form action="/jumpPage" method="post">
                        <input type="hidden" name="ty" value="2">
                        <input type="number"  name="jump"  min="1" max="${sina}"  value="1"/>
                        <input type="submit" value="jump">
                    </form>
                    </li>
                </ul>
            </div>
            <!-- 商品列表 End -->

        </div>
    </c:when>
        </c:choose>
    </div>
    <!-- 右边购物列表 End -->

</div>
</div>
</div>

    <%@include file="footer.jsp"%>
</div>
</body>
</html>
