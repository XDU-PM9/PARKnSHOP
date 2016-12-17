/**
 * Created by wei on 16-12-11.
 */
var productCount;
var onePageCount = 28;
var currentPage = 1;

function nextPage() {
    if (productCount > currentPage * onePageCount) {
        getOnePage(currentPage++ * onePageCount);
    }
}

function previousPage() {
    if (currentPage > 1) {
        getOnePage(--currentPage * onePageCount);
    }
}

function jumpPage(page) {
    if (page > 0 && (page - 1) * onePageCount < productCount) {
        getOnePage((page - 1) * onePageCount)
        currentPage = page;
    }
}

// function search() {
//     if(false==$('#shop_search').hasClass('current')) {
//         // window.location.href = '/search?name=' + $('#searchText').val();
//         return;
//     }
//     searchOption.name = $('#searchText').val();
//     getCount(false);
//     getOnePage(0);
// }

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
            displayCount(msg);
        },
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