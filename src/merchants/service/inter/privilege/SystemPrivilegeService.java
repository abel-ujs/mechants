package merchants.service.inter.privilege;

import java.util.List;

import merchants.entity.privilege.SystemPrivilege;
import merchants.service.base.DAO;

public interface SystemPrivilegeService extends DAO<SystemPrivilege>{
	
	/**
	 * 批量加入权限
	 * @param privileges
	 */
	public void batchSave(List<SystemPrivilege> privileges);
}
