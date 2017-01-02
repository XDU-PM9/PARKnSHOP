<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
    <title></title>
    <link href="/resources/css/admin/menu.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="/resources/libs/iconfont.css">
</head>
<body bgcolor="#efeff0">
<div class="sidebar">
    <ul>
        <li><a href="#" class="headList list1"><i class="iconfont leftIco">&#xe62c;</i>Shop Apply<i class="iconfont rightIco">&#xe613;</i></a></li>
        <li class="hiden">
            <ul>
                <li><a href="/admin/applymanage" target="main-frame"><i class="iconfont leftIco">&#xe620;</i>Apply List</a></li>
                <!--<li><a href="#" target="main-frame"><i class="iconfont leftIco">&#xe620;</i>Person Information</a></li>
                <li><a href="#" target="main-frame"><i class="iconfont leftIco">&#xe620;</i>Show goods</a></li>-->
            </ul>
        </li>
        <li><a href="#" class="headList list2"><i class="iconfont leftIco">&#xe62c;</i>Owner Manage<i class="iconfont rightIco">&#xe613;</i></a></li>
        <li  class="hiden">
            <ul>
                <li><a href="/admin/ownermanage" target="main-frame"><i class="iconfont leftIco">&#xe620;</i>Owner List</a></li>
                <li><a href="/admin/shopmanage" target="main-frame"><i class="iconfont leftIco">&#xe620;</i>Shop List</a></li>
                <!--<li><a href="#"><i class="iconfont leftIco">&#xe620;</i>Main</a></li>-->
            </ul>
        </li>
        <li><a href="#" class="headList list3"><i class="iconfont leftIco">&#xe62c;</i>Customer Manage<i class="iconfont rightIco">&#xe613;</i></a></li>
          <li class="hiden">
              <ul>
                  <li><a href="/admin/customermanage" target="main-frame"><i class="iconfont leftIco">&#xe620;</i>Customer List</a></li>
                  <%--&lt;!&ndash;<li><a href="#"><i class="iconfont leftIco">&#xe620;</i>Main</a></li>
                  <li><a href="#"><i class="iconfont leftIco">&#xe620;</i>Main</a></li>&ndash;&gt;--%>
              </ul>
          </li>
          <li><a href="#" class="headList list4"><i class="iconfont leftIco">&#xe62c;</i>AD Manage<i class="iconfont rightIco">&#xe613;</i></a></li>
          <li class="hiden">
              <ul>
                  <li><a href="/admin/shopadapply" target="main-frame"><i class="iconfont leftIco">&#xe620;</i>ShopAd Apply</a></li>
                  <li><a href="/admin/goodsadapply" target="main-frame"><i class="iconfont leftIco">&#xe620;</i>GoodsAd Apply</a></li>
                  <li><a href="/admin/shopadlist" target="main-frame"><i class="iconfont leftIco">&#xe620;</i>ShopAd List</a></li>
                  <li><a href="/admin/goodsadlist" target="main-frame"><i class="iconfont leftIco">&#xe620;</i>GoodsAd List</a></li>

              </ul>
          </li>
          <li><a href="#" class="headList list5"><i class="iconfont leftIco">&#xe62c;</i>Data Manage<i class="iconfont rightIco">&#xe613;</i></a></li>
          <li class="hiden">
              <ul>
                  <li><a href="/admin/datamanage" target="main-frame"><i class="iconfont leftIco">&#xe620;</i>Backup Manage</a></li>
              </ul>
          </li>

        <li><a href="#" class="headList list6"><i class="iconfont leftIco">&#xe62c;</i>Income Manage<i class="iconfont rightIco">&#xe613;</i></a></li>
        <li class="hiden">
            <ul>
                <li><a href="/admin/incomemanage" target="main-frame"><i class="iconfont leftIco">&#xe620;</i>Rate Setting</a></li>
            </ul>
        </li>
        <li><a href="#" class="headList list7"><i class="iconfont leftIco">&#xe62c;</i>Income List<i class="iconfont rightIco">&#xe613;</i></a></li>
        <li class="hiden">
            <ul>
                <li><a href="/admin/weeklyincome" target="main-frame"><i class="iconfont leftIco">&#xe620;</i>Weekly Income</a></li>
                <li><a href="/admin/monthlyincome" target="main-frame"><i class="iconfont leftIco">&#xe620;</i>Monthly Income</a></li>
                <li><a href="/admin/yearlyincome" target="main-frame"><i class="iconfont leftIco">&#xe620;</i>Yearly Income</a></li>
            </ul>
        </li>
        <li><a href="#" class="headList list7"><i class="iconfont leftIco">&#xe62c;</i>Order List<i class="iconfont rightIco">&#xe613;</i></a></li>
        <li class="hiden">
            <ul>
                <li><a href="#" ><i class="iconfont leftIco">&#xe620;</i>Weekly Order</a></li>
                <li><a href="#" ><i class="iconfont leftIco">&#xe620;</i>Monthly Orde</a></li>
                <li><a href="#" ><i class="iconfont leftIco">&#xe620;</i>Yearly Orde</a></li>
            </ul>
        </li>

    </ul>
</div>
<script src="/resources/libs/jquery.js"></script>
<script src="/resources/js/admin/left.js"></script>
</body>
</html>