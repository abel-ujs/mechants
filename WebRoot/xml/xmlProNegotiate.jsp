<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="merchants.utils.GenXmlData"%>
<%@ page import="java.util.*"%>
<%
	String ID = request.getParameter("id");
	int pro_id = Integer.parseInt(ID);
	String QuerySQL = "select cdate,cng_Address,cng_Advice,cng_Analysis,cng_Content,cng_Element,cng_Person,cng_Problem,cng_Require,cng_Result,cng_Works,coperator,csummarize,datetime,dwillDate,project_id from Pro_Negotiate where project_id="
			+ pro_id;
	GenXmlData.GenParameterXmlData(response, QuerySQL);
%>
