<%--
  Created by IntelliJ IDEA.
  User: H
  Date: 2016/12/17
  Time: 20:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>My Cart</title>
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
    <script language="JavaScript">
        function getValue() {
            var hobbies = document.getElementsByName("id1");
            var value = "";
            for (i = 0; i < hobbies.length; i++) {
                if (hobbies[i].checked) {
                    if (!value) {
                        value = hobbies[i].value;
                    } else {
                        value += "," + hobbies[i].value;
                    }
                }
            }
            if ("" == value) {
                alert("Please select the checkBox which you want to check out!");
//                $('#confirmButton').attr("disabled",true);
                return false;
            }
            else {
//                var status=false;
//                //从checkbox处作为入口
//                $('.checkbox').each(function (cb) {
//                    console.log($(cb));
//                    //获取商品数量所在节点
//                    if ($(cb).next('.gwc_list_shuliang').children(':text').val() > $(cb).next('.gwc_list_shuliang').childreen('p').val()) {
//                        $('#tips').append(
//                            "<span>"+$(cb).next().children('a').val()+"库存不足<span>"
//                        );
//                        status=true;
//                    }
//                })
//                if(status){
//                    return false;
//                }
                var che = document.getElementById("abc");
                che.value = value;
                document.getElementById("cartSubmitForm").submit();
            }
        }

        function isSelectAll() {
            var hobbies = document.getElementsByName("id1");
            for (i = 0; i < hobbies.length; i++) {
                if (!hobbies[i].checked) {
                    return false;
                }
            }
            return true;
        }

        function selectAll() {
            var hobbies = document.getElementsByName("id1");
            if (isSelectAll()) {
                for (i = 0; i < hobbies.length; i++) {
                    hobbies[i].checked = false;
                }
            } else {
                for (i = 0; i < hobbies.length; i++) {
                    hobbies[i].checked = true;
                }
            }
        }

        function selectOther() {
            var hobbies = document.getElementsByName("id1");
            for (i = 0; i < hobbies.length; i++) {
                if (hobbies[i].checked) {
                    hobbies[i].checked = false;
                } else {
                    hobbies[i].checked = true;
                }
            }
        }

    </script>
</head>
<body>
<%@include file="head.jsp" %>
<!-- 购物车 Body -->
<div class="shop_gwc_bd clearfix">

    <div class="shop_gwc_bd_contents clearfix">

        <c:choose>
            <c:when test="${cartList.size()==0}">
                <div>
                    <img src="/resources/images/customer/cart.png"/>
                    <div style="width: 700px;margin-left:280px;margin-top: -380px;padding-bottom:100px;margin-bottom: 100px;">
                        <font style="font-family: 'Microsoft Yahei';font-size:24px;line-height: 48px;"> Your cart is
                            still empty!Let's go shopping!You can<br/>
                            <a href="/listCollect?requestPage=1"> Look at your collects!</a><br/>
                            <a href="/"> Go Shopping!</a>
                        </font>
                    </div>
                </div>
            </c:when>


            <c:when test="${cartList.size()>0}">
                <!-- 购物车列表 -->
                <table>
                    <thead>
                    <tr>
                        <th><a onclick="selectAll()">select all</a></th>
                        <th colspan="1"><span>Product</span></th>
                        <th><span>Price($)</span></th>
                        <th><span>amount</span></th>
                        <th><span>summary</span></th>
                        <th><span>operation</span></th>
                    </tr>
                    </thead>

                    <tbody>
                    <c:forEach var="cart" items="${cartList}">
                        <tr>
                            <td class="checkbox">
                                    <%--<input type="hidden" name="goodsId" value="${cart.getGoodsId()}"/>--%>
                                <c:if test="${cart.goodsAmount>=cart.amount}">
                                <input name="id1" type="checkbox" value="${cart.getCartId()}"/>
                                </c:if>
                            </td>
                            <td class="gwc_list_title"><a href="">${cart.getGoodsName()} </a></td>
                            <td class="gwc_list_danjia"><span>$<strong
                                    id="danjia_001">${cart.getPrice()}</strong></span></td>
                            <td class="gwc_list_shuliang"><span><a class="good_num_jian this_good_nums" ty="-"
                                                                   href="/changeAmount?cartId=${cart.getCartId()}&amount=${cart.getAmount()-1}">-</a>
                                <input type="text" value="${cart.getAmount()}" id="goods_001" name="num1"
                                       class="good_nums"/>
                                <%--<input type="hidden" value="${cart.goodsAmount}">--%>
                                <a href="/changeAmount?cartId=${cart.getCartId()}&amount=${cart.getAmount()+1}" ty="+"
                                   class="good_num_jia this_good_nums">+</a></span>
                                <p>In stock：${cart.goodsAmount}</p>
                            </td>
                            <td class="gwc_list_xiaoji"><span>$<strong id="xiaoji_001"
                                                                       class="good_xiaojis">${cart.getPrice()*cart.getAmount()}</strong></span>
                            </td>
                            <td class="gwc_list_caozuo"><a
                                    href="/insertCollect?goodsId=${cart.getGoodsId()}">collect</a><a
                                    href="/removeProduct?goodsId=${cart.getCartId()}">delete</a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                    <tfoot>

                    <tr>
                        <td colspan="6">
                            <div class="clear"></div>
                            <div class="gwc_foot_links">

                                <div id="tips" style="color: red">
                                </div>
                                <form action="/order/cartSubmit" id="cartSubmitForm" method="post">
                                    <input type="hidden" name="ch" id="abc" value="" required>
                                    <a href="" class="go">Go Shopping</a>
                                    <input id="confirmButton" type="button" onclick="getValue()"
                                           style="background: none repeat scroll 0 0 #FE8502; border: 1px solid #FF6633; border-radius: 5px 5px 5px 5px; color: #FFFFFF !important; display: inline-block; font-size: 14px; font-weight: 600; height: 36px; line-height: 36px; padding: 4px 12px;"
                                           value="Confirm & Fill out the Orders"></input>
                                </form>
                            </div>
                        </td>
                    </tr>
                    </tfoot>
                </table>
                <!-- 购物车列表 End -->
                <div name="message" style="font-family: 'Microsoft YaHei';font-size: larger;color: red"></div>

            </c:when>
        </c:choose>

    </div>
    <!-- 购物车有商品 end -->

</div>
<!-- 购物车 Body End -->

<%@include file="footer.jsp" %>

</body>
</html>
