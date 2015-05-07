package merchants.service.inter.canvassResource;

import merchants.entity.canvassResource.Personal;
import merchants.service.base.DAO;

public interface PersonalService extends DAO<Personal> {

	boolean checkBycPersonName(String  cPersonName);

	boolean checkBycPersonCode(String  cPersonCode);

}
