<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>
		
	</title>
	<link href="/css/base.css" rel="stylesheet" type="text/css" />
	<link href="/css/index.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="/js/jquery-1.4.1.min.js"></script>
	<script type="text/javascript">
	var h=$(window).height();
	$(function(){
			$(window).load(function(){
					var w_w = $(window).width();
					var w_h = $(window).height();
					change(w_w,w_h);
			}).resize(function(){
					var w_w = $(window).width();
					var w_h = $(window).height();
					change(w_w,w_h);
				    });
		    $(window).resize();
			});
			function change(w_w,w_h){
	            $("#top").css("width",w_w+"px");
				$("#left").css("height",w_h-150);
				$("#button").css("height",w_h-124);
				$("#right").css({"width":(w_w-195),"height":(w_h-150)});
				$("#i_left").css("height",w_h-150);
				$("#i_button").css("height",w_h-150);
				$("#i_right").css({"width":(w_w-195),"height":(w_h-150)});
			}
			
	
		</script>
</head>
<body>
  <form name="form1" method="post" action="" id="form1">
<div>

</div>

  <div>
  	<div id="top">
  	  <iframe id="i_top" name="top" src="/control/admin/top" scrolling="no" frameborder='1' ></iframe>
  	</div>
    
<div id="middle_div">
  		<div id="left">
  		<iframe id="i_left" name="left" src="/control/admin/left"  scrolling="no" frameborder='0' ></iframe>
  		</div>
        
        <div id="right">
  		<iframe id="i_right" name="right" src="/control/admin/main"  scrolling="auto" frameborder='0' ></iframe>
  		</div>
        
        <div class="qin"></div>
  	</div>
    
  	<div id="bottom">
    <iframe id="i_bottom" name="bottom" src="/control/admin/bottom" scrolling="no" frameborder='0' ></iframe>
  	</div>
    
    </div>
    </form>
</body>
</html>


