<%@ page  contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>图书分析界面</title>
</head>
	<body>
		<div style="width: 100%;position:relative">
			<div id="testMain" style="width: 90%;height:730px;" ></div>
			<div style="width: 20% ;height: 730px;position:absolute;left:3%;top:3%;font-size: 20px">
				<label>查看月份</label>
				<select  id="months" onchange="change()" style="width: 90px;height: 30px;font-size: 20px">
					<option value="1">1月份</option>
					<option value="2">2月份</option>
					<option value="3">3月份</option>
					<option value="4">4月份</option>
					<option value="5">5月份</option>
					<option value="6">6月份</option>
					<option value="7">7月份</option>
					<option value="8">8月份</option>
					<option value="9">9月份</option>
					<option value="10">10月份</option>
					<option value="11">11月份</option>
					<option value="12">12月份</option>
					<option value="0" selected>整年</option>
				</select>
			</div>
		</div>
	</body>
<script type="text/javascript" src="dvpt/config.js"></script>
<script type="text/javascript" src="js/extend.js"></script>
<script data-main="main" src="dvpt/require.min.2.1.11.js"></script>
<script type="text/javascript">

function fuzhi(myChart,month){
    $.ajax({
        contentType : "application/json",
        type : "GET",
        url : 'borrowInfo?month='+month,
        dataType : "json",
        success : function(data) {
            //创建一个数组，用来装对象传给series.data，因为series.data里面不能直接for循环
            var serviceData=[];
			var legendData=[];
			var text = document.getElementById("months").value;
			if(text==0){
			    text = '今年借阅量';
			}else{
			    text =text+'月份借阅量';
			}
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
                    text:text,
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
}


	requirejs(['jquery', 'echarts'], function (jqeury, echarts) {
	    debugger
        var month = document.getElementById("months").value;
        var myChart = echarts.init(document.getElementById("testMain"));
        fuzhi(myChart,month);
	});
	function change() {
        requirejs(['jquery', 'echarts'], function (jqeury, echarts) {
            debugger
            var month = document.getElementById("months").value;
            var myChart = echarts.init(document.getElementById("testMain"));
            fuzhi(myChart,month);

        });
    }
</script>

</html>