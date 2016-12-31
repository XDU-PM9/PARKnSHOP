<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2016/12/26
  Time: 18:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="../../../resources/css/shop_common.css" type="text/css"/>
    <link rel="stylesheet" href="../../../resources/css/shop_header.css" type="text/css"/>
    <link rel="stylesheet" href="../../../resources/css/shop_manager.css" type="text/css"/>
    <link rel="stylesheet" href="../../../resources/css/shop_list.css" type="text/css"/>
    <style type="text/css">

        /****閸欏厖鏅堕弽宄扮础鐏炵偞锟斤拷****/
        .user_center .Notice{ height:30px; padding:5px 0px; line-height:30px; background:#fffbeb; border:1px solid  #ffd800; color: #333333; font-family:"閺傛澘鐣担锟�"; margin-bottom:10px;}
        .user_center .Notice span{ font-weight:bold; color:#090; padding-left:5px;}
        .user_center .right_style{ width:980px;}
        .user_center .right_style .title_Section{ border-bottom:1px solid #ddd; height:40px; line-height:40px; font-size:18px;}
        .user_center .right_style .title_Section span{ border-bottom:3px solid #F30; padding:7px 10px;}
        .info_content .user_info{ height:130px; padding-bottom:10px;}
        .info_content .user_info li{
            float:left;
            width:176px;
            text-align:center;
            height:120px;
            border-radius: 5px;
            -moz-border-radius: 5px;
            -webkit-border-radius: 5px;
            background-color:#F7B51A;
            margin:0px 10px;
        }
        .info_content .user_info li img{ margin-top:20px;}
        .info_content .user_info li.Balance{ background:#ffa3a3}
        .info_content .user_info li.integral{ background-color:#6ebfda}
        .info_content .user_info li.Order_form{ background-color:#69F}
        .info_content .user_info li.grade{ background-color:#ffa3a3}
        .info_content .user_info li.Favorable{ background-color: #F93}
        .info_content .user_info li.Balance:hover{ background:#F78A8A}
        .info_content .user_info li.integral:hover{ background-color:#56AECC}
        .info_content .user_info li.Order_form:hover{ background-color:#568BF5}
        .info_content .user_info li.grade:hover{ background-color:#EA8181}
        .info_content .user_info li.Favorable:hover{ background-color:#F38920}
        .info_content .user_info li h4{ margin-top:10px}
        .info_content .user_info li a{ display:block; line-height:30px; color:#FFF; font-size:16px}
        /***鐠併垹宕熼弽宄扮础鐏炵偞锟斤拷**/
        .user_info_p_s .left_user_cont {width: 710px;margin-right: 10px;float: left;}
        .user_info_p_s .right_user_recording {width: 240px;float: right}
        .user_info_p_s .us_Orders, .user_info_p_s .us_Orders table {width: 710px;}
        .user_info_p_s .us_Orders .Orders_name {height: 40px;}
        .user_info_p_s .us_Orders .Orders_name .title_name {border-bottom: 1px solid #ddd;}
        .user_info_p_s .us_Orders table tbody tr {height: 100px;border-bottom: 1px dashed #ddd;}
        .user_info_p_s .us_Orders table thead {line-height: 30px;background: #EAEAEA;}
        .user_info_p_s .us_Orders table thead th {padding: 5px 10px;font-size: 14px;text-align:center;}
        .user_info_p_s .us_Orders table tbody tr td {padding: 10px;text-align:center}
        .user_info_p_s .us_Orders table tbody tr td .View{
            display: inline-block;
            padding: 6px 12px;
            margin-bottom: 0;
            font-size: 14px;
            font-weight: normal;
            line-height: 1.42857143;
            text-align: center;
            white-space: nowrap;
            vertical-align: middle;
            cursor: pointer;
            -webkit-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            user-select: none;
            background-image: none;
            border: 1px solid transparent;
            border-radius: 4px;
            color: #fff;
            background-color: #F63;
            border-color: #F63;
        }
        .user_info_p_s .us_Orders table tbody tr td .View:hover{background-color: #F60 ;}
        .user_info_p_s .us_Orders table tbody tr td.img_link {width: 450px;text-align:left;}
        .user_info_p_s .us_Orders table tbody tr td.img_link .title {
            display: block;
            width: 314px;
            float: left;
            padding: 10px;
        }
        .user_info_p_s .us_Orders table tbody tr td .img {
            display: block;
            width: 80px;
            padding: 1px;
            height: 80px;
            float: left;
            border: 1px solid #ddd;
        }


        .user_center .right_style .title_Section{ border-bottom:1px solid #ddd; height:40px; line-height:40px; font-size:18px;}
        .user_center .right_style .title_Section span{ border-bottom:3px solid #F30; padding:7px 10px;}
        /*******************************************璁㈠崟纭椤垫牱寮忓睘鎬�*******************************************/
        .Orders_style .Address_info{ border:3px solid  #F90;}
        .Orders_style .Address_info .title_name{ line-height:40px; height:40px;padding:0px 20px;  font-size:16px;border-bottom:1px solid #F90;}
        .Orders_style .Address_info .title_name a{ float:right; font-size:12px; color: #fff; background:#F60; border:1px solid #F60; line-height:24px; padding:0px 10px; margin-top:7px; border-radius:3px;}
        .Orders_style .Address_info li{ float:left; width:536px; padding:0px 20px;  height:40px; line-height:40px; border-bottom:1px dashed #ddd; padding:0px 20px; font-family:"鏂板畫浣�"}
        .Orders_style .Address_info li label{ float:left; width:80px; margin-right:10px; text-align:right}
    </style>
</head>
<body>


<%@include file="head.jsp"%>
<div class="shop_member_bd clearfix">
<%@include file="customer_left.jsp"%>
<!--样式-->
<div class="user_info_p_s  clearfix">
    <!--订单记录-->
    <c:choose>
        <c:when test="${maxSize<=0}">
    <div class="left_user_cont">
        <div class="us_Orders left clearfix">
            <div  class="Orders_name">
                <script defer type="text/javascript">
                    function viewhistory() {
                        document.getElementById("a11").href ="/viewBuyHistory/dailySearch?page=1&day="+document.getElementById("day1").value+"&types="+document.getElementById("types1").value;
                    }
                </script>

                <input type="date" id="day1"/>
                <select  id="types1" style="height:21px">
                    <option value="daily">current Day</option>
                    <option value="weekly">current week</option>
                    <option value="monthly">current Month</option>
                    <option value="yearly">current Year</option>
                </select>
                <a id="a11" onclick="viewhistory()">
                    <font style="font-family:'Microsoft Yahei';font-size: medium;color:orangered;">
                        search
                    </font>
                </a>

            </div>
        </div>
    </div>
        </c:when>
        <c:when test="${maxSize>0}">
            <div: class="left_user_cont">
                <div class="us_Orders left clearfix">
                    <div  class="Orders_name">
                        <script defer type="text/javascript">
                            function viewhistory() {
                                document.getElementById("a1").href ="/viewBuyHistory/dailySearch?page=1&day="+document.getElementById("day").value+"&types="+document.getElementById("types").value;
                            }
                        </script>

                        <input type="date" id="day"/>
                        <select  id="types" style="height:21px">
                            <option value="daily">current Day</option>
                            <option value="weekly">current week</option>
                            <option value="monthly">current Month</option>
                            <option value="yearly">current Year</option>
                        </select>
                        <a id="a1" onclick="viewhistory()">
                            <font style="font-family:'Microsoft Yahei';font-size: medium;color:orangered;">
                                search
                            </font>
                        </a>

                    </div>
                    <div class="Orders_name">
                        <font style="font-family:'Microsoft Yahei';font-size: medium;color:orangered;">Purchase History[${descr}]</font>
                    </div>
                    <table>
                        <thead>
                        <tr>
                            <th>Product Name</th>
                            <th>Amount</th>
                            <th>State</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="orders"  items="${ordersEntityList}">
                            <tr>
                                <td class="img_link">
                                    <a href="#" class="img"><img src="${orders.getPhoto()}" width="80" height="80"/></a>
                                    <a href="#" class="title">${orders.getGoodsName()}</a>
                                </td>
                                <td>${orders.getAmount()}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${orders.getState()==-1}">deleted</c:when>
                                        <c:when test="${orders.getState()==1}">Processing Orders</c:when>
                                        <c:when test="${orders.getState()==2}">Preparing for Shippment</c:when>
                                        <c:when test="${orders.getState()==3}">Shipped</c:when>
                                        <c:when test="${orders.getState()==4}">Complete</c:when>
                                        <c:when test="${orders.getState()==5}">Commented</c:when>
                                    </c:choose>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <div class="pagination">
                        <ul>
                            <script>
                                function getUrl() {
                                    var url=window.location.href;
                                    var qs=url.split("?");
                                    var q=qs[1].split("&");
                                    var args="page="+document.getElementById("jumps").value;
                                    if(q)
                                    {
                                        for(var i=1;i<q.length;i++)
                                        {
                                            args=args+"&"+q[i];
                                        }
                                    }
                                    document.getElementById("a12").href ="/viewBuyHistory/dailySearch?"+args;
                                }
                            </script>
                            <c:choose>
                                <c:when test="${page>1}"> <li><a href="/viewBuyHistory/dailySearch?page=${page-1}&day=${day}&types=${types}"> <span>Previous</span></a></li></c:when>
                            </c:choose>
                            <li><span class="currentpage">${page} of ${sina}</span></li>
                            <c:choose>
                                <c:when test="${page<sina}"> <li><a href="/viewBuyHistory/dailySearch?page=${page+1}&day=${day}&types=${types}"> <span>Next</span> </a></li></c:when>
                            </c:choose>

                            <li>
                                <input type="number" id="jumps" name="jump" min="1" max="${sina}" value="1">
                                <a id="a12" onclick="getUrl()">jump</a>
                            </li>
                        </ul>

                    </div>
                </div>
            </div:>
        </c:when>

    </c:choose>

</div>
</div>

</body>
</html>
