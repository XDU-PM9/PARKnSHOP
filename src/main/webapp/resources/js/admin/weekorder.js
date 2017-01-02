/**
 * Created by jk on 2016/12/29.
 */
var order = []
var allPage;
var curPage;
var index = 1;
var Max;
var Min = 1;
/*加载方法*/
$(function () {
    uploadApply();
    next();
    prev();
    turn();
});
/*工具方法*/
function uploadApply() {
    order.splice(0,order.length);
    clearTable();
    var data={};
    data.size = 5;
    data.index = index;
    $.ajax({
        type:'post',
        contentType : 'application/json',
        data: JSON.stringify(data),
        url:'/admin/getFinishOrderAdminWeek',
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
                addTd(i,response.data[i].ordersId);
                addTd(i,response.data[i].goodsName);
                addTd(i,response.data[i].createTime);
                addTd(i,response.data[i].reciver);
                addTd(i,response.data[i].price);
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
    var str = "<td><a href='#' class='cancel'>Cancel</a></td>"
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
