
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import=" java.text.*"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>查看个人信息</title>
<script language="javascript">
	function UpdateInfo(){
	    window.location.href="/owner/OwnerInfo_Edit";
	}

    function UpdatePassword(){
        window.location.href="/owner/ownerPassword_Edit";
    }
</script>
<link href="../css/css.css" rel="stylesheet" type="text/css">

</head>
<body class="write_bg">

<form name="form1" method="post" action="">

  <table width="835" border="0" align="center" cellpadding="0" cellspacing="0">
 
  <tr>
    <td width="771" height="30" align="left" valign="top" ><span class="text_blod_title">查看个人信息</span></td>
  </tr>
  
  <tr>
    <td colspan="2" valign="top"  ><table width="700" border="0" align="center" cellpadding="0" cellspacing="0">
          
          <tr>
            <td width="100" height="40" align="left" class="text_cray1">用户名：</td>
            <td width="350" align="left" class="text_cray">${ sessionScope.user.username }</td>
            <td width="230" rowspan="5" align="center" class="text_cray"><img src="" width="139" height="139"></td>
          </tr>

        <tr>
            <td width="100" height="40" align="left" class="text_cray1">身份证号码：</td>
            <td width="left" class="text_cray">${ sessionScope.user.identityId }</td>

        </tr>

          <tr>
            <td width="100" height="40" align="left" class="text_cray1">邮箱：</td>
            <td align="left" class="text_cray">${ sessionScope.user.email }</td>
          </tr>

          <tr>
            <td width="100" height="40" align="left" class="text_cray1">电话：</td>
            <td align="left" class="text_cray">${ sessionScope.user.phone }</td>
          </tr>

        </table><br>

      <table width="263" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="30" align="center"><input name="button1" type="button" id="button1" value="Update Information" onClick="UpdateInfo()"></td>
          <td height="30" align="center"><input name="button2" type="button" id="button2" value="Update Password" onClick="UpdatePassword()"></td>
          </tr>

      </table>
  </table>
</form>
</body>
</html>
