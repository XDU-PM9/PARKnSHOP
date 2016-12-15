<%--
  Created by IntelliJ IDEA.
  User: niewenzhi
  Date: 2016/12/12
  Time: 20:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
    <title></title>
    <link href="/resources/css/admin/menu.css" rel="stylesheet" type="text/css" />
    <style type="text/css">
        body {background: #ffffff;border: 1px solid black;border-top:none;}
        #tabbar-div {background: #278296;padding-left: 10px;height: 21px; padding-top: 0px;}
        #tabbar-div p { margin: 1px 0 0 0;}
        .tab-front {background: #80BDCB;line-height: 20px;font-weight: bold;padding: 4px 15px 4px 18px;border-right: 2px solid #335b64;cursor: hand;cursor: pointer;}
    </style>
</head>
<body>
<div id="tabbar-div">
    <p>
        <span class="tab-front" id="menu-tab">Menu</span>
    </p>
</div>
<div class="leftMain">
    <div class="list1">
        <h1>Manage Apply:</h1>
        <ul class="leftList">
            <li style="border-bottom: 1px solid black"><a href="/admin/applymanage" class="leftLink" target="main-frame">Check Apply</a></li>
        </ul>
    </div>
    <div class="list2">
        <h1>Manage Owner:</h1>
        <ul class="leftList">
            <li><a href="/admin/ownermanage" class="leftLink" target="main-frame">Apply all owner</a></li>
            <li style="border-bottom: 1px solid black"><a href="/admin/shopmanage" class="leftLink" target="main-frame">Apply all shop</a></li>
        </ul>
    </div>
    <div class="list3">
        <h1>Manage Customer:</h1>
        <ul class="leftList">
            <li style="border-bottom: 1px solid black"><a href="/admin/customermanage" class="leftLink" target="main-frame">Apply all customer</a></li>
        </ul>
    </div>
    <div class="list4">
        <h1>Manage Adver:</h1>
        <ul class="leftList">
            <li><a href="" class="leftLink" target="main-frame">Top 5</a></li>
            <li><a href="" class="leftLink" target="main-frame">Top 10</a></li>
        </ul>
    </div>
</div>
<script src="/resources/libs/jquery.js"></script>
<script src="/resources/js/admin/left.js"></script>
</body>
</html>
