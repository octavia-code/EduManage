package org.jit.sose.service.impl;

import java.util.List;

import org.jit.sose.entity.CourseClassInfo;
import org.jit.sose.mapper.CourseClassInfoMapper;
import org.jit.sose.service.CourseClassInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class CourseClassInfoServiceImpl implements CourseClassInfoService {
	@Autowired
	private CourseClassInfoMapper courseClassInfoMapper;

	@Override
	public String insert(CourseClassInfo courseClassInfo) {
		int result = courseClassInfoMapper.countByCourseClassInfo(courseClassInfo);
		if(result > 0){
			return "exist";
		}
		courseClassInfoMapper.insert(courseClassInfo);
		return null;
	}

	@Override
	public void update(CourseClassInfo courseClassInfo) {
		courseClassInfoMapper.update(courseClassInfo);
	}

	@Override
	public void delete(Integer id) {
		courseClassInfoMapper.delete(id);
	}

	@Override
	public Integer deleteSelection(List<Integer> idList) {
		return courseClassInfoMapper.deleteSelection(idList);
	}

	@Override
	public CourseClassInfo selectById(Integer id) {
		return courseClassInfoMapper.selectById(id);
	}

	@Override
	public PageInfo<CourseClassInfo> selectPageInfo(CourseClassInfo courseClassInfo, Integer pageNum,
			Integer pageSize) {
		// 设置分页参数
		PageHelper.startPage(pageNum, pageSize);
		// 查询集合
		List<CourseClassInfo> courseClassInfoList = courseClassInfoMapper.listByCourseClassInfo(courseClassInfo);
		PageInfo<CourseClassInfo> pageInfo = new PageInfo<CourseClassInfo>(courseClassInfoList);
		return pageInfo;
	}

	@Override
	public List<CourseClassInfo> selectCourseClassInfoList() {
		return courseClassInfoMapper.selectCourseClassInfoList();
	}

	@Override
	public List<CourseClassInfo> selectByChoiceNoId(CourseClassInfo record) {
		
		return courseClassInfoMapper.selectByChoiceNoId(record);
	}


	@Override
	public List<CourseClassInfo> selectByClassId(CourseClassInfo record) {
		return courseClassInfoMapper.selectByClassId(record);
	}
	
	@Override
	public Integer getCourseClassInfoId(CourseClassInfo courseClassInfo){
		return courseClassInfoMapper.getCourseClassInfoId(courseClassInfo);
	}


}
