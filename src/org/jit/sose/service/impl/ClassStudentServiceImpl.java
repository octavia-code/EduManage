package org.jit.sose.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.jit.sose.config.FileConfig;
import org.jit.sose.entity.ClassStudent;
import org.jit.sose.entity.FileInfo;
import org.jit.sose.mapper.ClassStudentMapper;
import org.jit.sose.mapper.FileInfoMapper;
import org.jit.sose.service.ClassStudentService;
import org.jit.sose.util.ExcelDownload;
import org.jit.sose.util.ExcelUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


@Service
public class ClassStudentServiceImpl implements ClassStudentService {
	@Autowired
	private ClassStudentMapper classStudentMapper;
	
	@Autowired
	private FileInfoMapper fileInfoMapper;
	
	@Override
	public void insert(ClassStudent classStudent) {
		classStudentMapper.insert(classStudent);
	}

	@Override
	public void update(ClassStudent classStudent) {
		classStudentMapper.update(classStudent);
	}
	
	@Override
	public void delete(Integer id) {
		classStudentMapper.delete(id);
	}

	@Override
	public Integer deleteSelection(List<Integer> idList) {
		return classStudentMapper.deleteSelection(idList);
	}

	
	@Override
	public ClassStudent selectById(Integer id) {
		return classStudentMapper.selectById(id);
	}
	
	@Override
	public List<ClassStudent> selectStudentStateList() {
		return classStudentMapper.selectStudentStateList();
	}
	
	@Override
	public List<ClassStudent> listByClassStudent() {
		return classStudentMapper.listByClassStudent();
	}

	@Override
	public PageInfo<ClassStudent> selectPageInfo(ClassStudent classStudent, Integer pageNum, Integer pageSize) {
		// 设置分页参数
		PageHelper.startPage(pageNum, pageSize);
		// 查询集合
		List<ClassStudent> classStudentList = classStudentMapper.listByClassStudent(classStudent);
		PageInfo<ClassStudent> pageInfo = new PageInfo<ClassStudent>(classStudentList);
		return pageInfo;
	}

	@Override
	public void listByClassInfoId(Integer classInfoId,HttpServletResponse response) {
		List<ClassStudent> classStudentList = classStudentMapper.listByClassInfoId(classInfoId);
		//创建工作部
		Workbook wb = new HSSFWorkbook();
		//创建工作页
		Sheet sheet = wb.createSheet();
		// 创建备注
		Row remarkRow = sheet.createRow(0);
		Cell remark1 = remarkRow.createCell(0);
		Cell remark2 = remarkRow.createCell(1);
		Cell remark3 = remarkRow.createCell(2);
		Cell remark4 = remarkRow.createCell(3);
		Cell remark5 = remarkRow.createCell(4);
		Cell remark6 = remarkRow.createCell(5);
		//创建标题
		Row titleRow = sheet.createRow(1);
		Cell title1 = titleRow.createCell(0);
		Cell title2 = titleRow.createCell(1);
		remark1.setCellValue("学籍状态:");
		remark2.setCellValue("A-在读");
		remark3.setCellValue("B-入伍");
		remark4.setCellValue("C-病休");
		remark5.setCellValue("D-毕业");
		remark6.setCellValue("X-退学");
		title1.setCellValue("学生姓名");
		title2.setCellValue("学生状态");
		// 遍历表中数据
		for (int i = 0; i < classStudentList.size(); i++) {
			ClassStudent classStudent = classStudentList.get(i);
			Row row = sheet.createRow(i + 2);
			Cell cell1 = row.createCell(0);
			Cell cell2 = row.createCell(1);

			cell1.setCellValue(classStudent.getStudentName());
			cell2.setCellValue(classStudent.getStudentState());
		}
		ClassStudent classStudent = classStudentList.get(0);
		String fileName = classStudent.getClassName();
		try {
			ExcelDownload.getExportedFile(wb, fileName, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insertByExcel(Integer classInfoId, Integer fileInfoId) {
		InputStream is = null;
		// 获取file对象
		FileInfo file = fileInfoMapper.selectById(fileInfoId);
		String fileName = file.getAccessUrl();
		fileName = fileName.substring(7);
		System.out.println("----fileName---" + fileName.toString());
		try {
			// 获取工作簿对象
			is = new FileInputStream(FileConfig.UPLOAD_PATH + File.separator + fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//xls格式调用该方法
		if (fileName.toString().endsWith(".xls")) {
			List<ClassStudent> classStudentList = ExcelUpload.ReadClassStudentExcelxls(fileName);
			System.out.println("classStudentList--"+classStudentList);
			// 调用mapper层增加方法
//			classStudentMapper.insertOrUpdateList(classInfoId, classStudentList);
		} 
		//xlsx调用该方法
		else {
			List<ClassStudent> classStudentList = ExcelUpload.ReadClassStudentExcelother(fileName);
			// 调用mapper层增加方法
			classStudentMapper.insertOrUpdateList(classInfoId, classStudentList);
		}
	}

}
