<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2016/12/26
  Time: 18:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <c:forEach var="orders" items="${ordersEntityList}">
        ${orders. getUserByUserId().getUsername()}
    </c:forEach>
    <div class="pagination">
        <ul>
            <script>
                function getUrl() {
                    var url=window.location.href;
                    var qs=url.split("?");
                    var q=qs[1].split("&");
                    var args="page="+document.getElementById("jumps").value;
                    if(q)
                    {
                        for(var i=1;i<q.length;i++)
                        {
                            args=args+"&"+q[i];
                        }
                    }
                    document.getElementById("a12").href ="/viewBuyHistory/dailySearch?"+args;
                }
            </script>
            <c:choose>
                <c:when test="${page>1}"> <li><a href="/viewBuyHistory/dailySearch?page=${page-1}&day=${day}&types=${types}"> <span>Previous</span></a></li></c:when>
            </c:choose>
            <li><span class="currentpage">${page} of ${sina}</span></li>
            <c:choose>
                <c:when test="${page<sina}"> <li><a href="/viewBuyHistory/dailySearch?page=${page+1}&day=${day}&types=${types}"> <span>Next</span> </a></li></c:when>
            </c:choose>

            <li>
                <input type="number" id="jumps" name="jump" min="1" max="${sina}" value="1">
                <a id="a12" onclick="getUrl()">jump</a>
           </li>
        </ul>

    </div>
</body>
</html>
