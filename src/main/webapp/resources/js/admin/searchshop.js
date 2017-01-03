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
    $("#searchShop").click(function () {
        clearTable();
        var name = $("#shopName").val();
        var data = {};
        data.name = name;
        $.ajax({
            type:'post',
            contentType : 'application/json',
            data: JSON.stringify(data),
            url:'/admin/searchShopByName',
            success:function (data) {
                var response =JSON.parse(data);
                console.log(response);
                var length = response.data.length;
                for(var i=0;i<length;i++){
                    addTr(i);
                    addTd(i,response.data[i].shopId);
                    addTd(i,response.data[i].shopName);
                    addTd(i,response.data[i].introduction);
                    addTd(i,response.data[i].views);
                    addImg(i,response.data[i].logo);
                    addTd(i,response.data[i].ownerId);
                    addshopstate(i,response.data[i].state);
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
function addshopstate(i,str) {
    var className = "tr"+i;
    if(str == 3){
        $("."+className+"").append("<td>Normal</td>");
    }
    else if(str == 2){
        $("."+className+"").append("<td>Blacked</td>");
    }
    else if(str == -1){
        $("."+className+"").append("<td>Deleted</td>");
    }
    else if(str == -0){
        $("."+className+"").append("<td>Rejected</td>");
    }
    else if(str == 1){
        $("."+className+"").append("<td>Applying</td>");
    }
    else {
        $("."+className+"").append("<td>Wrong</td>");
    }

}