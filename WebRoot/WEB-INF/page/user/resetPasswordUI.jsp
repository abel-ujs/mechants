<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp"%>
<html>
	<head>
		<title>管理员重置用户密码</title>
		<link rel="stylesheet" href="/css/projectInformation/content.css" />
		<link rel="stylesheet" href="/css/projectInformation/content_list.css" />
		<link rel="stylesheet" href="/css/projectInformation/vip.css"
			type="text/css">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script type="text/javascript">
	//提示用户输入信息

	function setEmpty(obj) {
		obj.value = "";
	}
	</script>
	</head>
	<body>

		<div id="pform">
			<form class="projectForm"
				action="/canvassGroup/front/hRPerson_resetPassword" method="post">
				<input type="hidden" name="systemUser.id" value="${systemUser.id}" />

				<div class="ftitle">
					<font>管理员重置用户密码</font>
				</div>
				<div class="cont">
					<table cellspacing="0" cellpadding="0">
						<tbody>



							<tr>
								<td class="ltd">
									<font> 姓名 </font>
								</td>
								<td class="rtd">
									<input type="text" name="systemUser.cPsnName"
										value="${systemUser.cPsnName}" disabled="disabled" />
								</td>
							</tr>
							<tr>
								<td class="ltd">
									<font> 用户名 </font>
								</td>
								<td class="rtd">
									<input type="text" name="systemUser.cUserName"
										disabled="disabled" value="${systemUser.cUserName}"
										id="systemUser.cUserName" />
								</td>
							</tr>

							<tr>
								<td class="ltd">
									<font> 新密码 </font>
								</td>
								<td class="rtd">
									<input type="text" name="systemUser.cPassword"
										onclick="setEmpty(this)" id="systemUser.cPassword" value="请重置密码" />

								</td>
							</tr>


							<tr bgcolor="f5f5f5">
								<td width="25%">
								
								</td>
								<td width="75%">
									&nbsp;&nbsp;
									<input type="submit" value="修改" class="fButtonDel" />
									&nbsp;&nbsp;
									<input type="button" value="取消"
										onclick="javascript:window.location.href='/canvassGroup/front/hRPerson_list'"
										class="fButtonDel" />
								</td>

							</tr>
						</tbody>
					</table>
				</div>
			</form>
		</div>

	</body>
</html>