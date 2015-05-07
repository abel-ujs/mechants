<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="merchants.utils.GenXmlData"%>
<%
	String QuerySQL = "select dbo.getState(cState) as name,COUNT(id) as number from ProjectInfo group by cState";
	GenXmlData.GenNodeXmlData(response, QuerySQL, false);
%>