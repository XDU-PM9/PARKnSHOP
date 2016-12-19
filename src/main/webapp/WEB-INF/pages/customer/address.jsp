<%--
  Created by IntelliJ IDEA.
  User: H
  Date: 2016/12/17
  Time: 10:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>Manage Address</title>
    <link rel="stylesheet" href="../../../resources/css/base.css" type="text/css" />
    <link rel="stylesheet" href="../../../resources/css/shop_common.css" type="text/css" />
    <link rel="stylesheet" href="../../../resources/css/shop_header.css" type="text/css" />
    <link rel="stylesheet" href="../../../resources/css/shop_manager.css" type="text/css" />
    <link rel="stylesheet" href="../../../resources/css/shop_shdz_835.css" type="text/css" />
    <script type="text/javascript" src="../../../resources/js/jquery.js" ></script>
    <script type="text/javascript" src="../../../resources/js/topNav.js" ></script>
</head>
<body>
<%@include file="head.jsp"%>
<!-- Header End -->

<!-- 我的个人中心 -->
<div class="shop_member_bd clearfix">
    <!-- 左边导航 -->
   <%@include file="customer_left.jsp"%>
    <!-- 左边导航 End -->

    <!-- 右边购物列表 -->
    <div class="shop_member_bd_right clearfix">

        <div class="shop_meber_bd_good_lists clearfix">
            <div class="title"><h3>Manage Address<a style="float:right;" href="javasrcipt:void(0);" id="new_add_shdz_btn">Add Address</a></h3></div>
            <div class="clear"></div>
            <!-- 收货人地址 Title End -->
            <div class="shop_bd_shdz clearfix">
                <div class="shop_bd_shdz_lists clearfix">
                    <ul>
                        <c:forEach var="addr" items="${addressEntityList}">
                        <li>
                            <label><span><input type="radio" name="shdz" /></span></label>
                            <em>Hong Kong</em><em>${addr.getProvince()}</em><em>${addr.country}</em>
                            <em>${addr.getOthers()}</em><em>${addr.getName()}(receive)</em><em>${addr.getPhone()}</em>
                            <span class="admin_shdz_btn">
                                <a href="/editAddress?addressId=${addr.getAddressId()}">edit</a>
                                <a href="/deleteAddress?addressId=${addr.getAddressId()}">delete</a>
                            </span>
                        </li>
                            </c:forEach>

                    </ul>
                </div>
                <!-- 新增收货地址 -->
                <div id="new_add_shdz_contents" style="display:none;" class="shop_bd_shdz_new clearfix">
                    <div class="title">Add a Address</div>
                    <div class="shdz_new_form">
                        <form action="/saveAddress" method="post">
                            <ul>
                                <li><label ><span>*</span>User Name：</label>
                                    <input type="text" name="name" class="name" required/>
                                </li>
                                <li><label><span>*</span>Province：</label> <select name="province" onChange = "hw_select()" required></select>
                                <span style="color: red;margin-left:20px;">*</span>country：</label><select name="country" onChange = "hw_select()" required></select></li>
                                <li><label ><span>*</span>Detail：</label><input name="others"  type="text" class="xiangxi" required /></li>
                                <li><label ><span>*</span>Zip Code：</label><input type="text" pattern="[0-9]{6}" name="zip" class="youbian" required/></li>
                                <li><label ><span>*</span>Phone Num：</label><input type="tel" pattern="[0-9]{8}|^[1][3578][0-9]{9}$"  name="phoneNum" class="shouji" required /></li>
                                <li><label>&nbsp;</label><input type="submit" value="Add a address" /></li>
                            </ul>
                        </form>
                    </div>

                </div>
                <!-- 新增收货地址 End -->
            </div>
            <div class="clear"></div>
        </div>
    </div>
    <!-- 右边购物列表 End -->

</div>
<!-- 我的个人中心 End -->

<!-- Footer - wll - 2013/3/24 -->
<div class="clear"></div>
<div class="shop_footer">

    <div class="shop_footer_copy">
        <p>Copyright 2004-2013 itcast Inc.,All rights reserved.</p>
    </div>
</div>
<!-- Footer End -->
<script type="text/javascript">
    jQuery(function(){
        jQuery("#new_add_shdz_btn").toggle(function(){
            jQuery("#new_add_shdz_contents").show(500);
        },function(){
            jQuery("#new_add_shdz_contents").hide(500);
        });
    });
