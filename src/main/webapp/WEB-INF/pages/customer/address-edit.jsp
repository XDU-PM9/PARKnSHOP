<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
</head>
<body>
<div class="user_style   clearfix">
	<%@include file="User_Left.jsp" %>
	  
	 <div class="right_style">
    	 <div class="info_content">
     	<!--地址管理样式-->
      		<div class="adderss_style">
     
        <div class="address_title">
       
        <div class="Add_Addresss">
         <form action="/updateAddress" method="post">
         <input type="hidden" name="addressId" value="${addressEntity.getAddressId()}"/>
         <input type="hidden" name="userId" value="6"/>
             <div class="title_name"><i></i>Modification Address</div>
             <table>
              <tbody><tr>
               <td class="label_name">Receiving Area</td>
				
               <td colspan="3" class="select">
               
                <label> Area </label><select name="province" onChange = "hw_select()"></select>
                <label> Distinct </label><select name="country" onChange = "hw_select()"></select>
         
               </td>
               </tr>
               
               <tr><td class="label_name">Detailed Address</td><td><input name="others" type="text"  value="${addressEntity.getOthers()}" class="Add-text">[not null]</td>
                <td class="label_name">Zip Code</td><td><input name="zip"  value="${addressEntity.getPostcode()}"  type="text" class="Add-text">[not null]</td></tr>
              <tr>
              <td class="label_name">User Name</td><td><input name="name" value="${addressEntity.getName()}"  type="text" class="Add-text">[not null]</td>
              <td class="label_name">Phone Num</td><td><input name="phoneNum"  value="${address.getPhone()}"  type="text" class="Add-text">[not null]</td>
              </tr>
                
             </tbody>
             
             </table>
    
             <div class="btn"><input name="1" type="submit" value="修改地址" class="Add_btn"></div>
           </form>
         </div>
      </div>
     </div> 
   </div>
  </div>
</div>
<script language="JavaScript">
    var hw_selecttext =
        "HK|Aberdeen^Admiralty^Ap Lei Chau^Big Wave Bay^Causeway Bay^Central^Central sheung Wan^Central South^Chai Wan^Gloucester Road^Happy Valley^Harbour Road^Jardine's Lookkout^Kennedy Town^Lai Tak Tsuen^Mid-Levels^Mid-Levels West^North Point^Pok Fu Lam^Quarry Bay^Sai Wan^Shau Kei Wan^Shek O^Sheung Wan West^Siu Sai Wan^So Kon Po^Southern District^Stanley^Tai Hang Road^The Peak^Tim Mei Ave^Tin Hau^Wah Fu^Wan Chai^Wong Chuk Hang*" +
        "KLN|Cheung Sha Wan^Choi Wan^Diamond Hill^Ho Man Tin^Hung Hom^Jordan^Jordan Road^Kowloon Bay^Kowloon City^Kowloon Tong^kwun Tong^La Salle Road^Lai Chi Kok^Lam Tin^Lok Fu^Ma Tau Wai^Mei Foo^Mong Kok^Ngau Chi Wan^Ngau Tau Kok^Rainbow Village^San Po Kong^Sau Mau Ping^Sham Shui Po^Shek Kip Mei^Tai Kok Tsui^To Kwa Wan^Tsim Sha Tsui^Tsz Wan Shan^Wong Tai Sin^Yau Ma Tei^Yau Tong*" +
        "NT|Chek Lap Kok^Chinese University^Clear Water Bay^Discovery Bay^Fairview Park^Fanling^Fo Tan^HKUST^Kwai Chung^Long Ping^Ma On Shan^Ma Wan^Pat Heung^Sai Kung (North)^Sai Kung (South)^Science Park^Sha Tin^Sheung Shui^Siu Lek Yuen^Tai Po^Tai Wai^Tin Shui Wai^Tseung Kwan O^Tsing Yi^Tsuen Wan^Tuen Mun^Tuen Mun^Wu Kai Sha^Yuen Long*"
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