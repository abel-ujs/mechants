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

//企业性质
@Entity
public class Nature implements Serializable {
	private static final long serialVersionUID = 7829514104266529602L;
	// 企业性质id
	private Integer id;
	// 内外资
	private String cNatcCode;
	// 企业性质编码
	private String cNatCode;
	// 企业性质名称
	private String cNatName;
	// 投资方信息集
	private Set<Investor> investors = new HashSet<Investor>();

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(length = 60, nullable = false)
	public String getcNatcCode() {
		return cNatcCode;
	}

	public void setcNatcCode(String cNatcCode) {
		this.cNatcCode = cNatcCode;
	}

	@Column(length = 6, nullable = false)
	public String getcNatCode() {
		return cNatCode;
	}

	public void setcNatCode(String cNatCode) {
		this.cNatCode = cNatCode;
	}

	@Column(length = 60, nullable = false)
	public String getcNatName() {
		return cNatName;
	}

	public void setcNatName(String cNatName) {
		this.cNatName = cNatName;
	}

	@OneToMany(mappedBy = "nature", cascade = CascadeType.PERSIST)
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
		Nature other = (Nature) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}