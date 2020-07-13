package org.jit.sose.controller.info;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.jit.sose.entity.ClassStudent;
import org.jit.sose.service.ClassStudentService;
import org.jit.sose.util.DataExceptionUtil;
import org.jit.sose.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

/**
 * 班级学生关联
 * 
 * @author 徐国皓
 *
 */

@RestController
@RequestMapping("/info/classStudent")
public class ClassStudentController {
	@Autowired
	private ClassStudentService classStudentService;

	/**
	 * 查询班级学生信息和id集合
	 * 
	 * @author 徐国皓
	 * @return 班级学生信息和id集合
	 */
	@RequestMapping(value = "/selectStudentStateList", method = RequestMethod.GET)
	public List<ClassStudent> selectClassStudentList() {
		return classStudentService.selectStudentStateList();
	}
	/**
	 * 查询班级学生信息列表和id集合
	 * 
	 * @author 徐国皓
	 * @return 班级学生信息和id集合
	 */
	@RequestMapping(value = "/listByClassStudent", method = RequestMethod.GET)
	public List<ClassStudent> listByClassStudent() {
		return classStudentService.listByClassStudent();
	}

	/**
	 * 插入班级学生信息
	 * 
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public void insert(@RequestBody ClassStudent classStudent) {
		classStudentService.insert(classStudent);
	}

	/**
	 * 更新班级学生信息
	 * 
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public void update(@RequestBody ClassStudent classStudent) {
		// 更新操作
		classStudentService.update(classStudent);
	}

	/**
	 * 逻辑删除班级学生信息
	 * 
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public void delete(@RequestBody Integer id) {
		classStudentService.delete(id);
	}

	/**
	 * 批量逻辑删除班级学生信息
	 * 
	 */
	@RequestMapping(value = "/deleteSelection", method = RequestMethod.POST)
	public Integer deleteSelection(@RequestBody List<Integer> idList) {
		return classStudentService.deleteSelection(idList);
	}

	/**
	 * 过滤查询
	 * 
	 * @return 班级学生信息集合
	 */
	@RequestMapping(value = "/selectPageInfo", method = RequestMethod.POST)
	public PageInfo<ClassStudent> selectPageInfo(@RequestBody String str) {
		// 使用fastjson转为json对象
		JSONObject strj = JSON.parseObject(str);
		ClassStudent classStudent = new ClassStudent();
		// 过滤查询条件
		String schoolInfoId = strj.getString("schoolInfoId");
		classStudent.setSchoolInfoId(StringUtil.isEmpty(schoolInfoId) ? null : strj.getIntValue("schoolInfoId"));

		String specialtyId = strj.getString("specialtyId");
		classStudent.setSpecialtyId(StringUtil.isEmpty(specialtyId) ? null : strj.getIntValue("specialtyId"));
		
		String classInfoId = strj.getString("classInfoId");
		classStudent.setClassInfoId(StringUtil.isEmpty(classInfoId) ? null : strj.getIntValue("classInfoId"));
		
	
		String yearName = strj.getString("yearName");
		classStudent.setYearName(StringUtil.isEmpty(yearName) ? null : yearName);	
		
		// 当前页码
		int pageNum = strj.getIntValue("pageNum");
		// 页面大小
		int pageSize = strj.getIntValue("pageSize");
		return classStudentService.selectPageInfo(classStudent, pageNum, pageSize);
		
	}

	/**
	 * Excel文件导出
	 * @param classInfoId
	 * @param response
	 */
	@RequestMapping(value = "/excelDownload",method=RequestMethod.GET)
	public void excelDownload(@RequestParam(value="classInfoId")Integer classInfoId,HttpServletResponse response ){
		classStudentService.listByClassInfoId(classInfoId, response);
	}
	
	/**
	 * Excel文件导入
	 */
	@RequestMapping(value = "/insertByExcel",method=RequestMethod.POST)
	public void insertByExcel(@RequestBody String str){
		JSONObject strj = JSON.parseObject(str);
		Integer classInfoId = strj.getInteger("classInfoId");
		Integer fileInfoId = strj.getInteger("fileInfoId");
//		System.out.println("classInfoId+fileInfoId---"+classInfoId+fileInfoId);
		classStudentService.insertByExcel(classInfoId, fileInfoId);
	}
}
