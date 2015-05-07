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
public class Pro_Settled implements Serializable {

	/**
	 * 项目落户信息表
	 */
	private static final long serialVersionUID = 2318037288009026970L;
	@Id @GeneratedValue
	private Integer id;
	/*办理事项*/
	private String chandleSubstance;
	/*办理人员*/
	private String chandlePerson;
	/*办理时间*/
	private Date chandleDate;
	/*结果*/
	private String cfruit;
	/*落户状态*/
	private Boolean cstate;
	/*提交时间*/
	private Date ddate = new Date();
	/*登记人*/
	private String coperator ;
	
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
	
	
	@Column(length=200)
	public String getChandleSubstance() {
		return chandleSubstance;
	}
	
	public void setChandleSubstance(String chandleSubstance) {
		this.chandleSubstance = chandleSubstance;
	}
	
	@Column(length=20)
	public String getChandlePerson() {
		return chandlePerson;
	}
	
	public void setChandlePerson(String chandlePerson) {
		this.chandlePerson = chandlePerson;
	}
	@Temporal(TemporalType.DATE)
	@Column(length=8)
	public Date getChandleDate() {
		return chandleDate;
	}
	
	public void setChandleDate(Date chandleDate) {
		this.chandleDate = chandleDate;
	}
	
	public String getCfruit() {
		return cfruit;
	}
	
	public void setCfruit(String cfruit) {
		this.cfruit = cfruit;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(length=8)
	public Date getDdate() {
		return ddate;
	}
	
	@Column(length=50)
	public Boolean getCstate() {
		return cstate;
	}
	
	public void setCstate(Boolean cstate) {
		this.cstate = cstate;
	}
	
	public void setDdate(Date ddate) {
		this.ddate = ddate;
	}
	@Column(length=50)
	public String getCoperator() {
		return coperator;
	}
	public void setCoperator(String coperator) {
		this.coperator = coperator;
	}
}
