package merchants.web.action.canvassResource;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import merchants.entity.PageView;
import merchants.entity.QueryResult;
import merchants.entity.canvassGroup.HRPerson;
import merchants.entity.canvassResource.Customer;
import merchants.entity.canvassResource.Industry;
import merchants.entity.canvassResource.Investor;
import merchants.entity.canvassResource.Nature;
import merchants.entity.canvassResource.Personal;
import merchants.service.inter.canvassResource.CustomerService;
import merchants.service.inter.canvassResource.IndustryService;
import merchants.service.inter.canvassResource.InvestorService;
import merchants.service.inter.canvassResource.NatureService;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

/**
 * 处理介绍人有关请求
 */
@Controller
@Scope("prototype")
public class InvestorAction {

	@Resource
	InvestorService investorService;
	@Resource
	CustomerService customerService;
	@Resource
	IndustryService industryService;
	@Resource
	NatureService natureService;
	private Investor investor;
	// 实现多行记录的删除
	private Integer[] idList;
	// 当前页码
	private Integer page = 1;
	// 用于接收查询参数并用于回显以完成翻页请求

	// 用于接收查询参数并用于回显以完成翻页请求
	public Integer rows = 10;
	private String searchKey;
	private String searchValue;

	public Investor getInvestor() {
		return investor;
	}

