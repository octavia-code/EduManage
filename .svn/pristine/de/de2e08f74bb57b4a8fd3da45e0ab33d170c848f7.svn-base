package org.jit.sose.controller.info;

import java.util.List;

import org.jit.sose.entity.StudentInfo;
import org.jit.sose.service.StudentInfoService;
import org.jit.sose.util.ResponseUtil;
import org.jit.sose.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping("/info/studentInfo") // /模块名/类名
public class StudentInfoController {

	@Autowired
	private StudentInfoService studentInfoService;

	/**
	 * 查询学生信息和id集合
	 * 
	 * @return 学生信息和id集合
	 */
	@RequestMapping(value = "/selectStudentInfoList", method = RequestMethod.GET)
	public List<StudentInfo> selectStudentInfoList() {
		return studentInfoService.selectStudentInfoList();
	}

	/**
	 * 插入学生信息
	 * 
	 * @param studentNumber 学号 (String----长度20)
	 * @param studentName 学生名称(String----长度50)
	 * @param sex         性别(char----长度1)
	 * @param remark     备注信息(String----长度500)
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insert(@RequestBody StudentInfo studentInfo) {
		String result = studentInfoService.insert(studentInfo);
		// 调用添加数据接口
		return ResponseUtil.success(result);
	}

	/**
	 * 更新学生信息
	 * 
	  * @param studentNumber 学号 (String----长度20)
	 * @param studentName 学生名称(String----长度50)
	 * @param sex         性别(char----长度1)
	 * @param remark     备注信息(String----长度500)
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public void update(@RequestBody StudentInfo studentInfo) {
		// @RequestBody 自动根据参数类型转换值
		// 验证数据是否合法

		// 更新操作
		studentInfoService.update(studentInfo);
	}

	/**
	 * 逻辑删除学生信息
	 * 
	 * @param id 学生标识(Integer----长度5)
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public void delete(@RequestBody Integer id) {
		studentInfoService.delete(id);
	}

	/**
	 * 批量逻辑删除学生信息
	 * 
	 * @param id 学生标识(Integer----长度5)
	 * @return 成功删除的记录数
	 */
	@RequestMapping(value = "/deleteSelection", method = RequestMethod.POST)
	public Integer deleteSelection(@RequestBody List<Integer> idList) {
		return studentInfoService.deleteSelection(idList);
	}

	/**
	 * 过滤查询
	 * 
	 * @param start      当前页(Integer----长度5)
	 * @param pageSize   页面大小(Integer----长度3,最大值为100)
	 * @param studentNumber 学号 (String----长度20)
	 * @param studentName 学生名称 (String----长度50)
	 * @return 学生信息集合
	 */
	@RequestMapping(value = "/selectPageInfo", method = RequestMethod.POST)
	public PageInfo<StudentInfo> selectPageInfo(@RequestBody String str) {
		// 使用fastjson转为json对象
		JSONObject strj = JSON.parseObject(str);
		StudentInfo studentInfo = new StudentInfo();

		// 过滤查询条件
		String studentNumber = strj.getString("studentNumber");
		String studentName = strj.getString("studentName");
		studentInfo.setStudentNumber(StringUtil.isEmpty(studentNumber) ? null : studentNumber);
		studentInfo.setStudentName(StringUtil.isEmpty(studentName) ? null : studentName);

		// 当前页码
		int pageNum = strj.getIntValue("pageNum");
		// 页面大小
		int pageSize = strj.getIntValue("pageSize");
		return studentInfoService.selectPageInfo(studentInfo, pageNum, pageSize);
	}
	
	/**
	 * 上课学生信息查询
	 * @param str
	 * @return 学生信息集合
	 */
	@RequestMapping(value = "/selectStudentManage", method = RequestMethod.POST)
	public PageInfo<StudentInfo> selectStudentManage(@RequestBody String str) {
		// 使用fastjson转为json对象
		JSONObject strj = JSON.parseObject(str);

        Integer courseClassInfoId=strj.getInteger("courseClassInfoId");
/*		// 当前页码
		int pageNum = strj.getIntValue("pageNum");
		// 页面大小
		int pageSize = strj.getIntValue("pageSize");*/
		return studentInfoService.selectStudentManage(courseClassInfoId);
	}
	
	
	/**
	 * 学生库信息查询
	 * @param str
	 * @return 学生信息集合
	 */
	@RequestMapping(value = "/listByMessage", method = RequestMethod.POST)
	public PageInfo<StudentInfo> listByMessage(@RequestBody StudentInfo studentInfo) {
		return studentInfoService.listByMessage(studentInfo);
	}
	
	
	
}
