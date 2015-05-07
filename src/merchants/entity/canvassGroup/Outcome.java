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
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import merchants.entity.Attachment;
import merchants.entity.projectInformation.ProjectInfo;

 

@Entity
// 达产达效信息
public class Outcome implements Serializable {
	private static final long serialVersionUID = 1494883605408522594L;
	// id
	private Integer id;
	// 项目达产达效时间
	private Date ddate;
	// 规模
	private double cInvestment;
	// 效益
	private String cProfit;
	// 主要产品
	private String cMainProducts;
	// 达产达效原因（或不达产达效原因）
	private String cReason;
	// 与原目标对比情况
	private String cCompare;
	// 相关附件
	private Set<Attachment> attachments=new HashSet<Attachment>();
	// 所属项目
	private ProjectInfo project;
	// 建档人员
	private String cOperator;
	@Id@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	@Column
	public double getcInvestment() {
		return cInvestment;
	}
	@Temporal(TemporalType.DATE)
	public Date getDdate() {
		return ddate;
	}
	public void setDdate(Date ddate) {
		this.ddate = ddate;
	}
	public void setcInvestment(double cInvestment) {
		this.cInvestment = cInvestment;
	}
	@Column(length=200)
	public String getcProfit() {
		return cProfit;
	}
	public void setcProfit(String cProfit) {
		this.cProfit = cProfit;
	}
	@Column(length=200)
	public String getcMainProducts() {
		return cMainProducts;
	}
	public void setcMainProducts(String cMainProducts) {
		this.cMainProducts = cMainProducts;
	}
	@Column(length=500)
	public String getcReason() {
		return cReason;
	}
	public void setcReason(String cReason) {
		this.cReason = cReason;
	}
	@Column(length=500)
	public String getcCompare() {
		return cCompare;
	}
	public void setcCompare(String cCompare) {
		this.cCompare = cCompare;
	}
	@Column(length = 20)
	public String getcOperator() {
		return cOperator;
	}

	public void setcOperator(String cOperator) {
		this.cOperator = cOperator;
	}
	@OneToMany(mappedBy="outcome",cascade={CascadeType.PERSIST,CascadeType.REMOVE})
	public Set<Attachment> getAttachments() {
		return attachments;
	}
	public void setAttachments(Set<Attachment> attachments) {
		this.attachments = attachments;
	}
	@OneToOne
	@JoinColumn(name="project_id")
	public ProjectInfo getProject() {
		return project;
	}
	public void setProject(ProjectInfo project) {
		this.project = project;
	}
	

}
