package merchants.web.action.canvassGruop;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import merchants.entity.Iverfy;
import merchants.entity.PageView;
import merchants.entity.QueryResult;
import merchants.entity.Status;
import merchants.entity.canvassGroup.HRPerson;
import merchants.entity.canvassGroup.Outcome;
import merchants.entity.canvassGroup.Pro_Capital_In;
import merchants.entity.canvassGroup.Pro_Enclosed;
import merchants.entity.canvassGroup.Pro_Negotiate;
import merchants.entity.canvassGroup.ProjectGroup;
import merchants.entity.projectInformation.ProjectInfo;
import merchants.service.inter.canvassGroup.EnclosedService;
import merchants.service.inter.canvassGroup.HRPersonService;
import merchants.service.inter.canvassGroup.OutcomeService;
import merchants.service.inter.canvassGroup.ProjectGroupService;
import merchants.service.inter.projectInformation.ProjectService;
import merchants.web.action.base.BaseAction;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

/**
 * 达产达效操作Action
 */
@Controller
@Scope("prototype")
public class OutComeAction extends BaseAction {

	@Resource
	private ProjectService projectService;

	@Resource
	private OutcomeService outcomeService;

	@Resource
	ProjectGroupService projectGroupService;

	@Resource
	HRPersonService HRPersonService;

	/* easy-ui */
	public String indexSave() {
		return "save";
	}

	public String indexSubmit() {
		return "submit";
	}

	/* 加载保存项目 */
	@SuppressWarnings("unchecked")
	public void loadSave() throws IOException {
		System.out.println("load save start");
		Integer page = Integer.valueOf(ServletActionContext.getRequest()
				.getParameter("page"));
		Integer rows = Integer.valueOf(ServletActionContext.getRequest()
				.getParameter("rows"));

		JsonConfig config = new JsonConfig();
		config.setJsonPropertyFilter(new PropertyFilter() {
			public boolean apply(Object source, String name, Object value) {
				if (name.equals("project") || name.equals("ddate")) {
					return true;
				} else {
					return false;
				}
			}
		});
		PageView<Outcome> pageView = new PageView<Outcome>(rows, page);
		HRPerson loginUserOld = (HRPerson) ServletActionContext.getRequest()
				.getSession().getAttribute("user");
		HRPerson loginUser = HRPersonService.find(loginUserOld.getId());
		ProjectGroup group = projectGroupService.find(loginUser
				.getProjectGroup().getId());
		System.out.println("groupname" + group.getcGroupName());
		StringBuffer where = new StringBuffer();
		List<Object> parameters = new ArrayList<Object>();
		where
				.append(" o.project.iVerify=?1 and o.project.cState=?2 and o.project.projectGroup=?3 ");
		parameters.add(Iverfy.SAVE);
		parameters.add(Status.OUTCOME);
		parameters.add(group);
		String searchKey = ServletActionContext.getRequest().getParameter(
				"searchKey");
		String searchValue = ServletActionContext.getRequest().getParameter(
				"searchValue");
		System.out.println("serach--》" + searchKey + searchValue);
		if (searchKey != null && searchValue != null) {
			if(searchKey.equals("cProCode")){
				where.append(" and ( o.project.cProCode=?" + (parameters.size() + 1)
						+ " )");
				parameters.add(searchValue);
			}else if(searchKey.equals("cProName")){
				where.append("  and  ( o.project.cProName" + " like ?"
						+ (parameters.size() + 1)+")");
				parameters.add("%" + searchValue + "%");
			}
		}
		int firstindex = (pageView.getCurrentpage() - 1)
				* pageView.getMaxresult();
		QueryResult<Outcome> queryResult = outcomeService.getScrollData(
				firstindex, rows, where.toString(), parameters.toArray());
		long total = outcomeService.getScrollData(-1, -1, where.toString(),
				parameters.toArray()).getTotalrecord();
		Map map = new HashMap();

		ArrayList al = new ArrayList();
		List<Outcome> list = queryResult.getResultlist();
		for (Outcome p : list) {
			JSONObject jo1 = JSONObject.fromObject(p, config);
			ProjectInfo project = p.getProject();
			System.out.println("cprocode" + project.getId()
					+ project.getcProCode());
			jo1.put("cProCode", project.getcProCode());
			jo1.put("cProName", project.getcProName());
			jo1.put("ddate", p.getDdate().getTime());
			al.add(jo1.toString());

		}
		map.put("total", total);
		map.put("rows", al);
		JSONObject jo = JSONObject.fromObject(map);
		System.out.println(jo);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.print(jo);
		out.flush();
		out.close();
	}

