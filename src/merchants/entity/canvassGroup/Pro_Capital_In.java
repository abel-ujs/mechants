package merchants.entity.canvassGroup;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import merchants.entity.projectInformation.ProjectInfo;

@Entity
public class Pro_Capital_In implements Serializable {

	/**
	 * 增资扩股项目表
	 */
	private static final long serialVersionUID = -6601245249814031925L;
	
	
	private Integer id;
	
	
	/*时间*/
	private Date dinDate;
	/*单位*/
	private String cunits;
	/*资金规模*/
	private Integer dscale;
	/*需求*/
	private String cdemand;
	/*原因*/
	private String creason;
	/*操作员*/
	private String coperator;
	/*操作日期*/
	private Date ddate = new Date();
	/*申请人*/
	private String capplicant;
	 
	/*项目编码*/
	
	private ProjectInfo project;
	
	@OneToOne()
	@JoinColumn(name="project_id")
	public ProjectInfo getProject() {
		return project;
	}
	public void setProject(ProjectInfo project) {
		this.project = project;
	}
	@Id @GeneratedValue
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Temporal(TemporalType.DATE)
	public Date getDinDate() {
		return dinDate;
	}
	public void setDinDate(Date dinDate) {
		this.dinDate = dinDate;
	}
	public String getCunits() {
		return cunits;
	}
	public void setCunits(String cunits) {
		this.cunits = cunits;
	}
	public String getCdemand() {
		return cdemand;
	}
	public void setCdemand(String cdemand) {
		this.cdemand = cdemand;
	}
	public String getCreason() {
		return creason;
	}
	public void setCreason(String creason) {
		this.creason = creason;
	}
	public String getCoperator() {
		return coperator;
	}
	public void setCoperator(String coperator) {
		this.coperator = coperator;
	}
	
	@Temporal(TemporalType.DATE)
	public Date getDdate() {
		return ddate;
	}
	public void setDdate(Date ddate) {
		this.ddate = ddate;
	}
	public String getCapplicant() {
		return capplicant;
	}
	public void setCapplicant(String capplicant) {
		this.capplicant = capplicant;
	}
	public Integer getDscale() {
		return dscale;
	}
	public void setDscale(Integer dscale) {
		this.dscale = dscale;
	}
	 
}
