/**
 * Created by jk on 2016/12/30.
 */
/*全局变量*/
var orderNum = [];
var allPage;
var curPage;
var index = 1;
var Max;
var Min = 1;
/*加载方法*/
$(function () {
    uploadOrder();
    next();
    prev();
    turn();
})
/*数据加载*/
function uploadOrder() {
    clearTable();
    orderNum.splice(0,orderNum.length);
    var data={};
    data.lines = 5;
    data.page = index;
    $.ajax({
        type:'post',
        contentType : 'application/json',
        data: JSON.stringify(data),
        url:'/owner/checkedOrder',
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
            pageNum(allPage,curPage);
            addSel(allPage);
            var length = response.data.length;
            for(var i=0;i<length;i++){
                addTr(i);
                addTdIndex(i);
                addTd(i,response.data[i].goodsName);
                addTd(i,response.data[i].paidTime);
                addTd(i,response.data[i].adress);
                addTd(i,response.data[i].reciver);
                addTd(i,response.data[i].reciverPhone);
                orderNum.push(response.data[i].orderNumber)
                // addOption(i);
            }
        }
    })
}
/*增加标签的方法*/
/*添加标签的方法*/
function addTdIndex(i) {
    var className = "tr"+i;
    var trIndex = i+1;
    $("."+className+"").append("<td>"+trIndex+"</td>");
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
    var str = "<td><a href='#' class='checked'>Check</a></td>"
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
/*页面链接方法*/
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
