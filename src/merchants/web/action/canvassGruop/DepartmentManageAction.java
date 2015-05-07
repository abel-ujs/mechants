package merchants.web.action.canvassGruop;

import java.util.Date;

import javax.annotation.Resource;

import merchants.entity.PageView;
import merchants.entity.QueryResult;
import merchants.entity.canvassGroup.Department;
import merchants.entity.canvassGroup.HRPerson;
import merchants.entity.canvassResource.Investor;
import merchants.service.inter.canvassGroup.DepartmentService;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

/**
 * 部门管理
 */
@Controller
@Scope("prototype")
public class DepartmentManageAction {
	@Resource
	DepartmentService departmentService;
	private Department department;
	/* 当前页 */
	private Integer page = 1;

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	/*
	 * 员工列表
	 */

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String list() {

		PageView<Department> pageView = new PageView<Department>(10, page);
		int firstindex = (pageView.getCurrentpage() - 1)
				* pageView.getMaxresult();
		QueryResult<Department> queryResult = departmentService.getScrollData(
				firstindex, 10, null, null);
		pageView.setRecords(queryResult.getResultlist());
		pageView.setTotalrecords((int) queryResult.getTotalrecord());
		ActionContext.getContext().put("pageView", pageView);
		return "list";
	}

	/**
	 * 转到部门录入页面
	 */
	public String inputUI() {
		return "inputUI";
	}

	
	 /**
	 * 保存
	 */
	 public String save() {
	 System.out.println("department==null:" + department == null);
	 try {
	 department.setdOrganizeDate(new Date());
	 departmentService.save(department);
	 ActionContext.getContext().put("message", "部门信息保存成功");
	 } catch (Exception e) {
	 ActionContext.getContext().put("message", "部门名称不能为空且必须唯一，部门信息保存失败");
	 System.out.println(e.getStackTrace());
	 }
	 ActionContext.getContext()
	 .put("urladdress", "/control/department/list");
	 return "message";
	 }
	 
	/**
	 *导航到部门信息修改界面
	 */
	public String updateUI() {
		Integer id = Integer.parseInt(ServletActionContext.getRequest()
				.getParameter("id"));
		Department d = departmentService.find(id);
		ActionContext.getContext().put("department", d);
		return "updateUI";
	}

	/**
	 * 修改投资方信息
	 */

	public String update() {
		Integer id = Integer.parseInt(ServletActionContext.getRequest()
				.getParameter("id"));
		Department newDeparment = departmentService.find(id);
		newDeparment.setcDeptName(department.getcDeptName());
		newDeparment.setdOrganizeDate(department.getdOrganizeDate());
		newDeparment.setVrCharger(department.getVrCharger());
		newDeparment.setVrTel(department.getVrTel());
		departmentService.update(newDeparment);
		ActionContext.getContext().put("message", "部门信息修改成功");
		ActionContext.getContext()
				.put("urladdress", "/control/department/list");
		return "message";
	}

	/**
	 * 删除介绍单位
	 */
	public String delete() {
		Integer id = Integer.parseInt(ServletActionContext.getRequest()
				.getParameter("id"));
		try {
			departmentService.delete(id);
			ActionContext.getContext().put("message", "部门删除成功");
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
			System.out.println("有外键约束，删除异常");
			ActionContext.getContext().put("message", "有外键约束,部门删除失败");
		}

		ActionContext.getContext()
				.put("urladdress", "/control/department/list");
		return "message";
	}
}