	/* 加载提交项目 */
	@SuppressWarnings("unchecked")
	public void loadSubmit() throws IOException {
		Integer page = Integer.valueOf(ServletActionContext.getRequest()
				.getParameter("page"));
		Integer rows = Integer.valueOf(ServletActionContext.getRequest()
				.getParameter("rows"));

		JsonConfig config = new JsonConfig();
		config.setJsonPropertyFilter(new PropertyFilter() {
			public boolean apply(Object source, String name, Object value) {
				if (name.equals("project") || name.equals("ddate")) {
					return true;
				} else {
					return false;
				}
			}
		});
		PageView<Outcome> pageView = new PageView<Outcome>(rows, page);
		HRPerson loginUserOld = (HRPerson) ServletActionContext.getRequest()
				.getSession().getAttribute("user");
		HRPerson loginUser = HRPersonService.find(loginUserOld.getId());
		ProjectGroup group = projectGroupService.find(loginUser
				.getProjectGroup().getId());
		StringBuffer where = new StringBuffer();
		List<Object> parameters = new ArrayList<Object>();
		where
				.append(" (((o.project.iVerify=?4 or o.project.iVerify=?1 )and o.project.cState=?2)or o.project.cState>?2 )and o.project.projectGroup=?3 ");
		parameters.add(Iverfy.SUBMIT);
		parameters.add(Status.OUTCOME);
		parameters.add(group);
		parameters.add(Iverfy.REFUSE);
		String searchKey = ServletActionContext.getRequest().getParameter(
				"searchKey");
		String searchValue = ServletActionContext.getRequest().getParameter(
				"searchValue");
		System.out.println("serach--》" + searchKey + searchValue);
		if (searchKey != null && searchValue != null) {
			if(searchKey.equals("cProCode")){
				where.append(" and ( o.project.cProCode=?" + (parameters.size() + 1)
						+ " )");
				parameters.add(searchValue);
			}else if(searchKey.equals("cProName")){
				where.append("  and  ( o.project.cProName" + " like ?"
						+ (parameters.size() + 1)+")");
				parameters.add("%" + searchValue + "%");
			}
		}
		int firstindex = (pageView.getCurrentpage() - 1)
				* pageView.getMaxresult();
		QueryResult<Outcome> queryResult = outcomeService.getScrollData(
				firstindex, rows, where.toString(), parameters.toArray());
		long total = outcomeService.getScrollData(-1, -1, where.toString(),
				parameters.toArray()).getTotalrecord();
		Map map = new HashMap();
		ArrayList al = new ArrayList();
		String state = "";
		String reason = "";
		List<Outcome> list = queryResult.getResultlist();
		for (Outcome p : list) {

			JSONObject jo1 = JSONObject.fromObject(p, config);
			ProjectInfo project = p.getProject();
			Integer iverify = project.getiVerify();
			System.out.println(iverify);
			switch (iverify) {
			case 1:
				state = "通过";
				break;
			case 2:
				state = "不通过";
				reason = project.getcReason();
				if(reason.equals("")) reason="没有填写原因";
				break;
			case 3:
				state = "未审核";
				break;
			}
			if (project.getcState() > Status.OUTCOME)
				state = "通过";
			jo1.put("state", state);
			jo1.put("reason", reason);
			jo1.put("ddate", p.getDdate().getTime());
			jo1.put("cProCode", project.getcProCode());
			jo1.put("cProName", project.getcProName());
			al.add(jo1.toString());
		}
		map.put("total", total);
		map.put("rows", al);
		JSONObject jo = JSONObject.fromObject(map);
		System.out.println("load submit outcome" + jo);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.print(jo);
		out.flush();
		out.close();
	}

