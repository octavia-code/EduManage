package org.jit.sose.test.mapper;

import java.util.List;

import org.jit.sose.entity.YearPlan;
import org.jit.sose.service.YearPlanService;
import org.jit.sose.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class YearPlanMapperTest extends BaseTest {
	@Autowired
	private YearPlanService mapper;
	
	@Test
	public void list(){
		List<YearPlan> list=	mapper.selectYearPlanList();
		System.out.println("数据为："+list);
		
	}
	

}
