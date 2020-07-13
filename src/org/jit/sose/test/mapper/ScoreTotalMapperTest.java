package org.jit.sose.test.mapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jit.sose.mapper.ScoreTotalMapper;
import org.jit.sose.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ScoreTotalMapperTest extends BaseTest{

	@Autowired
	private ScoreTotalMapper scoreTotalMapper;

	@Test
	public void insert() {
		
		List<Integer> couIdList = new ArrayList<Integer>(Arrays.asList(45,46,90,89,99));
		
		int result = scoreTotalMapper.insertCouId(couIdList);
		System.out.println("插入成功");
		System.out.println(result);
	}

}
