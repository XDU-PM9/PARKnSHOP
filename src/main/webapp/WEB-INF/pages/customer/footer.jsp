<%--
  Created by IntelliJ IDEA.
  User: H
  Date: 2016/12/20
  Time: 13:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- Footer - wll - 2013/3/24 -->
<div class="clear"></div>
<div class="shop_footer">
    <div class="shop_footer_copy">
        <p>©Copyright 2004-2016 ParknShop.com.,All rights reserved.</p>
    </div>
</div>

<div>
    <script defer type="text/javascript">
        function viewhistory() {
            document.getElementById("a1").href ="/viewBuyHistory/dailySearch?page=1&day="+document.getElementById("day").value+"&types="+document.getElementById("types").value;
        }

    </script>

    <input type="date" id="day"/>
    <select  id="types">
        <option value="daily">current Day</option>
        <option value="weekly">current week</option>
        <option value="monthly">current Month</option>
        <option value="yearly">current Year</option>
    </select>
    <a id="a1" onclick="viewhistory()">search</a>

</div>
<!-- Footer End -->
