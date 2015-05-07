package merchants.entity;

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

import merchants.entity.canvassGroup.Outcome;

/**
 * 用户所拥有的附件实体,与用户存在多对以关系
 * @author naruto
 *
 */
@Entity
public class Attachment {
	private Integer id;
	/*用户上传文件的名字*/
	private String displayFilename;
	/*上传文件的保存名*/
	private String storageFilename;
	/*上传时间*/
	private Date uploadTime = new Date();
	/*文件存储路径*/
	private String path;
	/*文件说明*/
	private String introduction;
	/*所属达产达效信息*/
     private Outcome outcome;
	
	@Id @GeneratedValue
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(length=50)
	public String getDisplayFilename() {
		return displayFilename;
	}
	public void setDisplayFilename(String displayFilename) {
		this.displayFilename = displayFilename;
	}
	@Column(length=50)
	public String getStorageFilename() {
		return storageFilename;
	}
	public void setStorageFilename(String storageFilename) {
		this.storageFilename = storageFilename;
	}
	@Temporal(TemporalType.DATE) @Column(nullable=false)
	public Date getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}
	@Column(length=100)
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	@Column(length=100)
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	@ManyToOne(cascade=CascadeType.REFRESH)
    @JoinColumn(name="outcome_id") 
	public Outcome getOutcome() {
		return outcome;
	}
	public void setOutcome(Outcome outcome) {
		this.outcome = outcome;
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
		Attachment other = (Attachment) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
}
