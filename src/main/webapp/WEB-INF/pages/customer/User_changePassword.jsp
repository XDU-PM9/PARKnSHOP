<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
    <link rel="Bookmark" href="resources/images/ico/PARKico.png">
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
	
	<!--右侧样式-->
   <div class="right_style">
     <div class="info_content">
      <!--修改密码样式-->
       <div class="change_Password">
       	<form  action="changePassword" method="post">
       		<input type="hidden" name="userId" value="6"/>
           <div class="title_Section"><span  class="title_ASD">修改密码</span></div>
           <ul class="p_modify">
              <div class="Note">暂只支持原密码修改，不支持邮箱电话验证密码修改</div>
              <li><label>原密码</label><input name="password" type="password"  class="text_Password"/></li>
              <li class="new_password">
                 <label>新密码</label>
               	 <div class="ywz_zhuce_xiaoxiaobao">
				  <div class="ywz_zhuce_kuangzi" style="margin-left:-11px;"><input name="pass" type="password" id="tbPassword" class="ywz_zhuce_kuangwenzi1 text_Password"></div>
						<div class="ywz_zhuce_huixian" id="pwdLevel_1">弱 </div>
						<div class="ywz_zhuce_huixian" id="pwdLevel_2">中 </div>
						<div class="ywz_zhuce_huixian" id="pwdLevel_3">强 </div>
			     </div>
           		 <div class="ywz_zhuce_yongyu1">
            	<span id="pwd_err" style="color: rgb(255, 0, 0)">6-16位，由字母（区分大小写）、数字、符号组成</span>
		     </div>
              </li>            
              <li><label>确认密码</label><input name="pass1" type="password"  class="text_Password"/></li>
              <li><input  type="submit" class="bnt_blue_1" style="border:none;" value="确认修改"></li>
           </ul>
           </form>
       </div>
       
     </div>
   </div>
    </div>
</body>
</html>