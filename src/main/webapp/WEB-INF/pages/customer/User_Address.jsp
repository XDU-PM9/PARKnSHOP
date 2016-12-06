<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>     
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
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


<title>资金管理</title>
</head>

<body>

<div class="user_style   clearfix">
	<%@include file="User_Left.jsp" %>

    <div class="right_style">
        <div class="info_content">
            <!--地址管理样式-->
            <div class="adderss_style">

                <div class="title_Section"><span  class="title_ASD">收货地址管理</span></div>
                <div class="adderss_list">
                    <!--地址列表-->
                    <div class="Address_List clearfix">
                        <!--地址类表-->

                        <c:forEach var="addr" items="${addressEntityList}">
                            <ul class="Address_info">
                                <div class="address_title">
                                    <a href="editAddress?addressId=${addr.getAddressId()}" class="modify iconfont icon-fankui btn btn-primary" title="修改信息"  id="purebox">
                                    </a> 地址信息
                                    <a href="/deleteAddress?addressId=${addr.getAddressId()}" class="delete">
                                        <i class="iconfont icon-close2"></i>
                                    </a>
                                </div>
                                <li>${addr.getName()}</li>
                                <li>${addr.getProvince()} ${addr.country}  ${addr.getOthers()}</li>
                                <li>${addr.getPhone()}</li>
                                <li>${addr.getPostcode()}</li>
                            </ul>
                        </c:forEach>

                    </div>
                </div>
                <form action="/saveAddress" method="post">
                    <input type="hidden" name="userId" value="6"/>
                    <div class="Add_Addresss">
                        <div class="title_name"><i></i>添加地址</div>
                        <table>
                            <tbody><tr>
                                <td class="label_name">收货区域</td>
                                <td colspan="3" class="select">

                                    <label> 省份 </label>  <select name="province" onChange = "hw_select()"></select>
                                    <label> 市/县 </label>   <select name="country" onChange = "hw_select()"></select>
                                </td>
                            </tr>
                            <tr><td class="label_name">详细地址</td><td><input name="others" type="text" class="Add-text"><i>（必填）</i></td>
                                <td class="label_name">收件人姓名</td><td><input name="name" type="text" class="Add-text"><i>（必填）</i></td>

                            </tr>
                            <tr>
                                <td class="label_name">Zip Code</td><td><input name="zip" type="text" class="Add-text"><i>（必填）</i></td>
                                <td class="label_name">Phone </td><td><input name="phoneNum" type="text" class="Add-text"><i>（必填）</i></td>
                            </tr>

                            </tbody></table>
                        <div class="address_Note"><span>注：</span>只能添加3个收货地址信息。请乎使用假名填写地址，如造成损失由收货人自己承担。</div>
                        <div class="btn"><input name="1" type="submit" value="添加地址" class="Add_btn"></div>

                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>

