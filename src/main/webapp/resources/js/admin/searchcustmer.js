/**
 * Created by jk on 2017/1/2.
 */
/*全局属性*/
/*主方法*/
$(function () {
    uploadInfor();
})
/*加载方法*/
function uploadInfor() {
    $("#searchUser").click(function () {
        clearTable();
        var name = $("#userName").val();
        var data = {};
        data.name = name;
        $.ajax({
            type:'post',
            contentType : 'application/json',
            data: JSON.stringify(data),
            url:'/admin/searchUserByName',
            success:function (data) {
                var response =JSON.parse(data);
                console.log(response);
                var length = response.data.length;
                for(var i=0;i<length;i++){
                    addTr(i);
                    addTd(i,response.data[i].userId);
                    addTd(i,response.data[i].username);
                    addImg(i,response.data[i].userImage);
                    addTd(i,response.data[i].phone);
                    addTd(i,response.data[i].email);
                }
            }
        })
    })
}
/*操作方法*/
function clearTable(){
    $("#tableInfor").html("");
}
function addTr(i) {
    var className = "tr"+i;
    $("#tableInfor").append("<tr class="+className+"></tr>");
}
function addTd(i,str) {
    var className = "tr"+i;
    $("."+className+"").append("<td>"+str+"</td>");
}
function addImg(i,url) {
    var className = "tr"+i;
    url='"'+url+'"';
    var str = "<td class='imgTd'><img src="+url+"></td>";
    $("."+className+"").append(str)
}