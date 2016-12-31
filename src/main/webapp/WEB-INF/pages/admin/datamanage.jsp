<%--
  Created by IntelliJ IDEA.
  User: niewenzhi
  Date: 2016/12/28
  Time: 15:29
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
    <span class="action-span1" style="color: black;font-size: 22px">Data Manage</span>
    <div style="clear:both"></div>
</h1>
<div class="form-div">
    <form action="" name="backup">
        <span style="color: #686869;font-size: 18px;">The Last Backup Time:</span>
            <span id="backUpTime" style="color: #686869;font-size: 18px">Sorry, there is no backup file.</span>
        <button id="backUpBtn">BackUp</button>
    </form>
</div>
<form method="post" action="" name="listForm">
    <!-- start brand list -->
    <div class="list-div" id="listDiv">
        <table cellpadding="3" cellspacing="1">
            <tr>
                <th>Time</th>
                <th>Option</th>
            </tr>

            <tbody id="tableInfor">
            </tbody>
        </table>
    </div>
</form>
<div id="footer">
    &copy; 2016-2017 Group9-PARKnSHOP </div>
</div>
</body>
<script src="/resources/libs/jquery.js"></script>
<script src="/resources/js/admin/dataManage.js"></script>
</html>
