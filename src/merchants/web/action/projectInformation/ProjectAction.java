package merchants.web.action.projectInformation;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
import merchants.entity.canvassGroup.HRPerson;
import merchants.entity.canvassGroup.Outcome;
import merchants.entity.canvassGroup.Pro_Capital_In;
import merchants.entity.canvassGroup.Pro_Commun;
import merchants.entity.canvassGroup.Pro_Negotiate;
import merchants.entity.canvassGroup.Pro_Settled;
import merchants.entity.canvassGroup.Pro_Signing;
import merchants.entity.canvassGroup.ProjectGroup;
import merchants.entity.canvassResource.Customer;
import merchants.entity.canvassResource.Industry;
import merchants.entity.canvassResource.Investor;
import merchants.entity.canvassResource.Personal;
import merchants.entity.projectInformation.ProjectInfo;
import merchants.service.inter.canvassGroup.CanvassGroupService;
import merchants.service.inter.canvassGroup.CapitalService;
import merchants.service.inter.canvassGroup.CommunService;
import merchants.service.inter.canvassGroup.HRPersonService;
import merchants.service.inter.canvassGroup.NegotiateService;
import merchants.service.inter.canvassGroup.OutcomeService;
import merchants.service.inter.canvassGroup.SettledService;
import merchants.service.inter.canvassGroup.SignService;
import merchants.service.inter.canvassResource.CustomerService;
import merchants.service.inter.canvassResource.IndustryService;
import merchants.service.inter.canvassResource.InvestorService;
import merchants.service.inter.canvassResource.PersonalService;
import merchants.service.inter.projectInformation.ProjectService;
import merchants.utils.DateJsonValueProcessor;
import merchants.utils.EnumMap;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

/**
 * 处理意向 项目信息有关请求
 */
@Controller
@Scope("prototype")
public class ProjectAction {
	private JSONObject resultObj;
	@Resource
	ProjectService projectService;
	@Resource
	HRPersonService hRPersonService;
	@Resource
	CanvassGroupService canvassGroupService;
	@Resource
	InvestorService investorService;
	@Resource
	PersonalService personalService;
	@Resource
	CustomerService customerService;
	@Resource
	IndustryService industryService;
	@Resource
	CommunService communService;
	@Resource
	NegotiateService negotiateService;
	@Resource
	SignService signService;
	@Resource
	SettledService settledService;
	@Resource
	CapitalService capitalService;
	@Resource
	OutcomeService outcomeService;
	// 用来接收项目来源类型 personal:customer
	private String type;
	// 用来接收项目来源类id
	private String id;
	private ProjectInfo project;
	private Investor investor;
	private ProjectGroup projectGroup;
	private Industry industry;

	private List<Map> records = new ArrayList<Map>();
	// 用于接收查询参数并用于回显以完成翻页请求
	private Integer total;
	public Integer rows = 10;
	private String searchKey;
	private String searchValue;
	/* 当前页码 */
	public Integer page = 1;

	private Integer pageCount = 10;
	/* 接收操作类型 */
	private String operation;

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public ProjectInfo getProject() {
		return project;
	}

	public void setProject(ProjectInfo project) {
		this.project = project;
	}

	public String getType() {
		return type;
	}

	public JSONObject getResultObj() {
		return resultObj;
	}

