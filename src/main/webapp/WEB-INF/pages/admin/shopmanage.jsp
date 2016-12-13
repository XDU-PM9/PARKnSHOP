<%--
  Created by IntelliJ IDEA.
  User: niewenzhi
  Date: 2016/12/12
  Time: 22:32
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
            <tbody id="tableInfor">
            <tr>
                <th>shopId</th>
                <th>shopName</th>
                <th>shopLogo</th>
                <th>introduction</th>
                <th>ownerId</th>
                <th>option</th>
            </tr>
            <tr>
                <td class="first-cell">
                    <span>WYH</span>
                </td>
                <td><span>WYH</span></td>
                <td class="imgTd"><img src="/resources/images/portrait/a.png" alt=""></td>
                <td class="shopDesc">
                    Android是一种基于Linux的自由及开放源代码的操作系统，主要使用于移动设备，如智能手机和平板电脑，由Google公司和开放手机联
                </td>
                <td>
                    <span>10086</span>
                </td>
                <td>
                    <a href="">Black</a> |
                    <a href="">Delete</a> |
                    <a href="">Recover</a>
                </td>

            </tr>

            <!--end，这些都是显示代码，没有格式化，开发时会删除 -->
            <tr>
                <td align="right" nowrap="true" colspan="6">
                    <div id="turn-page" style="margin-top: 20px">
                        总计  <span id="totalRecords">11</span>
                        个记录分为 <span id="totalPages">2</span>
                        页当前第 <span id="pageCurrent">1</span>页
        <span id="page-link">
          <a href="javascript:listTable.gotoPageFirst()">第一页</a>
          <a href="javascript:listTable.gotoPagePrev()">上一页</a>
          <a href="javascript:listTable.gotoPageNext()">下一页</a>
          <a href="javascript:listTable.gotoPageLast()">最末页</a>
          <select id="gotoPage" onchange="listTable.gotoPage(this.value)">
            <option value="1">1</option><option value="2">2</option>
          </select>
        </span>
                    </div>
                </td>
            </tr>
            </tbody>
        </table>

        <!-- end brand list -->
    </div>
</form>
<div id="footer">
    &copy; 2016-2017 Group9-PARKnSHOP </div>
</div>
<script src="/resources/libs/jquery.js"></script>
<script src="/resources/js/admin/apply_shop.js"></script>
</body>
</html>