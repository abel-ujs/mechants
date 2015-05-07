<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="merchants.utils.GenXmlData"%>
<%@ page import="java.util.*"%>
<%
	String ID = request.getParameter("id");
	int pro_id = Integer.parseInt(ID);
	String QuerySQL = "select * from ViewProSign"
			+ " left outer join CDocName"
			+ " on (ViewProSign.project_id = CDocName.project_id and itype=4)"
			+ " where ViewProSign.project_id=" + pro_id;
	GenXmlData.GenParameterXmlData(response, QuerySQL);
%>
