/**
 * Created by wei on 16-12-11.
 */
var pageCount = 1;
var onePageCount = 2;
var currentPage = 1;

function nextPage() {
    getOnePage(currentPage++ * onePageCount);
}

function previousPage() {
    getOnePage((--currentPage - 1) * onePageCount);
}

function jumpPage() {
    var page = $('#pageNumber').val();
    getOnePage((page - 1) * onePageCount)
    currentPage = page;
}

function search() {
    if ($('#shop_search').hasClass('current')) {
        window.location.href = '/searchShop?shopName=' + $('#searchText').val();
        return;
    }
    var searchText = $('#searchText');
    //searchText不为空，则进行搜索
    if (undefined != searchText && null != searchText && '' != searchText) {
        searchOption.name = $('#searchText').val();
        getCount();
        getOnePage(0);
    }
}

function getOnePage(start) {
    searchOption.start = start.toString();
    searchOption.count = onePageCount.toString();
    $.ajax({
        type: 'POST',
        url: "/search",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(searchOption),
        success: function (msg) {
            listSearchResult(msg);
            displayCount();
        },
        datatype: 'json'
    })
}

function getCount(searchShop) {
    $.ajax({
        type: 'POST',
        url: "/getProductCount",
        contentType: "application/json; charset=utf-8",
        data: getJsonData(),
        success: function (msg) {
            pageCount = Math.ceil(msg / onePageCount);
            currentPage = 1;
            displayCount();
        },
        // datatype: 'json'
    })
}

var searchOption = {
    time: 'false',   //(按时间排序,新的在后面)
    timeDesc: 'false',   //（按时间降序排序，新的在前面）
    price: 'false',  //(价格)
    priceDesc: 'false',
    view: 'false',   //(浏览量)
    viewDesc: 'false',
    discount: 'false',   //（折扣）
    discountDesc: 'false',
    sales: 'false',  //（销量）
    salesDesc: 'false',
    name: '',    //（商品名）
    type: '',    //（类型）
    start: '0',    //开始搜索处
    count: '0'     //搜索数量
};

function initOrder() {
    searchOption.time = 'false';   //(按时间排序,新的在后面)
    searchOption.timeDesc = 'false';   //（按时间降序排序，新的在前面）
    searchOption.price = 'false';  //(价格)
    searchOption.priceDesc = 'false';
    searchOption.view = 'false';   //(浏览量)
    searchOption.viewDesc = 'false';
    searchOption.discount = 'false';   //（折扣）
    searchOption.discountDesc = 'false';
    searchOption.sales = 'false';  //（销量）
    searchOption.salesDesc = 'false';
    search();
}

function setOrderByTimeNewtoOld() {
    searchOption.timeDesc='true';
    search();
}

function setOrderByTimeOldtoNew() {
    searchOption.time='true';
    search();
}

function getJsonData() {
    return JSON.stringify(searchOption, function (key, value) {
        if ("name" == key) {
            return $('#searchText').val();
        }
        return value;
    });
}

function listSearchResult(result) {
    $('#productList').find('li').remove();
    $.each(result, function () {
        var result = this;
        $('#productList').append(
            "<li>" +
            "<dl>" +
            "<dt><a href=" + "/goods/detail?goodsId=" + result.goodsId + "><img src=" + result.picture + "/></a></dt>" +
            "<dd class='title'><a href=" + "/goods/detail?goodsId=" + result.goodsId + ">" + result.goodsName + "</a></dd>" +
            "<dd class='content'>" +
            "<span class='goods_jiage'>$<strong>" + result.price + "</strong></span>" +
            "<span class='goods_chengjiao'>" + result.sales + "buy</span>" +
            "</dd>" +
            "</dl>" +
            "</li>"
        );
    })
}

function displayCount() {
    if (pageCount <= 1) {
        $('#pageList').find('li').remove();
        $('#pageList').append("<li><span class='currentpage' id='currentPage'>1</span></li>");
        $('#pageList').append("<li><p>A total of 1 page</p></li>");
    } else {
        $('#pageList').find('li').remove();
        if (currentPage > 1) {
            $('#pageList').append("<li><input type='button' onclick='previousPage()' value='Previous'</input></li>");
        }
        $('#pageList').append("<li><span class='currentpage' id='currentPage'>" + currentPage + "</span></li>");
        if (currentPage < pageCount) {
            $('#pageList').append("<li><input type='button' onclick='nextPage()' value='Next'</input></li>");
        }
        $('#pageList').append("<li><p>A total of " + pageCount + " page</p></li>");
        $('#pageList').append("<li><input id='pageNumber' type='number' min='1' max='" + pageCount + "'</li>");
        $('#pageList').append("<li><input type='button' value='jump' onclick='jumpPage()'> ");
    }
}

