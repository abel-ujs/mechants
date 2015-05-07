<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>项目进度统计</title>
<script type="text/javascript" src="/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="/js/jquery.xml2json.js"></script>
<script src="/js/highcharts.js"></script>
<script type="text/javascript" src="/js/exporting.js"></script>
<script type="text/javascript">
	$(function() {
		var chart;
		$(document).ready(
				function() {
					chart = new Highcharts.Chart({
						//常规图表选项设置
						chart : {
							renderTo : 'container', //在哪个区域呈现，对应HTML中的一个元素ID
							plotBackgroundColor : null, //绘图区的背景颜色
							plotBorderWidth : null, //绘图区边框宽度
							plotShadow : false
						//绘图区是否显示阴影            
						},
						title : {
							text : '项目进度统计'
						},
						tooltip : {
							formatter : function() {
								return '<b>' + this.point.name + '</b>: '
										+ this.percentage.toFixed(2) + ' %';
							}
						},
						plotOptions : {
							pie : {
								allowPointSelect : true,
								cursor : 'pointer',
								dataLabels : {
									enabled : true,
									color : '#000000',
									connectorColor : '#000000',
									formatter : function() {
										return '<b>' + this.point.name
												+ '</b>: '
												+ this.percentage.toFixed(2)
												+ ' %';
									}
								},
								point : {
									events : {
										click : function(e) {
											key = e.point.name;
											$("#list").load(
													"/report/report_getList", {
														"key" : e.point.name,
														"type" : 5,
														"page" : 1
													});
											return true;
										}
									}
								},
								showInLegend : true
							}
						},
						legend : {
							layout : 'vertical',
							align : 'right',
							itemStyle : {
								cursor : 'pointer',
								fontSize : '20px'
							},
							y : -70,
							borderWidth : 2
						},
						credits : {
							enabled : false
						},
						//图表要展现的数据
						series : [ {
							type : 'pie',
							name : '项目进度'
						} ]
					});
				});

		$.get('/xml/xmlGroupByState.jsp',
				function(xml) {
					var json = $.xml2json(xml);
					browsers = [];
					for ( var i = 0; i < json.row.length; i++) {
						browsers.push([ json.row[i].name,
								parseInt(json.row[i].number) ]);
					}
					chart.series[0].setData(browsers);
				});

	});
</script>
<script type="text/javascript">
	function topage(page) {
		$("#list").load("/report/report_getList", {
			"key" : key,
			"type" : 5,
			"page" : page
		});
	}
</script>
</head>
<body>
	<div style="margin: 0 1em">
		<div id="container"
			style="min-width: 400px; height: 400px; margin: 0 auto"></div>
	</div>
	<div style="margin: 0 1em">
		<div id="list" style="min-width: 400px; height: 480px; margin: 0 auto"></div>
	</div>
</body>
</html>