/**
 * Created by jk on 2017/1/1.
 */
/*日期收入对应数组*/
var date = ["0"];
var earn = ["0"];
/*var response = {"error":false,
 "data":[
 {"date":"2017-01-02","earn":10.0},
 {"date":"2017-01-05","earn":30.0}
 ,{"date":"2017-01-09","earn":15.0}
 ]}*/

/*主方法*/
$(function () {
    uploadInfor();
    var data = [{
        name : 'money',
        value:earn,
        color:'#1f7e92',
        line_width:3
    }];
    var chart = new iChart.LineBasic2D({
        render : 'canvasDiv',
        data: data,
        title : 'Yearly Income',
        width : 800,
        height : 400,
        coordinate:{height:'90%',background_color:'#f6f9fa'},
        sub_option:{
            hollow_inside:false,//设置一个点的亮色在外环的效果
            point_size:16
        },
        labels:date
    });
    chart.draw();
})
/*加载方法*/
function uploadInfor() {
    $.ajax({
        type:'post',
        url:'/admin/getYearCalculate',
        async:false,
        success:function (data) {
            var response =JSON.parse(data);
            console.log(response);
            var length = response.data.length;
            for(var i=0;i<length;i++){
                date.push(response.data[i].date);
                earn.push(response.data[i].earn)
            }
        }
    })
    /*var length = response.data.length;
     console.log(length)
     for(var i=0;i<length;i++){
     date.push(response.data[i].date);
     earn.push(response.data[i].earn)
     }*/
}