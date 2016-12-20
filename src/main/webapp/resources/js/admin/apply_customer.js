/**
 * Created by niewenzhi on 2016/12/13.
 */
/*全局变量*/
var userId = [];
var allPage;
var curPage;
var index = 1;
/*main方法*/
$(function () {
    uploadApply();
    black();
    del();
    recover();
    next();
    prev();
    turn();
});
/*main方法*/
function uploadApply() {
    userId.splice(0,userId.length);
    clearTable();
    var data={};
    data.size = 5;
    data.index = index;
    $.ajax({
        type:'post',
        contentType : 'application/json',
        data: JSON.stringify(data),
        url:'/admin/applyalluser',
        success: function (data) {
            var response =JSON.parse(data);
            console.log(response);
            var length = response.data.length;
            curPage = index;
            allPage = response.total;
            pageNum(allPage,curPage);
            addSel(allPage);
            for(var i=0;i<length;i++){
                addTr(i);
                addTd(i,response.data[i].userId);
                addTd(i,response.data[i].username);
                addImg(i,response.data[i].userImage);
                addTd(i,response.data[i].email);
                addTd(i,response.data[i].phone);
                userId.push(response.data[i].userId);
                addOption(i);
            }
        }
    })
}
/*增加标签的方法*/
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
    var str = "<td><a href='#' class='black'>Black</a> | <a href='#' class='delete'>Delete</a>| <a href='#' class='recover'>Recover</a></td>"
    $("."+className+"").append(str);
}
function addImg(i,url) {
    var className = "tr"+i;
    url='"'+url+'"';
    var str = "<td class='imgTd'><img src="+url+"></td>";
    $("."+className+"").append(str)
}

function addSel(num) {
    for(var i=0;i<num;i++){
        addOpt(i);
    }
}
function addOpt(num){
    num  = num+1;
    /*引号化*/
    numb='"'+num+'"';
    var str = "<option value="+numb+">"+num+"</option>"
    $("#gotoPage").append(str);
}
function pageNum(all,cur) {
    $("#allPage").html(all);
    $("#pageCurrent").html(cur);
}
function clearTable() {
    $("#tableInfor").html("");
    $("#gotoPage").html("");
}
/*操作方法*/
function black() {
    $("body").on('click','.black',function () {
        var id = $(this).parent().parent().index();
        var data = {};
        data.userId = userId[id];
        console.log(data.userId);
        /*测试成功*/
        $.ajax({
            type:'post',
            contentType : 'application/json',
            data: JSON.stringify(data),
            url:'/admin/blackuser',
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
    })
}
function del() {
    $("body").on('click','.delete',function () {
        var id = $(this).parent().parent().index();
        var data = {};
        data.userId = userId[id];
        /*测试成功*/
        $.ajax({
            type:'post',
            contentType : 'application/json',
            data: JSON.stringify(data),
            url:'/admin/deleteuser',
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
    })
}
function recover() {
    $("body").on('click','.recover',function () {
        var id = $(this).parent().parent().index();
        var data = {};
        data.userId = userId[id];
        /*测试成功*/
        $.ajax({
            type:'post',
            contentType : 'application/json',
            data: JSON.stringify(data),
            url:'/admin/whiteuser',
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
    })
}
function next() {
    
}
function prev() {
    
}
function turn() {
    
}
