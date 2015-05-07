<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta charset="UTF-8">
		<title>Basic CRUD Application - jQuery EasyUI CRUD Demo</title>
		<link rel="stylesheet" type="text/css" href="/css/easyUI/easyui.css">
		<link rel="stylesheet" type="text/css" href="/css/easyUI/icon.css">
		<link rel="stylesheet" type="text/css" href="/css/easyUI/demo.css">
		<script type="text/javascript" src="/js/easyUI/jquery.js"></script>
		<script type="text/javascript" src="/js/easyUI/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="/js/easyUI/datagrid-groupview.js"></script>
	</head>
	<body onload="initgrid()">
		<table id="dg" class="easyui-datagrid"
			url="/projectInformation/front/project_getProjectListByState?cState=3"
			toolbar="#toolbar" pagination="true" rownumbers="true"
			fitColumns="true" singleSelect="true" fit="true">
			<thead>
				<tr>
					<th field="cProCode" width="100">
						项目编号
					</th>
					<th field="cng_Address" width="100">
						洽谈地点
					</th>
					<th field="cng_Advice" width="100">
						洽谈引进建议
					</th>
					<th field="cng_Analysis" width="100">
						洽谈分析
					</th>
					<th field="cng_Content" width="100">
						洽谈内容
					</th>
					<th field="cng_Element" width="100">
						吸引企业入驻因素
					</th>
					<th field="cng_Person" width="100">
						参与人员
					</th>
					<th field="cng_Problem" width="100">
						待解决问题
					</th>
					<th field="cng_Require" width="100">
						项目方要求
					</th>
					<th field="cng_Result" width="100">
						洽谈结果
					</th>
					<th field="cng_Works" width="100">
						工作安排
					</th>
					<th field="csummarize" width="100">
						小 结
					</th>
					<th field="coperator" width="100">
						操作员
					</th>
					<th field="cdate" width="100">
						研判日期
					</th>
				</tr>
			</thead>
		</table>
		<div id="toolbar">
			<!--<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-tip" plain="true" onclick="newUser()">查看详情</a> -->
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-ok" plain="true" onclick=
	passProject();
>通过</a>
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-no" plain="true" onclick=
	formReason();
>不通过</a>
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-search" plain="true" onclick="find()">搜索</a>
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-reload" plain="true" onclick=
	refresh();
>刷新</a>
		</div>
	 
		<div id="dlg" class="easyui-dialog"
			style="width: 400px; height: 280px; padding: 10px 20px" closed="true"
			buttons="#dlg-buttons">
			<div class="ftitle">
				项目不通过原因
			</div>
			<div class="fitem">
				<label></label>
				<textarea id="message" name="message" required="true"
					class="easyui-validatebox" onmouseover=
	focus();
onfocus="if(value=='请输入不通过原因') value='';" onmouseout=
	blur();
onblur="if (value=='') value='请输入不通过原因';">请输入不通过原因</textarea>
				<!-- <textarea name="comment" id="comment" cols="5" rows="10" tabindex="4" onmouseover="focus()" onfocus="if(value=='feilong.org：多看，好处多呢') {value=''}" onmouseout="blur()" onblur="if (value=='') {value='feilong.org：多看，好处多呢'}">feilong.org：多看，好处多呢</textarea>
		-->
			</div>
		</div>

		<div id="dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-ok" onclick=
	refuseProject();
>确定</a>
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-cancel" onclick=
	javascript: $('#dlg').dialog('close');
>取消</a>
		</div>

		<div id="search" title="搜索" iconCls="icon-search" modal="true"
			style="background: #fafafa; width: 380px; height: 100px;">
			<div style="padding: 20px 20px 20px 20px;">
				<input id="ss"></input>
				<div id="mm" style="width: 120px">
					<div data-options="name:'cProCode',iconCls:'icon-ok'">
						项目编码
					</div>
					<div data-options="name:'cProName'">
						项目名称
					</div>
				</div>
			</div>
		</div>
		<script type="text/javascript">
	function find() {
		$('#search').window('open');
	}
	$(function initsearch() {
		$('#ss').searchbox( {
			searcher : function(value, name) {
				$('#dg').datagrid('load', {
					"searchKey" : name,
					"searchValue" : value
				});
			},
			menu : '#mm',
			prompt : '请输入项目编号或项目名称'
		});

		$('#search').window( {
			resizable : false,
			draggable : false,
			width : 300,
			height : 100,
			minimizable : false,
			maximizable : false,
			title : '搜索',
			closed : true
		});
	})
	function initgrid() {
		$("#dg").datagrid( {
			groupField : 'cProName',
			showGroup : true,
			view : groupview,
			showHeader : true,
			groupFormatter : function(value, rows) {
				return value + ' - 共' + rows.length + '条洽谈记录';
			}
		});
	}
	function passProject() {
		var row = $('#dg').datagrid('getSelected');
		if (row) {
			$.messager.confirm('Confirm', '确定通过该项目么?', function(r) {
				if (r) {
					$.post('/projectInformation/front/project_agreeProject', {
						"projectid" : row.cProCode
					}, function(result) {
						if (result.success) {
							$('#dg').datagrid('reload'); // reload the  data
						} else {
							$.messager.show( { // show error message
										title : 'Error',
										msg : result.errorMsg
									});
						}
					}, 'json');
				}
			});
		} else
			$.messager.alert('提示', "请选择你要通过的记录", 'info');
	}

	function formReason() {
		var row = $('#dg').datagrid('getSelected');
		if (row)
			$('#dlg').dialog('open').dialog('setTitle', '原因');
		else
			$.messager.alert('提示', "请选择你要不通过的记录", 'info');
	}

	function refuseProject() {
		var reason = $('#message').val();
		var row = $('#dg').datagrid('getSelected');
		if (row) {
			$.messager.confirm('Confirm', '确定不通过该项目么?', function(r) {
				if (r) {
					$('#dlg').dialog('close');
					$.post('/projectInformation/front/project_disagreeProject',
							{
								"projectid" : row.cProCode,
								"reason" : reason
							}, function(result) {
								if (result.success) {
									$('#dg').datagrid('reload'); // reload the user data
						} else {
							$.messager.show( { // show error message
										title : 'Error',
										msg : result.errorMsg
									});
						}
					}, 'json');
				}
			});
		}
		//else $.messager.alert('提示',"请选择你要不通过的记录",'info');
	}

	function refresh() {
		$('#dg').datagrid('load',{ url:'/projectInformation/front/project_getProjectListByState?cState=3' });
	}
</script>
		<style type="text/css">
#fm {
	margin: 0;
	padding: 10px 30px;
}

.ftitle {
	font-size: 14px;
	font-weight: bold;
	padding: 5px 0;
	margin-bottom: 10px;
	border-bottom: 1px solid #ccc;
}

.fitem {
	margin-bottom: 5px;
}

.fitem label {
	display: inline-block;
	width: 80px;
}
</style>
	</body>
</html>