/**
 * Created by jk on 2016/12/30.
 */
/*全局变量*/
/*主方法*/
$(function () {
    getRate();
    getShopPrice();
    getGoodsPrice();
    editRate();
    editShopPrice();
    editGoodsPrice();
    submitRate();
    submitGoods();
    submitShop();
})
/*加载三条数据对应的方法*/
function getRate() {
    $.ajax({
        url:'/admin/getRate',
        success:function (data) {
            var response =JSON.parse(data);
            console.log(response);
            var rate = response.rateorPrice;
            $("#rate").append("<td>"+rate+"</td>");
            $("#rate").append("<td><a href='#' id='editRate'>edit</a></td>");
            $("#setRate").val(response.rateorPrice);
        }
    })
}
function getShopPrice(){
    $.ajax({
        url:'/admin/getShopPrice',
        success:function (data) {
            var response =JSON.parse(data);
            console.log(response);
            var shopPrice = response.rateorPrice;
            $("#shopPrice").append("<td>"+shopPrice+"</td>");
            $("#shopPrice").append("<td><a href='#' id='editShopPrice'>edit</a></td>");
            $("#setShopPrice").val(response.rateorPrice);
        }
    })
}
function getGoodsPrice() {
    $.ajax({
        url:'/admin/getGoodsPrice',
        success:function (data) {
            var response =JSON.parse(data);
            console.log(response);
            var goodsPrice = response.rateorPrice;
            $("#goodsPrice").append("<td>"+goodsPrice+"</td>");
            $("#goodsPrice").append("<td><a href='#' id='editGoodsPrice'>edit</a></td>");
            $("#setGoodsPrice").val(response.rateorPrice);
        }
    })
}
/*增加标签的方法*/
/*链接方法*/
function editRate() {
    $("body").on("click","#editRate",function () {
        $(".hide-block").show();
        $(".rate-block").show();
        $(".goodsPrice-block").hide();
        $(".shopPrice-block").hide();
    })
}
function editShopPrice() {
    $("body").on("click","#editShopPrice",function () {
        $(".hide-block").show();
        $(".shopPrice-block").show();
        $(".goodsPrice-block").hide();
        $(".rate-block").hide();
    })
}
function editGoodsPrice() {
    $("body").on("click","#editGoodsPrice",function () {
        $(".hide-block").show();
        $(".goodsPrice-block").show();
        $(".shopPrice-block").hide();
        $(".rate-block").hide();
    })
}
function close() {
    $(".hide-block").hide();
}
function submitRate() {
    $("#rate-submitBtn").click(function () {
        var data= {};
        data.rateorPrice = $("#setRate").val();
        $.ajax({
            type:'post',
            contentType : 'application/json',
            data: JSON.stringify(data),
            url:'/admin/setRate',
            success:function (data) {
                var response =JSON.parse(data);
                console.log(response);
                if(response.error == false){
                    alert("Success!");
                    location.reload();
                }
                else{
                    window.wxc.xcConfirm('Operation Mistake, Please try again. ', window.wxc.xcConfirm.typeEnum.error);
                }
            }
        })
    })
}
function submitGoods() {
    $("#goodsPrice-submitBtn").click(function () {
        var data= {};
        data.rateorPrice = $("#setGoodsPrice").val();
        console.log(data);
        $.ajax({
            type:'post',
            contentType : 'application/json',
            data: JSON.stringify(data),
            url:'/admin/setGoodsPrice',
            success:function (data) {
                var response =JSON.parse(data);
                console.log('shopprice: '+response);
                if(response.error == false){
                    alert("Success!");
                    location.reload();
                }
                else{
                    window.wxc.xcConfirm('Operation Mistake, Please try again. ', window.wxc.xcConfirm.typeEnum.error);
                }
            }
        })
    })
}
function submitShop() {
    $("#shopPrice-submitBtn").click(function () {
        var data= {};
        data.rateorPrice     = $("#setShopPrice").val();
        $.ajax({
            type:'post',
            contentType : 'application/json',
            data: JSON.stringify(data),
            url:'/admin/setShopPrice',
            
            success:function (data) {
                var response =JSON.parse(data);
                console.log(response);
                if(response.error == false){
                    alert("Success!");
                    location.reload();
                }
                else{
                    window.wxc.xcConfirm('Operation Mistake, Please try again. ', window.wxc.xcConfirm.typeEnum.error);
                }
            }
        })
    })
}