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
import java.util.Set;

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
import merchants.entity.canvassGroup.Pro_Capital_In;
import merchants.entity.canvassGroup.Pro_Negotiate;
import merchants.entity.canvassGroup.Pro_Signing;
import merchants.entity.canvassGroup.ProjectGroup;
import merchants.entity.projectInformation.ProjectInfo;
import merchants.service.inter.canvassGroup.HRPersonService;
import merchants.service.inter.canvassGroup.NegotiateService;
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

@Controller
@Scope("prototype")
public class NegotiateAction extends BaseAction {
	@Resource
	private NegotiateService negotiateService;

	@Resource
	private ProjectService projectInfoService;

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
		System.out.println("page + rows " + page + ">" + rows);
		JsonConfig config = new JsonConfig();
		config.setJsonPropertyFilter(new PropertyFilter() {
			public boolean apply(Object source, String name, Object value) {
				if (name.equals("project") || name.equals("cdate")
						|| name.equals("datetime") || name.equals("dwillDate")) {
					return true;
				} else {
					return false;
				}
			}
		});
		PageView<Pro_Negotiate> pageView = new PageView<Pro_Negotiate>(rows,
				page);
		HRPerson loginUser = (HRPerson) ServletActionContext.getRequest()
				.getSession().getAttribute("user");
		ProjectGroup group = projectGroupService.find(loginUser
				.getProjectGroup().getId());
		StringBuffer where = new StringBuffer();
		List<Object> parameters = new ArrayList<Object>();
		where
				.append(" o.project.iVerify=?1 and o.project.cState=?2 and o.project.projectGroup=?3 ");
		parameters.add(Iverfy.SAVE);
		parameters.add(Status.NEGO);
		parameters.add(group);
		String searchKey = ServletActionContext.getRequest().getParameter(
				"searchKey");
		String searchValue = ServletActionContext.getRequest().getParameter(
				"searchValue");
		System.out.println("serach--》" + searchKey + searchValue);
		if (searchKey != null && searchValue != null) {
			if (searchKey.equals("cProCode")) {
				where.append(" and ( o.project.cProCode=?"
						+ (parameters.size() + 1) + " )");
				parameters.add(searchValue);
			} else if (searchKey.equals("cProName")) {
				where.append("  and  ( o.project.cProName" + " like ?"
						+ (parameters.size() + 1) + ")");
				parameters.add("%" + searchValue + "%");
			}
		}
		int firstindex = (pageView.getCurrentpage() - 1)
				* pageView.getMaxresult();
		QueryResult<Pro_Negotiate> queryResult = negotiateService
				.getScrollData(firstindex, rows, where.toString(), parameters
						.toArray());
		long total = negotiateService.getScrollData(-1, -1, where.toString(),
				parameters.toArray()).getTotalrecord();
		System.out.println("total" + total);
		Map map = new HashMap();

