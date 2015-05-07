package merchants.entity;

import java.util.List;

import merchants.utils.WebTool;

public class PageView<T> {

	private List<T> records;
	private PageIndex pageindex;
	private int totalpage = 1;
	private int totalrecords;
	private int currentpage = 1;
	private int maxresult = 12;

	public PageView(int maxresult, int currentpage) {
		this.maxresult = maxresult;
		this.currentpage = currentpage;

	}

	public List<T> getRecords() {
		return records;
	}

	public void setRecords(List<T> records) {
		this.records = records;
	}

	public PageIndex getPageindex() {
		return pageindex;
	}

	public long getTotalpage() {
		return totalpage;
	}

	public long getTotalrecords() {
		return totalrecords;
	}

	public void setTotalrecords(int totalrecords) {
		this.totalrecords = totalrecords;
		this.totalpage = totalrecords % this.maxresult == 0 ? totalrecords
				/ this.maxresult : totalrecords / this.maxresult + 1;
		this.pageindex = WebTool.getPageIndex(this.currentpage, this.totalpage);
	}

	public int getCurrentpage() {
		return currentpage;
	}

	public int getMaxresult() {
		return maxresult;
	}

	public void setMaxresult(int maxresult) {
		this.maxresult = maxresult;
	}

}
