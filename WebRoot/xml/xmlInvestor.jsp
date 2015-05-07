<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="merchants.utils.GenXmlData"%>
<%@ page import="java.util.*"%>
<%
	String ID = request.getParameter("id");
	int id = Integer.parseInt(ID);
	String QuerySQL = "select Investor.*,Nature.cNatName,Industry.cInduName from Investor,Nature,Industry"
			+ " where Investor.nature_id = Nature.id and Investor.industry_id = Industry.id "
			+ " and Investor.id in (select investor_id from ProjectInfo where id ="
			+ id+")";
	GenXmlData.GenParameterXmlData(response, QuerySQL);
%>
