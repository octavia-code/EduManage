package org.jit.sose.test.mapper;

import org.jit.sose.entity.Specialty;
import org.jit.sose.mapper.SpecialtyMapper;
import org.jit.sose.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class SpecialtyMapperTest extends BaseTest{

	@Autowired
	private SpecialtyMapper specialtyMapper;

	@Test
	public void insert(){
		Specialty specialty = new Specialty();
		specialty.setSchoolInfoId(1);
		specialty.setSubjectName("软件工程");
		specialtyMapper.insert(specialty);
	}
}
