package merchants.entity.canvassResource;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
// 介绍单位信息
public class Customer implements Serializable {
	private static final long serialVersionUID = -4928003883870916695L;
	// 介绍单位ID
	private Integer id;
	// 客户编码
   private String cCusCode;
	// 客户名称
	private String cCusName;
	// 客户分类编码
	private String cCCCode;
	// 地区分类
	private AreaClass areaClass;
	// 地址
	private String cCusAddress;
	// 邮政编码
	private String cCusPostCode;
	// 行业信息
	private Industry industry;
	// 联系人
	private String cCusPerson;
	// 固定电话
	private String cCusPhone;
	// 传真
	private String cCusFax;
	// Email地址
	private String cCusEmail;
	// QQ号码
	private String cCusQQ;
	// 手机
	private String cCusHand;
	// 法人
	private String cCusLPerson;
	// 建档人
	private String cCreatePerson;
	// 备注
	private String cMemo;
	// 简介
	private String cCusBrief;
	// 介绍单位是否启动（用于软删除)
	private Boolean iValid=true;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(length = 20)
	public String getcCusCode() {
		return cCusCode;
	}

	public void setcCusCode(String cCusCode) {
		this.cCusCode = cCusCode;
	}

	@Column(length = 50, nullable = false)
	public String getcCusName() {
		return cCusName;
	}

	public void setcCusName(String cCusName) {
		this.cCusName = cCusName;
	}

	@Column(length = 12)
	public String getcCCCode() {
		return cCCCode;
	}

	public void setcCCCode(String cCCCode) {
		this.cCCCode = cCCCode;
	}

	@ManyToOne(cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "areaClass_id")
	public AreaClass getAreaClass() {
		return areaClass;
	}

	public void setAreaClass(AreaClass areaClass) {
		this.areaClass = areaClass;
	}

	@Column(length = 200)
	public String getcCusAddress() {
		return cCusAddress;
	}

	public void setcCusAddress(String cCusAddress) {
		this.cCusAddress = cCusAddress;
	}

	@Column(length = 6)
	public String getcCusPostCode() {
		return cCusPostCode;
	}

	public void setcCusPostCode(String cCusPostCode) {
		this.cCusPostCode = cCusPostCode;
	}

	@ManyToOne(optional = false, cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "industry_id")
	public Industry getIndustry() {
		return industry;
	}

	public void setIndustry(Industry industry) {
		this.industry = industry;
	}

	@Column(length = 60)
	public String getcCusPerson() {
		return cCusPerson;
	}

	public void setcCusPerson(String cCusPerson) {
		this.cCusPerson = cCusPerson;
	}

	@Column(length = 15)
	public String getcCusPhone() {
		return cCusPhone;
	}

	public void setcCusPhone(String cCusPhone) {
		this.cCusPhone = cCusPhone;
	}

	@Column(length = 15)
	public String getcCusFax() {
		return cCusFax;
	}

	public void setcCusFax(String cCusFax) {
		this.cCusFax = cCusFax;
	}

	@Column(length = 60)
	public String getcCusEmail() {
		return cCusEmail;
	}

	public void setcCusEmail(String cCusEmail) {
		this.cCusEmail = cCusEmail;
	}

	@Column(length = 20)
	public String getcCusQQ() {
		return cCusQQ;
	}

	public void setcCusQQ(String cCusQQ) {
		this.cCusQQ = cCusQQ;
	}

	@Column(length = 15)
	public String getcCusHand() {
		return cCusHand;
	}

	public void setcCusHand(String cCusHand) {
		this.cCusHand = cCusHand;
	}

	@Column(length = 60)
	public String getcCusLPerson() {
		return cCusLPerson;
	}

	public void setcCusLPerson(String cCusLPerson) {
		this.cCusLPerson = cCusLPerson;
	}

	@Column(length = 60)
	public String getcCreatePerson() {
		return cCreatePerson;
	}

	public void setcCreatePerson(String cCreatePerson) {
		this.cCreatePerson = cCreatePerson;
	}

	@Column(length = 300)
	public String getcMemo() {
		return cMemo;
	}

	public void setcMemo(String cMemo) {
		this.cMemo = cMemo;
	}

	@Column(length = 300)
	public String getcCusBrief() {
		return cCusBrief;
	}

	public void setcCusBrief(String cCusBrief) {
		this.cCusBrief = cCusBrief;
	}
    @Column
    @org.hibernate.annotations.Type(type="yes_no")
	public Boolean getiValid() {
		return iValid;
	}

	public void setiValid(Boolean iValid) {
		this.iValid = iValid;
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
		Customer other = (Customer) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
