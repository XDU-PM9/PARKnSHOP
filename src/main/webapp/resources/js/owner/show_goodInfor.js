/**
 * Created by jk on 2016/12/20.
 */
/*全局变量*/
var id;
$(function () {
  var url = window.location.href;
  console.log(url);
  var array=url.split("?");
  id = array[1];
  console.log(id);
  uploadPhoto();
  uploadInfor();
})
function uploadPhoto() {
    $('#photo').change(function(){
        var length = this.files.length;
        for(var i = 0;i<length;i++){
            var file = this.files[i]; //选择上传的文件
            var r = new FileReader();
            r.readAsDataURL(file); //Base64
            $(r).load(function(){
                $('#showPhoto').append('<img src="'+ this.result +'" alt="" />');
            });
        }
    });
}
function uploadInfor() {
    var data = {};
    data.id = id;
    /*补充id*/
    $.ajax({
        type:'post',
        contentType : 'application/json',
        data: JSON.stringify(data),
        url:'/owner/updateGoods',
        success:function (data) {
            var response =JSON.parse(data);
            console.log(response);
            inputVal("id",response.data.id);
            inputVal("createTime",response.data.createTime);
            inputVal("views",response.data.views);
            inputVal("state",response.data.state);
            inputVal("goodsName",response.data.name);
            inputVal("introduction",response.data.desc);
            inputVal("price",response.data.price);
            inputVal("discount",response.data.discount);
            inputVal("inventory",response.data.inventory);
           /* for(var i=0;i<length;i++){
                addPhoto(response.picturePath[i])
            }*/
        }
    })
    
}
function inputVal(id,str) {
    $("#"+id+"").val(str);
}
function addPhoto(url) {
    url = '"'+url+'"';
    $("#photoList").append()
}