<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp"%>
<html>
	<head>
		<title>投资方信息列表</title>
		<link rel="stylesheet" type="text/css" href="/css/easyUI/easyui.css">
		<link rel="stylesheet" type="text/css" href="/css/easyUI/icon.css">
		<link rel="stylesheet" type="text/css" href="/css/easyUI/demo.css">
		<script type="text/javascript" src="/js/easyUI/jquery.js"></script>
		<script type="text/javascript" src="/js/easyUI/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="/js/easyUI/investorList.js"></script>
		<SCRIPT language=JavaScript src="/js/FoshanRen.js"></SCRIPT>
		<script language="javascript" src="/js/ajax.js"></script>
		<script language="javascript" src="/js/DatePicker.js"></script>
		<script type="text/javascript">
	//  提示用户输入信息
	function setEmpty(obj) {
		obj.value = "";

	}
 
</script>
	</head>
	<body>
		<table id="dg" class="easyui-datagrid">
		</table>
		<!-- 新建框 &&编辑框-->
		<div id="dlg" class="easyui-dialog"
			style="width: 800px; height: 400px; padding: 10px 20px" closed="true"
			buttons="#dlg-buttons">
			<form id="fm" method="post"  >
				<table cellspacing="0" cellpadding="0" width="%100">
					<tbody>
						<tr>
							<td  >
								<font> 投资方名称：</font>
							</td>
							<td class="rtd">
								<input class="easyui-validatebox" required="true" missingMessage="投资方名称为必填项" type="text" name="investor.cInvName"
									id="investor.cInvName" onchange="checkcInvName()" />
								<label id="cInvName_label" style="color: #F00">
								</label> 
								<label id="checkmessage"></label>
								
							</td>
							</tr>
							 
						<tr>
							<td class="listltd">
								<font> 所属企业性质：</font>
							</td>
							<td class="rtd">
								<input class="easyui-combobox" name="nature" id="nature" 
									style="width: 140px"
									data-options="valueField:'id',textField:'text',url:'/canvassResource/front/investor_loadNatureData'" />
							 	 
							</td>

							<td class="listltd">
								<font>所属行业 ：</font>
							</td>
							<td class="rtd">
								<input class="easyui-combobox"   name="industry" id="industry"  
									style="width: 140px"
									data-options="valueField:'id',textField:'text',url:'/projectInformation/front/project_loadIndustryData'" />
								 </td>
						</tr>

						<tr>
							<td class="listltd">
								<font> 法人：</font>
							</td>
							<td class="rtd">
								<input class="easyui-validatebox" required="true" missingMessage="法人为必填项" type="text" name="investor.cInvLPerson" />
								 
							</td>
							<td >
								<font> 网址：</font>
							</td>
							<td class="rtd">
								<input   type="text" name="investor.cInvWebSite" value="请输入网址" />
							</td>
						</tr>
						<tr>
							<td class="listltd">
								<font> 固定电话：</font>
							</td>
							<td class="rtd">
								<input type="text" name="investor.cInvLPhone"
									onchange="isFixedPhone(this)" />
							</td>

							<td class="listltd">
								<font> 传真：</font>
							</td>
							<td class="rtd">
								<input type="text" name="investor.cInvLFax"
									onchange="isFaxNumber(this)" />
							</td>
						</tr>
						<tr>
							<td class="listltd">
								<font> 手机：</font>
							</td>
							<td class="rtd">
								<input type="text" name="investor.cInvLHand"
									onchange="isphoneNum(this)" />
							</td>

							<td class="listltd">
								<font> Email：</font>
							</td>
							<td class="rtd">
								<input type="text" name="investor.cInvLEmail"
									onchange="isEmail(this)" />
							</td>
						</tr>
						<tr>
							<td class="listltd">
								<font> 地址：</font>
							</td>
							<td class="rtd">
								<input  class="easyui-validatebox" required="true" missingMessage="地址为必填项" type="text" name="investor.cInvAddress" />
								 
							</td>
							<td class="listltd">
								<font>联系人：</font>
							</td>
							<td class="rtd">
								<input class="easyui-validatebox" required="true" missingMessage=">联系人为必填项" type="text" name="investor.cInvPerson" />
								 
							</td>
						</tr>
						<tr>
							<td class="listltd">
								<font> 注册资金：</font>
							</td>
							<td class="rtd">
								<input onchange="isMoney(this)" type="text"
									name="investor.dCapital" />

							</td>

							<td class="listltd">
								<font> 年产值：</font>
							</td>
							<td class="rtd">
								<input type="text" name="investor.dOValue"
									onchange="isMoney(this)" />

							</td>
						</tr>
						<tr>
							<td class="listltd">
								<font> 行业地位：</font>
							</td>
							<td class="rtd">
								<input type="text" name="investor.cIndustry" />

							</td>

							<td class="listltd">
								<font> 技术水平：</font>
							</td>
							<td class="rtd">
								<input type="text" name="investor.cLevel" />

							</td>
						</tr>
						<tr>
							<td class="listltd">
								<font> 主要产品：</font>
							</td>
							<td class="rtd">
								&nbsp;&nbsp;
								<textarea rows="5" cols="40" name="investor.cMainProducts"></textarea>
							</td>
						</tr>
						<tr>
							<td class="listltd">
								<font> 产品竞争力：</font>
							</td>
							<td class="rtd">

								&nbsp;&nbsp;
								<textarea rows="5" cols="40" name="investor.cCompeProducts"></textarea>
							</td>
						</tr>
						<tr>
							<td class="listltd">
								<font> 市场分额：</font>
							</td>
							<td class="rtd">
								&nbsp;&nbsp;
								<textarea rows="5" cols="40" name="investor.cMarketShare"></textarea>
							</td>
						</tr>
						<tr>
							<td class="listltd">
								<font> 产品销售对象信息：</font>
							</td>
							<td class="rtd">

								&nbsp;&nbsp;
								<textarea rows="5" cols="40" name="investor.cInformation"></textarea>
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
					<div data-options="name:'cInvLPerson',iconCls:'icon-ok'">
						法人
					</div>
					<div data-options="name:'cInvName'">
						投资方名称
					</div>
					<div data-options="name:'cInvPerson'">
						联系人
					</div>
				</div>
			</div>
		</div>
	</body>
</html>