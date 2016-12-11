<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>卖家管理页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <form action="" method="get">
    	<table width="100%" border="0" cellspacing="0">
  <tr>
    <td width="75" height="25" bgcolor="#deedf8"></td>
    <td width="1122" align="left" valign="top" bgcolor="#deedf8"><span class="text_cray1">当前位置: 卖家管理页面</span></td>
    <td width="140" valign="top" bgcolor="#deedf8" class="text_cray1">欢迎您，${ sessionScope.user.username }</td>
    <td width="64" align="left" valign="top" bgcolor="#deedf8"><a href="/owner/logout" target="_blank" class="cray">退出</a></td>
  </tr>
</table>
    </form>
  </body>
</html>