		ArrayList al = new ArrayList();
		List<Pro_Negotiate> list = queryResult.getResultlist();
		for (Pro_Negotiate p : list) {
			JSONObject jo1 = JSONObject.fromObject(p, config);
			ProjectInfo project = p.getProject();
			System.out.println("cprocode" + project.getId()
					+ project.getcProCode());
			jo1.put("cProCode", project.getcProCode());
			jo1.put("cProName", project.getcProName());
			jo1.put("cdate", p.getCdate().getTime());
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
				if (name.equals("project") || name.equals("cdate")
						|| name.equals("datetime") || name.equals("dwillDate")) {
					return true;
				} else {
					return false;
				}
			}
		});
		PageView<Pro_Negotiate> pageView = new PageView<Pro_Negotiate>(rows,
				page);
		HRPerson loginUser = (HRPerson) ServletActionContext.getRequest()
				.getSession().getAttribute("user");
		ProjectGroup group = projectGroupService.find(loginUser
				.getProjectGroup().getId());
		StringBuffer where = new StringBuffer();
		List<Object> parameters = new ArrayList<Object>();
		where
				.append(" (((o.project.iVerify=?4 or o.project.iVerify=?1 )and o.project.cState=?2) or o.project.cState>?2)and o.project.projectGroup=?3 ");
		parameters.add(Iverfy.SUBMIT);
		parameters.add(Status.NEGO);
		parameters.add(group);
		parameters.add(Iverfy.REFUSE);
		String searchKey = ServletActionContext.getRequest().getParameter(
				"searchKey");
		String searchValue = ServletActionContext.getRequest().getParameter(
				"searchValue");
		System.out.println("serach--》" + searchKey + searchValue);
		if (searchKey != null && searchValue != null) {
			if (searchKey.equals("cProCode")) {
				where.append(" and ( o.project.cProCode=?"
						+ (parameters.size() + 1) + " )");
				parameters.add(searchValue);
			} else if (searchKey.equals("cProName")) {
				where.append("  and  ( o.project.cProName" + " like ?"
						+ (parameters.size() + 1) + ")");
				parameters.add("%" + searchValue + "%");
			}
		}
		int firstindex = (pageView.getCurrentpage() - 1)
				* pageView.getMaxresult();
		QueryResult<Pro_Negotiate> queryResult = negotiateService
				.getScrollData(firstindex, rows, where.toString(), parameters
						.toArray());
		long total = negotiateService.getScrollData(-1, -1, where.toString(),
				parameters.toArray()).getTotalrecord();
		Map map = new HashMap();
		ArrayList al = new ArrayList();
		String state = "";
		String reason = "";
		List<Pro_Negotiate> list = queryResult.getResultlist();
		for (Pro_Negotiate p : list) {

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
				if (reason.equals(""))
					reason = "没有填写原因";
				break;
			case 3:
				state = "未审核";
				break;
			}
			if (project.getcState() > Status.NEGO)
				state = "通过";
			jo1.put("state", state);
			jo1.put("reason", reason);
			jo1.put("cdate", p.getCdate().getTime());
			jo1.put("cProCode", project.getcProCode());
			jo1.put("cProName", project.getcProName());
			al.add(jo1.toString());
		}
		map.put("total", total);
		map.put("rows", al);
		JSONObject jo = JSONObject.fromObject(map);
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
			ProjectInfo project = negotiateService.find(id).getProject();
			projectInfoService.editIverfy(project.getId(), Iverfy.SAVE);
			negotiateService.delete(Integer.valueOf(id));
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
		ProjectInfo project = projectInfoService.getProjectBycProCode(cProCode);
		if (project.getiVerify() == 1 || project.getiVerify() == 3
				|| project.getcState() > Status.NEGO)
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
		HRPerson loginUser = (HRPerson) ServletActionContext.getRequest()
				.getSession().getAttribute("user");
		ProjectGroup group = projectGroupService.find(loginUser
				.getProjectGroup().getId());
		if (loginUser.getProjectGroup() == null) {
			errorMsg = "没有分配招商小组";
		} else {
			List<ProjectInfo> projectInfo = projectInfoService.findProject(
					group.getId(), Status.NEGO, Iverfy.SAVE);
			for (ProjectInfo p : projectInfo) {
				Map m = new HashMap();
				System.out.println(p.getId());
				m.put("id", p.getId());
				m.put("cProName", p.getcProName());
				al.add(m);
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
		System.out.println("add start ");
		HRPerson loginUser = (HRPerson) ServletActionContext.getRequest()
				.getSession().getAttribute("user");
		String errorMsg = "";
		Integer type = Integer.valueOf(ServletActionContext.getRequest()
				.getParameter("type"));
		Integer p = Integer.valueOf(ServletActionContext.getRequest()
				.getParameter("p"));
		Integer projectId = Integer.valueOf(ServletActionContext.getRequest()
				.getParameter("projectId"));
		Map map = ServletActionContext.getRequest().getParameterMap();
		Pro_Negotiate entity = new Pro_Negotiate();
		entity.setCdate(new Date());
		entity.setCng_Address(((String[]) map.get("cng_Address"))[0]);
		entity.setCng_Advice(((String[]) map.get("cng_Advice"))[0]);
		entity.setCng_Analysis(((String[]) map.get("cng_Analysis"))[0]);
		entity.setCng_Content(((String[]) map.get("cng_Content"))[0]);
		entity.setCng_Element(((String[]) map.get("cng_Element"))[0]);
		entity.setCng_Person(((String[]) map.get("cng_Person"))[0]);
		entity.setCng_Problem(((String[]) map.get("cng_Problem"))[0]);
		entity.setCng_Require(((String[]) map.get("cng_Require"))[0]);
		entity.setCng_Result(((String[]) map.get("cng_Result"))[0]);
		entity.setCng_Works(((String[]) map.get("cng_Works"))[0]);
		entity.setCsummarize(((String[]) map.get("csummarize"))[0]);
		String id = ServletActionContext.getRequest().getParameter("id");
		entity.setCoperator(loginUser.getcUserName());
		ProjectInfo project = projectInfoService.find(projectId);
		entity.setProject(project);
		System.out.println("p type " + p + type);
		if (type == 1 && p == 1) {
			projectInfoService.editIverfy(projectId, Iverfy.SUBMIT);
			negotiateService.save(entity);
		} else if (type == 1 && p == 0) {
			projectInfoService.editIverfy(projectId, Iverfy.SAVE);
			negotiateService.save(entity);
		} else if (type == 2 && p == 1) {
			entity.setId(Integer.valueOf(id));
			negotiateService.update(entity);
			projectInfoService.editIverfy(projectId, Iverfy.SUBMIT);
		} else if (type == 2 && p == 0) {
			entity.setId(Integer.valueOf(id));
			negotiateService.update(entity);
			projectInfoService.editIverfy(projectId, Iverfy.SAVE);
		}
		Map m = new HashMap();
		m.put("errorMsg", errorMsg);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.getWriter().print(JSONObject.fromObject(m));
		response.getWriter().flush();
		response.getWriter().close();
	}

	@SuppressWarnings("unchecked")
	public void loadEditProject() throws IOException {
		String cProCode = ServletActionContext.getRequest().getParameter(
				"cProCode");
		ProjectInfo project = projectInfoService.getProjectBycProCode(cProCode);
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

	public void exportExcel(String fileName, List<Pro_Negotiate> content) {
		WritableWorkbook wwb;
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(fileName);
			wwb = Workbook.createWorkbook(fos);
			WritableSheet ws = wwb.createSheet("增资扩股信息列表", 10); // 创建一个工作表
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
			ws.addCell(new Label(2, 0, "洽谈地点", wcf));
			ws.addCell(new Label(3, 0, "洽谈引进建议", wcf));
			ws.addCell(new Label(4, 0, "洽谈分析", wcf));
			ws.addCell(new Label(5, 0, "吸引企业入驻因素", wcf));
			ws.addCell(new Label(5, 0, "参与人员", wcf));
			ws.addCell(new Label(5, 0, "待解决问题", wcf));
			ws.addCell(new Label(5, 0, "项目方要求", wcf));
			ws.addCell(new Label(5, 0, "洽谈结果", wcf));
			ws.addCell(new Label(5, 0, "工作安排", wcf));
			ws.addCell(new Label(5, 0, "小  结", wcf));
			ws.addCell(new Label(6, 0, "操作员", wcf));
			ws.addCell(new Label(7, 0, "日期", wcf));
			// 设置内容单元格样式 填充数据的内容
			WritableCellFormat contentWCF = new WritableCellFormat();
			for (int i = 0; i < content.size(); i++) {
				ws.addCell(new Label(0, i + 1, content.get(i).getProject()
						.getcProCode(), contentWCF));
				ws.addCell(new Label(1, i + 1, content.get(i).getProject()
						.getcProName(), contentWCF));
				ws.addCell(new Label(2, i + 1, content.get(i).getCng_Address(),
						contentWCF));
				ws.addCell(new Label(3, i + 1, content.get(i).getCng_Advice(),
						contentWCF));
				ws.addCell(new Label(4, i + 1,
						content.get(i).getCng_Analysis(), contentWCF));
				ws.addCell(new Label(5, i + 1, content.get(i).getCng_Element(),
						contentWCF));
				ws.addCell(new Label(6, i + 1, content.get(i).getCng_Person(),
						contentWCF));
				ws.addCell(new Label(7, i + 1, content.get(i).getCng_Problem(),
						contentWCF));
				ws.addCell(new Label(7, i + 1, content.get(i).getCng_Require(),
						contentWCF));
				ws.addCell(new Label(7, i + 1, content.get(i).getCng_Result(),
						contentWCF));
				ws.addCell(new Label(7, i + 1, content.get(i).getCng_Works(),
						contentWCF));
				ws.addCell(new Label(7, i + 1, content.get(i).getCsummarize(),
						contentWCF));
				ws.addCell(new Label(7, i + 1, content.get(i).getCoperator(),
						contentWCF));
				ws.addCell(new Label(7, i + 1, content.get(i).getCdate()
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
		HRPerson loginUser = (HRPerson) ServletActionContext.getRequest()
				.getSession().getAttribute("user");
		ProjectGroup group = projectGroupService.find(loginUser
				.getProjectGroup().getId());
		StringBuffer where = new StringBuffer();
		List<Object> parameters = new ArrayList<Object>();
		where
				.append(" o.project.iVerify=?1 and o.project.cState=?2 and o.project.projectGroup=?3 ");
		parameters.add(Iverfy.SAVE);
		parameters.add(Status.NEGO);
		System.out.println(group.getcGroupName());
		parameters.add(group);
		List<Pro_Negotiate> content = negotiateService.getScrollData(-1, -1,
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
		HRPerson loginUser = (HRPerson) ServletActionContext.getRequest()
				.getSession().getAttribute("user");
		ProjectGroup group = projectGroupService.find(loginUser
				.getProjectGroup().getId());
		StringBuffer where = new StringBuffer();
		List<Object> parameters = new ArrayList<Object>();
		where
				.append(" ((o.project.iVerify=?4 or o.project.iVerify=?1 or o.project.iVerify=?5)and o.project.cState=?2 and o.project.projectGroup=?3) or o.project.cState>?2");
		parameters.add(Iverfy.SUBMIT);
		parameters.add(Status.NEGO);
		parameters.add(group);
		parameters.add(Iverfy.REFUSE);
		parameters.add(Iverfy.PASS);
		List<Pro_Negotiate> content = negotiateService.getScrollData(-1, -1,
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
