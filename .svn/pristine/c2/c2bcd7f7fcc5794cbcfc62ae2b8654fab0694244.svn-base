package org.jit.sose.service.impl;

import java.util.List;

import org.jit.sose.entity.ClassInfo;
import org.jit.sose.mapper.ClassInfoMapper;
import org.jit.sose.service.ClassInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class ClassInfoServiceImpl implements ClassInfoService {
	@Autowired
	private ClassInfoMapper classInfoMapper;

	@Override
	public void insert(ClassInfo classInfo) {
		classInfoMapper.insert(classInfo);
	}

	@Override
	public void update(ClassInfo classInfo) {
		classInfoMapper.update(classInfo);
	}

	@Override
	public void delete(Integer id) {
		classInfoMapper.delete(id);
	}

	@Override
	public Integer deleteSelection(List<Integer> idList) {
		return classInfoMapper.deleteSelection(idList);
	}

	@Override
	public ClassInfo selectById(Integer id) {
		return classInfoMapper.selectById(id);
	}

	@Override
	public PageInfo<ClassInfo> listClassName(ClassInfo classInfo, Integer pageNum, Integer pageSize) {
		// 设置分页参数
		PageHelper.startPage(pageNum, pageSize);
		// 查询集合
		List<ClassInfo> classInfoList = classInfoMapper.listByClassInfo(classInfo);
		PageInfo<ClassInfo> pageInfo = new PageInfo<ClassInfo>(classInfoList);
		return pageInfo;
	}

	@Override
	public List<ClassInfo> selectClassInfoList() {
		return classInfoMapper.selectClassInfoList();
	}

	@Override
	public List<ClassInfo> listBySpecialtyId(ClassInfo classInfo) {
		return classInfoMapper.listBySpecialtyId(classInfo);
	}

	@Override
	public List<ClassInfo> listByYearPlanId(ClassInfo classInfo) {
		return classInfoMapper.listByYearPlanId(classInfo);
	}
	
	@Override
	public List<ClassInfo> getClassNameByYearPlanIdSpecialtyId(ClassInfo classInfo) {
		return classInfoMapper.getClassNameByYearPlanIdSpecialtyId(classInfo);
	}
}
