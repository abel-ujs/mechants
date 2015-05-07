// JavaScript Document

var h
    h=$(window).height();
if ($.browser.msie) {
    h =window.parent.h-124;
}
var top_i;
var footer_i=h-31;
var minNav_li;
var tree_box;
var nav;
var nl_mTop;
//按钮的 数目
var minNav_size;
//布局
function styleAction(){
	$("#gxz").css("height",h-2);
	$("#min_navList").css("top", footer_i);
}
//拖动 方法
function tuodong(){
    top_i = $("#nav_list").offset().top;
	tree_box.css("height",top_i-33);
	//可显示按钮的 数目
    var showNav_i = (footer_i-top_i)/31;
         showNav_i = Math.floor(showNav_i);
	//迷你按钮的 数目
	var minNav_i = minNav_size-showNav_i;
	
	minNav_li.hide();
	if(minNav_i<=0){return;}
	//显示 	迷你按钮 
	for(var i=0;i<minNav_i;i++){
		minNav_li.eq(minNav_size-1-i).show();
	}	 
	}

//



$(function(){
  minNav_li =$("#min_navList_ul li");
  minNav_size=minNav_li.size();
  tree_box=$(".tree_box");
  nav=$(".nav");
  //布局
  styleAction();
  tuodong();
  //去虚线
  $("a").focus(function(){
   $(this).blur();
   })
  
  
  //拖动
  var move = false;
  var move_tool=$("#move_tool");
  var nav_list = $("#nav_list");
  //触发 拖动条件
  move_tool.mousedown(function(){
  move = true;	
  nl_mTop=nav_list.offset().top;
  $("body").css("cursor","n-resize");
  $("#nav_list a").css("cursor", "n-resize");
  })
  //消除 拖动条件   改变迷你按钮 
  $("body").mouseup(function(){
  //1px bug
  if(move){
  nl_mTop=nl_mTop-1;
  nav_list.css("margin-top",nl_mTop);
  }							 
  move = false;	
  $("body").css("cursor","default");
  $("#nav_list a").css("cursor", "pointer");
  })
  //限制
  var maxPx=h-minNav_size*31-40;
  var minPx=h-25;
  //初始化 nav_list 位置,和 tree_box 高度
  nav_list.css("margin-top",maxPx);
  //nav.css("margin-top",minNav_size*31 + 10);
  tree_box.css("height",maxPx-33);
  //拖动 
  $("body").mousemove(function(event){						   
  if(move){
  var y = event.pageY-5;
  if(y<=maxPx){y=maxPx;}
  if(y>=minPx){y=minPx;}
  var key_i =nl_mTop-y;
  if(key_i>=31){
       nl_mTop=nl_mTop-31;
       nav_list.css("margin-top",nl_mTop);
       tuodong();  
  }
  if(key_i<= -31){
       nl_mTop=nl_mTop+31;
       nav_list.css("margin-top",nl_mTop);
       tuodong();  
  }
  }
  })
  
  //菜单初始化
  $(".nl_after").next().show();
  //切换菜单
  $("h1").click(function(){
  $(".nav").hide();
  $(this).next().show();
  $(".nl_after").removeClass();
  $(this).addClass("nl_after");
  var now_min=$("."+$(this).attr("id"));
      $("#min_navList_ul a").removeClass();
	  now_min.find("a").addClass("min_after");
  })
  //min 按钮
  $("#min_navList_ul li").click(function(){
  var now_h1=$("#"+$(this).attr("class"));
      $(".nav").hide();
      now_h1.next().show();
      $(".nl_after").removeClass();
      now_h1.addClass("nl_after");
	  $("#min_navList_ul a").removeClass();
	  $(this).find("a").addClass("min_after");
  })
  
})

