package org.jit.sose.test.service;

import java.util.ArrayList;
import java.util.List;

import org.jit.sose.service.CourseClassStudentInfoService;
import org.jit.sose.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CourseClassStudentInfoServiceTest extends BaseTest {
	@Autowired
	private CourseClassStudentInfoService service;

	@Test
	public void insert() {
		List<Integer> stuIdList = new ArrayList<Integer>();
		stuIdList.add(16);
		stuIdList.add(17);
		stuIdList.add(18);
		int result = service.insertList(23, stuIdList);
		System.out.println("添加了：" + result + "条数据");

	}

}
