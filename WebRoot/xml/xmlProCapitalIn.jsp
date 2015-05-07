<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="merchants.utils.GenXmlData"%>
<%@ page import="java.util.*"%>
<%
	String ID = request.getParameter("id");
	int pro_id = Integer.parseInt(ID);
	String QuerySQL = "select * from ViewProCapitalIn"
			+ " left outer join CDocName"
			+ " on (ViewProCapitalIn.project_id = CDocName.project_id and itype=7)"
			+ " where ViewProCapitalIn.project_id=" + pro_id;
	GenXmlData.GenParameterXmlData(response, QuerySQL);
%>
