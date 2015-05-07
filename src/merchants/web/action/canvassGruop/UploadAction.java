package merchants.web.action.canvassGruop;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import merchants.entity.canvassGroup.DocType;
import merchants.entity.canvassGroup.Pro_Enclosed;
import merchants.entity.projectInformation.ProjectInfo;
import merchants.service.inter.canvassGroup.EnclosedService;
import merchants.service.inter.projectInformation.ProjectService;
import merchants.web.action.base.BaseAction;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class UploadAction extends BaseAction {
	@Resource
	private EnclosedService enclosedService;
	@Resource
	private ProjectService projectInfoService;
	private String introduction;
	private Integer status;
	private String fileSavePath;
	private Integer pnid;
	
	
	public Integer getPnid() {
		return pnid;
	}
	public void setPnid(Integer pnid) {
		this.pnid = pnid;
	}
	public String getFileSavePath() {
		return fileSavePath;
	}
	public void setFileSavePath(String fileSavePath) {
		this.fileSavePath = fileSavePath;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public String selectFile(){
		String projectId=ServletActionContext.getRequest().getParameter("projectId");
		String statustype=ServletActionContext.getRequest().getParameter("statustype");
		System.out.println("projectId-->"+projectId);
		System.out.println("statustype-->"+statustype);
		ActionContext.getContext().getSession().put("projectId",projectId);
		ActionContext.getContext().getSession().put("statustype",statustype);
		return "selectfile";
	}
	public String uploadfile(){
		String projectId = (String) ActionContext.getContext().getSession().get("projectId");
		String statustype=(String) ActionContext.getContext().getSession().get("statustype");
		ProjectInfo projectInfo = projectInfoService.find(Integer.valueOf(projectId));
		switch(Integer.valueOf(statustype)){
		case 1:this.setStatus(DocType.COMMUN); break;
		case 2:this.setStatus(DocType.NEGOTIATE);break;
		case 3:this.setStatus(DocType.SIGN);break;
		case 4:this.setStatus(DocType.SETTLED);break;
		case 5:this.setStatus(DocType.OUTCOME);break;
		case 6:this.setStatus(DocType.CAPITAL);break;
		}
		if(this.file!=null){
			Pro_Enclosed pn = this.buildEnclosed(projectInfo, this.getStatus(), introduction);
			enclosedService.save(pn);
			fileSavePath = this.generateFileSaveName(this.getFileFileName());
			this.saveFile(file, "/upload", fileSavePath);
			
			ActionContext.getContext().put("fileSavePath", fileSavePath);
			ActionContext.getContext().put("pnid", pn.getId());
			ActionContext.getContext().put("message", "保存成功！");
			ActionContext.getContext().put("filename", this.fileFileName);
			ActionContext.getContext().put("introduction", this.getIntroduction());
		}
		else ActionContext.getContext().put("message", "保存失败！");
		return "message1";
	}
	public void delUploadFile(){
		String result = "fail";
		if(pnid != null && fileSavePath != null){
			String realPath = ServletActionContext.getServletContext().getRealPath("/upload/" + fileSavePath);
			File file = new File(realPath);
			if(file.exists()){
				file.delete();
			}
			enclosedService.delete(pnid);
			result = "success";
			
		}
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.print(result);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
