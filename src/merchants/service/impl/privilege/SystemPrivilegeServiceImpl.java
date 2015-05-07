package merchants.service.impl.privilege;

import java.util.List;

import merchants.entity.privilege.SystemPrivilege;
import merchants.service.base.DaoSupport;
import merchants.service.inter.privilege.SystemPrivilegeService;

import org.springframework.stereotype.Service;

@Service
public class SystemPrivilegeServiceImpl extends DaoSupport<SystemPrivilege> implements SystemPrivilegeService{
	
	/**
	 * 批量加入权限
	 */
	public void batchSave(List<SystemPrivilege> privileges) {
		for(SystemPrivilege p : privileges){
			save(p);
		}
	}

}
