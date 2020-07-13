package org.jit.sose.service.impl;

import java.util.List;

import org.jit.sose.entity.CourseType;
import org.jit.sose.mapper.CourseTypeMapper;
import org.jit.sose.service.CourseTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class CourseTypeServiceImpl implements CourseTypeService {

	@Autowired
	private CourseTypeMapper courseTypeMapper;

	@Override
	public PageInfo<CourseType> listByCourseType(CourseType courseType, Integer pageNum, Integer pageSize) {
		// TODO Auto-generated method stub
		// 设置分页参数
		PageHelper.startPage(pageNum, pageSize);
		// 查询集合
		List<CourseType> courseTypesList = courseTypeMapper.listByCourseType(courseType);
		PageInfo<CourseType> pageInfo = new PageInfo<CourseType>(courseTypesList);
		return pageInfo;
	}

	@Override
	public void insert(CourseType courseType) {
		courseTypeMapper.insert(courseType);

	}

	@Override
	public void update(CourseType courseType) {
		courseTypeMapper.update(courseType);

	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		courseTypeMapper.delete(id);

	}

	@Override
	public void deleteSelection(List<Integer> idList) {
		courseTypeMapper.deleteSelection(idList);
	}

	@Override
	public PageInfo<CourseType> listByCourseTypeDESC(CourseType courseType, Integer pageNum, Integer pageSize) {
		// TODO Auto-generated method stub
		// 设置分页参数
		PageHelper.startPage(pageNum, pageSize);
		// 查询集合
		List<CourseType> courseTypesList = courseTypeMapper.listByCourseTypeDESC(courseType);
		PageInfo<CourseType> pageInfo = new PageInfo<CourseType>(courseTypesList);
		return pageInfo;
	}

	@Override
	public List<CourseType> checkInsert(CourseType courseType) {
		// TODO Auto-generated method stub
		return courseTypeMapper.checkInsert(courseType);
	}

	@Override
	public List<CourseType> checkUpdate(CourseType courseType) {
		// TODO Auto-generated method stub
		return courseTypeMapper.checkUpdate(courseType);
	}

	@Override
	public List<CourseType> selectCourseTypeList() {
		return courseTypeMapper.selectCourseTypeList();
	}

}
