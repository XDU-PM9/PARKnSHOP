/**
 * Created by jk on 2016/12/18.
 */
/*全局变量*/
var flag = true;
/*加载*/
$(function () {
    checkPass();
})
/*工具方法*/
function checkPass() {
    $("#oldPass").blur(function () {
        var data = {};
        data.password = $("#oldPass").val();
        $.ajax({
            type:'post',
            contentType : 'application/json',
            data: JSON.stringify(data),
            url:'',
            success:function (data) {
                var response =JSON.parse(data);
                console.log(response);
                /*if(1){
                    $(".yes").show();
                }
                else {
                    $(".no").show();
                    flag = false;
                }*/
            }
        })
    })
    $("#newPass").blur(function () {
        /*正则验证*/
    })
    $("#passAgain").blur(function () {
        /*正则验证*/
    })
}