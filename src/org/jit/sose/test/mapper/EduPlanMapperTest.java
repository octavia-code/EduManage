package org.jit.sose.test.mapper;

import org.jit.sose.entity.EduPlan;
import org.jit.sose.mapper.EduPlanMapper;
import org.jit.sose.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class EduPlanMapperTest extends BaseTest {
	@Autowired
	private EduPlanMapper eduPlanMapper;

	@Test
	public void selectById() {
		EduPlan eduPlan = eduPlanMapper.selectById(1);
		System.out.println(eduPlan);
		logger.debug(eduPlan.toString());
	}
	

	@Test
	public void delete() {
		eduPlanMapper.delete(7);
	}
}
