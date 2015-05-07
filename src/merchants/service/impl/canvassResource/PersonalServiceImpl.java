package merchants.service.impl.canvassResource;

import java.io.Serializable;

import javax.persistence.Query;

import org.springframework.stereotype.Service;

import merchants.entity.canvassResource.Personal;
import merchants.entity.projectInformation.ProjectInfo;
import merchants.service.base.DaoSupport;
import merchants.service.inter.canvassResource.PersonalService;

@Service
public class PersonalServiceImpl extends DaoSupport<Personal> implements
		PersonalService {
	@Override
	public void delete(Serializable... entityids) {
		for (Serializable entityid : entityids) {
			Personal personal = find(entityid);
			personal.setiValid(false);
			update(personal);
		}
	}

	public boolean checkBycPersonName(String cPersonName) {
		Query query = em
				.createQuery("select o from Personal o where o.cPersonName=?1");
		query.setParameter(1, cPersonName);

		if (query.getResultList() != null && query.getResultList().size() > 0) {
			return true;
		}
		return false;
	}

	public boolean checkBycPersonCode(String cPersonCode) {
		Query query = em
				.createQuery("select o from Personal o where o.cPersonCode=?1");
		query.setParameter(1, cPersonCode);

		if (query.getResultList() != null && query.getResultList().size() > 0) {
			return true;
		}
		return false;
	}

}
