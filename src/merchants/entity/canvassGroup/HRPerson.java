package merchants.entity.canvassGroup;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import merchants.entity.canvassResource.Gender;
import merchants.entity.privilege.PrivilegeGroup;

@Entity
// 单位人员信息
public class HRPerson implements Serializable {
	private static final long serialVersionUID = 7943458035431598147L;
	// 人员Id
	private Integer id;
	// 人员编码
	private String cPsnCode;
	// 姓名
	private String cPsnName;
	/* 用户名 */
	private String cUserName;
	/* 密码 */
	private String cPassword;
	// 曾用名
	private String vAliaName;
	// 部门
	private Department department;
	// 人员类别 ：0-正式 1-临时工 2：合同工
	private Integer rPersonType;
	// 性别
	private Gender rSex;
	// 出生日期
	private Date dBirthday;
	// 手机
	private String cPsnMobilePhone;
	// 是否业务员 y/Y-是 n/N-否
	private Boolean bPsnPerson;
	// 家庭电话
	private String cPsnFPhone;
	// 办公电话
	private String cPsnOPhone;

	// Email
	private String cPsnEmail;
	// 通讯地址
	private String cPsnPostAddr;
	// 家庭住址
	private String cPsnFAddr;
	// QQ
	private String cPsnQQCode;
	// 个人网址
	private String cPsnURL;
	// 到职日期
	private Date dEnterUnitDate;
	// 是否是试用人员 y/Y-是 n/N-否
	private Boolean bProbation=true;
	// 民族
	private String rNational;
	// 健康状况
	private String rHealthStatus;
	// 婚姻状况 0-未婚 1-已婚 2-其它
	private Integer rMarriStatus;
	// 证件号码
	private String vIDNo;
	// 户籍
	private String rPerResidence;
	// 参加工作时间
	private Date dJoinworkDate;
	// 进入本行业时间
	private Date dEnterDate;
	// 转正时间
	private Date dRegularDate;
	// 社会保障号 
	private String vSsNo;
	// 是否为组长 y/Y-是 n/N-否
	private Boolean isCharger=true;
	// 是否为小组临时成员 y/Y-是 n/N-否
	private Boolean isTemp;
	// 介绍人是否启动（用于软删除)
	private Boolean iValid = true;
//	// 用于区别该实体表示员工还是用户 true:员工 ，false:用户
//	private Boolean type = true;
	// 人员所属项目小组 projectGroup
	private ProjectGroup projectGroup;
	/* 用户权限组 */
	private Set<PrivilegeGroup> groups = new HashSet<PrivilegeGroup>();

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(length = 10)
	public String getcPsnCode() {
		return cPsnCode;
	}

	public void setcPsnCode(String cPsnCode) {
		this.cPsnCode = cPsnCode;
	}

	@Column(length = 40, nullable = false)
	public String getcPsnName() {
		return cPsnName;
	}

	public void setcPsnName(String cPsnName) {
		this.cPsnName = cPsnName;
	}

	@Column(length = 40, nullable = false, unique = true)
	public String getcUserName() {
		return cUserName;
	}

	public void setcUserName(String cUserName) {
		this.cUserName = cUserName;
	}

	@Column(length = 40, nullable = false)
	public String getcPassword() {
		return cPassword;
	}

	public void setcPassword(String cPassword) {
		this.cPassword = cPassword;
	}

	@Column(length = 40)
	public String getvAliaName() {
		return vAliaName;
	}

	public void setvAliaName(String vAliaName) {
		this.vAliaName = vAliaName;
	}

	@ManyToOne(optional = true, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "department_id")
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Column(length = 20)
	public Integer getrPersonType() {
		return rPersonType;
	}

	public void setrPersonType(Integer rPersonType) {
		this.rPersonType = rPersonType;
	}

	@Column(length = 5, nullable = false)
	@Enumerated(EnumType.STRING)
	public Gender getrSex() {
		return rSex;
	}

	public void setrSex(Gender rSex) {
		this.rSex = rSex;
	}

	@Temporal(TemporalType.DATE)
	public Date getdBirthday() {
		return dBirthday;
	}

	public void setdBirthday(Date dBirthday) {
		this.dBirthday = dBirthday;
	}

	@Column(length = 20)
	public String getcPsnMobilePhone() {
		return cPsnMobilePhone;
	}

	public void setcPsnMobilePhone(String cPsnMobilePhone) {
		this.cPsnMobilePhone = cPsnMobilePhone;
	}

	@Column
	@org.hibernate.annotations.Type(type = "yes_no")
	public Boolean getbPsnPerson() {
		return bPsnPerson;
	}

	public void setbPsnPerson(Boolean bPsnPerson) {
		this.bPsnPerson = bPsnPerson;
	}

	@Column(length = 20)
	public String getcPsnFPhone() {
		return cPsnFPhone;
	}

	public void setcPsnFPhone(String cPsnFPhone) {
		this.cPsnFPhone = cPsnFPhone;
	}

	@Column(length = 20)
	public String getcPsnOPhone() {
		return cPsnOPhone;
	}

