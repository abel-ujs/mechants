<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="merchants.utils.GenXmlData"%>
<%@ page import="java.util.*"%>
<%
	String QuerySQL = "select * from ProjectInfo";
	GenXmlData.GenNodeXmlData(response, QuerySQL, false);
%>
