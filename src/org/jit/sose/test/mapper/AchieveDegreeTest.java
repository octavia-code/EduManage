package org.jit.sose.test.mapper;

import org.jit.sose.entity.AchieveDegree;
import org.jit.sose.mapper.AchieveDegreeMapper;
import org.jit.sose.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class AchieveDegreeTest extends BaseTest {

	@Autowired
	private AchieveDegreeMapper mapper;
	
	@Test
	public void test(){
//		AchieveDegree degree = new AchieveDegree();
//		degree.setLinedown("30");
//		degree.setLineup("40");
//		degree.setUserCode("1612011058");
//		mapper.insert(degree);
		String lineup = "30";
		AchieveDegree degree = new AchieveDegree();
		degree.setLineup(lineup);
		mapper.updateScore(degree);
	}

}
