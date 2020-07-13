package org.jit.sose.util;

import java.io.BufferedOutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;

public class ExcelDownload {

	/*public static void excelDownload(List<CourseClassStudentInfo> courseClassStudentInfoList, String fileName,
			HttpServletResponse response) {
		//创建工作部
		Workbook wb = new HSSFWorkbook();
		//创建工作页
		Sheet sheet = wb.createSheet();
		// 创建标题单元行
		Row titleRow = sheet.createRow(0);
		Cell title1 = titleRow.createCell(0);
		Cell title2 = titleRow.createCell(1);
		Cell title3 = titleRow.createCell(2);

		title1.setCellValue("序号");
		title2.setCellValue("学号");
		title3.setCellValue("学生姓名");
		// 遍历表中数据
		for (int i = 0; i < courseClassStudentInfoList.size(); i++) {
			CourseClassStudentInfo courseClassStudentInfo = courseClassStudentInfoList.get(i);
			Row row = sheet.createRow(i + 1);
			Cell cell1 = row.createCell(0);
			Cell cell2 = row.createCell(1);
			Cell cell3 = row.createCell(2);

			cell1.setCellValue(courseClassStudentInfo.getSeq());
			cell2.setCellValue(courseClassStudentInfo.getStudentNumber());
			cell3.setCellValue(courseClassStudentInfo.getStudentName());
		}
		try {
			getExportedFile(wb, fileName, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 *
	 * 方法说明: 指定路径下生成EXCEL文件
	 * 
	 * @return
	 */
	public static void getExportedFile(Workbook workbook, String name, HttpServletResponse response) throws Exception {
		BufferedOutputStream fos = null;
		try {
			String fileName = name + ".xls";
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/vnd.ms-excel;charset=utf-8");// 设置contentType为excel格式
			response.setHeader("Content-Disposition", "attachment;filename="
                    .concat(String.valueOf(URLEncoder.encode(fileName, "UTF-8"))));
			fos = new BufferedOutputStream(response.getOutputStream());
			workbook.write(fos);
			System.out.println("fileName---" + fileName);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				fos.close();
			}
		}

	}

}
