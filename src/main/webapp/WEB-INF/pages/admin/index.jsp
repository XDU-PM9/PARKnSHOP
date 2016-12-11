<%--
  Created by IntelliJ IDEA.
  User: niewenzhi
  Date: 2016/12/11
  Time: 15:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<frameset rows="30,*" cols="*" frameborder="no" border="0" framespacing="0">
    <frame src="/resources/pages/admin/top.jsp" name="topFrame" scrolling="No" noresize="noresize" id="topFrame" title="topFrame">
    <frameset rows="*" cols="247,*" framespacing="0" frameborder="no" border="0">
        <frame src="/resources/pages/admin/left.jsp" name="leftFrame" scrolling="No" noresize="noresize" id="leftFrame" title="leftFrame">
        <frame src="/resources/pages/admin/main.jsp" name="mainFrame" id="mainFrame" title="mainFrame">
    </frameset>
</frameset>
</body>
</html>
