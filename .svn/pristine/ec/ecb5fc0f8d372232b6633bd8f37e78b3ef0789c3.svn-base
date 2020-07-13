package org.jit.sose.test.mapper;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jit.sose.entity.CourseClassStudentInfo;
import org.jit.sose.mapper.CourseClassStudentInfoMapper;
import org.jit.sose.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class excelInputTest extends BaseTest {

	@Autowired
	private CourseClassStudentInfoMapper courseClassStudentInfoMapper;

	@Test
	public void InputExcelTest() {
		InputStream is = null;
		HSSFWorkbook wb = null;
		try {
//			File file = new File("D://学生信息 (17).xls");
//			System.out.println(file.toString());
//			is = new FileInputStream("D://学生信息 (17).xls");
//			is = new FileInputStream(FileConfig.UPLOAD_PATH +File.separator+ "stuInfo.xls");
			wb = new HSSFWorkbook(is);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("读取表格失败");
		}

		//创建工作簿
		HSSFSheet sheet = wb.getSheetAt(0);
		ArrayList<ArrayList<Object>> rowList = new ArrayList<ArrayList<Object>>();
		ArrayList<Object> columnList = new ArrayList<Object>();
		//
		HSSFCell cell;
		HSSFRow row;

		//读取工作簿第二行以及之后的值
		for (int i = sheet.getFirstRowNum() + 1; i < sheet.getPhysicalNumberOfRows(); i++) {
			row = sheet.getRow(i);
			for (int j = row.getFirstCellNum(); j < row.getLastCellNum(); j++) {
				Object value = null;
				cell = row.getCell(j);
				cell.setCellType(cell.CELL_TYPE_STRING);
				value = cell.getStringCellValue();
				System.out.println(j);
				System.out.println("单元格为：" + cell);
				columnList.add(value);
				System.out.println("columnList值为：" + columnList);
			}

		}
		List<CourseClassStudentInfo> courseClassStudentInfoList = new ArrayList<CourseClassStudentInfo>();

		//取出集合内所需元素
		for (int i = 0; i < columnList.size(); i += 3) {
			CourseClassStudentInfo courseClassStudentInfo = new CourseClassStudentInfo();
			Object seq = columnList.get(i);
			Object studentNumber = columnList.get(i + 1);
			seq = Integer.parseInt(seq.toString());
			studentNumber = String.valueOf(studentNumber);
			System.out.println("----seq----"+seq);
			System.out.println("----studentNumber----"+studentNumber);
			courseClassStudentInfo.setSeq((Integer) seq);
			courseClassStudentInfo.setStudentNumber((String) studentNumber);
			courseClassStudentInfoList.add(courseClassStudentInfo);
		}
		System.out.println("courseClassStudentInfoList值为：" + courseClassStudentInfoList);

		for (CourseClassStudentInfo courseClassStudentInfo : courseClassStudentInfoList) {
			
		}
		courseClassStudentInfoMapper.insertOrUpdateList(1, courseClassStudentInfoList);
		
	}

}
