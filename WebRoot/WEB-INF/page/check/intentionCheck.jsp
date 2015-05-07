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
	</head>
	<body>
		<table id="dg" class="easyui-datagrid"
			url="/projectInformation/front/project_getProjectListByState?cState=1"
			toolbar="#toolbar" pagination="true" rownumbers="true"
			fitColumns="true" singleSelect="true" width="100%" fit="true">
			<thead>
				<tr>
					<th field="cProCode" width="100">
						项目编号
					</th>
					<th field="cProName" width="100">
						项目名称
					</th>
					<th field="cSource" width="100">
						项目来源
					</th>
					<th field="industry" width="100">
						所属行业
					</th>
					<th field="investor" width="100">
						投资方
					</th>
					<th field="projectGroup" width="100">
						所属招商小组
					</th>
					<th field="cCategory" width="100">
						项目类别
					</th>
					<th field="iObjective" width="100">
						入园目的
					</th>
					<th field="dInvestment" width="100">
						投资规模
					</th>
					<th field="cMainBuessiness" width="100">
						主要业务
					</th>
					<th field="cBusinessModel" width="100">
						商业模式
					</th>
					<th field="cPromotionPlan" width="100">
						投资推进计划
					</th>
					<th field="cMainProducts" width="100">
						主要产品
					</th>
					<th field="cMarketingInfo" width="100">
						市场营销策略
					</th>
				</tr>
			</thead>
		</table>
		<div id="toolbar">
			<!-- <a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-tip" plain="true" onclick="newUser()">查看详情</a> -->
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-ok" plain="true" onclick=assignGroup();>通过</a>
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-no" plain="true" onclick=formReason();>不通过</a>
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-search" plain="true" onclick="find()">搜索</a>
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-reload" plain="true" onclick=refresh();>刷新</a>
		</div>
		<div id="dlg" class="easyui-dialog"
			style="width: 400px; height: 180px; padding: 10px 20px" closed="true"
			buttons="#dlg-buttons">

			<div class="fitem">
				<textarea id="message" name="message" required="true"
					missingMessage="原因为必填信息" rows="6" cols="40"
					class="easyui-validatebox" onmouseover=focus();
					onfocus="if(value=='请输入不通过原因') value='';" onmouseout=blur();
					onblur="if (value=='') value='请输入不通过原因';">请输入不通过原因</textarea>
			</div>
		</div>

		<div id="dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-ok" onclick=refuseProject();>确定</a>
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-cancel" onclick=javascript:$('#dlg').dialog('close');
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
		<div id="dgroup" class="easyui-dialog"
			style="width: 400px; height: 280px; padding: 10px 20px" closed="true"
			buttons="#dgroup-buttons">
			<div class="ftitle">
				分配招商小组
			</div>
			<div class="fitem">
				<select id="sg" class="easyui-combogrid" style="width: 250px"
					data-options="panelWidth: 1000, idField: 'id',textField: 'cGroupName',
	url: '/canvassGroup/front/canvassGroup_groupProjectList',
	columns: [[
    {field:'id',title:'小组ID',width:100,align:'center'},
    {field:'cGroupName',title:'小组名称',width:200,align:'center'},
    {field:'cProName',title:'项目名称',width:800,align:'center'},
    {field:'pNum',title:'项目个数',width:120,align:'center'},
    {field:'cProPerson',title:'小组成员',width:360,align:'center'},
    {field:'ppNum',title:'成员个数',width:120,align:'center'},
    ]],
    fitColumns: true
    "></select>
			</div>
			<div style="margin: 10px 0">
				<a href="javascript:void(0)" class="easyui-linkbutton"
					onclick=setEnable();>分配人员</a>
				<div class="fitem">
				</div>
				<div>
					<!--  {field:'cPsnCode',title:'人员编码',width:80}, -->
					<select id="pg" class="easyui-combogrid" style="width: 250px"
						disable="true"
						data-options=" panelWidth: 500, multiple: true, idField: 'id', textField: 'cPsnName', url:'/canvassGroup/front/canvassGroup_groupPList',
    columns: [[
    {field:'ck',checkbox:true},
    {field:'cPsnName',title:'姓名',width:120,align:'center'},
    {field:'department',title:'部门',width:80,align:'center'},
    {field:'cGroupName',title:'所属小组',width:80,align:'center'}
    ]],
    fitColumns: true
    ">
					</select>
				</div>
			</div>

			<div id="dgroup-buttons">
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-ok" onclick=passIntention();>确定</a>
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-cancel" onclick=javascript:$('#dgroup').dialog('close');
>取消</a>
			</div>

			<script type="text/javascript">
	function assignGroup() {
		var row = $('#dg').datagrid('getSelected');
		if (row) {
			$('#pg').combogrid('disable');
			$('#dgroup').dialog('open').dialog('setTitle', '分配招商小组');
		} else
			$.messager.alert('提示', "请选择你要不通过的记录", 'info');
	}

	function passIntention() {
		var row = $('#dg').datagrid('getSelected');
		var group = $('#sg').combogrid('getValue');
		var persons = $('#pg').combogrid('getValues');
		alert(group+persons);
		if (row) {
			$.messager.confirm('确定', '确定通过该项目么?', function(r) {
				if (r) {
					$('#dgroup').dialog('close');
					$.post('/canvassGroup/front/canvassGroup_assignGroup', {
						"projectid" : row.cProCode,
						"group" : group,
						"persons" : persons
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
				} else
					$('#dgroup').dialog('close');
			});
		}
		//else $.messager.alert('提示',"请选择你要不通过的记录",'info');
	}

	function formReason() {
		var row = $('#dg').datagrid('getSelected');
		if (row)
			$('#dlg').dialog('open').dialog('setTitle', '原因填写提示');
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
		$('#dg').datagrid('load',{ url:'/projectInformation/front/project_getProjectListByState?cState=1' });
	}

	function setEnable() {
		$('#pg').combogrid('enable');
	}
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