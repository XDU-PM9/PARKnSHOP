<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户密码修改</title>
  <script language="javascript">
      function Cancel(){
          window.location.href="/owner/OwnerInformation";
      }
  </script>
<link href="../css/css.css" rel="stylesheet" type="text/css">
</head>

<body class="write_bg">
 <form name="form2" method="post" action="/owner/ownerPassword_Edit">

<table width="835" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="744" height="30" align="left" class="text_blod_title">密码修改</td>
  </tr>

  <tr>
    <td colspan="2"  ><table width="700" border="0" align="center" cellspacing="0">
     
      <tr>
        <td width="100" height="40" class="text_cray1">原密码：</td>
        <td align="left" class="text_cray1"><input name="oldPassword" type="text" class="text_cray" id="textfield5" size="30" /></td>
      </tr>
      <tr>
        <td width="100" height="40" class="text_cray1">新密码：</td>
        <td align="left" class="text_cray1"><input name="newPassword" type="text" class="text_cray" id="textfield6" size="30" /></td>
      </tr>
      <tr>
        <td width="100" height="40" class="text_cray1">确认新密码：</td>
        <td align="left" class="text_cray1"><input name="confirmpwd" type="text" class="text_cray" id="textfield7" size="30" /></td>
      </tr>
    </table>
      <br></td>
  </tr>
</table>
<table width="700" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td align="center"><input name="button" type="submit" id="button"value="submit"></td>
    <td align="center"><input name="button1" type="button" id="button1" value="cancel" onClick="Cancel()"></td>
    <td align="center"><input name="button2" type="reset" id="button2" value="reset"></td>
    </tr>

  <tr>
    <%
      String msg = String.valueOf(request.getAttribute("msg"));
      if (msg.equals("null")){
        msg = "";
      }

    %>

    <p><%=msg%></p>
  </tr>
</table>


</form>
</body>
</html>
