<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp"%>
<html>
	<head>
		<title>介绍人信息列表</title>
		<link rel="stylesheet" type="text/css" href="/css/easyUI/easyui.css">
		<link rel="stylesheet" type="text/css" href="/css/easyUI/icon.css">
		<link rel="stylesheet" type="text/css" href="/css/easyUI/demo.css">
		<script type="text/javascript" src="/js/easyUI/jquery-1.10.2.min.js"></script>
		<script type="text/javascript" src="/js/easyUI/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="/js/easyUI/personalList.js"></script>
		<SCRIPT language=JavaScript src="/js/FoshanRen.js"></SCRIPT>
		<script language="javascript" src="/js/ajax.js"></script>
		<script language="javascript" src="/js/DatePicker.js"></script>
		<script type="text/javascript"> 
	 //  提示用户输入信息
		function setEmpty(obj){
		   obj.value="";
		}
		 </script>
	</head>
	<body>
		<table id="dg" class="easyui-datagrid">
		</table>
		<!-- 新建框 -->
		<div id="dlg" class="easyui-dialog"
			style="width: 800px; height: 400px; padding: 10px 20px" closed="true"
			buttons="#dlg-buttons">
			<form id="fm" method="post">
				<table cellspacing="0" cellpadding="0">
					<tbody>

						<tr>
							<td class="listltd">
								<font> 人员姓名：</font>
							</td>
							<td class="rtd">
								<input class="easyui-validatebox" required="true"
									missingMessage="人员姓名为必填项" type="text" id="personal.cPersonName"
									name="personal.cPersonName" onchange="checkcPersonName()" />
								<label id="cPersonName_label" style="color: #F00">
								</label>
								<label id="checkmessage2"></label>
							</td>

							<td class="listltd">
								<font>性别：</font>
							</td>
							<td class="rtd">
								<select id="cSex" name="personal.cSex"
									class="easyui-validatebox" missingMessage="性别为必选项"
									required="true" style="width: 200px">
									<option value="MALE" selected="selected">
										男
									</option>
									<option value="FEMAL">
										女
									</option>
								</select>
							</td>
						</tr>
						<tr>
							<td class="listltd">
								<font>身份证号码：</font>
							</td>
							<td class="rtd">
								<input class="easyui-validatebox" required="true"
									missingMessage="身份证号码为必填项" type="text" name="personal.cIDNo"
									id="IDNumber" onchange="isIDNumber(this)" />

							</td>

							<td class="listltd">
								<font>职务：</font>
							</td>
							<td class="rtd">
								<input type="text" name="personal.cPersonPost" />

							</td>
						</tr>

						<tr>
							<td class="listltd">
								<font>邮箱：</font>
							</td>
							<td class="rtd">
								<input class="easyui-validatebox"
									data-options="validType:'email',invalidMessage:'请输入正确的邮箱'"
									type="text" name="personal.cPersonEmail" />
							</td>

							<td class="listltd">
								<font>手机：</font>
							</td>
							<td class="rtd">
								<input class="easyui-validatebox" required="true"
									missingMessage="手机为必填项" type="text" name="personal.cPersonHand"
									onchange="isphoneNum(this)" />

							</td>
						</tr>
						<tr>
							<td class="listltd">
								<font>电话：</font>
							</td>
							<td class="rtd">
								<input type="text" name="personal.cPersonPhone"
									onchange="isFixedPhone(this)"   />

							</td>

							<td class="listltd">
								<font>QQ号码：</font>
							</td>
							<td class="rtd">
								<input type="text" name="personal.cPersonQQ" />
							</td>
						</tr>
						<tr>
							<td class="listltd">
								<font>单位：</font>
							</td>
							<td class="rtd">
								<input class="easyui-validatebox" required="true"
									missingMessage="单位为必填项" type="text"
									name="personal.cPersonCompany" />

							</td>
						</tr>
						<tr>
							<td class="listltd">
								<font>住址：</font>
							</td>
							<td class="rtd" style="padding: 10px 0 10px 20px;">
								<textarea rows="5" cols="40" name="personal.cPersonAddress"> </textarea>
							</td>
						</tr>
						<tr>
							<td class="listltd">
								<font>简介：</font>
							</td>
							<td class="rtd" style="padding: 10px 0 10px 20px;">
								<textarea rows="5" cols="40" name="personal.cPersonBrief"> </textarea>
							</td>
						</tr>
						<tr>
							<td class="listltd">
								<font>备注：</font>
							</td>
							<td class="rtd" style="padding: 10px 0 10px 20px;">
								<textarea rows="5" cols="40" name="personal.cMemo"> </textarea>
							</td>
						</tr>

					</tbody>
				</table>
			</form>
		</div>
		<div id="dlg-buttons">

			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-ok" onclick="save()">保存</a>
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-cancel" onclick=javascript:$('#dlg').dialog('close');
>取消</a>
		</div>


		<!--搜索框  -->
		<div id="search" title="搜索" iconCls="icon-search" modal="true"
			maximizable="false" minimizable="false"
			style="background: #fafafa; width: 380px; height: 100px;">
			<div style="padding: 20px 20px 20px 20px;">
				<input id="ss"></input>
				<div id="mm" style="width: 120px">
					<div data-options="name:'cIDNo',iconCls:'icon-ok'">
						身份证号码
					</div>
					<div data-options="name:'cPersonName'">
						姓名
					</div>
					<div data-options="name:'cPersonCompany'">
						单位
					</div>
				</div>
			</div>
		</div>
	</body>
</html>