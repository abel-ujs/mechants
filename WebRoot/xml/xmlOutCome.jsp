<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="merchants.utils.GenXmlData"%>
<%@ page import="java.util.*"%>
<%
	String ID = request.getParameter("id");
	int pro_id = Integer.parseInt(ID);
	String QuerySQL = "select * from ViewOutCome"
			+ " left outer join CDocName"
			+ " on (ViewOutCome.project_id = CDocName.project_id and itype=6)"
			+ " where ViewOutCome.project_id=" + pro_id;
	GenXmlData.GenParameterXmlData(response, QuerySQL);
%>
