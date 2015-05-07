var grid;
var win;
var form;
var search;
$(function() {
	grid = $('#dg').datagrid( {
		idField : 'id',// 多行删除一定要配置idField
		pageSize : 10,
		rownumbers : true,
		fit : true,// 自动大小
		rownumbers : true,// 行号
		striped : true, // 奇偶行颜色不同
		url : '/canvassResource/front/investor_display',
		// loadMsg:'数据装载中......',
		singleSelect : false,// 单行选取
		pagination : true,// 显示分页
		columns : [ [ {
			field : 'checkbox',
			checkbox : true
		}, {
			field : 'investor.cInvName',
			title : ' 投资方名称',
			width : 100,
			sortable : true
		}, {
			field : 'investor.cInvAddress',
			title : '地址',
			width : 100,
			sortable : true
		}, {
			field : 'investor.cInvWebSite',
			title : '网址',
			width : 100,
			sortable : true
		}, {
			field : 'investor.nature',
			title : '所属企业性质',
			width : 100,
			sortable : true
		}, {
			field : 'investor.industry',
			title : '所属行业',
			width : 100,
			sortable : true
		}, {
			field : 'investor.cInvLPerson',
			title : '法人',
			width : 100,
			sortable : true
		}, {
			field : 'investor.cInvLPhone',
			title : ' 固定电话',
			width : 100,
			sortable : true
		}, {
			field : 'investor.cInvLFax',
			title : ' 传真号码',
			width : 100,
			sortable : true
		}, {
			field : 'investor.cInvLHand',
			title : '手机',
			width : 100,
			sortable : true
		}, {
			field : 'investor.cInvLEmail',
			title : ' Email',
			width : 100,
			sortable : true
		}, {
			field : 'investor.cInvPerson',
			title : ' 联系人',
			width : 100,
			sortable : true
		}, {
			field : 'investor.dCapital',
			title : ' 注册资金',
			width : 100,
			sortable : true
		}, {
			field : 'investor.dOValue',
			title : ' 年产值',
			width : 100,
			sortable : true
		}, {
			field : 'investor.cIndustry',
			title : ' 行业地位',
			width : 100,
			sortable : true
		}, {
			field : 'investor.cLevel',
			title : ' 技术水平',
			width : 100,
			sortable : true
		}, {
			field : 'investor.cMainProducts',
			title : '主要产品',
			width : 100,
			sortable : true
		}, {
			field : 'investor.cCompeProducts',
			title : '产品竞争力',
			width : 100,
			sortable : true
		}, {
			field : 'investor.cMarketShare',
			title : '市场份额',
			width : 100,
			sortable : true
		}, {
			field : 'investor.cInformation',
			title : '产品销售对象信息',
			width : 100,
			sortable : true
		}, {
			field : 'investor.cOperator',
			title : ' 建档人员',
			width : 100,
			sortable : true
		}

		] ],
		toolbar : [ {
			text : '新建',
			iconCls : 'icon-add',
			handler : newData
		}, '-', {
			text : '编辑',
			iconCls : 'icon-edit',
			handler : editData
		}, '-', {
			text : '删除',
			iconCls : 'icon-remove',
			handler : delData
		}, '-', {
			text : '查询',
			iconCls : 'icon-excel',
			handler : newSearch
		}, '-', {
			text : '导出Excel',
			iconCls : 'icon-search',
			handler : exportExcel
		},'-', {
			text : '刷新',
			iconCls : 'icon-reload',
			handler : reLoad
		} ]
	});

	// 设置分页控件
	var p = grid.datagrid('getPager');
	$(p).pagination( {
		pageSize : 10,// 每页显示的记录条数，默认为10
		pageList : [ 3, 10, 20, 30, 40, 50, 100, 150 ],
		displayMsg : "From {from} To  {to}  Total {total} Records"
	});

	win = $('#data-window').window( {
		closed : true
	});
	form = win.find('form');

	$('#ss').searchbox( {
		width : 300,
		searcher : function(value, name) {
			grid.datagrid('load', {
				"searchKey" : name,
				"searchValue" : value
			});
		},
		menu : '#mm',
		prompt : '请输入单位名称或单位法人或联系人'
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
		url : '/canvassResource/front/investor_display'
	});
}

// 保存新增或者修改的数据
function saveData() {
	if (form.form('validate')) {
		var query = createParam();
		$.post(form.url, query, function(result) {
			if (result.status == 1) {
				grid.datagrid('reload');
				win.window('close');
				$.messager.show( {
					title : '提示',
					msg : '数据记录保存成功。'
				});
			} else {
				$.messager.alert('错误', result.msg, 'error');
			}
		}, 'json');
	}
}

// 删除记录，参数是记录的主关键字
function delData() {
	// 获取表格选择行
	var rows = $('#dg').datagrid('getSelections');
	// 判断是否选择行
	if (!rows || rows.length == 0) {
		$.messager.alert('提示', '请选择要删除的数据!', 'info');
		return;
	}
	var parm;

	// 循环给提交删除参数赋值(音乐风格编码)
	$.each(rows, function(i, n) {
		if (i == 0) {
			parm = "idList=" + n.id;
		} else {
			parm += "&idList=" + n.id;
		}
	});
	$.messager.confirm('提示', '是否删除选中数据?', function(r) {
		if (!r) {
			return;
		}
		// 提交
			$.post('/canvassResource/front/investor_delete', parm, function(
					result) {
				if (result.status == "success") {
					$('#dg').datagrid('reload');
				} else {
					$.messager.alert('错误', result.msg, 'error');
				}
			}, 'json');
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

//
var url;
// 显示新增数据窗口

function newData() {
	$('#dlg').dialog( {
		title : '新建投资方',
		buttons : [ {
			text : '保存',
			iconCls : 'icon-ok',
			handler : save
		}, {
			text : '取消',
			iconCls : 'icon-cancel',
			handler : function() {
				$('#dlg').dialog('close');
			}
		}, ]
	});
	$("input[id='investor.cInvName']").attr("disabled", null);
	$('#dlg').dialog('open');
	$('#fm').form('clear');
	url = '/canvassResource/front/investor_save';
}
// 显示编辑数据窗口

function editData() {
	$('#dlg').dialog( {
		title : '编辑介绍人',
		buttons : [ {
			text : '编辑',
			iconCls : 'icon-edit',
			handler : edit
		}, {
			text : '取消',
			iconCls : 'icon-cancel',
			handler : function() {
				$('#dlg').dialog('close');
			}
		}, ]
	});

	var rows = $('#dg').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', "请选择你要更新的投资方", 'info');
		return;
	}
	if (rows.length > 1) {
		$.messager.alert('提示', "只能选择一个投资方进行更新", 'info');
		return;
	}
	$("input[id='investor.cInvName']").attr("disabled", true);
	$('#dlg').dialog('open');
	$('#fm').form('load', rows[0]);
}
function save() {
	$('#fm').form('submit', {
		url : url,
		onSubmit : function() {
			return $(this).form('validate');
		},
		success : function(result) {
			var result = $.parseJSON(result);// 将回调函数的json字符串转成一个对象
		if (result.errorMsg) {
			$.messager.show( {
				title : 'Error',
				msg : result.errorMsg
			});
		} else {
			$('#dlg').dialog('close'); // close the dialog
		$('#dg').datagrid('reload'); // reload the user data
	}
}
	});

}
function edit() {
	var rows = $('#dg').datagrid('getSelections');
	$('#fm').form('submit', {
		url : '/canvassResource/front/investor_edit?id=' + rows[0].id,
		onSubmit : function() {
			return $(this).form('validate');
		},
		success : function(result) {
			var result = $.parseJSON(result);// 将回调函数的json字符串转成一个对象
		if (result.errorMsg) {
			$.messager.show( {
				title : 'Error',
				msg : result.errorMsg
			});
		} else {
			$('#dlg').dialog('close'); // close the dialog
		$('#dg').datagrid('reload'); // reload the user data
	}
}
	});
}
function exportExcel() {
	$.messager.progress( {
		text : '正在导出Excel. 请稍等...',
		interval:100
	});
	$.post('/canvassResource/front/investor_excel', {fileName:'investorInfo.xls'}, function(data) {
		var data = $.parseJSON(data);// 将回调函数的json字符串转成一个对象
		if(data.status=="succeed"){
			var message="<a href="+data.filePath+">"+"投资方信息"+"</a>";
			$.messager.progress('close');
			$.messager.alert("下载提示",message);
		}
			//javascript: window.location = 'getFile.php?p=' + data;
	});

}
