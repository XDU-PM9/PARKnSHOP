/**
 * Created by wei on 16-12-11.
 */
$(document).ready(function () {
    $('#registerForm').bind('submit',function () {
        registerOption.username=$('#username').val();
        registerOption.password=$('#password').val();
        if(registerOption.password!=$('#passwordConfirm').val()){
            $('#tips').html('Entered passwords differ').addClass('tips');
            return false;
        }
        registerOption.email=$('#email').val();
        $.ajax({
            type:'POST',
            contentType: "application/json; charset=utf-8",
            data:JSON.stringify(registerOption),
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
var registerOption={
    username:"",
    password:"",
    email:""
}
