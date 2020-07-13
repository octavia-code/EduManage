package org.jit.sose.test.mapper;

import org.jit.sose.entity.IndicatorSec;
import org.jit.sose.mapper.IndicatorSecMapper;
import org.jit.sose.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class IndicatorSecMapperTest extends BaseTest {
	
	@Autowired
	private IndicatorSecMapper mapper;
	
	@Test 
	public void update(){
		IndicatorSec record =new IndicatorSec();
		record.setId(47);
		record.setYearPlanId(17);
		record.setIndicatorFirstId(27);
		record.setContent("中国制造");
		record.setUserId(1);
		record.setSeq(2);
		
		mapper.update(record);
	}

}
