<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp"%>
<html>
	<head>
		<title>产品类型选择</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<SCRIPT language=JavaScript src="/js/FoshanRen.js"></SCRIPT>
		 
		<SCRIPT language=JavaScript>
function checkIt(){
	var objForm = document.forms[0];
	var pDocument = opener.document;
	if (pDocument){
		pDocument.getElementById("person.id").value = objForm.dicId.value;
		pDocument.getElementById("person.cPsnName").value =objForm.dicName.value;
	}
	window.close();
}
function getDicName(dicId,strDicName){
	var objForm = document.forms[0];
	objForm.dicId.value = dicId;
	objForm.dicName.value = strDicName;
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
		人员列表,请选择:
		<form method="post" name="main" action="">
			<input type="hidden" name="dicId" />
			<input type="hidden" name="dicName" />
			<table width="100%" border="0">
				<tr>
					<c:forEach items="${hHPersons}" var="entity" varStatus="loopStatus">
						<td>
							<INPUT TYPE="radio" NAME="type"
								onclick="getDicName('${entity.id}','${entity.cPsnName}')" />
							${entity.cPsnName}

						</td>
<c:if test="${loopStatus.count%4==0}">
				</tr>
				<tr>
					</c:if>

					</c:forEach>
				</tr>
			</table>
			<input type='button' name='create' value=" 确 认 "
				onClick="javascript:checkIt()">
			<input type='button' name="cancel"
				onClick="javaScript:window.close()" value=" 取 消 ">

		</form>
	</body>
</html>