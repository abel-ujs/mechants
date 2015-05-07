package merchants.web.interceptor;


import java.lang.reflect.Method;
import org.apache.struts2.ServletActionContext;

import merchants.entity.canvassGroup.HRPerson;
import merchants.entity.privilege.PrivilegeGroup;
import merchants.entity.privilege.SystemPrivilege;
import merchants.entity.privilege.SystemPrivilegePK;
import merchants.utils.WebTool;
import merchants.web.annotation.Permission;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;


/**
 * 权限拦截器 
 */
public class PermissionInterceptor implements Interceptor{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3494660318048245851L;

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void init() {
		// TODO Auto-generated method stub
		
	}

	public String intercept(ActionInvocation invocation) throws Exception {
		String methodName = invocation.getProxy().getMethod();
		Method currentMethod = invocation.getAction().getClass().getMethod(methodName);
		
		if(currentMethod != null){
			if(currentMethod.isAnnotationPresent(Permission.class)){
				//如果存在权限注解
				HRPerson employee = WebTool.getEmployee(ServletActionContext.getRequest());
				if(employee == null){
					//如果没有登录
					ActionContext.getContext().put("message", "您还没有登录");
					ActionContext.getContext().put("urladdress", "/canvassGroup/front/hRPerson_loginUI");
					return "message";
				}
				if(hasPrivilegeToExec(currentMethod)){
					//如果有权限，则放行
					return invocation.invoke();
				}else{
					//如果没有权限
					ActionContext.getContext().put("message", "您没有权限执行该操作");
					ActionContext.getContext().put("urladdress", "/canvassGroup/front/hRPerson_loginUI");
					return "message";
				}
				
			}else{
				//没有权限注解，也放行
				return invocation.invoke();
			}
		}
		return null;

	}

	private boolean hasPrivilegeToExec(Method method){
		//如果用权限注解，则获取该注解
		Permission annotation = method.getAnnotation(Permission.class);
		//获取权限模块
		String module = annotation.module();
		/*获取权限值*/
		String privilege = annotation.privilege();
		SystemPrivilege systemPrivilege = new SystemPrivilege(new SystemPrivilegePK(module,privilege));
		HRPerson employee = WebTool.getEmployee(ServletActionContext.getRequest());
		if(employee == null){
			return false;
		}
		boolean result = false;//员工是否有权限
		for(PrivilegeGroup group : employee.getGroups()){
			if(group.getPrivileges().contains(systemPrivilege)){
				result = true;
				break;
			}
		}
		return result;
	}

	

}
