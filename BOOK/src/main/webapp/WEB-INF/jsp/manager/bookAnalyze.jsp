<%@ page  contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>Insert title here</title>
</head>
	<body>
		<div id="testMain" style="width: 1600px;height:730px;" ></div>
	</body>
<script type="text/javascript" src="dvpt/config.js"></script>
<script type="text/javascript" src="js/extend.js"></script>
<script data-main="main" src="dvpt/require.min.2.1.11.js"></script>
<script type="text/javascript">

function fuzhi(myChart){
    $.ajax({
        contentType : "application/json",
        type : "GET",
        url : "borrowInfo",
        dataType : "json",
        success : function(data) {
            //创建一个数组，用来装对象传给series.data，因为series.data里面不能直接for循环
            var serviceData=[];
			var legendData=[];
            for(var i=0;i<data.length;i++){
                var obj=new Object();
                obj.name=data[i].lxmc;
                obj.value=data[i].num;
                legendData[i]=data[i].lxmc;
                serviceData[i]=obj;
            }
            myChart.setOption({
                textStyle:{
                    fontWeight:'bold',
                    fontSize:20
                },
                title:{
                    text:'图书分析',
                    textStyle:{
                        fontWeight:'bold',
                        fontSize:30
                    },
                    x:'center',
					top:'20'

                },
                tooltip : {
                    trigger: 'item',
                    formatter: "{a} <br/>{b} : {c} ({d}%)"
                },
                legend: {
                    type: 'scroll',
                    orient: 'vertical',
                    right:10,
                    top:10,
                    bottom:20,
                    data: legendData
                },
                series:[{
                    name:'图书借阅量',
                    type:'pie',
                    radius:'75%',
                    data:serviceData,
                    center: ['50%', '50%'],
                    itemStyle: {
                        emphasis: {
                            shadowBlur: 60,
                            shadowOffsetX: 0,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                        }
                    }
                }]
            });
        } ,
        error: function(msg) {
            console.log(msg)
        }
    });
    myChart.hideLoading();
}
requirejs(['jquery', 'echarts'], function (jqeury, echarts) {
	var myChart = echarts.init(document.getElementById("testMain"));
    myChart.showLoading();
	fuzhi(myChart);
});

        requirejs(['jquery', 'echarts'], function (jqeury, echarts) {
            var myChart = echarts.init(document.getElementById("testMain"));
            fuzhi(myChart);
        });

</script>

</html>