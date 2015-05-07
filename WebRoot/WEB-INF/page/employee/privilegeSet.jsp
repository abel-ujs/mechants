<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp"%>
<html>
	<head>
		<title>权限设置</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="/css/projectInformation/content.css" />
		<link rel="stylesheet" href="/css/projectInformation/content_list.css" />
		<link rel="stylesheet" href="/css/projectInformation/vip.css"
			type="text/css">
		<SCRIPT language=JavaScript src="/js/FoshanRen.js"></SCRIPT>
	</head>
	<body>
		<div id="pform">
			<form action="/control/employee/updateEmployeePrivilege"
				method="post">
				<input type="hidden" name="employee.id" value="${employee.id }" />
				<div class="ftitle">
					<font>选择员工角色</font>
				</div>

				<div class="cont">
					<table cellspacing="0" cellpadding="0">
						<tbody>
						
							<tr bgcolor="f5f5f5">
								<c:forEach items="${groups}" var="privilegegroup"
									varStatus="statu">
									<td>
										<input type="checkbox" name="groupids"
											value="${privilegegroup.groupid}"
											<c:if test="${fn:contains(employee.groups,privilegegroup)}">checked</c:if>>
										${privilegegroup.name}
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<c:if test="${statu.count%8==0}">
											<br>
										</c:if>
									</td>
									<c:if test="${statu.count%2==0}">
									</tr>
									<tr>
									</c:if>
								</c:forEach>


							</tr>
							
							<tr bgcolor="f5f5f5">
								<td colspan="2">
									<br>
									<div align="left">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="submit" name="SYS_SET" value=" 确 定 "
											class="fButtonDel">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="button"
											onclick="window.location.href='/control/employee/list'"
											value="取消 " class="fButtonDel">
									</div>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</form>
		</div>
	</body>
</html>