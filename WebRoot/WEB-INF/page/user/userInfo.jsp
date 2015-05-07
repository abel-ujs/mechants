<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp"%>
<html>
	<head>
		<title>员工详情</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="/css/formValidationStyle.css"
			type="text/css">
		<link rel="stylesheet" href="/css/projectInformation/content.css" />
		<link rel="stylesheet" href="/css/projectInformation/content_list.css" />
		<link rel="stylesheet" href="/css/projectInformation/vip.css"
			type="text/css">
		<script type="text/javascript">
	function closeFun() {
		var index = parent.layer.getFrameIndex();
		parent.layer.close(index);
	}
</script>
	</head>

	<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0"
		marginwidth="0" marginheight="0">
   
	 
		<div id="pform">
			 
				<div class="ftitle">
					<font>员工详情</font>
				</div>
				<div class="cont">
					<table cellspacing="0" cellpadding="0">
						<tbody>
							<tr>
								<td class="listltd">
									<font>员工ID：</font>
								</td>
								<td class="listrtd" colspan="2">
									<input type="text" value="${entity.id}" disabled="disabled" />
								</td>
							</tr>
							 
							<tr>
								<td class="listltd">
									<font>姓名：</font>
								</td>
								<td class="listrtd" colspan="2">
									<input type="text" value="${entity.cPsnName}"
										disabled="disabled" />
								</td>
							</tr>
							<tr>
								<td class="listltd">
									<font>用户名：</font>
								</td>
								<td class="listrtd" colspan="2">
									<input type="text" value="${entity.cUserName}"
										disabled="disabled" />
								</td>
							</tr>
							 
							<tr>
								<td class="listltd">
									<font>部门：</font>
								</td>
								<td class="listrtd" colspan="2">
									<input type="text" value="${entity.department.cDeptName}"
										disabled="disabled" />
								</td>
							</tr>
							 
							<tr>
								<td class="listltd">
									<font>性别：</font>
								</td>
								<c:if test="${entity.rSex eq 'MALE'}">
									<td class="listrtd" colspan="2">
										<input type="text" value="男" disabled="disabled" />
									</td>
								</c:if>
								<c:if test="${entity.rSex eq 'FEMAL'}">
									<td class="listrtd">
										<input type="text" value="女" disabled="disabled" />
									</td>
								</c:if>

							</tr>
							<tr>
								<td class="listltd">
									<font>出生日期：</font>
								</td>
								<td class="listrtd" colspan="2">
									<input type="text" value="${entity.dBirthday}"
										disabled="disabled" />
								</td>
							</tr>
							<tr>
								<td class="listltd">
									<font>手机：</font>
								</td>
								<td class="listrtd" colspan="2">
									<input type="text" value="${entity.cPsnMobilePhone}"
										disabled="disabled" />
								</td>
							</tr>
							 
							 
							<tr>
								<td class="listltd">
									<font>办公电话：</font>
								</td>
								<td class="listrtd" colspan="2">
									<input type="text" value="${entity.cPsnOPhone}"
										disabled="disabled" />
								</td>
							</tr>
							
							<tr>
								<td class="listltd">
									<font>电子邮件：</font>
								</td>
								<td class="listrtd" colspan="2">
									<input type="text" value="${entity.cPsnEmail}"
										disabled="disabled" />
								</td>
							</tr>
							 
							
							<tr>
								<td class="listltd">
									<font>家庭住址：</font>
								</td>
								<td class="listrtd" colspan="2">
									<input type="text" value="${entity.cPsnFAddr}"
										disabled="disabled" />
								</td>
							</tr>
							 
							
							<tr>
								<td class="listltd">
									<font>到职日期：</font>
								</td>
								<td class="listrtd" colspan="2">
									<input type="text" value="${entity.dEnterUnitDate}"
										disabled="disabled" />
								</td>
							</tr>
						 	
							<tr>
								<td class="listltd">
									<font>所属项目小组 ：</font>
								</td>
								<td class="listrtd" colspan="2">
									<input type="text" value="${entity.projectGroup.cGroupName}"
										disabled="disabled" />
								</td>
							</tr>
				 
 
						</tbody>
					</table>
				</div>
		  
		</div>
	</body>
</html>