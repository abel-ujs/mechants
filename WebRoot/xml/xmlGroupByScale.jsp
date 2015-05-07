<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="merchants.utils.GenXmlData"%>
<%
	String QuerySQL = "select dbo.getScale(dInvestment) as name,COUNT(id) as number from ProjectInfo "
	+"group by dbo.getScale(dInvestment)";
	GenXmlData.GenNodeXmlData(response, QuerySQL, false);
%>