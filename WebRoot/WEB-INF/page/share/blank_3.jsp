<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp"%>


<c:forEach begin="1" end="${10-fn:length(pageView.records)}">
	<tr>
		<td>&nbsp</td>
		<td>&nbsp</td>
		<td>&nbsp</td>

	</tr>
</c:forEach>