
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.text.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
    <script language="javascript">
        function Cancel(){
            window.location.href="/owner/ownerInformation";
        }
    </script>

</head>

<body class="write_bg">


<form name="form" method="POST" enctype="multipart/form-data" action="/owner/ownerInfoEdit">

    <input name="username" type="text" disabled="true" class="text_cray" id="textfield1" value="${ sessionScope.user.username }" />
    <img src="${sessionScope.user.userImage}">
    <input name="userImage" type="file" class="text_cray" size="20" accept="image/png,image/gif,image/jpeg"/>
    <input name="email" type="text"  class="text_cray" id="textfield2" value="${ sessionScope.user.email }"/>
    <input name="phone" type="text" class="text_cray" id="textfield3" value="${ sessionScope.user.phone }"/>
    <input name="id" type="text" disabled="true" class="text_cray" id="identityId" value="${ sessionScope.user.identityId }" />
    <input name="button" type="submit" id="button" value="Save">
    <input name="button2" type="button" id="button2" value="取消" onClick="Cancel()">
</form>
</body>
</html>
