package merchants.entity.projectInformation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import merchants.entity.canvassGroup.Outcome;
import merchants.entity.canvassGroup.Pro_Capital_In;
import merchants.entity.canvassGroup.Pro_Commun;
import merchants.entity.canvassGroup.Pro_Enclosed;
import merchants.entity.canvassGroup.Pro_Negotiate;
import merchants.entity.canvassGroup.Pro_Settled;
import merchants.entity.canvassGroup.Pro_Signing;
import merchants.entity.canvassGroup.ProjectGroup;
import merchants.entity.canvassResource.Customer;
import merchants.entity.canvassResource.Industry;
import merchants.entity.canvassResource.Investor;
import merchants.entity.canvassResource.Personal;

//项目信息
@Entity
public class ProjectInfo implements Serializable {

	private static final long serialVersionUID = -8665028295862854889L;
	// /项目ID
	private Integer id;
	// /项目来源
	private String cSource;
	// /项目来源-介绍人
	private Personal personal;
	// /项目来源-介绍单位
	private Customer customer;
	// /所属行业
	private Industry industry;
	// 投资方
	private Investor investor;
	// 项目编码 (项目编码：年+月+0001）
	private String cProCode;
	// 项目名称
	private String cProName;
	// 入住园区目的（0-扩大产能、1-企业搬迁、2-开发新产品、3-创业）
	private Integer iObjective;

	// 投资规模(万）
	private double dInvestment=0;
	// 主要业务
	private String cMainBuessiness;
	// 商业模式
	private String cBusinessModel;
	// 投资推进计划
	private String cPromotionPlan;
	// 主要产品
	private String cMainProducts;
	// 市场营销策略信息
	private String cMarketingInfo;
	// 是否结束流转：0/null-正常；1-流转；2-终止
	private Integer iEnd = 0;
	// 建档人员
	private String cOperator;
	private String cSummary;
	// 状态
	private Integer cState = 1;
	// 审核情况 0-保存未审核 1:审核通过 2:审核不通过 3：提交未审核
	private Integer iVerify = 0;
	// 项目类别（0：平台类项目 1：产业性项目 2：现代服务业项目
	private Integer cCategory;
	// 项目终止原因
	private String cReason;

	// 项目所属项目小组
	private ProjectGroup projectGroup;
	// 项目所属达产达效
	private Outcome outcome;
	// HL 关联信息
	private Pro_Commun commun;
	private Pro_Signing sign;
	private List<Pro_Negotiate> negotiate = new ArrayList<Pro_Negotiate>();
	private Pro_Capital_In capital;
	private Pro_Settled settled;
	private Set<Pro_Enclosed> enclosed = new HashSet<Pro_Enclosed>();

	// HL关联信息

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(length = 30, nullable = false)
	public String getcSource() {
		return cSource;
	}

	public void setcSource(String cSource) {
		this.cSource = cSource;
	}

	@OneToOne()
	@JoinColumn(name = "personal_id")
	public Personal getPersonal() {
		return personal;
	}

	public void setPersonal(Personal personal) {
		this.personal = personal;
	}

	@OneToOne
	@JoinColumn(name = "customer_id")
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	// 下面只配置一端
	@ManyToOne(optional = false)
	@JoinColumn(name = "industry_id")
	public Industry getIndustry() {
		return industry;
	}

	public void setIndustry(Industry industry) {
		this.industry = industry;
	}

	@ManyToOne(optional = false)
	@JoinColumn(name = "investor_id")
	public Investor getInvestor() {
		return investor;
	}

	public void setInvestor(Investor investor) {
		this.investor = investor;
	}

	@Column(length = 20, nullable = false, unique = true)
	public String getcProCode() {
		return cProCode;
	}

	public void setcProCode(String cProCode) {
		this.cProCode = cProCode;
	}

	@Column(length = 50, nullable = false, unique = true)
	public String getcProName() {
		return cProName;
	}

	public void setcProName(String cProName) {
		this.cProName = cProName;
	}

	@Column(nullable = false)
	public Integer getiObjective() {
		return iObjective;
	}

	public void setiObjective(Integer iObjective) {
		this.iObjective = iObjective;
	}

	@Column
	public double getdInvestment() {
		return dInvestment;
	}

