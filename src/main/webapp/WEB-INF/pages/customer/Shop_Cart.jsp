<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Title</title>
    <link rel="Bookmark" href="resources/images/ico/PARKico.png">
    <link href="/resources/customer/common.css" rel="stylesheet" type="text/css" />
    <link href="/resources/customer/iconfont.css"  rel="stylesheet" type="text/css" />
    <link href="/resources/customer/style.css" rel="stylesheet" type="text/css" />
    <script src="/resources/customer/jquery-1.9.1.min.js" type="text/javascript"></script>
    <script src="/resources/customer/jquery.SuperSlide.2.1.1.js" type="text/javascript"></script>
    <script src="/resources/customer/common_js.js" type="text/javascript"></script>
    <script src="/resources/customer/footer.js" type="text/javascript"></script>
    <link href="/resources/customer/Orders.css" rel="stylesheet" type="text/css" />
    <link href="/resources/customer/purebox-metro.css" rel="stylesheet" id="skin">
    <script src="/resources/customer/jquery.reveal.js" type="text/javascript"></script>
</head>
<body>

<!--购物车样式-->
    <div class="Inside_pages clearfix">
        <div class="shop_carts">
            <div class="Process"><img src="images/Process_img_01.png" /></div>
                <div class="Shopping_list">

                   <div class="title_name">
                        <ul>
                            <li class="checkbox"></li>
                            <li class="name">Product Name</li>
                            <li class="scj">Product  Describle</li>
                            <li class="bdj">Price</li>
                            <li class="sl">amount</li>
                            <li class="xj">summary</li>
                            <LI class="cz">operation</LI>
                       </ul>
                    </div>
            <div class="shopping">
                <form  method="post" action="cart-submit.do">
                    <input type="hidden" name="ch" id="abc" value="">
                    <input type="hidden" id="cde" name="nums" value=""/>
                    <%int i=0;%>
                    <table class="table_list">
                        <c:forEach var="cart" items="${cartList}">

                            <tr class="tr">
                                <td class="checkbox">
                                    <input type="hidden" name="goodsId" value="${cart.getGoodsId()}"/>
                                    <input name="id1" type="checkbox" value="${cart.getCartId()}" />
                                </td>

                                <td class="name">
                                    <div class="img"><a href="#"><img src="${cart.getPicture()}" /></a></div>
                                    <div class="p_name" style="padding-left:50px;"><a href="#">${cart.getGoodsName()}</a></div>
                                </td>
                                <td class="scj sp">
                                        ${cart.getGoodsIntroduction()}
                                </td>
                                <td class="bgj sp" ><span id="price_item_1">￥${cart.getPrice()}</span></td>
                                <td class="sl">
                                    <div class="Numbers">
                                        <a  href="/changeAmount?cartId=${cart.getCartId()}&amount=${cart.getAmount()-1}" class="jian">-</a>
                                        <input type="text" name="num1" value="${cart.getAmount()}" class="number_text">
                                        <a  href="/changeAmount?cartId=${cart.getCartId()}&amount=${cart.getAmount()+1}" class="jia">+</a>
                                    </div>
                                </td>
                                <td class="xj" >
                                    <span>￥${cart.getPrice()*cart.getAmount()}</span>
                                </td>
                                <td class="cz">
                                    <p><a href="/removeProduct?goodsId=${cart.getCartId()}">delete</a><P>
                                    <p><a href="/insertCollect?goodsId=${cart.getGoodsId()}">collect</a></p>
                                </td>
                            </tr>

                            <%i++; %>
                        </c:forEach>
                    </table>
                    <div class="sp_Operation clearfix">
                        <!--结算-->
                        <div class="toolbar_right">
                            <div class="btn">

                                <input type="submit" class="cartsubmit"  value="生成订单"  onclick="getValue()">
                                <a class="continueFind" href="product_list.jsp"></a>

                            </div>
                        </div>
                    </div>
                </form>
            </div>

        </div>
    </div>
</div>


</body>
</html>
