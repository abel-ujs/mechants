<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<title>介绍单位查看列表</title>
		<link rel="stylesheet" href="/css/hlcss/list.css" />
		<script type="text/javascript" src="/js/jquery-1.7.2.min.js"></script>
		<script type="text/javascript" src="/js/dialog.js"></script>
		<script type="text/javascript" src="/js/layer.min.js"></script>
		<script type="text/javascript" src="/js/common.js"></script>
		<script type="text/javascript">
	//弹出详细的效果
	function showDetail(temp) {
		var url = "investor_getInvestorInfo?id=";
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
				<font>投资方查看列表</font>
			</div>
			<form method="post"
				action="/canvassResource/front/investor_displayList">
				<input type="hidden" name="page" value=${page }/>

				<table id="listTable" class="HeaderStyle" width="100%"
					cellspacing="0">

					<tr>
						<th width="5%">
							序号
						</th>

						<th width="20%">
							投资方名称
						</th>


						<th width="10%">
							法人
						</th>

						<th width="10%">
							行业
						</th>

						<th width="10%">
							联系人
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
						<tr>

							<td bgcolor="f5f5f5">
								<div align="center">
									${status.count }
								</div>
							</td>

							<td bgcolor="f5f5f5">
								<div align="center">
									${entry.cInvName}
								</div>
							</td>
							<td bgcolor="f5f5f5">
								<div align="center">
									${entry.cInvLPerson }
								</div>
							</td>


							<td bgcolor="f5f5f5">
								<div align="center">
									${entry.industry.cInduName}
								</div>
							</td>



							<td bgcolor="f5f5f5">
								<div align="center">
									${entry.cInvPerson}
								</div>
							</td>

							<td bgcolor="f5f5f5">
								<div align="center">
									<a href="#" onclick="showDetail('${entry.id}')"><img
											src="/images/Menu/view.png" alt="查看详情" title="查看详情"
											width="20" height="20" border="0">
									</a>
								</div>
							</td>
							<td bgcolor="f5f5f5">
								<div align="center">
									<a
										href="/canvassResource/front/investor_updateUI?id=${entry.id}"><img
											alt="修改" title="修改" src="/images/Menu/edit.png" width="20"
											height="20" border="0">
									</a>&nbsp;&nbsp;&nbsp;
									<a href="/canvassResource/front/investor_delete?id=${entry.id}"
										 
	 
><img 	onclick="return deleteConfirm('您确定删除该投资方吗?')"	title="删除" src="/images/Menu/delete.png" width="20"
											height="20" border="0">
									</a>
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
