<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp"%>
<html>
	<head>
		<title>项目来源选择</title>
<link rel="stylesheet" href="/css/projectInformation/content.css" />
<link rel="stylesheet" href="/css/projectInformation/content_list.css" />
<link rel="stylesheet" href="/css/projectInformation/vip.css" type="text/css">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<SCRIPT language=JavaScript src="/js/FoshanRen.js"></SCRIPT>
		 
		<SCRIPT language=JavaScript>
function checkIt(){
	var objForm = document.forms[0];
	var pDocument = opener.document;
	if (pDocument){
		pDocument.getElementById("id").value = objForm.dicId.value;
		pDocument.getElementById("type").value =objForm.dicType.value;
		pDocument.getElementById("echo").value =objForm.dicType.echo;
	}
	window.close();
}
function getDicName(dicId,strDicName,cPersonName){
	var objForm = document.forms[0];
	objForm.dicId.value = dicId;
	objForm.dicType.value = strDicName;
	objForm.dicType.echo = cPersonName;
}
 
</SCRIPT>
		<style>
<!--
.inputText {
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 12px;
	color: #666666;
	border: 1px solid #999999;
}

body {
	font-family: Georgia, "Times New Roman", Times, serif;
	font-size: 12px;
	color: #666666;
}
-->
</style>
	</head>

	<body>
	<div id="pform">
		<div class="ftitle"><font><c:if test="${requestScope.type eq 'personal'}">介绍人请选择</c:if></font>
		<font><c:if test="${requestScope.type eq 'customer'}">介绍单位请选择</c:if></font></div>
		<form method="post" name="main" action="">
			<input type="hidden" name="dicId" />
			<input type="hidden" name="dicType" />
			<input type="hidden" name="echo" />
			<table width="100%" border="0">
			<!-- 项目来源类型为介绍人 -->
			<c:if test="${requestScope.type eq 'personal'}">
				<tr>
					<c:forEach items="${personals}" var="entity" varStatus="loopStatus">
						<td>
							<INPUT TYPE="radio" NAME="type"
								onclick="getDicName('${entity.id}','personal','${entity.cPersonName}')" />
							${entity.cPersonName}

						</td>
<c:if test="${loopStatus.count%4==0}">

				</tr>
				<tr>
					</c:if>

					</c:forEach>
				</tr>
				</c:if>
				<!-- 项目来源类型为介绍单位 -->
				<c:if test="${requestScope.type eq 'customer'}">
				<tr>
					<c:forEach items="${customers}" var="entity" varStatus="loopStatus">
						<td>
							<INPUT TYPE="radio" NAME="type"
								onclick="getDicName('${entity.id}','customer','${entity.cCusName}')" />
							${entity.cCusName}

						</td>
<c:if test="${loopStatus.count%4==0}">

				</tr>
				<tr>
					</c:if>

					</c:forEach>
				</tr>
				</c:if>
			</table>
			<input type='button' name='create'  class="fButtonDel" value=" 确 认 "
				onClick="javascript:checkIt()" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type='button' name="cancel"
				onClick="javaScript:window.close()"  class="fButtonDel" value=" 取 消 " />

		</form>
		</div>
	</body>
</html>