	public void setdInvestment(double dInvestment) {
		this.dInvestment = dInvestment;
	}

	@Column(length = 200)
	public String getcMainBuessiness() {
		return cMainBuessiness;
	}

	public void setcMainBuessiness(String cMainBuessiness) {
		this.cMainBuessiness = cMainBuessiness;
	}

	@Column(length = 200)
	public String getcBusinessModel() {
		return cBusinessModel;
	}

	public void setcBusinessModel(String cBusinessModel) {
		this.cBusinessModel = cBusinessModel;
	}

	@Column(length = 200)
	public String getcPromotionPlan() {
		return cPromotionPlan;
	}

	public void setcPromotionPlan(String cPromotionPlan) {
		this.cPromotionPlan = cPromotionPlan;
	}

	@Column(length = 200)
	public String getcMainProducts() {
		return cMainProducts;
	}

	public void setcMainProducts(String cMainProducts) {
		this.cMainProducts = cMainProducts;
	}

	@Column(length = 200)
	public String getcMarketingInfo() {
		return cMarketingInfo;
	}

	public void setcMarketingInfo(String cMarketingInfo) {
		this.cMarketingInfo = cMarketingInfo;
	}

	@Column(nullable = false)
	public Integer getiEnd() {
		return iEnd;
	}

	public void setiEnd(Integer iEnd) {
		this.iEnd = iEnd;
	}

	@Column(length = 20)
	public String getcOperator() {
		return cOperator;
	}

	public void setcOperator(String cOperator) {
		this.cOperator = cOperator;
	}

	@Column(length = 300)
	public String getcSummary() {
		return cSummary;
	}

	public void setcSummary(String cSummary) {
		this.cSummary = cSummary;
	}

	@Column(length = 1)
	public Integer getcState() {
		return cState;
	}

	public void setcState(Integer cState) {
		this.cState = cState;
	}

	@Column
	public Integer getcCategory() {
		return cCategory;
	}

	public void setcCategory(Integer cCategory) {
		this.cCategory = cCategory;
	}

	@Column(length = 1000)
	public String getcReason() {
		return cReason;
	}

	public void setcReason(String cReason) {
		this.cReason = cReason;
	}

	@ManyToOne()
	@JoinColumn(name = "projectGroup_id")
	public ProjectGroup getProjectGroup() {
		return projectGroup;
	}

	public void setProjectGroup(ProjectGroup projectGroup) {
		this.projectGroup = projectGroup;
	}

	@Column
	public Integer getiVerify() {
		return iVerify;
	}

	public void setiVerify(Integer iVerify) {
		this.iVerify = iVerify;
	}

	@OneToOne(mappedBy = "project")
	public Outcome getOutcome() {
		return outcome;
	}

	public void setOutcome(Outcome outcome) {
		this.outcome = outcome;
	}

	// HL关联信息
	@OneToMany(mappedBy = "project")
	public List<Pro_Negotiate> getNegotiate() {
		return negotiate;
	}

	public void setNegotiate(List<Pro_Negotiate> negotiate) {
		this.negotiate = negotiate;
	}

	@OneToOne(mappedBy = "project")
	public Pro_Capital_In getCapital() {
		return capital;
	}

	public void setCapital(Pro_Capital_In capital) {
		this.capital = capital;
	}

	@OneToOne(mappedBy = "project")
	public Pro_Settled getSettled() {
		return settled;
	}

	public void setSettled(Pro_Settled settled) {
		this.settled = settled;
	}

	@OneToMany(mappedBy = "project")
	public Set<Pro_Enclosed> getEnclosed() {
		return enclosed;
	}

	public void setEnclosed(Set<Pro_Enclosed> enclosed) {
		this.enclosed = enclosed;
	}

	@OneToOne(mappedBy = "project")
	public Pro_Commun getCommun() {
		return commun;
	}

	public void setCommun(Pro_Commun commun) {
		this.commun = commun;
	}

	@OneToOne(mappedBy = "project")
	public Pro_Signing getSign() {
		return sign;
	}

	public void setSign(Pro_Signing sign) {
		this.sign = sign;
	}

	// HL关联信息

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
		ProjectInfo other = (ProjectInfo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
