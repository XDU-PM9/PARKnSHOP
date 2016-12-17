/**
 * Created by wei on 16-12-11.
 */
var pageCount=1;
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

function getOnePage(start) {
    searchOption.start = start.toString();
    searchOption.count = onePageCount.toString();
    $.ajax({
        type: 'POST',
        url: "/search",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(searchOption),
        success: function (msg) {
            listSearchResult(msg)
        },
        datatype: 'json'
    })
}

function displayCount(count) {
    productCount = count;
    if (count < 28) {

    }
}

function search() {
    if(!$('#shop_search').hasClass('current')) {
        window.location.href = '/search?name=' + $('#searchText').val();
        return;
    }
    var searchText = $('#searchText').val();
    //searchText不为空，则进行搜索
    if (undefined != searchText && null != searchText && '' != searchText) {
        getShopCount();
        searchShop();
    }
}

function getShopCount() {
    $.ajax({
        type: 'POST',
        url: "/getShopCount",
        data: {
            shopName: $('#searchText').val()
        },
        success: function (msg) {
            pageCount=Math.ceil(msg/onePageCount);
            displayCount();
        }
    })
}

function searchShop() {
    $.ajax({
        type: 'POST',
        url: '/searchShop',
        data: {
            shopName: $('#searchText').val(),
            start:'0',
            count:'28'
        },
        success: function (msg) {
            listShopResult(msg);
        },
        datatype: 'json'
    })
}

function listShopResult(result) {
    $('#productList').find('li').remove();
    $.each(result, function () {
        var result = this;
        $('#productList').append(
            "<li>" +
            "<dl>" +
            "<dt><a href=" + "/shop/detail?shopId=" + result.shopId + "><img src=" + result.logo + "/></a></dt>" +
            "<dd class='title'><a href=" + "/shop/detail?shopId=" + result.goodsId + ">" + result.shopName + "</a></dd>" +
            "<dd class='content'>" +
            // "<span class='goods_jiage'>$<strong>" + result.price + "</strong></span>" +
            "<span class='goods_chengjiao'>" + result.views + "view</span>" +
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
