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
import merchants.entity.QueryResult;
import merchants.entity.canvassResource.Personal;
import merchants.service.inter.canvassResource.PersonalService;
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
public class PersonalAction {
	@Resource
	PersonalService personalService;
	private Personal personal;
	// 实现多行记录的删除
	private Integer[] idList;
	/* 当前页码 */
	public Integer page = 1;
	// 用于接收查询参数并用于回显以完成翻页请求
	public Integer rows = 10;
	private String searchKey;
	private String searchValue;

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

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Personal getPersonal() {
		return personal;
	}

	public void setPersonal(Personal personal) {
		this.personal = personal;
	}

	public Integer[] getIdList() {
		return idList;
	}

	public void setIdList(Integer[] idList) {
		this.idList = idList;
	}

	/**
	 * 功能界面
	 */
	public String display2() {

		return "personalDisplay";
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

			where.append(" and o.cPersonName" + " like ?"
					+ (parameters.size() + 1));
			parameters.add("%" + searchValue + "%");
			where.append(" or o.cIDNo=?" + (parameters.size() + 1));
			parameters.add(searchValue);
			where.append(" or o.cPersonCompany=?" + (parameters.size() + 1));
			parameters.add(searchValue);
		}
		List<Map> records = new ArrayList<Map>();
		Integer total;
		Map<String, Object> map = new HashMap<String, Object>();
		QueryResult<Personal> qr = personalService.getScrollData((page - 1)
				* rows, rows, where.toString(), parameters.toArray());
		total = Integer.parseInt(qr.getTotalrecord() + "");
		for (int i = 0; i < qr.getResultlist().size(); i++) {
			Personal p = qr.getResultlist().get(i);
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("id", p.getId());
			m.put("personal.cPersonName", p.getcPersonName());
			if (p.getiValid()) {
				m.put("personal.iValid", "有效");
			} else {
				m.put("personal.iValid", "无效");
			}
			if ("FEMAL".equals(p.getcSex().toString())) {
				m.put("personal.cSex", "女");
			} else {
				m.put("personal.cSex", "男");
			}
			m.put("personal.cIDNo", p.getcIDNo());
			m.put("personal.cPersonPost", p.getcPersonPost());
			m.put("personal.cPersonAddress", p.getcPersonAddress());
			m.put("personal.cPersonEmail", p.getcPersonEmail());
			m.put("personal.cPersonHand", p.getcPersonHand());
			m.put("personal.cPersonPhone", p.getcPersonPhone());
			m.put("personal.cPersonQQ", p.getcPersonQQ());
			m.put("personal.cPersonCompany", p.getcPersonCompany());
			m.put("personal.cPersonBrief", p.getcPersonBrief());
			m.put("personal.cMemo", p.getcMemo());
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
	 * 删除
	 */
	public void delete() {
		Map<String, String> map = new HashMap<String, String>();
		try {
			for (Integer id : idList) {
				personalService.delete(id);
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
	 * 保存介绍人入库
	 */
	public void save() {
		Map<String, String> map = new HashMap<String, String>();
		try {
			personalService.save(personal);
			map.put("errorMsg", "");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("errorMsg", "介绍人保存失败");
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
	public String getPersonalInfo() {
		Integer id = Integer.parseInt(ServletActionContext.getRequest()
				.getParameter("id"));
		Personal p = personalService.find(id);
		ActionContext.getContext().put("entity", p);
		return "personalInfo";
	}

	/**
	 * 转到介绍人修改页面
	 */
	public String updateUI() {
		Integer id = Integer.parseInt(ServletActionContext.getRequest()
				.getParameter("id"));
		Personal p = personalService.find(id);
		ActionContext.getContext().put("personal", p);
		return "updateUI";
	}

	/**
	 * 修改介绍人
	 */
	public void edit() {
		Map<String, String> map = new HashMap<String, String>();
		Integer id = Integer.parseInt(ServletActionContext.getRequest()
				.getParameter("id"));
		Personal newPersonal = personalService.find(id);
		newPersonal.setcPersonPost(personal.getcPersonPost());
		newPersonal.setcSex(personal.getcSex());
		newPersonal.setcPersonEmail(personal.getcPersonEmail());
		newPersonal.setcPersonPhone(personal.getcPersonPhone());
		newPersonal.setcPersonHand(personal.getcPersonHand());
		newPersonal.setcPersonQQ(personal.getcPersonQQ());
		newPersonal.setcPersonCompany(personal.getcPersonCompany());
		newPersonal.setcPersonAddress(personal.getcPersonAddress());
		newPersonal.setcPersonBrief(personal.getcPersonBrief());
		newPersonal.setcMemo(personal.getcMemo());
		personalService.update(newPersonal);
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
	 * 检查介绍人姓名是否存在 使用Ajax认证
	 */
	public void checkcPersonName() {
		String ajaxMessage = null;
		if (personalService.checkBycPersonName(this.personal.getcPersonName())) {
			// 如果项目编码存在
			ajaxMessage = "fail";
		} else {
			ajaxMessage = "success";
		}
		try {

			ServletActionContext.getResponse().getWriter().write(ajaxMessage);
		} catch (IOException e) {
			System.out.println("Ajax介绍人姓名认证异常");
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
	public void exportExcel(String fileName, List<Personal> content) {
		WritableWorkbook wwb;
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(fileName);
			wwb = Workbook.createWorkbook(fos);
			WritableSheet ws = wwb.createSheet("介绍人列表", 10); // 创建一个工作表
			// 设置表头单元格的文字格式
			WritableFont wf = new WritableFont(WritableFont.ARIAL, 12,
					WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,
					Colour.BLUE);
			WritableCellFormat wcf = new WritableCellFormat(wf);
			wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
			wcf.setAlignment(Alignment.CENTRE);
			ws.setRowView(1, 500);
			ws.addCell(new Label(0, 0, "介绍人姓名", wcf));
			ws.addCell(new Label(1, 0, "性别", wcf));
			ws.addCell(new Label(2, 0, "身份证号码", wcf));
			ws.addCell(new Label(3, 0, "职务", wcf));
			ws.addCell(new Label(4, 0, "住址", wcf));
			ws.addCell(new Label(5, 0, "邮箱", wcf));
			ws.addCell(new Label(6, 0, "手机", wcf));
			ws.addCell(new Label(7, 0, "电话", wcf));
			ws.addCell(new Label(8, 0, "QQ号码", wcf));
			ws.addCell(new Label(9, 0, "单位", wcf));
			ws.addCell(new Label(10, 0, "简介", wcf));
			ws.addCell(new Label(11, 0, "备注", wcf));
			// 设置内容单元格样式 填充数据的内容
			WritableCellFormat contentWCF = new WritableCellFormat();
			
			for (int i = 0; i < content.size(); i++) {
				ws.addCell(new Label(0, i + 1, content.get(i).getcPersonName(),
						contentWCF));
				if (content.get(i).getcSex().toString().equals("FEMAL")) {
					ws.addCell(new Label(1, i + 1, "女", contentWCF));
				} else {
					ws.addCell(new Label(1, i + 1, "男", contentWCF));
				}
				ws.addCell(new Label(2, i + 1, content.get(i).getcIDNo(),
						contentWCF));
				ws.addCell(new Label(3, i + 1, content.get(i).getcPersonPost(),
						contentWCF));
				ws.addCell(new Label(4, i + 1, content.get(i)
						.getcPersonAddress(), contentWCF));
				ws.addCell(new Label(5, i + 1,
						content.get(i).getcPersonEmail(), contentWCF));
				ws.addCell(new Label(6, i + 1, content.get(i).getcPersonHand(),
						contentWCF));
				ws.addCell(new Label(7, i + 1,
						content.get(i).getcPersonPhone(), contentWCF));
				ws.addCell(new Label(8, i + 1, content.get(i).getcPersonQQ(),
						contentWCF));
				ws.addCell(new Label(9, i + 1, content.get(i)
						.getcPersonCompany(), contentWCF));
				ws.addCell(new Label(10, i + 1, content.get(i)
						.getcPersonBrief(), contentWCF));
				ws.addCell(new Label(11, i + 1, content.get(i).getcMemo(),
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
		Map<String,String>map=new HashMap<String,String>();
		String fileName = ServletActionContext.getRequest().getParameter(
				"fileName");
		String filePath = ServletActionContext.getServletContext().getRealPath(
				"\\excel")
				+ "\\" + fileName;
		List<Personal> content = personalService.getScrollData()
				.getResultlist();
		exportExcel(filePath, content);
		map.put("fileName", fileName);
		map.put("filePath", "/excel/"+fileName);
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
