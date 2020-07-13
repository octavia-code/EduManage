package org.jit.sose.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.jit.sose.config.FileConfig;
import org.jit.sose.entity.CourseClassStudentInfo;
import org.jit.sose.entity.FileInfo;
import org.jit.sose.entity.ScoreTotal;
import org.jit.sose.entity.StudentInfo;
import org.jit.sose.mapper.CourseClassStudentInfoMapper;
import org.jit.sose.mapper.FileInfoMapper;
import org.jit.sose.mapper.ScoreTotalMapper;
import org.jit.sose.service.CourseClassStudentInfoService;
import org.jit.sose.util.ExcelDownload;
import org.jit.sose.util.ExcelUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseClassStudentInfoServiceImpl implements CourseClassStudentInfoService {
	@Autowired
	private CourseClassStudentInfoMapper recordMapper;

	@Autowired
	private FileInfoMapper fileInfoMapper;
	
	@Autowired
	private ScoreTotalMapper scoreTotalMapper;

	@Override
	public Integer deleteSelection(List<Integer> idList) {
		return recordMapper.deleteSelection(idList);
	}

	@Override
	public List<CourseClassStudentInfo> listStudentWithScore(Integer gradeFormId, Integer courseOutlineId,
			Integer courseClassInfoId) {
		return recordMapper.listStudentWithScore(gradeFormId, courseOutlineId, courseClassInfoId);
	}

	@Override
	public Integer insertList(Integer courseClassInfoId, List<Integer> stuIdList) {
		List<CourseClassStudentInfo> list = new ArrayList<CourseClassStudentInfo>();
		CourseClassStudentInfo info = null;

		// 遍历添加学生
		for (Integer stuId : stuIdList) {
			info = new CourseClassStudentInfo(courseClassInfoId, stuId);
			/*
			 * int aaa=recordMapper.countSame(info); if(aaa > 0){ re }
			 */
			list.add(info);
		}
		Integer result =  recordMapper.insertList(list);
		
		
		//获取课程班级学生id插入总成绩表
		List<Integer> courseClassInfoIdList = recordMapper.listByCourseClassInfoId(courseClassInfoId);
//		CourseClassStudentInfo classStudentInfo = new CourseClassStudentInfo();
//		ScoreTotal scoreTotal = new ScoreTotal();
//		scoreTotal.setCouId(classStudentInfo.getId());
//		System.out.println("返回数据"+classStudentInfo.getId());
		
		scoreTotalMapper.insertCouId(courseClassInfoIdList);
		
		return result;
	}

	@Override
	public void insertByExcel(Integer courseClassInfoId, Integer fileInfoId) {
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
			List<CourseClassStudentInfo> courseClassStudentInfoList = ExcelUpload.ReadExcelxls(fileName);
			// 调用mapper层增加方法
			recordMapper.insertOrUpdateList(courseClassInfoId, courseClassStudentInfoList);
		} 
		//xlsx调用该方法
		else {
			List<CourseClassStudentInfo> courseClassStudentInfoList = ExcelUpload.ReadExcelOther(fileName);
			// 调用mapper层增加方法
			recordMapper.insertOrUpdateList(courseClassInfoId, courseClassStudentInfoList);
		}
		//获取课程班级学生id插入总成绩表
		List<Integer> courseClassInfoIdList = recordMapper.listByCourseClassInfoId(courseClassInfoId);
		System.out.println("返回数据"+courseClassInfoIdList);
		scoreTotalMapper.insertCouId(courseClassInfoIdList);		
	}

	@Override
	public void SortSeq(List<StudentInfo> studentInfo) {
		for (int i = 0; i < studentInfo.size(); i++) {
			recordMapper.SortSeq(studentInfo.get(i));
		}
	}

	@Override
	public void excelDownload(HttpServletResponse response, Integer courseClassInfoId, String fileName) {
		List<CourseClassStudentInfo> courseClassStudentInfoList = recordMapper.exportStudentInfo(courseClassInfoId);
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
			ExcelDownload.getExportedFile(wb, fileName, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
