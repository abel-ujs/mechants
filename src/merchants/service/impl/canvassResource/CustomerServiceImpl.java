package merchants.service.impl.canvassResource;

import java.io.Serializable;

import javax.persistence.Query;

import merchants.entity.canvassResource.Customer;
import merchants.service.base.DaoSupport;
import merchants.service.inter.canvassResource.CustomerService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomerServiceImpl extends DaoSupport<Customer> implements
		CustomerService {
	@Override
	public void delete(Serializable... entityids) {
		for (Serializable entityid : entityids) {
			Customer customer = find(entityid);
			customer.setiValid(false);
			update(customer);
		}
	}

	public boolean checkBycCusCode(String cCusCode) {
		// TODO Auto-generated method stub
		Query query = em
				.createQuery("select o from Customer o where o.cCusCode=?1");
		query.setParameter(1, cCusCode);
		if (query.getResultList() != null && query.getResultList().size() > 0) {
			return true;
		}
		return false;
	}

	public boolean checkBycCusName(String cCusName) {
		// TODO Auto-generated method stub
		Query query = em
				.createQuery("select o from Customer o where o.cCusName=?1");
		query.setParameter(1, cCusName);

		if (query.getResultList() != null && query.getResultList().size() > 0) {
			return true;
		}
		return false;
	}

}
