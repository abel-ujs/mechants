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
//投资方信息
@Entity
public class Investor  implements Serializable {
	 
	private static final long serialVersionUID = 4880922323722144781L;
	// 投资方ID
	private Integer id;
	// 投资方编码
	private String cInvCode;
	// 投资方名称
	private String cInvName;
	// 地址
	private String cInvAddress;
	// 网址
	private String cInvWebSite;
	// 所属企业性质
	private Nature nature;
	// 所属行业信息
	private Industry industry;
	// 创建时间
	private Date cCreateDate;
	// 法人
	private String cInvLPerson;
	// 电话
	private String cInvLPhone;
	// 传真
	private String cInvLFax;
	// 手机
	private String cInvLHand;
	// Email
	private String cInvLEmail;
	// 职务
	private String cInvLPost;
	// 联系人
	private String cInvPerson;
	// 电话
	private String cInvPhone;
	// 传真
	private String cInvFax;
	// 手机
	private String cInvHand;
	// Email
	private String cInvEmail;
	// 职务
	private String cInvPost;
	// 注册资金
	private Double dCapital=0D;
	// 年产值
	private Double dOValue=0D;
	// 行业地位
	private String cIndustry;
	// 技术水平
	private String cLevel;
	// 主要产品
	private String cMainProducts;
	// 产品竞争力
	private String cCompeProducts;
	// 市场分额
	private String cMarketShare;
	// 产品销售对象信息
	private String cInformation;
	// 建档人员
	private String cOperator;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(length=12)
	public String getcInvCode() {
		return cInvCode;
	}
	public void setcInvCode(String cInvCode) {
		this.cInvCode = cInvCode;
	}
	@Column(length=60,nullable=false)
	public String getcInvName() {
		return cInvName;
	}
	public void setcInvName(String cInvName) {
		this.cInvName = cInvName;
	}
	@Column(length=200)
	public String getcInvAddress() {
		return cInvAddress;
	}
	public void setcInvAddress(String cInvAddress) {
		this.cInvAddress = cInvAddress;
	}
	@Column(length=60)
	public String getcInvWebSite() {
		return cInvWebSite;
	}
	public void setcInvWebSite(String cInvWebSite) {
		this.cInvWebSite = cInvWebSite;
	}
	@ManyToOne(cascade={CascadeType.REFRESH})
	@JoinColumn(name="nature_id")
	public Nature getNature() {
		return nature;
	}
	public void setNature(Nature nature) {
		this.nature = nature;
	}
	@ManyToOne(cascade={CascadeType.REFRESH})
	@JoinColumn(name="industry_id")
	public Industry getIndustry() {
		return industry;
	}
	public void setIndustry(Industry industry) {
		this.industry = industry;
	}
	@Temporal(TemporalType.DATE)
	public Date getcCreateDate() {
		return cCreateDate;
	}
	public void setcCreateDate(Date cCreateDate) {
		this.cCreateDate = cCreateDate;
	}
	@Column(length=20)
	public String getcInvLPerson() {
		return cInvLPerson;
	}
	public void setcInvLPerson(String cInvLPerson) {
		this.cInvLPerson = cInvLPerson;
	}
	@Column(length=15)
	public String getcInvLPhone() {
		return cInvLPhone;
	}
	public void setcInvLPhone(String cInvLPhone) {
		this.cInvLPhone = cInvLPhone;
	}
	@Column(length=15)
	public String getcInvLFax() {
		return cInvLFax;
	}
	public void setcInvLFax(String cInvLFax) {
		this.cInvLFax = cInvLFax;
	}
	@Column(length=15)
	public String getcInvLHand() {
		return cInvLHand;
	}
	public void setcInvLHand(String cInvLHand) {
		this.cInvLHand = cInvLHand;
	}
	@Column(length=60)
	public String getcInvLEmail() {
		return cInvLEmail;
	}
	public void setcInvLEmail(String cInvLEmail) {
		this.cInvLEmail = cInvLEmail;
	}
	@Column(length=30)
	public String getcInvLPost() {
		return cInvLPost;
	}
	public void setcInvLPost(String cInvLPost) {
		this.cInvLPost = cInvLPost;
	}
	@Column(length=20)
	public String getcInvPerson() {
		return cInvPerson;
	}
	public void setcInvPerson(String cInvPerson) {
		this.cInvPerson = cInvPerson;
	}
	@Column(length=15)
	public String getcInvPhone() {
		return cInvPhone;
	}
	public void setcInvPhone(String cInvPhone) {
		this.cInvPhone = cInvPhone;
	}
	@Column(length=15)
	public String getcInvFax() {
		return cInvFax;
	}
	
	public void setcInvFax(String cInvFax) {
		this.cInvFax = cInvFax;
	}
	@Column(length=15)
	public String getcInvHand() {
		return cInvHand;
	}
	public void setcInvHand(String cInvHand) {
		this.cInvHand = cInvHand;
	}
	@Column(length=60)
	public String getcInvEmail() {
		return cInvEmail;
	}
	public void setcInvEmail(String cInvEmail) {
		this.cInvEmail = cInvEmail;
	}
	@Column(length=30)
	public String getcInvPost() {
		return cInvPost;
	}
	public void setcInvPost(String cInvPost) {
		this.cInvPost = cInvPost;
	}
	@Column
	public Double getdCapital() {
		return dCapital;
	}
	public void setdCapital(Double dCapital) {
		this.dCapital = dCapital;
	}
	@Column
	public Double getdOValue() {
		return dOValue;
	}
	public void setdOValue(Double dOValue) {
		this.dOValue = dOValue;
	}
	@Column(length=60)
	public String getcIndustry() {
		return cIndustry;
	}
	public void setcIndustry(String cIndustry) {
		this.cIndustry = cIndustry;
	}
	@Column(length=200)
	public String getcLevel() {
		return cLevel;
	}
	public void setcLevel(String cLevel) {
		this.cLevel = cLevel;
	}
	@Column(length=200)
	public String getcMainProducts() {
		return cMainProducts;
	}
	public void setcMainProducts(String cMainProducts) {
		this.cMainProducts = cMainProducts;
	}
	@Column(length=200)
	public String getcCompeProducts() {
		return cCompeProducts;
	}
	public void setcCompeProducts(String cCompeProducts) {
		this.cCompeProducts = cCompeProducts;
	}
	@Column(length=200)
	public String getcMarketShare() {
		return cMarketShare;
	}
	public void setcMarketShare(String cMarketShare) {
		this.cMarketShare = cMarketShare;
	}
	@Column(length=200)
	public String getcInformation() {
		return cInformation;
	}
	public void setcInformation(String cInformation) {
		this.cInformation = cInformation;
	}
	@Column(length=20)
	public String getcOperator() {
		return cOperator;
	}
	public void setcOperator(String cOperator) {
		this.cOperator = cOperator;
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
		Investor other = (Investor) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
