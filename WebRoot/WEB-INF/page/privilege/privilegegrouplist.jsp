<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/page/share/taglib.jsp" %>
<html>
<head>
<title>权限组列表</title>
<link rel="stylesheet" href="/css/projectInformation/content.css" />
<link rel="stylesheet" href="/css/projectInformation/content_list.css" />
<link rel="stylesheet" href="/css/projectInformation/vip.css" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript">
 
	//到指定的分页页面
	function topage(page){
		var form = document.forms[0];
		form.page.value=page;
		form.submit();
	}
 //完成添加角色的点击事件
	function add(){
		var form = document.forms[0];
		form.action="/control/privilege/add";
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
<form action="/control/privilege/list" method="post">
<input type="hidden" name="page"/>
<div class="ftitle">
					<font>角色管理</font>
				</div>

<table id="listTable" class="HeaderStyle" width="100%"
					cellspacing="0">
   
    <tr>
      <th width="5%">序号</th>
      <th width="5%">角色</th>
	  <th width="5%">操作</th>
    </tr>
<!---------------------------LOOP START------------------------------>
<c:forEach items="${pageView.records}" var="entry" varStatus="status">
    <tr>
      <td bgcolor="f5f5f5"> <div align="center"> ${status.count}</div></td>
	  <td bgcolor="f5f5f5"> <div align="center"> ${entry.name }</div></td>
	  <td bgcolor="f5f5f5"> 
		  <div align="center">
		  <a href="/control/privilege/edit?privilegeGroup.groupid=${entry.groupid }"><img   
											title="修改" src="/images/Menu/edit.png" width="20" height="20"
											border="0"> </a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		  <a href="javascript:if(confirm('确定要删除该角色吗')){location='/control/privilege/delete?privilegeGroup.groupid=${entry.groupid }';}"><img
									 		title="删除" src="/images/Menu/delete.png" width="20"
											height="20" border="0"></a>
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
   <tr>
   		<td colspan="12" align="left">
   			<button onclick="add()" class="fButtonDel">添加角色</button>
   		</td>
   		
  </tr>
  </table>

</form>
</div>
</body>
</html>