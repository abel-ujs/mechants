<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp"%>
<html>
	<head>
		<title>科技新城招商跟踪管理平台-部门信息录入</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="/css/projectInformation/content.css" />
		<link rel="stylesheet" href="/css/projectInformation/content_list.css" />
		<link rel="stylesheet" href="/css/projectInformation/vip.css"/>
		<script language="javascript" src="/js/DatePicker.js"></script>
		<script language="javascript" src="/js/jquery-1.7.2.min.js"></script>
		<script language="javascript" src="/js/ajax.js"></script>
		<script type="text/javascript">
	//提示用户输入信息

	function setEmpty(obj) {
		obj.value = "";
	}
</script>
		 
	</head>
  
	<body>

		<div id="pform">
			<form class="customerForm"
				action="/control/department/save" method="post">
				<div class="ftitle">
					<font>部门信息录入</font>
				</div>
				<div class="cont">
					<table cellspacing="0" cellpadding="0">
						<tbody>

							<tr>
								<td class="listltd">
									<font> 部门名称：</font>
								</td>
								<td class="rtd">
									<input type="text" name="department.cDeptName"
										id="department.decDeptName"   />
									<label id="cInvName_label" style="color: #F00">
										*
									</label>
									<label id="checkmessage"></label>
								</td>
							</tr>

							<tr>
								<td class="listltd">
									<font> 负责人：</font>
								</td>
								<td class="rtd">
									<input type="text" name="department.vrCharger" />
									<label id="label" style="color: #F00">
										*
									</label>
								</td>
							</tr>
							<tr>
								<td class="listltd">
									<font> 负责人电话：</font>
								</td>
								<td class="rtd">
									<input type="text" name="department.vrTel"   />
								</td>
							</tr>
							 
							 
							<tr>
								<td class="listltd">
									<font> 负责人职务：</font>
								</td>
								<td class="rtd">
									<input type="text" name="department.vrPostName" />
									<label id="label" style="color: #F00">
										*
									</label>
								</td>
							</tr>
							 
							<tr>
								<td colspan="2" id="butbg" style="border: none;">
									<table
										style="border: none; width: 300px; height: 31px; float: left; margin: 10px 0 10px 250px;"
										cellspacing="0" cellpadding="0">
										<tr>
											<td class="fbtd" style="border: none;">
												<input type="submit" value="提&nbsp;交" class="fButtonDel">
											</td>
											<td class="fbtd" style="border: none;">
												<input type="reset" value="取&nbsp;消" class="fButtonDel">
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</form>
		</div>

	</body>
</html>