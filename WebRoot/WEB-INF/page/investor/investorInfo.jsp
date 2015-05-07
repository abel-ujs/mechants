<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp"%>
<html>
	<head>
		<title>投资方详情</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="/css/formValidationStyle.css"
			type="text/css">
		<link rel="stylesheet" href="/css/projectInformation/content.css" />
		<link rel="stylesheet" href="/css/projectInformation/content_list.css" />
		<link rel="stylesheet" href="/css/projectInformation/vip.css"
			type="text/css">
		<script type="text/javascript">
	function closeFun() {
		var index = parent.layer.getFrameIndex();
		parent.layer.close(index);
	}
</script>
	</head>

	<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0"
		marginwidth="0" marginheight="0">
		<div id="pform">

			<div class="ftitle">
				<font>投资方详情</font>
			</div>
			<div class="cont">
				<table cellspacing="0" cellpadding="0">
					<tbody>
						<tr>
							<td class="listltd">
								<font>投资方Id：</font>
							</td>
							<td class="listrtd" colspan="2">
								<input type="text" value="${investor.id}" disabled="disabled" />
							</td>
						</tr>

						<tr>
							<td class="listltd">
								<font>投资方名称：</font>
							</td>
							<td class="listrtd" colspan="2">
								<input type="text" value="${investor.cInvName}"
									disabled="disabled" />
							</td>
						</tr>
						<tr>
							<td class="listltd">
								<font>地址 ：</font>
							</td>
							<td class="listrtd" colspan="2">
								<input type="text" value="${investor.cInvAddress}"
									disabled="disabled" />
							</td>
						</tr>
						<tr>
							<td class="listltd">
								<font>网址 ：</font>
							</td>
							<td class="listrtd" colspan="2">
								<input type="text" value="${investor.cInvWebSite}"
									disabled="disabled" />
							</td>
						</tr>
						<tr>
							<td class="listltd">
								<font>所属企业性质 ：</font>
							</td>
							<td class="listrtd" colspan="2">
								<input type="text" value="${investor.nature.cNatName}"
									disabled="disabled" />
							</td>
						</tr>
						<tr>
							<td class="listltd">
								<font>行业信息：</font>
							</td>
							<td class="listrtd" colspan="2">
								<input type="text" value="${investor.industry.cInduName}"
									disabled="disabled" />
							</td>
						</tr>
						<tr>
							<td class="listltd">
								<font>法人：</font>
							</td>
							<td class="listrtd" colspan="2">
								<input type="text" value="${investor.cInvLPerson}"
									disabled="disabled" />
							</td>
						</tr>

						<tr>
							<td class="listltd">
								<font>固定电话：</font>
							</td>
							<td class="listrtd" colspan="2">
								<input type="text" value="${investor.cInvLPhone}"
									disabled="disabled" />
							</td>
						</tr>
						<tr>
							<td class="listltd">
								<font>传真：</font>
							</td>
							<td class="listrtd" colspan="2">
								<input type="text" value="${investor.cInvLFax}"
									disabled="disabled" />
							</td>
						</tr>


						<tr>
							<td class="listltd">
								<font>手机：</font>
							</td>
							<td class="listrtd" colspan="2">
								<input type="text" value="${investor.cInvLHand}"
									disabled="disabled" />
							</td>
						</tr>
						<tr>
							<td class="listltd">
								<font>Email：</font>
							</td>
							<td class="listrtd" colspan="2">
								<input type="text" value="${investor.cInvLEmail}"
									disabled="disabled" />
							</td>
						</tr>
						<tr>
							<td class="listltd">
								<font>联系人：</font>
							</td>
							<td class="listrtd" colspan="2">
								<input type="text" value="${investor.cInvPerson}"
									disabled="disabled" />
							</td>
						</tr>
						<tr>
							<td class="listltd">
								<font>注册资金：</font>
							</td>
							<td class="listrtd" colspan="2">
								<input type="text" value="${investor.dCapital}"
									disabled="disabled" />
							</td>
						</tr>
						<tr>
							<td class="listltd">
								<font>年产值：</font>
							</td>
							<td class="listrtd" colspan="2">
								<input type="text" value="${investor.dOValue}"
									disabled="disabled" />
							</td>
						</tr>

						<tr>
							<td class="listltd">
								<font>行业地位：</font>
							</td>
							<td class="listrtd" colspan="2">
								<input type="text" value="${investor.cIndustry}"
									disabled="disabled" />
							</td>
						</tr>
						<tr>
							<td class="listltd">
								<font>技术水平：</font>
							</td>
							<td class="listrtd" colspan="2">
								<input type="text" value="${investor.cLevel}"
									disabled="disabled" />
							</td>
						</tr>
						<tr>
							<td class="listltd">
								<font>主要产品：</font>
							</td>
							<td class="listrtd" colspan="2">
								<textarea rows="5" cols="40">${investor.cMainProducts}</textarea>
							</td>
						</tr>
						<tr>
							<td class="listltd">
								<font>产品竞争力：</font>
							</td>
							<td class="listrtd" colspan="2">
								<textarea rows="5" cols="40">${investor.cCompeProducts}</textarea>
							</td>
						</tr>
						<tr>
							<td class="listltd">
								<font>市场分额：</font>
							</td>
							<td class="listrtd" colspan="2">
								<textarea rows="5" cols="40">${investor.cMarketShare}</textarea>
							</td>
						</tr>
						<tr>
							<td class="listltd">
								<font>产品销售对象信息：</font>
							</td>
							<td class="listrtd" colspan="2">
								<textarea rows="5" cols="40">${investor.cInformation}</textarea>
							</td>
						</tr>
						<tr>
							<td class="listltd">
								<font>建档人员：</font>
							</td>
							<td class="listrtd" colspan="2">
								<input type="text" value="${investor.cOperator}"
									disabled="disabled" />
							</td>
						</tr>
					</tbody>
				</table>
			</div>

		</div>
	</body>
</html>