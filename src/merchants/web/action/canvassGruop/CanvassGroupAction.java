package merchants.web.action.canvassGruop;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import merchants.entity.QueryResult;
import merchants.entity.canvassGroup.HRPerson;
import merchants.entity.canvassGroup.ProjectGroup;
import merchants.entity.projectInformation.ProjectInfo;
import merchants.service.inter.canvassGroup.CanvassGroupService;
import merchants.service.inter.canvassGroup.HRPersonService;
import merchants.service.inter.projectInformation.ProjectService;
import merchants.utils.DateJsonValueProcessor;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

/**
 * 处理招商小组有关请求
 */
@Controller
@Scope("prototype")
public class CanvassGroupAction {
	@Resource
	CanvassGroupService canvassGroupService;
	@Resource
	HRPersonService hRPersonService;
	@Resource
	ProjectService projectService;
	// 初始化 选择小组 增加成员
	private ProjectGroup group;
	private HRPerson person;
	private ProjectInfo project;

	public ProjectInfo getProject() {
		return project;
	}

	public void setProject(ProjectInfo project) {
		this.project = project;
	}

	public ProjectGroup getGroup() {
		return group;
	}

	public void setGroup(ProjectGroup group) {
		this.group = group;
	}

	public HRPerson getPerson() {
		return person;
	}

	public void setPerson(HRPerson person) {
		this.person = person;
	}
	
	
	/**
	 * 输出各小组成员
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void groupPList() throws IOException {
		List<HRPerson> pList = hRPersonService.getScrollData().getResultlist();
		System.out.println("人员个数" + pList.size());
		JsonConfig pconfig = new JsonConfig();
		Map map = new HashMap();
		ArrayList jsonList = new ArrayList();
		pconfig.registerJsonValueProcessor(java.util.Date.class,
				new DateJsonValueProcessor());
		pconfig.setJsonPropertyFilter(new PropertyFilter() {
			public boolean apply(Object source, String name, Object value) {
				if (name.equals("projectGroup") || name.equals("groups")
						|| name.equals("department")) {
					return true;
				} else {
					return false;
				}
			}
		});

		for (HRPerson p : pList) {
			JSONObject json = JSONObject.fromObject(p, pconfig);
			// json.accumulate("cSource", source);
			if (p.getProjectGroup()==null)
				json.put("cGroupeName", "未分配小组");
			else json.put("cGroupName", p.getProjectGroup().getcGroupName());
			json.put("department", p.getDepartment().getcDeptName());
			jsonList.add(json.toString());
		}

		map.put("total", pList.size());
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
	 * 输出每个招商小组拥有项目数JSON
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void groupProjectList() throws IOException {
		List<ProjectGroup> projectGroups = canvassGroupService.getScrollData()
				.getResultlist();
		int count = projectGroups.size();
		JsonConfig pconfig = new JsonConfig();
		Map map = new HashMap();
		ArrayList jsonList = new ArrayList();
		pconfig.setJsonPropertyFilter(new PropertyFilter() {
			public boolean apply(Object source, String name, Object value) {
				if (name.equals("persons") || name.equals("projects")) {
					return true;
				} else {
					return false;
				}
			}
		});

		for (ProjectGroup g : projectGroups) {
			JSONObject json = JSONObject.fromObject(g, pconfig);
			// json.accumulate("cSource", source);
			int t = 0, q = 0;
			for (ProjectInfo p : g.getProjects()) {
				t++;
				json.accumulate("cProName", p.getcProName());
			}
			for (HRPerson p : g.getPersons()) {
				q++;
				json.accumulate("cProPerson", p.getcPsnName());
			}
			json.put("pNum", t);
			json.put("ppNum", q);
			jsonList.add(json.toString());
		}

		map.put("total", count);
		map.put("rows", jsonList);
		JSONObject jsonObj = JSONObject.fromObject(map);

		System.out.println(jsonObj.toString());

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.print(jsonObj);
		out.flush();
		out.close();
	}

	/**
	 * 分配招商小组，并通过意向项目
	 * 
	 * @throws IOException
	 */
	public void assignGroup() throws IOException {
		System.out.println("Enter");
		String pid = ServletActionContext.getRequest()
				.getParameter("projectid");
		System.out.println(pid);
		String gid = ServletActionContext.getRequest().getParameter("group");
		System.out.println(gid);
		String persons = ServletActionContext.getRequest().getParameter(
				"persons");
		System.out.println(persons);
		Map map = new HashMap();
		if (pid.isEmpty() || gid.isEmpty()) {
			map.put("errorMsg", "操作失败！");
			throw new IOException();
		}
		
		project = projectService.getProjectBycProCode(pid);
		group = canvassGroupService.find(Integer.parseInt(gid));
		if (persons!=null) {
			String strp[] = persons.split(",");
			for (String p : strp) {
				System.out.println("该员工id为：" + p);
				person = this.hRPersonService.find(Integer.parseInt(p));
				person.setProjectGroup(group);
				this.hRPersonService.update(person);
			}
		}
		// 双向？
		/*
		 * if(group.getProjects().isEmpty()) project.setProjectGroup(group);
		 * else {group.getProjects().add(project);}
		 */
		project.setProjectGroup(group);
		int state = project.getcState() + 1;
		project.setcState(state);
		project.setiVerify(0);
		projectService.update(project);
		System.out.println("End,Success");
		map.put("success", true);
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.getWriter().print(JSONObject.fromObject(map));
		response.getWriter().flush();
		response.getWriter().close();
	}
	
	
	/**
	 * 转到安排招商小组界面
	 * 
	 * @return
	 */
	/*
	 * public String groupConfigUI() { List<ProjectGroup> projectGroups =
	 * canvassGroupService.getScrollData() .getResultlist(); List<HRPerson>
	 * hHPersons = hRPersonService.getScrollData() .getResultlist();
	 * StringBuffer where = new StringBuffer(); List<Object> parameters = new
	 * ArrayList<Object>(); where.append(" o.iVerify=?1"); parameters.add(0);
	 * where.append(" and o.cState=?2"); parameters.add(2); String cProCode=
	 * ServletActionContext.getRequest().getParameter("code"); project =
	 * projectService.getProjectBycProCode(cProCode);
	 * ActionContext.getContext().put("projectGroups", projectGroups);
	 * ActionContext.getContext().put("hHPersons", hHPersons);
	 * ActionContext.getContext().put("project", project); return
	 * "groupConfigUI"; }
	 */

