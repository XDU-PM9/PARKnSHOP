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
       <div class="collect_style r_user_style">
        <div class="title_Section"><span  class="title_ASD">User Collect Product</span></div>
         <div class="collect">
              <ul class="Quantity"><li> collection's Num：
             ${maxSize}
              	items</li><li></li></ul>
          <div class="collect_list">
      <ul>
      	
      <c:forEach var="c" items="${Collects}">
      	
		       <li class="collect_p">
		        <a href="removeCollect?collectionId=${c.getCollectionId()}">
		        <em class="iconfont icon-close2 delete"></em>
		        </a>
		         <a href="#" class="buy_btn">Buy Away</a>


		       <div class="collect_info">

		        <div class="img_link">
		         <a href="#" class="center ">
					 <img src="${c.getPicture()}"/>
		         ${c.getGoodsByGoodsId().getIntroduction()}
				 </a>
		        </div>
		        <dl class="xinxi">
		         <dt><a href="#" class="name">${c.getGoodsByGoodsId().getGoodsName()}</a></dt>
		         <dd><span class="Price"><b>￥</b>${c.getGoodsByGoodsId().getPrice() }</span><span class="collect_Amount"><i class="iconfont icon-shoucang"></i>13</span></dd>
		        </dl>
		        </div>
		       </li>
       
      </c:forEach>
      </ul>
     </div>
			     <div class="Paging">
					    <div class="Pagination">
							<c:choose>
								<c:when test="${currentPage==1}"><a class="pn-prev disabled">&lt;Previous</a></c:when>
								<c:when test="${currentPage>1}"><a href="/listCollect?requestPage=${currentPage-1}" class="pn-prev disabled">&lt;Previous</a></c:when>
							</c:choose>
							<c:forEach begin="1" end="${sina+1}" step="1" var="cs">
								<a href="/listCollect?requestPage=${cs}" class="on">${cs}Page</a>
							</c:forEach>
							<c:if test="${currentPage==sina+1}">
								<a  class="pn-prev disabled">
									Next&gt;
								</a>
							</c:if>
							<c:if test="${currentPage<=sina}">
								<a href="/listCollect?requestPage=${currentPage+1}">
									Next&gt;
								</a>
							</c:if>

					     </div>
			    </div>
      </div>
     </div>   
    </div>
    </div>
</div>

</body>
</html>