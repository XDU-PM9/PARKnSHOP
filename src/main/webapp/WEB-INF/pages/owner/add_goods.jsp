<%--
  Created by IntelliJ IDEA.
  User: fallb
  Date: 2016/12/11
  Time: 20:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="/owner/addGoods" enctype="multipart/form-data" method="post" id="goods_form">
    <inpul>
        <input type="text" name="name" id="name">test</inpul>
    </div>

    <%--请把这项设置为只可以输入小数--%>
    <div>
        <input type="number" name="price" id="price"/>
    </div>

    <div>
        <input type="number" name="inventory" id="inventory">
    </div>

    <div>
        <input type="text" name="desc" id="desc">
    </div>

    <div>
        <select name="goods_type" form="goods_form">
            <option value="TV& Home Theater">TV& Home Theater</option>
            <option value="Computers & Tablets">Computers & Tablets</option>
            <option value="Cell Phones">Cell Phones</option>
            <option value="Cameras & Camcorders">Cameras & Camcorders</option>
            <option value="Audio">Audio</option>
            <option value="Car Electronics & GPS">Car Electronics & GPS</option>
            <option value="Video, Games, Movies & Music">Video, Games, Movies & Music</option>
            <option value="Health, Fitness & Sports">Health, Fitness & Sports</option>
            <option value="Home & Office">Home & Office</option>
        </select>
    </div>

    <div>
        <select name="post_way" form="goods_form">
            <option value="包邮">包邮</option>
            <option value="不包邮">不包邮</option>
        </select>
    </div>

    <%--图片的显示--%>
    <div>
        <input type="file" name="photo" id="photo" accept="image/jpeg,image/gif,image/png" multiple/>
    </div>

    <div>
        <input type="submit" value="提交"/>
    </div>
</form>



</body>
</html>
