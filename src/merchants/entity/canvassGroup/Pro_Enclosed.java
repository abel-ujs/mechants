package merchants.entity.canvassGroup;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import merchants.entity.projectInformation.ProjectInfo;

@Entity
public class Pro_Enclosed implements Serializable {

	/**
	 * 项目附件表
	 */
	private static final long serialVersionUID = 7953629676456886612L;
	@Id @GeneratedValue
	private Integer id;
	
	/*文档名称*/
	private String cdocName;
	
	/*上传文件的保存名*/
	private String storageFilename;
	/*上传时间*/
	private Date uploadTime = new Date() ;
	/*文件存储路径*/
	private String path;
	/*文件说明*/
	private String introduction;
	/*文档标志
	 * 0.原始文档
	 * 1.签约文档
	 * 2.落户文档
	 * 3.增资文档
	 * 4.达产达效文档
	 * 5.洽谈文档
	 * 6.其他文档*/
	private Integer itype;
	
	/*项目编码*/
	@ManyToOne(cascade=CascadeType.REFRESH)
	@JoinColumn(name="project_id")
	private ProjectInfo project;
	
	
	public Integer getItype() {
		return itype;
	}
	public void setItype(Integer itype) {
		this.itype = itype;
	}
	public ProjectInfo getProject() {
		return project;
	}
	public void setProject(ProjectInfo project) {
		this.project = project;
	}
	
	
	@Column(length=4)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	@Column(length=200)
	public String getCdocName() {
		return cdocName;
	}
	public void setCdocName(String cdocName) {
		this.cdocName = cdocName;
	}
	public String getStorageFilename() {
		return storageFilename;
	}
	public void setStorageFilename(String storageFilename) {
		this.storageFilename = storageFilename;
	}
	@Temporal(TemporalType.DATE)
	public Date getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	
}
