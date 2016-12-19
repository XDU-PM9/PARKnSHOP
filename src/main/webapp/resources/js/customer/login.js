/**
 * Created by wei on 16-12-11.
 */
$(document).ready(function () {
    $('#loginForm').bind('submit', function () {
        $.ajax({
            url:'/customer/login',
            type: 'POST',
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(loginOption, function (key, value) {
                if ("username" == key) {
                    return $('#username').val();
                }
                if ("password" == key) {
                    return $('#password').val();
                }
                return value;
            }),
            success: function (msg) {
                if ("false" == msg.error) {
                    // var ca = document.cookie.split(';');
                    // for (var i = 0; i < ca.length; i++) {
                    //     var c = ca[i].trim();
                    //     if (c.indexOf('url') == 0) {
                    //         var url=c.substring('url='.length, c.length);
                            // document.cookie= "url="+url+";expires="+new Date().toGMTString();
                            // window.location.href = url;
                    // }
                    // if(null!=msg.url&&''!=msg.url&&'null'!=msg.url){
                    //     alert(msg.url);
                    if(true==msg.fresh||'true'==msg.fresh){
                        window.location.reload();
                        // window.location.href=msg.url;
                    }else {
                        window.location.href = '/';
                    }
                } else {
                    $('#tips').html(msg.message).addClass('tips');
                }
            },
            dataType: 'json',
        });
        return false;
    })
})
var loginOption = {
    username: "",
    password: ""
}
