<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<link rel="stylesheet" href="/css/formValidationStyle.css"type="text/css">
<link rel="stylesheet" href="/css/canvassResource/content.css" />
<link rel="stylesheet" href="/css/canvassResource/vip.css"type="text/css">
<script type="text/javascript" src="/js/DatePicker.js"></script>
<script type="text/javascript" src="/js/common.js"></script>
<SCRIPT type=text/javascript src="/js/FoshanRen.js"></SCRIPT>
<script type="text/javascript" src="/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="/js/jquery.metadata.js"></script>
<script type="text/javascript" src="/js/jquery.validate.js"></script>
<script type="text/javascript" src="/js/layer.min.js"></script>
<script type="text/javascript">
	function selected(code) {
		$("#temp").val(code);
	}
</script>
<script>
	function personAssigned() {
		var group = $("#group").val();
		var person = $("#temp").val();
		if(person=="") { layer.msg("请选择人员",1,4);return false;}
		var URL = "/canvassGroup/front/canvassGroup_addPerson";
		$.ajax({
			url : URL,
			data : {
				"group" : group,
				"person" : person
			},
			async : true,
			type : 'post',
			success : function(result) {
				if (result == "ok")
					layer.msg('成功添加人员', 1, 8);
				var i = parent.layer.getFrameIndex();
				parent.layer.close(i);
			}
		});
	}
</script>

<script type="text/javascript">
	function cancelAssigned() {
		var i = parent.layer.getFrameIndex();
		parent.layer.close(i);
	}
</script>

<base href="<%=basePath%>">

<title>My JSP 'ownProjectList.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<body>
<div id="pform">
<div class="cont">
	<input type="hidden" value="" id="temp" />
	<input type="hidden" value="${group}" id="group" />
	<table width="100%" border="0">
		<tr>
			<c:forEach items="${HRPersons}" var="entity" varStatus="loopStatus">
				<td width="25%"><INPUT TYPE="radio" name="select" id="person"
					onclick="selected('${entity.id}')" /> ${entity.cPsnName}</td>
				<c:if test="${loopStatus.count%4==0}">
		</tr>
		<tr>
			</c:if>
			</c:forEach>
		</tr>
		<br>
		<tr>
		<td/>
			<td align="center">
			<input type="button" value="确定" class="fButtonDel" onclick="personAssigned()"/>
			</td>
			<td align="center">
			<input type="button" value="取消" class="fButtonDel" onclick="cancelAssigned()"/>
			</td>
			<td/>
		</tr>
	</table>
	</div>
</body>
</html>
