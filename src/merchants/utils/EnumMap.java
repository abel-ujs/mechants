package merchants.utils;

import java.util.HashMap;
import java.util.Map;

 public class EnumMap {
	private Map<Integer, String> cCategery;
	
	private Map<Integer, String> iObjective;
	
	private Map<Integer, String> pState;

	public Map<Integer, String> getpState() {
		return pState;
	}

	public void setpState(Map<Integer, String> pState) {
		this.pState = pState;
	}

	public Map<Integer, String> getcCategery() {
		return cCategery;
	}

	public void setcCategery(Map<Integer, String> cCategery) {
		this.cCategery = cCategery;
	}

	public Map<Integer, String> getiObjective() {
		return iObjective;
	}

	public void setiObjective(Map<Integer, String> iObjective) {
		this.iObjective = iObjective;
	}

	public EnumMap() {
		// 项目类别
		this.cCategery = new HashMap<Integer,String>();
		
		
		this.cCategery.put(0, "平台类项目");
		this.cCategery.put(1, "产业行项目");
		this.cCategery.put(2, "现代服务业项目");

		// 入园目的
		this.iObjective = new HashMap<Integer,String>();
		
		this.iObjective.put(0, "扩大产能");
		this.iObjective.put(1, "企业搬迁");
		this.iObjective.put(2, "开发新产品");
		this.iObjective.put(3, "创业");
		
		this.pState = new HashMap<Integer,String>();
		
		this.pState.put(1, "意向项目");
		this.pState.put(2, "研判项目");
		this.pState.put(3, "洽谈项目");
		this.pState.put(4, "签约项目");
		this.pState.put(5, "落户项目");
		this.pState.put(6, "达产达效项目");
		this.pState.put(7, "增资扩股项目");
		
	}
	public String getCategory(int key)
	{
		return this.getcCategery().get(key);
	}
	
	public String getObjective(int key)
	{
		return this.getiObjective().get(key);
	}
	
	public String getState(int key)
	{
		return this.getpState().get(key);
	}
}
