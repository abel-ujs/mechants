<%@ include file="/WEB-INF/page/share/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<link rel="stylesheet" href="/css/canvassResource/content.css" />
		<script type="text/javascript" src="/js/jquery-1.7.2.min.js"></script>
		<script type="text/javascript" src="/js/jquery.fn.imgplayer.min.js"></script>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>科技新城招商跟踪管理平台</title>
		<script type="text/javascript">
	 function logout(){
	window.opener.document.location.reload();
	 window.opener=null;
	         window.close();
	        
	 }
	 
	 </script>
	</head>

	<body>
		<div id="page-top">
			<div class="topleft"></div>
			<div class="topright"></div>
		</div>
		<div id="mess-bg">
			<div class="pageleft">
				登录用户:${sessionScope.user.cUserName }&nbsp;&nbsp;|&nbsp;&nbsp;
				<script type="text/javascript">
	  m = [ "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" ];
	  d = new Date();
	document.write(d.getFullYear() + "年" + (d.getMonth() + 1) + "月" + d.getDate()
			+ "日" + m[d.getDay()]);
</script>
			</div>
			<div class="pageright">
				<c:if test="${sessionScope.user.cUserName!='admin'}"><a href="/canvassGroup/front/hRPerson_updateUI" target="right">用户信息维护</a> &nbsp;&nbsp;|&nbsp;&nbsp;</c:if>
				<a id="exit" onclick="logout"
					href="/canvassGroup/front/hRPerson_logout" target="_blank">退出</a>
			</div>
		</div>
	</body>
</html>
