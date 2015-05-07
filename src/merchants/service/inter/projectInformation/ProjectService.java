package merchants.service.inter.projectInformation;

import java.util.List;

import merchants.entity.canvassGroup.ProjectGroup;
import merchants.entity.projectInformation.ProjectInfo;
import merchants.service.base.DAO;

public interface ProjectService extends DAO<ProjectInfo> {
	/*
	 * 根据项目编号查询意向项目信息
	 */
public ProjectInfo getProjectBycProCode(String cProCode);
/*
 * 根据项目名称查询意向项目信息
 */
public ProjectInfo getProjectBycProName(String cProName);
/**
 * @param 项目编码
 * @return 项目基本信息，包括项目名称，项目分类，入园目的，投资规模，主要业务，商业模式
 */
public String getProjectInformation(String cProCode);
/**
 * @param 项目id号
 * @return 修改是否成功*/
public boolean editcState(Integer projectId,Integer state);
public boolean editIsEnd(Integer projectId,Integer state);
public boolean editIverfy(Integer projectId,Integer Iverfy);
public List<ProjectInfo> getProjectsByProjectGroup(ProjectGroup projectGroup);
public boolean checkByProCode(String cProCode);
public boolean checkByProName(String cProName);
public List<ProjectInfo> findByGroup(Integer groupId);
public List<ProjectInfo> findProject(Integer groupId,Integer cStatus,Integer iVerify);
}
