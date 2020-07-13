package org.jit.sose.test.mapper;

import java.util.List;

import org.jit.sose.entity.AssessItem;
import org.jit.sose.entity.Assessment;
import org.jit.sose.entity.CourseOutline;
import org.jit.sose.mapper.AssessmentMapper;
import org.jit.sose.mapper.CourseOutlineMapper;
import org.jit.sose.test.BaseTest;
import org.junit.Test;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 课程大纲相关
 * 
 * @author: 王越
 * @date: 2019-07-31 11:03:35
 */
public class CourseOutlineMapperTest extends BaseTest {

	@Autowired
	private CourseOutlineMapper courseOutlineMapper;

	@Autowired
	private AssessmentMapper AssessmentMapper;

	@Test
	public void listByCourseOutline() {
		CourseOutline courseOutline = courseOutlineMapper.selectByCourseOutline("E", 1, 28);
		System.out.println(courseOutline);
		for (Assessment assessment : courseOutline.getAssessmentList()) {
			System.out.println("	" + assessment);
			for (AssessItem assessItem : assessment.getAssessItemList()) {
				System.out.println("		" + assessItem);
			}

		}
	}

	@Test
	public void listByCourseOutlineId() {
		List<Assessment> assessmentList = AssessmentMapper.listByCourseOutlineId(2);
		for (Assessment assessment : assessmentList) {
			System.out.println("	" + assessment);
			for (AssessItem assessItem : assessment.getAssessItemList()) {
				System.out.println("		" + assessItem);
			}

		}
	}
	
	@Test
	public void select(){
		CourseOutline a=new CourseOutline();
		a.setCourseInfoId(28);
		List<CourseOutline> list=courseOutlineMapper.listByCourseOutline(a);
		for(CourseOutline b:list){
			System.out.println("数据："+b.getOutlineName());
		}
	}
	
	
	
	
	
	
	
	
	
	

}
