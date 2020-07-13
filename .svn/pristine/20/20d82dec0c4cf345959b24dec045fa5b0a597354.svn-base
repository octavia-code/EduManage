package org.jit.sose.test.mapper;

import org.jit.sose.entity.Excel;
import org.jit.sose.mapper.ExcelMapper;
import org.jit.sose.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ExcelMapperTest extends BaseTest{
	
	@Autowired
	private ExcelMapper mapper;
	
	@Test
	public void insert(){
		Excel a =new Excel();
		a.setTitle("太帅");
		a.setUserId(2);
		mapper.insert(a);
		System.out.println("fanhui:"+a.getId());
	}
	
	
/*	@Test
	public void insertList(){
		List<Excel> list = new ArrayList<Excel>();
		
		Excel  a =new Excel();
		a.setUserId(1);
		a.setRowId(0);
		a.setColId(0);
		a.setContent("我爱你");
		
		Excel  b =new Excel();
		b.setUserId(1);
		b.setRowId(0);
		b.setColId(1);
		b.setContent("我不爱你");
		
		list.add(a);
		list.add(b);
		
		
		mapper.insertList(list);
		
	}
	
	
	@Test
	public void updateList(){
	List<Excel> list = new ArrayList<Excel>();
		
		Excel  a =new Excel();
		a.setUserId(1);
		a.setRowId(0);
		a.setColId(0);
        a.setRowspan(2);
        a.setColspan(2);
        a.setStyle("left");
		
		
		Excel  b =new Excel();
		b.setUserId(1);
		b.setRowId(0);
		b.setColId(1);
        b.setRowspan(3);
        b.setColspan(2);
        b.setStyle("right");
		
		list.add(a);
		list.add(b);
		
		
		mapper.updateList(list);
		
		
	}*/
	

}