	/* 删除单条记录 */
	@SuppressWarnings("unchecked")
	public void deleteRow() throws IOException {
		System.out.println("start");
		Integer id = Integer.valueOf(ServletActionContext.getRequest()
				.getParameter("id"));
		Map map = new HashMap();
		if (id != null) {
			ProjectInfo project = outcomeService.find(id).getProject();
			projectService.editIverfy(project.getId(), Iverfy.SAVE);
			outcomeService.delete(Integer.valueOf(id));
			map.put("success", true);
		} else
			map.put("errorMsg", "删除失败！");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");
		response.getWriter().print(JSONObject.fromObject(map));
		response.getWriter().flush();
		response.getWriter().close();
	}

	/* 判断是否可编辑 */
	@SuppressWarnings("unchecked")
	public void isEdit() throws IOException {
		String errorMsg = "";
		String cProCode = ServletActionContext.getRequest().getParameter(
				"cProCode");
		ProjectInfo project = projectService.getProjectBycProCode(cProCode);
		if (project.getiVerify() == 1 || project.getiVerify() == 3 || project.getcState() > Status.OUTCOME )
			errorMsg = "不能执行此操作";
		Map map = new HashMap();
		map.put("errorMsg", errorMsg);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.getWriter().print(JSONObject.fromObject(map));
		response.getWriter().flush();
		response.getWriter().close();
	}

	@SuppressWarnings("unchecked")
	public void exsitProject() throws IOException {

		Map map = new HashMap();
		List al = new ArrayList();
		String errorMsg = "";
		HRPerson loginUserOld = (HRPerson) ServletActionContext.getRequest()
				.getSession().getAttribute("user");
		HRPerson loginUser = HRPersonService.find(loginUserOld.getId());
		if (loginUser.getProjectGroup() == null) {
			errorMsg = "没有分配招商小组";
		} else {
			ProjectGroup group = projectGroupService.find(loginUser
					.getProjectGroup().getId());
			List<ProjectInfo> projectInfo = projectService.findProject(group
					.getId(), Status.OUTCOME, Iverfy.SAVE);
			for (ProjectInfo p : projectInfo) {
				if (p.getOutcome() == null) {
					Map m = new HashMap();
					System.out.println(p.getId());
					m.put("id", p.getId());
					m.put("cProName", p.getcProName());
					al.add(m);
				}

			}
		}
		if (al.size() == 0)
			errorMsg = "没有新建项目";
		map.put("errorMsg", errorMsg);
		map.put("project", al);
		System.out.println(JSONObject.fromObject(map));
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.getWriter().print(JSONObject.fromObject(map));
		response.getWriter().flush();
		response.getWriter().close();
	}

