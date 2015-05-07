package merchants.entity.canvassGroup;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import merchants.entity.projectInformation.ProjectInfo;

@Entity
public class Pro_Commun implements Serializable{
	/**
	 * 初步研判信息表
	 */
	private static final long serialVersionUID = 2693186929814539543L;

	@Id @GeneratedValue
	private Integer id;
	
	
	
	/*小组id号*/
	private Integer igroupID;
	
	/*小组外其他人员*/
	private String cpersonnel; 
	
	/*研判信息结论*/
	private String cinformation;
	
	/*操作人员*/
	private String coperator;
	
	/*状态*/
	private Integer istatus;
	
	/*招商计划*/
	private String cplan;
	
	  
	/*提交日期*/
	private Date cdate = new Date();
	/*研判日期*/
	private Date mdate ;
	/*恰谈是否结束*/
	private Boolean iend =false;
	
	/*项目编码*/
	@OneToOne()
	@JoinColumn(name="project_id")
	private ProjectInfo project;
	
	
	public ProjectInfo getProject() {
		return project;
	}
	public void setProject(ProjectInfo project) {
		this.project = project;
	}
	@Column(length=4)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	@Column(length=4)
	public Integer getIgroupID() {
		return igroupID;
	}

	public void setIgroupID(Integer igroupID) {
		this.igroupID = igroupID;
	}
	@Column(length=200)
	public String getCpersonnel() {
		return cpersonnel;
	}

	public void setCpersonnel(String cpersonnel) {
		this.cpersonnel = cpersonnel;
	}

	public String getCinformation() {
		return cinformation;
	}

	public void setCinformation(String cinformation) {
		this.cinformation = cinformation;
	}
	@Column(length=20)
	public String getCoperator() {
		return coperator;
	}

	public void setCoperator(String coperator) {
		this.coperator = coperator;
	}

	public Integer getIstatus() {
		return istatus;
	}

	public void setIstatus(Integer istatus) {
		this.istatus = istatus;
	}

	public String getCplan() {
		return cplan;
	}

	public void setCplan(String cplan) {
		this.cplan = cplan;
	}
	
	@Temporal(TemporalType.DATE)
	public Date getCdate() {
		return cdate;
	}
	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}
	@Temporal(TemporalType.DATE)
	public Date getMdate() {
		return mdate;
	}
	public void setMdate(Date mdate) {
		this.mdate = mdate;
	}
	@Column
	@org.hibernate.annotations.Type(type="yes_no")
	public Boolean getIend() {
		return iend;
	}
	public void setIend(Boolean iend) {
		this.iend = iend;
	}
	


}
