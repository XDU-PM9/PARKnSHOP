/**
 * Created by jk on 2016/12/12.
 */
/*全局变量*/
var shopId = [];
var allPage;
var curPage;
var index = 1;
var Max;
var Min=1;
/*加载....*/
$(function () {
    uploadApply();
    black();
    del();
    recover();
    next();
    prev();
    turn();
})
/*主方法*/
function uploadApply() {
    var data={};
    data.size = 5;
    data.index = 1;
    $.ajax({
        type:'post',
        contentType : 'application/json',
        data: JSON.stringify(data),
        url:'/admin/applyallshop',
        success: function (data) {
            var response =JSON.parse(data);
            console.log(response);
            Max = response.total;
            allPage = response.total;
            curPage = index;
            if(curPage <= Min ){
                $("#prev").hide();
            }
            else {
                $("#prev").show();
            }
            if(curPage >= Max){
                $("#next").hide();
            }
            else{
                $("#next").show();
            }
            var length = response.data.length;
            pageNum(allPage,curPage);
            addSel(allPage);
            for(var i=0;i<length;i++){
                addTr(i);
                addTd(i,response.data[i].shopId);
                addTd(i,response.data[i].shopName);
                addImg(i,response.data[i].logo);
                addTd(i,response.data[i].introduction);
                addTd(i,response.data[i].ownerId);
                shopId.push(response.data[i].shopId);
                if(response.data[i].state == 3){
                    addOption1(i);
                }
                else if(response.data[i].state == 2){
                    addOption2(i);
                }
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
function addOption1(i) {
    var className = "tr"+i;
    var str = "<td><a href='#' class='black'>Black</a> | <a href='#' class='delete'>Delete</a></td>"
    $("."+className+"").append(str);
}
function addOption2(i) {
    var className = "tr"+i;
    var str = "<td><a href='#' class='delete'>Delete</a>| <a href='#' class='recover'>Recover</a></td>"
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
        data.shopId = shopId[id];
        console.log(data.userId);
        /*测试成功*/
        $.ajax({
            type:'post',
            contentType : 'application/json',
            data: JSON.stringify(data),
            url:'/admin/blackshop',
            success: function (data) {
                var response =JSON.parse(data);
                console.log(response);
                if(response.error==false) {
                    location.reload();
                }
                else {
                    alert("error");
                }
            }
        })
    })
}
function del() {
    $("body").on('click','.delete',function () {
        var id = $(this).parent().parent().index();
        var data = {};
        data.shopId = shopId[id];
        /*测试成功*/
        $.ajax({
            type:'post',
            contentType : 'application/json',
            data: JSON.stringify(data),
            url:'/admin/deleteshop',
            success: function (data) {
                var response =JSON.parse(data);
                console.log(response);
                if(response.error==false) {
                    location.reload();
                }
                else {
                    alert("error");
                }
            }
        })
    })
}
function recover() {
    $("body").on('click','.recover',function () {
        var id = $(this).parent().parent().index();
        var data = {};
        data.shopId = shopId[id];
        /*测试成功*/
        $.ajax({
            type:'post',
            contentType : 'application/json',
            data: JSON.stringify(data),
            url:'/admin/whiteshop',
            success: function (data) {
                var response =JSON.parse(data);
                console.log(response);
                if(response.error==false) {
                    location.reload();
                }
                else {
                    alert("error");
                }
            }
        })
    })
}
function next() {
    $("#next").click(function () {
        var max = Max;
        if(index >= max){
            alert("This is the last page");
            /*location.reload();*/
        }
        else {
            index++;
            uploadApply();
        }
    })
}
function prev() {
    $("#prev").click(function () {
        var min = 1;
        if(index<=min){
            alert("This is the first page")
        }
        else{
            index--;
            uploadApply();
        }
    })
}
function turn() {
    $("#turnPage").click(function () {
            index = $("#gotoPage").val();
            uploadApply();
        }
    )
}
