<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<frameset rows="51,*" framespacing="0" border="0">
    <frame src="/admin/top" id="header-frame" name="header-frame" frameborder="no" scrolling="no">
    <frameset cols="180,*" framespacing="0" border="0" id="frame-body">
        <frame src="/admin/menu" id="menu-frame" name="menu-frame" frameborder="no" scrolling="yes">
        <frame src="/admin/main" id="main-frame" name="main-frame" frameborder="no" scrolling="yes">
    </frameset>
</frameset>
<frameset rows="0, 0" framespacing="0" border="0">
    <frame src="http://api.ecshop.com/record.php?mod=login&url={$shop_url}" id="hidd-frame" name="hidd-frame" frameborder="no" scrolling="no">
</frameset>
<body>
</body>
</html>