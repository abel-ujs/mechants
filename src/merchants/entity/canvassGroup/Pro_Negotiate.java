package merchants.entity.canvassGroup;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import merchants.entity.projectInformation.ProjectInfo;

@Entity
public class Pro_Negotiate implements Serializable {

	/**
	 * 洽谈记录表
	 */
	private static final long serialVersionUID = 5393387667517314177L;
	
	@Id @GeneratedValue 
	private Integer id;
	
	
	
	/*洽谈时间*/
	private Date datetime;
	
	/*洽谈地址*/
	private String cng_Address;
	
	/*参与人员*/
	private String cng_Person;
	
	/*项目分析信息*/
	private String cng_Analysis;
	
	/*解决问题*/
	private String cng_Problem;
	
	/*工作安排*/
	private String cng_Works;
	
	/*需要领导*/
	private String cng_Leadership;
	
	/*小结*/
	private String csummarize;
	
	/*意向签约日期*/
	private Date dwillDate;
	
	/*操作员*/
	private String coperator;
	
	/*提交日期*/
	private Date cdate = new Date();
	
	/*洽谈内容*/
	private String cng_Content;
	
	/*项目方提出的要求*/
	private String cng_Require;
	
	/*项目分析结果*/
	private String cng_Result;
	
	/*项目引进建议*/
	private String cng_Advice;
	
	/*吸引企业入住因素*/
	private String cng_Element;
	
	/*项目编码*/
	@ManyToOne()
	@JoinColumn(name="project_id")
	private ProjectInfo project;
	
	
	public String getCng_Content() {
		return cng_Content;
	}
	public void setCng_Content(String cng_Content) {
		this.cng_Content = cng_Content;
	}
	public String getCng_Require() {
		return cng_Require;
	}
	public void setCng_Require(String cng_Require) {
		this.cng_Require = cng_Require;
	}
	public String getCng_Result() {
		return cng_Result;
	}
	public void setCng_Result(String cng_Result) {
		this.cng_Result = cng_Result;
	}
	public String getCng_Advice() {
		return cng_Advice;
	}
	public void setCng_Advice(String cng_Advice) {
		this.cng_Advice = cng_Advice;
	}
	public String getCng_Element() {
		return cng_Element;
	}
	public void setCng_Element(String cng_Element) {
		this.cng_Element = cng_Element;
	}
	public ProjectInfo getProject() {
		return project;
	}
	public void setProject(ProjectInfo project) {
		this.project = project;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Temporal(TemporalType.DATE)
	public Date getDatetime() {
		return datetime;
	}
	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}
	public String getCng_Address() {
		return cng_Address;
	}
	public void setCng_Address(String cng_Address) {
		this.cng_Address = cng_Address;
	}
	public String getCng_Person() {
		return cng_Person;
	}
	public void setCng_Person(String cng_Person) {
		this.cng_Person = cng_Person;
	}
	public String getCng_Analysis() {
		return cng_Analysis;
	}
	public void setCng_Analysis(String cng_Analysis) {
		this.cng_Analysis = cng_Analysis;
	}
	public String getCng_Problem() {
		return cng_Problem;
	}
	public void setCng_Problem(String cng_Problem) {
		this.cng_Problem = cng_Problem;
	}
	public String getCng_Works() {
		return cng_Works;
	}
	public void setCng_Works(String cng_Works) {
		this.cng_Works = cng_Works;
	}
	public String getCng_Leadership() {
		return cng_Leadership;
	}
	public void setCng_Leadership(String cng_Leadership) {
		this.cng_Leadership = cng_Leadership;
	}
	public String getCsummarize() {
		return csummarize;
	}
	public void setCsummarize(String csummarize) {
		this.csummarize = csummarize;
	}
	@Temporal(TemporalType.DATE)
	public Date getDwillDate() {
		return dwillDate;
	}
	public void setDwillDate(Date dwillDate) {
		this.dwillDate = dwillDate;
	}
	public String getCoperator() {
		return coperator;
	}
	public void setCoperator(String coperator) {
		this.coperator = coperator;
	}
	@Temporal(TemporalType.DATE)
	public Date getCdate() {
		return cdate;
	}
	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}

	
}
