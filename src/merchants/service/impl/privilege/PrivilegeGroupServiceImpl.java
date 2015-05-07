package merchants.service.impl.privilege;

import java.util.List;
import java.util.UUID;

import javax.persistence.Query;

import org.springframework.stereotype.Service;

import merchants.entity.privilege.PrivilegeGroup;
import merchants.service.base.DaoSupport;
import merchants.service.inter.privilege.PrivilegeGroupService;

@Service
public class PrivilegeGroupServiceImpl extends DaoSupport<PrivilegeGroup> implements PrivilegeGroupService{
	@Override
	public void save(PrivilegeGroup entity) {
		entity.setGroupid(UUID.randomUUID().toString());
		super.save(entity);
	}

	@SuppressWarnings("unchecked")
	public List<PrivilegeGroup> getGroupByGroupids(String... groupids) {
		if(groupids != null && groupids.length > 0){
			StringBuilder whereStr = new StringBuilder();
			for(int i=0;i<groupids.length;i++){
				whereStr.append("?").append(i+1).append(",");
			}
			whereStr.deleteCharAt(whereStr.length()-1);
			Query query = em.createQuery("select o from PrivilegeGroup o where o.groupid in(" + whereStr.toString() + ")");//?1,?2
			for(int i=0;i<groupids.length;i++){
				query.setParameter(i+1, groupids[i]);
			}
			return query.getResultList();
		}
		return null;
	}
}
