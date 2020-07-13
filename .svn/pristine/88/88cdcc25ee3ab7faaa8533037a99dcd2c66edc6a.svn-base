package org.jit.sose.controller.outline;

import java.util.List;

import org.jit.sose.entity.CourseOutline;
import org.jit.sose.entity.vo.CourseOutlineTreeVo;
import org.jit.sose.service.CourseOutlineService;
import org.jit.sose.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 课程考核
 * 
 * @author: 王越
 * @date: 2019-07-30 17:53:03
 */
@RestController
@RequestMapping("/outline/courseOutline")
public class CourseOutlineController {

	@Autowired
	private CourseOutlineService courseOutlineService;

	/**
	 * 查询任务id和任务名称集合
	 * 
	 * @return 任务id和任务名称集合
	 */
	@RequestMapping(value = "/selectCourseOutlineList", method = RequestMethod.GET)
	public List<CourseOutline> selectCourseOutlineList() {
		List<CourseOutline> courseOutlineList = courseOutlineService.selectCourseOutlineList();
		return courseOutlineList;
	}
	
	/**
	 * 查询课程大纲集合树，连同其课程考核和课程考核项
	 * 
	 * @param courseOutline 课程大纲条件对象
	 * @return 课程大纲集合树
	 */
	@RequestMapping(value = "/selectCourseOutlineTree", method = RequestMethod.POST)
	public CourseOutlineTreeVo selectCourseOutlineTree(@RequestBody CourseOutline courseOutline) {
		CourseOutlineTreeVo courseOutlineTreeVo = courseOutlineService.selectCourseOutlineTree(
				courseOutline.getOutlineNo(), courseOutline.getCourseOutlineTypeId(), courseOutline.getCourseInfoId());
		return courseOutlineTreeVo;
	}

	/**
	 * 在域表中查询大纲版本号
	 * 
	 * @return
	 */
	@RequestMapping(value = "/listOutlineNoByEecstate", method = RequestMethod.GET)
	public List<CourseOutline> listOutlineNoByEecstate() {
		return courseOutlineService.listOutlineNoByEecstate();
	}

	/**
	 * 检查大纲是否存在
	 * 
	 * @param record
	 * @return exist:记录已存在；null:正常执行
	 */
	@RequestMapping(value = "/checkIsExist", method = RequestMethod.POST)
	public String checkIsExist(@RequestBody CourseOutline record) {
		// 先查询是否已存在记录
		String result = courseOutlineService.checkIsExist(record);
		return ResponseUtil.success(result);
	}

	/**
	 * 添加大纲
	 * 
	 * @param record
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public void insert(@RequestBody CourseOutline record) {
		courseOutlineService.insert(record);
	}
	/**
	 * 更新课程大纲
	 * 
	 * @param record
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public void update(@RequestBody CourseOutline record){
		courseOutlineService.update(record);
	}
	
	/**
	 * 通过课程大纲信息查询具体记录
	 * 
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/listByCourseOutline", method = RequestMethod.POST)
	public List<CourseOutline> listByCourseOutline(@RequestBody CourseOutline record) {
		return courseOutlineService.listByCourseOutline(record);
	}
	/**
	 * 通过id逻辑删除课程大纲
	 * 
	 * @param id
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public void delete(@RequestBody Integer id){
		courseOutlineService.delete(id);
	}
	
	
}
