package org.jit.sose.test.mapper;

import java.util.ArrayList;
import java.util.List;

import org.jit.sose.entity.FileInfo;
import org.jit.sose.entity.GradeForm;
import org.jit.sose.entity.ScoreTotal;
import org.jit.sose.mapper.FileInfoMapper;
import org.jit.sose.mapper.GradeFormMapper;
import org.jit.sose.mapper.ScoreTotalMapper;
import org.jit.sose.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import sun.print.resources.serviceui;

/**
 * 文件mapper接口测试类
 * 
 * @author: dylan
 * @date: 2019-08-02 00:12:55
 */
public class GradeFormTest extends BaseTest {

	@Autowired
	private GradeFormMapper gradeFormMapper;
	
	@Autowired
	private ScoreTotalMapper scoreTotalMapper;

	@Test
	public void selectByGradeFromId() {
		GradeForm gradeForm = new GradeForm();
		gradeForm.setChoiceCourseNoId(14);
		gradeForm.setSchoolInfoId(1);
		gradeForm.setCourseClassInfoId(1);
		int result = gradeFormMapper.selectByGradeFromId(gradeForm);
		System.out.println(result);
	}

	@Test
	public void updateState() {
		System.out.println("进入");
		scoreTotalMapper.updateState(25, "Q");
		System.out.println("success");
	}
}
