
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import=" java.text.*"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Personal infomation</title>
<script language="javascript">
	function UpdateInfo(){
	    window.location.href="/owner/OwnerInfo_Edit";
	}

    function UpdatePassword(){
        window.location.href="/owner/ownerPassword_Edit";
    }
</script>
</head>
<body>
            <p>${ sessionScope.user.username }</p>
            <img src="${sessionScope.user.userImage}">
            <img src="/resources/images/a.png">
            <p>${ sessionScope.user.identityId}</p>
            <p>${ sessionScope.user.email }</p>
            <p>${ sessionScope.user.phone }</p>
            <a  href="/owner/ownerInfoEdit">Update Information</a>
            <a  href="/owner/ownerPasswordEdit">Update Password</a>
</body>
</html>
