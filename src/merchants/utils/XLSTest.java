package merchants.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

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
import merchants.entity.canvassResource.Personal;
import merchants.service.impl.canvassResource.PersonalServiceImpl;

/**
 * 导出数据为XLS格式
 * @param fileName 文件的名称，可以设为绝对路径，也可以设为相对路径
 * @param content 数据的内容
 */
public class XLSTest {
	public static void exportExcel(String fileName, List<Personal> content) {
		WritableWorkbook wwb;
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(fileName);
			wwb = Workbook.createWorkbook(fos);
			WritableSheet ws = wwb.createSheet("介绍人列表", 10); // 创建一个工作表
			// 设置单元格的文字格式
			WritableFont wf = new WritableFont(WritableFont.ARIAL, 12,
					WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,
					Colour.BLUE);
			WritableCellFormat wcf = new WritableCellFormat(wf);
			wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
			wcf.setAlignment(Alignment.CENTRE);
			ws.setRowView(1, 500);
			// 填充数据的内容
			for (int i = 0; i < content.size(); i++) {
				ws.addCell(new Label(1, i + 1, content.get(i).getcIDNo(), wcf));
				ws.addCell(new Label(2, i + 1, content.get(i).getcPersonName(), wcf));
				ws.addCell(new Label(3, i + 1, content.get(i).getcPersonPost(), wcf));
				if (i == 0)
					wcf = new WritableCellFormat();
			}
			wwb.write();
			wwb.close();
		} catch (IOException e) {
		} catch (RowsExceededException e) {
		} catch (WriteException e) {
		}
	}
 
	public static void main(String[] args) {
		String fileName = "C:\\test.xls";
		List<Personal> content=new PersonalServiceImpl().getScrollData(-1,-1,null,null).getResultlist();
		exportExcel(fileName, content);
		System.out.println("成功导出数据到Excel文件(" + fileName + ")了!!!");
 
	}
}