<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp"%>
<html>
	<head>
		<title>科技新城招商跟踪管理平台-投资方信息修改</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="/css/canvassResource/content.css" />
		<link rel="stylesheet" href="/css/canvassResource/vip.css"
			type="text/css">
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
				action="/canvassResource/front/investor_update" method="post">
				<input type="hidden" name="id" value="${investor.id}" />
				<div class="ftitle">
					<font>投资方信息修改</font>
				</div>
				<div class="cont">
					<table cellspacing="0" cellpadding="0">
						<tbody>
							<tr>
								<td class="ltd">
									<font> 投资方ID：</font>
								</td>
								<td class="rtd">
									<input type="text"   value="${investor.id}"
										disabled="disabled" />
								</td>
							</tr>
							<tr>
								<td class="ltd">
									<font> 投资方名称：</font>
								</td>
								<td class="rtd">
									<input type="text" name="investor.cInvName"
										value="${investor.cInvName}" disabled="disabled" />
								</td>
							</tr>

							<tr>
								<td class="ltd">
									<font> 地址：</font>
								</td>
								<td class="rtd">
									<input type="text" name="investor.cInvAddress"
										value="${investor.cInvAddress}" />

								</td>
							</tr>
							<tr>
								<td class="ltd">
									<font> 网址：</font>
								</td>
								<td class="rtd">
									<input type="text" name="investor.cInvWebSite"
										value="${investor.cInvWebSite}" />
								</td>
							</tr>
							<tr>
								<td class="ltd">
									<font> 所属企业性质：</font>
								</td>
								<td class="rtd">
									<select name="natureId">
										<option value="" selected="selected">
											请选择
										</option>
										<!-- 接收所属企业性质信息： -->
										<c:forEach items="${requestScope.natures}" var="entity">

											<option value="${entity.id}"
												<c:if test="${investor.nature.id==entity.id}">selected="selected"</c:if>>
												${entity.cNatName}
											</option>
										</c:forEach>
									</select>

								</td>
							</tr>
							<tr>
								<td class="ltd">
									<font> 行业信息：</font>
								</td>
								<td class="rtd">
									<select name="industryId">
										<option value="" selected="selected">
											请选择
										</option>
										<!-- 接收行业信息： -->
										<c:forEach items="${requestScope.industries}" var="entity">
											<option value="${entity.id}"
												<c:if test="${investor.industry.id==entity.id}">selected="selected"</c:if>>
												${entity.cInduName}
											</option>
										</c:forEach>
									</select>

								</td>
							</tr>

							<tr>
								<td class="ltd">
									<font> 法人：</font>
								</td>
								<td class="rtd">
									<input type="text" name="investor.cInvLPerson"
										value="${investor.cInvLPerson}" />

								</td>
							</tr>
							<tr>
								<td class="ltd">
									<font> 固定电话：</font>
								</td>
								<td class="rtd">
									<input type="text" name="investor.cInvLPhone"
										onchange="isFixedPhone(this)" value="${investor.cInvLPhone}" />
								</td>
							</tr>
							<tr>
								<td class="ltd">
									<font> 传真：</font>
								</td>
								<td class="rtd">
									<input type="text" name="investor.cInvLFax"
										onchange="isFaxNumber(this)" value="${investor.cInvLFax}" />
								</td>
							</tr>
							<tr>
								<td class="ltd">
									<font> 手机：</font>
								</td>
								<td class="rtd">
									<input type="text" name="investor.cInvLHand"
										value="${investor.cInvLHand}" onchange="isphoneNum(this)" />
								</td>
							</tr>
							<tr>
								<td class="ltd">
									<font> Email：</font>
								</td>
								<td class="rtd">
									<input type="text" name="investor.cInvLEmail"
										value="${investor.cInvLEmail}" onchange="isEmail(this)" />
								</td>
							</tr>
							<tr>
								<td class="ltd">
									<font>联系人：</font>
								</td>
								<td class="rtd">
									<input type="text" name="investor.cInvPerson"
										value="${investor.cInvPerson}" />
									<label id="label" style="color: #F00">
										*
									</label>
								</td>
							</tr>
							<tr>
								<td class="ltd">
									<font> 注册资金：</font>
								</td>
								<td class="rtd">
									<input onchange="isMoney(this)" type="text"
										value="${investor.dCapital}" name="investor.dCapital" />

								</td>
							</tr>
							<tr>
								<td class="ltd">
									<font> 年产值：</font>
								</td>
								<td class="rtd">
									<input type="text" name="investor.dOValue"
										onchange="isMoney(this)" value="${investor.dOValue}" />

								</td>
							</tr>
							<tr>
								<td class="ltd">
									<font> 行业地位：</font>
								</td>
								<td class="rtd">
									<input type="text" name="investor.cIndustry"
										value="${investor.cIndustry}" />

								</td>
							</tr>
							<tr>
								<td class="ltd">
									<font> 技术水平：</font>
								</td>
								<td class="rtd">
									<input type="text" name="investor.cLevel"
										value="${investor.cLevel}" />

								</td>
							</tr>
							<tr>
								<td class="ltd">
									<font> 主要产品：</font>
								</td>
								<td class="rtd">
									&nbsp;&nbsp;
									<textarea rows="5" cols="40" name="investor.cMainProducts">${investor.cMainProducts}</textarea>
								</td>
							</tr>
							<tr>
								<td class="ltd">
									<font> 产品竞争力：</font>
								</td>
								<td class="rtd">

									&nbsp;&nbsp;
									<textarea rows="5" cols="40" name="investor.cCompeProducts">${investor.cCompeProducts}</textarea>
								</td>
							</tr>
							<tr>
								<td class="ltd">
									<font> 市场分额：</font>
								</td>
								<td class="rtd">

									&nbsp;&nbsp;
									<textarea rows="5" cols="40" name="investor.cMarketShare">${investor.cMarketShare}</textarea>
								</td>
							</tr>
							<tr>
								<td class="ltd">
									<font> 产品销售对象信息：</font>
								</td>
								<td class="rtd">

									&nbsp;&nbsp;
									<textarea rows="5" cols="40" name="investor.cInformation">${investor.cInformation}</textarea>
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
												<input onclick="window.location.href='/canvassResource/front/investor_displayList'" type="button" value="取&nbsp;消" class="fButtonDel">
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