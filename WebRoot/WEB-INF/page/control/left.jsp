<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title></title>
		<link href="/css/base.css" rel="stylesheet" type="text/css" />
		<link href="/css/left.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="/js/jquery-1.7.2.min.js"></script>
		<script type="text/javascript" src="/js/left.js"></script>
		<link rel="StyleSheet" href="/css/dtree.css" type="text/css" />
		<script type="text/javascript" src="/js/dtree_v3.02.js"></script>

	</head>
	<body>
		<form name="form1" method="post" action="" id="form1">
			<div>
			</div>
			<div id="MenuControl1_test">
				<div id="gxz" runat="server">
					<!-- 菜单盒子 -->
					<div id="nav_list" runat="server">
						<div id=move_tool></div>
						<merchants:permission privilege="menu" module="canvassResource">
							<h1 id="001">
								<a href="javascript:;"> <img src="/images/ico09.gif" />招商资源管理</a>
							</h1>
							<div class="nav">
								<h2>
									<img src="/images/ico09.gif" />
									招商资源管理
								</h2>
								<div class="tree_box">
									<ul>
										<div class="dtree">
											<script type="text/javascript">
	d0 = new dTree('d0');
	d0.config.target = "right";
	d0.add(0, -1, '招商资源管理', '', '', '', '/images/ico09.gif',
			'/images/ico09.gif');
	d0.add('0f2d4011-0c3b-4489-a7b6-3c95d8baf001', 0, '介绍人管理',
			'/canvassResource/front/personal_display2', '', '',
			'/images//Menu/show.jpg', '');
	d0.add('0f2d4011-0c3b-4489-a7b6-3c95d8baf001', 0, '介绍单位管理',
			'/canvassResource/front/customer_display2', '', '',
			'/images//Menu/show.jpg', '');
	d0.add('0f2d4011-0c3b-4489-a7b6-3c95d8baf001', 0, '投资方管理',
			'/canvassResource/front/investor_display2', '', '',
			'/images//Menu/show.jpg', '');

	document.write(d0);
</script>
										</div>
									</ul>
								</div>
							</div>
						</merchants:permission>
						<merchants:permission privilege="menu1" module="project">
							<h1 id="041">
								<a href="javascript:;"> <img src="/images/ico09.gif" />意向项目管理</a>
							</h1>
							<div class="nav">
								<h2>
									<img src="/images/ico09.gif" />

								</h2>
								<div class="tree_box">
									<ul>
										<div class="dtree">
											<script type="text/javascript">
	d4 = new dTree('d4');
	d4.config.target = "right";
	
	d4.add(0, -1, '意向项目管理', '', '', '', '/images//232.gif', '/images//232.gif');

	d4.add('1f2d4011-0c3b-4489-a7b6-3c95d8baf010', 0, '已保存意向项目管理',
			'/projectInformation/front/project_displaySaveUI', '', '',
			'/images//Menu/show.jpg', '');
	d4.add('1f2d4011-0c3b-4489-a7b6-3c95d8baf011', 0, '已提交意向项目管理',
			'/projectInformation/front/project_displaySubmitUI', '', '',
			'/images//Menu/show.jpg', '');
	document.write(d4);
</script>
										</div>
									</ul>
								</div>
							</div>
						</merchants:permission>
						<merchants:permission privilege="menu" module="commun">
							<h1 id="0121">
								<a href="javascript:;"><img src="/images/ico09.gif" />研判项目管理</a>
							</h1>
							<div class="nav">
								<h2>
									<img src="/images/ico09.gif" />
									研判项目管理
								</h2>
								<div class="tree_box">
									<ul>
										<div class="dtree">
											<script type="text/javascript">
	d12 = new dTree('d12');
	d12.config.target = "right";
	d12.add(0, -1, ' 研判项目管理', '', '', '', '/images//group_gear.png',
			'/images//group_gear.png');

	d12.add('2f2d4011-0c3b-4489-a7b6-3c95d8baf002', 0, '已保存研判项目',
			' /group/co/indexSave', '', '', '/images//Menu/input.jpg', '');
	d12.add('2f2d4011-0c3b-4489-a7b6-3c95d8baf003', 0, '已提交研判项目',
			' /group/co/indexSubmit', '', '', '/images//Menu/show.jpg', '');
	document.write(d12);
