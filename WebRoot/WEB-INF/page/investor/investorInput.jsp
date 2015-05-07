<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp"%>
<html>
	<head>
		<title>科技新城招商跟踪管理平台-投资方信息录入</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="/css/projectInformation/content.css" />
		<link rel="stylesheet" href="/css/projectInformation/content_list.css" />
		<link rel="stylesheet" href="/css/projectInformation/vip.css"/>
		<script language="javascript" src="/js/DatePicker.js"></script>
		<script language="javascript" src="/js/jquery-1.7.2.min.js"></script>
		<script language="javascript" src="/js/ajax.js"></script>
		<script type="text/javascript">
	//提示用户输入信息

	function setEmpty(obj) {
		obj.value = "";
	}
</script>
		 
	</head>

	<body>

		<div id="pform">
			<form class="customerForm"
				action="/canvassResource/front/investor_save" method="post">
				<div class="ftitle">
					<font>投资方信息录入</font>
				</div>
				<div class="cont">
					<table cellspacing="0" cellpadding="0">
						<tbody>

							<tr>
								<td class="listltd">
									<font> 投资方名称：</font>
								</td>
								<td class="rtd">
									<input type="text" name="investor.cInvName"
										id="investor.cInvName" onchange="checkcInvName()" />
									<label id="cInvName_label" style="color: #F00">
										*
									</label>
									<label id="checkmessage"></label>
								</td>
							</tr>

							<tr>
								<td class="listltd">
									<font> 地址：</font>
								</td>
								<td class="rtd">
									<input type="text" name="investor.cInvAddress" />
									<label id="label" style="color: #F00">
										*
									</label>
								</td>
							</tr>
							<tr>
								<td class="listltd">
									<font> 网址：</font>
								</td>
								<td class="rtd">
									<input type="text" name="investor.cInvWebSite" value="请输入网址" />
								</td>
							</tr>
							<tr>
								<td class="listltd">
									<font> 所属企业性质：</font>
								</td>
								<td class="rtd">
									<select name="natureId">
										<option value="" selected="selected">
											请选择
										</option>
										<!-- 接收所属企业性质信息： -->
										<c:forEach items="${requestScope.natures}" var="entity">
											<option value="${entity.id}">
												${entity.cNatName}
											</option>
										</c:forEach>
									</select>
									<label id="label" style="color: #F00">
										*
									</label>
								</td>
							</tr>
							<tr>
								<td class="listltd">
									<font> 行业信息：</font>
								</td>
								<td class="rtd">
									<select name="industryId">
										<option value="" selected="selected">
											请选择
										</option>
										<!-- 接收行业信息： -->
										<c:forEach items="${requestScope.industries}" var="entity">
											<option value="${entity.id}">
												${entity.cInduName}
											</option>
										</c:forEach>
									</select>
									<label id="label" style="color: #F00">
										*
									</label>
								</td>
							</tr>

							<tr>
								<td class="listltd">
									<font> 法人：</font>
								</td>
								<td class="rtd">
									<input type="text" name="investor.cInvLPerson" />
									<label id="label" style="color: #F00">
										*
									</label>
								</td>
							</tr>
							<tr>
								<td class="listltd">
									<font> 固定电话：</font>
								</td>
								<td class="rtd">
									<input type="text" name="investor.cInvLPhone"
										onchange="isFixedPhone(this)" />
								</td>
							</tr>
							<tr>
								<td class="listltd">
									<font> 传真：</font>
								</td>
								<td class="rtd">
									<input type="text" name="investor.cInvLFax"
										onchange="isFaxNumber(this)" />
								</td>
							</tr>
							<tr>
								<td class="listltd">
									<font> 手机：</font>
								</td>
								<td class="rtd">
									<input type="text" name="investor.cInvLHand"
										onchange="isphoneNum(this)" />
								</td>
							</tr>
							<tr>
								<td class="listltd">
									<font> Email：</font>
								</td>
								<td class="rtd">
									<input type="text" name="investor.cInvLEmail"
										onchange="isEmail(this)" />
								</td>
							</tr>
							<tr>
								<td class="listltd">
									<font>联系人：</font>
								</td>
								<td class="rtd">
									<input type="text" name="investor.cInvPerson"
										  />
									<label id="label" style="color: #F00">
										*
									</label>
								</td>
							</tr>
							<tr>
								<td class="listltd">
									<font> 注册资金：</font>
								</td>
								<td class="rtd">
									<input onchange="isMoney(this)" type="text"
										name="investor.dCapital" />

								</td>
							</tr>
							<tr>
								<td class="listltd">
									<font> 年产值：</font>
								</td>
								<td class="rtd">
									<input type="text" name="investor.dOValue"
										onchange="isMoney(this)" />

								</td>
							</tr>
							<tr>
								<td class="listltd">
									<font> 行业地位：</font>
								</td>
								<td class="rtd">
									<input type="text" name="investor.cIndustry" />

								</td>
							</tr>
							<tr>
								<td class="listltd">
									<font> 技术水平：</font>
								</td>
								<td class="rtd">
									<input type="text" name="investor.cLevel" />

								</td>
							</tr>
							<tr>
								<td class="listltd">
									<font> 主要产品：</font>
								</td>
								<td class="rtd">
									&nbsp;&nbsp;
									<textarea rows="5" cols="40" name="investor.cMainProducts"
	
></textarea>
								</td>
							</tr>
							<tr>
								<td class="listltd">
									<font> 产品竞争力：</font>
								</td>
								<td class="rtd">

									&nbsp;&nbsp;
									<textarea rows="5" cols="40" name="investor.cCompeProducts"
			
></textarea>
								</td>
							</tr>
							<tr>
								<td class="listltd">
									<font> 市场分额：</font>
								</td>
								<td class="rtd">

									&nbsp;&nbsp;
									<textarea rows="5" cols="40" name="investor.cMarketShare"
	
></textarea>
								</td>
							</tr>
							<tr>
								<td class="listltd">
									<font> 产品销售对象信息：</font>
								</td>
								<td class="rtd">

									&nbsp;&nbsp;
									<textarea rows="5" cols="40" name="investor.cInformation"
	
></textarea>
								</td>
							</tr>
							<tr>
								<td colspan="2" id="butbg" style="border: none;">
									<table
										style="border: none; width: 300px; height: 31px; float: left; margin: 10px 0 10px 250px;"
										cellspacing="0" cellpadding="0">
										<tr>
											<td class="fbtd" style="border: none;">
												<input type="submit" value="提&nbsp;交" class="fButtonDel">
											</td>
											<td class="fbtd" style="border: none;">
												<input type="reset" value="取&nbsp;消" class="fButtonDel">
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</form>
		</div>

	</body>
</html>