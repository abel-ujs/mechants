<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp"%>
<html>
	<head>
		<title>部门信息列表</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/css/hlcss/list.css" />
		<script type="text/javascript" src="/js/jquery-1.7.2.min.js"></script>
		<SCRIPT language=JavaScript src="/js/FoshanRen.js"></SCRIPT>
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
	//处理增加新部门请求页面
	function add() {
		var form = document.forms[0];
		form.action = "/control/department/inputUI";
		form.submit();
	}
</script>
	<style type="text/css">
a {
	text-decoration: none;
}

table {
	border-collapse: collapse;
	font-size: 9pt;
	WORD-BREAK: break-all;
}

.HeaderStyle TH {
	height: 26px;
	background-color: #D7E7F6;
	color: #125277;
	font-size: 9pt;
	border: 1px solid #ccc;
	border-collapse: collapse;
}

td {
	border: 1px solid #ccc;
	border-collapse: collapse;
}
</style>
</head>

	<body>
	<div id="pform">
		<form action="/control/department/list" method="post">
			<input type="hidden" name="page" />
			<div class="ftitle"><font>部门信息列表</font></div>
	 
			<table id="listTable" class="HeaderStyle" width="100%"  
					cellspacing="0">

				<tr>
					<th width="5%">
						序号
					</th>
					<th width="10%">
						 部门名称
					</th>
					<th width="10%">
						创建日期
					</th>
					<th width="5%">
						负责人
					</th>
					<th width="10%">
						负责人电话
					</th>
					<th width="10%">
						职务
					</th>
					<th width="10%">
						操作
					</th>
				</tr>
				<c:forEach items="${pageView.records}" var="entry"
					varStatus="status">
					<tr height="10%">

						<td align="center" bgcolor="f5f5f5">
							${status.count}
						</td>
						<td bgcolor="f5f5f5">
							<div align="center">
								${entry.cDeptName}
							</div>
						</td>
						<td bgcolor="f5f5f5">
							<div align="center">
								${entry.dOrganizeDate}
							</div>
						</td>
						 
						<td bgcolor="f5f5f5">
							<div align="center">
								${entry.vrCharger}
							</div>
						</td>
						<td bgcolor="f5f5f5">
							<div align="center">
								${entry.vrTel}
							</div>
						</td>
						<td bgcolor="f5f5f5">
							<div align="center">
								${entry.vrPostName}
							</div>
						</td>
						<td bgcolor="f5f5f5">
							<div align="center">

								<a
										href="/control/department/updateUI?id=${entry.id}"><img
											title="部门修改" src="/images/Menu/edit.png" width="20"
											height="20" border="0"> </a>&nbsp;&nbsp;&nbsp;
									<a href="/control/department/delete?id=${entry.id}"><img
											title="删除部门" src="/images/Menu/delete.png" width="20"
											onclick="return deleteConfirm('您确定删除该部门吗?')" height="20"
											border="0"> </a>
							</div>
						</td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="12" align="right">
					<br />
					<br />
						<%@ include file="/WEB-INF/page/share/fenye.jsp"%>
					</td>
				</tr>
				<tr bgcolor="f5f5f5">
						<td colspan="12">
							<div align="left">
								<input type="button" value="增加新部门" class="fButtonDel"
									onclick="add()" />
							</div>
						</td>
					</tr>
			</table>
		
		</form>
		</div>
	</body>
</html>