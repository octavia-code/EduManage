package org.jit.sose.test.mapper;

import java.util.ArrayList;
import java.util.List;

import org.jit.sose.entity.CourseInfo;
import org.jit.sose.entity.ScoreTotal;
import org.jit.sose.mapper.CourseInfoMapper;
import org.jit.sose.mapper.ScoreTotalMapper;
import org.jit.sose.service.CourseInfoService;
import org.jit.sose.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CourseInfoMapperTest extends BaseTest {

	@Autowired
	private CourseInfoMapper courseInfoMapper;

	@Autowired
	private CourseInfoService courseInfoService;

	@Autowired
	private ScoreTotalMapper scoreTotalMapper;

	@Test
	public void insert123() {
//		ScoreTotal scoreTotal = new ScoreTotal(1, 99.7);
//		scoreTotalMapper.insert(scoreTotal);
	}

	@Test
	public void selectById2() {
		System.out.println(courseInfoService.selectById(28));
	}

	@Test
	public void deleteSelection() {
		List<Integer> idList = new ArrayList<Integer>();
		idList.add(89);
		idList.add(90);
		idList.add(91);
		int result = courseInfoMapper.deleteSelection(idList);
		System.out.println(result);
	}

	@Test
	public void selectById() {
		System.out.println(courseInfoMapper.selectById(28));
	}
	
	@Test
	public void delete() {
		courseInfoMapper.delete(30);
	}

	@Test
	public void insert() {
		CourseInfo courseInfo = new CourseInfo();
		courseInfo.setCourseTypeId(1);
		courseInfo.setCourseName("test3");
		courseInfo.setCourseCode("0305128001");
		courseInfo.setScord(3.0);
		courseInfo.setTheoryDur(0);
		courseInfo.setExpDur(0);
		courseInfo.setTotalDur(0);
		courseInfo.setOutsideDru(0);
		courseInfo.setRemark(null);
		courseInfoMapper.insert(courseInfo);
		System.out.println(courseInfo.getId());
	}

	@Test
	public void countByCourseInfo() {
		CourseInfo courseInfo = new CourseInfo();
		courseInfo.setCourseCode("0809412108");
		System.out.println(courseInfoMapper.countByCourseInfo(courseInfo));
	}

}