	@SuppressWarnings("unchecked")
	public void add() throws IOException {
		HRPerson loginUserOld = (HRPerson) ServletActionContext.getRequest()
				.getSession().getAttribute("user");
		HRPerson loginUser = HRPersonService.find(loginUserOld.getId());
		String errorMsg = "";
		Integer type = Integer.valueOf(ServletActionContext.getRequest()
				.getParameter("type"));
		Integer p = Integer.valueOf(ServletActionContext.getRequest()
				.getParameter("p"));
		Integer projectId = Integer.valueOf(ServletActionContext.getRequest()
				.getParameter("projectId"));
		Double cInvestment = Double.valueOf(ServletActionContext.getRequest()
				.getParameter("cInvestment"));
		String cProfit = ServletActionContext.getRequest().getParameter(
				"cProfit");
		String cMainProducts = ServletActionContext.getRequest().getParameter(
				"cMainProducts");
		String cReason = ServletActionContext.getRequest().getParameter(
				"cReason");
		String cCompare = ServletActionContext.getRequest().getParameter(
				"cCompare");
		String id = ServletActionContext.getRequest().getParameter("id");
		Outcome entity = new Outcome();
		entity.setcInvestment(cInvestment);
		entity.setcProfit(cProfit);
		entity.setcReason(cReason);
		entity.setcCompare(cCompare);
		entity.setcMainProducts(cMainProducts);
		entity.setDdate(new Date());
		entity.setcOperator(loginUser.getcUserName());
		ProjectInfo project = projectService.find(projectId);
		entity.setProject(project);
		System.out.println("p type " + p + type);
		if (type == 1 && p == 1) {
			projectService.editIverfy(projectId, Iverfy.SUBMIT);
			outcomeService.save(entity);
		} else if (type == 1 && p == 0) {
			projectService.editIverfy(projectId, Iverfy.SAVE);
			outcomeService.save(entity);
		} else if (type == 2 && p == 1) {
			entity.setId(Integer.valueOf(id));
			outcomeService.update(entity);
			projectService.editIverfy(projectId, Iverfy.SUBMIT);
		} else if (type == 2 && p == 0) {
			entity.setId(Integer.valueOf(id));
			outcomeService.update(entity);
			projectService.editIverfy(projectId, Iverfy.SAVE);
		}
		Map map = new HashMap();
		map.put("errorMsg", errorMsg);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.getWriter().print(JSONObject.fromObject(map));
		response.getWriter().flush();
		response.getWriter().close();
	}

	@SuppressWarnings("unchecked")
	public void loadEditProject() throws IOException {
		String cProCode = ServletActionContext.getRequest().getParameter(
				"cProCode");
		ProjectInfo project = projectService.getProjectBycProCode(cProCode);
		Map map = new HashMap();
		map.put("id", project.getId());
		map.put("cProName", project.getcProName());
		List a = new ArrayList();
		a.add(map);
		System.out.println(JSONObject.fromObject(map));
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.getWriter().print(
				"[" + JSONObject.fromObject(map).toString() + "]");
		response.getWriter().flush();
		response.getWriter().close();
	}

