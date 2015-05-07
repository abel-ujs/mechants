<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<title>员工列表</title>
		<link rel="stylesheet" href="/css/hlcss/list.css" />
		<script type="text/javascript" src="/js/jquery-1.7.2.min.js"></script>
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
					border:[12,0,5,'#ccc',true],
					iframe : {
						src : url
					},
					area : [ '750px', '500px' ],
					offset : [ '10px', '0px' ]
					
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
	    form.action="/canvassGroup/front/hRPerson_inputUI";
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
			<div class="ftitle">
				<font>员工列表</font>
			</div>

			<form method="post" action="/canvassGroup/front/hRPerson_list">
				<input type="hidden" name="page" />
				<table id="listTable" class="HeaderStyle" width="100%"
					cellspacing="0">

					<tr>

						<th width="5%">
							序号
						</th>

						<th width="8%">
							用户名
						</th>
						<th width="8%">
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
						<th width="8%">
							操作
						</th>
					</tr>
					<c:forEach items="${pageView.records}" var="entry"
						varStatus="status">
						<tr>
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
									<a href="#" onclick="showDetail('${entry.id}')"><img
											src="/images/Menu/view.png" alt="查看详情" title="查看详情"
											width="20" height="20" border="0"> </a>

								</div>
							</td>
							<td bgcolor="f5f5f5">
								<div align="center">
 
									<a
										href="/canvassGroup/front/hRPerson_adminUpdateUI?id=${entry.id}"><img
											title="重置密码" src="/images/Menu/edit.png" width="20"
											height="20" border="0"> </a>&nbsp;&nbsp;&nbsp;
									<a href="/canvassGroup/front/hRPerson_delete?id=${entry.id}"><img
											title="删除用户" src="/images/Menu/delete.png" width="20"
											onclick="return deleteConfirm('您确定删除该用户吗?')" height="20"
											border="0"> </a>&nbsp;&nbsp;&nbsp;
									<!--  
									<a href="/canvassGroup/front/hRPerson_changeState?id=${entry.id}&type=on"><img
											title="启用" src="/images/Menu/delete.png" width="20"
											height="20" border="0"> </a>
									<a href="/canvassGroup/front/hRPerson_changeState?id=${entry.id}&type=off"><img
											title="停用" src="/images/Menu/delete.png" width="20"
											height="20" border="0"> </a>	-->
								</div>
							</td>
						</tr>
					</c:forEach>

					<tr>


						<td colspan="7" align="right">
							<br />
							<br />
							<%@ include file="/WEB-INF/page/share/fenye.jsp"%>
						</td>
					</tr>
					<tr bgcolor="f5f5f5">
						<td colspan="12">
							<div align="left">
								<input type="button" value="增加新员工" class="fButtonDel"
									onclick="add()" />
							</div>
						</td>
					</tr>
				</table>




			</form>
		</div>
	</body>
</html>
