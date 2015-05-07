var grid;
var win;
var form;
var search;
$(function() {
	grid = $('#dg').datagrid( {
		pageSize : 10,
		rownumbers : true,
		fit : true,// 自动大小
		rownumbers : true,// 行号
		url : '/projectInformation/front/project_displaySubmit',
		// loadMsg:'数据装载中......',
		singleSelect : true,// 单行选取
		pagination : true,// 显示分页

		columns : [ [ {
			field : 'cProCode',
			title : '项目编号',
			width : 80
		}, {
			field : 'cProName',
			title : ' 项目名称',
			width : 100,
			sortable : true
		}, {
			field : 'cSource',
			title : ' 项目来源',
			width : 100,
			sortable : true
		}, {
			field : 'industry',
			title : ' 所属行业',
			width : 100,
			sortable : true
		}, {
			field : 'investor',
			title : ' 投资方',
			width : 100,
			sortable : true
		}, {
			field : 'projectGroup',
			title : ' 所属招商小组',
			width : 100,
			sortable : true
		}, {
			field : 'objective',
			title : '入住园区目的',
			width : 100,
			sortable : true
		}, {
			field : 'cCategory',
			title : '项目类别',
			width : 100,
			sortable : true
		}, {
			field : 'dInvestment',
			title : ' 投资规模',
			width : 100,
			sortable : true
		}, {
			field : 'cMainBuessiness',
			title : ' 主要业务',
			width : 100,
			sortable : true
		}, {
			field : 'cBusinessModel',
			title : ' 商业模式',
			width : 100,
			sortable : true
		}, {
			field : 'cPromotionPlan',
			title : ' 投资推进计划',
			width : 100,
			sortable : true
		}, {
			field : 'cMainProducts',
			title : ' 主要产品',
			width : 100,
			sortable : true
		}, {
			field : 'cMarketingInfo',
			title : ' 市场营销策略',
			width : 100,
			sortable : true
		}, {
			field : 'cOperator',
			title : ' 建档人员',
			width : 100,
			sortable : true
		} ] ],
		toolbar : [ {
			text : '查询',
			iconCls : 'icon-search',
			handler : newSearch
		},'-', {
			text : '导出Excel',
			iconCls : 'icon-excel',
			handler : exportExcel
		},  '-', {
			text : '刷新',
			iconCls : 'icon-reload',
			handler : reLoad
		} ]
	});

	// 设置分页控件
	var p = grid.datagrid('getPager');
	$(p).pagination( {
		pageSize : 10,// 每页显示的记录条数，默认为10
		// pageList: [10,20,30,40,50,100,150],
		displayMsg : "From {from} To  {to}  Total {total} Records"
	});

	win = $('#data-window').window( {
		closed : true
	});
	$('#ss').searchbox( {
		width : 300,
		searcher : function(value, name) {
			grid.datagrid('load', {
				"searchKey" : name,
				"searchValue" : value
			});
		},
		menu : '#mm',
		prompt : '请输入项目编号或项目名称'
	});

	search = $('#search').window( {
		closed : true
	});
});

// 显示搜索窗口
function newSearch() {
	search.window('open');
}
// 重新加载datagrid
function reLoad() {
	grid.datagrid('load', {
		url : '/projectInformation/front/project_displaySubmit'
	});
}

function closeWindow() {
	win.window('close');
}
// 将表单序列化为json
function createParam() {
	var query = form.serializeArray();
	query = convertArray(query);
	return "json=" + JSON.stringify(query);
}
// 主要是推荐这个函数。它将jquery系列化后的值转为name:value的形式。
function convertArray(o) {
	var v = {};
	for ( var i in o) {
		if (o[i].name != '__VIEWSTATE') {
			if (typeof (v[o[i].name]) == 'undefined')
				v[o[i].name] = o[i].value;
			else
				v[o[i].name] += "," + o[i].value;
		}
	}
	return v;
}
function exportExcel() {
	$.messager.progress( {
		text : '正在导出Excel. 请稍等...',
		interval:100
	});
	$.post('/projectInformation/front/project_excelSubmit', {fileName:'projectSubmitInfo.xls'}, function(data) {
		var data = $.parseJSON(data);// 将回调函数的json字符串转成一个对象
		if(data.status=="succeed"){
			var message="<a href="+data.filePath+">"+"已提交意向项目信息"+"</a>";
			$.messager.progress('close');
			$.messager.alert("下载提示",message);
		}
			//javascript: window.location = 'getFile.php?p=' + data;
	});

}

