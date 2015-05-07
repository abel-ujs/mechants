//删除确认
function deleteConfirm(message){
	var result = confirm(message);
	  return result;
}

function checkOrganisationCode(){
	var organisationCode = $("input[id='user.organisationCode']").val();
	if(organisationCode == "" || !isOrganisationCode(organisationCode)){
		$("input[id='user.organisationCode']").val("");
		$("input[id='user.organisationCode']").blur();
		alert("组织结构代码格式有误");
		$("#btn_ok").attr("disabled",true);
		return false;
	}
	$.ajax({
		   type: "POST",
		   url: "/user/front/checkByOrganisationCode",
		   data: "user.organisationCode=" + organisationCode,
		   success: function(msg){
			     if(msg == "fail"){
			    	 $("#organisationCode_message").html("<font color=\"red\">该组织机构代码已经被注册</font>");
			    	 $("#btn_ok").attr("disabled",true);
			    	 
			     }else{
			    	 $("#organisationCode_message").html("<font color=\"green\">恭喜，该组织机构代码可以注册</font>");
			    	 $("#btn_ok").attr("disabled",null);
			     }
		     
		   }
		});
}
function checkIDNumber(){
	var IDNumber = $("#IDNumber").val();
	if(IDNumber == "" || !isIDNumber(IDNumber)){
		$("#IDNumber").val("");
		$("#IDNumber").blur();
		alert("身份证号格式有误");
		$("#btn_ok").attr("disabled",true);
		return false;
	}
	
	$.ajax({
		   type: "POST",
		   url: "/user/front/checkByIDNumber",
		   data: "user.IDNumber=" + IDNumber,
		   success: function(msg){
			     if(msg == "fail"){
			    	 $("#IDNumber_message").html("<font color=\"red\">该身份证号码已经被注册</font>");
			    	 $("#btn_ok").attr("disabled",true);
			    	 
			     }else{
			    	 $("#IDNumber_message").html("<font color=\"green\">恭喜，该身份证号码可以注册</font>");
			    	 $("#btn_ok").attr("disabled",null);
			     }
		     
		   }
		});
}
/**
 * 验证身份证号
 * @param value
 * @return
 */
function isIDNumber(value){
	var reg = /^(\d{15}|\d{17}[\dXx])$/;
	if(!reg.test(value)){
		return false;
	}
	return true;
}
/*验证手机号码*/
function isphoneNum(obj){
	var  reg = /^(1[3-9][0-9]\d{8})?$/;
	if(!reg.test(obj.value)){
		alert("手机号码格式错误");
		obj.value="";
		obj.blur();
		$("#btn_ok").attr("disabled",true);
	}else{
		$("#btn_ok").attr("disabled",null);
	}
}
/*验证邮箱*/
function isEmail(value){
	var  reg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
	if(!reg.test(value)){
		return false;
	}
	return true;
}
/*验证登录名，登录名由英文字母，数字或“_”组成，长度为6-20，首字符必须是字母*/
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
/*验证座机号码*/
function isFixedPhone(obj){
	var reg = /^\d{3}-\d{8}|\d{4}-\d{7}$/; 
	if(!reg.test(obj.value)){
		alert("固定电话格式错误");
		obj.value="";
		obj.blur();
		$("#btn_ok").attr("disabled",true);
	}else{
		$("#btn_ok").attr("disabled",null);
	}
}

