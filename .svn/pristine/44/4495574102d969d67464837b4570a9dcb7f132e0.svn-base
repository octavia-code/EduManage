package org.jit.sose.service.impl;

import java.util.List;

import org.jit.sose.entity.CourseInfo;
import org.jit.sose.mapper.CourseInfoMapper;
import org.jit.sose.service.CourseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class CourseInfoServiceImpl implements CourseInfoService {

	@Autowired
	private CourseInfoMapper courseInfoMapper;

	@Override
	public List<CourseInfo> listCourseInfo() {
		return courseInfoMapper.listCourseInfo();
	}

	@Transactional
	@Override
	public String insert(CourseInfo courseInfo) {
		// 判断courseCode是否存在
		int result = courseInfoMapper.countByCourseInfo(courseInfo);
		
		if (result > 0) {
			return "code_exist";
		}
		courseInfoMapper.insert(courseInfo);
		return null;
	}

	@Override
	public String update(CourseInfo courseInfo) {
		// 判断courseCode是否存在，去除当前记录
		int result = courseInfoMapper.countByCourseInfo(courseInfo);
		if (result > 0) {
			return "code_exist";
		}
		courseInfoMapper.update(courseInfo);
		return null;
	}

	@Override
	public void delete(Integer id) {
		courseInfoMapper.delete(id);
	}

	@Override
	public Integer deleteSelection(List<Integer> idList) {
		return courseInfoMapper.deleteSelection(idList);
	}

	@Override
	public PageInfo<CourseInfo> selectPageInfo(CourseInfo courseInfo, Integer pageNum, Integer pageSize) {
		// 设置分页参数
		PageHelper.startPage(pageNum, pageSize);
		// 查询集合
		List<CourseInfo> courseInfoList = courseInfoMapper.listByCourseInfo(courseInfo);
		PageInfo<CourseInfo> pageInfo = new PageInfo<>(courseInfoList);
		return pageInfo;
	}

	@Override
	public CourseInfo selectById(Integer id) {
		return courseInfoMapper.selectById(id);
	}

}