	public void setcPsnOPhone(String cPsnOPhone) {
		this.cPsnOPhone = cPsnOPhone;
	}

	@Column(length = 60)
	public String getcPsnEmail() {
		return cPsnEmail;
	}

	public void setcPsnEmail(String cPsnEmail) {
		this.cPsnEmail = cPsnEmail;
	}

	@Column(length = 200)
	public String getcPsnPostAddr() {
		return cPsnPostAddr;
	}

	public void setcPsnPostAddr(String cPsnPostAddr) {
		this.cPsnPostAddr = cPsnPostAddr;
	}

	@Column(length = 200)
	public String getcPsnFAddr() {
		return cPsnFAddr;
	}

	public void setcPsnFAddr(String cPsnFAddr) {
		this.cPsnFAddr = cPsnFAddr;
	}

	@Column(length = 20)
	public String getcPsnQQCode() {
		return cPsnQQCode;
	}

	public void setcPsnQQCode(String cPsnQQCode) {
		this.cPsnQQCode = cPsnQQCode;
	}

	@Column(length = 50)
	public String getcPsnURL() {
		return cPsnURL;
	}

	public void setcPsnURL(String cPsnURL) {
		this.cPsnURL = cPsnURL;
	}

	@Temporal(TemporalType.DATE)
	public Date getdEnterUnitDate() {
		return dEnterUnitDate;
	}

	public void setdEnterUnitDate(Date dEnterUnitDate) {
		this.dEnterUnitDate = dEnterUnitDate;
	}

	@Column
	@org.hibernate.annotations.Type(type = "yes_no")
	public Boolean getbProbation() {
		return bProbation;
	}

	public void setbProbation(Boolean bProbation) {
		this.bProbation = bProbation;
	}

	@Column(length = 20)
	public String getrNational() {
		return rNational;
	}

	public void setrNational(String rNational) {
		this.rNational = rNational;
	}

	@Column(length = 60)
	public String getrHealthStatus() {
		return rHealthStatus;
	}

	public void setrHealthStatus(String rHealthStatus) {
		this.rHealthStatus = rHealthStatus;
	}

	@Column(length = 20)
	public Integer getrMarriStatus() {
		return rMarriStatus;
	}

	public void setrMarriStatus(Integer rMarriStatus) {
		this.rMarriStatus = rMarriStatus;
	}

	@Column(length = 20)
	public String getvIDNo() {
		return vIDNo;
	}

	public void setvIDNo(String vIDNo) {
		this.vIDNo = vIDNo;
	}

	@Column(length = 20)
	public String getrPerResidence() {
		return rPerResidence;
	}

	public void setrPerResidence(String rPerResidence) {
		this.rPerResidence = rPerResidence;
	}

	@Temporal(TemporalType.DATE)
	public Date getdJoinworkDate() {
		return dJoinworkDate;
	}

	public void setdJoinworkDate(Date dJoinworkDate) {
		this.dJoinworkDate = dJoinworkDate;
	}

	@Temporal(TemporalType.DATE)
	public Date getdEnterDate() {
		return dEnterDate;
	}

	public void setdEnterDate(Date dEnterDate) {
		this.dEnterDate = dEnterDate;
	}

	@Temporal(TemporalType.DATE)
	public Date getdRegularDate() {
		return dRegularDate;
	}

	public void setdRegularDate(Date dRegularDate) {
		this.dRegularDate = dRegularDate;
	}

	@Column(length = 25)
	public String getvSsNo() {
		return vSsNo;
	}

	public void setvSsNo(String vSsNo) {
		this.vSsNo = vSsNo;
	}

	@Column
	@org.hibernate.annotations.Type(type = "yes_no")
	public Boolean getIsCharger() {
		return isCharger;
	}

	public void setIsCharger(Boolean isCharger) {
		this.isCharger = isCharger;
	}

	@Column
	@org.hibernate.annotations.Type(type = "yes_no")
	public Boolean getIsTemp() {
		return isTemp;
	}

	public void setIsTemp(Boolean isTemp) {
		this.isTemp = isTemp;
	}

	@Column
	@org.hibernate.annotations.Type(type = "yes_no")
	public Boolean getiValid() {
		return iValid;
	}

	public void setiValid(Boolean iValid) {
		this.iValid = iValid;
	}

//	@Column
//	@org.hibernate.annotations.Type(type = "yes_no")
//	public Boolean getType() {
//		return type;
//	}
//
//	public void setType(Boolean type) {
//		this.type = type;
//	}

	@ManyToOne()
	@JoinColumn(name = "projectGroup_id")
	public ProjectGroup getProjectGroup() {
		return projectGroup;
	}

	public void setProjectGroup(ProjectGroup projectGroup) {
		this.projectGroup = projectGroup;
	}

	@ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinTable(name = "eg", inverseJoinColumns = @JoinColumn(name = "group_id"), joinColumns = @JoinColumn(name = "user_id"))
	public Set<PrivilegeGroup> getGroups() {
		return groups;
	}

	public void setGroups(Set<PrivilegeGroup> groups) {
		this.groups = groups;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HRPerson other = (HRPerson) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