</script>
										</div>
									</ul>
								</div>
							</div>
						</merchants:permission>
						<merchants:permission privilege="menu" module="negotiate">
							<h1 id="0261">
								<a href="javascript:;"> <img src="/images/ico09.gif" />洽谈项目管理</a>
							</h1>
							<div class="nav">
								<h2>
									<img src="/images/ico09.gif" />
									洽谈项目管理
								</h2>
								<div class="tree_box">
									<ul>
										<div class="dtree">
											<script type="text/javascript">
	d26 = new dTree('d26');
	d26.config.target = "right";
	d26.add(0, -1, '洽谈项目管理', '', '', '', '../images/Main/ico09.gif',
			'../images/Main/ico09.gif');
	d26.add('3f2d4011-0c3b-4489-a7b6-3c95d8baf002', 0, '已保存洽谈项目',
			' /group/ne/indexSave', '', '', '/images//Menu/input.jpg', '');
	d26.add('3f2d4011-0c3b-4489-a7b6-3c95d8baf003', 0, '已提交洽谈项目',
			' /group/ne/indexSubmit', '', '', '/images//Menu/show.jpg', '');
	

	document.write(d26);
</script>
										</div>
									</ul>
								</div>
							</div>
						</merchants:permission>
						<merchants:permission privilege="menu" module="sign">
							<h1 id="0571">
								<a href="javascript:;"> <img src="/images/ico09.gif" />签约项目管理</a>
							</h1>
							<div class="nav">
								<h2>
									<img src="/images/ico09.gif" />
									签约项目管理
								</h2>
								<div class="tree_box">
									<ul>
										<div class="dtree">
											<script type="text/javascript">
	d57 = new dTree('d57');
	d57.config.target = "right";
	d57.add(0, -1, '签约项目管理', '', '', '', '../images/icons/chart_bar.png',
			'..//images/icons/chart_bar.png');
	d57.add('4f2d4011-0c3b-4489-a7b6-3c95d8baf002', 0, '已保存签约项目',
			'/group/sign/indexSave ', '', '', '/images//Menu/input.jpg', '');
	d57.add('4f2d4011-0c3b-4489-a7b6-3c95d8baf003', 0, '已提交签约项目',
			'/group/sign/indexSubmit ', '', '', '/images//Menu/show.jpg', '');
	document.write(d57);
</script>
										</div>
									</ul>
								</div>
							</div>
						</merchants:permission>
						<merchants:permission privilege="menu" module="settled">
							<h1 id="0231">
								<a href="javascript:;"> <img src="/images/ico09.gif" />落户项目管理</a>
							</h1>
							<div class="nav">
								<h2>
									<img src="/images/ico09.gif" />
									落户项目管理
								</h2>
								<div class="tree_box">
									<ul>
										<div class="dtree">
											<script type="text/javascript">
	d23 = new dTree('d23');
	d23.config.target = "right";
	d23.add(0, -1, '落户项目管理', '', '', '', '../images/icons/chart_bar.png',
			'..//images/icons/chart_bar.png');
	d23.add('5f2d4011-0c3b-4489-a7b6-3c95d8baf002', 0, '已保存落户项目',
			'/group/se/indexSave', '', '', '/images//Menu/input.jpg', '');
	d23.add('5f2d4011-0c3b-4489-a7b6-3c95d8baf003', 0, '已提交落户项目',
			'/group/se/indexSubmit', '', '', '/images//Menu/show.jpg', '');

	document.write(d23);