</script>
<script language="JavaScript">
    var hw_selecttext =
        "HK|Aberdeen^Admiralty^Ap Lei Chau^Big Wave Bay^Causeway Bay^Central^Central sheung Wan^Central South^Chai Wan^Gloucester Road^Happy Valley^Harbour Road^Jardine's Lookkout^Kennedy Town^Lai Tak Tsuen^Mid-Levels^Mid-Levels West^North Point^Pok Fu Lam^Quarry Bay^Sai Wan^Shau Kei Wan^Shek O^Sheung Wan West^Siu Sai Wan^So Kon Po^Southern District^Stanley^Tai Hang Road^The Peak^Tim Mei Ave^Tin Hau^Wah Fu^Wan Chai^Wong Chuk Hang*" +
        "KLN|Cheung Sha Wan^Choi Wan^Diamond Hill^Ho Man Tin^Hung Hom^Jordan^Jordan Road^Kowloon Bay^Kowloon City^Kowloon Tong^kwun Tong^La Salle Road^Lai Chi Kok^Lam Tin^Lok Fu^Ma Tau Wai^Mei Foo^Mong Kok^Ngau Chi Wan^Ngau Tau Kok^Rainbow Village^San Po Kong^Sau Mau Ping^Sham Shui Po^Shek Kip Mei^Tai Kok Tsui^To Kwa Wan^Tsim Sha Tsui^Tsz Wan Shan^Wong Tai Sin^Yau Ma Tei^Yau Tong*" +
        "NT|Chek Lap Kok^Chinese University^Clear Water Bay^Discovery Bay^Fairview Park^Fanling^Fo Tan^HKUST^Kwai Chung^Long Ping^Ma On Shan^Ma Wan^Pat Heung^Sai Kung (North)^Sai Kung (South)^Science Park^Sha Tin^Sheung Shui^Siu Lek Yuen^Tai Po^Tai Wai^Tin Shui Wai^Tseung Kwan O^Tsing Yi^Tsuen Wan^Tuen Mun^Tuen Mun^Wu Kai Sha^Yuen Long"
    var TheSplit1 = "*",TheSplit2 = "|",TheSplit3 = "^", TheSplit4 = "@"
    var hwallselecttext = hw_selecttext
    var hwdefault_value = "On Select"
    var hwallselecttextarr
    hwallselecttextarr = hwallselecttext.split(TheSplit1)
    hwArraylength = hwallselecttextarr.length
    var hwwhere = new Array(hwArraylength);
    hwwhere[0]= new hw_comefrom("Select Area@","Select Distinct@");

    for (var hwl=0;hwl<hwArraylength;hwl++) {
        eval(hwwhere[hwl+1] = new hw_comefrom(hwallselecttextarr[hwl].split(TheSplit2)[0],hwallselecttextarr[hwl].split(TheSplit2)[1]))
    }
    function hw_comefrom(hwSelect_s1,hwSelect_s2) { this.hwSelect_s1 = hwSelect_s1; this.hwSelect_s2 = hwSelect_s2; }
    function hw_select() {
        with(document.all.province) {
            var hwSelect_s12 = options[selectedIndex].value;
        }
        for(hwi = 0;hwi < hwwhere.length;hwi ++) {
            if (hwwhere[hwi].hwSelect_s1.indexOf(TheSplit4)!=-1) {
                var hwThisV = hwwhere[hwi].hwSelect_s1.split(TheSplit4)[1]
            }
            else {
                var hwThisV = hwwhere[hwi].hwSelect_s1
            }
            if (hwThisV == hwSelect_s12) {
                hwSelect_s13 = (hwwhere[hwi].hwSelect_s2).split(TheSplit3);
                for(hwj = 0;hwj < hwSelect_s13.length;hwj++) {
                    with(document.all.country) {
                        length = hwSelect_s13.length;
                        if (hwSelect_s13[hwj].indexOf(TheSplit4)!=-1) {
                            options[hwj].text = hwSelect_s13[hwj].split(TheSplit4)[0]
                            options[hwj].value = hwSelect_s13[hwj].split(TheSplit4)[1]
                        }
                        else {
                            options[hwj].text = hwSelect_s13[hwj];
                            options[hwj].value = hwSelect_s13[hwj];
                        }
                        var hwSelect_s14=options[selectedIndex].value;
                    }
                }
                break;
            }
        }
        document.all.hw.value=hwSelect_s12+""+hwSelect_s14;
    }
    function hw_init() {
        with(document.all.province) {
            length = hwwhere.length;
            var hwm = 0
            for(hwk=0;hwk<hwwhere.length;hwk++) {
                if (hwwhere[hwk].hwSelect_s1.indexOf(TheSplit4)!=-1) {
                    options[hwk].text = hwwhere[hwk].hwSelect_s1.split(TheSplit4)[0];
                    options[hwk].value = hwwhere[hwk].hwSelect_s1.split(TheSplit4)[1];
                    if (hwdefault_value.indexOf(hwwhere[hwk].hwSelect_s1.split(TheSplit4)[1])!=-1){hwm = hwk}
                }
                else {
                    options[hwk].text = hwwhere[hwk].hwSelect_s1;
                    options[hwk].value = hwwhere[hwk].hwSelect_s1;
                    if (hwdefault_value.indexOf(hwwhere[hwk].hwSelect_s1)!=-1){hwm = hwk}
                }
            }
            selectedIndex = hwm
        }
        with(document.all.country)
        {
            var hwn = 0
            hwSelect_s13 = (hwwhere[hwm].hwSelect_s2).split(TheSplit3);
            length = hwSelect_s13.length;
            for(hwl=0;hwl<length;hwl++) {
                if (hwSelect_s13[hwl].indexOf(TheSplit4)!=-1) {
                    options[hwl].text = hwSelect_s13[hwl].split(TheSplit4)[0];
                    options[hwl].value = hwSelect_s13[hwl].split(TheSplit4)[1];
                    if (hwdefault_value.indexOf(hwSelect_s13[hwl].split(TheSplit4)[1])!=-1){hwn = hwl}
                }
                else {
                    options[hwl].text = hwSelect_s13[hwl];
                    options[hwl].value = hwSelect_s13[hwl];
                    if (hwdefault_value.indexOf(hwSelect_s13[hwl])!=-1){hwn = hwl}
                }
            }
            selectedIndex = hwn
        }
    }
    hw_init();
</script>
</body>
</html>
