package merchants.entity.privilege;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import merchants.entity.canvassGroup.HRPerson;

/**
 * 权限组
 */
@Entity
public class PrivilegeGroup implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6653209898059140361L;
	/*组id*/
	@Id @Column(length=36)
	private String groupid;
	/*组名称*/
	@Column(length=20,nullable=false)
	private String name;
	
	@ManyToMany(cascade=CascadeType.REFRESH,fetch=FetchType.EAGER)
	@JoinTable(name="ps",inverseJoinColumns={@JoinColumn(name="module",referencedColumnName="module"),
			@JoinColumn(name="privilege",referencedColumnName="privilege")},joinColumns=@JoinColumn(name="group_id"))
	private Set<SystemPrivilege> privileges = new HashSet<SystemPrivilege>();

	/*拥有该权限组的用户*/
	@ManyToMany(mappedBy="groups",cascade=CascadeType.REFRESH)
	private Set<HRPerson> employees = new HashSet<HRPerson>();
	
	public String getGroupid() {
		return groupid;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<SystemPrivilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(Set<SystemPrivilege> privileges) {
		this.privileges = privileges;
	}

	

	

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
		result = prime * result + ((groupid == null) ? 0 : groupid.hashCode());
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
		PrivilegeGroup other = (PrivilegeGroup) obj;
		if (groupid == null) {
			if (other.groupid != null)
				return false;
		} else if (!groupid.equals(other.groupid))
			return false;
		return true;
	}
	
	
}