	public void exportExcel(String fileName, List<Outcome> content) {
		WritableWorkbook wwb;
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(fileName);
			wwb = Workbook.createWorkbook(fos);
			WritableSheet ws = wwb.createSheet("达产达效信息列表", 10); // 创建一个工作表
			// 设置表头单元格的文字格式
			WritableFont wf = new WritableFont(WritableFont.ARIAL, 12,
					WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,
					Colour.BLUE);
			WritableCellFormat wcf = new WritableCellFormat(wf);
			wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
			wcf.setAlignment(Alignment.CENTRE);
			ws.setRowView(1, 500);
			ws.addCell(new Label(0, 0, "项目编号", wcf));
			ws.addCell(new Label(1, 0, "项目名称", wcf));
			ws.addCell(new Label(2, 0, "实际规模", wcf));
			ws.addCell(new Label(3, 0, "实际效益", wcf));
			ws.addCell(new Label(4, 0, "主要产品", wcf));
			ws.addCell(new Label(5, 0, "原因", wcf));
			ws.addCell(new Label(5, 0, "与原目标对比", wcf));
			ws.addCell(new Label(6, 0, "操作员", wcf));
			ws.addCell(new Label(7, 0, "日期", wcf));
			// 设置内容单元格样式 填充数据的内容
			WritableCellFormat contentWCF = new WritableCellFormat();
			for (int i = 0; i < content.size(); i++) {
				ws.addCell(new Label(0, i + 1, content.get(i).getProject()
						.getcProCode(), contentWCF));
				ws.addCell(new Label(1, i + 1, content.get(i).getProject()
						.getcProName(), contentWCF));
				ws.addCell(new Label(2, i + 1, String.valueOf(content.get(i)
						.getcInvestment()), contentWCF));
				ws.addCell(new Label(3, i + 1, content.get(i).getcProfit(),
						contentWCF));
				ws.addCell(new Label(4, i + 1, content.get(i)
						.getcMainProducts(), contentWCF));
				ws.addCell(new Label(5, i + 1, content.get(i).getcReason(),
						contentWCF));
				ws.addCell(new Label(6, i + 1, content.get(i).getcCompare(),
						contentWCF));
				ws.addCell(new Label(7, i + 1, content.get(i).getcOperator(),
						contentWCF));
				ws.addCell(new Label(7, i + 1, content.get(i).getDdate()
						.toString(), contentWCF));
			}
			wwb.write();
			wwb.close();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void excelSave() {
		Map<String, String> map = new HashMap<String, String>();
		String fileName = ServletActionContext.getRequest().getParameter(
				"fileName");
		String filePath = ServletActionContext.getServletContext().getRealPath(
				"\\excel")
				+ "\\" + fileName;
		HRPerson loginUserOld = (HRPerson) ServletActionContext.getRequest()
				.getSession().getAttribute("user");
		HRPerson loginUser = HRPersonService.find(loginUserOld.getId());
		ProjectGroup group = projectGroupService.find(loginUser
				.getProjectGroup().getId());
		StringBuffer where = new StringBuffer();
		List<Object> parameters = new ArrayList<Object>();
		where
				.append(" o.project.iVerify=?1 and o.project.cState=?2 and o.project.projectGroup=?3 ");
		parameters.add(Iverfy.SAVE);
		parameters.add(Status.OUTCOME);
		System.out.println(group.getcGroupName());
		parameters.add(group);
		List<Outcome> content = outcomeService.getScrollData(-1, -1,
				where.toString(), parameters.toArray()).getResultlist();
		exportExcel(filePath, content);
		map.put("fileName", fileName);
		map.put("filePath", "/excel/" + fileName);
		map.put("status", "succeed");
		try {
			ServletActionContext.getResponse().setCharacterEncoding("gbk");
			ServletActionContext.getResponse().getWriter().print(
					JSONObject.fromObject(map));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(JSONObject.fromObject(map).toString());
	}

	public void excelSubmit() {
		Map<String, String> map = new HashMap<String, String>();
		String fileName = ServletActionContext.getRequest().getParameter(
				"fileName");
		String filePath = ServletActionContext.getServletContext().getRealPath(
				"\\excel")
				+ "\\" + fileName;
		HRPerson loginUserOld = (HRPerson) ServletActionContext.getRequest()
				.getSession().getAttribute("user");
		HRPerson loginUser = HRPersonService.find(loginUserOld.getId());
		ProjectGroup group = projectGroupService.find(loginUser
				.getProjectGroup().getId());
		StringBuffer where = new StringBuffer();
		List<Object> parameters = new ArrayList<Object>();
		where
				.append(" ((o.project.iVerify=?4 or o.project.iVerify=?1 or o.project.iVerify=?5)and o.project.cState=?2 and o.project.projectGroup=?3) or o.project.cState>?2");
		parameters.add(Iverfy.SUBMIT);
		parameters.add(Status.OUTCOME);
		parameters.add(group);
		parameters.add(Iverfy.REFUSE);
		parameters.add(Iverfy.PASS);
		List<Outcome> content = outcomeService.getScrollData(-1, -1,
				where.toString(), parameters.toArray()).getResultlist();
		exportExcel(filePath, content);
		map.put("fileName", fileName);
		map.put("filePath", "/excel/" + fileName);
		map.put("status", "succeed");
		System.out.println("成功导出数据到Excel文件" + fileName);
		try {
			ServletActionContext.getResponse().setCharacterEncoding("gbk");
			ServletActionContext.getResponse().getWriter().print(
					JSONObject.fromObject(map));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(JSONObject.fromObject(map).toString());
	}

}
