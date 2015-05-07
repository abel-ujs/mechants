package merchants.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import merchants.entity.privilege.SystemPrivilege;
import merchants.service.inter.privilege.SystemPrivilegeService;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;


@Controller
@Scope("prototype")
public class SystemPrivilegeInitAction {
	
	@Resource SystemPrivilegeService systemPrivilegeServcie;
	
	public String init(){
		if(systemPrivilegeServcie.getCount() == 0){
			//如果未初始化
			List<SystemPrivilege> list = new ArrayList<SystemPrivilege>();
//			list.add(new SystemPrivilege("personnal", "inputUI", "介绍人录入"));
//			list.add(new SystemPrivilege("personnal", "researchUI", "介绍人查询"));
//			list.add(new SystemPrivilege("personnal", "displayList", "介绍人编辑"));
			
//			list.add(new SystemPrivilege("customer", "inputUI", "介绍单位录入"));
//			list.add(new SystemPrivilege("customer", "researchUI", "介绍单位查询"));
//			list.add(new SystemPrivilege("customer", "displayList", "介绍单位编辑"));
			
			list.add(new SystemPrivilege("canvassResource", "menu", "招商资源管理菜单显示"));
			
			
//			list.add(new SystemPrivilege("project", "inputUI", "意向项目录入"));
//			list.add(new SystemPrivilege("project", "researchUI", "意向项目查询"));
//			list.add(new SystemPrivilege("project", "displayUISave", "已保存意向项目"));
//			list.add(new SystemPrivilege("project", "displayUISubmit", "已提交意向项目"));
//			list.add(new SystemPrivilege("project", "getProjectListByState", "领导审核"));
//			list.add(new SystemPrivilege("project", "projectEnd", "报表查看"));
			
			list.add(new SystemPrivilege("project", "menu1", "意向项目管理菜单显示"));
			list.add(new SystemPrivilege("project", "menu2", "领导审核菜单显示"));
			list.add(new SystemPrivilege("project", "menu3", "报表查看菜单显示"));
			
//			list.add(new SystemPrivilege("commun", "index", "研判录入"));
//			list.add(new SystemPrivilege("commun", "researchIndex", "研判查询"));
//			list.add(new SystemPrivilege("commun", "displaySaveCommun", "已保存研判项目"));
//			list.add(new SystemPrivilege("commun", "displaySubmitCommun", "已提交研判项目"));
			
			list.add(new SystemPrivilege("commun", "menu", "研判项目管理菜单显示"));
			
//			list.add(new SystemPrivilege("negotiate", "index", "在谈项目录入"));
//			list.add(new SystemPrivilege("negotiate", "researchIndex", "在谈项目查询"));
//			list.add(new SystemPrivilege("negotiate", "displaySaveNegotiate", "已保存在谈项目"));
//			list.add(new SystemPrivilege("negotiate", "displaySubmitNegotiate", "已提交在谈项目"));
			
			list.add(new SystemPrivilege("negotiate", "menu", "在谈项目管理菜单显示"));
			
//			list.add(new SystemPrivilege("sign", "index", "签约录入"));
//			list.add(new SystemPrivilege("sign", "researchIndex", "签约查询"));
//			list.add(new SystemPrivilege("sign", "displaySaveSign", "已保存签约项目"));
//			list.add(new SystemPrivilege("sign", "displaySubmitSign", "已提交签约项目"));
			
			list.add(new SystemPrivilege("sign", "menu", "签约项目管理菜单显示"));
			
//			list.add(new SystemPrivilege("settled", "index", "落户录入"));
//			list.add(new SystemPrivilege("settled", "researchIndex", "落户查询"));
//			list.add(new SystemPrivilege("settled", "displaySaveSettled", "已保存落户项目"));
//			list.add(new SystemPrivilege("settled", "displaySubmitSettled", "已提交落户项目"));
			
			list.add(new SystemPrivilege("settled", "menu", "落户项目管理菜单显示"));
			
//			list.add(new SystemPrivilege("outcome", "index", "达产达效录入"));
//			list.add(new SystemPrivilege("outcome", "researchIndex", "达产达效查询"));
//			list.add(new SystemPrivilege("outcome", "displaySaveOutcome", "已保存达产达效项目"));
//			list.add(new SystemPrivilege("outcome", "displaySubmitOutcome", "已提交达产达效项目"));
			
			list.add(new SystemPrivilege("outcome", "menu", "达产达效项目管理菜单显示"));
			
//			list.add(new SystemPrivilege("capital", "index", "增资扩股录入"));
//			list.add(new SystemPrivilege("capital", "researchIndex", "增资扩股查询"));
//			list.add(new SystemPrivilege("capital", "displaySaveCapital", "已保存增资扩股项目"));
//			list.add(new SystemPrivilege("capital", "displaySubmitCapital", "已提交增资扩股项目"));
			
			list.add(new SystemPrivilege("capital", "menu", "增资扩股项目管理菜单显示"));
			
//			list.add(new SystemPrivilege("report", "groupCategory", "按类别统计"));
//			list.add(new SystemPrivilege("report", "groupIndustry", "按行业统计"));
//			list.add(new SystemPrivilege("report", "groupState", "按状态统计"));
//			list.add(new SystemPrivilege("report", "groupSource", "按来源统计"));
//			list.add(new SystemPrivilege("report", "groupScale", "按规模统计"));
			
			list.add(new SystemPrivilege("report", "menu", "分类统计菜单显示"));
			list.add(new SystemPrivilege("console", "menu", "后台管理菜单显示"));
			
			
			//批量插入
			systemPrivilegeServcie.batchSave(list);
			ActionContext.getContext().put("message", "系统初始化成功");
			ActionContext.getContext().put("urladdress", "/");
			return "message";
		}
		ActionContext.getContext().put("message", "系统已经初始化，请不要重复操作");
		ActionContext.getContext().put("urladdress", "/");
		return "message";
	}
}
