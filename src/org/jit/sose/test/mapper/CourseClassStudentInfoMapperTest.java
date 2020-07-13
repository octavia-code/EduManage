package org.jit.sose.test.mapper;

import java.util.ArrayList;
import java.util.List;

import org.jit.sose.entity.CourseClassStudentInfo;
import org.jit.sose.entity.ScoreAssessment;
import org.jit.sose.mapper.CourseClassStudentInfoMapper;
import org.jit.sose.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CourseClassStudentInfoMapperTest extends BaseTest {
	@Autowired
	private CourseClassStudentInfoMapper mapper;

	@Test
	public void listStudentWithScore() {
		List<CourseClassStudentInfo> list = mapper.listStudentWithScore(17, 1, 26);
		for (CourseClassStudentInfo courseClassStudentInfo : list) {
			System.out.println(courseClassStudentInfo);
			List<ScoreAssessment> scoreAssessmentList = courseClassStudentInfo.getScoreAssessmentList();
			for (ScoreAssessment scoreAssessment : scoreAssessmentList) {
				System.out.println("      " + scoreAssessment);
			}
		}
	}

	@Test
	public void insert() {
		CourseClassStudentInfo a = new CourseClassStudentInfo();
		a.setCourseClassInfoId(24);
		a.setStudentInfoId(17);

		CourseClassStudentInfo b = new CourseClassStudentInfo();
		b.setCourseClassInfoId(24);
		b.setStudentInfoId(18);
		// b.setSeq(6);

		List<CourseClassStudentInfo> list = new ArrayList<CourseClassStudentInfo>();
		list.add(a);
		list.add(b);

		int aaa = mapper.insertList(list);
		System.out.println("添加了：" + aaa + "条数据");

	}

	@Test
	public void ExcelTest() {
		int courseClassInfoId = 1;
		CourseClassStudentInfo courseClassstudentInfo1 = new CourseClassStudentInfo();
		courseClassstudentInfo1.setSeq(5);
		courseClassstudentInfo1.setStudentNumber("1410103069");

		CourseClassStudentInfo courseClassstudentInfo2 = new CourseClassStudentInfo();
		courseClassstudentInfo2.setSeq(125);
		courseClassstudentInfo2.setStudentNumber("1512001080");

		List<CourseClassStudentInfo> courseClassStudentInfoList = new ArrayList<CourseClassStudentInfo>();
		courseClassStudentInfoList.add(courseClassstudentInfo1);
		courseClassStudentInfoList.add(courseClassstudentInfo2);
		int result = mapper.insertOrUpdateList(courseClassInfoId, courseClassStudentInfoList);
		System.out.println(result);
	}
}
