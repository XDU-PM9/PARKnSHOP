/**
 * Created by SONG on 2016/12/20.
 */
$(function () {
    var total =parseInt($("#totalAcount").html());
    console.log(total);
    checkAcount();
    addToCart();
})
function checkAcount() {
    var total =parseInt($("#totalAcount").html());
    console.log(total);
    $("#good_num_jia").click(function () {
        var curAcount  = $("#good_nums").val();
        if(curAcount >= total){
            $("#good_num_jia").hide();
        }
        if(curAcount>1){
            $("#good_num_jian").show();
        }
    })
    $("#good_num_jian").click(function () {
        var curAcount = $("#good_nums").val();
        if(curAcount<=1){
            $("#good_num_jian").hide();
        }
        if(curAcount <total){
            $("#good_num_jia").show();
        }
    })
    $("#good_nums").change(function () {
        var curAcount = $(this).val();
        if(curAcount >= total){
            $(this).val(total);
            $("#good_num_jia").hide();
        }
        else if(curAcount <total){
            $("#good_num_jia").show();
        }
        if(curAcount <=1){
            $(this).val(1);
            $("#good_num_jian").hide();
        }
        else if(curAcount >1){
            $("#good_num_jian").show();
        }
    })
}
function  addToCart() {
    $("#addToCart").click(function () {
        var goodId = parseInt($("#hideGoodsId").html());
        var amount = $("#good_nums").val();
        console.log(goodId+""+amount);
        var str = "/addProduct?goodsId="+goodId+"&amount="+amount;
        $("#addToCart").attr("href",str);
    })
}