	public void setInvestor(Investor investor) {
		this.investor = investor;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer[] getIdList() {
		return idList;
	}

	public void setIdList(Integer[] idList) {
		this.idList = idList;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	/**
	 * 功能界面
	 */
	public String display2() {

		return "investorDisplay";
	}

	/**
	 * 为了给datagrid准备分页数据
	 */
	public void display() {
		StringBuffer where = new StringBuffer();
		List<Object> parameters = new ArrayList<Object>();
		if (searchValue != null && !"".equals(searchValue) && searchKey != null
				&& !"".equals(searchKey)) {
			where.append("   o.cInvName" + " like ?" + (parameters.size() + 1));
			parameters.add("%" + searchValue + "%");
			where.append(" or o.cInvLPerson=?" + (parameters.size() + 1));
			parameters.add(searchValue);
			where.append(" or o.cInvPerson=?" + (parameters.size() + 1));
			parameters.add(searchValue);
		}
		List<Map> records = new ArrayList<Map>();
		Integer total;
		Map<String, Object> map = new HashMap<String, Object>();
		QueryResult<Investor> qr = investorService.getScrollData((page - 1)
				* rows, rows, where.toString(), parameters.toArray());
		total = Integer.parseInt(qr.getTotalrecord() + "");
		for (int i = 0; i < qr.getResultlist().size(); i++) {
			Investor p = qr.getResultlist().get(i);
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("id", p.getId());
			m.put("investor.cInvName", p.getcInvName());
			m.put("investor.cInvAddress", p.getcInvAddress());
			m.put("investor.cInvWebSite", p.getcInvWebSite());
			m.put("investor.nature", p.getNature().getcNatName());
			m.put("investor.industry", p.getIndustry().getcInduName());
			m.put("investor.cInvLPerson", p.getcInvLPerson());
			m.put("investor.cInvLPhone", p.getcInvLPhone());
			m.put("investor.cInvLFax", p.getcInvLFax());
			m.put("investor.cInvLHand", p.getcInvLHand());
			m.put("investor.cInvLEmail", p.getcInvLEmail());
			m.put("investor.cInvPerson", p.getcInvPerson());
			m.put("investor.dCapital", p.getdCapital());
			m.put("investor.dOValue", p.getdOValue());
			m.put("investor.cIndustry", p.getcIndustry());
			m.put("investor.cLevel", p.getcLevel());
			m.put("investor.cMainProducts", p.getcMainProducts());
			m.put("investor.cCompeProducts", p.getcCompeProducts());
			m.put("investor.vcMarketShare", p.getcMarketShare());
			m.put("investor.cInformation", p.getcInformation());
			m.put("investor.cOperator", p.getcOperator());
			records.add(m);
		}
		map.put("total", total);
		map.put("rows", records);
		JSONObject resultObj = JSONObject.fromObject(map);
		try {
			ServletActionContext.getResponse().setCharacterEncoding("utf-8");
			ServletActionContext.getResponse().getWriter().print(resultObj);
			ServletActionContext.getResponse().flushBuffer();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 加载集合数据
	 */
	public void loadNatureData() {
		StringBuffer sb = new StringBuffer("[");
		List<Nature> list = natureService.getScrollData().getResultlist();
		for (Nature entity : list) {
			sb.append("{\"id\":" + entity.getId());
			sb.append(",\"text\":" + "\"" + entity.getcNatName() + "\"},");
		}
		sb.deleteCharAt(sb.lastIndexOf(","));
		sb.append("]");
		try {
			ServletActionContext.getResponse().setCharacterEncoding("utf-8");
			ServletActionContext.getResponse().getWriter().print(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 保存投资方入库
	 */
	public void save() {
		Map<String, String> map = new HashMap<String, String>();
		HRPerson user = (HRPerson) ServletActionContext.getRequest()
				.getSession().getAttribute("user");
		investor.setcOperator(user.getcPsnName());

		try {
			investor.setIndustry(industryService.find(Integer
					.parseInt(ServletActionContext.getRequest().getParameter(
							"industry"))));
			investor.setNature(natureService.find(Integer
					.parseInt(ServletActionContext.getRequest().getParameter(
							"nature"))));
			investorService.save(investor);
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
			System.out.println("投资方保存失败");
			map.put("errorMsg", "企业性质或者行业信息没有选定，请选择");
			try {
				ServletActionContext.getResponse().setCharacterEncoding("gbk");
				ServletActionContext.getResponse().getWriter().print(
						JSONObject.fromObject(map));
			} catch (IOException ee) {
				ee.printStackTrace();
			}
			return;

		}
		map.put("errorMsg", "");
		try {
			ServletActionContext.getResponse().setCharacterEncoding("gbk");
			ServletActionContext.getResponse().getWriter().print(
					JSONObject.fromObject(map));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除
	 */
	public void delete() {
		Map<String, String> map = new HashMap<String, String>();
		try {
			for (Integer id : idList) {
				investorService.delete(id);
			}
			map.put("status", "success");

		} catch (Exception e) {
			map.put("status", "fail");
			map.put("msg", "有外键约束,投资方删除失败!");
		}
		try {
			ServletActionContext.getResponse().setCharacterEncoding("utf-8");
			ServletActionContext.getResponse().getWriter().print(
					JSONObject.fromObject(map));
		} catch (IOException e) {
			System.out.println("删除数据异常");
		}

	}

	/**
	 * 根据id得到详细信息
	 */
	public String getInvestorInfo() {
		Integer id = Integer.parseInt(ServletActionContext.getRequest()
				.getParameter("id"));
		Investor investor = investorService.find(id);
		ActionContext.getContext().put("investor", investor);
		return "investorInfo";
	}

	/**
	 * 转到投资方修改页面
	 */
	public String updateUI() {
		List<Industry> industries = industryService.getScrollData()
				.getResultlist();
		List<Nature> natures = natureService.getScrollData().getResultlist();
		Integer id = Integer.parseInt(ServletActionContext.getRequest()
				.getParameter("id"));
		Investor investor = investorService.find(id);
		ActionContext.getContext().put("industries", industries);
		ActionContext.getContext().put("natures", natures);
		ActionContext.getContext().put("investor", investor);
		return "updateUI";
	}

	/**
	 * 修改投资方信息
	 */
	public void edit() {
		Map<String, String> map = new HashMap<String, String>();
		Integer id = Integer.parseInt(ServletActionContext.getRequest()
				.getParameter("id"));
		Investor newInvestor = investorService.find(id);
		newInvestor.setcInvAddress(investor.getcInvAddress());
		newInvestor.setcInvWebSite(investor.getcInvWebSite());
		newInvestor.setcInvLPerson(investor.getcInvLPerson());
		newInvestor.setcInvLPhone(investor.getcInvLPhone());
		newInvestor.setcInvLFax(investor.getcInvLFax());
		newInvestor.setcInvLHand(investor.getcInvLHand());
		newInvestor.setcInvLEmail(investor.getcInvLEmail());
		newInvestor.setcInvPerson(investor.getcInvPerson());
		newInvestor.setdCapital(investor.getdCapital());
		newInvestor.setdOValue(investor.getdOValue());
		newInvestor.setcIndustry(investor.getcIndustry());
		newInvestor.setcLevel(investor.getcLevel());
		newInvestor.setcMainProducts(investor.getcMainProducts());
		newInvestor.setcCompeProducts(investor.getcCompeProducts());
		newInvestor.setcMarketShare(investor.getcMarketShare());
		newInvestor.setcInformation(investor.getcInformation());
		newInvestor.setcCompeProducts(investor.getcCompeProducts());
		try {
			investor.setIndustry(industryService.find(Integer
					.parseInt(ServletActionContext.getRequest().getParameter(
							"industry"))));
			investor.setNature(natureService.find(Integer
					.parseInt(ServletActionContext.getRequest().getParameter(
							"nature"))));
			investorService.update(newInvestor);
			map.put("errorMsg", "");
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
			System.out.println("投资方保存失败");
			map.put("errorMsg", "企业性质或者行业信息没有选定，请选择");

		} finally {
			try {
				ServletActionContext.getResponse().setCharacterEncoding("gbk");
				ServletActionContext.getResponse().getWriter().print(
						JSONObject.fromObject(map));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 检查投资方名称是否存在 使用Ajax认证
	 */
	public void checkcInvName() {
		String ajaxMessage = null;
		if (investorService.checkBycInvName(this.investor.getcInvName())) {
			// 如果项目编码存在
			ajaxMessage = "fail";
		} else {
			ajaxMessage = "success";
		}
		try {

			ServletActionContext.getResponse().getWriter().write(ajaxMessage);
		} catch (IOException e) {
			System.out.println("Ajax投资方名称认证异常");
			e.printStackTrace();
		}

	}

	/**
	 * 导出数据为XLS格式
	 * 
	 * @param fileName
	 *            文件的名称，可以设为绝对路径，也可以设为相对路径
	 * @param content
	 *            数据的内容
	 */
	public void exportExcel(String fileName, List<Investor> content) {
		WritableWorkbook wwb;
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(fileName);
			wwb = Workbook.createWorkbook(fos);
			WritableSheet ws = wwb.createSheet("投资方列表", 10); // 创建一个工作表
			// 设置表头单元格的文字格式
			WritableFont wf = new WritableFont(WritableFont.ARIAL, 12,
					WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,
					Colour.BLUE);
			WritableCellFormat wcf = new WritableCellFormat(wf);
			wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
			wcf.setAlignment(Alignment.CENTRE);
			ws.setRowView(1, 500);
			ws.addCell(new Label(0, 0, "投资方名称", wcf));
			ws.addCell(new Label(1, 0, "地址", wcf));
			ws.addCell(new Label(2, 0, "网址", wcf));
			ws.addCell(new Label(3, 0, "所属企业性质", wcf));
			ws.addCell(new Label(4, 0, "所属行业", wcf));
			ws.addCell(new Label(5, 0, "法人", wcf));
			ws.addCell(new Label(6, 0, "固定电话", wcf));
			ws.addCell(new Label(7, 0, "传真号码", wcf));
			ws.addCell(new Label(8, 0, "手机", wcf));
			ws.addCell(new Label(9, 0, "Email", wcf));
			ws.addCell(new Label(10, 0, "联系人", wcf));
			ws.addCell(new Label(11, 0, "注册资金", wcf));
			ws.addCell(new Label(12, 0, "年产值", wcf));
			ws.addCell(new Label(13, 0, "行业地位", wcf));
			ws.addCell(new Label(14, 0, "技术水平", wcf));
			ws.addCell(new Label(15, 0, "主要产品", wcf));
			ws.addCell(new Label(16, 0, "产品竞争力", wcf));
			ws.addCell(new Label(17, 0, "市场份额", wcf));
			ws.addCell(new Label(18, 0, "产品销售对象信息", wcf));
			ws.addCell(new Label(19, 0, "建档人员", wcf));
			// 设置内容单元格样式 填充数据的内容
			WritableCellFormat contentWCF = new WritableCellFormat();
			for (int i = 0; i < content.size(); i++) {
				ws.addCell(new Label(0, i + 1, content.get(i).getcInvName(),
						contentWCF));
				ws.addCell(new Label(1, i + 1, content.get(i).getcInvAddress(),
						contentWCF));

				ws.addCell(new Label(2, i + 1, content.get(i).getcInvWebSite(),
						contentWCF));
				ws.addCell(new Label(3, i + 1, content.get(i).getNature()
						.getcNatName(), contentWCF));
				ws.addCell(new Label(4, i + 1, content.get(i).getIndustry()
						.getcInduName(), contentWCF));
				ws.addCell(new Label(5, i + 1, content.get(i).getcInvLPerson(),
						contentWCF));
				ws.addCell(new Label(6, i + 1, content.get(i).getcInvLPhone(),
						contentWCF));
				ws.addCell(new Label(7, i + 1, content.get(i).getcInvLFax(),
						contentWCF));
				ws.addCell(new Label(8, i + 1, content.get(i).getcInvLHand(),
						contentWCF));
				ws.addCell(new Label(9, i + 1, content.get(i).getcInvLEmail(),
						contentWCF));
				ws.addCell(new Label(10, i + 1, content.get(i).getcInvPerson(),
						contentWCF));
				ws.addCell(new Label(11, i + 1,
						content.get(i).getdCapital() == null ? "0" : content
								.get(i).getdCapital().toString(), contentWCF));
				ws.addCell(new Label(12, i + 1,
						content.get(i).getdOValue() == null ? "0" : content
								.get(i).getdOValue().toString(), contentWCF));
				ws.addCell(new Label(13, i + 1, content.get(i).getcIndustry(),
						contentWCF));
				ws.addCell(new Label(14, i + 1, content.get(i).getcLevel(),
						contentWCF));
				ws.addCell(new Label(15, i + 1, content.get(i)
						.getcMainProducts(), contentWCF));
				ws.addCell(new Label(16, i + 1, content.get(i)
						.getcCompeProducts(), contentWCF));
				ws.addCell(new Label(17, i + 1, content.get(i)
						.getcMarketShare(), contentWCF));
				ws.addCell(new Label(18, i + 1, content.get(i)
						.getcInformation(), contentWCF));
				ws.addCell(new Label(19, i + 1, content.get(i).getcOperator(),
						contentWCF));
			}
			wwb.write();
			wwb.close();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void excel() {
		Map<String, String> map = new HashMap<String, String>();
		String fileName = ServletActionContext.getRequest().getParameter(
				"fileName");
		String filePath = ServletActionContext.getServletContext().getRealPath(
				"\\excel")
				+ "\\" + fileName;
		List<Investor> content = investorService.getScrollData()
				.getResultlist();
		exportExcel(filePath, content);
		map.put("fileName", fileName);
		map.put("filePath", "/excel/" + fileName);
		map.put("status", "succeed");
		System.out.println("成功导出数据到Excel文件" + fileName);
		try {
			ServletActionContext.getResponse().setCharacterEncoding("gbk");
			ServletActionContext.getResponse().getWriter().print(
					JSONObject.fromObject(map));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(JSONObject.fromObject(map).toString());
	}

}
