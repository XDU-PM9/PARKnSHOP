/**
 * Created by jk on 2016/12/18.
 */
/*全局变量*/
var flag = true;
/*加载*/
$(function () {
    checkInfor();
    update();
})
/*工具方法*/
function update() {
    $("#updateBtn").click(function () {
        var data = {};
        data.userName = $("#userName").val();
        data.userPhone = $("#userPhone").val();
        data.userEmail = $("#userEmail").val();
        data.userAdress = $("#userAdress").val();
        $.ajax({
            type:'post',
            contentType : 'application/json',
            data: JSON.stringify(data),
            url:'',
            success:function (data) {
                var response =JSON.parse(data);
                console.log(response);
            }
        })
    })
}
function checkInfor() {
    $("#userName").blur(function () {
        /*正则验证*/
    })
    $("#userPhone").blur(function () {
        /*正则验证*/
    })
    $("#userEmail").blur(function () {
        /*正则验证*/
    })
    $("#userAdress").blur(function () {
        /*正则验证*/
    })
}
