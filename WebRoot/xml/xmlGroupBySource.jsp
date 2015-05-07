<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="merchants.utils.GenXmlData"%>
<%
	String QuerySQL = "select dbo.getSource(personal_id) as name,COUNT(id) as number from ProjectInfo "
			+ "group by dbo.getSource(personal_id)";
	GenXmlData.GenNodeXmlData(response, QuerySQL, false);
%>