<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<link rel="stylesheet" href="/css/vip.css" type="text/css">
<script type="text/javascript" src="/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	function generatejs(){
		var value = "$.ajax({type: 'POST',url: '/group/upload/delUploadFile',data: 'pnid=${pnid}&fileSavePath=${fileSavePath}',";
		value = value + "success: function(msg){if(msg == 'success'){$('#span_${pnid}').html('');}}})";
		return value;
	}
	function checkWin(){
		var pdocument=opener.document;
		var p1 = pdocument.getElementById("filename");
		
		if( pdocument && "${filename}" != ""){
			var value = $("#filename", pdocument).html();
			value = value + '<span id="span_${pnid}">已上传:${filename}(${introduction}) <a href="#" onclick="' + generatejs() + '">删除</a></span><br>';
			$("#filename", pdocument).html(value);
			
		}
		window.close();
	}

</script>
<body>
<div align="center">
<table>
<tr>
	<td height="40"></td>
</tr>
<tr>
	<td align="center">
		<table width="305" border="0" cellpadding="0" cellspacing="0" align="center">
		  <tr>
			<td height="212" align="center" valign="middle" bgcolor="#95CBFD"><table width="295" border="0" cellpadding="0" bgcolor="#FFFFFF">
			  <tr>
				<td width="288" align="center" bgcolor="#C2E1FE"><table width="100" border="0" cellpadding="0" cellspacing="0">
					<tr>
					  <td>&nbsp;</td>
					</tr>
				  </table>
					<table width="273" border="0" cellpadding="0" cellspacing="10" bgcolor="#FFFFFF">
					  <tr>
						<td width="253" height="60" align="center" valign="bottom" class="font12">
							<p><c:out value="${message}" escapeXml="false"/>							
							</p>
						</td>
					  </tr>
					  <tr>
						<td height="80" align="center" valign="middle"><font size="2"><span class="content">
						 <input type="button" name="sure" value="确 定"
						 onclick="javascript:<c:if test="${urladdress == null}">checkWin();</c:if><c:if test="${urladdress != null}">window.location.href='${urladdress}'</c:if>"
						 >
						 
						</span></font></td>
					  </tr>
					</table>
					<table width="100" border="0" cellpadding="0" cellspacing="0">
					  <tr>
						<td>&nbsp;</td>
					  </tr>
				  </table></td>
			  </tr>
			</table></td>
		  </tr>
		</table>
	</td>
</tr>
<tr>
	<td height="40"><br></td>
</tr>
</table>
</div>
</body>
</html>
