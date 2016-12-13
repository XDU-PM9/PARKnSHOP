/**
 * Created by wei on 16-12-11.
 */
$(document).ready(function () {
    $('#loginForm').bind('submit',function () {
        loginOption.username=$('#username').val();
        loginOption.password=$('#password').val();
        $.ajax({
            type:'POST',
            contentType: "application/json; charset=utf-8",
            data:JSON.stringify(loginOption),
            success: function (msg) {
                if("false"==msg.error){
                    window.location.href='/index.html';
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
