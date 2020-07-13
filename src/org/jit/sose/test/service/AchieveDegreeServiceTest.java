package org.jit.sose.test.service;

import org.jit.sose.entity.AchieveDegree;
import org.jit.sose.service.AchieveDegreeService;
import org.jit.sose.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class AchieveDegreeServiceTest extends BaseTest {


	@Autowired
	private AchieveDegreeService service;
	
	
	@Test
	public void test(){
		String lineup = "30";
		AchieveDegree degree = new AchieveDegree();
		degree.setLineup(lineup);
		service.updateScore(degree);
	}
}
