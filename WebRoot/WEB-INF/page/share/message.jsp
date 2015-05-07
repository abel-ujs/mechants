<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp"%>
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="/css/messcontent.css" />
	</head>

	<body>
		<div id="mess-bor">
			<div id="mess-innerbor">
				<div class="messtitle">
					<font>消息提示</font>
				</div>
				<div class="messmiddle">
					<table cellpadding="0" cellspacing="0">
						<tr>
							<td>
								<img src="/images/message/mess_icon.png" width="50" height="75" />
							</td>
							<td class="messmiddleright">
								<p>
									<c:out value="${message}" escapeXml="false" />
								</p>
							</td>
						</tr>
					</table>
				</div>
				<div class="messbut">
					<input type="button" name="sure" value="确 定" class="mess-ButtonDel"
						style="background-color: #fff;"
						onclick="javascript:<c:if test="${urladdress == null}">window.close();</c:if><c:if test="${urladdress != null}">window.location.href='${urladdress}'</c:if>">
				</div>
			</div>
		</div>
	</body>
</html>
