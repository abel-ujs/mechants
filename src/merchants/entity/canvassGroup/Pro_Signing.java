package merchants.entity.canvassGroup;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
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
public class Pro_Signing implements Serializable {

	/**
	 * 项目签约表
	 */
	private static final long serialVersionUID = 477804203222031444L;
	@Id @GeneratedValue
	private Integer id;
	/*签约时间*/
	private Date datetime;
	/*参加人员*/
	private String dsignPerson;
	/*签约地点*/
	private String dsignAddress;
	
	/*签约附件ID*/
	private Integer enclosedId;

	/*提交日期*/
	private Date ddate = new Date();
	/*合同名称*/
	private String csignName;
	/*操作员*/
	private String coperator;
	
	/*项目编码*/
	@OneToOne(cascade=CascadeType.REFRESH)
	@JoinColumn(name="project_id")
	private ProjectInfo project;
	
	public Integer getEnclosedId() {
		return enclosedId;
	}
	public void setEnclosedId(Integer enclosedId) {
		this.enclosedId = enclosedId;
	}
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
	@Temporal(TemporalType.DATE)
	@Column(length=8)
	public Date getDatetime() {
		return datetime;
	}
	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}
	@Column(length=200)
	public String getDsignPerson() {
		return dsignPerson;
	}
	public void setDsignPerson(String dsignPerson) {
		this.dsignPerson = dsignPerson;
	}
	@Column(length=200)
	public String getDsignAddress() {
		return dsignAddress;
	}
	public void setDsignAddress(String dsignAddress) {
		this.dsignAddress = dsignAddress;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(length=8)
	public Date getDdate() {
		return ddate;
	}
	public void setDdate(Date ddate) {
		this.ddate = ddate;
	}
	@Column(nullable=false,length=50)
	public String getCsignName() {
		return csignName;
	}
	public void setCsignName(String csignName) {
		this.csignName = csignName;
	}
	@Column(length=50)
	public String getCoperator() {
		return coperator;
	}
	public void setCoperator(String coperator) {
		this.coperator = coperator;
	}
	
}
