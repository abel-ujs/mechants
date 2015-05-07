package merchants.service.impl.canvassGroup;

import java.util.List;

import javax.persistence.Query;

import merchants.entity.canvassGroup.Pro_Enclosed;
import merchants.entity.projectInformation.ProjectInfo;
import merchants.service.base.DaoSupport;
import merchants.service.inter.canvassGroup.EnclosedService;

import org.springframework.stereotype.Service;

@Service
public class EnclosedServiceImpl extends DaoSupport<Pro_Enclosed> implements
		EnclosedService {

	@SuppressWarnings("unchecked")
	public List<Pro_Enclosed> listProEnclosed(Integer itype, ProjectInfo project) {
		String sql="select o from Pro_Enclosed o where o.itype=?1 and o.project=?2";
		Query qr = em.createQuery(sql);
		qr.setParameter(1, itype);
		qr.setParameter(2, project);
		return qr.getResultList();
	}


}
