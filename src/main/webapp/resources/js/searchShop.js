/**
 * Created by wei on 16-12-11.
 */
var pageCount=1;
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

//获取一页数据
function getOnePage(start) {
    searchOption.start = start.toString();
    searchOption.count = onePageCount.toString();
    $.ajax({
        type: 'POST',
        url: "/searchShop",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(searchOption),
        success: function (msg) {
            listShopSearchResult(msg)
            displayCount();
        },
        datatype: 'json'
    })
}

//搜索
function search() {
    if(!$('#shop_search').hasClass('current')) {
        window.location.href = '/search?name=' + $('#searchText').val();
        return;
    }
    var searchText = $('#searchText').val();
    //searchText不为空，则进行搜索
    if (undefined != searchText && null != searchText && '' != searchText) {
        searchOption.shopName=searchText;
    }else {
        searchOption.shopName='';
    }
        // searchOption.start='0';
        // searchOption.count=onePageCount.toString();
        getShopCount();
        getOnePage(0);
}

//获取搜索到的店铺数量
function getShopCount() {
    $.ajax({
        type: 'POST',
        url: "/getShopCount",
        contentType: "application/json; charset=utf-8",
        data: getJsonData(),
        success: function (msg) {
            pageCount=Math.ceil(msg/onePageCount);
            displayCount();
        }
    })
}

// function searchShop() {
//     $.ajax({
//         type: 'POST',
//         url: '/searchShop',
//         data: {
//             shopName: $('#searchText').val(),
//             start:'0',
//             count:'28'
//         },
//         success: function (msg) {
//             listShopResult(msg);
//         },
//         datatype: 'json'
//     })
// }

//根据searchOption获取到JSON
function getJsonData() {
    return JSON.stringify(searchOption, function (key, value) {
        if ("shopName" == key) {
            return $('#searchText').val();
        }
        return value;
    });
}

//显示搜索结果
function listShopSearchResult(result) {
    $('#productList').find('li').remove();
    if(null==result||'null'==result||undefined==result||''==result){
        $('#productList').append("<li><span>No results found for <br>"+searchOption.name+"</span>")
    }
    $.each(result, function () {
        var result = this;
        $('#productList').append(
            "<li>" +
            "<dl>" +
            "<dt><a href=" + "/shop/detail?shopId=" + result.shopId + "><img src=" + result.logo + "/></a></dt>" +
            "<dd class='title'><a href=" + "/shop/detail?shopId=" + result.shopId + ">" + result.shopName + "</a></dd>" +
            "<dd class='content'>" +
            // "<span class='goods_jiage'>$<strong>" + result.price + "</strong></span>" +
            "<span class='goods_chengjiao'>" + result.views + "view</span>" +
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
        // $('#pageList').append("<li><p>A total of 1 page</p></li>");
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
        $('#pageList').append("<li><input id='pageNumber' type='number' value='1' min='1' max='" + pageCount + "'</li>");
        $('#pageList').append("<li><input type='button' value='jump' onclick='jumpPage()'> ");
    }
}

//搜索选项
var searchOption = {
    time: 'false',   //(按时间排序,新的在后面)
    timeDesc: 'false',   //（按时间降序排序，新的在前面）
    views: 'false',   //(浏览量)
    viewsDesc: 'false',
    // sales: 'false',  //（销量）
    // salesDesc: 'false',
    shopName: '',    //（商品名）
    // type: '',    //（类型）
    start: '0',    //开始搜索处
    count: '0'     //搜索数量
};

function initOrder() {
    searchOption.time='false';
    searchOption.timeDesc='false';
    searchOption.views='false';
    searchOption.viewsDesc='false';
    $('#defaultOrder').removeClass('selected');
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

function orderByViewHightoLow() {
    initOrder();
    $('#viewsHightoLowOrder').addClass('selected');
    searchOption.viewsDesc='true';
    search();
}

