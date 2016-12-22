/**
 * Created by SONG on 2016/12/20.
 */
$(function () {
    $('#photo').change(function(){
        var length = this.files.length;
        for(var i = 0;i<length;i++){
            var file = this.files[i]; //选择上传的文件
            var r = new FileReader();
            r.readAsDataURL(file); //Base64
            $(r).load(function(){
                $('#showLogo').append('<img src="'+ this.result +'" alt="" />');
            });
        }
    });
})