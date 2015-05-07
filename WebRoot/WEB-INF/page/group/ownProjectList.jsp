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
<link rel="stylesheet" href="/css/formValidationStyle.css"
	type="text/css">
<link rel="stylesheet" href="/css/canvassResource/content.css" />
<link rel="stylesheet" href="/css/canvassResource/vip.css"
	type="text/css">
<base href="<%=basePath%>">

<title>My JSP 'ownProjectList.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
</head>
<body>
<div id="pform">
<div class="cont">
	<table>
		<tr>
			<td><select style="width:100%;" size=5>
					<c:forEach items="${requestScope.projectList}" var="entity">
						<option>${entity.cProName}</option>
					</c:forEach>
			</select>
		</tr>
	</table>
	</div>
	</div>
</body>
</html>
