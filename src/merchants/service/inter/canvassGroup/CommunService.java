package merchants.service.inter.canvassGroup;


import java.util.List;

import merchants.entity.canvassGroup.Pro_Commun;
import merchants.entity.canvassGroup.ProjectGroup;
import merchants.service.base.DAO;


public interface CommunService extends DAO<Pro_Commun> {
	public List<ProjectGroup> findGroup();
	public List<Pro_Commun> findSaveCommun();
}