</script>
										</div>
									</ul>
								</div>
							</div>
						</merchants:permission>
						<merchants:permission privilege="menu" module="outcome">
							<h1 id="0451">
								<a href="javascript:;"> <img src="/images/ico09.gif" />达产达效项目管理</a>
							</h1>
							<div class="nav">
								<h2>
									<img src="/images/ico09.gif" />
									达产达效项目管理
								</h2>
								<div class="tree_box">
									<ul>
										<div class="dtree">
											<script type="text/javascript">
	d45 = new dTree('d45');
	d45.config.target = "right";
	d45.add(0, -1, '达产达效项目管理', '', '', '', '', '');
	d45.add('6f2d4011-0c3b-4489-a7b6-3c95d8baf002', 0, '已保存达产达效项目',
			'/group/outcome/indexSave', '', '', '/images//Menu/input.jpg', '');
	d45.add('6f2d4011-0c3b-4489-a7b6-3c95d8baf003', 0, '已提交达产达效项目',
			' /group/outcome/indexSubmit', '', '', '/images//Menu/show.jpg',
			'');

	document.write(d45);
</script>
										</div>
									</ul>
								</div>
							</div>
						</merchants:permission>
						<merchants:permission privilege="menu" module="capital">
							<h1 id="0401">
								<a href="javascript:;"> <img src="/images/ico09.gif" />增资扩股项目管理</a>
							</h1>
							<div class="nav">
								<h2>
									<img src="/images/ico09.gif" />
									增资扩股项目管理
								</h2>
								<div class="tree_box">
									<ul>
										<div class="dtree">
											<script type="text/javascript">
	d40 = new dTree('d40');
	d40.config.target = "right";
	d40.add(0, -1, '增资扩股项目管理', '', '', '', '', '');
	d40.add('7f2d4011-0c3b-4489-a7b6-3c95d8baf002', 0, '已保存增资扩股项目',
			' /group/ca/indexSave', '', '', '/images//Menu/input.jpg', '');
	d40.add('7f2d4011-0c3b-4489-a7b6-3c95d8baf003', 0, '已提交增资扩股项目',
			' /group/ca/indexSubmit', '', '', '/images//Menu/show.jpg', '');


	document.write(d40);
</script>
										</div>
									</ul>
								</div>
							</div>
						</merchants:permission>
						<merchants:permission privilege="menu2" module="project">
							<h1 id="0411">
								<a href="javascript:;"> <img src="/images/ico09.gif" />领导审核</a>
							</h1>
							<div class="nav">
								<h2>
									<img src="/images/ico09.gif" />
									领导审核
								</h2>
								<div class="tree_box">
									<ul>
										<div class="dtree">
											<script type="text/javascript">
	d41 = new dTree('d41');
	d41.config.target = "right";
	d41.add(0, -1, '领导审核', '', '', '', '', '');
	d41.add('8f2d4011-0c3b-4489-a7b6-3c95d8baf002', 0, '意向项目审核',
			'/projectInformation/front/project_intentionCheck', '', '',
			'/images//Menu/management.gif', '');
	d41.add('8f2d4011-0c3b-4489-a7b6-3c95d8baf003', 0, '研判项目审核',
			'/projectInformation/front/project_communCheck', '', '',
			'/images//Menu/management.gif', '');
	d41.add('8f2d4011-0c3b-4489-a7b6-3c95d8baf004', 0, '恰谈项目审核',
			'/projectInformation/front/project_negotiateCheck', '', '',
			'/images//Menu/management.gif', '');
	d41.add('8f2d4011-0c3b-4489-a7b6-3c95d8baf005', 0, '签约项目审核',
			'/projectInformation/front/project_signCheck', '', '',
			'/images//Menu/management.gif', '');
	d41.add('8f2d4011-0c3b-4489-a7b6-3c95d8baf006', 0, '落户项目审核',
			'/projectInformation/front/project_settledCheck', '', '',
			'/images//Menu/management.gif', '');
	d41.add('8f2d4011-0c3b-4489-a7b6-3c95d8baf007', 0, '达产达效项目审核',
			' /projectInformation/front/project_outcomeCheck', '', '',
			'/images//Menu/management.gif', '');
	d41.add('8f2d4011-0c3b-4489-a7b6-3c95d8baf008', 0, '增资扩股项目审核',
			'/projectInformation/front/project_capitalCheck',
			'', '', '/images//Menu/management.gif', '');
	document.write(d41);
