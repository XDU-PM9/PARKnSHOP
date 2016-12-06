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
	     <!--个人信息-->
	      <div class="Personal_info" id="Personal">
	         <div class="title_Section"><span>个人信息</span></div>
	         <ul class="xinxi">
				 <li><label>Name：</label> <span><input name="name" type="text" value="${user.getUsername()}"  class="text"  disabled="disabled"/></span></li>
				 <li><label>Email：</label>  <span>${user.getEmail()}</span></li>
				 <li><label>Phone：</label>  <span>${user.getPhone()}</span></li>
	          <div class="bottom">
				  <input name="" type="submit" value="修改信息"  class="modify"/>
				  <input name="" type="submit" value="确认修改"  class="confirm"/></div>
	         </ul>
	          <form action="" method="post">
	         <ul class="Head_portrait">


				    <li class="User_avatar"><img src="${user.getUserImage()}" width="210px" height="210px"/></li>

	          		<li><input name="name" type="submit" value="上传头像"  class="submit"/></li>
	         </ul>
	          </form> 
	      </div>
	    </div>
	    </div>
	   </div>
	</div>
</body>
</body>
</html>