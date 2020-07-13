package org.jit.sose.test.mapper;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.jit.sose.entity.CourseClassStudentInfo;
import org.jit.sose.test.BaseTest;
import org.junit.Test;

public class ExcelDownload extends BaseTest{
	

	@Test
	public void ExcelDownloadTest() throws Exception{
		CourseClassStudentInfo courseClassStudentInfo1 = new CourseClassStudentInfo();
		CourseClassStudentInfo courseClassStudentInfo2 = new CourseClassStudentInfo();
		List<CourseClassStudentInfo> courseClassStudentInfoList = new ArrayList<CourseClassStudentInfo>();
		courseClassStudentInfo1.setSeq(1);
		courseClassStudentInfo1.setStudentNumber("1612011058");
		courseClassStudentInfo1.setStudentName("王锐");
		courseClassStudentInfo2.setSeq(2);
		courseClassStudentInfo2.setStudentNumber("1612011059");
		courseClassStudentInfo2.setStudentName("马飞飞");
		

		courseClassStudentInfoList.add(courseClassStudentInfo1);
		courseClassStudentInfoList.add(courseClassStudentInfo2);
		try {
			Workbook wb =new HSSFWorkbook();
			Sheet sheet = wb.createSheet();
			//创建标题单元行
			Row titleRow = sheet.createRow(0);
			Cell title1 = titleRow.createCell(0);
			Cell title2 = titleRow.createCell(1);
			Cell title3 = titleRow.createCell(2);
			
			title1.setCellValue("序号");
			title2.setCellValue("学号");
			title3.setCellValue("学生姓名");
			
			for(int i =0;i<courseClassStudentInfoList.size();i++){
				CourseClassStudentInfo courseClassStudentInfo = courseClassStudentInfoList.get(i);
				Row row = sheet.createRow(i+1);
				Cell cell1 = row.createCell(0);
				Cell cell2 = row.createCell(1);
				Cell cell3 = row.createCell(2);
				
				cell1.setCellValue(courseClassStudentInfo.getSeq());
				cell2.setCellValue(courseClassStudentInfo.getStudentNumber());
				cell3.setCellValue(courseClassStudentInfo.getStudentName());
			}
			FileOutputStream file ;
			file = new FileOutputStream("d:\\poiTest.xls");
			wb.write(file);
			file.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
	}

}
