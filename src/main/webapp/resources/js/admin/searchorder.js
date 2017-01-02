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
    $("#searchOrder").click(function () {
        clearTable();
        var name = $("#orderNum").val();
        var data = {};
        data.num = name;
        $.ajax({
            type:'post',
            contentType : 'application/json',
            data: JSON.stringify(data),
            url:'/admin/searchOrderByNum',
            success:function (data) {
                var response =JSON.parse(data);
                console.log(response);
                var length = response.data.length;
                for(var i=0;i<length;i++){
                    addTr(i);
                    addTd(i,response.data[i].ordersId);
                    addTd(i,response.data[i].goodsName);
                    addTd(i,response.data[i].createTime);
                    addTd(i,response.data[i].revicer);
                    addTd(i,response.data[i].price);
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