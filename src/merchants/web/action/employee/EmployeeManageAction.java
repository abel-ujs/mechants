package merchants.web.action.employee;

import java.util.List;

import javax.annotation.Resource;

import merchants.entity.PageView;
import merchants.entity.QueryResult;
import merchants.entity.canvassGroup.HRPerson;
import merchants.entity.privilege.PrivilegeGroup;
import merchants.service.inter.canvassGroup.HRPersonService;
import merchants.service.inter.privilege.PrivilegeGroupService;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

/**
 * 员工管理
 */
@Controller
@Scope("prototype")
public class EmployeeManageAction {
	@Resource
	HRPersonService employeeService;
	@Resource
	PrivilegeGroupService privilegeGroupService;
	private HRPerson employee;
	/* 当前页 */
	private Integer page = 1;
	/** 用户所在权限组id **/
	private String[] groupids;

	public String[] getGroupids() {
		return groupids;
	}

	public void setGroupids(String[] groupids) {
		this.groupids = groupids;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public HRPerson getEmployee() {
		return employee;
	}

	public void setEmployee(HRPerson employee) {
		this.employee = employee;
	}

	/*
	 * 员工列表
	 */

	public String list() {
		// PageView<HRPerson> pageView = new PageView<HRPerson>(10, page);
		// int firstindex =
		// (pageView.getCurrentpage()-1)*pageView.getMaxresult();
		//		
		// QueryResult<HRPerson> qr = null;
		// qr = employeeService.getScrollData(firstindex,
		// pageView.getMaxresult());
		// pageView.setRecords(qr.getResultlist());
		// ActionContext.getContext().put("pageView", pageView);
		// return "list";
		PageView<HRPerson> pageView = new PageView<HRPerson>(10, page);
		int firstindex = (pageView.getCurrentpage() - 1)
				* pageView.getMaxresult();
		QueryResult<HRPerson> queryResult = employeeService.getScrollData(
				firstindex, 10, null, null);
		pageView.setRecords(queryResult.getResultlist());
		pageView.setTotalrecords((int) queryResult.getTotalrecord());
		ActionContext.getContext().put("pageView", pageView);
		return "list";
	}

	/**
	 * 设置员工股权限组界面
	 */
	public String privilegeGroupSet() {
		// 查出所有权限组
		List<PrivilegeGroup> groups = privilegeGroupService.getScrollData()
				.getResultlist();
		ActionContext.getContext().put("groups", groups);

		// 查出员工信息
		employee = employeeService.find(employee.getId());
		return "privilegeSet";
	}

	/*
	 * 更新员工权限
	 */
	public String updateEmployeePrivilege() {

		// 获取员工信息
		employee = employeeService.find(employee.getId());
		if (employee != null) {
			employee.getGroups().clear();// 清空原来的权限组
			List<PrivilegeGroup> groups = privilegeGroupService
					.getGroupByGroupids(groupids);
			if (groups != null) {
				for (PrivilegeGroup group : groups) {
					employee.getGroups().add(group);
				}
			}

			employeeService.update(employee);
			ActionContext.getContext().put("message", "员工权限设置成功");
			ActionContext.getContext().put("urladdress",
					"/control/employee/list");
		} else {
			ActionContext.getContext().put("message", "没有该员工");
			ActionContext.getContext().put("urladdress",
					"/control/employee/list");
		}

		return "message";
	}

}
