/**
 * Created by wei on 16-12-11.
 */
var pageCount = 1;
var onePageCount = 8;
var currentPage = 1;

//下一页
function nextPage() {
    getOnePage(currentPage++ * onePageCount);
}

//上一页
function previousPage() {
    getOnePage((--currentPage - 1) * onePageCount);
}

//跳页
function jumpPage() {
    var page = $('#pageNumber').val();
    if(page>pageCount){
        return;
    }
    getOnePage((page - 1) * onePageCount)
    currentPage = page;
}

//搜索
function search() {
    if ($('#shop_search').hasClass('current')) {
        window.location.href = '/searchShop?shopName=' + $('#searchText').val();
        return;
    }
    var searchText = $('#searchText').val();
    //searchText不为空，则进行搜索
    if (undefined != searchText && null != searchText && '' != searchText) {
        searchOption.name = searchText;
    }else{
        searchOption.name='';
    }
        searchOption.start='0';
        searchOption.count=onePageCount.toString();
        getCount();
        getOnePage(0);
}

//获取一页数据
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

//获取搜索结果总数
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

//搜索选项
var searchOption = {
    time: 'false',   //(按时间排序,新的在后面)
    timeDesc: 'false',   //（按时间降序排序，新的在前面）
    price: 'false',  //(价格)
    priceDesc: 'false',
    views: 'false',   //(浏览量)
    viewsDesc: 'false',
    discount: 'false',   //（折扣）
    discountDesc: 'false',
    sales: 'false',  //（销量）
    salesDesc: 'false',
    name: '',    //（商品名）
    type: '',    //（类型）
    shopId:'',  //店铺Id
    start: '0',    //开始搜索处
    count: '0'     //搜索数量
};

function initType() {
    $('#productType').find('span').removeClass('typeSelected');
    $('#typeSelectFirst').addClass('typeSelected');
    searchOption.type='';
}

function setShopId(param) {
    searchOption.shopId=param.toString();
    search();
}

function setType(id,type) {
    $('#productType').find('span').removeClass('typeSelected');
    $(id).addClass('typeSelected');
    searchOption.type=type;
    search();
}

function initOrder() {
    searchOption.time = 'false';   //(按时间排序,新的在后面)
    searchOption.timeDesc = 'false';   //（按时间降序排序，新的在前面）
    searchOption.price = 'false';  //(价格)
    searchOption.priceDesc = 'false';
    searchOption.views = 'false';   //(浏览量)
    searchOption.viewsDesc = 'false';
    searchOption.discount = 'false';   //（折扣）
    searchOption.discountDesc = 'false';
    searchOption.sales = 'false';  //（销量）
    searchOption.salesDesc = 'false';
    initOrderClass();
}

function initOrderClass() {
    $('#defaultOrder').removeClass('selected');
    $('#salesHightoLowOrder').removeClass('selected');
    $('#priceHightoLowOrder').removeClass('selected');
    $('#viewsHightoLowOrder').removeClass('selected');
}

function orderByDefault() {
    initOrder();
    $('#defaultOrder').addClass('selected');
    search();
}
function orderByTimeNewtoOld() {
    initOrder();
    searchOption.timeDesc='true';
    search();
}

function orderByTimeOldtoNew() {
    initOrder();
    searchOption.time='true';
    search();
}

function orderByPriceLowtoHigh() {
    initOrder();
    searchOption.price='true';
    search();
}

function orderByPriceHightoLow() {
    initOrder();
    $('#priceHightoLowOrder').addClass('selected');
    searchOption.priceDesc='true';
    search();
}

function orderByViewsHightoLow() {
    initOrder();
    $('#viewsHightoLowOrder').addClass('selected');
    searchOption.viewsDesc='true';
    search();
}

function orderByViewsLowtoHigh() {
    initOrder();
    searchOption.views='true';
    search();
}

function orderBySalesHightoLow() {
    initOrder();
    $('#salesHightoLowOrder').addClass('selected');
    searchOption.salesDesc='true';
    search();
}

function orderBySalesLowtoHigh() {
    initOrder();
    searchOption.sales='true';
    search();
}

function orderByDiscountHightoLow() {
    initOrder();
    searchOption.discountDesc='true';
    search();
}

function orderByDiscountLowtoHigh() {
    initOrder();
    searchOption.discount='true';
    search();
}

//根据searchOption获取JSON数据
function getJsonData() {
    return JSON.stringify(searchOption, function (key, value) {
        if ("name" == key) {
            return $('#searchText').val();
        }
        return value;
    });
}

//显示搜索结果
function listSearchResult(result) {
    $('#productList').find('li').remove();
    if(null==result||'null'==result||undefined==result||''==result){
        $('#productList').append("<li><span>No results found for <br>"+searchOption.name+"</span>")
    }
    $.each(result, function () {
        var result = this;
        $('#productList').append(
            "<li>" +
            "<dl>" +
            "<dt><a href=" + "/goods/detail?goodsId=" + result.goodsId + "><img src=" + result.picture + "/></a></dt>" +
            "<dd class='title'><a href=" + "/goods/detail?goodsId=" + result.goodsId + ">" + result.goodsName + "</a></dd>" +
            "<dd class='content'>" +
            "<span class='goods_jiage'>HK$<strong>" + result.price.toFixed(2) + "</strong></span>" +
            "<span class='goods_chengjiao'>" + result.sales + " buy</span>" +
            "</dd>" +
            "</dl>" +
            "</li>"
        );
    })
}

//显示下方的分页
function displayCount() {
    if (pageCount <= 1) {
        $('#pageList').find('li').remove();
        // $('#pageList').append("<li><span class='currentpage' id='currentPage'>1 of 1</span></li>");
    } else {
        $('#pageList').find('li').remove();
        if (currentPage > 1) {
            $('#pageList').append("<li><input type='button' onclick='previousPage()' value='Previous'</input></li>");
        }
        $('#pageList').append("<li><span class='currentpage' id='currentPage'>" + currentPage +" of "+pageCount+ "</span></li>");
        if (currentPage < pageCount) {
            $('#pageList').append("<li><input type='button' onclick='nextPage()' value='Next'</input></li>");
        }
        // $('#pageList').append("<li><p>A total of " + pageCount + " page</p></li>");
        $('#pageList').append("<li><input id='pageNumber' name='jump' type='number' value='1' min='1' max='" + pageCount + "'</li>");
        $('#pageList').append("<li><input type='button' name='jump' value='jump' onclick='jumpPage()'> ");
    }
}

function firstSearch() {
    var type=decodeURIComponent(GetQueryString('type'));
    if(''!=type){
        setType(null,type);
    }
    // search();
}

function GetQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg); //获取url中"?"符后的字符串并正则匹配
    var context = "";
    if (r != null)
        context = r[2];
    reg = null;
    r = null;
    return context == null || context == "" || context == "undefined" ? "" : context;
}
