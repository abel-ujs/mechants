<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="merchants.utils.GenXmlData"%>
<%
String QuerySQL = "select dbo.getCategory(cCategory) as name,count(id) as number from ProjectInfo group by cCategory";
GenXmlData.GenNodeXmlData(response, QuerySQL, false);
%>