/*验证QQ*/
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
/*验证邮编*/
function isPostcode(value){
	var reg = /^\d{6}$/;
	if(!reg.test(value)){
		return false;
	}
	return true;
}
/*验证组织结构代码*/
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
/*验证表单*/
function checkform(){
	//登录名不能为空
	if($("input[id='user.username']").val() == ""){
		alert("登录名不能为空");
		$("input[id='user.password']").blur();
		return false;
	}
	//密码不能为空，且要与重复密码一致
	if($("input[id='user.password']").val() == ""){
		alert("密码不能为空");
		$("input[id='user.password']").blur();
		return false;
	}
	if($("input[id='user.password']").val() != $("input[id='repassword']").val()){
		alert("两次输入的密码不一致");
		$("input[id='user.password']").val("");
		$("input[id='repassword']").val("");
		return false;
	}
	//单位名称/姓名不能为空
	if($("input[id='user.name']").val() == ""){
		alert("单位名称/姓名不能为空");
		return false;
	}
	
	//组织结构代码
	if(typeof($("input[id='user.organisationCode']").attr("disabled")) == "undefined"){
		if($("input[id='user.organisationCode']").val() == ""){
			alert("组织机构代码不能为空");
			$("input[id='user.organisationCode']").blur();
			return false;
		}
	}
	//身份证号码
	if(typeof($("#IDNumber").attr("disabled")) == "undefined"){
		if($("#IDNumber").val() == ""){
			alert("身份证号码不能为空");
			$("input[id='user.organisationCode']").blur();
			return false;
		}
	}
	
	//联系人不能为空
	if($("input[id='user.contactName']").val() == ""){
		alert("联系人不能为空");
		return false;
	}
	//固定号码
	if($("input[id='user.contactPhone']").val() == ""){
		alert("联系人电话(固定电话)不能为空");
		$("input[id='user.contactPhone']").blur();
		return false;
	}
	//手机号码
	if($("input[id='user.contactCellphone']").val() == ""){
			alert("手机号码不能为空");
			$("input[id='user.contactCellphone']").blur();
			return false;
	}
	
//	//固定电话与手机号至少要填一个
//	if($("input[id='user.contactPhone']").val() == "" && $("input[id='user.contactCellphone']").val() == ""){
//		alert("联系人手机号和联系人电话(固定电话)至少填一个");
//		return false;
//	}else{
//		//固定号码
//		if($("input[id='user.contactPhone']").val() != ""){
//			if(!isFixedPhone($("input[id='user.contactPhone']").val())){
//				alert("联系人电话(固定电话)格式错误");
//				$("input[id='user.contactPhone']").val("");
//				$("input[id='user.contactPhone']").blur();
//				return false;
//			}
//		}
//		
//		//手机号码
//		if($("input[id='user.contactCellphone']").val() != ""){
//			if(!isphoneNum($("input[id='user.contactCellphone']").val())){
//				alert("手机号码格式错误");
//				$("input[id='user.contactCellphone']").val("");
//				$("input[id='user.contactCellphone']").blur();
//				return false;
//			}
//		}
//		
//	}
	//邮箱
	if(!isEmail($("input[id='user.email']").val())){
		alert("邮箱格式错误");
		$("input[id='user.email']").val("");
		$("input[id='user.email']").blur();
		return false;
	}
	//邮编
	if(!isPostcode($("input[id='user.postcode']").val())){
		alert("邮编格式错误");
		$("input[id='user.postcode']").val("");
		$("input[id='user.postcode']").blur();
		return false;
	}
	//通讯地址不能为空
	if($("input[id='user.address']").val() == ""){
		alert("通讯地址不能为空");
		$("input[id='user.address']").blur();
		return false;
	}
	if(!$("#file").val() || $("#file").val == ""){
		alert("上传文件不能为空");
		$("#file").blur();
		return false;
	}
	if(!isBankNo($("#bankNo").val())){
		alert("银行卡号格式不正确");
		$("#bankNo").val("");
		$("#bankNo").blur();
		return false;
	}
	if($("#payee").val() == ""){
		alert("收款人不能为空");
		$("#payee").blur();
		return false;
	}
	return true;
}
/*验证银行账号*/
function isBankNo(value){
	var reg = /^\d{19}$/;
	if(!reg.test(value)){
		return false;
	}
	return true;
}
//========================
//打开窗口
//========================
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

 //window.open(url,'_blank','channelmode=no,directories=no,fullscreen=no,location=no,menubar=no,resizable=yes,scrollbars=yes,status=yes,titlebar=yes,toolbar=no,left='+(screen.availWidth<'+width+'?0:(screen.availWidth-'+width+')/2)+',top='+(screen.availHeight<'+height+'?0:(screen.availHeight-'+height+')/2)+',width='+width+',height='+height);
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
 
 //add by liq 2008-9-22  ?   
   left = (screen.width) ? (screen.width-width)/2 : 0;
  top = (screen.height) ? (screen.height-height)/2 : 0;
  window.open(url,'_blank','channelmode=no,directories=no,fullscreen=no,location=no,menubar=no,resizable=yes,scrollbars=yes,status=yes,titlebar=yes,toolbar=no,left='+left+',top='+top+',width='+width+',height='+height);
}
//========================
//打开最大化的窗口
//========================
function OpenMaxWindow(url)
{
window.open(url,'_blank','channelmode=no,directories=no,fullscreen=no,location=no,menubar=no,resizable=yes,scrollbars=yes,status=yes,titlebar=yes,toolbar=no,left='+(screen.availWidth<'+width+'?0:(screen.availWidth-'+width+')/2)+',top='+(screen.availHeight<'+height+'?0:(screen.availHeight-'+height+')/2)+',width='+screen.availWidth+',height='+screen.availHeight);
}
//打开上传申请材料界面
function ShowUpload(url) {
    OpenDialog(url, '600', '300');

}
//窗口最大化
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
//========================
//打开对话框
//========================

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
//验证专利申请号,录入格式如：200810235431.X或者PCT/CN2008/152625
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
//验证日期2011-02-05
function checkDate(value){
	var reg = /^2\d{3}-[01][0-9]-[0123][0-9]$/;
	if(!reg.test(value)){
		return false;
	}
	return true;
}
//验证金额
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
