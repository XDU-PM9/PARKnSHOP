/**
 * Created by jk on 2016/12/12.
 */
/*全局属性*/
var shopId = [];
/*加载方法*/
$(function () {
    uploadApply();
});
/*工具方法*/
function uploadApply() {
    var data={};
    data.size = 5;
    data.index = 1;
    $.ajax({
        type:'post',
        contentType : 'application/json',
        data: JSON.stringify(data),
        url:'/admin/apply',
        success: function (data) {
            var response =JSON.parse(data);
            console.log(response);
            var length = response.data.length;
            for(var i=0;i<length;i++){
                addTr(i);
                addTd(i,response.data[i].ownerName);
                addTd(i,response.data[i].realName);
                addImg(i,response.data[i].realImg);
                addTd(i,response.data[i].shopName);
                addImg(i,response.data[i].shopImg);
                addTd(i,response.data[i].shopDesc);
                shopId.push(response.data[i].shopId);
                addOption(i);
            }
        }
    })
}
function uploadShop() {
    
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
    var str = "<td><a href='#' class='agree'>Agree</a> | <a href='#' class='disagree'>DisAgree</a> </td>"
    $("."+className+"").append(str);
}
function addImg(i,url) {
    var className = "tr"+i;
    url='"'+url+'"';
    var str = "<td class='imgTd'><img src="+url+"></td>";
    $("."+className+"").append(str)
}
/*
$("a").click(function () {
        alert("aaa");
        var id = $(this).parent().parent().index();
        console.log(id);
        var data = {};
        data.shopId = shopId[id-2];
        data.result = 1;
        $.ajax({
            type:'post',
            contentType : 'application/json',
            data: JSON.stringify(data),
            url:'admin/apply',
            success: function (data) {
                var response =JSON.parse(data);
                console.log(response);
                if(response.error==false) {
                    location.reload();
                }
                else {
                    alter("error");
                }    
                }
            
        })
    return false;
    })
*/