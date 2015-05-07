<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<link rel="stylesheet" href="/css/hlcss/list.css" type="text/css">
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>项目报表查看</title>
<script type="text/javascript" src="/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="/js/dialog.js"></script>
<script type="text/javascript" src="/js/common.js"></script>
<script type="text/javascript" src="/js/layer.min.js"></script>
<script type="text/javascript">
	//到指定的分页页面
	function topage(page) {
		var form = document.forms[0];
		form.page.value = page;
		form.submit();
	}
</script>
</head>
<body>
	<div id="pform">
		<form method="post"
			action="/projectInformation/front/project_projectEnd">
			<input type="hidden" name="page" value="${page}" />
			<div class="ftitle">
				<font>项目招商过程查看</font>
			</div>
			<div class="cont">
				<table class="HeaderStyle" width="100%" cellspacing="0">
					<tr>
						<th>ID</th>
						<th>项目名称</th>
						<th>项目来源</th>
						<th>所属行业</th>
						<th>投资规模(万)</th>
						<th>所属项目小组</th>
						<th>报表查看</th>
					</tr>
					<c:forEach items="${pageView.records}" var="entry"
						varStatus="status">
						<tr>
							<td bgcolor="f5f5f5">
								<div align="center">${entry.id }</div></td>
							<td bgcolor="f5f5f5">
								<div align="center">${entry.cProName}</div></td>

							<!-- 项目来源来判断 -->
							<c:if test="${entry.personal==null}">
								<td bgcolor="f5f5f5">
									<div align="center">介绍单位:${entry.customer.cCusName }</div></td>
							</c:if>
							<c:if test="${entry.customer==null}">
								<td bgcolor="f5f5f5">
									<div align="center">介绍人:${entry.personal.cPersonName}</div></td>
							</c:if>
							<td bgcolor="f5f5f5">
								<div align="center">${entry.industry.cInduName}</div></td>

							<td bgcolor="f5f5f5">
								<div align="center">${entry.dInvestment}</div></td>

							<td bgcolor="f5f5f5">
								<div align="center">${entry.projectGroup.cGroupName}</div></td>

							<td bgcolor="f5f5f5">
								<div align="center">
									<s:url id="reportURL" action="report_finalReport"
										namespace="/report" escapeAmp="false">
										<s:param name="pCode">${entry.cProCode}</s:param>
									</s:url>
									<s:a href="%{reportURL}" target="_blank">报表查看</s:a>
								</div></td>
						</tr>
					</c:forEach>

					<tr>
						<td colspan="12" align="right"><br /> <br /> <%@ include
								file="/WEB-INF/page/share/fenye.jsp"%></td>
					</tr>
				</table>
			</div>
		</form>
	</div>
</body>
</html>
