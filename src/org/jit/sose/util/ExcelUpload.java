package org.jit.sose.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jit.sose.config.FileConfig;
import org.jit.sose.entity.ClassStudent;
import org.jit.sose.entity.CourseClassStudentInfo;


public class ExcelUpload {
	/**
	 * 传入文件为xls格式时调用该方法
	 * @param fileName
	 * @return
	 */
	public static List<CourseClassStudentInfo> ReadExcelxls(String fileName){
		ArrayList<Object> columnList = new ArrayList<Object>();
		InputStream is;
		HSSFWorkbook wb = null;
		try {
			is = new FileInputStream(FileConfig.UPLOAD_PATH +File.separator+fileName);
			wb = new HSSFWorkbook(is);
		} 
		 catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("读取表格失败");
		}
		HSSFSheet sheet =  wb.getSheetAt(0);	
		HSSFCell cell;
		HSSFRow row;
		for (int i = sheet.getFirstRowNum()+1; i < sheet.getPhysicalNumberOfRows(); i++) {
			row = sheet.getRow(i);
			for (int j = row.getFirstCellNum(); j < row.getLastCellNum(); j++) {
				Object value = null;
				cell = row.getCell(j);
				cell.setCellType(cell.CELL_TYPE_STRING);
				value = cell.getStringCellValue();
				columnList.add(value);
			}
		}
		//将数据集合转换为实体类集合
		List<CourseClassStudentInfo> courseClassStudentInfoList = new ArrayList<CourseClassStudentInfo>();
		for (int i = 0; i < columnList.size(); i += 3) {
			CourseClassStudentInfo courseClassStudentInfo = new CourseClassStudentInfo();
			Object seq = columnList.get(i);
			Object studentNumber = columnList.get(i + 1);
			seq = Integer.parseInt((String) seq);
			studentNumber = String.valueOf(studentNumber);
			courseClassStudentInfo.setSeq((Integer) seq);
			courseClassStudentInfo.setStudentNumber((String) studentNumber);
			courseClassStudentInfoList.add(courseClassStudentInfo);
		}
		return courseClassStudentInfoList;
	}
	
	/**
	 * 传入文件格式为xls以外时调用
	 * @param fileName
	 * @return
	 */
	public static List<CourseClassStudentInfo> ReadExcelOther(String fileName){
		ArrayList<Object> columnList = new ArrayList<Object>();
		InputStream is;
		XSSFWorkbook wb = null;
		try {
			is = new FileInputStream(FileConfig.UPLOAD_PATH +File.separator+fileName);
			wb = new XSSFWorkbook(is);
		} 
		 catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("读取表格失败");
		}
		XSSFSheet sheet = wb.getSheetAt(0);	
		XSSFCell cell;
		XSSFRow row;
		for (int i = sheet.getFirstRowNum()+1; i < sheet.getPhysicalNumberOfRows(); i++) {
			row = sheet.getRow(i);
			for (int j = row.getFirstCellNum(); j < row.getLastCellNum(); j++) {
				Object value = null;
				cell = row.getCell(j);
				cell.setCellType(cell.CELL_TYPE_STRING);;
				value = cell.getStringCellValue();
				columnList.add(value);
			}
		}
		//将数据集合转换为实体类集合
		List<CourseClassStudentInfo> courseClassStudentInfoList = new ArrayList<CourseClassStudentInfo>();
		for (int i = 0; i < columnList.size(); i += 3) {
			CourseClassStudentInfo courseClassStudentInfo = new CourseClassStudentInfo();
			Object seq = columnList.get(i);
			Object studentNumber = columnList.get(i + 1);
			seq = Integer.parseInt(seq.toString());
			studentNumber = String.valueOf(studentNumber);
			courseClassStudentInfo.setSeq((Integer) seq);
			courseClassStudentInfo.setStudentNumber((String) studentNumber);
			courseClassStudentInfoList.add(courseClassStudentInfo);
		}
		return courseClassStudentInfoList;
	}
	
	public static List<ClassStudent> ReadClassStudentExcelxls(String fileName){
		ArrayList<Object> columnList = new ArrayList<Object>();
		InputStream is;
		HSSFWorkbook wb = null;
		try {
			is = new FileInputStream(FileConfig.UPLOAD_PATH +File.separator+fileName);
			wb = new HSSFWorkbook(is);
		} 
		 catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("读取表格失败");
		}
		HSSFSheet sheet =  wb.getSheetAt(0);	
		HSSFCell cell;
		HSSFRow row;
		for (int i = sheet.getFirstRowNum()+2; i < sheet.getPhysicalNumberOfRows(); i++) {
			row = sheet.getRow(i);
			for (int j = row.getFirstCellNum(); j < row.getLastCellNum(); j++) {
				Object value = null;
				cell = row.getCell(j);
				cell.setCellType(cell.CELL_TYPE_STRING);
				value = cell.getStringCellValue();
				columnList.add(value);
			}
		}
		System.out.println("columnList---"+columnList);
		//将数据集合转换为实体类集合
		List<ClassStudent> classStudentList = new ArrayList<ClassStudent>();
		for (int i = 0; i < columnList.size(); i += 2) {
			ClassStudent classStudent = new ClassStudent();
			Object studentName = columnList.get(i);
			Object studentState = columnList.get(i + 1);
			studentName = String.valueOf(studentName);
			studentState = String.valueOf(studentState);
			classStudent.setStudentName((String)studentName);
			classStudent.setStudentState((String)studentState);
			classStudentList.add(classStudent);
		}		
		return classStudentList;
	}
	
	public static List<ClassStudent> ReadClassStudentExcelother(String fileName){
		ArrayList<Object> columnList = new ArrayList<Object>();
		InputStream is;
		XSSFWorkbook wb = null;
		try {
			is = new FileInputStream(FileConfig.UPLOAD_PATH +File.separator+fileName);
			wb = new XSSFWorkbook(is);
		} 
		 catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("读取表格失败");
		}
		XSSFSheet sheet =  wb.getSheetAt(0);	
		XSSFCell cell;
		XSSFRow row;
		for (int i = sheet.getFirstRowNum()+2; i < sheet.getPhysicalNumberOfRows(); i++) {
			row = sheet.getRow(i);
			for (int j = row.getFirstCellNum(); j < row.getLastCellNum(); j++) {
				Object value = null;
				cell = row.getCell(j);
				cell.setCellType(cell.CELL_TYPE_STRING);
				value = cell.getStringCellValue();
				columnList.add(value);
			}
		}
		System.out.println("columnList---"+columnList);
		//将数据集合转换为实体类集合
		List<ClassStudent> classStudentList = new ArrayList<ClassStudent>();
		for (int i = 0; i < columnList.size(); i += 2) {
			ClassStudent classStudent = new ClassStudent();
			Object studentName = columnList.get(i);
			Object studentState = columnList.get(i + 1);
			studentName = String.valueOf(studentName);
			studentState = String.valueOf(studentState);
			classStudent.setStudentName((String)studentName);
			classStudent.setStudentState((String)studentState);
			classStudentList.add(classStudent);
		}		
		return classStudentList;
	}
}
