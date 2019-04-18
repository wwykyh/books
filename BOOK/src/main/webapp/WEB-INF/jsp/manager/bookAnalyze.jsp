<%@ page  contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>

	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>图书分析界面</title>
	<script type="text/javascript" src="dvpt/config.js"></script>
	<script type="text/javascript" src="js/extend.js"></script>
	<script data-main="main" src="dvpt/require.min.2.1.11.js"></script>
</head>
	<body>
		<div style="width: 100%;height:1300px ;position:relative">
			<div id="monthAnalyze" style="width: 90%;height:680px;padding-left: 2%" ></div>
			<div style="width: 20% ;height: 680px;position:absolute;left:3%;top:3%;font-size: 20px">
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
			<div id="testMain" style="height:500px;width:80%;padding-left: 8%" ></div>
			<div class="footer_bottom" style="text-align: center ;font-size: 15px;"><span>Copyright ©2015 厦门市巨龙信息科技有限公司 技术支持   建议使用1280*768及以上分辨率</span> </div>

		</div>
	</body>
	<script type="text/javascript">
		requirejs(['jquery', 'echarts'], function (jqeury, echarts) {
			var month = document.getElementById("months").value;
			var myChart = echarts.init(document.getElementById("monthAnalyze"));
			var myChart1 = echarts.init(document.getElementById("testMain"));
			fuzhi(myChart,month);
			lineChart(myChart1);

		});
		function change() {
			requirejs(['jquery', 'echarts'], function (jqeury, echarts) {
				var month = document.getElementById("months").value;
				var myChart = echarts.init(document.getElementById("monthAnalyze"));;
				fuzhi(myChart,month);
			});
		}
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
						text = '今年借阅量分析';
					}else{
						text =text+'月份借阅量分析';
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
								fontSize:25
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
							right:'6%',
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

		function lineChart(myChart1) {

			$.ajax({
				contentType : "application/json",
				type : "GET",
				url : 'lineChartInfo',
				dataType : "json",
				success : function(data){
				  var serviceData=[];
					for(var i=0;i<data.length;i++){
						serviceData[i] = data[i].num;
					}

					myChart1.setOption({
						tooltip: {
							trigger: 'axis',
							formatter: "{a}  <br/>{b}份 : {c}本"
						},
						xAxis: {
							type: 'category',
							boundaryGap: false,
							data: ['一月', '二月', '三月', '四月', '五月', '六月', '七月','八月','九月','十月','十一月','十二月']
						},
						yAxis: {
							type: 'value'
						},
						title:{
							text:'月借阅量曲线分析图',
							textStyle:{
								fontWeight:'bold',
								fontSize:25
							},
							x:'center'
						},
						series: [{
							name:'图书借阅量',
							data: serviceData,
							type: 'line',
							smooth: true
						}]
					})

				}
			});

		}

	</script>

</html>
