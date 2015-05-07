<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp"%>
<html>
	<head>
		<title>用户信息维护</title>
		<link rel="stylesheet" href="/css/projectInformation/content.css" />
		<link rel="stylesheet" href="/css/projectInformation/content_list.css" />
		<link rel="stylesheet" href="/css/projectInformation/vip.css"
			type="text/css">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<SCRIPT language=JavaScript src="/js/FoshanRen.js"></SCRIPT>
		<script language="javascript" src="/js/jquery-1.7.2.min.js"></script>
		<script language="javascript" src="/js/ajax.js"></script>
		<script language="javascript" src="/js/DatePicker.js"></script>
 
	</head>
	<body>

		<div id="pform">
			<form class="projectForm"
				action="/canvassGroup/front/hRPerson_update" method="post">
				<input type="hidden" name="systemUser.id" value="${systemUser.id}" />
				<div class="ftitle">
					<font>用户信息维护：</font>
				</div>
				<div class="cont">
					<table cellspacing="0" cellpadding="0">
						<tbody>

							<tr>
								<td class="ltd">
									<font> 姓名 </font>
								</td>
								<td class="rtdchec">
									<input type="text" name="systemUser.cPsnName"
										value="${systemUser.cPsnName}" readonly="readonly" />
								</td>
							</tr>
							<tr>
								<td class="ltd">
									<font> 用户名 </font>
								</td>
								<td class="rtdchec">
									<input type="text" name="systemUser.cUserName"
										readonly="readonly" value="${systemUser.cUserName}"
										id="systemUser.cUserName" />
								</td>
							</tr>

							<tr>
								<td class="ltd">
									<font> 旧密码 </font>
								</td>
								<td class="rtdchec">
									<input type="password" name="systemUser.cPassword"
										id="systemUser.cPassword" />

								</td>
							</tr>
							<tr>
								<td class="ltd">
									<font> 新密码 </font>
								</td>
								<td class="rtdchec">
									<input type="password" name="password1" />
								</td>
							</tr>
							<tr>
								<td class="ltd">
									<font> 请再次输入新密码 </font>
								</td>
								<td class="rtdchec">
									<input type="password" name="password2" />
								</td>
							</tr>
							<tr>
								<td class="ltd">
									<font>性别：</font>
								</td>
								<td class="rtd">
									<select name="systemUser.rSex">

										<option value="MALE"
											<c:if test="${systemUser.rSex=='MALE'}"> selected="selected"</c:if>>
											男
										</option>

										<option value="FEMAL"
											<c:if test="${systemUser.rSex=='FEMAL'}"> selected="selected"</c:if>>
											女
										</option>
									</select>
								</td>
							</tr>
							<tr>
								<td class="ltd">
									<font> 出生日期</font>
								</td>
								<td class="rtdchec">
									<input type="text" name="systemUser.dBirthday"
										value="${systemUser.dBirthday}"
										onclick=
	javascript: HS_setDate(this);
/>
								</td>
							</tr>
							<tr>
								<td class="ltd">
									<font> 手机 </font>
								</td>
								<td class="rtdchec">
									<input type="text" name="systemUser.cPsnMobilePhone"
										onchange="isphoneNum(this)"
										value="${systemUser.cPsnMobilePhone}" />
									<label id="cProCode_label" style="color: #F00">
										*
									</label>
								</td>
							</tr>
							<tr>
								<td class="ltd">
									<font> 户籍</font>
								</td>
								<td class="rtdchec">
									<input type="text" name="systemUser.rPerResidence"
										value="${systemUser.rPerResidence}" />
								</td>
							</tr>

							<tr>
								<td class="ltd">
									<font> 身份证号码 </font>
								</td>
								<td class="rtdchec">
									<input type="text" name="systemUser.vIDNo"
										onchange="isIDNumber(this)" value="${systemUser.vIDNo}" />
									<label id="cProCode_label" style="color: #F00">
										*
									</label>
								</td>
							</tr>
							<tr>
								<td class="ltd">
									<font> 民族</font>
								</td>
								<td class="rtdchec">
									<input type="text" name="systemUser.rNational"
										value="${systemUser.rNational}" />
								</td>
							</tr>
							<tr>
								<td class="ltd">
									<font> 通讯地址 </font>
								</td>
								<td class="rtdchec">
									<input type="text" name="systemUser.cPsnPostAddr"
										value="${systemUser.cPsnPostAddr}" />
								</td>
							</tr>

							<tr bgcolor="f5f5f5">
								<td width="25%">
									<div align="right">
										<input type="submit" value="修改" class="fButtonDel" />
									</div>
								</td>
								<td width="75%">

									<input type="reset" value="重置" class="fButtonDel" />
								</td>

							</tr>
						</tbody>
					</table>
				</div>
			</form>
		</div>

	</body>
</html>