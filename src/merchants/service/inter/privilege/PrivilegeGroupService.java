package merchants.service.inter.privilege;

import java.util.List;


import merchants.entity.privilege.PrivilegeGroup;
import merchants.service.base.DAO;

public interface PrivilegeGroupService extends DAO<PrivilegeGroup> {
	
	/**
	 * 批量获取权限组
	 * @param groupids
	 * @return
	 */
	public List<PrivilegeGroup> getGroupByGroupids(String... groupids);
		
}
