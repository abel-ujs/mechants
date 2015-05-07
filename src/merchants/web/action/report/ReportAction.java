package merchants.web.action.report;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.NoResultException;
import merchants.entity.PageView;
import merchants.entity.QueryResult;
import merchants.entity.projectInformation.ProjectInfo;
import merchants.service.inter.canvassGroup.NegotiateService;
import merchants.service.inter.canvassResource.IndustryService;
import merchants.service.inter.projectInformation.ProjectService;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class ReportAction {
	@Resource
	ProjectService projectService;
	@Resource
	NegotiateService negotiateService;
	@Resource
	IndustryService industryService;

	public Integer page = 1;
	
	private final Integer pageCount = 10;

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public String groupCategory() throws IOException {
		return "category";
	}

	public String groupIndustry() {
		return "industry";
	}

	public String groupScale() {
		return "scale";
	}

	public String groupSource() {
		return "source";
	}

	public String groupState() {
		return "state";
	}

	public String getList() {
		String key = ServletActionContext.getRequest().getParameter("key");
		int type = Integer.parseInt(ServletActionContext.getRequest()
				.getParameter("type"));
		String tp = ServletActionContext.getRequest().getParameter("page");
		ActionContext.getContext().put("page", tp);
		ActionContext.getContext().put("key", key);
		switch (type) {
		case 1:
			return "categoryList";
		case 2:
			return "industryList";
		case 3:
			return "scaleList";
		case 4:
			return "sourceList";
		case 5:
			return "stateList";
		default:
			return null;
		}
	}

	public String getCategoryList() throws IOException {
		StringBuffer where = new StringBuffer();
		List<Object> parameters = new ArrayList<Object>();
		int category;
		String cCategory = (String) ServletActionContext.getRequest()
				.getParameter("key");
		String tp = ServletActionContext.getRequest().getParameter("page");
		if(!tp.isEmpty()) this.setPage(Integer.parseInt(tp));
		System.out.println(cCategory);
		if (cCategory.equals("平台类项目"))
			category = 0;
		else if (cCategory.equals("产业性项目"))
			category = 1;
		else
			category = 2;
		where.append("o.cCategory=?");
		parameters.add(category);
		PageView<ProjectInfo> pageView = new PageView<ProjectInfo>(pageCount, page);
		int firstindex = (pageView.getCurrentpage() - 1)
				* pageView.getMaxresult();
		QueryResult<ProjectInfo> queryResult = projectService.getScrollData(
				firstindex, pageCount, where.toString(), parameters.toArray());
		pageView.setRecords(queryResult.getResultlist());
		pageView.setTotalrecords((int) queryResult.getTotalrecord());
		ActionContext.getContext().put("pageView", pageView);
		return "projectList";
	}

	public String getIndustryList(){
		StringBuffer where = new StringBuffer();
		List<Object> parameters = new ArrayList<Object>();
		String industry = (String) ServletActionContext.getRequest()
				.getParameter("key");
		String tp = ServletActionContext.getRequest().getParameter("page");
		if(!tp.isEmpty()) this.setPage(Integer.parseInt(tp));
		try{
		PageView<ProjectInfo> pageView = new PageView<ProjectInfo>(pageCount, page);
		where.append("o.industry.cInduName=?");
		parameters.add(industry);
		int firstindex = (pageView.getCurrentpage() - 1)
				* pageView.getMaxresult();
		QueryResult<ProjectInfo> queryResult = projectService.getScrollData(
				firstindex, pageCount, where.toString(), parameters.toArray());
		System.out.println(queryResult.getResultlist().size());
		pageView.setRecords(queryResult.getResultlist());
		pageView.setTotalrecords((int) queryResult.getTotalrecord());
		ActionContext.getContext().put("pageView", pageView);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return "projectList";
	}

	public String getSourceList() {
		StringBuffer where = new StringBuffer();
		String cSource = (String) ServletActionContext.getRequest()
				.getParameter("key");
		String tp = ServletActionContext.getRequest().getParameter("page");
		if(!tp.isEmpty()) this.setPage(Integer.parseInt(tp));
		try{
		if (cSource.equals("个人"))
			where.append("o.personal is not null");
		else
			where.append("o.personal is null");
		PageView<ProjectInfo> pageView = new PageView<ProjectInfo>(pageCount, page);
		int firstindex = (pageView.getCurrentpage() - 1)
				* pageView.getMaxresult();
		QueryResult<ProjectInfo> queryResult = projectService.getScrollData(
				firstindex, pageCount, where.toString(),null);
		System.out.println(queryResult.getResultlist().size());
		pageView.setRecords(queryResult.getResultlist());
		pageView.setTotalrecords((int) queryResult.getTotalrecord());
		ActionContext.getContext().put("pageView", pageView);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "projectList";
	}

	public String getScaleList() {
		StringBuffer where = new StringBuffer();
		String scale = (String) ServletActionContext.getRequest().getParameter(
				"key");
		String tp = ServletActionContext.getRequest().getParameter("page");
		if(!tp.isEmpty()) this.setPage(Integer.parseInt(tp));
		if (scale.equals("低于十万"))
			where.append("o.dInvestment < 100000");
		else if (scale.equals("十万至一百万"))
			where.append("o.dInvestment>=100000 and o.dInvestment<1000000");
		else if (scale.equals("一百万至一千万"))
			where.append("o.dInvestment>=1000000 and o.dInvestment<10000000");
		else
			where.append("o.dInvestment>=10000000");
		PageView<ProjectInfo> pageView = new PageView<ProjectInfo>(pageCount, page);
		int firstindex = (pageView.getCurrentpage() - 1)
				* pageView.getMaxresult();
		QueryResult<ProjectInfo> queryResult = projectService.getScrollData(
				firstindex, pageCount, where.toString(), null);
		pageView.setRecords(queryResult.getResultlist());
		pageView.setTotalrecords((int) queryResult.getTotalrecord());
		ActionContext.getContext().put("pageView", pageView);
		return "projectList";
	}

	public String getStateList() {
		StringBuffer where = new StringBuffer();
		List<Object> parameters = new ArrayList<Object>();
		String cState = (String) ServletActionContext.getRequest()
				.getParameter("key");
		String tp = ServletActionContext.getRequest().getParameter("page");
		if(!tp.isEmpty()) this.setPage(Integer.parseInt(tp));
		int state;
		/*
		 * 项目状态 1-意向项目；2-在谈项目；3-在谈结束;4-签约项目；5-落户项目；6-达产达效项目
		 * 7-增资项目；0-结束项目；-1-不引进项目
		 */
		if (cState.equals("意向项目"))
			state = 1;
		else if (cState.equals("研判项目"))
			state = 2;
		else if (cState.equals("洽谈项目"))
			state = 3;
		else if (cState.equals("签约项目"))
			state = 4;
		else if (cState.equals("落户项目"))
			state = 5;
		else if (cState.equals("达效项目"))
			state = 6;
		else if (cState.equals("增资项目"))
			state = 7;
		else if (cState.equals("结束项目"))
			state = 0;
		else
			state = -1;
		PageView<ProjectInfo> pageView = new PageView<ProjectInfo>(pageCount, page);
		where.append("o.cState=?");
		parameters.add(state);
		int firstindex = (pageView.getCurrentpage() - 1)
				* pageView.getMaxresult();
		QueryResult<ProjectInfo> queryResult = projectService.getScrollData(
				firstindex, pageCount, where.toString(), parameters.toArray());
		pageView.setRecords(queryResult.getResultlist());
		pageView.setTotalrecords((int) queryResult.getTotalrecord());
		ActionContext.getContext().put("pageView", pageView);
		return "projectList";
	}

	public String finalReport() {
		String code = ServletActionContext.getRequest().getParameter("pCode");
		System.out.println("此次查询的项目ID为" + code);
		try {
			ProjectInfo project = projectService.getProjectBycProCode(code);
			String source = project.getCustomer() != null ? project
					.getCustomer().getcCusName() : project.getPersonal()
					.getcPersonName();
			project.setcSource(source);
			if (project.getcState() > 2||project.getcState() ==0) {
				String nego = negotiateService.getProNegotiate(project.getId());
				System.out.println(nego);
				ActionContext.getContext().put("negotiate", nego);
			}
			ActionContext.getContext().put("project", project);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "final";
	}
}
