package org.jit.sose.service.impl;

import java.util.List;

import org.jit.sose.entity.Specialty;
import org.jit.sose.mapper.SpecialtyMapper;
import org.jit.sose.service.SpecialtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class SpecialtyServiceImpl implements SpecialtyService {
	

	@Autowired
	private SpecialtyMapper specialtyMapper;

	@Override
	public 	String insert(Specialty specialty) {
		int result = specialtyMapper.countBySpecialty(specialty);
		if(result>0){
			return "exist";
		}
		specialtyMapper.insert(specialty);
		return null;
	}

	@Override
	public void update(Specialty specialty) {
		specialtyMapper.update(specialty);
	
	}

	@Override
	public void delete(Integer id) {
		specialtyMapper.delete(id);
		
		
	}

	@Override
	public Integer deleteSelection(List<Integer> idList) {
		return specialtyMapper.deleteSelection(idList);
	
		
	}

	@Override
	public PageInfo<Specialty> selectPageInfo(Specialty specialty, Integer pageNum, Integer pageSize) {
		// 设置分页参数
		PageHelper.startPage(pageNum, pageSize);
		// 查询集合
		List<Specialty> specialtyList = specialtyMapper.listBySpecialty(specialty);
		PageInfo<Specialty> pageInfo = new PageInfo<Specialty>(specialtyList);
		return pageInfo;
	}
	@Override
	public Specialty selectById(Integer id) {
		return specialtyMapper.selectById(id);
	}

	@Override
	public List<Specialty> selectSpecialtyList() {
		List<Specialty> specialtyList = specialtyMapper.selectSpecialtyList();
		return specialtyList;
	}

	@Override
	public List<Specialty> selectBySpecialty(Specialty specialty) {
		List<Specialty> specialtyList = specialtyMapper.selectBySpecialty(specialty);
		return specialtyList;
	}



	

}
