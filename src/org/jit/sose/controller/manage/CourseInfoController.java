package org.jit.sose.controller.manage;

import java.util.List;

import org.jit.sose.entity.CourseInfo;
import org.jit.sose.service.CourseInfoService;
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

import lombok.extern.slf4j.Slf4j;

/**
 * 课程信息控制器类
 * 
 * @author: 王越
 * @date: 2019年5月16日 下午11:02:16
 */
@Slf4j
@RestController
@RequestMapping("/manage/courseInfo") // /模块名/类名
public class CourseInfoController {

	@Autowired
	private CourseInfoService courseInfoService;

	/**
	 * 查询课程信息和id集合
	 * 
	 * @return 课程信息和id集合
	 */
	@RequestMapping(value = "/listCourseInfo", method = RequestMethod.GET)
	public List<CourseInfo> listCourseInfo() {
		return courseInfoService.listCourseInfo();
	}

	/**
	 * 插入课程信息
	 * 
	 * @param courseCode 课程编码 (String----长度20)
	 * @param courseName 课程名称(String----长度200)
	 * @param scord      学分(double----长度3,保留小数点后1位)
	 * @param theoryDur  理论学时(Integer----长度3)
	 * @param expDur     实验学时(Integer----长度3)
	 * @param totalDur   总学时:理论学时+实验学时(Integer----长度4)
	 * @param outsideDru 课外学时数(Integer----长度4)
	 * @param remark     备注信息(String----长度2000)
	 * @return 重复属性信息
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insert(@RequestBody CourseInfo courseInfo) {
		// 调用添加数据接口
		String result = courseInfoService.insert(courseInfo);
		return ResponseUtil.success(result);
	}

	/**
	 * 更新课程信息
	 * 
	 * @param id         课程标识(Integer----长度5)
	 * @param courseCode 课程编码 (String----长度20)
	 * @param courseName 课程名称(String----长度200)
	 * @param scord      学分(double----长度3,保留小数点后1位)
	 * @param theoryDur  理论学时(Integer----长度3)
	 * @param expDur     实验学时(Integer----长度3
	 * @param totalDur   总学时:理论学时+实验学时(Integer----长度4)
	 * @param outsideDru 课外学时数(Integer----长度4)
	 * @param remark     备注信息(String----长度2000)
	 * @return 重复属性信息
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@RequestBody CourseInfo courseInfo) {
		// 更新操作
		String result = courseInfoService.update(courseInfo);
		return ResponseUtil.success(result);
	}

	/**
	 * 逻辑删除课程信息
	 * 
	 * @param id 课程标识(Integer----长度5)
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public void delete(@RequestBody Integer id) {
		courseInfoService.delete(id);
	}

	/**
	 * 批量逻辑删除课程信息
	 * 
	 * @param id 课程标识(Integer----长度5)
	 * @return 成功删除的记录数
	 */
	@RequestMapping(value = "/deleteSelection", method = RequestMethod.POST)
	public Integer deleteSelection(@RequestBody List<Integer> idList) {
		return courseInfoService.deleteSelection(idList);
	}

	/**
	 * 过滤查询
	 * 
	 * @param start      当前页(Integer----长度5)
	 * @param pageSize   页面大小(Integer----长度3,最大值为100)
	 * @param courseCode 课程编码 (String----长度20)
	 * @param courseName 课程名称 (String----长度20)
	 * @return 课程信息集合
	 */
	@RequestMapping(value = "/selectPageInfo", method = RequestMethod.POST)
	public PageInfo<CourseInfo> selectPageInfo(@RequestBody String str, Integer userId) {
		log.error("从token中获取的userId:" + userId);
		// 使用fastjson转为json对象
		JSONObject strj = JSON.parseObject(str);
		CourseInfo courseInfo = new CourseInfo();

		// 过滤查询条件
		String courseCode = strj.getString("courseCode");
		String courseName = strj.getString("courseName");
		courseInfo.setCourseCode(StringUtil.isEmpty(courseCode) ? null : courseCode);
		courseInfo.setCourseName(StringUtil.isEmpty(courseName) ? null : courseName);
		courseInfo.setCourseTypeId(
				StringUtil.isEmpty(strj.getString("courseTypeId")) ? null : strj.getIntValue("courseTypeId"));

		// 当前页码
		int pageNum = strj.getIntValue("pageNum");
		// 页面大小
		int pageSize = strj.getIntValue("pageSize");
		return courseInfoService.selectPageInfo(courseInfo, pageNum, pageSize);
	}

}
