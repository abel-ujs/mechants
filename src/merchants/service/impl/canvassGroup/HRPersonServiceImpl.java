package merchants.service.impl.canvassGroup;

import java.util.List;

import javax.persistence.Query;

import merchants.entity.canvassGroup.HRPerson;
import merchants.service.base.DaoSupport;
import merchants.service.inter.canvassGroup.HRPersonService;

import org.springframework.stereotype.Service;

@Service
public class HRPersonServiceImpl extends DaoSupport<HRPerson> implements HRPersonService{
	@SuppressWarnings("unchecked")
	public HRPerson find(String username, String password) {
		Query query = em.createQuery("select o from HRPerson o where o.cUserName=?1 and o.cPassword=?2");
		query.setParameter(1, username).setParameter(2, password);
		List<HRPerson> users = query.getResultList();
		if(users.size() > 0){
			return users.get(0);
		}
		return null;
	}

}
