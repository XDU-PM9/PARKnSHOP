/**
 * Created by wei on 16-12-11.
 */
$(document).ready(function () {
    $('#loginForm').bind('submit',function () {
        $.ajax({
            type:'POST',
            contentType: "application/json; charset=utf-8",
            data:JSON.stringify(loginOption,function (key,value) {
                if("username"==key){
                    return $('#username').val();
                }
                if("password"==key){
                    return $('#password').val();
                }
                return value;
            }),
            success: function (msg) {
                if("false"==msg.error){
                    window.location.href='/';
                }else{
                    $('#tips').html(msg.message).addClass('tips');
                }
            },
            dataType: 'json',
        });
        return false;
    })
})
var loginOption={
    username:"",
    password:""
}
