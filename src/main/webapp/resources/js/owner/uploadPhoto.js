/**
 * Created by jk on 2016/12/18.
 */
$(function(){
    $('#person').change(function(){
        var file = this.files[0]; //选择上传的文件
        var r = new FileReader();
        r.readAsDataURL(file); //Base64
        $(r).load(function(){
            $('#showPerson').html('<img src="'+ this.result +'" alt="" />');
        });
    });
    $('#logo').change(function(){
        var file = this.files[0]; //选择上传的文件
        var r = new FileReader();
        r.readAsDataURL(file); //Base64
        $(r).load(function(){
            $('#showLogo').html('<img src="'+ this.result +'" alt="" />');
        });
    });
    $('#theme').change(function(){
        var length = this.files.length;
        for(var i = 0;i<length;i++){
            var file = this.files[i]; //选择上传的文件
            var r = new FileReader();
            r.readAsDataURL(file); //Base64
            $(r).load(function(){
                $('#showTheme').append('<img src="'+ this.result +'" alt="" />');
            });
        }
    });
});
