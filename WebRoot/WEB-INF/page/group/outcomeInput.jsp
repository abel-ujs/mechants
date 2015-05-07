<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp"%>
<html>
	<head>
		<title>达产达效信息录入</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script language="javascript" src="/js/common.js"></script>
		<SCRIPT language=JavaScript	src="/js/FoshanRen.js"></SCRIPT>
		<script language="javascript" src="/js/jquery-1.6.4.js"></script>
		<script type="text/javascript">
		<!--  处理查看按钮点击事件-->
		function display(){
		var cProName=document.getElementById("project.cProName").value;
		<!--get方法提交的参数容易有中文乱码问题-->
		winOpen('/canvassGroup/front/outcome_loadProjectInfo?project.cProName='+cProName,'项目信息',600,400);
		}
		<!-- 提示用户输入信息-->
		function setEmpty(obj){
		   obj.value="";
		
		}
		
		function upload(){
		var projectId=document.getElementById("projectId").value;
		window.open('/group/upload/selectFile?projectId="+projectId+"&statustype=5','','width=800,height=300,top=400,left=300');
		}
		
		</script>
	</head>
	<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0"
		marginwidth="0" marginheight="0">
		<form action="/canvassGroup/front/outcome_save" method="post" enctype="multipart/form-data">
		
		<input type="hidden" name="hRPerson.id"  id="hRPerson.id"  />
			<table width="98%" border="0" cellspacing="1" cellpadding="3"
				align="center">
				<tr bgcolor="6f8ac4">
					<td colspan="2">
						<font color="#FFFFFF">达产达效信息录入：</font>
					</td>
				</tr>
				<tr bgcolor="f5f5f5">
					<td width="25%">
						<div align="left">
							项目名称：
						</div>
					</td>
					<td width="75%">
						
						<input type="text" name="project.cProName"   id="project.cProName"
						onclick="setEmpty(this)" 	value="请输入项目名称"  />
							  
							  <input type="button"  value="项目查看"
							   onclick="display()" >
							  </td>
						 
				</tr>
				
				<tr bgcolor="f5f5f5">
					<td width="25%">
						<div align="left">
							实际规模：
							 
						</div>
					</td>
					<td>
					<input type="text" name="outcome.cInvestment"  value="请输入实际规模" onclick="setEmpty(this)"
							  />
							  </td>
				</tr>
				<tr bgcolor="f5f5f5">
					<td width="25%">
						<div align="left">
						实际效益： 
						</div>
					</td>
					<td width="75%">
						
						<input type="text" name="outcome.cProfit"  value="请输入实际效益" onclick="setEmpty(this)"
							  />
							  </td>
				</tr>
				<tr bgcolor="f5f5f5">
					<td width="25%">
						<div align="left">
							实际主要产品： 
						</div>
					</td>
					<td width="75%">
						<textarea name="outcome.cMainProducts" ></textarea>
						 
							  </td>
				</tr>
				 
				<tr bgcolor="f5f5f5">
					<td width="25%">
						<div align="left">
							附件： 
						</div>
					</td>
					<td width="75%">
						
							<input type="button" value="上传附件" onclick="upload()" ><br/>
							<textarea  rows="5" cols="50" readonly="readonly" id="filename" ></textarea><br/>
							 </td>
				</tr>
				<tr bgcolor="f5f5f5">
					<td width="25%">
						<div align="left">
							达产达效原因（或不达产达效原因)： 
						</div>
					</td>
					<td width="75%">
						<textarea  name="outcome.cReason"> </textarea>
							  </td>
				</tr>
				<tr bgcolor="f5f5f5">
					<td width="25%">
						<div align="left">
							与原目标对比情况： 
						</div>
					</td>
					<td width="75%">
						<textarea  name="outcome.cCompare"> </textarea>
							  </td>
				</tr>
				
				<tr bgcolor="f5f5f5">
					<td width="25%">
						<div align="left">
						 <input type="submit" value="保存"/>
						</div>
					</td>
					<td width="25%">
						<div align="left">
						 <input type="button" value="取消"/>
						</div>
					</td>
				</tr>
			</table>
           
		</form>
			<div>
			<iframe src="/share/page_footer.jsp" width="100%" height="60px"
				scrolling="no" frameborder="0"></iframe>
		</div>
	</body>
</html>