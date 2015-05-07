package merchants.service.inter.canvassResource;

import java.util.List;

import merchants.entity.canvassResource.Customer;
import merchants.service.base.DAO;

public interface CustomerService extends DAO<Customer> {

	boolean checkBycCusName(String cCusName);

	boolean checkBycCusCode(String  cCusCode);
	 

}
