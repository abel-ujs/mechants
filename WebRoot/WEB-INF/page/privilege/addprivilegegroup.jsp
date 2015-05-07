<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp"%>
<html>
	<head>
		<title>添加角色</title>
		<link rel="stylesheet" href="/css/projectInformation/content.css" />
		<link rel="stylesheet" href="/css/projectInformation/content_list.css" />
		<link rel="stylesheet" href="/css/projectInformation/vip.css"
			type="text/css">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="/css/vip.css" type="text/css">
		<script>
function checkfm(){
	if(document.getElementById("group_name").value == ""){
		alert("权限组名称不能为空");
		return false;
	}
	return true;
}
</script>
	</head>
	<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0"
		marginwidth="0" marginheight="0">
		<div id="pform">
			<form action="/control/privilege/insert" method="post"
				onsubmit="return checkfm()">
				<div class="ftitle">
					<font>添加角色</font>
				</div>
				<div class="cont">
					<table cellspacing="0" cellpadding="0">

	
						<tr bgcolor="f5f5f5">
							<td width="22%">
								<div align="right">
								角色名称：</div>
							</td>
							<td width="78%">
								<input type="text" id="group_name" name="privilegeGroup.name"
									size="20" maxlength="20" />
								<font color="#FF0000">*</font>
							</td>
						</tr>
						
							<tr bgcolor="f5f5f5">
							<td width="22%" align="left" valign="top">
								<div align="right">
									选择权限：
								</div>
							</td>
							<td  width="78%">
								<table>
									<tr>
										<c:forEach items="${privileges}" var="privilege"
											varStatus="statu">
											<td>
												<input type="checkbox" name="privileges"
													value="${privilege.id.module},${privilege.id.privilege}">
												${privilege.name}
											</td>
											<c:if test="${statu.count%3==0}">
									</tr>
									<tr>
										</c:if>
										</c:forEach>
								</table>
							</td>
						</tr>
						<tr bgcolor="f5f5f5">
							<td colspan="2">
								<div style="margin-left:50px" >
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="submit" name="SYS_SET" value=" 确 定 "
										class="fButtonDel">&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="button" name="SYS_SET" value=" 取消 "
										class="fButtonDel"
										onclick="window.location.href='/control/privilege/list'">
								</div>
							</td>
							<td colspan="2">
								<div align="left">
								</div>
							</td>
						</tr>

					</table>
				</div>
			</form>
		</div>
	</body>
</html>