</script>
										</div>
									</ul>
								</div>
							</div>
						</merchants:permission>
						<merchants:permission privilege="menu" module="report">
							<h1 id="0421">
								<a href="javascript:;"> <img
										src="/images//Menu/statistics.png" />分类统计</a>
							</h1>
							<div class="nav">
								<h2>
									<img src="/images/Menu/statistics.png" />
									分类统计
								</h2>
								<div class="tree_box">
									<ul>
										<div class="dtree">
											<script type="text/javascript">
	d42 = new dTree('d42');
	d42.config.target = "right";
	d42.add(0, -1, '分类统计', '', '', '', '/images//Menu/statistics.png', '');
	d42.add('9f2d4011-0c3b-4489-a7b6-3c95d8baf002', 0, '按类别统计',
			' /report/report_groupCategory', '', '',
			'/images//Menu/statistics.png', '');
	d42.add('9f2d4011-0c3b-4489-a7b6-3c95d8baf003', 0, '按行业统计',
			'/report/report_groupIndustry', '', '',
			'/images//Menu/statistics.png', '');
	d42.add('9f2d4011-0c3b-4489-a7b6-3c95d8baf004', 0, '按状态统计',
			' /report/report_groupState', '', '',
			'/images//Menu/statistics.png', '');
	d42.add('9f2d4011-0c3b-4489-a7b6-3c95d8baf005', 0, '按来源统计',
			'/report/report_groupSource', '', '',
			'/images//Menu/statistics.png', '');
	d42.add('9f2d4011-0c3b-4489-a7b6-3c95d8baf006', 0, '按规模统计',
			' /report/report_groupScale', '', '',
			'/images//Menu/statistics.png', '');
	document.write(d42);
</script>
										</div>
									</ul>
								</div>
							</div>
						</merchants:permission>
						<merchants:permission privilege="menu3" module="project">
							<h1 id="0431">
								<a href="javascript:;"> <img src="/images//Menu/show.jpg" />招商过程结果查看</a>
							</h1>
							<div class="nav">
								<h2>
									<img src="/images//Menu/show.jpg" />
									招商过程结果查看
								</h2>
								<div class="tree_box">
									<ul>
										<div class="dtree">
											<script type="text/javascript">
	d43 = new dTree('d43');
	d43.config.target = "right";
	d43.add(0, -1, '招商过程结果查看', '', '', '/images//Menu/show.jpg',
			'/images//Menu/show.jpg', '/images//Menu/show.jpg');
 
	d43.add('af2d4011-0c3b-4489-a7b6-3c95d8baf003', 0, '招商过程结果查看',
			'/projectInformation/front/project_projectEndNew', '', '',
			'/images//Menu/show.jpg', '');
	document.write(d43);
