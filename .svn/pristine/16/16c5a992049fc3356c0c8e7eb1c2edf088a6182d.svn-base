package org.jit.sose.controller.outline;

import java.util.List;

import org.jit.sose.entity.Assessment;
import org.jit.sose.service.AssessmentService;
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
@RequestMapping("/outline/assessment")
public class AssessmentController {

	@Autowired
	private AssessmentService assessmentService;

	/**
	 * 通过大纲id查询课程考核集合<br>
	 * 作为成绩登记页面的动态表头
	 * 
	 * @param courseOutlineId 大纲id
	 * @return 课程考核集合
	 */
	@RequestMapping(value = "/listByCourseOutlineId", method = RequestMethod.POST)
	public List<Assessment> listByCourseOutlineId(@RequestBody Integer courseOutlineId) {
		return assessmentService.listByCourseOutlineId(courseOutlineId);
	}

	/**
	 * 添加课程考核
	 * 
	 * @param record
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public void insert(@RequestBody Assessment record) {
		assessmentService.insert(record);
	}

	/**
	 * 更新课程考核
	 * 
	 * @param record
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public void update(@RequestBody Assessment record) {
		assessmentService.update(record);
	}

	/**
	 * 根据id逻辑删除课程考核
	 * 
	 * @param id
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public void delete(@RequestBody Integer id) {
		assessmentService.delete(id);
	}
}
