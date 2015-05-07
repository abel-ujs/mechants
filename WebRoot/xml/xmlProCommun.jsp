<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="merchants.utils.GenXmlData"%>
<%@ page import="java.util.*"%>
<%
	String ID = request.getParameter("id");
	int pro_id = Integer.parseInt(ID);
	String QuerySQL = "select * from ViewProCommun"
			+ " left outer join CDocName"
			+ " on (ViewProCommun.project_id = CDocName.project_id and itype=2)"
			+ " where ViewProCommun.project_id=" + pro_id;
	GenXmlData.GenParameterXmlData(response, QuerySQL);
%>
