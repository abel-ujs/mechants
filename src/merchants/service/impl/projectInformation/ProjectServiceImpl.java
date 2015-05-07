package merchants.service.impl.projectInformation;

import java.util.List;

import javax.persistence.Query;

import merchants.entity.canvassGroup.ProjectGroup;
import merchants.entity.canvassResource.Personal;
import merchants.entity.projectInformation.ProjectInfo;
import merchants.service.base.DaoSupport;
import merchants.service.inter.projectInformation.ProjectService;

import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl extends DaoSupport<ProjectInfo> implements
		ProjectService {

	public ProjectInfo getProjectBycProCode(String cProCode) {
		Query query = em
				.createQuery("select o from ProjectInfo o where o.cProCode=?1");
		query.setParameter(1, cProCode);
		ProjectInfo p = (ProjectInfo) query.getSingleResult();
		return p;
	}

	public ProjectInfo getProjectBycProName(String cProName) {
		Query query = em
				.createQuery("select o from ProjectInfo o where o.cProName=?1");
		query.setParameter(1, cProName);
		ProjectInfo p = null;
		try {
			p = (ProjectInfo) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return p;
	}

	public String getProjectInformation(String cProCode) {
		Query query = em
				.createQuery("select o from ProjectInfo o where o.cProCode=?1");
		query.setParameter(1, cProCode);
		ProjectInfo p = (ProjectInfo) query.getSingleResult();
		StringBuffer sb = new StringBuffer("");
		if (p != null) {
			sb.append(p.getcProName()).append(",");
			// 项目类别（0：平台类项目 1：产业性项目 2：现代服务业项目
			switch (p.getcCategory()) {
			case 0:
				sb.append("平台类项目").append(",");
				break;
			case 1:
				sb.append("产业性项目").append(",");
				break;
			case 2:

				sb.append("现代服务业项目").append(",");
				break;
			default:
				sb.append("未指定").append(",");
				break;

			}
			// 入住园区目的（0-扩大产能、1-企业搬迁、2-开发新产品、3-创业）
			switch (p.getiObjective()) {
			case 0:
				sb.append("扩大产能").append(",");
				break;
			case 1:
				sb.append("企业搬迁").append(",");
				break;
			case 2:
				;
				sb.append("开发新产品").append(",");
				break;
			case 3:
				;
				sb.append("创业").append(",");
				break;
			default:
				sb.append("未指定").append(",");
				break;

			}
			sb.append(p.getdInvestment()).append("元").append(",");
			sb.append(p.getcMainBuessiness()).append(",");
			sb.append(p.getcBusinessModel());
		}

		return sb.toString();
	}

	public List<ProjectInfo> getProjectsByProjectGroup(ProjectGroup projectGroup) {
		Query query = em
				.createQuery("select o from ProjectInfo o where o.projectGroup=?1");
		query.setParameter(1, projectGroup);
		List<ProjectInfo> list = query.getResultList();
		return list;
	}

	public boolean checkByProCode(String cProCode) {
		Query query = em
				.createQuery("select o from ProjectInfo o where o.cProCode=?1");
		query.setParameter(1, cProCode);
		// 此方法有问题不好用的
		// Personal p = (Personal) query.getSingleResult();
		if (query.getResultList() != null && query.getResultList().size() > 0) {
			return true;
		}
		return false;

	}

	public boolean checkByProName(String cProName) {
		Query query = em
				.createQuery("select o from ProjectInfo o where o.cProName=?1");
		query.setParameter(1, cProName);
		if (query.getResultList() != null && query.getResultList().size() > 0) {
			return true;
		}
		return false;
	}

	// HL
	public boolean editcState(Integer projectId, Integer state) {
		String sql = "update ProjectInfo  set cState = ?1 where id=?2 ";
		Query query = em.createNativeQuery(sql);
		query.setParameter(1, state);
		query.setParameter(2, projectId);
		return query.executeUpdate() > 0;
	}

	/* 研判是否引进信息修改 */
	public boolean editIsEnd(Integer projectId, Integer state) {
		String sql = "update ProjectInfo set iEnd = ?1 where id=?2";
		Query query = em.createNativeQuery(sql);
		query.setParameter(1, state);
		query.setParameter(2, projectId);
		return query.executeUpdate() > 0;
	}

	/* 提交项目信息 */
	public boolean editIverfy(Integer projectId,Integer Iverfy) {
		String sql = "update ProjectInfo set iVerify=?1 where id=?2";
		Query query = em.createNativeQuery(sql);
		query.setParameter(1, Iverfy);
		query.setParameter(2, projectId);
		return query.executeUpdate() > 0;
	}

	public List<ProjectInfo> findByGroup(Integer groupId) {
		String sql = "select o from ProjectInfo o where o.projectGroup.id =?1";
		Query query = em.createQuery(sql);
		query.setParameter(1, groupId);
		List<ProjectInfo> projectInfo = query.getResultList();
		return projectInfo;
	}
	public List<ProjectInfo> findProject(Integer groupId, Integer cState,
			Integer iVerify) {
		String sql = "select o from ProjectInfo o where o.projectGroup.id =?1 and o.cState=?2 and o.iVerify=?3";
		Query query = em.createQuery(sql);
		query.setParameter(1, groupId);
		query.setParameter(2, cState);
		query.setParameter(3, iVerify);
		List<ProjectInfo> projectInfo = query.getResultList();
		return projectInfo;
	}

}
