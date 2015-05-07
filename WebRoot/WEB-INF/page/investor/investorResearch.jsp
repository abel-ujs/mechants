<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp"%>
<html>
	<head>
		<title>查询介绍单位</title>
<link rel="stylesheet" href="/css/projectInformation/content.css" />
<link rel="stylesheet" href="/css/projectInformation/content_list.css" />
<link rel="stylesheet" href="/css/projectInformation/vip.css" type="text/css">  
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script language="javascript" src="/js/jquery-1.7.2.min.js"></script>
		<script type="text/javascript">
		var keyNumber=2;
	   <!-- 提示用户输入信息-->
		function setEmpty(obj){
		   obj.value="";
		
		}
		<!--条件输入验证-->
		 
	function conditionCheck() {
		var tag = false;
		var inputs = document.getElementsByTagName("INPUT");	
		var input;
		for ( var i = 0; i < inputs.length; i++) {
			input = inputs[i];
			if(input.type=="text"){
			    if (input.value!="") {
				tag = true;	  
			}
			}
		}
		if(!tag){
		alert("请输入至少一个查询条件");
		}
		return tag;

	}
	
	function addXX(){
		var shtml = "<tr class='add_row'><td><select name=select"+ keyNumber+"><option value='or' >或者</option><option value='and' >并且</option></select></td><td><select name=key"+keyNumber+" ><option value=cInvName selected='selected' >投资方名称</option><option value=cInvLPerson>法人</option>";
            shtml +="<option value='cInvPerson' >联系人</option></select><input type='text' name=value"+keyNumber+" /></td></tr>";
			var maxConditionNumber=2;
			if($("table").find(".add_row").length>0){
				 if($("table").find(".add_row").length<maxConditionNumber)
					$("table").find("tr.add_row").last().after(shtml);
				else{
					alert("您最多只能选择3个查询条件!");
				}
			}else{
				$("table #addSearch").after(shtml);
			}
		keyNumber++;
	}
	function delXX(){
		
		$("table").find("tr.add_row:last").remove();
	}
	   </script>
	</head>
	<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0"
		marginwidth="0" marginheight="0">
	<div id="pform">
		<form action="/canvassResource/front/investor_research"
			" method="post">
 <div class="ftitle"><font>查询投资方：</font></div>
 <div id="pform">
			<table width="100%" border="0" cellspacing="1" cellpadding="3"
				align="center">
			
						
				

				<tr id="addSearch">
					<td>
						<input type="button" value="+" class="yxquery-addbut"  onClick="addXX()" />
						<input type="button" value="-" class="yxquery-reducebut"  onClick="delXX()" />
					</td>
					<td>
						<select name="key1">
							<option value="cInvName" selected="selected">
								投资方名称
							</option>
							<option value="cInvLPerson">
								法人
							</option>
							<option value="cInvPerson">
								联系人
							</option>
						</select>
						<input type="text" name="value1" />
					</td>
				</tr>

				<tr bgcolor="f5f5f5">
					<td width="15%">

					</td>
					<td width="75%">
						<input type="submit" value="查询"  class="fButtonDel" onClick="return conditionCheck()" />
					</td>
				</tr>

			</table></div>
		</form>
		</div>
	</body>
</html>