/**
 * Created by jk on 2016/12/29.
 */
var dataArray = [];
$(function () {
    uploadData();
    rollback();
    del();
    backup();
})
function uploadData() {
    dataArray.splice(0,dataArray.length);
    clearTable();
    $.ajax({
        type:'post',
        contentType : 'application/json',
        url:'/admin//getallbackup',
        success:function (data) {
            var response =JSON.parse(data);
            console.log(response);
            $("#backUpTime").html(response.lastbackuptime);
            var length = response.data.length;
            for(var i=0;i<length;i++){
                addTr(i);
                addTd(i,response.data[i].filename);
                addOption(i);
                dataArray.push(response.data[i].filename);
            }
        }
    })
}
/*增加标签的方法*/
function clearTable() {
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
function addOption(i) {
    var className = "tr"+i;
    var str = "<td><a href='#' class='rollback'>Rollback</a> | <a href='#' class='delete'>Delete</a></td>"
    $("."+className+"").append(str);
}
/*链接方法*/
function rollback() {
    $("body").on('click','.rollback',function () {
        var id = $(this).parent().parent().index();
        var data = {};
        console.log(id);
        data.filename = dataArray[id];
        console.log(data);
        /!*测试成功*!/
        $.ajax({
            type:'post',
            contentType : 'application/json',
            data: JSON.stringify(data),
            url:'/admin/rollback',
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
function del() {
    $("body").on('click','.delete',function () {
        var id = $(this).parent().parent().index();
        var data = {};
        console.log(id);
        data.filename = dataArray[id];
        console.log(data);
        /!*测试成功*!/
        $.ajax({
            type:'post',
            contentType : 'application/json',
            data: JSON.stringify(data),
            url:'/admin/deletebackup',
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
function backup() {
    $("#backUpBtn").click(function () {
        $.ajax({
            type:'post', 
            url:'/admin/backup',
            success:function (data) {
                var response =JSON.parse(data);
                console.log(response);
                if(response.error == false){
                    alert("BackUp Success!");
                    location.reload();
                }
                else{
                    alert("Default!");
                }
            }
        })
    })
}