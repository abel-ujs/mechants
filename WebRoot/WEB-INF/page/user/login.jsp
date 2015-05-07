<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>科技新城招商跟踪管理平台-用户登录页面</title>
		<link rel="stylesheet" type="text/css"
			href="/css/user/login/common_index.css" />
		<link rel="stylesheet" type="text/css" href="/css/user/login/css.css">
		<script language="javascript" src="/js/jquery-1.7.2.min.js"></script>
		<script type="text/javascript">
   		function getCheckCode(imgObj){
   			imgObj.src = "/canvassGroup/front/hRPerson_generateCheckCode?" + Math.random();
   		}
   		function checkform(){
   			if(document.getElementById("systemUser.cUserName").value == ""){
   				alert("登录名不能为空");
   				return false;
   			}
   			if(document.getElementById("systemUser.cPassword").value == ""){
   				alert("密码不能为空");
   				return false;
   			}
   			if(document.getElementById("checkCode").value == ""){
   				alert("验证码不能为空");
   				return false;
   			}
   			return true;
   		}
   	</script>
	</head>
	<body>
		<form action="/canvassGroup/front/hRPerson_check" method="post"
			onSubmit="return checkform()">
			<div id="container">
				<div id="c-top">
				</div>
				<div id="c-title"></div>
				<div id="c-middle">
					<div id="c-middle-left"></div>
					<div id="c-middle-right">

						<table cellpadding="0" cellspacing="0" border="0">
							<tr>
								<td id="c-middle-texttitle">
									登录名：
								</td>
								<td>
									<input name="systemUser.cUserName" id="systemUser.cUserName" type="text" maxlength="24"
										class="Inputtxt1" style="width: 140px;">
								</td>
							</tr>
							<tr>
								<td id="c-middle-texttitle">
									密&nbsp;&nbsp;码：
								</td>
								<td>
									<input name="systemUser.cPassword" id="systemUser.cPassword" type="password"
										class="Inputtxt1" style="width: 140px;">
								</td>
							</tr>
							<tr>
								<td id="c-middle-texttitle">
									验证码：
								</td>
								<td class="code">
									<table border="0" cellpadding="0" cellspacing="0">
										<tbody>
											<tr>
												<td>
													<input name="checkCode" type="text" id="checkCode"
														class="Inputtxt1" style="width: 50px;">
												</td>
												<td>
													<div id="YZIMG">
														<img id="checkcode"
															src="/canvassGroup/front/hRPerson_generateCheckCode"
															onClick="getCheckCode(this)">
													</div>
												</td>
											</tr>
										</tbody>
									</table>
								</td>
							</tr>
							<tr>
								<td colspan="2" style="text-align: center;">
									<table style="border: none; margin: 7px 0 0 40px"
										cellspacing="0" cellpadding="0">
										<tr>
											<td class="btd">
												<input type="submit" name="btnLogin" value="登&nbsp;录"
													id="btnLogin" class="ButtonDel">
											</td>
											<td class="btd">
												<input type="button" name="btnClose" value="取&nbsp;消"
													id="btnLogin" class="ButtonDel">
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</div>
				</div>
				<div id="c-footer">
					<table>
						<tr>
							<td>
								技术支持：**************** 电话：0511-88892050 传真：0511-88892050-8009
							</td>
						</tr>
						<tr>
							<td>
								Copyright &copy;
								<script type="text/javascript">document.write(new Date().getFullYear().toString());</script>
								<a href="http://www.baidu.com/" target="_blank"
									title="镇江华扬信息科技有限公司">www.baidu.com</a> Allrights Reserved.
							</td>
						</tr>
					</table>
				</div>
			</div>
		</form>
	</body>
</html>
