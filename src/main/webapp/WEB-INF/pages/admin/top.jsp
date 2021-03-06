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
    <title></title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/resources/css/admin/top.css">
    <link rel="stylesheet" href="/resources/libs/iconfont.css">
    <style type="text/css">
        #header-div {
            background: rgb(104, 104, 105);
            border-bottom: 1px solid #FFF;
        }
        #logo-div {
            height: 50px;
            float: left;
        }
        #license-div {
            height: 50px;
            float: left;
            text-align:center;
            vertical-align:middle;
            line-height:50px;
        }
        #license-div a:visited, #license-div a:link {
            color: #EB8A3D;
        }
        #license-div a:hover {
            text-decoration: none;
            color: #EB8A3D;
        }
        #submenu-div {
            height: 50px;
        }
        #submenu-div ul {
            margin: 0;
            padding: 0;
            list-style-type: none;
        }
        #submenu-div li {
            float: right;
            padding: 0 10px;
            margin: 3px 0;
            border-left: 1px solid #FFF;
        }
        #submenu-div a:visited, #submenu-div a:link {
            color: #FFF;
            text-decoration: none;
        }
        #submenu-div a:hover {
            color: #F5C29A;
        }
        /* #loading-div {
             clear: right;
             text-align: right;
             display: block;
         }
         #menu-div {
             background: #80BDCB;
             font-weight: bold;
             height: 24px;
             line-height:24px;
         }*/
        #menu-div ul {
            margin: 0;
            padding: 0;
            list-style-type: none;
        }
        #menu-div li {
            float: left;
            border-right: 1px solid #192E32;
            border-left:1px solid #BBDDE5;
        }
        #menu-div a:visited, #menu-div a:link {
            display:block;
            padding: 0 20px;
            text-decoration: none;
            color: #335B64;
            background:#9CCBD6;
        }
        #menu-div a:hover {
            color: #000;
            background:#80BDCB;
        }
        #submenu-div a.fix-submenu{ clear:both; margin-left:5px; padding:1px 5px; *padding:3px 5px 5px; background:#DDEEF2; color:#278296; }
        #submenu-div a.fix-submenu:hover{ padding:1px 5px; *padding:3px 5px 5px; background:#FFF; color:#278296; }
        #menu-div li.fix-spacel{ width:30px; border-left:none; }
        #menu-div li.fix-spacer{ border-right:none; }
    </style>
</head>
<body>

<div id="header-div">
    <div id="logo-div" style="bgcolor:#000000;">
        <a href="#"><img src="/resources/images/ico/banner.jpg" alt="PARKnSHOP" width="220px" height="50px"/></a>
    </div>
    <div id="license-div" style="bgcolor:#000000;"></div>
    <div id="submenu-div">
        <ul>
            <li style="margin: 1% 0 0 0"><a href="/admin/logout" target="_parent" style="font-size: 20px">Quit</a></li>
            <li style="border-left:none;margin:1% 0 0 0"><p style="font-size: 20px;color: white">Welcome,${sessionScope.user.username}</p></li>
        </ul>
    </div>
</div>
</body>
</html>
