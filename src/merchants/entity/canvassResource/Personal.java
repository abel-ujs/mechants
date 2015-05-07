package merchants.entity.canvassResource;

import java.io.Serializable;

import javax.jws.HandlerChain;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
//介绍人信息
public class Personal implements Serializable {

	private static final long serialVersionUID = 1L;
	// 介绍人ID
	private Integer id;
	// 人员编号
 	private String cPersonCode;
	// 人员姓名
	private String cPersonName;
	// 性别
	private Gender cSex;
	// 证件号码
	private String cIDNo;
	// 职务
	private String cPersonPost;
	// 住址
	private String cPersonAddress;
	// 邮箱
	private String cPersonEmail;
	// 手机
	private String cPersonHand;
	// 电话
	private String cPersonPhone;
	// QQ号码
	private String cPersonQQ;
	// 单位
	private String cPersonCompany;
	// 简介
	private String cPersonBrief;
	// 备注
	private String cMemo;
	// 介绍人是否启动（用于软删除)
	private Boolean iValid=true;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO )
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(length = 20)
	public String getcPersonCode() {
		return cPersonCode;
	}

	public void setcPersonCode(String cPersonCode) {
		this.cPersonCode = cPersonCode;
	}

	@Column(length = 40, nullable = false)
	public String getcPersonName() {
		return cPersonName;
	}

	public void setcPersonName(String cPersonName) {
		this.cPersonName = cPersonName;
	}

	 

	@Column(length = 5, nullable = false)
	@Enumerated(EnumType.STRING)
	public Gender getcSex() {
		return cSex;
	}

	public void setcSex(Gender cSex) {
		this.cSex = cSex;
	}

	@Column(length = 18, nullable = false)
	public String getcIDNo() {
		return cIDNo;
	}

	public void setcIDNo(String cIDNo) {
		this.cIDNo = cIDNo;
	}

	@Column(length = 60)
	public String getcPersonPost() {
		return cPersonPost;
	}

	public void setcPersonPost(String cPersonPost) {
		this.cPersonPost = cPersonPost;
	}

	@Column(length = 200)
	public String getcPersonAddress() {
		return cPersonAddress;
	}

	public void setcPersonAddress(String cPersonAddress) {
		this.cPersonAddress = cPersonAddress;
	}

	@Column(length = 60)
	public String getcPersonEmail() {
		return cPersonEmail;
	}

	public void setcPersonEmail(String cPersonEmail) {
		this.cPersonEmail = cPersonEmail;
	}

	@Column(length = 60)
	public String getcPersonHand() {
		return cPersonHand;
	}

	public void setcPersonHand(String cPersonHand) {
		this.cPersonHand = cPersonHand;
	}

	@Column(length = 60)
	public String getcPersonPhone() {
		return cPersonPhone;
	}

	public void setcPersonPhone(String cPersonPhone) {
		this.cPersonPhone = cPersonPhone;
	}

	@Column(length = 20)
	public String getcPersonQQ() {
		return cPersonQQ;
	}

	public void setcPersonQQ(String cPersonQQ) {
		this.cPersonQQ = cPersonQQ;
	}

	@Column(length = 60)
	public String getcPersonCompany() {
		return cPersonCompany;
	}

	public void setcPersonCompany(String cPersonCompany) {
		this.cPersonCompany = cPersonCompany;
	}

	@Column(length = 1200)
	public String getcPersonBrief() {
		return cPersonBrief;
	}

	public void setcPersonBrief(String cPersonBrief) {
		this.cPersonBrief = cPersonBrief;
	}

	@Column(length = 300)
	public String getcMemo() {
		return cMemo;
	}

	public void setcMemo(String cMemo) {
		this.cMemo = cMemo;
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
		Personal other = (Personal) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
