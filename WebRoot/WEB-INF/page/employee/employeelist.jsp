<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp"%>
<html>
	<head>
		<title>用户权限分配</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/css/hlcss/list.css" />
		<script type="text/javascript" src="/js/jquery-1.7.2.min.js"></script>
		<SCRIPT language=JavaScript src="/js/FoshanRen.js"></SCRIPT>
		<script type="text/javascript" src="/js/dialog.js"></script>
		<script type="text/javascript" src="/js/common.js"></script>
		<script type="text/javascript" src="/js/layer.min.js"></script>

		<script type="text/javascript">
	function showDetail(temp) {
		var url = "/canvassGroup/front/hRPerson_getUserInfo?id=";
		url += temp;
		$.layer( {
			type : 2,
			title : false,
			border : [ 12, 0, 5, '#ccc', true ],
			iframe : {
				src : url
			},
			area : [ '750px', '500px' ],
			offset : [ '10px', '20px' ]

		});
	}
	//到指定的分页页面
	function topage(page) {
		var form = document.forms[0];
		form.page.value = page;
		form.submit();
	}
	//处理增加员工请求页面
	function add() {
		var form = document.forms[0];
		form.action = "/canvassGroup/front/hRPerson_inputUI";
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
		<form action="/control/employee/list" method="post">
			<input type="hidden" name="page" />
			<div class="ftitle"><font>用户权限分配</font></div>
			
			<table id="listTable" class="HeaderStyle" width="100%"  
					cellspacing="0">

				<tr height="10%">
					<th width="5%">
						序号
					</th>


					<th width="10%">
						用户名
					</th>
					<th width="10%">
						姓名
					</th>
					<th width="5%">
						性别
					</th>
					<th width="10%">
						部门名称
					</th>
					<th width="10%">
						查看详情
					</th>
					<th width="10%">
						操作
					</th>
				</tr>
				<c:forEach items="${pageView.records}" var="entry"
					varStatus="status">
					<tr >

						<td align="center" bgcolor="f5f5f5">
							${status.count}
						</td>
						<td bgcolor="f5f5f5">
							<div align="center">
								${entry.cUserName}
							</div>
						</td>
						<td bgcolor="f5f5f5">
							<div align="center">
								${entry.cPsnName}
							</div>
						</td>
						<td bgcolor="f5f5f5">
							<div align="center">
								<c:if test="${entry.rSex eq 'MALE'}">男</c:if>
								<c:if test="${entry.rSex eq 'FEMAL'}">女</c:if>
							</div>
						</td>
						<td bgcolor="f5f5f5">
							<div align="center">
								${entry.department.cDeptName}
							</div>
						</td>
						<td bgcolor="f5f5f5">
							<div align="center">
								<a href="#" onClick="showDetail('${entry.id}')"><img
										src="/images/Menu/view.png" alt="查看详情" title="查看详情" width="20"
										height="20" border="0"> </a>

							</div>
						</td>
						<td bgcolor="f5f5f5">
							<div align="center">

								<a
									href="/control/employee/privilegeGroupSet?employee.id=${entry.id}"><img
										title="设置权限" src="/images/Menu/edit.png" width="20"
										height="20" border="0"> </a>
							</div>
						</td>
					</tr>
				</c:forEach>
				<%@ include file="/WEB-INF/page/share/blank_7.jsp"%>
				<tr>
					<td colspan="12" align="right">
					<br />
					<br />
						<%@ include file="/WEB-INF/page/share/fenye.jsp"%>
					</td>
				</tr>
				
			</table>
		
		</form>
		</div>
	</body>
</html>