package merchants.service.impl.canvassResource;

import javax.persistence.Query;

import merchants.entity.canvassResource.Investor;
import merchants.service.base.DaoSupport;
import merchants.service.inter.canvassResource.InvestorService;

import org.springframework.stereotype.Service;

@Service
public class InvestorServiceImpl extends DaoSupport<Investor> implements
		InvestorService {

	public boolean checkBycInvName(String cInvName) {
		Query query = em
				.createQuery("select o from Investor o where o.cInvName=?1");
		query.setParameter(1, cInvName);

		if (query.getResultList() != null && query.getResultList().size() > 0) {
			return true;
		}
		return false;
	}
}
