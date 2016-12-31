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
})
/*加载三条数据对应的方法*/
function getRate() {
    $.ajax({
        type:'post',
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
        type:'post',
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
        type:'post',
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
    })
}
function editShopPrice() {
    $("body").on("click","#editShopPrice",function () {
        $(".hide-block").show();
        $(".shopPrice-block").show();
    })
}
function editGoodsPrice() {
    $("body").on("click","#editGoodsPrice",function () {
        $(".hide-block").show();
        $(".goodsPrice-block").show();
    })
}