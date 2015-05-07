<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="merchants.utils.GenXmlData"%>
<%
	String QuerySQL = "select cInduName as name, COUNT(ProjectInfo.id) as number "
			+ "from ProjectInfo,Industry "
			+ "where ProjectInfo.industry_id=Industry.id group by cInduName";
	GenXmlData.GenNodeXmlData(response, QuerySQL, false);
%>