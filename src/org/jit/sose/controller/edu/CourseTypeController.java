package org.jit.sose.controller.edu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jit.sose.entity.CourseType;
import org.jit.sose.service.CourseTypeService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;

import lombok.extern.slf4j.Slf4j;

/**
 * 课程信息控制器类
 * 
 * @author single-聪
 *
 */
@Slf4j
@RestController
@RequestMapping("/edu/courseType")
public class CourseTypeController {

	@Autowired
	private CourseTypeService courseTypeService;

	/**
	 * 查询课程类别和id集合
	 * 
	 * @author 王越
	 * @return 课程类别和id集合
	 */
	@RequestMapping(value = "/selectCourseTypeList", method = RequestMethod.GET)
	public List<CourseType> selectCourseTypeList() {
		return courseTypeService.selectCourseTypeList();
	}

	/**
	 * 新增前查询是否有相同课程类别
	 * 
	 * @param typeName 要新增的类别名称(String----长度200)
	 * @return 是否有相同的名称
	 */
	@RequestMapping(value = "/checkInsert", method = RequestMethod.POST)
	public Map<String, Object> checkInsert(@RequestBody String str) {
		Map<String, Object> map = new HashMap<>();
		JSONObject strj = new JSONObject(str);
		CourseType coursetype = new CourseType();
		// 类别名称
		coursetype.setTypeName(strj.getString("typeName"));
		List<CourseType> nowData = courseTypeService.checkInsert(coursetype);
		if (nowData.size() > 0) {
			map.put("info", "same");
			CourseType courseType2 = nowData.get(0);
			if (courseType2.getTypeName().equals(coursetype.getTypeName())) {
				map.put("name", "same");
			} else {
				map.put("name", "none");
			}
		} else {
			map.put("info", "none");
		}
		return map;
	}

	/**
	 * 编辑前查询是否有重复的名称
	 * 
	 * @param id       要修改名称的字段的id(Integer----长度2)
	 * @param typeName 要修改的类别名称(String----长度200)
	 * @return 是否存在相同的名称
	 */
	@RequestMapping(value = "/checkUpdate", method = RequestMethod.POST)
	public Map<String, Object> checkUpdate(@RequestBody CourseType courseType) {
		Map<String, Object> map = new HashMap<>();
		List<CourseType> nowData = courseTypeService.checkUpdate(courseType);
		if (nowData.size() > 0) {
			map.put("info", "same");
			CourseType courseType2 = nowData.get(0);
			if (courseType2.getTypeName().equals(courseType.getTypeName())) {
				map.put("name", "same");
			} else {
				map.put("name", "none");
			}
		} else {
			map.put("info", "none");
		}
		return map;
	}

	/**
	 * 过滤查询
	 * 
	 * @param start    当前页(Integer----长度5)
	 * @param pageSize 页面大小(Integer----长度3,最大值为100)
	 * @param typeName 类别名称 (String----长度20)
	 * @return 课程类别信息集合
	 */
	@RequestMapping(value = "/listByCourseType", method = RequestMethod.POST)
	public PageInfo<CourseType> listByCourseType(@RequestBody String str) {
		JSONObject strj = new JSONObject(str);
		CourseType courseType = new CourseType();
		// 过滤查询条件
		courseType.setTypeName("".equals(strj.getString("typeName")) ? null : strj.getString("typeName"));

		// 当前页索引
		Integer pageNum = strj.getInt("pageNum");
		// 当前页页面大小
		Integer pageSize = strj.getInt("pageSize");

		return courseTypeService.listByCourseType(courseType, pageNum, pageSize);
	}

	/**
	 * 倒序过滤查询
	 * 
	 * @param start    当前页(Integer----长度5)
	 * @param pageSize 页面大小(Integer----长度3,最大值为100)
	 * @param typeName 类别名称 (String----长度20)
	 * @return 课程类别信息集合
	 */
	@RequestMapping(value = "/listByCourseTypeDESC", method = RequestMethod.POST)
	public PageInfo<CourseType> listByCourseTypeDESC(@RequestBody String str) {
		JSONObject strj = new JSONObject(str);
		CourseType courseType = new CourseType();
		// 过滤查询条件
		courseType.setTypeName("".equals(strj.getString("typeName")) ? null : strj.getString("typeName"));
		// 当前页索引
		Integer pageNum = strj.getInt("pageNum");
		// 当前页页面大小
		Integer pageSize = strj.getInt("pageSize");

		return courseTypeService.listByCourseTypeDESC(courseType, pageNum, pageSize);
	}

	/**
	 * 新增课程类别
	 * 
	 * @param typeName 类别名称(String----长度200)
	 * @return success/fail
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public void insert(@RequestBody CourseType courseType) {
		courseTypeService.insert(courseType);
	}

	/**
	 * 更新课程类别
	 * 
	 * @param typeName 类别名称(String----长度200)
	 * @return success/fail
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public void update(@RequestBody CourseType courseType) {

		courseTypeService.update(courseType);

	}

	/**
	 * 逻辑删除课程信息
	 * 
	 * @param id 课程标识(Integer----长度5)
	 * @return success/fail
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public void delete(@RequestBody Integer id) {
		courseTypeService.delete(id);
	}

	/**
	 * 批量逻辑删除课程信息
	 * 
	 * @param id 课程标识(Integer----长度5)
	 * @return success/fail
	 */
	@RequestMapping(value = "/deleteSelection", method = RequestMethod.POST)
	public void deleteSelection(@RequestBody List<Integer> idList) {
		courseTypeService.deleteSelection(idList);
	}

}
