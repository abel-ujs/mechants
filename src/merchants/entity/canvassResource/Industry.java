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
@Entity
//行业信息Industry
public class Industry implements Serializable {
 
	private static final long serialVersionUID = 2398221595980183953L;
	//行业id
	private Integer id;
	//行业编码
	private String cInduCode;
	//行业名称
	private String cInduName;
	//行业级次
	private Integer iInduGrade;
	//介绍单位集
	private Set<Customer> customers=new HashSet<Customer>();
	//投资方集
	private Set<Investor> investors=new HashSet<Investor>();
	@Id@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(length=12,nullable=false)
	public String getcInduCode() {
		return cInduCode;
	}
	public void setcInduCode(String cInduCode) {
		this.cInduCode = cInduCode;
	}
	@Column(length=20,nullable=false)
	public String getcInduName() {
		return cInduName;
	}
	public void setcInduName(String cInduName) {
		this.cInduName = cInduName;
	}
	@Column(length=1)
	public Integer getiInduGrade() {
		return iInduGrade;
	}
	public void setiInduGrade(Integer iInduGrade) {
		this.iInduGrade = iInduGrade;
	}
	@OneToMany(mappedBy="industry",cascade =CascadeType.PERSIST)
	
	public Set<Customer> getCustomers() {
		return customers;
	}
	public void setCustomers(Set<Customer> customers) {
		this.customers = customers;
	}
	@OneToMany(mappedBy="nature",cascade =CascadeType.PERSIST)
	public Set<Investor> getInvestors() {
		return investors;
	}
	public void setInvestors(Set<Investor> investors) {
		this.investors = investors;
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
		Industry other = (Industry) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
