package merchants.entity.canvassGroup;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import merchants.entity.projectInformation.ProjectInfo;

//项目小组
@Entity
public class ProjectGroup implements Serializable {
	private static final long serialVersionUID = -1834561291723204039L;
	// id
	private Integer id;
	// 项目小组名称
	private String cGroupName;
	// 项目小组所属人员状态（有临时 和 正式之分），用一个集合来存放
	private List<HRPerson> persons = new ArrayList<HRPerson>();
	// 项目小组所属项目
	private List<ProjectInfo> projects = new ArrayList<ProjectInfo>();
  
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(length = 20, nullable = false)
	public String getcGroupName() {
		return cGroupName;
	}

	public void setcGroupName(String cGroupName) {
		this.cGroupName = cGroupName;
	}

	// 有问题
	@OneToMany(mappedBy = "projectGroup", cascade = CascadeType.PERSIST)
	public List<HRPerson> getPersons() {
		return persons;
	}

	public void setPersons(List<HRPerson> persons) {
		this.persons = persons;
	}
	@OneToMany(mappedBy = "projectGroup", cascade = {CascadeType.PERSIST})
	public List<ProjectInfo> getProjects() {
		return projects;
	}

	public void setProjects(List<ProjectInfo> projects) {
		this.projects = projects;
	}
	
 
}
