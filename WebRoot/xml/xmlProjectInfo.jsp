<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="merchants.utils.GenXmlData"%>
<%@ page import="java.util.*"%>
<%
	String ID = request.getParameter("id");
	int pro_id = Integer.parseInt(ID);
	String QuerySQL = "select cProName,dbo.getObjective(iObjective) as Objective,cMarketingInfo,cMainProducts,dInvestment,coperator from ProjectInfo where id="+pro_id;
	GenXmlData.GenParameterXmlData(response,QuerySQL);
%>
