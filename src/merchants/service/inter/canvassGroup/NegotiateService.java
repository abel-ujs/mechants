package merchants.service.inter.canvassGroup;

import java.util.List;

import merchants.entity.canvassGroup.Pro_Negotiate;
import merchants.entity.projectInformation.ProjectInfo;
import merchants.service.base.DAO;

public interface NegotiateService extends DAO<Pro_Negotiate> {
	
	public String getProNegotiate(int projectID);
	public Integer deleteAllByProject(ProjectInfo project);
	public List<Pro_Negotiate> findOrderById(Integer projectId);
}
