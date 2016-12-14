/**
 * Created by jk on 2016/12/12.
 */
$(function () {
    uploadApply();
});
function uploadApply() {
    var data={};
    data.size = 5;
    data.index = 1;
    $.ajax({
        type:'post',
        contentType : 'application/json',
        data: JSON.stringify(data),
        url:'/admin/applyallowner',
        success: function (data) {
            var response =JSON.parse(data);
            console.log(response);
            var length = response.data.length;
            for(var i=0;i<length;i++){
                addTr(i);
                addTd(i,response.data[i].userId);
                addTd(i,response.data[i].username);
                addImg(i,response.data[i].userImage);
                addTd(i,response.data[i].email);
                addTd(i,response.data[i].phone);
                addOption(i);
            }
        }
    })
}
function addTr(i) {
    var className = "tr"+i;
    $("#tableInfor").append("<tr class="+className+"></tr>");
}
function addTd(i,str) {
    var className = "tr"+i;
    $("."+className+"").append("<td>"+str+"</td>");
}
function addOption(i) {
    var className = "tr"+i;
    var str = "<td><a href=''>Black</a> | <a href=''>Delete</a> | <a href=''>Recover</a></td>"
    $("."+className+"").append(str);
}
function addImg(i,url) {
    var className = "tr"+i;
    url='"'+url+'"';
    var str = "<td class='imgTd'><img src="+url+"></td>";
    $("."+className+"").append(str)
}