package org.jit.sose.controller.outline;

import java.util.List;

import org.jit.sose.entity.CourseOutlineType;
import org.jit.sose.service.CourseOutlineTypeService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/outline/courseOutlineType")
public class CourseOutlineTypeController {

	@Autowired
	private CourseOutlineTypeService courseOutlineTypeService;

	/**
	 * 查询课程大纲类别集合
	 * 
	 * @return 课程大纲类别集合
	 */
	@RequestMapping(value = "/ListCourseOutlineType", method = RequestMethod.GET)
	public List<CourseOutlineType> ListCourseOutlineType() {
		return courseOutlineTypeService.ListCourseOutlineType();
	}

}
