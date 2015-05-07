package merchants.service.inter.canvassGroup;

import java.util.List;

import merchants.entity.canvassGroup.Pro_Enclosed;
import merchants.entity.projectInformation.ProjectInfo;
import merchants.service.base.DAO;

public interface EnclosedService extends DAO<Pro_Enclosed> {
	
	public List<Pro_Enclosed> listProEnclosed(Integer itype,ProjectInfo project); 

}
