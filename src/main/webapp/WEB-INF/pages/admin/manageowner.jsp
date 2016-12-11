<%--
  Created by IntelliJ IDEA.
  User: niewenzhi
  Date: 2016/12/11
  Time: 19:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<div>

</div>
<table id="tableInfor">
    <tr>
        <th></th>
    </tr>

</table>
<div>

</div>
<script src="/resources/libs/jquery.js"></script>
<script>
    var data={};
    data.size = 5;
    data.index = 1;
    $(function () {
        $.ajax({
            type:'post',
            contentType : 'application/json',
            data: JSON.stringify(data),
            url:'/admin/apply',
            success: function (data) {
                var response =JSON.parse(data);;
                console.log(response);
                var length = response.data.length;
                for(var i=0;i<length;i++){
                    addTr(i);
                    addTd(i,response.data[i].shopId);
                    addTd(i,response.data[i].ownerEmail);
                    addTd(i,response.data[i].ownerEmail);
                    addTd(i,response.data[i].ownerEmail);
                    addTd(i,response.data[i].ownerEmail);
                }
            }
        })
    })
    function addTr(i) {
        var className = i;
        $("#tableInfor").append("<tr class="+className+"></tr>");
    }
    function addTd(i,str) {
        $("."+i+"").append("<td>"+str+"</td>");
    }
</script>
</body>
</html>
