<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="/resources/css/owner/main.css">
    <link rel="stylesheet" href="/resources/libs/iconfont.css">
</head>
<body>
    <div class="personInfor">
        <ul>
            <li id="myPhoto">
                <img src="${ sessionScope.user.userImage }" alt="My photo">

            </li>
            <li id="myName"><i class="iconfont">&#xe60d;:</i>${ sessionScope.user.username }</li>
            <li id="myPhone"><i class="iconfont">&#xe604;:</i>${ sessionScope.user.phone }</li>
            <li id="myEmail"><i class="iconfont">&#xe649;:</i>${ sessionScope.user.email }</li>
            <li></li>
        </ul>
    </div>
    <div class="changeInfor">
        <a href="/owner/ownerInfoEdit" class="button lightbg-blue clearfix"><span>Update infor</span>
            <div class="icon">
                <div class="arrow"></div>
            </div>
        </a>
        <a href="/owner/ownerPasswordEdit" class="button lightbg-blue clearfix"><span>Modify password</span>
            <div class="icon">
                <div class="arrow"></div>
            </div>
        </a>
    </div>
    <script type="text/javascript" src="../../../resources/libs/jquery.js"></script>
    <script type="text/javascript" src=""></script>
</body>
</html>