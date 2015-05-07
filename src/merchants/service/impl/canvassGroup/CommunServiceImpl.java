package merchants.service.impl.canvassGroup;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import merchants.entity.canvassGroup.Pro_Commun;
import merchants.entity.canvassGroup.ProjectGroup;
import merchants.entity.projectInformation.ProjectInfo;
import merchants.service.base.DaoSupport;
import merchants.service.inter.canvassGroup.CommunService;

import org.springframework.stereotype.Service;

@Service
public class CommunServiceImpl extends DaoSupport<Pro_Commun> implements  CommunService{
	public List<ProjectGroup> findGroup() {
		String findSql = "select o from ProjectGroup o";
		Query query = em.createQuery(findSql);
		@SuppressWarnings("unchecked")
		List<ProjectGroup> group = query.getResultList();
		return group;
	}

	@SuppressWarnings("unchecked")
	public List<Pro_Commun> findSaveCommun() {
		String sqlProject = "select o from ProjectInfo o where o.cState=?1 ";
		Query qrp = em.createQuery(sqlProject);
		qrp.setParameter(1, 1);
		List<ProjectInfo> project = qrp.getResultList();
		List<Pro_Commun> listCommun =new ArrayList<Pro_Commun>(); 
		for(ProjectInfo p:project){
			String sqlCommun="select o from Pro_Commun o where o.project=?1";
			Query qrc=em.createQuery(sqlCommun);
			qrc.setParameter(1, p);
			List<Pro_Commun> c =qrc.getResultList();
			for(Pro_Commun commun:c){
				listCommun.add(commun);
			}
		}
		return listCommun;
	}
	
}
