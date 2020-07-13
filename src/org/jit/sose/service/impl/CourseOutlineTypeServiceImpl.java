package org.jit.sose.service.impl;

import java.util.List;

import org.jit.sose.entity.CourseOutlineType;
import org.jit.sose.mapper.CourseOutlineTypeMapper;
import org.jit.sose.service.CourseOutlineTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseOutlineTypeServiceImpl implements CourseOutlineTypeService {

	@Autowired
	private CourseOutlineTypeMapper courseOutlineTypeMapper;

	@Override
	public List<CourseOutlineType> ListCourseOutlineType() {
		return courseOutlineTypeMapper.ListCourseOutlineType();
	}
}
