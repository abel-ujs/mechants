/**
 * 验证意向项目信息录入时，是否已经在在用户所填的项目编码
 */
function checkcProCode(){
	var cProCode = $("input[id='project.cProCode']").val();
	if(cProCode!=""){
 	 $("#cProCode_label").remove();
	}
	if(cProCode == "" || !isProCode(cProCode)){
		$("#project.cProCode").val("");
		$("#project.cProCode").blur();
		alert("项目编码格式有误,项目编码格式（年+月+四位编码）如：2013010001");
		return false;
	}
	$.ajax({
		   type: "POST",
		   url: "/projectInformation/front/project_checkcProCode",
		   data: "project.cProCode=" + cProCode,
		   success: function(msg){
			     if(msg == "fail"){
			    	 $("#checkmessage").html("<font color=\"red\">抱歉,该项目编码已经存在，请更换项目编码！</font>");
			     }else {
			    	 $("#checkmessage").html("<img   src=\"/images/validate/success.png\" />");
			     }
		     
		   }
		});
}
/**
 * 验证意向项目信息录入时，是否已经存在在项目名称 此时传参方式为
 */
function checkcProName(){
	var cProName = $("input[id='project.cProName']").val();
	if(cProName!=""){
	 	 $("#cProName_label").remove();
		}
	$.ajax({
		   type: "POST",
		   url: "/projectInformation/front/project_checkcProName",
		   data: "project.cProName=" + cProName,
		   success: function(msg){
			     if(msg == "fail"){
			    	 $("#cProNameMessage").html("<font color=\"red\">抱歉,该项目名称已经存在，请更换项目名称！</font>");
			     }else {
			    	 $("#cProNameMessage").html("<img   src=\"/images/validate/success.png\" />");
			     }
		     
		   }
		});
}
/**
 * 验证介绍人信息录入时，是否已经存在用户所填的介绍人姓名
 */
function checkcPersonName(){
	var cPersonName = $("input[id='personal.cPersonName']").val();
	if(cPersonName!=""){
	 	 $("#cPersonName_label").remove();
		}
	 
	$.ajax({
		   type: "POST",
		   url: "/canvassResource/front/personal_checkcPersonName",
		   data: "personal.cPersonName=" + cPersonName,
		   success: function(msg){
			     if(msg == "fail"){
			    	 $("#checkmessage2").html("<font color=\"red\">抱歉，该介绍人已存在，请更换介绍人姓名！</font>");
			    	 
			     }else{
			    	 $("#checkmessage2").html("<img   src=\"/images/validate/success.png\" />");
			     }
		     
		   }
		});
}
/**
 * 验证介绍人信息录入时，是否已经存在用户所填的介绍人编码
 */
function checkcPersonCode(){
	var cPersonCode = $("input[id='personal.cPersonCode']").val();
	if(cPersonCode!=""){
	 	 $("#cPersonCode_label").remove();
		}
	if(cPersonCode == "" || !isPersonCode(cPersonCode)){
		$("#personal.cPersonCode").val("");
		$("#personal.cPersonCode").blur();
		alert("介绍人编码格式有误,介绍人编码格式（psn+四位编码）如：psn0001");
		return false;
	}
	$.ajax({
		   type: "POST",
		   url: "/canvassResource/front/personal_checkcPersonCode",
		   data: "personal.cPersonCode=" + cPersonCode,
		   success: function(msg){
			     if(msg == "fail"){
			    	 $("#checkmessage1").html("<font color=\"red\">抱歉，该介绍人编码已存在，请更换介绍人编码！</font>");
			    	 
			     }else{
			    	 $("#checkmessage1").html("<img   src=\"/images/validate/success.png\" />");
			     }
		     
		   }
		});
}
 
/**
 * 验证介绍单位信息录入时，是否已经存在用户所填的介绍单位客户名称
 */
function checkcCusName(){
	var cCusName = $("input[id='customer.cCusName']").val();
	if(cCusName!=""){
	 	 $("#cCusName_label").remove();
		}
	$.ajax({
		   type: "POST",
		   url: "/canvassResource/front/customer_checkcCusName",
		   data: "customer.cCusName=" + cCusName,
		   success: function(msg){
			     if(msg == "fail"){
			    	 $("#checkmessage2").html("<font color=\"red\">抱歉，该介绍单位名称已存在，请更换介绍单位名称！</font>");
			    	 
			     }else{
			    	 $("#checkmessage2").html("<img   src=\"/images/validate/success.png\" />");
			     }
		     
		   }
		});
}
/**
 * 验证介绍人信息录入时，是否已经存在用户所填的介绍人编码
 */
