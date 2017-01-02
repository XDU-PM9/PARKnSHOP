<%@ page import="com.parknshop.bean.CalculateDbBean" %>
<%@ page import="java.util.List" %>
<%@ page import="com.parknshop.controller.OwnerAnalysisController" %>
<%@ page import="com.parknshop.utils.Log" %>
<%@ page import="com.parknshop.utils.DateFormat" %>
<%@ page import="java.util.Iterator" %>
<!DOCTYPE html>
<html>
<head>
 <meta content='text/html;charset=utf-8' http-equiv='content-type'>
 <title>Echarts图表Demo</title>
 <script src="/resources/js/esl/esl.js"></script>
</head>

<body>

<div id="line" style="width:1000px;height:600px;">  </div>
<script type="text/javascript">

    var fileLocation ='/resources/js/echarts';
    require.config({

        paths:{
            echarts: fileLocation,
            'echarts/chart/line': fileLocation,
            'echarts/chart/bar': fileLocation,
            'echarts/chart/pie': fileLocation
        }
    });

    // 作为入口
    require(
        [
            'echarts',
            'echarts/chart/line'
        ],

        displayChart

    );

    function displayChart(ec) {
        //折线图
        var lineChart = ec.init(document.getElementById('line'));
        var lineChartOtion = getLineChartOption();
        lineChart.setOption(lineChartOtion);
    }

    <%
      double total = 0.0;
      List<CalculateDbBean> yearData = (List<CalculateDbBean>) request.getAttribute(OwnerAnalysisController.YEAR);
    %>
    //获得Line图的选项和数据
    function getLineChartOption(){
        var lineChartOption={
            xAxis : [
                {
                    type : 'category',
                    boundaryGap : false,
                    data:[
                        <%
                           if (yearData!=null){
                             Iterator<CalculateDbBean> iterator = yearData.iterator();
                             while (iterator.hasNext()){
                                 CalculateDbBean item = iterator.next();
                                 out.print("'"+DateFormat.getAnalysisY(item.getDate())+"'");
                                 if (iterator.hasNext()){
                                    out.print(",");
                                 }
                             }
                            }
                        %>
                    ]
//            data : ['周一','周二','周三','周四','周五','周六','周日']
                }
            ],
            yAxis : [
                {
                    type : 'value',
                    axisLabel : {
                        formatter: '{value}'
                    },
                    splitArea : {show : true}
                }
            ],
            series : [
                {
                    name:'income',
                    type:'line',
                    itemStyle: {
                        normal: {
                            lineStyle: {
                                shadowColor : 'rgba(0,0,0,0.4)'
                            }
                        }
                    },
                    data:[
                        <%
                         if (yearData!=null){
                            Iterator<CalculateDbBean> i = yearData.iterator();
                            while (i.hasNext()){
                                CalculateDbBean item = i.next();
                                double price = item.getPrice();
                                total+=price;
                                out.print("'"+price+"'");
                                if (i.hasNext()){
                                    out.print(",");
                                }
                            }
                         }
                        %>
                    ]
//            data:[-2, 1, 2, 5, 3, 2, 0]
                }
            ],
            title : {
                text: 'Total Income: HK$<%out.print(total);%>',
            }
        };
        return lineChartOption;
    }

</script>
</body>
</html>
