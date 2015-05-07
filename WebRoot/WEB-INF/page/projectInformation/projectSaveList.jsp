<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp"%>
<html>
	<head>
		<title>项目信息列表</title>
		<link rel="stylesheet" type="text/css" href="/css/easyUI/easyui.css">
		<link rel="stylesheet" type="text/css" href="/css/easyUI/icon.css">
		<link rel="stylesheet" type="text/css" href="/css/easyUI/demo.css">
		<script type="text/javascript" src="/js/easyUI/jquery.js"></script>
		<script type="text/javascript" src="/js/easyUI/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="/js/easyUI/projectSaveList.js"></script>
		<SCRIPT language=JavaScript src="/js/FoshanRen.js"></SCRIPT>
		<script language="javascript" src="/js/ajax.js"></script>
		<script language="javascript" src="/js/DatePicker.js"></script>
		<script type="text/javascript"> 
	     //处理项目来源选择事件 
			function cSourceSelect(obj) {
		 var type ="";
		  if (document.forms[1].cSource[0].checked){
		  type="personal";
		  }else{
		  type="customer";
		  }
	     winOpen('/projectInformation/front/project_selectProjectSourceUI?type='+type,'项目来源选择',600,400);
	}
	 //  提示用户输入信息
		function setEmpty(obj){
		   obj.value="";
		
		}
		function confirm(){
		    document.getElementById("project.iVerify").value="3";
		    save();
		} 
		
		 function persist(){
		    document.getElementById("project.iVerify").value="0";
		    save();
		} 
		function editconfirm(){
			
		    document.getElementById("iVerify").value="3";
		    editsave();
		  
		} 
		
		 function editpersist(){
		    document.getElementById("iVerify").value="0";
		    editsave();
		} 
		 </script>
	</head>
	<body>
		<table id="dg" class="easyui-datagrid">
		</table>
		
		<!--编辑框-->
		<div id="dlgedit" class="easyui-dialog"
			style="width:800px; height:400px; padding: 10px 20px" closed="true"
			buttons="#dlgedit-buttons">
			 
			<form id="fmedit" method="post" novalidate>
			<input type="hidden" name="iVerify" id="iVerify" />
				 <table cellspacing="0" cellpadding="0">
						<tbody>
							<tr>
								<td class="listltd">
									<font> 项目来源： </font>
								</td>
								<td class="rtdchec">
								<input type="text" id="echo2" value=""
										 disabled="disabled" name="cSource">
								 
									
								</td>
							 </tr>
							<tr>
								<td class="listltd">
									<font>项目编码 ：</font>
								</td>
								<td class="rtd">
									<input type="text" id="cProCode"
										name="cProCode" disabled="disabled" />
									

								</td>
							</tr>
							<tr>
								<td class="listltd">
									<font>项目名称 ：</font>
								</td>
								<td class="rtd">
									<input type="text" name="cProName"
										id="cProName" value="请输入项目名称" disabled="disabled"
										 />
								</td>
							  
								<td class="listltd">
									<font> 投资方：</font>
								</td>
								<td class="rtd">
									<input name="investor" id="investor" class="easyui-combobox" style="width:200px"
							data-options="valueField:'id',textField:'text',url:'/projectInformation/front/project_loadInvestorData'"  />
								</td>
							 </tr>
							 <tr>
							 
								<td class="listltd">
									<font>所属行业 ：</font>
								</td>
								<td class="rtd">
									<input name="industry" id="industry" class="easyui-combobox" style="width:200px"
