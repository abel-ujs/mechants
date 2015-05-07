<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp"%>
<html>
<head>
<title>项目小组信息录入</title>
<link rel="stylesheet" href="/css/formValidationStyle.css"
	type="text/css">
<link rel="stylesheet" href="/css/canvassResource/content.css" />
<link rel="stylesheet" href="/css/canvassResource/vip.css"
	type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/js/DatePicker.js"></script>
<script type="text/javascript" src="/js/common.js"></script>
<SCRIPT type=text/javascript src="/js/FoshanRen.js"></SCRIPT>
<script type="text/javascript" src="/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="/js/jquery.metadata.js"></script>
<script type="text/javascript" src="/js/jquery.validate.js"></script>
<script type="text/javascript" src="/js/layer.min.js"></script>

<script type="text/javascript">
	function loadDiv() {
		$("#groupdiv").load("/canvassGroup/front/canvassGroup_GroupPersonList",
				{
					group : $('#g').val()
				});
		$("#projectdiv").load(
				"/canvassGroup/front/canvassGroup_OwnProjectList", {
					project : $('#g').val()
				});
	}
</script>

<script type="text/javascript">
	function addGroupPerson() {
		var group = $('#g').val();
		if (group == 0) {
			layer.msg("请选择项目小组", 1, 8);
			return false;
		}
		var url = "/canvassGroup/front/canvassGroup_selectPerson?group="
				+ group;
		$.layer({
			type : 2,
			title : false,
			closeBtn : [ 0, true ],
			border : [ 12, 0.5, '#ccc', true ],
			iframe : {
				src : url
			},
			area : [ '600px', '400px' ],
			offset : [ '50px', '' ],
			end : function() {
				loadDiv();
			}
		});
	}
</script>

<script type="text/javascript">
	function assignGroup() {
		var url = "/canvassGroup/front/canvassGroup_groupSelect";
		var group = $('#g').val();
		var project = $('#pCode').val();
		$.ajax({
			url : url,
			data : {
				"group" : group,
				"project" : project
			},
			async : true,
			type : 'post',
			success : function(result) {
				if (result == "ok")
					layer.msg('成功分配小组', 1, 8);
				var i = parent.layer.getFrameIndex();
				parent.layer.close(i);
			}
		});
	}

	function cancelAssign() {
		layer.msg("取消分配小组，请下次继续审核", 1, 4);
		var i = parent.layer.getFrameIndex();
		parent.layer.close(i);
	}
</script>
</head>
<body>
	<div id="pform">
		<input type="hidden" value="${request.pCode}" id="pCode" />
		<div class="ftitle">
			<font>请为该项目&nbsp;&nbsp;${requestScope.pName}&nbsp;&nbsp;分配小组</font>
		</div>
		<div class="cont">
			<table width="100%" align="center">
				<tr align="center">
					<td>项目小组</td>
					<td><select id="g" onchange="loadDiv()">
							<option value="0">请选择</option>
							<c:forEach items="${requestScope.groupList}" var="entity">
								<option value="${entity.id}">${entity.cGroupName}</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr align="center">
					<td>小组成员列表</td>
					<td>小组拥有项目列表</td>
				</tr>
				<tr align="center">
					<td><div align="center" id="groupdiv"></div></td>
					<td><div align="center" id="projectdiv"></div>
					</td>
				</tr>
				<tr />
				<tr align="center">
					<td><input type="button" value="添加小组成员" class="fButtonDel"
						onclick="addGroupPerson()" />
					</td>
					<td><input type="button" value="确定" class="fButtonDel"
						onclick="assignGroup()" />
					</td>
					<td><input type="button" value="取消" class="fButtonDel"
						onclick="cancelAssign()" /></td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>