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
</head>
</bod>
<h1>
    <span class="action-span1" style="color: black;font-size: 22px">Income List</span>
    <div style="clear:both"></div>
</h1>
<form method="post" action="/admin/setRate" name="listForm">
    <!-- start brand list -->
    <div class="list-div" id="listDiv">
        <table cellpadding="3" cellspacing="1">
            <tr>
                <th class="type" style="width: 20%">Type</th>
                <th class="content">Content</th>
                <th class="option">Option</th>
            </tr>
            <tr id="rate">
                <td>Rate</td>
            </tr>
            <tr id="shopPrice">
                <td>shopPrice</td>
            </tr>
            <tr id="goodsPrice">
                <td>goodsPrice</td>
            </tr>
            <tbody id="tableInfor">
            </tbody>
        </table>
    </div>
</form>
<div class="hide-block">
    <div class="rate-block">
        <p style="width: 510px;border-bottom: 0.5px solid #aeaeae;margin: 10px 0 0 2px;text-align: center">Please Edit The Infor:</p>
        <form action="/admin/setRate" enctype="multipart/form-data" method="post">
            <ul class="formInfor">
                <div class="inforBlock">
                    <span>Rate:</span>
                    <li>
                        <input type="text" name="rate" id="setRate"/>
                    </li>
                </div>
                <input type="submit"  class="butlogin" value="Submit" id="rate-submitBtn"/>
            </ul>
        </form>
    </div>
    <div class="shopPrice-block">
        <p style="width: 510px;border-bottom: 0.5px solid #aeaeae;margin: 10px 0 0 2px;text-align: center">Please Edit The Infor:</p>
        <form action=/admin/setShopPrice"" enctype="multipart/form-data" method="post">
            <ul class="formInfor">
                <div class="inforBlock">
                    <span>ShopPrice:</span>
                    <li>
                        <input type="text" name="shopPrice" id="setShopPrice"/>
                    </li>
                </div>
                <input type="submit"  class="butlogin" value="Submit" id="shopPrice-submitBtn"/>
            </ul>
        </form>
    </div>
    <div class="goodsPrice-block">
        <p style="width: 510px;border-bottom: 0.5px solid #aeaeae;margin: 10px 0 0 2px;text-align: center">Please Edit The Infor:</p>
        <form action="/admin/setGoodsPrice" enctype="multipart/form-data" method="post">
            <ul class="formInfor">
                <div class="inforBlock">
                    <span>goodsPrice:</span>
                    <li>
                        <input type="text" name="goodsPrice" id="setGoodsPrice"/>
                    </li>
                </div>
                <input type="submit"  class="butlogin" value="Submit" id="goodsPrice-submitBtn"/>
            </ul>
        </form>
    </div>
</div>
<div id="footer">
    &copy; 2016-2017 Group9-PARKnSHOP </div>
</div>

</body>
<script src="/resources/libs/jquery.js"></script>
<script src="/resources/js/admin/incomeManage.js"></script>
<script>
    /*$(function () {
     $(".hide-block").show();
     $(".rate-block").show();
     })*/
</script>
</html>
