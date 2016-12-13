/**
 * Created by wei on 16-12-11.
 */
function search() {
    $.ajax({
        type:'POST',
        url:"/search",
        contentType: "application/json; charset=utf-8",
        data:JSON.stringify(searchOption,function(key,value){
            if("name"==key){
                return $('#searchText').val();
            }
            return value;
        }),
        // "name=w",
        success:function(msg) {
            listSearchResult(msg)
        },
        datatype:'json'
    })
}

function listSearchResult(result){
    $.each(result, function() {
        var result = this;
        $('#loadingBar').before(
            "<div id=" + result.goodsId
            + "><div>"
            + "<a href='/shop.jsp?id="
            + result.goodsId + "'>" + result.goodsName
            + "</a><span>" + result.date + ""
            + "</span></div><div class='h2'>"
            + result.brief + "</div></div>");
        $("#" + result.id).mouseover(
            function() {
                $(this).css("background-color",
                    "rgb(255,255,255)");
            });
})
}

var searchOption={
    time:'false',   //(按时间排序,新的在后面)
    timeDesc:'false',   //（按时间降序排序，新的在前面）
    price:'false',  //(价格)
    priceDesc:'false',
    view:'false',   //(浏览量)
    viewDesc:'false',
    discount:'false',   //（折扣）
    discountDesc:'false',
    sales:'false',  //（销量）
    salesDesc:'false',
    name:'',    //（商品名）
    type:''    //（类型）
}