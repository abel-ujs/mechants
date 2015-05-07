package merchants.service.impl.canvassGroup;


import java.util.List;

import javax.persistence.Query;

import merchants.entity.canvassGroup.Pro_Negotiate;
import merchants.entity.projectInformation.ProjectInfo;
import merchants.service.base.DaoSupport;
import merchants.service.inter.canvassGroup.NegotiateService;

import org.springframework.stereotype.Service;

@Service
public class NegotiateServiceImpl extends DaoSupport<Pro_Negotiate> implements
		NegotiateService {

	public String getProNegotiate(int projectID) {
		String sql = "select datetime,cng_Address,cng_Person,cng_Content,cng_Require,cng_Analysis,cng_Result,cng_Advice,cng_Problem,cng_Works,cng_Element,csummarize,coperator,cdate from Pro_Negotiate o where o.project_id=?1";
		Query qr = em.createNativeQuery(sql);
		qr.setParameter(1, projectID);
		@SuppressWarnings("unchecked")
		List<Object> list = qr.getResultList();
		String sql2 = "select cGroupName from ProjectGroup,ProjectInfo where ProjectGroup.id = projectGroup_id and ProjectInfo.id=?1";
		Query qr2 = em.createNativeQuery(sql2);
		qr2.setParameter(1, projectID);
		String groupname = qr2.getSingleResult().toString();
		StringBuffer sb = new StringBuffer();
		int i = 1;
		for (Object obj : list) {
			Object[] objs = (Object[]) obj;
			sb.append("第" + i + "次洽谈记录" + "\n");
			if (i == 1) {
				if (objs[0] != null)
					sb.append("一.洽谈时间 " + objs[0].toString());
				if (objs[1] != null)
					sb.append(" 地点" + objs[1].toString());
				if (objs[2] != null)
					sb.append(" 洽谈人" + objs[2].toString() + "\n");
				if (objs[3] != null)
					sb.append("二·洽谈内容：" + objs[3].toString() + "\n");
				if (objs[4] != null)
					sb.append("三.项目方提出的要求：" + objs[4].toString() + "\n");
				if (objs[5] != null)
					sb.append("四.项目分析内容：" + objs[5].toString() + "\n");
				if (objs[6] != null)
					sb.append("五.项目分析结果：" + objs[6].toString() + "\n");
				if (objs[7] != null)
					sb.append("六.项目引进建议：" + objs[7].toString() + "\n");
				if (objs[8] != null)
					sb.append("七.需解决的问题：" + objs[8].toString() + "\n");
				if (objs[9] != null)
					sb.append("八.下一步工作计划：" + objs[9].toString() + "\n");
				if (objs[10] != null)
					sb.append("九.吸引企业入驻的原因：" + objs[10].toString() + "\n");
				if (objs[11] != null)
					sb.append("十。本次招商小结" + objs[11].toString() + "\n");
				sb.append("招商小组："+groupname+"  登记人："+objs[12].toString()+"  登记时间："+objs[13].toString()+"\n");
				i++;
			}
			else
			{
				sb.append("第" + i + "次洽谈记录" + "\n");
				if (objs[0] != null)
					sb.append("洽谈时间 " + objs[0].toString());
				if (objs[1] != null)
					sb.append(" 地点" + objs[1].toString());
				if (objs[2] != null)
					sb.append(" 洽谈人" + objs[2].toString() + "\n");
				if (objs[3] != null)
					sb.append("洽谈内容：" + objs[3].toString() + "\n");
				if (objs[4] != null)
					sb.append("项目方提出的要求：" + objs[4].toString() + "\n");
				if (objs[8] != null)
					sb.append("需解决的问题：" + objs[8].toString() + "\n");
				if (objs[9] != null)
					sb.append("下一步工作计划：" + objs[9].toString() + "\n");
				if (objs[11] != null)
					sb.append("本次招商小结" + objs[11].toString() + "\n");
				sb.append("招商小组："+groupname+"  登记人："+objs[12].toString()+"  登记时间："+objs[13].toString()+"\n");
				i++;
			}
		}
		return sb.toString();

	}


	public Integer deleteAllByProject(ProjectInfo project) {
		String sql="delete from Pro_Negotiate o where o.project=?1 ";
		Query qr = em.createQuery(sql);
		qr.setParameter(1, project);
		return qr.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<Pro_Negotiate> findOrderById(Integer projectId) {
		String sql="select * from Pro_Negotiate o where o.project_id = ?1 order by id";
		Query qr = em.createNativeQuery(sql);
		qr.setParameter(1, projectId);
		List<Pro_Negotiate> list = qr.getResultList();
		return list;
	}

}
