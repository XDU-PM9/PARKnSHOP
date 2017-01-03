<%--
  Created by IntelliJ IDEA.
  User: weina
  Date: 2016/12/27
  Time: 15:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        h1 {
            margin-left: 44%;
        }
        img {
            display: block;
            margin: 0 44%;
            width: 200px;
        }
        button {
            margin: 0 49%;
            padding:10px 19px;
            border-radius:0.3em;
            -webkit-border-radius:0.3em;
            -moz-border-radius:0.3em;
            -o-border-radius:0.3em;
            color:#4D4949;
            background: #d0d0d0;

            border:1px solid #EBEBEB;
            font-weight:bold;
            font-size:15px;
            outline:none;
            transition: all 0.5s ease-out;
            -webkit-transition: all 0.5s ease-out;
            -moz-transition: all 0.5s ease-out;
            -ms-transition: all 0.5s ease-out;
            -o-transition: all 0.5s ease-out;
            margin-top:15px;
            cursor: pointer;
        }
        button:hover{
            background:#2E2D2D;
            color:#fff;
        }
    </style>
</head>
<body>
<H1>this is QRCode</H1><br>
<img src="${imgUrl}"><br>
<button onclick="window.location.href='${path}'">Paid</button>
</body>
</html>
