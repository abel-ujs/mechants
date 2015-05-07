<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp"%>
<html>
	<head>
		<title>员工详情</title>
<link rel="stylesheet" href="/css/projectInformation/content.css" />
<link rel="stylesheet" href="/css/projectInformation/content_list.css" />
<link rel="stylesheet" href="/css/projectInformation/vip.css" type="text/css">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="/css/formValidationStyle.css"
			type="text/css">
		<script language="javascript" src="/js/jquery-1.7.2.min.js"></script>
		<script language="javascript" src="/js/Validform_v5.3.2.js"></script>
		<script language="javascript" src="/js/DatePicker.js"></script>
		<script language="javascript" src="/js/ajax.js"></script>
		<script type="text/javascript">
		 <!-- 提示用户输入信息-->
		function setEmpty(obj){
		   obj.value="";
		
		}
	function closeFun() {
		var index = parent.layer.getFrameIndex();
		parent.layer.close(index);
	}
		function cPsnNameCheck() {
		 if(document.getElementById("systemUser.cPsnName").value=="")
		     alert("姓名不能为空");
	}
		function cUserNameCheck() {
		if(document.getElementById("systemUser.cUserName").value=="")
		     alert("用户名不能为空");
	}
	    
	 
	
</script>
	</head>

	<body>
<div id="pform">
		<form class="customerForm" action="/canvassGroup/front/hRPerson_save"
			method="post">
<div class="ftitle">
					<font>员工录入</font>
				</div>
				<div class="cont">
			<table cellspacing="0" cellpadding="0">
				<tbody>


					<tr>
						<td class="listltd">
							<font>姓名：</font>
						</td>
						<td class="listrtd" colspan="2">
							<input value="请输入姓名"  onclick="setEmpty(this)" onblur="cPsnNameCheck()" type="text" name="systemUser.cPsnName" id="systemUser.cPsnName"/>
							<label id="cProCode_label" style="color: #F00">
								*
							</label>
						</td>
					</tr>
					<tr>
						<td class="listltd">
							<font>用户名：</font>
						</td>
						<td class="listrtd" colspan="2">
							<input onclick="setEmpty(this)" onblur="cUserNameCheck()" type="text" name="systemUser.cUserName"
								id="systemUser.cUserName" value="请输入用户名" />
							<label id="cProCode_label" style="color: #F00">
								*
							</label>
						</td>
					</tr>


					<tr>
						<td class="listltd">
							<font> 部门：</font>
						</td>
						<td class="listrtd" colspan="2">
							<select name="departmentId" id="departmentId"  >
								<!-- 接收部门信息： -->
								<c:forEach items="${requestScope.departments}"
									var="systemUserIn">
									<option value="${systemUserIn.id}">
										${systemUserIn.cDeptName}
									</option>
								</c:forEach>
							</select>
							<label id="cProCode_label" style="color: #F00">
								*
							</label>
						</td>
					</tr>
					 
					<tr>
						<td class="listltd">
							<font>性别：</font>
						</td>
						<td class="listrtd" colspan="2">
							<select name="systemUser.rSex" id="systemUser.rSex">
								<option value="MALE" selected="selected">
									男
								</option>
								<option value="FEMAL">
									女
								</option>
							</select>
							<label id="cProCode_label" style="color: #F00">
								*
							</label>
						</td>

					</tr>
					<tr>
						<td class="listltd">
							<font>出生日期：</font>
						</td>
						<td class="listrtd" colspan="2">
							<input name="systemUser.dBirthday" type="text"
								onclick="javascript:HS_setDate(this)" />
						</td>
					</tr>
					<tr>
						<td class="listltd">
							<font>手机：</font>
						</td>
						<td class="listrtd" colspan="2">
							<input onchange="return isphoneNum(this)" type="text" name="systemUser.cPsnMobilePhone" />
							 
						</td>
					</tr>


					<tr>
						<td class="listltd">
							<font>办公电话：</font>
						</td>
						<td class="listrtd" colspan="2">

							<input onchange="return isFixedPhone(this)" type="text" name="systemUser.cPsnOPhone" />
						</td>
					</tr>

					<tr>
						<td class="listltd">
							<font>电子邮件：</font>
						</td>
						<td class="listrtd" colspan="2">

							<input onchange="return isEmail(this)" type="text" name="systemUser.cPsnEmail" />
						</td>
					</tr>

					<tr>
						<td class="listltd">
							<font>家庭住址：</font>
						</td>
						<td class="listrtd" colspan="2">

							<input type="text" name="systemUser.cPsnFAddr" />
						</td>
					</tr>
					<tr>
						<td class="listltd">
							<font>到职日期：</font>
						</td>
						<td class="listrtd" colspan="2">
							<input name="systemUser.dEnterUnitDate" type="text"
								onclick="javascript:HS_setDate(this)" />

						</td>
					</tr>
					<tr bgcolor="f5f5f5">
								<td width="25%">
									
								</td>
								<td width="75%">
									&nbsp;&nbsp;
									<input type="submit" value="确定" class="fButtonDel" />
									&nbsp;&nbsp;
									<input type="button" value="取消" class="fButtonDel" 
									onclick="javascript:window.location.href='/canvassGroup/front/hRPerson_list'"
									/>
								</td>

							</tr>
				</tbody>
			</table>
			</div>
		</form>
</div>
	</body>
</html>