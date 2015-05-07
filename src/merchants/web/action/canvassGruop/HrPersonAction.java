package merchants.web.action.canvassGruop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import merchants.entity.PageView;
import merchants.entity.QueryResult;
import merchants.entity.canvassGroup.Department;
import merchants.entity.canvassGroup.HRPerson;
import merchants.entity.canvassResource.Personal;
import merchants.service.inter.canvassGroup.DepartmentService;
import merchants.service.inter.canvassGroup.HRPersonService;
import merchants.utils.CheckCode;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

/**
 * 用户操作action
 */
@Controller
@Scope("prototype")
public class HrPersonAction {
	@Resource
	HRPersonService userService;
	@Resource
	DepartmentService departmentService;
	/* 用户实体 */
	private HRPerson systemUser;
	private String checkCode;
	private Integer pageCount = 10;
	/* 当前页码 */
	public Integer page = 1;

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public HRPerson getSystemUser() {
		return systemUser;
	}

	public void setSystemUser(HRPerson systemUser) {
		this.systemUser = systemUser;
	}

	public String getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}

	/**
	 * 跳转到登陆界面
	 * 
	 * @return
	 */
	public String loginUI() {
		ActionContext.getContext().put("currentArea", "登陆");
		return "loginUI";
	}

	/**
	 * 用户登录验证
	 */
	public String check() {
		/* 默认调转地址 */
		ActionContext.getContext().put("urladdress",
				"/canvassGroup/front/hRPerson_loginUI");
		ActionContext.getContext().put("message", "用户名或密码错误");
		/* 验证验证码 */
		// 获取session中的验证字符串
		String sessionCheckCode = ((String) ServletActionContext.getRequest()
				.getSession().getAttribute(CheckCode.RANDOMCODEKEY))
				.toUpperCase();
		if (!sessionCheckCode.equals(this.checkCode.toUpperCase())) {
			ActionContext.getContext().put("message", "验证码错误");
			ActionContext.getContext().put("urladdress",
					"/canvassGroup/front/hRPerson_loginUI");
			return "message";
		}
		if (!"".equals(this.systemUser.getcUserName().trim())) {
			System.out.println("用户名，密码" + systemUser.getcUserName() + ","
					+ systemUser.getcPassword());
			HRPerson loginUser = userService.find(systemUser.getcUserName(),
					systemUser.getcPassword());
			if (loginUser != null) {
				// 如果查询到的用户不为空，则将登陆用户的信息存入session中
				ServletActionContext.getRequest().getSession().setAttribute(
						"user", loginUser);
				System.out.println("验证成功");
				return "loginSucceed";

			}
		} else {
			System.out.println("失败");
			ActionContext.getContext().put("message", "登录名或密码错误");
			ActionContext.getContext().put("urladdress",
					"/canvassGroup/front/hRPerson_loginUI");
		}
		return "message";

	}

	public void generateCheckCode() {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("image/jpeg");// 设置相应类型,告诉浏览器输出的内容为图片
		response.setHeader("Pragma", "No-cache");// 设置响应头信息，告诉浏览器不要缓存此内容
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expire", 0);
		CheckCode checkCode = new CheckCode();
		try {
			checkCode.getRandcode(ServletActionContext.getRequest(), response);
		} catch (IOException e) {
			e.printStackTrace();
		}// 输出图片方法

	}

	/**
	 * 注销登录
	 * 
	 * @return
	 */
	public String logout() {
		ServletActionContext.getRequest().getSession().removeAttribute("user");
		ActionContext.getContext().put("message", "用户注销成功");
		ActionContext.getContext().put("urladdress",
				"/canvassGroup/front/hRPerson_loginUI");
		return "message";
	}

	/**
	 *导航到用户自己的用户信息修改界面
	 */
	public String updateUI() {
		Integer id = ((HRPerson) ServletActionContext.getRequest().getSession()
				.getAttribute("user")).getId();
		HRPerson person = userService.find(id);
		ActionContext.getContext().put("systemUser", person);
		return "updateUI";
	}

	/**
	 * 用户信息修改
	 */
	public String update() {
		HRPerson newSystemUser = userService.find(systemUser.getId());
		newSystemUser.setcUserName(systemUser.getcUserName());
		// 密码有点麻烦
		String password1 = ServletActionContext.getRequest().getParameter(
				"password1").trim();
		String password2 = ServletActionContext.getRequest().getParameter(
				"password2").trim();
		if (!password1.equals(password2)) {
			ActionContext.getContext().put("message", "两次输入密码不一致，请重新输入");
			ActionContext.getContext().put("urladdress",
					"/canvassGroup/front/hRPerson_updateUI");
			return "message";
		}
		if (userService.find(systemUser.getcUserName(), systemUser
				.getcPassword()) == null) {
			ActionContext.getContext().put("message", "旧密码输入有误，请重新输入");
			ActionContext.getContext().put("urladdress",
					"/canvassGroup/front/hRPerson_updateUI");
			return "message";
		}
		if (systemUser.getcPsnMobilePhone() == null
				|| "".equals(systemUser.getcPsnMobilePhone())) {
			ActionContext.getContext().put("message", "手机号码不能为空，请重新输入");
			ActionContext.getContext().put("urladdress",
					"/canvassGroup/front/hRPerson_updateUI");
			return "message";
		}
		if (systemUser.getvIDNo() == null || "".equals(systemUser.getvIDNo())) {
			ActionContext.getContext().put("message", "身份证号码号码不能为空，请重新输入");
			ActionContext.getContext().put("urladdress",
					"/canvassGroup/front/hRPerson_updateUI");
			return "message";
		}
		newSystemUser.setrSex(systemUser.getrSex());
		newSystemUser.setdBirthday(systemUser.getdBirthday());
		newSystemUser.setcPsnMobilePhone(systemUser.getcPsnMobilePhone());
		newSystemUser.setrPerResidence(systemUser.getrPerResidence());
		newSystemUser.setvIDNo(systemUser.getvIDNo());
		newSystemUser.setrNational(systemUser.getrNational());
		newSystemUser.setcPsnPostAddr(systemUser.getcPsnPostAddr());
		newSystemUser.setcPassword(password2);
		userService.update(newSystemUser);
		ActionContext.getContext().put("message", "用户信息修改成功");
		ActionContext.getContext().put("urladdress", "/control/admin/main");
		return "message";
	}

	/**
	 * 检查招商人员即用户密码是否输入正确
	 */
	public void checkcCusCode() {
		String ajaxMessage = null;
		if (userService.find(systemUser.getcUserName(), systemUser
				.getcPassword()) != null) {
			// 如果该用户在在即密码输入正确
			ajaxMessage = "success";
		} else {
			ajaxMessage = "fail";
		}
		try {
			ServletActionContext.getResponse().getWriter().write(ajaxMessage);
		} catch (IOException e) {
			System.out.println("Ajax密码认证异常");
			e.printStackTrace();
		}

	}

	/**
	 * 员工信息显示列表
	 */
	public String list() {
		PageView<HRPerson> pageView = new PageView<HRPerson>(pageCount, page);
		int firstindex = (pageView.getCurrentpage() - 1)
				* pageView.getMaxresult();
		QueryResult<HRPerson> queryResult = userService.getScrollData(
				firstindex, pageCount, null, null);
		pageView.setRecords(queryResult.getResultlist());
		pageView.setTotalrecords((int) queryResult.getTotalrecord());
		ActionContext.getContext().put("pageView", pageView);
		return "list";
	}

	/**
	 * 根据id得到详细信息
	 */
	public String getUserInfo() {
		Integer id = Integer.parseInt(ServletActionContext.getRequest()
				.getParameter("id"));
		HRPerson p = userService.find(id);
		ActionContext.getContext().put("entity", p);
		return "userInfo";
	}

	/**
	 * 转到员工录入页面
	 */
	public String inputUI() {
		List<Department> departments = departmentService.getScrollData()
				.getResultlist();
		ActionContext.getContext().put("departments", departments);
		return "inputUI";
	}

	/**
	 * 保存
	 */
	public String save() {
		Integer departmentId = Integer.parseInt(ServletActionContext
				.getRequest().getParameter("departmentId"));
		systemUser.setDepartment(departmentService.find(departmentId));
		// 用户初始化密码123456
		systemUser.setcPassword("123456");
		try {
			userService.save(systemUser);
			ActionContext.getContext().put("message", "用户信息保存成功");
		} catch (Exception e) {
			ActionContext.getContext().put("message", "姓名或用户名不唯一，用户信息保存失败");
			System.out.println(e.getStackTrace());
		}
		ActionContext.getContext().put("urladdress",
				"/canvassGroup/front/hRPerson_list");
		return "message";
	}

	/**
	 * 删除
	 */
	public String delete() {
		Integer id = Integer.parseInt(ServletActionContext.getRequest()
				.getParameter("id"));
		userService.delete(id);
		ActionContext.getContext().put("message", "用户删除成功");
		ActionContext.getContext().put("urladdress",
				"/canvassGroup/front/hRPerson_list");
		return "message";
	}

	/*
	 * 改变用户是否启用与停用的状态
	 */

	public String changeState() {
		Integer id = Integer.parseInt(ServletActionContext.getRequest()
				.getParameter("id"));
		String type = ServletActionContext.getRequest().getParameter("type");
		HRPerson newUser = userService.find(id);
		if (type.equals("on")) {
			newUser.setiValid(true);
			ActionContext.getContext().put("message", "用户启用成功");

		} else if (type.equals("off")) {
			newUser.setiValid(false);
			ActionContext.getContext().put("message", "用户停用成功");

		}
		userService.update(newUser);
		ActionContext.getContext().put("urladdress",
				"/canvassGroup/front/hRPerson_list");
		return "message";
	}

	/**
	 *导航到管理员重置用户密码界面
	 */
	public String adminUpdateUI() {
		Integer id = Integer.parseInt(ServletActionContext.getRequest()
				.getParameter("id"));
		HRPerson person = userService.find(id);
		ActionContext.getContext().put("systemUser", person);
		return "resetPasswordUI";
	}

	/*
	 * 管理员重置用户密码
	 */

	public String resetPassword() {

		HRPerson newUser = userService.find(systemUser.getId());
		newUser.setcPassword(systemUser.getcPassword());
		ActionContext.getContext().put("message", "用户密码重置成功");
		userService.update(newUser);
		ActionContext.getContext().put("urladdress",
				"/canvassGroup/front/hRPerson_list");
		return "message";
	}
}
