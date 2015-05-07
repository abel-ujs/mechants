<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<link rel="stylesheet" href="/css/hlcss/list.css" type="text/css">
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>已提交意向项目信息列表</title>
<script type="text/javascript" src="/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="/js/dialog.js"></script>
<script type="text/javascript" src="/js/common.js"></script>
</head>
<body>
	<div class="cont-list">
		<table class="HeaderStyle" width="100%" cellspacing="0">
			<tr>
				<th>项目编码</th>
				<th>项目名称</th>
				<th>项目状态</th>
				<th>行业</th>
				<th>入园目的</th>
				<th>项目类别</th>
				<th>项目来源</th>
				<th>投资规模</th>
			</tr>
			<c:forEach items="${pageView.records}" var="entry" varStatus="status">
				<tr>

					<td bgcolor="f5f5f5">
						<div align="center">${entry.cProCode}</div></td>
					<td bgcolor="f5f5f5">
						<div align="center">${entry.cProName}</div></td>
					<!-- 
	                         项目状态 
	        1-意向项目；2-在谈项目；3-在谈结束;4-签约项目；5-落户项目；6-达产达效项目
	        7-增资项目；0-结束项目；-1-不引进项目
	       -->
					<c:choose>
						<c:when test="${entry.cState==1}">
							<td bgcolor="f5f5f5">
								<div align="center">意向项目</div></td>
						</c:when>
						<c:when test="${entry.cState==2}">

							<td bgcolor="f5f5f5">
								<div align="center">在谈项目</div></td>
						</c:when>
						<c:when test="${entry.cState==3}">
							<td bgcolor="f5f5f5">
								<div align="center">在谈结束</div></td>
						</c:when>
						<c:when test="${entry.cState==4}">
							<td bgcolor="f5f5f5">
								<div align="center">签约项目</div></td>
						</c:when>
						<c:when test="${entry.cState==5}">
							<td bgcolor="f5f5f5">
								<div align="center">落户项目</div></td>
						</c:when>
						<c:when test="${entry.cState==6}">
							<td bgcolor="f5f5f5">
								<div align="center">达产达效项目</div></td>
						</c:when>
						<c:when test="${entry.cState==7}">
							<td bgcolor="f5f5f5">
								<div align="center">增资项目</div></td>
						</c:when>
						<c:when test="${entry.cState==0}">
							<td bgcolor="f5f5f5">
								<div align="center">结束项目</div></td>
						</c:when>
						<c:when test="${entry.cState==-1}">
							<td bgcolor="f5f5f5">
								<div align="center">不引进项目</div></td>
						</c:when>
					</c:choose>
					<td bgcolor="f5f5f5">
						<div align="center">${entry.industry.cInduName}</div></td>
					<!--  入住园区目的（0-扩大产能、1-企业搬迁、2-开发新产品、3-创业 -->
					<c:choose>
						<c:when test="${entry.iObjective==0}">
							<td bgcolor="f5f5f5">
								<div align="center">扩大产能</div></td>
						</c:when>
						<c:when test="${entry.iObjective==1}">
							<td bgcolor="f5f5f5">
								<div align="center">企业搬迁</div></td>
						</c:when>
						<c:when test="${entry.iObjective==2}">
							<td bgcolor="f5f5f5">
								<div align="center">开发新产品</div></td>
						</c:when>
						<c:when test="${entry.iObjective==3}">
							<td bgcolor="f5f5f5">
								<div align="center">创业</div></td>
						</c:when>
					</c:choose>

					<!-- 项目类别（0：平台类项目 1：产业性项目 2：现代服务业项目 -->
					<c:choose>
						<c:when test="${entry.cCategory==0}">
							<td bgcolor="f5f5f5">
								<div align="center">平台类项目</div></td>
						</c:when>
						<c:when test="${entry.cCategory==1}">
							<td bgcolor="f5f5f5">
								<div align="center">产业性项目</div></td>
						</c:when>
						<c:when test="${entry.cCategory==2}">
							<td bgcolor="f5f5f5">
								<div align="center">现代服务业项目</div></td>
						</c:when>
					</c:choose>
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
						<div align="center">${entry.dInvestment}</div></td>
			</c:forEach>

			<tr>
				<td colspan="12" align="right"><br /> <br /> <%@ include
						file="/WEB-INF/page/share/fenye.jsp"%></td>
			</tr>
		</table>
	</div>
</body>
</html>