	public void setResultObj(JSONObject resultObj) {
		this.resultObj = resultObj;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Investor getInvestor() {
		return investor;
	}

	public void setInvestor(Investor investor) {
		this.investor = investor;
	}

	public ProjectGroup getProjectGroup() {
		return projectGroup;
	}

	public void setProjectGroup(ProjectGroup projectGroup) {
		this.projectGroup = projectGroup;
	}

	public Industry getIndustry() {
		return industry;
	}

	public void setIndustry(Industry industry) {
		this.industry = industry;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List<Map> getRecords() {
		return records;
	}

	public void setRecords(List<Map> records) {
		this.records = records;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	/**
	 * 转到已保存意向项目功能界面
	 */
	public String displaySaveUI() {
		return "displaySaveUI";
	}

	/**
	 * 为了给已保存意向项目功能界面准备分页数据
	 */
	public void displaySave() {
		StringBuffer where = new StringBuffer();
		List<Object> parameters = new ArrayList<Object>();
		if (searchValue != null && !"".equals(searchValue) && searchKey != null
				&& !"".equals(searchKey)) {
			where.append("  o.cProName" + " like ?" + (parameters.size() + 1));
			parameters.add("%" + searchValue + "%");
			where.append(" or o.cProCode=?" + (parameters.size() + 1));
			parameters.add(searchValue);
		}
		Map<String, Object> map = new HashMap<String, Object>();

		List<ProjectInfo> oldProjects = projectService.getScrollData(-1, -1,
				where.toString(), parameters.toArray()).getResultlist();
		List<ProjectInfo> newProjects = new ArrayList<ProjectInfo>();
		for (ProjectInfo p : oldProjects) {
			if (p.getcState() == 1 && p.getiVerify() == 0) {
				newProjects.add(p);
			}
		}
		// 注意此时的分页不是靠数据库实现的
		List<ProjectInfo> pageProjects = new ArrayList<ProjectInfo>();
		if (newProjects.size() > 0) {
			this.total = newProjects.size();
			int lastindex = (page - 1) * rows + rows > newProjects.size() ? newProjects
					.size()
					: (page - 1) * rows + rows;
			for (int i = (page - 1) * rows; i < lastindex; i++) {
				pageProjects.add(newProjects.get(i));
			}
		}

		for (int i = 0; i < pageProjects.size(); i++) {
			ProjectInfo p = pageProjects.get(i);
			Map<String, Object> m = new HashMap<String, Object>();

			m.put("id", p.getId());
			m.put("cProCode", p.getcProCode());
			m.put("cProName", p.getcProName());
			if (p.getPersonal() != null) {
				m.put("cSource", p.getPersonal().getcPersonName());
			} else {
				m.put("cSource", p.getCustomer().getcCusName());
			}
			m.put("industry", p.getIndustry().getcInduName());
			m.put("investor", p.getInvestor().getcInvName());
			m.put("projectGroup", p.getProjectGroup().getcGroupName());
			switch (p.getiObjective()) {
			case 0:
				m.put("objective", "扩大产能");
				break;
			case 1:
				m.put("objective", "企业搬迁");
				break;
			case 2:
				m.put("objective", "企开发新产品");
				break;
			case 3:
				m.put("objective", "创业");
				break;
			}
			// 项目类别（0：平台类项目 1：产业性项目 2：现代服务业项目
			switch (p.getcCategory()) {
			case 0:
				m.put("cCategory", "平台类项目");
				break;
			case 1:
				m.put("cCategory", "产业性项目");
				break;
			case 2:
				m.put("cCategory", "现代服务业项目");
				break;
			}
			m.put("dInvestment", p.getdInvestment());
			m.put("cMainBuessiness", p.getcMainBuessiness());
			m.put("cBusinessModel", p.getcBusinessModel());
			m.put("cPromotionPlan", p.getcPromotionPlan());
			m.put("cMainProducts", p.getcMainProducts());
			m.put("cMarketingInfo", p.getcMarketingInfo());
			m.put("cOperator", p.getcOperator());
			switch (p.getiVerify()) {
			case 0:
				m.put("iVerify", "保存未审核");
				break;
			case 1:
				m.put("iVerify", "审核通过");
				break;
			case 2:
				m.put("iVerify", "审核不通过");
				break;
			case 3:
				m.put("iVerify", "提交未审核");
				break;
			}

			this.records.add(m);
		}

		map.put("total", this.total);
		map.put("rows", this.records);
		resultObj = JSONObject.fromObject(map);
		try {
			ServletActionContext.getResponse().setCharacterEncoding("utf-8");
			ServletActionContext.getResponse().getWriter().print(resultObj);
			ServletActionContext.getResponse().flushBuffer();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 转到提交意向项目功能界面
	 */
	public String displaySubmitUI() {
		return "displaySubmitUI";
	}

	/**
	 * 为了给提交意向项目功能界面准备分页数据
	 */
	public void displaySubmit() {
		StringBuffer where = new StringBuffer();
		List<Object> parameters = new ArrayList<Object>();

		if (searchValue != null && !"".equals(searchValue) && searchKey != null
				&& !"".equals(searchKey)) {
			where.append("  o.cProName" + " like ?" + (parameters.size() + 1));
			parameters.add("%" + searchValue + "%");
			where.append(" or o.cProCode=?" + (parameters.size() + 1));
			parameters.add(searchValue);
		}
		Map<String, Object> map = new HashMap<String, Object>();

		List<ProjectInfo> oldProjects = projectService.getScrollData(-1, -1,
				where.toString(), parameters.toArray()).getResultlist();
		List<ProjectInfo> newProjects = new ArrayList<ProjectInfo>();
		for (ProjectInfo p : oldProjects) {
			if (p.getcState() == 1
					&& (p.getiVerify() == 2 || p.getiVerify() == 3)) {
				newProjects.add(p);
			}
		}
		System.out.println("rows=" + rows + ",page=" + page);
		// 注意此时的分页不是靠数据库实现的
		List<ProjectInfo> pageProjects = new ArrayList<ProjectInfo>();
		if (newProjects.size() > 0) {
			this.total = newProjects.size();
			int lastindex = (page - 1) * rows + rows > newProjects.size() ? newProjects
					.size()
					: (page - 1) * rows + rows;
			for (int i = (page - 1) * rows; i < lastindex; i++) {
				pageProjects.add(newProjects.get(i));
			}
		}
		for (int i = 0; i < pageProjects.size(); i++) {
			ProjectInfo p = pageProjects.get(i);
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("id", p.getId());
			m.put("cProCode", p.getcProCode());
			m.put("cProName", p.getcProName());
			if (p.getPersonal() != null) {
				m.put("cSource", p.getPersonal().getcPersonName());
			} else {
				m.put("cSource", p.getCustomer().getcCusName());
			}
			m.put("industry", p.getIndustry().getcInduName());
			m.put("investor", p.getInvestor().getcInvName());
			m.put("projectGroup", p.getProjectGroup().getcGroupName());
			switch (p.getiObjective()) {
			case 0:
				m.put("objective", "扩大产能");
				break;
			case 1:
				m.put("objective", "企业搬迁");
				break;
			case 2:
				m.put("objective", "企开发新产品");
				break;
			case 3:
				m.put("objective", "创业");
				break;
			}
			// 项目类别（0：平台类项目 1：产业性项目 2：现代服务业项目
			switch (p.getcCategory()) {
			case 0:
				m.put("cCategory", "平台类项目");
				break;
			case 1:
				m.put("cCategory", "产业性项目");
				break;
			case 2:
				m.put("cCategory", "现代服务业项目");
				break;
			}
			m.put("dInvestment", p.getdInvestment());
			m.put("cMainBuessiness", p.getcMainBuessiness());
			m.put("cBusinessModel", p.getcBusinessModel());
			m.put("cPromotionPlan", p.getcPromotionPlan());
			m.put("cMainProducts", p.getcMainProducts());
			m.put("cMarketingInfo", p.getcMarketingInfo());
			m.put("cOperator", p.getcOperator());
			switch (p.getiVerify()) {
			case 0:
				m.put("iVerify", "保存未审核");
				break;
			case 1:
				m.put("iVerify", "审核通过");
				break;
			case 2:
				m.put("iVerify", "审核不通过");
				break;
			case 3:
				m.put("iVerify", "提交未审核");
				break;
			}

			this.records.add(m);
		}

		map.put("total", this.total);
		map.put("rows", this.records);
		resultObj = JSONObject.fromObject(map);
		try {
			ServletActionContext.getResponse().setCharacterEncoding("utf-8");
			ServletActionContext.getResponse().getWriter().print(resultObj);
			ServletActionContext.getResponse().flushBuffer();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * 跳转选择项目来源界面 :将项目来源数据准备给项目来源选择界面
	 */
	public String selectProjectSourceUI() {
		String type = ServletActionContext.getRequest().getParameter("type");
		if (type.equals("personal")) {
			List<Personal> personals = personalService.getScrollData()
					.getResultlist();
			ActionContext.getContext().put("personals", personals);

		} else if (type.equals("customer")) {

			List<Customer> customers = customerService.getScrollData()
					.getResultlist();
			ActionContext.getContext().put("customers", customers);
		}
		ActionContext.getContext().put("type", type);
		return "selectProjectSourceUI";
	}

	/**
	 * 处理 保存意向项目信息
	 */
	public void save() {
		Map<String, String> map = new HashMap<String, String>();
		if (type == null || "".equals(type)) {
			map.put("errorMsg", "未选择项目来源");
			try {
				ServletActionContext.getResponse().setCharacterEncoding("gbk");
				ServletActionContext.getResponse().getWriter().print(
						JSONObject.fromObject(map));
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;

		}
		if (type.equals("personal")) {
			project.setPersonal(personalService.find(Integer.parseInt(id)));
			project.setcSource("personal");

		} else if (type.equals("customer")) {
			project.setCustomer(customerService.find(Integer.parseInt(id)));
			project.setcSource("customer");
		}
		project.setInvestor(investorService.find(investor.getId()));
		project.setIndustry(industryService.find(industry.getId()));
		HRPerson user = (HRPerson) ServletActionContext.getRequest()
				.getSession().getAttribute("user");
		project.setProjectGroup(user.getProjectGroup());
		project.setcOperator(user.getcPsnName());
		try {
			projectService.save(project);

		} catch (Exception e) {
			System.out.println(e.getStackTrace());
			System.out.println("插入意向项目信息违反项目编码或者项目名称唯一约束");

			map.put("errorMsg", "项目编码或者项目名称不唯一");
			try {
				ServletActionContext.getResponse().setCharacterEncoding("gbk");
				ServletActionContext.getResponse().getWriter().print(
						JSONObject.fromObject(map));
			} catch (IOException ee) {
				ee.printStackTrace();
			}
			return;

		}
		map.put("errorMsg", "");
		try {
			ServletActionContext.getResponse().setCharacterEncoding("gbk");
			ServletActionContext.getResponse().getWriter().print(
					JSONObject.fromObject(map));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return;
	}

	/**
	 * 处理 编辑界面的保存意向项目信息
	 */
	public void editSave() {
		System.out.println("edit save start");
		Map<String, String> map = new HashMap<String, String>();
		ProjectInfo p = projectService
				.find(Integer.parseInt(ServletActionContext.getRequest()
						.getParameter("id")));
		p.setiVerify(Integer.parseInt(ServletActionContext.getRequest()
				.getParameter("iVerify")));

		p.setcMainBuessiness(ServletActionContext.getRequest().getParameter(
				"cMainBuessiness"));
		p.setcBusinessModel(ServletActionContext.getRequest().getParameter(
				"cBusinessModel"));
		p.setcPromotionPlan(ServletActionContext.getRequest().getParameter(
				"cPromotionPlan"));
		p.setcMainProducts(ServletActionContext.getRequest().getParameter(
				"cMainProducts"));
		p.setcMarketingInfo(ServletActionContext.getRequest().getParameter(
				"cMarketingInfo"));
		try {
			p.setiObjective(Integer.parseInt(ServletActionContext.getRequest()
					.getParameter("iObjective")));
			p.setcCategory(Integer.parseInt(ServletActionContext.getRequest()
					.getParameter("cCategory")));
			p.setdInvestment(Double.parseDouble(ServletActionContext
					.getRequest().getParameter("dInvestment")));
			p.setInvestor(investorService.find(Integer
					.parseInt(ServletActionContext.getRequest().getParameter(
							"investor"))));
			p.setIndustry(industryService.find(Integer
					.parseInt(ServletActionContext.getRequest().getParameter(
							"industry"))));
			projectService.update(p);

		} catch (Exception e) {
			System.out.println(e.getStackTrace());
			System.out.println("更新意向项目信息异常");
			map.put("errorMsg", "更新意向项目信息异常");
			try {
				ServletActionContext.getResponse().setCharacterEncoding("gbk");
				ServletActionContext.getResponse().getWriter().print(
						JSONObject.fromObject(map));
			} catch (IOException ee) {
				ee.printStackTrace();
			}
			return;

		}
		map.put("errorMsg", "");
		try {
			ServletActionContext.getResponse().setCharacterEncoding("gbk");
			ServletActionContext.getResponse().getWriter().print(
					JSONObject.fromObject(map));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return;
	}

	/**
	 * 加载集合数据
	 */
	public void loadInvestorData() {
		StringBuffer sb = new StringBuffer("[");
		List<Investor> investors = investorService.getScrollData()
				.getResultlist();
		for (Investor investor : investors) {
			sb.append("{\"id\":" + investor.getId());
			sb.append(",\"text\":" + "\"" + investor.getcInvName() + "\"},");
		}
		sb.deleteCharAt(sb.lastIndexOf(","));
		sb.append("]");
		try {
			ServletActionContext.getResponse().setCharacterEncoding("utf-8");
			ServletActionContext.getResponse().getWriter().print(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 加载集合数据
	 */
	public void loadIndustryData() {
		StringBuffer sb = new StringBuffer("[");
		List<Industry> industries = industryService.getScrollData()
				.getResultlist();
		for (Industry industry : industries) {
			sb.append("{\"id\":" + industry.getId());
			sb.append(",\"text\":" + "\"" + industry.getcInduName() + "\"},");
		}
		sb.deleteCharAt(sb.lastIndexOf(","));
		sb.append("]");
		try {
			ServletActionContext.getResponse().setCharacterEncoding("utf-8");
			ServletActionContext.getResponse().getWriter().print(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// CaiSong

	/**
	 * @return 参看项目报表列表
	 */
	public String projectEnd() {
		String where = "o.cState<>-1";
		PageView<ProjectInfo> pageView = new PageView<ProjectInfo>(pageCount,
				page);
		int firstindex = (pageView.getCurrentpage() - 1)
				* pageView.getMaxresult();
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("id", "desc");
		QueryResult<ProjectInfo> queryResult = projectService.getScrollData(
				firstindex, pageCount, where.toString(), null, orderby);
		pageView.setRecords(queryResult.getResultlist());
		pageView.setTotalrecords((int) queryResult.getTotalrecord());
		ActionContext.getContext().put("pageView", pageView);
		return "projectiEndList";
	}

	/**
	 * 转到参看项目报表列表功能界面
	 */
	public String projectEndNew() {
		return "projectiEndListNew";
	}

	/**
	 * @return 参看项目报表列表
	 */
	public void projectEndNewData() {
		StringBuffer where = new StringBuffer();
		List<Object> parameters = new ArrayList<Object>();

		if (searchValue != null && !"".equals(searchValue) && searchKey != null
				&& !"".equals(searchKey)) {
			where.append("  o.cProName" + " like ?" + (parameters.size() + 1));
			parameters.add("%" + searchValue + "%");
			where.append(" or o.cProCode=?" + (parameters.size() + 1));
			parameters.add(searchValue);
		}
		Map<String, Object> map = new HashMap<String, Object>();

		List<ProjectInfo> oldProjects = projectService.getScrollData(-1, -1,
				where.toString(), parameters.toArray()).getResultlist();
		List<ProjectInfo> newProjects = new ArrayList<ProjectInfo>();
		for (ProjectInfo p : oldProjects) {
			if (p.getcState() != -1) {
				newProjects.add(p);
			}
		}
		// 注意此时的分页不是靠数据库实现的
		List<ProjectInfo> pageProjects = new ArrayList<ProjectInfo>();
		if (newProjects.size() > 0) {
			this.total = newProjects.size();
			int lastindex = (page - 1) * rows + rows > newProjects.size() ? newProjects
					.size()
					: (page - 1) * rows + rows;
			for (int i = (page - 1) * rows; i < lastindex; i++) {
				pageProjects.add(newProjects.get(i));
			}
		}
		for (int i = 0; i < pageProjects.size(); i++) {
			ProjectInfo p = pageProjects.get(i);
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("id", p.getId());
			m.put("cProCode", p.getcProCode());
			m.put("cProName", p.getcProName());
			if (p.getPersonal() != null) {
				m.put("cSource", p.getPersonal().getcPersonName());
			} else {
				m.put("cSource", p.getCustomer().getcCusName());
			}
			m.put("industry", p.getIndustry().getcInduName());
			m.put("investor", p.getInvestor().getcInvName());
			m.put("projectGroup", p.getProjectGroup().getcGroupName());
			switch (p.getiObjective()) {
			case 0:
				m.put("objective", "扩大产能");
				break;
			case 1:
				m.put("objective", "企业搬迁");
				break;
			case 2:
				m.put("objective", "企开发新产品");
				break;
			case 3:
				m.put("objective", "创业");
				break;
			}
			// 项目类别（0：平台类项目 1：产业性项目 2：现代服务业项目
			switch (p.getcCategory()) {
			case 0:
				m.put("cCategory", "平台类项目");
				break;
			case 1:
				m.put("cCategory", "产业性项目");
				break;
			case 2:
				m.put("cCategory", "现代服务业项目");
				break;
			}
			m.put("dInvestment", p.getdInvestment());
			m.put("cMainBuessiness", p.getcMainBuessiness());
			m.put("cBusinessModel", p.getcBusinessModel());
			m.put("cPromotionPlan", p.getcPromotionPlan());
			m.put("cMainProducts", p.getcMainProducts());
			m.put("cMarketingInfo", p.getcMarketingInfo());
			m.put("cOperator", p.getcOperator());
			switch (p.getiVerify()) {
			case 0:
				m.put("iVerify", "保存未审核");
				break;
			case 1:
				m.put("iVerify", "审核通过");
				break;
			case 2:
				m.put("iVerify", "审核不通过");
				break;
			case 3:
				m.put("iVerify", "提交未审核");
				break;
			}

			this.records.add(m);
		}

		map.put("total", this.total);
		map.put("rows", this.records);
		resultObj = JSONObject.fromObject(map);
		try {
			// 注意编码问题，否则前端无法获取数据
			ServletActionContext.getResponse().setCharacterEncoding("utf-8");
			ServletActionContext.getResponse().getWriter().print(resultObj);
			ServletActionContext.getResponse().flushBuffer();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// CaiSong
	//返回审核视图
	public String intentionCheck()
	{
		return "intentionCheck";
	}
	public String communCheck()
	{
		return "communCheck";
	}
	public String negotiateCheck()
	{
		return "negotiateCheck";
	}
	public String signCheck()
	{
		return "signCheck";
	}
	public String settledCheck()
	{
		return "settledCheck";
	}
	public String outcomeCheck()
	{
		return "outcomeCheck";
	}
	public String capitalCheck()
	{
		return "capitalCheck";
	}
	
	public String getProjectInfo() {
		Integer id = Integer.parseInt(ServletActionContext.getRequest()
				.getParameter("id"));
		ProjectInfo p = projectService.find(id);
		ActionContext.getContext().put("project", p);
		return "projectInfo";
	}
	@SuppressWarnings("unchecked")
	public void getProjectListByState() throws IOException {
		// 设置分页
		Integer page = Integer.valueOf(ServletActionContext.getRequest()
				.getParameter("page"));
		Integer rows = Integer.valueOf(ServletActionContext.getRequest()
				.getParameter("rows"));
		/*
		 * if (page == null || rows == null) { page = 1; rows = 10; }
		 */
		if (page == null || rows == null)
			throw new IOException();
		Integer total = 0;
		Map map = new HashMap();
		ArrayList jsonList = new ArrayList();
		// JSON过滤
		JsonConfig config = new JsonConfig();
		config.setJsonPropertyFilter(new PropertyFilter() {
			public boolean apply(Object source, String name, Object value) {
				if (name.equals("project")) {
					return true;
				} else {
					return false;
				}
			}
		});
		config.registerJsonValueProcessor(java.util.Date.class,
				new DateJsonValueProcessor());

		StringBuffer where = new StringBuffer();
		where.append("o.project.iVerify=?1 and o.project.cState=?2");
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("project.id", "desc");
		List<Object> parameters = new ArrayList<Object>();
		parameters.add(3);
		String cState = ServletActionContext.getRequest()
				.getParameter("cState");
		if (cState.isEmpty())
			System.out.println("The state is null");
		int state = Integer.parseInt(cState);
		System.out.println("the cState is " + state);
		parameters.add(state);
		//返回研判项目json
		if (state == 2) {
			String searchKey = ServletActionContext.getRequest().getParameter("searchKey");
			String searchValue = ServletActionContext.getRequest().getParameter("searchValue");
			System.out.println("serach--》"+searchKey+searchValue);
			if(searchKey != null && searchValue != null){
				where.append("  and  (o.project.cProName" + " like ?" + (parameters.size() + 1));
				parameters.add("%" + searchValue + "%");
				where.append(" or o.project.cProCode=?" + (parameters.size() + 1)+")");
				parameters.add(searchValue);
			}
			System.out.println("communList");
			PageView<Pro_Commun> pageView = new PageView<Pro_Commun>(rows, page);
			int firstindex = (pageView.getCurrentpage() - 1)
					* pageView.getMaxresult();
			QueryResult<Pro_Commun> queryResult = communService.getScrollData(
					firstindex, rows, where.toString(), parameters.toArray(),
					orderby);
			total = (int) queryResult.getTotalrecord();
			System.out.println("the total is" + total);
			List<Pro_Commun> list = queryResult.getResultlist();
			for (Pro_Commun p : list) {
				JSONObject json = JSONObject.fromObject(p, config);
				ProjectInfo project = p.getProject();
				json.put("cProCode", project.getcProCode());
				json.put("cProName", project.getcProName());
				jsonList.add(json.toString());
			}
		}
		//返回签约项目json
		else if (state == 4) {
			String searchKey = ServletActionContext.getRequest().getParameter("searchKey");
			String searchValue = ServletActionContext.getRequest().getParameter("searchValue");
			System.out.println("serach--》"+searchKey+searchValue);
			if(searchKey != null && searchValue != null){
				where.append("  and  (o.project.cProName" + " like ?" + (parameters.size() + 1));
				parameters.add("%" + searchValue + "%");
				where.append(" or o.project.cProCode=?" + (parameters.size() + 1)+")");
				parameters.add(searchValue);
			}
			System.out.println("signList");
			PageView<Pro_Signing> pageView = new PageView<Pro_Signing>(rows,
					page);
			int firstindex = (pageView.getCurrentpage() - 1)
					* pageView.getMaxresult();
			QueryResult<Pro_Signing> queryResult = signService.getScrollData(
					firstindex, rows, where.toString(), parameters.toArray(),
					orderby);
			total = (int) queryResult.getTotalrecord();
			System.out.println("该查询数据记录共有:" + total);
			List<Pro_Signing> list = queryResult.getResultlist();

			for (Pro_Signing p : list) {
				JSONObject json = JSONObject.fromObject(p, config);
				ProjectInfo project = p.getProject();
				json.put("cProCode", project.getcProCode());
				json.put("cProName", project.getcProName());
				jsonList.add(json.toString());
			}
		}
		//返回落户项目json
		else if (state == 5) {
			String searchKey = ServletActionContext.getRequest().getParameter("searchKey");
			String searchValue = ServletActionContext.getRequest().getParameter("searchValue");
			System.out.println("serach--》"+searchKey+searchValue);
			if(searchKey != null && searchValue != null){
				where.append("  and  (o.project.cProName" + " like ?" + (parameters.size() + 1));
				parameters.add("%" + searchValue + "%");
				where.append(" or o.project.cProCode=?" + (parameters.size() + 1)+")");
				parameters.add(searchValue);
			}
			System.out.println("settledList");
			PageView<Pro_Settled> pageView = new PageView<Pro_Settled>(rows,
					page);
			int firstindex = (pageView.getCurrentpage() - 1)
					* pageView.getMaxresult();
			QueryResult<Pro_Settled> queryResult = settledService
					.getScrollData(firstindex, rows, where.toString(),
							parameters.toArray(), orderby);
			total = (int) queryResult.getTotalrecord();
			List<Pro_Settled> list = queryResult.getResultlist();

			for (Pro_Settled p : list) {
				JSONObject json = JSONObject.fromObject(p, config);
				ProjectInfo project = p.getProject();
				json.put("cProCode", project.getcProCode());
				json.put("cProName", project.getcProName());
				jsonList.add(json.toString());
			}
		} 
		//返回达产达效项目json
		else if (state == 6) {
			String searchKey = ServletActionContext.getRequest().getParameter("searchKey");
			String searchValue = ServletActionContext.getRequest().getParameter("searchValue");
			System.out.println("serach--》"+searchKey+searchValue);
			if(searchKey != null && searchValue != null){
				where.append("  and  (o.project.cProName" + " like ?" + (parameters.size() + 1));
				parameters.add("%" + searchValue + "%");
				where.append(" or o.project.cProCode=?" + (parameters.size() + 1)+")");
				parameters.add(searchValue);
			}
			PageView<Outcome> pageView = new PageView<Outcome>(rows, page);
			int firstindex = (pageView.getCurrentpage() - 1)
					* pageView.getMaxresult();
			QueryResult<Outcome> queryResult = outcomeService.getScrollData(
					firstindex, rows, where.toString(), parameters.toArray(),
					orderby);
			total = (int) queryResult.getTotalrecord();

			List<Outcome> list = queryResult.getResultlist();

			for (Outcome p : list) {
				JSONObject json = JSONObject.fromObject(p, config);
				ProjectInfo project = p.getProject();
				json.put("cProCode", project.getcProCode());
				json.put("cProName", project.getcProName());
				jsonList.add(json.toString());
			}
		} 
		//返回增资扩股json
		else if (state == 7) {
			String searchKey = ServletActionContext.getRequest().getParameter("searchKey");
			String searchValue = ServletActionContext.getRequest().getParameter("searchValue");
			System.out.println("serach--》"+searchKey+searchValue);
			if(searchKey != null && searchValue != null){
				where.append("  and  (o.project.cProName" + " like ?" + (parameters.size() + 1));
				parameters.add("%" + searchValue + "%");
				where.append(" or o.project.cProCode=?" + (parameters.size() + 1)+")");
				parameters.add(searchValue);
			}
			PageView<Pro_Capital_In> pageView = new PageView<Pro_Capital_In>(
					rows, page);
			int firstindex = (pageView.getCurrentpage() - 1)
					* pageView.getMaxresult();
			QueryResult<Pro_Capital_In> queryResult = capitalService
					.getScrollData(firstindex, rows, where.toString(),
							parameters.toArray(), orderby);

			total = (int) queryResult.getTotalrecord();

			List<Pro_Capital_In> list = queryResult.getResultlist();

			for (Pro_Capital_In p : list) {
				JSONObject json = JSONObject.fromObject(p, config);
				ProjectInfo project = p.getProject();
				json.put("cProCode", project.getcProCode());
				json.put("cProName", project.getcProName());
				jsonList.add(json.toString());
			}

		} 
		
		//返回意向项目json
		else if (state == 1) {
			StringBuffer where2 = new StringBuffer();
			where2.append("o.iVerify=?1 and o.cState=?2");
			List<Object> parameters2 = new ArrayList<Object>();
//			System.out.println("intention");
//			String tempwhere = "o.iVerify=?1 and o.cState=?2";
			PageView<ProjectInfo> pageView = new PageView<ProjectInfo>(rows,
					page);
			LinkedHashMap<String, String> order = new LinkedHashMap<String, String>();
			order.put("id", "desc");
			if(searchKey != null && searchValue != null){
				where2.append("  and  (o.cProName" + " like ?" + (parameters.size() + 1));
				parameters2.add("%" + searchValue + "%");
				where2.append(" or o.cProCode=?" + (parameters.size() + 1)+")");
				parameters2.add(searchValue);
			}
			System.out.println();
			int firstindex = (pageView.getCurrentpage() - 1)
					* pageView.getMaxresult();
			QueryResult<ProjectInfo> queryResult = projectService
					.getScrollData(firstindex, rows, where2.toString(),
							parameters.toArray(), order);
			total = (int) queryResult.getTotalrecord();

			List<ProjectInfo> list = queryResult.getResultlist();

			JsonConfig pconfig = new JsonConfig();
			pconfig.registerJsonValueProcessor(java.util.Date.class,
					new DateJsonValueProcessor());

			pconfig.setJsonPropertyFilter(new PropertyFilter() {
				public boolean apply(Object source, String name, Object value) {
					if (name.equals("commun") || name.equals("negotiate")
							|| name.equals("outcome")
							|| name.equals("personal")
							|| name.equals("projectGroup")
							|| name.equals("settled") || name.equals("sign")
							|| name.equals("enclosed")
							|| name.equals("customer")
							|| name.equals("industry")
							|| name.equals("investor")
							|| name.equals("capital")) {
						return true;
					} else {
						return false;
					}
				}
			});

			for (ProjectInfo p : list) {
				JSONObject json = JSONObject.fromObject(p, pconfig);
				String source = p.getPersonal() != null ? p.getPersonal()
						.getcPersonName() : p.getCustomer().getcCusName();
				json.put("cSource", source);
				json.put("industry", p.getIndustry().getcInduName());
				json.put("investor", p.getInvestor().getcInvName());
				json.put("projectGroup", p.getProjectGroup().getcGroupName());
				json.put("cCategory",
						new EnumMap().getCategory(p.getcCategory()));
				json.put("iObjective",
						new EnumMap().getObjective(p.getiObjective()));
				jsonList.add(json.toString());
				System.out.println("jsonlist"+json.toString());
			}
		} 
		//返回洽谈项目json
		else {
			String searchKey = ServletActionContext.getRequest().getParameter("searchKey");
			String searchValue = ServletActionContext.getRequest().getParameter("searchValue");
			System.out.println("serach--》"+searchKey+searchValue);
			if(searchKey != null && searchValue != null){
				where.append("  and  (o.project.cProName" + " like ?" + (parameters.size() + 1));
				parameters.add("%" + searchValue + "%");
				where.append(" or o.project.cProCode=?" + (parameters.size() + 1)+")");
				parameters.add(searchValue);
			}
			System.out.println("negotiate");
			PageView<Pro_Negotiate> pageView = new PageView<Pro_Negotiate>(
					rows, page);
			int firstindex = (pageView.getCurrentpage() - 1)
					* pageView.getMaxresult();
			QueryResult<Pro_Negotiate> queryResult = negotiateService
					.getScrollData(firstindex, rows, where.toString(),
							parameters.toArray(), orderby);

			total = (int) queryResult.getTotalrecord();

			List<Pro_Negotiate> list = queryResult.getResultlist();

			for (Pro_Negotiate p : list) {
				JSONObject json = JSONObject.fromObject(p, config);
				ProjectInfo project = p.getProject();
				json.put("cProCode", project.getcProCode());
				json.put("cProName", project.getcProName());
				jsonList.add(json.toString());
			}
		}

		map.put("total", total);
		map.put("rows", jsonList);
		JSONObject jsonObj = JSONObject.fromObject(map);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.print(jsonObj);
		out.flush();
		out.close();
	}
	
	/**
	 * 项目审核通过JSON
	 * 
	 * @throws IOException
	 */
	public void agreeProject() throws IOException {
		String ID = ServletActionContext.getRequest().getParameter("projectid");
		System.out.println(ID);
		project = projectService.getProjectBycProCode(ID);
		Integer iverfy = Iverfy.SAVE;
		Map map = new HashMap();
		if (project == null)
			map.put("errorMsg", "操作失败！");
		else {
			int state = project.getcState();
			switch (state) {
			case 1:
			case 3:
			case 4:
			case 5:
			case 6:
				state++;
				break;
			case 2: {
				if (project.getiEnd() == 2)
					state = -1;
				else
					state++;
			}
				break;
			case 7:
				iverfy = Iverfy.PASS;
				break;
			}
			project.setiVerify(iverfy);
			project.setcState(state);
			projectService.update(project);
			map.put("success", true);
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.getWriter().print(JSONObject.fromObject(map));
		response.getWriter().flush();
		response.getWriter().close();
	}
	

	/**
	 * 不同意项目通过JSON
	 * 
	 * @throws IOException
	 */
	public void disagreeProject() throws IOException {
		System.out.println("不同意");
		String ID = ServletActionContext.getRequest().getParameter("projectid");
		String reason = ServletActionContext.getRequest()
				.getParameter("reason");
		project = projectService.getProjectBycProCode(ID);
		Map map = new HashMap();
		if (reason.isEmpty() || project == null)
			map.put("errorMsg", "操作失败！");
		else {
			if (project.getcState() == 1) {
				project.setcState(-1);
				project.setcReason(reason);
				projectService.update(project);
			} else {
				project.setiVerify(2);
				project.setcReason(reason);
				projectService.update(project);
			}
			map.put("success", true);
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");
		response.getWriter().print(JSONObject.fromObject(map));
		response.getWriter().flush();
		response.getWriter().close();
	}

	/**
	 * 删除意向的保存项目
	 */
	public void delete() {
		String ajaxMessage = "";
		Integer id = Integer.parseInt(ServletActionContext.getRequest()
				.getParameter("id"));
		try {
			projectService.delete(id);
			ajaxMessage = "success";
		} catch (Exception e) {
			ajaxMessage = "fail";
		}
		try {
			Map<String, String> map = new HashMap<String, String>();
			map.put("status", ajaxMessage);
			map.put("msg", "删除错误,违反外键约束!");
			System.out.println(JSONObject.fromObject(map).toString());
			ServletActionContext.getResponse().setCharacterEncoding("utf-8");
			ServletActionContext.getResponse().getWriter().print(
					JSONObject.fromObject(map));
		} catch (IOException e) {
			System.out.println("删除数据异常");
		}
		// return "message";
	}

	/**
	 * 检查项目编码是否在在 使用Ajax认证
	 */
	public void checkcProCode() {
		String ajaxMessage = null;
		if (projectService.checkByProCode(this.project.getcProCode())) {
			ajaxMessage = "fail";
		} else {
			ajaxMessage = "success";
		}
		try {

			ServletActionContext.getResponse().getWriter().write(ajaxMessage);
		} catch (IOException e) {
			System.out.println("Ajax项目编码认证异常");
		}

	}

	/**
	 * 检查项目名称是否在在 使用Ajax认证
	 */
	public void checkcProName() {
		String ajaxMessage = null;
		// 注意此时能过?传的参数是有中文乱码问题的
		// System.out.println("中文乱码"+ this.project.getcProName() );
		if (projectService.checkByProName(this.project.getcProName())) {
			ajaxMessage = "fail";
		} else {
			ajaxMessage = "success";
		}
		try {
			ServletActionContext.getResponse().getWriter().write(ajaxMessage);
		} catch (IOException e) {
			System.out.println("Ajax项目名称认证异常");
		}

	}
	@SuppressWarnings("unchecked")
	public void toSubmit() throws IOException{
		System.out.println("cprocode"+ServletActionContext.getRequest()
				.getParameter("cProCode"));
		String errorMsg="";
		String cProCode = ServletActionContext.getRequest()
				.getParameter("cProCode");
		ProjectInfo p = projectService.getProjectBycProCode(cProCode);
		if(projectService.editIverfy(p.getId(), Iverfy.SUBMIT));
		else errorMsg="提交失败！";
		Map map = new HashMap();
		map.put("errorMsg", errorMsg);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.getWriter().print(JSONObject.fromObject(map));
		response.getWriter().flush();
		response.getWriter().close();
	}
	/**
	 * 导出数据为XLS格式
	 * 
	 * @param fileName
	 *            文件的名称，可以设为绝对路径，也可以设为相对路径
	 * @param content
	 *            数据的内容
	 */
	public void exportExcel(String fileName, List<ProjectInfo> content) {
		WritableWorkbook wwb;
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(fileName);
			wwb = Workbook.createWorkbook(fos);
			WritableSheet ws = wwb.createSheet("投资方列表", 10); // 创建一个工作表
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
			ws.addCell(new Label(2, 0, "项目来源", wcf));
			ws.addCell(new Label(3, 0, "所属行业", wcf));
			ws.addCell(new Label(4, 0, "投资方", wcf));
			ws.addCell(new Label(5, 0, " 所属招商小组", wcf));
			ws.addCell(new Label(6, 0, "入住园区目的", wcf));
			ws.addCell(new Label(7, 0, "项目类别", wcf));
			ws.addCell(new Label(8, 0, "投资规模", wcf));
			ws.addCell(new Label(9, 0, "主要业务", wcf));
			ws.addCell(new Label(10, 0, "商业模式", wcf));
			ws.addCell(new Label(11, 0, "投资推进计划", wcf));
			ws.addCell(new Label(12, 0, "主要产品", wcf));
			ws.addCell(new Label(13, 0, "市场营销策略", wcf));
			ws.addCell(new Label(14, 0, " 建档人员", wcf));
			// 设置内容单元格样式 填充数据的内容
			WritableCellFormat contentWCF = new WritableCellFormat();
			for (int i = 0; i < content.size(); i++) {
				ws.addCell(new Label(0, i + 1, content.get(i).getcProCode(),
						contentWCF));
				ws.addCell(new Label(1, i + 1, content.get(i).getcProName(),
						contentWCF));
				if (content.get(i).getPersonal() != null) {
					ws.addCell(new Label(2, i + 1, content.get(i).getPersonal()
							.getcPersonName(), contentWCF));
				} else {
					ws.addCell(new Label(2, i + 1, content.get(i).getCustomer()
							.getcCusName(), contentWCF));
				}

				ws.addCell(new Label(3, i + 1, content.get(i).getIndustry()
						.getcInduName(), contentWCF));
				ws.addCell(new Label(4, i + 1, content.get(i).getInvestor()
						.toString(), contentWCF));
				ws.addCell(new Label(5, i + 1, content.get(i).getProjectGroup()
						.getcGroupName(), contentWCF));
				switch (content.get(i).getiObjective()) {
				case 0:
					ws.addCell(new Label(6, i + 1, "扩大产能", contentWCF));
					break;
				case 1:
					ws.addCell(new Label(6, i + 1, "企业搬迁", contentWCF));
					break;
				case 2:
					ws.addCell(new Label(6, i + 1, "企开发新产品", contentWCF));
					break;
				case 3:
					ws.addCell(new Label(6, i + 1, "创业", contentWCF));
					break;
				}
				// 项目类别（0：平台类项目 1：产业性项目 2：现代服务业项目
				switch (content.get(i).getcCategory()) {
				case 0:
					ws.addCell(new Label(7, i + 1, "平台类项目", contentWCF));
					break;
				case 1:
					ws.addCell(new Label(7, i + 1, "产业性项目", contentWCF));
					break;
				case 2:
					ws.addCell(new Label(7, i + 1, "现代服务业项目", contentWCF));
					break;
				}
				// 投资规模
				ws.addCell(new Label(8, i + 1, content.get(i).getdInvestment()
						+ "", contentWCF));
				ws.addCell(new Label(9, i + 1, content.get(i)
						.getcMainBuessiness(), contentWCF));
				ws.addCell(new Label(10, i + 1, content.get(i)
						.getcBusinessModel(), contentWCF));
				ws.addCell(new Label(11, i + 1, content.get(i)
						.getcPromotionPlan(), contentWCF));
				ws.addCell(new Label(12, i + 1, content.get(i)
						.getcMainProducts(), contentWCF));
				ws.addCell(new Label(13, i + 1, content.get(i)
						.getcMarketingInfo(), contentWCF));
				ws.addCell(new Label(14, i + 1, content.get(i).getcOperator(),
						contentWCF));
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
		List<ProjectInfo> oldProjects = projectService.getScrollData(-1, -1,
				null, null).getResultlist();
		List<ProjectInfo> content = new ArrayList<ProjectInfo>();
		for (ProjectInfo p : oldProjects) {
			if (p.getcState() == 1 && p.getiVerify() == 0) {
				content.add(p);
			}
		}
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

	public void excelSubmit() {
		Map<String, String> map = new HashMap<String, String>();
		String fileName = ServletActionContext.getRequest().getParameter(
				"fileName");
		String filePath = ServletActionContext.getServletContext().getRealPath(
				"\\excel")
				+ "\\" + fileName;
		List<ProjectInfo> oldProjects = projectService.getScrollData(-1, -1,
				null, null).getResultlist();
		List<ProjectInfo> content = new ArrayList<ProjectInfo>();
		for (ProjectInfo p : oldProjects) {
			if (p.getcState() == 1
					&& (p.getiVerify() == 2 || p.getiVerify() == 3)) {
				content.add(p);
			}
		}
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

	public void excelReport() {
		Map<String, String> map = new HashMap<String, String>();
		String fileName = ServletActionContext.getRequest().getParameter(
				"fileName");
		String filePath = ServletActionContext.getServletContext().getRealPath(
				"\\excel")
				+ "\\" + fileName;
		List<ProjectInfo> oldProjects = projectService.getScrollData(-1, -1,
				null, null).getResultlist();
		List<ProjectInfo> content = new ArrayList<ProjectInfo>();
		for (ProjectInfo p : oldProjects) {
			if (p.getcState() != 1) {
				content.add(p);
			}
		}
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