</script>
										</div>
									</ul>
								</div>
							</div>
						</merchants:permission>
						<merchants:permission privilege="menu" module="console">
							<h1 id="0441">
								<a href="javascript:;"> <img src="/images/ico09.gif" />后台管理</a>
							</h1>
							<div class="nav">
								<h2>
									<img src="/images/ico09.gif" />
									后台管理
								</h2>
								<div class="tree_box">
									<ul>
										<div class="dtree">
											<script type="text/javascript">
	d44 = new dTree('d44');
	d44.config.target = "right";
	d44.add(0, -1, '后台管理', '', '', '', '', '');
	d44.add('b22d4011-0c3b-4489-a7b6-3c95d8baf004', 0, '部门信息管理',
			'/control/department/list', '', '', '/images//Menu/management.gif',
			'');
	d44.add('b22d4011-0c3b-4489-a7b6-3c95d8baf001', 0, '员工信息管理',
			'/canvassGroup/front/hRPerson_list', '', '',
			'/images//Menu/management.gif', '');
	d44.add('b22d4011-0c3b-4489-a7b6-3c95d8baf002', 0, '员工角色分配',
			'/control/employee/list', '', '', '/images//Menu/set.png', '');
	d44.add('b22d4011-0c3b-4489-a7b6-3c95d8baf003', 0, '角色管理',
			'/control/privilege/list', '', '', '/images//Menu/management.gif',
			'');

	document.write(d44);
</script>
										</div>
									</ul>
								</div>
							</div>
						</merchants:permission>
					</div>

					<div id="min_navList">
						<!-- 迷你导航 -->
						<a href="javascript:;" id="min_navList_tool"></a>
						<ul id="min_navList_ul">
							<merchants:permission privilege="menu" module="canvassResource">
								<li class="01">
									<a title="招商资源管理" href="javascript:;"><img
											src="../Images/Menu/globe.gif" /> </a>
								</li>
							</merchants:permission>
							<merchants:permission privilege="menu1" module="project">
								<li class="01">
									<a title="意向项目管理" href="javascript:;"><img
											src="../Images/Menu/cog_add.png" /> </a>
								</li>
							</merchants:permission>
							<merchants:permission privilege="menu" module="commun">
								<li class="01">
									<a title="研判项目管理" href="javascript:;"><img
											src="../Images/Menu/232.gif" /> </a>
								</li>
							</merchants:permission>
							<merchants:permission privilege="menu" module="negotiate">
								<li class="01">
									<a title="洽谈项目管理" href="javascript:;"><img
											src="../Images/Menu/group_gear.png" /> </a>
								</li>
							</merchants:permission>

							<merchants:permission privilege="menu" module="sign">
								<li class="01">
									<a title="签约项目管理" href="javascript:;"><img
											src="../Images/Main/ico09.gif" /> </a>
								</li>
							</merchants:permission>

							<merchants:permission privilege="menu" module="settled">
								<li class="01">
									<a title="落户项目管理" href="javascript:;"><img
											src="../Images/icons/chart_line_edit.png" /> </a>
								</li>
							</merchants:permission>

							<merchants:permission privilege="menu" module="outcome">
								<li class="01">
									<a title="达产达效项目管理" href="javascript:;"><img
											src="../Images/icons/database_edit.png" /> </a>
								</li>
							</merchants:permission>
							<merchants:permission privilege="menu" module="capital">
								<li class="01">
									<a title="增资扩股项目管理" href="javascript:;"><img
											src="/images//Menu/statistics.png" /> </a>
								</li>
							</merchants:permission>
							<merchants:permission privilege="menu2" module="project">
								<li class="01">
									<a title="领导审核" href="javascript:;"><img
											src="/images//Menu/statistics.png" /> </a>
								</li>
							</merchants:permission>
							<merchants:permission privilege="menu" module="report">
								<li class="01">
									<a title="分类统计" href="javascript:;"><img
											src="/images//Menu/statistics.png" /> </a>
								</li>
							</merchants:permission>
							<merchants:permission privilege="menu3" module="project">
								<li class="01">
									<a title="招商过程结果查看" href="javascript:;"><img
											src="/images//Menu/statistics.png" /> </a>
								</li>
							</merchants:permission>
							<merchants:permission privilege="menu" module="console">
								<li class="01">
									<a title="后台管理" href="javascript:;"><img
											src="/images//Menu/statistics.png" /> </a>
								</li>
							</merchants:permission>

						</ul>
					</div>
				</div>
			</div>
		</form>
	</body>
</html>