function checkcCusCode(){
	var cCusCode = $("input[id='customer.cCusCode']").val();
	if(cCusCode!=""){
	 	 $("#cCusCode_label").remove();
		}
	if(cCusCode == "" || !iscCusCode(cCusCode)){
		$("#customer.cCusCode").val("");
		$("#customer.cCusCode").blur();
		alert("介绍单位编码格式有误,介绍单位编码格式（cus+四位编码）如：cus0001");
		return false;
	}
	$.ajax({
		   type: "POST",
		   url: "/canvassResource/front/customer_checkcCusCode",
		   data: "customer.cCusCode=" + cCusCode,
		   success: function(msg){
			     if(msg == "fail"){
			    	 $("#checkmessage1").html("<font color=\"red\">抱歉，该介绍单位编码已存在，请更换介绍单位编码！</font>");
			    	 
			     }else{
			    	 $("#checkmessage1").html("<img   src=\"/images/validate/success.png\" />");
			     }
		     
		   }
		});
}
/**
 * 验证招商人员密码
 */
function checkPassword(){
	alert("checkPassword");
	var cUserName = $("input[id='systemUser.cUserName']").val();
	var cPassword = $("input[id='systemUser.cPassword']").val();
	$.ajax({
		   type: "POST",
		   url: "/canvassGroup/front/hRPerson_checkPassword",
		   data: "systemUser.cPassword=" + cPassword+"&systemUser.cUserName="+cUserName,
		   success: function(msg){
			     if(msg == "fail"){
			    	 $("#checkmessage").html("<font color=\"red\">抱歉，密码输入有误，请重新输入！</font>");
			    	 
			     }else{
			    	 $("#checkmessage").html("<img   src=\"/images/validate/success.png\" />");
			     }
		     
		   }
		});
}
/**
 * 验证是否为意向项目信息中的项目编码格式
 */
function isProCode(value){
	var reg = /^2\d{9}$/;
	if(!reg.test(value)){
		return false;
	}
	return true;
}
/**
 * 验证是否为介绍人中的介绍人编码格式
 */
function isPersonCode(value){
	var reg = /^psn[0-9]{4,4}$/;
	if(!reg.test(value)){
		return false;
	}
	return true;
}
/**
 * 验证是否为介绍单位编码格式
 */
function iscCusCode(value){
	var reg = /^cus\d{4}$/;
	if(!reg.test(value)){
		return false;
	}
	return true;
}
/**
 * 验证投资方信息录入时，是否已经存在用户所填的投资方名称
 */
function checkcInvName(){
	var cInvName = $("input[id='investor.cInvName']").val();
	if(cInvName!=""){
	 	 $("#cInvName_label").remove();
		}
	$.ajax({
		   type: "POST",
		   url: "/canvassResource/front/investor_checkcInvName",
		   data: "investor.cInvName=" + cInvName,
		   success: function(msg){
			     if(msg == "fail"){
			    	 $("#checkmessage").html("<font color=\"red\">抱歉，该投资方名称名称已存在，请更换投资方名称！</font>");
			    	 
			     }else{
			    	 $("#checkmessage").html("<img   src=\"/images/validate/success.png\" />");
			     }
		     
		   }
		});
}
/**
 * 验证身份证号
 */
function isIDNumber(obj){
	var value=obj.value;
	if(value==""){
		alert("身份证号码不能为空!");
		obj.value="";
		obj.blur();
		return false;
	}
	var reg = /^(\d{15}|\d{17}[\dXx])$/;
	if(!reg.test(value)){
		alert("身份证号码格式错误,请重新输入!");
		obj.value="";
		obj.blur();
		return false;
	}
	return true;
}
/* 验证手机号码 */
function isphoneNum(obj){
	var  reg = /^(1[3-9][0-9]\d{8})?$/;
	if(!reg.test(obj.value)){
		alert("手机号码格式错误,请重新输入!");
		obj.value="";
		obj.blur();
		return false;
	}else{
		return true;
	}
}
/* 验证邮箱 */
function isEmail(obj){
	var value=obj.value;
	var  reg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
	if(!reg.test(value)){
		alert("邮箱格式错误,请重新输入!");
		obj.value="";
		obj.blur();
		return false;
	}
	return true;
}
/* 验证登录名，登录名由英文字母，数字或“_”组成，长度为6-20，首字符必须是字母 */
function UsernameIsRight(value){
	if(value == ""){
		alert("登录名不能为空");
		return false;
	}else{
		var reg = /^[a-zA-Z]\w{5,19}$/;
		if(!reg.test(value)){
			alert("登录名格式错误");
			return false;
		}
	}
	return true;
		
}
/* 验证座机号码 */
function isFixedPhone(obj){
	var reg = /^\d{3}-\d{8}|\d{4}-\d{7,8}$/; 
	if(!reg.test(obj.value)){
		alert("格式错误,格式如:027-88888888或0714-2731163或0714-88888888");
		obj.value="";
		obj.blur();
		 return false;
	}else{
		return true;
	}
}
/* 验证传真*/
function isFaxNumber(obj){
	var reg = /^\d{3}-\d{8}|\d{4}-\d{7,8}$/; 
	if(!reg.test(obj.value)){
		alert("格式错误,格式如:027-88888888或0714-2731163或0714-88888888");
		obj.value="";
		obj.blur();
		 return false;
	}else{
		return true;
	}
}