	public String groupConfigUI() {
		List<ProjectGroup> projectGroups = canvassGroupService.getScrollData()
				.getResultlist();
		String pkey = ServletActionContext.getRequest().getParameter("project");
		project = projectService.getProjectBycProCode(pkey);
		ActionContext.getContext().put("groupList", projectGroups);
		ActionContext.getContext().put("pName", project.getcProName());
		ActionContext.getContext().put("pCode", project.getcProCode());
		return "groupConfigUI";
	}

	public String OwnProjectList() {
		String pkey = ServletActionContext.getRequest().getParameter("project");
		group = canvassGroupService.find(Integer.parseInt(pkey));
		List<ProjectInfo> projectList = group.getProjects();
		ActionContext.getContext().put("projectList", projectList);
		return "ownprojectlist";
	}

	public String GroupPersonList() {
		String gkey = ServletActionContext.getRequest().getParameter("group");
		group = canvassGroupService.find(Integer.parseInt(gkey));
		List<HRPerson> personList = group.getPersons();
		ActionContext.getContext().put("personList", personList);
		return "grouppersonlist";
	}

	public void groupSelect() throws Exception {
		String gkey = ServletActionContext.getRequest().getParameter("group");
		group = canvassGroupService.find(Integer.parseInt(gkey));
		String pkey = ServletActionContext.getRequest().getParameter("project");
		project = this.projectService.getProjectBycProCode(pkey);
		project.setProjectGroup(group);
		project.setcState(2);
		project.setiVerify(0);
		group.getProjects().add(project);
		canvassGroupService.update(group);
		ServletActionContext.getResponse().getWriter().write("ok");
	}

	public String selectPerson() throws Exception {
		StringBuffer where = new StringBuffer();
		List<Object> parameters = new ArrayList<Object>();
		where.append("o.projectGroup.id is null or o.projectGroup.id<>?1");
		String gkey = ServletActionContext.getRequest().getParameter("group");
		parameters.add(Integer.parseInt(gkey));
		QueryResult<HRPerson> queryResult = hRPersonService.getScrollData(-1,
				-1, where.toString(), parameters.toArray());
		List<HRPerson> hrpersons = queryResult.getResultlist();
		ActionContext.getContext().put("HRPersons", hrpersons);
		ActionContext.getContext().put("group", gkey);
		return "selectperson";
	}

