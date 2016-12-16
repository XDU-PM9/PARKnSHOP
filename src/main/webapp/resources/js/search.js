/**
 * Created by wei on 16-12-11.
 */
var productCount;
var onePageCount=28;
var currentPage=1;

function nextPage() {
    if(productCount>currentPage*onePageCount){
        getOnePage(currentPage++*onePageCount);
    }
}

function previousPage() {
    if(currentPage>1){
        getOnePage(--currentPage*onePageCount);
    }
}

function jumpPage(page) {
    if(page>0&&(page-1)*onePageCount<productCount){
        getOnePage((page-1)*onePageCount)
        currentPage=page;
    }
}

function search() {
    searchOption.name=$('#searchText').val();
    getCount(false);
    getOnePage(0);
}

function getOnePage(start) {
    searchOption.start=start.toString();
    searchOption.count=onePageCount.toString();
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

function getCount(searchShop) {
    var url="/getProductCount";
    if(searchShop){
        url="/getShopCount";
    }
     $.ajax({
        type: 'POST',
        url: url,
        contentType: "application/json; charset=utf-8",
        data: getJsonData(),
        success: function (msg) {
            displayCount(msg);
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
    start:'0',    //开始搜索处
    count:'0'     //搜索数量
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
            "<dt><a href=" + "><img src=" + result.picture + "/></a></dt>" +
            "<dd class='title'><a href=" + ">" + result.goodsName + "</a></dd>" +
            "<dd class='content'>" +
            "<span class='goods_jiage'>$<strong>" + result.price + "</strong></span>" +
            "<span class='goods_chengjiao'>" + result.sales + "buy</span>" +
            "</dd>" +
            "</dl>" +
            "</li>"
        );
    })
}

function displayCount(count) {
    productCount=count;
    if(count<28){

    }
}

function firstSearch() {
    var searchText=$('#searchText');
    //searchText不为空，则进行搜索
    if(undefined!=searchText&&null!=searchText&&''!=searchText){
        if($('#search').hasClass('current')) {
            getCount();
            search();
        }else {
            getShopCount();
            searchShop();
        }
    }
}

function getShopCount() {
    $.ajax({
        type:'POST',
        url:"/getShopCount",
        data:{
            shopName:$('#searchText')
        },
        success:function (msg) {
            displayCount(msg);
        }
    })
}

function searchShop(){
    $.ajax({
        type:'POST',
        url:'/searchShop',
        data:{
            shopName:$('#searchText')
        },
        success:function (msg) {
            listShopResult(msg);
        },
        datatype:'json'
    })
}

function listShopResult(result){
    $('#productList').find('li').remove();
    $.each(result, function () {
        var result = this;
        $('#productList').append(
            "<li>" +
            "<dl>" +
            "<dt><a href=" + "><img src=" + result.logo+ "/></a></dt>" +
            "<dd class='title'><a href=" + ">" + result.shopName + "</a></dd>" +
            "<dd class='content'>" +
            // "<span class='goods_jiage'>$<strong>" + result.price + "</strong></span>" +
            "<span class='goods_chengjiao'>" + result.views + "view</span>" +
            "</dd>" +
            "</dl>" +
            "</li>"
        );
    })
}