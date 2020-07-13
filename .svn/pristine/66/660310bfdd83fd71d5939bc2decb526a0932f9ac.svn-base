package org.jit.sose.test.mapper;
		

import java.util.List;

import org.jit.sose.entity.ClassStudent;
import org.jit.sose.mapper.ClassStudentMapper;
import org.jit.sose.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ClassStudentMapperTest extends BaseTest{

	@Autowired
	private ClassStudentMapper mapper;		
	
	@Test
	public void select(){
		ClassStudent a=new ClassStudent();
		/*a.setStudentName("洪宇");*/
		a.setSchoolInfoId(2);
		List<ClassStudent> list=mapper.listByClassStudent(a);
		for(ClassStudent bbb:list){
			System.out.println("模糊查询数据为："+bbb);
		}
		
	}
	
}
