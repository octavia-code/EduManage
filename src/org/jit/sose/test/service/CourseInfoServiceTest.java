package org.jit.sose.test.service;

import org.jit.sose.entity.CourseInfo;
import org.jit.sose.service.CourseInfoService;
import org.jit.sose.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CourseInfoServiceTest extends BaseTest {

	@Autowired
	private CourseInfoService courseInfoService;

	@Test
	public void insert() {
		CourseInfo courseInfo = new CourseInfo();
		courseInfo.setCourseTypeId(1);
		courseInfo.setCourseCode("0303132002");
		courseInfo.setCourseName("大学生职业生涯规划指导2");
		courseInfo.setScord(0.0);
		courseInfo.setTheoryDur(1);
		courseInfo.setExpDur(1);
		courseInfo.setTotalDur(0);
		courseInfo.setOutsideDru(0);
		courseInfoService.insert(courseInfo);
	}

}
