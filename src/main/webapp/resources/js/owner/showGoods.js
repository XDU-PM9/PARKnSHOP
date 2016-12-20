/**
 * Created by jk on 2016/12/18.
 */
/*全局变量*/
var index = 1;
var allPage;
var curPage;
var goodId = [];
var Max;
/*加载*/
$(function () {
    showGoods();
    next();
    prev();
    turn();
    edit();
    del();
})
/*加载需要的方法*/
function showGoods() {
    goodId.splice(0,goodId.length);
    clearTable();
    var data={};
    data.size = 5;
    data.index = index;
    $.ajax({
        type:'post',
        contentType : 'application/json',
        data: JSON.stringify(data),
        url:'/owner/goods',
        success:function (data) {
            var response =JSON.parse(data);
            var length = response.data.length;
            if(response.success == true){
                $("#addBtn").show();
            }
            allPage = response.total;
            Max = allPage;
            curPage = index;
            pageNum(allPage,curPage);
            addSel(allPage);
            for(var i=0;i<length;i++){
                addTr(i);
                addTdIndex(i);
                addTd(i,response.data[i].name);
                addTd(i,response.data[i].price);
                addTd(i,response.data[i].views);
                goodId.push(response.data[i].id);
                addOption(i);
            }
        }
    })
}
function next() {
    $("#next").click(function () {
       /* var max = parseInt($("#allpage").html());
        console.log(max);*/
        if(index>(Max-1)){
            alert("This is the last page");
        }
        else {
            index++;
            showGoods();
        }
    })
}
function prev() {
    $("#prev").click(function () {
        var min = 1;
        if(index<=min){
            alert("This is the first page");
        }
        else{
            index--;
            showGoods();
        }
    })
}
function turn() {
    $("#turnPage").click(function () {
        index = $("#gotoPage").val();
        showGoods();
    })
}
function edit() {
    
}
function del() {
    
}
/*添加标签方法*/
function addTr(i) {
    var className = "tr"+i;
    $("#tableInfor").append("<tr class="+className+"></tr>");
}
function addTdIndex(i) {
    var className = "tr"+i;
    i = i+1;
    $("."+className+"").append("<td>"+i+"</td>");
}
function addTd(i,str) {
    var className = "tr"+i;
    $("."+className+"").append("<td>"+str+"</td>");
}
function addOption(i) {
    var className = "tr"+i;
    var str = "<td><a href='#' class='agree'>Edit</a> | <a href='#' class='disagree'>Delete</a> </td>"
    $("."+className+"").append(str);
}
function addImg(i,url) {
    var className = "tr"+i;
    /*引号化*/
    url = '"'+url+'"';
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
    var str = "<option value="+num+">"+num+"</option>"
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