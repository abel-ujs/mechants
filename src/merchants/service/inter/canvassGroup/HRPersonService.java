package merchants.service.inter.canvassGroup;

import merchants.entity.canvassGroup.HRPerson;
import merchants.service.base.DAO;

public interface HRPersonService extends DAO<HRPerson> {
	/**
	 * 根据用户名和密码查找用户
	 * @param username
	 * @param password
	 * @return
	 */
	public HRPerson find(String username, String password);
}
