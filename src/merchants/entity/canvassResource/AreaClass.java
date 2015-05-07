package merchants.entity.canvassResource;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
//地区信息
@Entity
public class AreaClass  implements Serializable{
	private static final long serialVersionUID = -4420076279679894260L;
//地区id
private Integer id;
//地区编码
private String cDCCode;
//地区名称
private String cDCName;
//编码级次
private Integer iDCGrade;
//介绍单位集
private Set<Customer> customers=new HashSet<Customer>();
@Id@GeneratedValue(strategy=GenerationType.AUTO)
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
@Column(length=12,nullable=false)
public String getcDCCode() {
	return cDCCode;
}
public void setcDCCode(String cDCCode) {
	this.cDCCode = cDCCode;
}
@Column(length=20,nullable=false)
public String getcDCName() {
	return cDCName;
}
public void setcDCName(String cDCName) {
	this.cDCName = cDCName;
}
@Column(length=1)
public Integer getiDCGrade() {
	return iDCGrade;
}
public void setiDCGrade(Integer iDCGrade) {
	this.iDCGrade = iDCGrade;
}
@OneToMany(mappedBy = "areaClass", cascade =CascadeType.PERSIST)
public Set<Customer> getCustomers() {
	return customers;
}
public void setCustomers(Set<Customer> customers) {
	this.customers = customers;
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
	AreaClass other = (AreaClass) obj;
	if (id == null) {
		if (other.id != null)
			return false;
	} else if (!id.equals(other.id))
		return false;
	return true;
}

}