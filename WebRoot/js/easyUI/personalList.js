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
		url : '/canvassResource/front/personal_display',
		// loadMsg:'数据装载中......',
		singleSelect : false,// 单行选取
		pagination : true,// 显示分页
		columns : [ [ {
			field : 'checkbox',
			checkbox : true
		}, {
			field : 'personal.cPersonName',
			title : ' 介绍人姓名',
			width : 100,
			sortable : true
		}, {
			field : 'personal.cSex',
			title : '性别',
			width : 100,
			sortable : true
		}, {
			field : 'personal.cIDNo',
			title : '身份证号码',
			width : 100,
			sortable : true
		}, {
			field : 'personal.cPersonPost',
			title : ' 职务',
			width : 100,
			sortable : true
		}, {
			field : 'personal.cPersonAddress',
			title : '住址',
			width : 100,
			sortable : true
		}, {
			field : 'personal.cPersonEmail',
			title : '邮箱',
			width : 100,
			sortable : true
		}, {
			field : 'personal.cPersonHand',
			title : ' 手机',
			width : 100,
			sortable : true
		}, {
			field : 'personal.cPersonPhone',
			title : ' 电话',
			width : 100,
			sortable : true
		}, {
			field : 'personal.cPersonQQ',
			title : ' QQ号码',
			width : 100,
			sortable : true
		}, {
			field : 'personal.cPersonCompany',
			title : ' 单位',
			width : 100,
			sortable : true
		}, {
			field : 'personal.cPersonBrief',
			title : ' 简介',
			width : 100,
			sortable : true
		}, {
			field : 'personal.cMemo',
			title : ' 备注',
			width : 100,
			sortable : true
		} ] ],
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
			iconCls : 'icon-search',
			handler : newSearch
		}, '-', {
			text : '导出Excel',
			iconCls : 'icon-excel',
			handler : exportExcel
		}, '-', {
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
		prompt : '请输入身份证号码或姓名或单位'
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
		url : '/canvassResource/front/personal_display'
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
			$.post('/canvassResource/front/personal_delete', parm, function(
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
		title : '新建介绍人',
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
	$("input[id='personal.cPersonName']").attr("disabled", null);
	$('#dlg').dialog('open');
	$('#fm').form('clear');
	url = '/canvassResource/front/personal_save';
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
		$.messager.alert('提示', "请选择你要更新的介绍人", 'info');
		return;
	}
	if (rows.length > 1) {
		$.messager.alert('提示', "只能选择一个介绍人进行更新", 'info');
		return;
	}
	// $('#dlg').dialog('open');id不能带点
	$("input[id='personal.cPersonName']").attr("disabled", "disabled");
	$('#dlg').dialog('open');
	$('#fm').form('load', rows[0]);
	//为了让编辑窗口回显项目类别的值
	var cSexObj = document.getElementById("cSex");
	for ( var i = 0; i < $('#cSex option').length; i++) {
		console.info(rows[0]['personal.cSex'] );
		if (rows[0]['personal.cSex'] == cSexObj.options[i].text) {
			cSexObj.options[i].selected="selected";
		}

	}
}
function save() {
	$('#fm').form('submit', {
		url : url,
		onSubmit : function() {
			return $(this).form('validate');
		},
		success : function(result) {
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
		url : '/canvassResource/front/personal_edit?id=' + rows[0].id,
		onSubmit : function() {
			return true;
		},
		success : function(result) {
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
	$.post('/canvassResource/front/personal_excel', {fileName:'personalInfo.xls'}, function(data) {
		var data = $.parseJSON(data);// 将回调函数的json字符串转成一个对象
		if(data.status=="succeed"){
			var message="<a href="+data.filePath+">"+"介绍人信息"+"</a>";
			$.messager.progress('close');
			$.messager.alert("下载提示",message);
		}
			//javascript: window.location = 'getFile.php?p=' + data;
	});

}
