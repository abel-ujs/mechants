<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp"%>
<html>
	<head>
		<title>介绍单位信息列表</title>
		<link rel="stylesheet" type="text/css" href="/css/easyUI/easyui.css">
		<link rel="stylesheet" type="text/css" href="/css/easyUI/icon.css">
		<link rel="stylesheet" type="text/css" href="/css/easyUI/demo.css">
		<script type="text/javascript" src="/js/easyUI/jquery.js"></script>
		<script type="text/javascript" src="/js/easyUI/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="/js/easyUI/customerList.js"></script>
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
								<font> 客户名称：</font>
							</td>
							<td class="rtd">
								<input  class="easyui-validatebox" required="true" missingMessage="客户名称为必填项" type="text" name="customer.cCusName"
									id="customer.cCusName" onChange="checkcCusName()" />
								<label id="cCusName_label" style="color: #F00">
									 
								</label>
								<label id="checkmessage2"></label>
							</td>
					 
							<td class="listltd">
								<font> 地址：</font>
							</td>
							<td class="rtd">
								<input  class="easyui-validatebox" required="true" missingMessage="地址为必填项" type="text" name="customer.cCusAddress" />
								 
							</td>
						</tr>
						 
						<tr>
							 <td class="listltd">
									<font>所属行业 ：</font>
								</td>
								<td class="rtd">
									<input name="industry" id="industry" class="easyui-combobox"   style="width:200px"
data-options="valueField:'id',textField:'text',url:'/projectInformation/front/project_loadIndustryData'"  
/>
									 
								</td>
						 
							<td class="listltd">
								<font> 联系人：</font>
							</td>
							<td class="rtd">
								<input class="easyui-validatebox" required="true" missingMessage="联系人为必填项" type="text" name="customer.cCusPerson" />
								 
							</td>
						</tr>
						<tr>
							<td class="listltd">
								<font> 固定电话：</font>
							</td>
							<td class="rtd">
								<input type="text" name="customer.cCusPhone"
									onchange="isFixedPhone(this)" />
							</td>
						 
							<td class="listltd">
								<font> 传真：</font>
							</td>
							<td class="rtd">
								<input type="text" name="customer.cCusFax"
									onchange="isFaxNumber(this)" />
							</td>
						</tr>
						<tr>
							<td class="listltd">
								<font> Email：</font>
							</td>
							<td class="rtd">
								<input type="text" name="customer.cCusEmail"
									onchange="isEmail(this)" />
							</td>
						 
							<td class="listltd">
								<font> QQ号码：</font>
							</td>
							<td class="rtd">
								<input type="text" name="customer.cCusQQ"
									onchange="isQQNumber(this)" />
							</td>
						</tr>
						<tr>
							<td class="listltd">
								<font> 手机：</font>
							</td>
							<td class="rtd">
								<input onChange="isphoneNum(this)" type="text"
									name="customer.cCusHand" />
								 
							</td>
						 
							<td class="listltd">
								<font> 法人：</font>
							</td>
							<td class="rtd">
								<input class="easyui-validatebox" required="true" missingMessage="法人为必填项" type="text" name="customer.cCusLPerson" />
								 
							</td>
						</tr>


						<tr>
							<td class="listltd">
								<font> 备注：</font>
							</td>
							<td class="rtd"  >
								<textarea rows="5" cols="40" name="customer.cMemo"
									onclick=setEmpty(this);>备注可选</textarea>
							</td>
						</tr>
						<tr>
							<td class="listltd">
								<font> 简介：</font>
							</td>
							<td class="rtd"  >
								<textarea rows="5" cols="40" name="customer.cCusBrief"
									onclick=setEmpty(this);>简介可选</textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
		<!--搜索框  -->
		<div id="search" title="搜索" iconCls="icon-search" modal="true"
			maximizable="false" minimizable="false"
			style="background: #fafafa; width: 380px; height: 100px;">
			<div style="padding: 20px 20px 20px 20px;">
				<input id="ss"></input>
				<div id="mm" style="width: 120px">
					<div data-options="name:'cCusPerson',iconCls:'icon-ok'">
						联系人
					</div>
					<div data-options="name:'cCusName'">
						单位名称
					</div>
					<div data-options="name:'cCusLPerson'">
						单位法人
					</div>
				</div>
			</div>
		</div>
	</body>
</html>