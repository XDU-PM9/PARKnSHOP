
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.text.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户信息修改</title>
    <script language="javascript">
        function Cancel(){
            window.location.href="/owner/OwnerInformation";
        }
    </script>
<link href="../css/css.css" rel="stylesheet" type="text/css">

</head>

<body class="write_bg">


<form name="form1" method="post" action="/owner/OwnerInfo_Edit">

<table width="835" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="786" height="30" align="left" class="text_blod_title">修改个人信息</td>
  </tr>
  
  <tr>
    <td colspan="2"><table width="100%" border="0" cellspacing="0" cellpadding="0">

    </table>
        <table width="700" border="0" align="center" cellpadding="0" cellspacing="0">
                
          <tr>
            <td width="100" height="40" align="left" class="text_cray1">用户名：</td>
            <td width="350" align="left" class="text_cray1"><input name="textfield1" type="text" disabled="true" class="text_cray" id="textfield1" value="${ sessionScope.user.username }" /></td>
              <td width="230" colspan="-1" rowspan="3" align="center" class="text_cray1"><img src="" width="120">
            <table width="90%" border="0" cellpadding="0" cellspacing="0">
                
                <tr>
                  <td height="7" align="center" class="text_cray">修改头像</td>
                </tr>
                
                <tr>
                  <td align="center"><input name="uploadFile" type="file" class="text_cray" size="20" /></td>
                </tr>
              </table>
            </td>
          </tr>
          <tr>
            <td width="100" height="40" align="left" class="text_cray1">邮箱：</td>
            <td align="left" class="text_cray1"><input name="email" type="text"  class="text_cray" id="textfield2" value="${ sessionScope.user.email }"  /></td>
          </tr>
          
          <tr>
            <td width="100" height="40" align="left" class="text_cray1">电话：</td>
            <td align="left" class="text_cray1"><input name="phone" type="text" class="text_cray" id="textfield3" value="${ sessionScope.user.phone }" />            </td>
          </tr>
            <tr>
            <td width="100" height="40" align="left" class="text_cray1">身份证号码：</td>
            <td width="350" align="left" class="text_cray1"><input name="textfield1" type="text" disabled="true" class="text_cray" id="identityId" value="${ sessionScope.user.identityId }" /></td>
            </tr>
                
        </table><br>
 
        <table width="700" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td width="99" height="30" align="center"><input name="button" type="submit" id="button" value="Save"></td>
              <td width="99" height="30" align="center"><input name="button2" type="button" id="button2" value="取消" onClick="Cancel()"> </td>
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
  </table>

</td>
  </tr>
</table>
</form>
</body>
</html>
