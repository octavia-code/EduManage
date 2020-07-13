package org.jit.sose.service.impl;

import java.util.List;

import org.jit.sose.entity.CourseProp;
import org.jit.sose.mapper.CoursePropMapper;
import org.jit.sose.service.CoursePropService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class CoursePropServiceImpl implements CoursePropService {

	@Autowired
	private CoursePropMapper coursePropMapper;

	@Override
	public PageInfo<CourseProp> listByCourseProp(CourseProp courseProp, Integer pageNum, Integer pageSize) {
		// 设置分页参数
		PageHelper.startPage(pageNum, pageSize);
		// 查询集合
		List<CourseProp> coursePropList = coursePropMapper.listByCourseProp(courseProp);
		PageInfo<CourseProp> pageInfo = new PageInfo<CourseProp>(coursePropList);
		return pageInfo;
	}

	@Override
	public void insert(CourseProp courseProp) {
		coursePropMapper.insert(courseProp);
	}

	@Override
	public void update(CourseProp courseProp) {
		coursePropMapper.update(courseProp);
	}

	@Override
	public void delete(Integer id) {
		coursePropMapper.delete(id);
	}

	@Override
	public Integer deleteSelection(List<Integer> idList) {
		return coursePropMapper.deleteSelection(idList);
	}

	@Override
	public List<CourseProp> selectCoursePropList() {
		return coursePropMapper.selectCoursePropList();
	}

}