data-options="valueField:'id',textField:'text',url:'/projectInformation/front/project_loadIndustryData'"  
/>
									 
								</td>
							 
								<td class="listltd">
									<font>入住园区目的 ：</font>
								</td>
								<td class="rtd">
									<select class="easyui-validatebox" required="true" missingMessage="入住园区目的为必选项"  name="iObjective" id="iObjective"  >

										<option value="0"  >
											扩大产能
										</option>
										<option value="1">
											企业搬迁
										</option>
										<option value="2">
											开发新产品
										</option>
										<option value="3">
											创业
										</option>
									</select>
								</td>
							</tr>

							<tr>
								<td class="listltd">
									<font>项目类别 ：</font>
								</td>
								<td class="rtd">
									<select class="easyui-validatebox" required="true" missingMessage="项目类别为必选项"  name="cCategory"  id="cCategory" >

										<option value="0"  >
											平台类项目
										</option>
										<option value="1">
											产业性项目
										</option>
										<option value="2">
											现代服务业项目
										</option>

									</select>
								</td>
							</tr>
							<tr>
								<td class="listltd">
									<font>投资规模：</font>
								</td>
								<td class="rtd">
									<input type="text" name="dInvestment" 
										onchange="isMoney(this)" 
										  />
								</td>
							</tr>

							<tr>
								<td class="listltd">
									<font>主要业务：</font>
								</td>
								<td class="rtd">
									
									<textarea rows="5" cols="40" name="cMainBuessiness"
										 ></textarea>
								</td>
							</tr>
							<tr>
								<td class="listltd">
									<font>商业模式：</font>
								</td>
								<td class="rtd">
								
									<textarea rows="5" cols="40" name="cBusinessModel"
										 ></textarea>

								</td>
							</tr>
							<tr>
								<td class="listltd">
									<font>投资推进计划：</font>
								</td>
								<td class="rtd">
									
									<textarea rows="5" cols="40" name="cPromotionPlan"
										 ></textarea>
								</td>
							</tr>
							<tr>
								<td class="listltd">
									<font>主要产品：</font>
								</td>
								<td class="rtd">
									
									<textarea rows="5" cols="40" name="cMainProducts"
										 ></textarea>
								</td>
							</tr>
							<tr>
								<td class="listltd">
									<font>市场营销策略：</font>
								</td>
								<td class="rtd">
								
									<textarea rows="5" cols="40" name="cMarketingInfo"
										 ></textarea>

								</td>
							</tr>
 
						</tbody>
					</table>
			</form>
		</div>
		<div id="dlgedit-buttons"  >
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-ok" onclick="editconfirm()">提交</a>
				<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-ok" onclick="editpersist()">编辑</a>
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-cancel" onclick=javascript:$('#dlgedit').dialog('close');
>取消</a>
		</div>
		
		<!-- 新建框 -->
		<div id="dlg" class="easyui-dialog"
			style="width:800px; height:400px; padding: 10px 20px" closed="true"
			buttons="#dlg-buttons">
			 
			<form id="fm" method="post" novalidate>
			<input type="hidden" name="project.iVerify" id="project.iVerify" />
			  
				 <table cellspacing="0" cellpadding="0">
						<tbody>
							<tr>
								<td class="listltd">
									<font> 项目来源： </font>
								</td>
								<td class="rtdchec">
								<input type="text" id="echo" value=""
										 disabled="disabled">
								 
									
									<input type="radio"   name="cSource"
										value="personal" onClick="cSourceSelect(this)"><font>介绍人</font>
									
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="cSource" value="customer"
										onClick="cSourceSelect(this)"><font>介绍单位</font>

									<!-- 用来接收项目来源类型  personal或 customer-->

									<input type="hidden" name="id" id="id">
									<input type="hidden" name="type" id="type">
								</td>
							 </tr>
							<tr>
								<td class="listltd">
									<font>项目编码 ：</font>
								</td>
								<td class="rtd">
									<input  class="easyui-validatebox" required="true" missingMessage="项目编码为必填项"  type="text" id="project.cProCode"
										name="project.cProCode" value="请输入项目编码"
										onclick="setEmpty(this)" onChange="checkcProCode()" />
									<label id="cProCode_label" style="color: #F00">
									</label>
									<label id="checkmessage">项目编码格式（年+月+四位编码）如：2013010001</label>

								</td>
							</tr>
							<tr>
								<td class="listltd">
									<font>项目名称 ：</font>
								</td>
								<td class="rtd">
									<input class="easyui-validatebox" required="true" missingMessage="项目名称为必填项" type="text" name="project.cProName"
										id="project.cProName" value="请输入项目名称" onClick="setEmpty(this)"
										datatype="*" onChange="checkcProName()" errormsg="项目名称不能为空!" />
									<label id="cProName_label" style="color: #F00">
										 
									</label>
									<label id="cProNameMessage"></label>
								</td>
							  
								<td class="listltd">
									<font> 投资方：</font>
								</td>
								<td class="rtd">
									<input name="investor.id" id="investor" class="easyui-combobox" style="width:200px"
							data-options="valueField:'id',textField:'text',url:'/projectInformation/front/project_loadInvestorData'"  />
								</td>
							 </tr>
							 <tr>
							 
								<td class="listltd">
									<font>所属行业 ：</font>
								</td>
								<td class="rtd">
									<input name="industry.id" id="industry" class="easyui-combobox" style="width:200px"
