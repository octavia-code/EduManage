package org.jit.sose.controller.info;

import java.util.List;
import org.jit.sose.entity.CourseClassInfo;
import org.jit.sose.entity.StudentInfo;
import org.jit.sose.service.CourseClassInfoService;
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

/**
 * 课程班级控制类
 *
 * @author 王丹梦 陆杏智
 * @date 2019年7月3日 下午4:20:47
 */
@RestController
@RequestMapping("/info/courseClassInfo")
public class CourseClassInfoController {
	@Autowired
	private CourseClassInfoService courseClassInfoService;

	/**
	 * 查询课程班级信息id和选课课号集合
	 * 
	 * @return 课程班级信息id和选课课号集合
	 */
	@RequestMapping(value = "/selectCourseClassInfoList", method = RequestMethod.GET)
	public List<CourseClassInfo> selectCourseClassInfoList() {
		List<CourseClassInfo> courseClassInfoList = courseClassInfoService.selectCourseClassInfoList();
		return courseClassInfoList;
	}

	/**
	 * 插入课程班级信息
	 * 
	 * @param choiceCourseNoId
	 *            选课课号
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insert(@RequestBody CourseClassInfo courseClassInfo) {
/*		// 验证数据是否为空
		DataExceptionUtil.stringEmpty(courseClassInfo.getChoiceCourseNoId(), "选课课号不能为空");
		// 验证数据长度
		DataExceptionUtil.stringLength(courseClassInfo.getChoiceCourseNoId(), 50, "选课课号内容长度最多为50");*/
		// 调用添加数据接口
		String result = courseClassInfoService.insert(courseClassInfo);
		return ResponseUtil.success(result);
	}

	/**
	 * 更新课程班级信息
	 * 
	 * @param choiceCourseNoId
	 *            选课课号
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public void update(@RequestBody CourseClassInfo courseClassInfo) {
/*		// 验证数据是否为空
		DataExceptionUtil.stringEmpty(courseClassInfo.getChoiceCourseNoId(), "选课课号不能为空");
		// 验证数据长度
		DataExceptionUtil.stringLength(courseClassInfo.getChoiceCourseNoId(), 50, "选课课号内容长度最多为50");*/
		// 更新操作
		courseClassInfoService.update(courseClassInfo);
	}

	/**
	 * 逻辑删除课程班级信息
	 * 
	 * @param id
	 *            课程班级信息标识(Integer----长度5)
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public void delete(@RequestBody Integer id) {
		courseClassInfoService.delete(id);
	}

	/**
	 * 批量逻辑删除课程班级信息
	 * 
	 * @param id
	 *            课程班级信息标识(Integer----长度5)
	 * @return map
	 */
	@RequestMapping(value = "/deleteSelection", method = RequestMethod.POST)
	public Integer deleteSelection(@RequestBody List<Integer> idList) {
		return courseClassInfoService.deleteSelection(idList);
	}

	/**
	 * 过滤查询
	 * 
	 * @param start
	 *            当前页(Integer----长度5)
	 * @param pageSize
	 *            页面大小(Integer----长度3,最大值为100)
	 * @param choiceCourseNoId
	 *            选课课号(String----长度50)
	 * @return 课程班级信息集合
	 */
	@RequestMapping(value = "/selectPageInfo", method = RequestMethod.POST)
	public PageInfo<CourseClassInfo> selectPageInfo(@RequestBody String str) {
		// 使用fastjson转为json对象
		JSONObject strj = JSON.parseObject(str);
		CourseClassInfo courseClassInfo = new CourseClassInfo();
		// 过滤查询条件
        courseClassInfo.setChoiceCourseNoId(
		        StringUtil.isEmpty(strj.getString("choiceCourseNoId"))? null : strj.getIntValue("choiceCourseNoId"));

		String yearTermId = strj.getString("yearTermId");
		courseClassInfo.setYearTermId(StringUtil.isEmpty(yearTermId) ? null : strj.getIntValue("yearTermId"));

		String courseInfoId = strj.getString("courseInfoId");
		courseClassInfo.setCourseInfoId(StringUtil.isEmpty(courseInfoId) ? null : strj.getIntValue("courseInfoId"));

		String courseOutlineId = strj.getString("courseOutlineId");
		courseClassInfo.setCourseOutlineId(StringUtil.isEmpty(courseOutlineId) ? null : strj.getIntValue("courseOutlineId"));
		
		String staffId = strj.getString("staffId");
		courseClassInfo.setStaffId(StringUtil.isEmpty(staffId) ? null : strj.getIntValue("staffId"));
		
		String schoolInfoId = strj.getString("schoolInfoId");
		courseClassInfo.setSchoolInfoId(StringUtil.isEmpty(schoolInfoId) ? null : strj.getIntValue("schoolInfoId"));
		
		String specialtyId = strj.getString("specialtyId");
		courseClassInfo.setSpecialtyId(StringUtil.isEmpty(specialtyId) ? null : strj.getIntValue("specialtyId"));

		String classInfoId = strj.getString("classInfoId");
		courseClassInfo.setClassInfoId(StringUtil.isEmpty(classInfoId) ? null : strj.getIntValue("classInfoId"));
		// 当前页码
		int pageNum = strj.getIntValue("pageNum");
		// 页面大小
		int pageSize = strj.getIntValue("pageSize");
		return courseClassInfoService.selectPageInfo(courseClassInfo, pageNum, pageSize);
	}

	
	/**
	 * 查询课程班级信息id和选课课号集合
	 * 
	 * @return 课程班级信息id和选课课号集合
	 */
	@RequestMapping(value = "/selectByChoiceNoId", method = RequestMethod.POST)
	public List<CourseClassInfo> selectByChoiceNoId(@RequestBody CourseClassInfo record) {
		List<CourseClassInfo> courseClassInfoList = courseClassInfoService.selectByChoiceNoId(record);
		return courseClassInfoList;
	}
	
	/**
	 * 通过班级id查课程班级id
	 * 
	 * @param record
	 * @return
	 */
	 @RequestMapping(value = "/selectByClassId", method = RequestMethod.POST)
		public List<CourseClassInfo> selectByClassId(@RequestBody CourseClassInfo record) {
			List<CourseClassInfo> courseClassInfoList = courseClassInfoService.selectByClassId(record);
			return courseClassInfoList;
		}

	
	/**
	 * 通过学院id 学年id 课程id 选课号id 教工id 班级id 查询课程班级id
	 */
	@RequestMapping(value = "/getCourseClassInfoId",method = RequestMethod.POST)
	public Integer getCourseClassInfoId(@RequestBody CourseClassInfo courseClassInfo){
		return courseClassInfoService.getCourseClassInfoId(courseClassInfo);
	}
	


	
	
}
