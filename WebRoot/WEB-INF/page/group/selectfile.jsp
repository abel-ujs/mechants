<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>上传文件</title>
		<script type="text/javascript" src="/js/common.js"></script>
		<script type="text/javascript" src="/js/jquery-1.6.4.js"></script>
		<link href="/style/content.css" rel="stylesheet" type="text/css">
		<script type="text/javascript">
	$("form").submit(function() {
		var p = $("input[name='introduction']").val();
		if (p == "" || p == null) {
			alert("__tag_14$14_请输入文件说明__tag_14$32_");
			return false;
		}
		return true;
	})
</script>
	</head>
	<body>
		<div id="pform">
			<form method="post" action="/group/upload/uploadfile"
				enctype="multipart/form-data">
				<div class="ftitle">
					<font>选择要导入的文件</font>
				</div>
				<div class="cont">
					<table cellspacing="0" cellpadding="0">
						<tbody>
							<tr>
								<td class="listltd">
									<font>选择文件</font>
								</td>
								<td class="rtd">
									<input type="file" name="file">
								</td>
							</tr>
							<tr>
								<td class="listltd">
									<font>文件说明</font>
								</td>
								<td class="rtd">
									<input type="text" name="introduction">
									<font color="#ff8080">*</font>必填
								</td>

							</tr>
							<tr>
								<td class="listltd">
									<input type="submit" value="上传" />
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</form>
		</div>
	</body>
</html>