data-options="valueField:'id',textField:'text',url:'/projectInformation/front/project_loadIndustryData'"  
/>
									 
								</td>
							 
								<td class="listltd">
									<font>入住园区目的 ：</font>
								</td>
								<td class="rtd">
									<select name="project.iObjective">

										<option value="0" selected="selected">
											扩大产能
										</option>
										<option value="1">
											企业搬迁
										</option>
										<option value="2">
											开发新产品
										</option>
										<option value="3">
											创业
										</option>
									</select>
								</td>
							</tr>

							<tr>
								<td class="listltd">
									<font>项目类别 ：</font>
								</td>
								<td class="rtd">
									<select name="project.cCategory">

										<option value="0" selected="selected">
											平台类项目
										</option>
										<option value="1">
											产业性项目
										</option>
										<option value="2">
											现代服务业项目
										</option>

									</select>
								</td>
							</tr>
							<tr>
								<td class="listltd">
									<font>投资规模：</font>
								</td>
								<td class="rtd">
									<input type="text" name="project.dInvestment" value="请输入数字"
										onchange="isMoney(this)" 
										onclick="setEmpty(this)" />
								</td>
							</tr>

							<tr>
								<td class="listltd">
									<font>主要业务：</font>
								</td>
								<td class="rtd">
									
									<textarea rows="5" cols="40" name="project.cMainBuessiness"
										onclick="setEmpty(this)">请输入主要业务</textarea>
								</td>
							</tr>
							<tr>
								<td class="listltd">
									<font>商业模式：</font>
								</td>
								<td class="rtd">
								
									<textarea rows="5" cols="40" name="project.cBusinessModel"
										onclick="setEmpty(this)">请输入商业模式</textarea>

								</td>
							</tr>
							<tr>
								<td class="listltd">
									<font>投资推进计划：</font>
								</td>
								<td class="rtd">
									
									<textarea rows="5" cols="40" name="project.cPromotionPlan"
										onclick="setEmpty(this)">请输入投资推进计划</textarea>
								</td>
							</tr>
							<tr>
								<td class="listltd">
									<font>主要产品：</font>
								</td>
								<td class="rtd">
									
									<textarea rows="5" cols="40" name="project.cMainProducts"
										onclick="setEmpty(this)">请输入主要产品</textarea>
								</td>
							</tr>
							<tr>
								<td class="listltd">
									<font>市场营销策略：</font>
								</td>
								<td class="rtd">
								
									<textarea rows="5" cols="40" name="project.cMarketingInfo"
										onclick="setEmpty(this)">请输入市场营销策略</textarea>

								</td>
							</tr>
 
						</tbody>
					</table>
			</form>
		</div>
		<div id="dlg-buttons"  >
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-ok" onclick="confirm()">提交</a>
				<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-ok" onclick="persist()">保存</a>
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
					<div data-options="name:'cProCode',iconCls:'icon-ok'">
						项目编号
					</div>
					<div data-options="name:'cProName'">
						项目名称
					</div>
				</div>
			</div>
		</div>
	</body>
</html>