package merchants.entity.canvassGroup;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
//部门信息
public class Department implements Serializable {
  
	private static final long serialVersionUID = -7594578951581233772L;
	// ID
	private Integer id;
	// 部门编码
	private String cDeptCode;
	// 部门名称
	private String cDeptName;
	// 查询码
	private String vrQueryCode;
	// 创建日期
	private Date dOrganizeDate;
	// 是否停用  y-停用  n-启用
	private String tiStop;
	// 负责人
	private String vrCharger;
	// 负责人电话
	private String vrTel;
	// 职务
	private String vrPostName;
	// 部门所属单位人员
	private Set<HRPerson> employees=new HashSet<HRPerson>();
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
    @Column(length=20)
	public String getcDeptCode() {
		return cDeptCode;
	}

	public void setcDeptCode(String cDeptCode) {
		this.cDeptCode = cDeptCode;
	}
	 @Column(length=50,nullable=false,unique=true)
	public String getcDeptName() {
		return cDeptName;
	}

	public void setcDeptName(String cDeptName) {
		this.cDeptName = cDeptName;
	}
	 @Column(length=20)
	public String getVrQueryCode() {
		return vrQueryCode;
	}

	public void setVrQueryCode(String vrQueryCode) {
		this.vrQueryCode = vrQueryCode;
	}
   @Temporal(TemporalType.DATE)
	public Date getdOrganizeDate() {
		return dOrganizeDate;
	}

	public void setdOrganizeDate(Date dOrganizeDate) {
		this.dOrganizeDate = dOrganizeDate;
	}
	 @Column(length=1)
	public String getTiStop() {
		return tiStop;
	}

	public void setTiStop(String tiStop) {
		this.tiStop = tiStop;
	}
	 @Column(length=50)
	public String getVrCharger() {
		return vrCharger;
	}

	public void setVrCharger(String vrCharger) {
		this.vrCharger = vrCharger;
	}
	 @Column(length=20)
	public String getVrTel() {
		return vrTel;
	}

	public void setVrTel(String vrTel) {
		this.vrTel = vrTel;
	}
	 @Column(length=50)
	public String getVrPostName() {
		return vrPostName;
	}

	public void setVrPostName(String vrPostName) {
		this.vrPostName = vrPostName;
	}

	@OneToMany(mappedBy="department",cascade=CascadeType.REMOVE)
	 public Set<HRPerson> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<HRPerson> employees) {
		this.employees = employees;
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
		Department other = (Department) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	

}
