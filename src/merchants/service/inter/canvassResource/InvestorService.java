package merchants.service.inter.canvassResource;

import merchants.entity.canvassResource.Investor;
import merchants.service.base.DAO;

public interface InvestorService extends DAO<Investor> {

	boolean checkBycInvName(String cInvName);

}
