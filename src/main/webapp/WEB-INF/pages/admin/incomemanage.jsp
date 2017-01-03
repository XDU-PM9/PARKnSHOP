<%--
  Created by IntelliJ IDEA.
  User: niewenzhi
  Date: 2016/12/31
  Time: 11:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link href="/resources/css/admin/top.css" rel="stylesheet" type="text/css" />
    <link href="/resources/css/admin/main.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="/resources/css/admin/input.css">
    <link rel="stylesheet" href="/resources/libs/xcConfirm.css">
</head>
</bod>
<h1>
    <span class="action-span1" style="color: black;font-size: 22px">Income List</span>
    <div style="clear:both"></div>
</h1>
<form method="post" action="" name="listForm">
    <!-- start brand list -->
    <div class="list-div" id="listDiv">
        <table cellpadding="3" cellspacing="1">
            <tr>
                <th class="type" style="width: 20%">Type</th>
                <th class="content">Content</th>
                <th class="option">Option</th>
            </tr>
            <tr id="rate">
                <td>Commission Rate</td>
            </tr>
            <tr id="shopPrice">
                <td>Shop Advert Price</td>
            </tr>
            <tr id="goodsPrice">
                <td>Godds Advert Price</td>
            </tr>
            <tbody id="tableInfor">
            </tbody>
        </table>
    </div>
</form>
<div class="hide-block">
    <div class="rate-block">
        <a href="javascript:close()" class="closeBtn"></a>
        <p style="width: 426px;border-bottom: 0.5px solid #aeaeae;margin: 10px 0 0 2px;text-align: center">Please Edit The Infor:</p>
        <form action="" enctype="multipart/form-data" method="post">
            <ul class="formInfor">
                <div class="inforBlock">
                    <span>Rate:</span>
                    <li>
                        <input type="text" name="rate" id="setRate"/>
                    </li>
                </div>
                <input type="button"  class="butlogin" value="Submit" id="rate-submitBtn"/>
            </ul>
        </form>
    </div>
    <div class="shopPrice-block">
        <a href="javascript:close()" class="closeBtn"></a>
        <p style="width: 426px;border-bottom: 0.5px solid #aeaeae;margin: 10px 0 0 2px;text-align: center">Please Edit The Infor:</p>
        <form action="" enctype="multipart/form-data" method="post">
            <ul class="formInfor">
                <div class="inforBlock">
                    <span>ShopPrice:</span>
                    <li>
                        <input type="text" name="shopPrice" id="setShopPrice"/>
                    </li>
                </div>
                <input type="button"  class="butlogin" value="Submit" id="shopPrice-submitBtn"/>
            </ul>
        </form>
    </div>
    <div class="goodsPrice-block">
        <a href="javascript:close()" class="closeBtn"></a>
        <p style="width: 426px;border-bottom: 0.5px solid #aeaeae;margin: 10px 0 0 2px;text-align: center">Please Edit The Infor:</p>
        <form action="" enctype="multipart/form-data" method="post">
            <ul class="formInfor">
                <div class="inforBlock">
                    <span>goodsPrice:</span>
                    <li>
                        <input type="text" name="goodsPrice" id="setGoodsPrice"/>
                    </li>
                </div>
                <input type="button"  class="butlogin" value="Submit" id="goodsPrice-submitBtn"/>
            </ul>
        </form>
    </div>
</div>
<div id="footer">
    &copy; 2016-2017 Group9-PARKnSHOP </div>
</div>

</body>
<script src="/resources/libs/jquery.js"></script>
<script src="/resources/libs/xcConfirm.js"></script>
<script src="/resources/js/admin/incomeManage.js"></script>

<script>
    /*$(function () {
     $(".hide-block").show();
     $(".rate-block").show();
     })*/
</script>
</html>
