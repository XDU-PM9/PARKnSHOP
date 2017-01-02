/**
 * Created by jk on 2016/12/12.
 */
/*全局属性*/
var shopId = [];
var allPage;
var curPage;
var index = 1;
var Max;
var Min = 1;
/*加载方法*/
$(function () {
    uploadApply();
    agree();
    disagree();
    next();
    prev();
    turn();
});
/*工具方法*/
function uploadApply() {
    shopId.splice(0,shopId.length);
    clearTable();
    var data={};
    data.size = 2;
    data.index = index;
    $.ajax({
        type:'post',
        contentType : 'application/json',
        data: JSON.stringify(data),
        url:'/admin/apply',
        success: function (data) {
            var response =JSON.parse(data);
            console.log(response);
            Max = response.total;
            allPage = response.total;
            console.log(Max);
            curPage = index;
            if(curPage <= Min){
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
                addTd(i,response.data[i].ownerName);
                addTd(i,response.data[i].realName);
                addImg(i,response.data[i].realImg);
                addTd(i,response.data[i].shopName);
                addImg(i,response.data[i].shopImg);
                addTd(i,response.data[i].shopDesc);
                addTd(i,response.data[i].shopDate);
                shopId.push(response.data[i].shopId);
                addOption(i);
            }
        }
    })
}
/*添加标签的方法*/
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
function clearTable(){
    $("#tableInfor").html("");
    $("#gotoPage").html("");
}
/*页面链接方法*/
function agree() {
    $("body").on('click','.agree',function () {
        var id = $(this).parent().parent().index();
        var data = {};
        data.shopId = shopId[id];
        data.result = 1;
        /*测试成功*/
        $.ajax({
            type:'post',
            contentType : 'application/json',
            data: JSON.stringify(data),
            url:'/admin/reply',
            success: function (data) {
                var response =JSON.parse(data);
                console.log(response);
                if(response.error==false) {
                    location.reload();
                }
                else {
                }
            }
        })
    })
}
function disagree() {
    $("body").on('click','.disagree',function () {
        var id = $(this).parent().parent().index();
        var data = {};
        data.shopId = shopId[id];
        data.result = 0;
        /*测试成功*/
        $.ajax({
            type:'post',
            contentType : 'application/json',
            data: JSON.stringify(data),
            url:'/admin/reply',
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
function turn() {
    $("#turnPage").click(function () {
            index = $("#gotoPage").val();
            uploadApply();
        }
    )

}

function next() {
    $("#next").click(function () {
       var max = Max ;
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