	public void addPerson() throws Exception {
		String pkey = ServletActionContext.getRequest().getParameter("person");
		String gkey = ServletActionContext.getRequest().getParameter("group");
		System.out.println(pkey);
		System.out.println(gkey);
		try {
			person = this.hRPersonService.find(Integer.parseInt(pkey));
			group = this.canvassGroupService.find(Integer.parseInt(gkey));
			person.setProjectGroup(group);
			this.hRPersonService.update(person);
			ServletActionContext.getResponse().getWriter().write("ok");
			System.out.println("success");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * public void assignGroup() throws Exception { String gkey =
	 * ServletActionContext.getRequest().getParameter("group"); String pkey =
	 * ServletActionContext.getRequest().getParameter("project"); project =
	 * this.projectService.find(Integer.parseInt(pkey)); group =
	 * this.canvassGroupService.find(Integer.parseInt(gkey));
	 * group.getProjects().add(project);
	 * ServletActionContext.getResponse().getWriter().write("ok"); }
	 */

	/**
	 * 处理选择小组请求
	 * 
	 * }
	 * 
	 * @return
	 */
	/*
	 * public String groupSelect() { StringBuffer where = new StringBuffer();
	 * List<Object> parameters = new ArrayList<Object>();
	 * where.append(" o.iVerify=?1"); parameters.add(3);
	 * where.append(" and o.cState=?2"); parameters.add(1); String cProCode =
	 * ServletActionContext.getRequest().getParameter("code"); project =
	 * projectService.getProjectBycProCode(cProCode); List<ProjectGroup>
	 * projectGroups = canvassGroupService.getScrollData() .getResultlist(); if
	 * (group.getId() != null) { ProjectGroup projectGroup =
	 * canvassGroupService.find(group.getId()); List<HRPerson> groupPersons =
	 * projectGroup.getPersons(); ActionContext.getContext().put("groupPersons",
	 * groupPersons); } List<HRPerson> hHPersons =
	 * hRPersonService.getScrollData() .getResultlist();
	 * ActionContext.getContext().put("projectGroups", projectGroups);
	 * ActionContext.getContext().put("hHPersons", hHPersons);
	 * ActionContext.getContext().put("project", project); return
	 * "groupConfigUI"; }
	 * 
	 * 
	 * 选择产品类型界面 将顶级类别数据准备给类型选择界面
	 * 
	 * public String selectHRPersonUI() { StringBuffer where = new
	 * StringBuffer(); List<Object> parameters = new ArrayList<Object>();
	 * where.append(" o.iVerify=?1"); parameters.add(3);
	 * where.append(" and o.cState=?2"); parameters.add(1); String cProCode =
	 * ServletActionContext.getRequest().getParameter("code"); project =
	 * projectService.getProjectBycProCode(cProCode); List<HRPerson> hHPersons =
	 * hRPersonService.getScrollData() .getResultlist();
	 * ActionContext.getContext().put("hHPersons", hHPersons);
	 * ActionContext.getContext().put("project", project); return
	 * "selectHRPersonUI"; }
	 *//**
	 * 处理 添加项目小组临时人员-
	 */
	/*
	 * public String addHRPerson() { List<ProjectGroup> projectGroups =
	 * canvassGroupService.getScrollData() .getResultlist();
	 * if(group.getId()!=null){ ProjectGroup projectGroup=
	 * canvassGroupService.find(group.getId());
	 * person=hRPersonService.find(person.getId());
	 * person.setProjectGroup(projectGroup);
	 * project=projectService.find(project.getId());
	 * project.setProjectGroup(projectGroup);
	 * canvassGroupService.save(projectGroup); projectGroup=
	 * canvassGroupService.find(group.getId()); List<HRPerson> groupPersons =
	 * projectGroup.getPersons(); ActionContext.getContext().put("groupPersons",
	 * groupPersons); } List<HRPerson> hHPersons =
	 * hRPersonService.getScrollData() .getResultlist();
	 * ActionContext.getContext().put("projectGroups", projectGroups);
	 * ActionContext.getContext().put("hHPersons", hHPersons);
	 * ActionContext.getContext().put("message", "领导安排小组成功");
	 * ActionContext.getContext().put("urladdress", "/control/admin/main");
	 * return "groupConfigUI"; }
	 */
}
