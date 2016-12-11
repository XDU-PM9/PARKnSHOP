
<!DOCTYPE html>
<html lang="zh-CN" ng-app="PNS">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1 , user-scalable=no">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
    <title></title>
    <link rel="stylesheet" href="/resources/css/owner/index.css">
</head>

<body>
<div class="topMain">
    <div class="logo">
        <a href="#"><img src="/resources/images/ico/banner.jpg" alt="" width="120" height="40"></a>
    </div>
    <div class="topHead">
        <ul class="headList">
            <li><span>Welcome,</span>${sessionScope.user.username}</li>
            <li><span>Current position:</span><span id="position">Saler's Management Page</span></li>
            <li><a href="/owner/logout" target="_parent">Quit</a></li>
        </ul>
    </div>
    <!--<form action="" method="get">
        <table width="100%" border="0" cellspacing="0">
            <tr>
                <td width="75" height="25" bgcolor="#deedf8"></td>
                <td width="1122" align="left" valign="top" bgcolor="#deedf8"><span class="text_cray1">Current Position: 卖家管理页面</span></td>
                <td width="140" valign="top" bgcolor="#deedf8" class="text_cray1">欢迎您，${ sessionScope.user.username }</td>
                <td width="64" align="left" valign="top" bgcolor="#deedf8"><a href="" target="_blank" class="cray">退出</a></td>
            </tr>
        </table>
    </form>-->
</div>
</body>
<!--<script>
    window.onload = function () {
        var str = document.title;
        document.getElementById("position").innerHTML = str;
    }
</script>-->
</html>
