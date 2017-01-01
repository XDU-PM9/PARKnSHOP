<%--
  Created by IntelliJ IDEA.
  User: niewenzhi
  Date: 2016/12/28
  Time: 15:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link href="/resources/css/admin/top.css" rel="stylesheet" type="text/css" />
    <link href="/resources/css/admin/main.css" rel="stylesheet" type="text/css" />
</head>
<body>
<h1>
    <span class="action-span1"><a href="">Apply List</a> </span>
    <div style="clear:both"></div>
</h1>

<div class="form-div">
    <form action="javascript:search_brand()" name="searchForm">
        <img src="/resources/images/ico/icon_search.gif" width="26" height="22" border="0" alt="SEARCH">
        <input type="text" name="brand_name" size="35" placeholder="Please input the shopId">
        <input type="submit" value="Search" class="button">
    </form>
</div>


<form method="post" action="" name="listForm">
    <!-- start brand list -->
    <div class="list-div" id="listDiv">
        <table cellpadding="3" cellspacing="1">
            <tr class="queryHead">
                <th class="adId">AdvertId</th>
                <th class="adName">Name</th>
                <th class="logoHead">User</th>
                <th class="shopName">StartTime</th>
                <th class="shopDes">Price</th>
                <th class="introduction">Introduction</th>
                <th class="option">Option</th>
            </tr>
            <tbody id="tableInfor">

            </tbody>
        </table>
        <div id="turn-page" style="margin-top: 20px;text-align: center;font-size: 15px">All page:<span id="allPage"></span>&nbsp;&nbsp;&nbsp;   Current page:<span id="pageCurrent"></span>
            <span id="page-link">
                <a href="#" id="prev" style="margin-right: 20px;">Prev Page</a>
                <a href="#" id="next" style="margin-right: 20px;">Next Page</a>
                <select id="gotoPage">
                </select>
                <a href="#" id="turnPage">Turn</a>
            </span>
        </div>
    </div>
</form>
<script src="/resources/libs/jquery.js"></script>
<script src="/resources/js/admin/shopadapply.js"></script>
</body>
</html>
