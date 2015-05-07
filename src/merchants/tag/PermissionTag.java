package merchants.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import merchants.entity.canvassGroup.HRPerson;
import merchants.entity.privilege.PrivilegeGroup;
import merchants.entity.privilege.SystemPrivilege;
import merchants.entity.privilege.SystemPrivilegePK;
import merchants.utils.WebTool;

import org.apache.struts2.ServletActionContext;



public class PermissionTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1548565083333004781L;
	/*模块名*/
	private String module;
	/*权限值*/
	private String privilege;
	
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public String getPrivilege() {
		return privilege;
	}
	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}
	
	@Override
	public int doStartTag() throws JspException {
		boolean result = false;
		SystemPrivilege systemPrivilege = new SystemPrivilege(new SystemPrivilegePK(module,privilege));
		HRPerson employee = WebTool.getEmployee(ServletActionContext.getRequest());
		for(PrivilegeGroup group : employee.getGroups()){
			if(group.getPrivileges().contains(systemPrivilege)){
				result = true;
				break;
			}
				
		}
		return result ? EVAL_BODY_INCLUDE : SKIP_BODY;
	}
	
	
}