/* 验证QQ */
function isQQNumber(obj){
	var reg = /^[0-9]{1,20}$/; 
	if(!reg.test(obj.value)){
		alert("QQ号码格式错误");
		obj.value="";
		obj.blur(); 
		return false;
	} 
	return true;
}
/* 验证邮编 */
function isPostcode(value){
	var reg = /^\d{6}$/;
	if(!reg.test(value)){
		return false;
	}
	return true;
}
/* 验证组织结构代码 */
function isOrganisationCode(value){
	if(value == ""){
		return true;
	}
	var reg = /^(\d{8}|[a-zA-Z]{8})-([0-9a-zA-Z])$/;
	if(!reg.test(value)){
		return false;
	}
	return true;
} 
/* 验证银行账号 */
function isBankNo(value){
	var reg = /^\d{19}$/;
	if(!reg.test(value)){
		return false;
	}
	return true;
}
// ========================
// 打开窗口
// ========================
function OpenWindow(url,width,height)
{
if (width==null)
  width=788;
else
  width=width-12;
if (height==null)
  height=569;
else
  height=height-31;
  
 LeftPosition = (screen.width) ? (screen.width-width)/2 : 0;
 TopPosition = (screen.height) ? (screen.height-height)/2 : 0;
 
 
 window.open(url,'_blank','channelmode=no,directories=no,fullscreen=no,location=no,menubar=no,resizable=yes,scrollbars=yes,status=yes,titlebar=yes,toolbar=no,left='+LeftPosition+',top='+ TopPosition +',width='+width+',height='+height);

 // window.open(url,'_blank','channelmode=no,directories=no,fullscreen=no,location=no,menubar=no,resizable=yes,scrollbars=yes,status=yes,titlebar=yes,toolbar=no,left='+(screen.availWidth<'+width+'?0:(screen.availWidth-'+width+')/2)+',top='+(screen.availHeight<'+height+'?0:(screen.availHeight-'+height+')/2)+',width='+width+',height='+height);
}
function OpenWindow(url,width,height,left,top)
{
  if (width==null)
    width=788;
  else
    width=width-12;
  if (height==null)
    height=569;
  else
    height=height-31;
 
 // add by liq 2008-9-22 ?
   left = (screen.width) ? (screen.width-width)/2 : 0;
  top = (screen.height) ? (screen.height-height)/2 : 0;
  window.open(url,'_blank','channelmode=no,directories=no,fullscreen=no,location=no,menubar=no,resizable=yes,scrollbars=yes,status=yes,titlebar=yes,toolbar=no,left='+left+',top='+top+',width='+width+',height='+height);
}
// ========================
// 打开最大化的窗口
// ========================
function OpenMaxWindow(url)
{
window.open(url,'_blank','channelmode=no,directories=no,fullscreen=no,location=no,menubar=no,resizable=yes,scrollbars=yes,status=yes,titlebar=yes,toolbar=no,left='+(screen.availWidth<'+width+'?0:(screen.availWidth-'+width+')/2)+',top='+(screen.availHeight<'+height+'?0:(screen.availHeight-'+height+')/2)+',width='+screen.availWidth+',height='+screen.availHeight);
}
// 打开上传申请材料界面
function ShowUpload(url) {
    OpenDialog(url, '600', '300');

}
// 窗口最大化
function MaxWindow()
{
    try
    {
		window.moveTo(0,0);
		window.resizeTo(window.screen.availWidth,window.screen.availHeight);
    }
	catch(err)
	{}
   	
}
// ========================
// 打开对话框
// ========================

function OpenDialog(url,width,height)
{
today=new Date();  
if (url.indexOf("?")<0)
{
   url=url+"?xxxx="+Math.random();
}
else
{
   url=url+"&xxxx="+Math.random();
}
if (width==null)
  width=800;
if (height==null)
  height=600;
return window.showModalDialog(url,'','dialogHeight:'+height+'px;dialogWidth:'+width+'px;edge:raised;center:Yes;help:No;resizable:Yes;status:yes;scroll:yes;unadorned:yes;');
}
// 验证专利申请号,录入格式如：200810235431.X或者PCT/CN2008/152625
function checkPatentNo(value){
	if(value == ""){
		return false;
	}
	var reg = /^\d{12}\.[0-9X]|PCT\/CN\d{4}\/\d{6}$/;
	if(!reg.test(value)){
		return false;
	}
	return true;
}
// 验证日期2011-02-05
function checkDate(value){
	var reg = /^2\d{3}-[01][0-9]-[0123][0-9]$/;
	if(!reg.test(value)){
		return false;
	}
	return true;
}
// 验证金额
function checkMoney(value){
	var reg = /^(([1-9]\d*)|\d)(\.\d{1,2})?$/;
	if(!reg.test(value)){
		return false;
	}
	return true;
}
function isMoney(obj){
	if(!checkMoney(obj.value)){
		alert("金额格式错误");
		obj.value="";
		obj.blur();
		return ;
	}
}
