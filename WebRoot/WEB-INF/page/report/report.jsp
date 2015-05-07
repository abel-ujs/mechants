<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<title>Web报表(B/S报表)演示 - 查询显示控件展现报表</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="/js/jquery-1.7.2.min.js"></script>
<script src="/js/CreateControl.js" type="text/javascript" charset="utf-8"></script>
<script src="/js/GRInstall.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
	var investorReport;
	var projectinfoReport;
	var procommunReport;
	var pronegotiateReport;
	var prosigningReport;
	var prosettledReport;
	var outcomeReport;
	var procapitalinReport;
	function window_onload() {
		var name = "${requestScope.project.cProName}";

		//state = ${requestScope.project.cState};
		var cState = "${requestScope.project.cState}";
		state =parseInt(cState); 
		
		var source = "${requestScope.project.cSource}";

		//id = ${requestScope.project.id};
		var ID = "${requestScope.project.id}";
		id=parseInt(ID);

		ReportViewer.Report.ParameterByName("cProName").AsString = name;

		ReportViewer.Report.ParameterByName("cSource").AsString = source;

		if (state == 0)	state = 8;

		if (state > 0) {
			projectinfoReport = ReportViewer.Report
					.ControlByName("SubProjectInfo").AsSubReport.Report;
			projectinfoReport.LoadFromURL("/grf/projectinfo.grf");
			projectinfoReport.OnInitialize = OnProjectInfoInitialze;

			investorReport = ReportViewer.Report.ControlByName("SubInvestor").AsSubReport.Report;
			investorReport.LoadFromURL("/grf/investor.grf");
			investorReport.OnInitialize = OnInvestorInitialize;
			state--;
			state--;
		}

		if (state > 0) {
			ReportViewer.Report.ControlByName("SubProCommun").Visible = true;
			procommunReport = ReportViewer.Report.ControlByName("SubProCommun").AsSubReport.Report;
			procommunReport.LoadFromURL("/grf/procommun.grf");
			procommunReport.OnInitialize = OnProCommunInitialize;
			state--;
		}

		if (state > 0) {
			ReportViewer.Report.ControlByName("SubProNegotiate").Visible = true;
			pronegotiateReport = ReportViewer.Report
					.ControlByName("SubProNegotiate").AsSubReport.Report;
			pronegotiateReport.LoadFromURL("/grf/pronegotiate.grf");
			pronegotiateReport.OnInitialize = OnProNegotiateInitialize;
			state--;
		}

		if (state > 0) {
			ReportViewer.Report.ControlByName("SubProSigning").Visible = true;
			prosigningReport = ReportViewer.Report
					.ControlByName("SubProSigning").AsSubReport.Report;
			prosigningReport.LoadFromURL("/grf/prosigning.grf");
			prosigningReport.OnInitialize = OnProSigningInitialize;

			state--;
		}

		if (state > 0) {
			ReportViewer.Report.ControlByName("SubProSettled").Visible = true;
			prosettledReport = ReportViewer.Report
					.ControlByName("SubProSettled").AsSubReport.Report;
			prosettledReport.LoadFromURL("/grf/prosettled.grf");
			prosettledReport.OnInitialize = OnProSettledInitialize;
			state--;
		}

		if (state > 0) {
			ReportViewer.Report.ControlByName("SubOutCome").Visible = true;
			outcomeReport = ReportViewer.Report.ControlByName("SubOutCome").AsSubReport.Report;
			outcomeReport.LoadFromURL("/grf/outcome.grf");
			outcomeReport.OnInitialize = OnOutComeInitialize;
			state--;
		}

		if (state > 0) {
			ReportViewer.Report.ControlByName("SubProCapitalIn").Visible = true;
			procapitalinReport = ReportViewer.Report
					.ControlByName("SubProCapitalIn").AsSubReport.Report;
			procapitalinReport.LoadFromURL("/grf/procapitalin.grf");
			procapitalinReport.OnInitialize = OnProCapitalInInitialize;
			state--;
		}

		ReportViewer.Report.ShowProgressUI = true;
		//启动运行
		ReportViewer.Start();
	}

	function OnInvestorInitialize() {
		//investorReport.LoadDataFromURL("xmlInvestor.jsp");
		//var id = 7
		investorReport.LoadDataFromURL(encodeURI("/xml/xmlInvestor.jsp?id="
				+ id));
	}

	function OnProjectInfoInitialze() {
		//var id = 4;
		projectinfoReport
				.LoadDataFromURL(encodeURI("/xml/xmlProjectInfo.jsp?id=" + id));
	}

	function OnProCommunInitialize() {
		//var id = 4;
		procommunReport.LoadDataFromURL(encodeURI("/xml/xmlProCommun.jsp?id="
				+ id));
	}

	function OnProNegotiateInitialize() {
		var nego = $("#nego").val();
		pronegotiateReport.ControlByName("MemoBox1").AsMemoBox.Text = nego;
	}

	function OnProSigningInitialize() {
		//var id = 4;
		prosigningReport.LoadDataFromURL(encodeURI("/xml/xmlProSigning.jsp?id="
				+ id));
	}

	function OnProSettledInitialize() {
		//var id = 4;
		prosettledReport.LoadDataFromURL(encodeURI("/xml/xmlProSettled.jsp?id="
				+ id));
	}

	function OnOutComeInitialize() {
		//var id = 4;
		outcomeReport
				.LoadDataFromURL(encodeURI("/xml/xmlOutCome.jsp?id=" + id));
	}

	function OnProCapitalInInitialize() {
		//var id = 4;
		procapitalinReport
				.LoadDataFromURL(encodeURI("/xml/xmlProCapitalIn.jsp?id=" + id));
	}
</script>
<style type="text/css">
body {
	text-align: center
}

#report {
	text-align: left;
	margin: 0 auto;
	width: auto;
}
</style>
<body onload="return window_onload()">
<input type="hidden" value="${negotiate}" id="nego">
	<div>
		<script>
			//用查询显示控件展现报表，从URL“../grf/1a.grf”获取报表膜板定义，从URL“../data/xmlCustomer.jsp”获取XML形式的报表数据，
			CreateDisplayViewerEx("100%", "100%", "/grf/allReport.grf", "",
					false, "");
		</script>
	</div>
</body>
</html>