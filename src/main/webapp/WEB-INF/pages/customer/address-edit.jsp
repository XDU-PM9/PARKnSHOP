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
             <div class="title_name"><i></i>修改地址</div>
             <table>
              <tbody><tr>
               <td class="label_name">收货区域</td>
				
               <td colspan="3" class="select">
               
                <label> 省份 </label><select name="province" onChange = "hw_select()"></select>
                <label> 市/县 </label><select name="country" onChange = "hw_select()"></select>
         
               </td>
               </tr>
               
               <tr><td class="label_name">详细地址</td><td><input name="others" type="text"  value="${addressEntity.getOthers()}" class="Add-text"><i>（必填）</i></td>
                <td class="label_name">Zip Code</td><td><input name="zip"  value="${addressEntity.getPostcode()}"  type="text" class="Add-text"><i>（必填）</i></td></tr>
              <tr>
              <td class="label_name">收件人姓名</td><td><input name="name" value="${addressEntity.getName()}"  type="text" class="Add-text"><i>（必填）</i></td>
              <td class="label_name">Phone Num</td><td><input name="phoneNum"  value="${address.getPhone()}"  type="text" class="Add-text"><i>（必填）</i></td>
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

</body>
</html>