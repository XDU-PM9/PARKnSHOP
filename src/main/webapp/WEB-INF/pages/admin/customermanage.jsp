<%--
  Created by IntelliJ IDEA.
  User: niewenzhi
  Date: 2016/12/11
  Time: 19:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="/resources/css/admin/menu.css" rel="stylesheet" type="text/css" />
    <link href="/resources/css/admin/main.css" rel="stylesheet" type="text/css" />
</head>
<body>

<h1>
    <span class="action-span1"><a href="">Customer List</a> </span>
    <div style="clear:both"></div>
</h1>

<div class="form-div">
    <form action="javascript:search_brand()" name="searchForm">
        <img src="/resources/images/ico/icon_search.gif" width="26" height="22" border="0" alt="SEARCH">
        <input type="text" name="brand_name" size="35" placeholder="Please input the userId">
        <input type="submit" value="Search" class="button">
    </form>
</div>

<%--<form method="post" action="" name="listForm">
    <!-- start brand list -->
    <div class="list-div" id="listDiv">
        <table cellpadding="3" cellspacing="1">
            <tr>
                <th>userId</th>
                <th>username</th>
                <th>userImg</th>
                <th>email</th>
                <th>phone</th>
                <th>option</th>
            </tr>
            <!--end，这些都是显示代码，没有格式化，开发时会删除 -->
            <tbody id="tableInfor">

            </tbody>
        </table>
        <div id="turn-page" style="margin-top: 20px;text-align: center;font-size: 15px">All page:<span id="allPage"></span>&nbsp;&nbsp;&nbsp;   Current page:<span id="pageCurrent"></span>
            <span id="page-link">
                <a href="#" id="prev" style="margin-right: 20px;">Prev Page</a>
                <a href="#" id="next" style="margin-right: 20px;">Next Page</a>
                <select name="" id="gotoPage">
                </select>
                <a href="#" id="turnPage">Turn</a>
            </span>
        </div>
        <!-- end brand list -->
    </div>
</form>--%>
<form method="post" action="" name="listForm">
    <!-- start brand list -->
    <div class="list-div" id="listDiv">
        <table cellpadding="3" cellspacing="1">
            <tr>
                <th>userId</th>
                <th>username</th>
                <th>userImg</th>
                <th>email</th>
                <th>phone</th>
                <th>option</th>
            </tr>

            <!--end，这些都是显示代码，没有格式化，开发时会删除 -->
            <tbody id="tableInfor">


            </tbody>
        </table>
        <div id="turn-page" style="margin-top: 20px;text-align: center;font-size: 15px">All page:<span id="allPage"></span>&nbsp;&nbsp;&nbsp;   Current page:<span id="pageCurrent"></span>
            <span id="page-link">
                <a href="#" id="prev" style="margin-right: 20px;">Prev Page</a>
                <a href="#" id="next" style="margin-right: 20px;">Next Page</a>
                <select name="" id="gotoPage">
                </select>
                <a href="#" id="turnPage">Turn</a>
            </span>
        </div>

        <!-- end brand list -->
    </div>
</form>
<div id="footer">
    &copy; 2016-2017 Group9-PARKnSHOP </div>
</div>
<script src="/resources/libs/jquery.js"></script>
<script src="/resources/js/admin/apply_customer.js"></script>
</body>
</html>
