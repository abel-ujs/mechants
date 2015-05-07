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
import merchants.entity.canvassResource.AreaClass;
import merchants.entity.canvassResource.Customer;
import merchants.entity.canvassResource.Industry;
import merchants.entity.canvassResource.Investor;
import merchants.entity.canvassResource.Personal;
import merchants.service.inter.canvassResource.AreaClassService;
import merchants.service.inter.canvassResource.CustomerService;
import merchants.service.inter.canvassResource.IndustryService;
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
public class CustomerAction {
	@Resource
	CustomerService customerService;
	@Resource
	IndustryService industryService;
	@Resource
	AreaClassService areaClassService;
	private Customer customer;
	// 实现多行记录的删除
	private Integer[] idList;
	// 当前页码
	private Integer page = 1;
	// 用于接收查询参数并用于回显以完成翻页请求
	public Integer rows = 10;
	private String searchKey;
	private String searchValue;

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
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

	public Integer[] getIdList() {
		return idList;
	}

	public void setIdList(Integer[] idList) {
		this.idList = idList;
	}

	/**
	 * 转到介绍单位列表
	 */
	public String display2() {
		return "customerDisplay";
	}

	/**
	 * 为了给datagrid准备分页数据
	 */
	public void display() {
		StringBuffer where = new StringBuffer();
		List<Object> parameters = new ArrayList<Object>();
		where.append("   o.iValid=?" + (parameters.size() + 1));
		parameters.add(true);
		if (searchValue != null && !"".equals(searchValue) && searchKey != null
				&& !"".equals(searchKey)) {
			where.append(" and o.cCusName" + " like ?"
					+ (parameters.size() + 1));
			parameters.add("%" + searchValue + "%");
			where.append(" or o.cCusPerson=?" + (parameters.size() + 1));
			parameters.add(searchValue);
			where.append(" or o.cCusLPerson=?" + (parameters.size() + 1));
			parameters.add(searchValue);
		}
		List<Map> records = new ArrayList<Map>();
		Map<String, Object> map = new HashMap<String, Object>();
		QueryResult<Customer> qr = customerService.getScrollData((page - 1)
				* rows, rows, where.toString(), parameters.toArray());
		Integer total = Integer.parseInt(qr.getTotalrecord() + "");
		for (int i = 0; i < qr.getResultlist().size(); i++) {
			Customer p = qr.getResultlist().get(i);
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("id", p.getId());
			m.put("customer.cCusName", p.getcCusName());
			m.put("customer.cCusAddress", p.getcCusAddress());
			m.put("customer.industry", p.getIndustry().getcInduName());
			m.put("customer.cCusPerson", p.getcCusPerson());
			m.put("customer.cCusPhone", p.getcCusPhone());
			m.put("customer.cCusFax", p.getcCusFax());
			m.put("customer.cCusEmail", p.getcCusEmail());
			m.put("customer.cCusQQ", p.getcCusQQ());
			m.put("customer.cCusHand", p.getcCusHand());
			m.put("customer.cCusLPerson", p.getcCusLPerson());
			m.put("customer.cMemo", p.getcMemo());
			m.put("customer.cCusBrief", p.getcCusBrief());
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
	 * 保存介绍单位入库
	 */

	public void save() {
		Map<String, String> map = new HashMap<String, String>();
		HRPerson user = (HRPerson) ServletActionContext.getRequest()
				.getSession().getAttribute("user");
		customer.setcCreatePerson(user.getcPsnName());
		try {
			Integer industryId = Integer.parseInt(ServletActionContext
					.getRequest().getParameter("industry"));
			customer.setIndustry(industryService.find(industryId));
			customerService.save(customer);
			map.put("errorMsg", "");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("errorMsg", ",所属行业为必选项，介绍单位保存失败!");
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
	 * 修改介绍单位信息
	 */
	public void edit() {
		Map<String, String> map = new HashMap<String, String>();
		Integer id = Integer.parseInt(ServletActionContext.getRequest()
				.getParameter("id"));
		Customer newCustomer = customerService.find(id);
		newCustomer.setcCusAddress(customer.getcCusAddress());
		newCustomer.setcCusPerson(customer.getcCusPerson());
		newCustomer.setcCusPhone(customer.getcCusPhone());
		newCustomer.setcCusFax(customer.getcCusFax());
		newCustomer.setcCusEmail(customer.getcCusEmail());
		newCustomer.setcCusQQ(customer.getcCusQQ());
		newCustomer.setcCusHand(customer.getcCusHand());
		newCustomer.setcCusLPerson(customer.getcCusLPerson());
		newCustomer.setcMemo(customer.getcMemo());
		newCustomer.setcCusBrief(customer.getcCusBrief());
		try {
			Integer industryId = Integer.parseInt(ServletActionContext
					.getRequest().getParameter("industry"));
			newCustomer.setIndustry(industryService.find(industryId));
			customerService.update(newCustomer);
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
	 * 根据id得到详细信息
	 */
	public String getCustomerInfo() {
		Integer id = Integer.parseInt(ServletActionContext.getRequest()
				.getParameter("id"));
		Customer c = customerService.find(id);
		ActionContext.getContext().put("customer", c);
		return "customerInfo";
	}

	/**
	 * 转到介绍单位修改页面
	 */
	public String updateUI() {
		List<Industry> industries = industryService.getScrollData()
				.getResultlist();
		List<AreaClass> areas = areaClassService.getScrollData()
				.getResultlist();
		Integer id = Integer.parseInt(ServletActionContext.getRequest()
				.getParameter("id"));
		Customer p = customerService.find(id);
		ActionContext.getContext().put("customer", p);

		ActionContext.getContext().put("industries", industries);
		ActionContext.getContext().put("areas", areas);
		return "updateUI";
	}

	/**
	 * 修改介绍单位
	 */
	public String update() {

		Customer newCustomer = customerService.find(customer.getId());
		Integer areaId = Integer.parseInt(ServletActionContext.getRequest()
				.getParameter("areaId"));
		newCustomer.setAreaClass(areaClassService.find(areaId));
		newCustomer.setcCusAddress(customer.getcCusAddress());
		newCustomer.setcCusPostCode(customer.getcCusPostCode());
		Integer industryId = Integer.parseInt(ServletActionContext.getRequest()
				.getParameter("industryId"));
		newCustomer.setIndustry(industryService.find(industryId));
		newCustomer.setcCusPerson(customer.getcCusPerson());
		newCustomer.setcCusPhone(customer.getcCusPhone());
		newCustomer.setcCusFax(customer.getcCusFax());
		newCustomer.setcCusEmail(customer.getcCusEmail());
		newCustomer.setcCusQQ(customer.getcCusQQ());
		newCustomer.setcCusHand(customer.getcCusHand());
		newCustomer.setcCusLPerson(customer.getcCusLPerson());
		// 建档人为登录系统者
		HRPerson user = (HRPerson) ServletActionContext.getContext()
				.getSession().get("user");
		newCustomer.setcCreatePerson(user.getcPsnName());
		newCustomer.setcMemo(customer.getcMemo());
		newCustomer.setcCusBrief(customer.getcCusBrief());
		customerService.update(newCustomer);
		ActionContext.getContext().put("message", "介绍单位修改成功");
		ActionContext.getContext().put("urladdress",
				"/canvassResource/front/customer_displayList");
		return "message";
	}

	/**
	 * 删除介绍单位
	 */
	public void delete() {
		Map<String, String> map = new HashMap<String, String>();
		try {
			for (Integer id : idList) {
				customerService.delete(id);
			}
			map.put("status", "success");

		} catch (Exception e) {
			map.put("status", "fail");
			map.put("msg", "删除错误!");
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
	 * 检查介绍单位名称是否存在 使用Ajax认证
	 */
	public void checkcCusName() {
		String ajaxMessage = null;
		if (customerService.checkBycCusName(this.customer.getcCusName())) {
			// 如果项目编码存在
			ajaxMessage = "fail";
		} else {
			ajaxMessage = "success";
		}
		try {

			ServletActionContext.getResponse().getWriter().write(ajaxMessage);
		} catch (IOException e) {
			System.out.println("Ajax介绍单位名称认证异常");
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
	public void exportExcel(String fileName, List<Customer> content) {
		WritableWorkbook wwb;
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(fileName);
			wwb = Workbook.createWorkbook(fos);
			WritableSheet ws = wwb.createSheet("介绍单位列表", 10); // 创建一个工作表
			// 设置表头单元格的文字格式
			WritableFont wf = new WritableFont(WritableFont.ARIAL, 12,
					WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,
					Colour.BLUE);
			WritableCellFormat wcf = new WritableCellFormat(wf);
			wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
			wcf.setAlignment(Alignment.CENTRE);
			ws.setRowView(1, 500);
			ws.addCell(new Label(0, 0, "介绍单位名称", wcf));
			ws.addCell(new Label(1, 0, "地址", wcf));
			ws.addCell(new Label(2, 0, "所属行业", wcf));
			ws.addCell(new Label(3, 0, "联系人", wcf));
			ws.addCell(new Label(4, 0, "固定电话", wcf));
			ws.addCell(new Label(5, 0, "传真号码", wcf));
			ws.addCell(new Label(6, 0, "Email", wcf));
			ws.addCell(new Label(7, 0, "QQ号码", wcf));
			ws.addCell(new Label(8, 0, "手机", wcf));
			ws.addCell(new Label(9, 0, "法人", wcf));
			ws.addCell(new Label(10, 0, "备注", wcf));
			ws.addCell(new Label(11, 0, "简介", wcf));
			// 设置内容单元格样式 填充数据的内容
			WritableCellFormat contentWCF = new WritableCellFormat();
			for (int i = 0; i < content.size(); i++) {
				ws.addCell(new Label(0, i + 1, content.get(i).getcCusName(),
						contentWCF));

				ws.addCell(new Label(1, i + 1, content.get(i).getcCusAddress(),
						contentWCF));

				ws.addCell(new Label(2, i + 1, content.get(i).getIndustry()
						.getcInduName(), contentWCF));
				ws.addCell(new Label(3, i + 1, content.get(i).getcCusPerson(),
						contentWCF));
				ws.addCell(new Label(4, i + 1, content.get(i).getcCusPhone(),
						contentWCF));
				ws.addCell(new Label(5, i + 1, content.get(i).getcCusFax(),
						contentWCF));
				ws.addCell(new Label(6, i + 1, content.get(i).getcCusEmail(),
						contentWCF));
				ws.addCell(new Label(7, i + 1, content.get(i).getcCusQQ(),
						contentWCF));
				ws.addCell(new Label(8, i + 1, content.get(i).getcCusHand(),
						contentWCF));
				ws.addCell(new Label(9, i + 1, content.get(i).getcCusLPerson(),
						contentWCF));
				ws.addCell(new Label(10, i + 1, content.get(i).getcMemo(),
						contentWCF));
				ws.addCell(new Label(11, i + 1, content.get(i).getcCusBrief(),
						contentWCF));
			}
			wwb.write();
			wwb.close();
			fos.close();
		} catch (IOException e) {
		} catch (RowsExceededException e) {
		} catch (WriteException e) {
		}
	}

	public void excel() {
		Map<String, String> map = new HashMap<String, String>();
		String fileName = ServletActionContext.getRequest().getParameter(
				"fileName");
		String filePath = ServletActionContext.getServletContext().getRealPath(
				"\\excel")
				+ "\\" + fileName;
		List<Customer> content = customerService.getScrollData()
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
	}

}
