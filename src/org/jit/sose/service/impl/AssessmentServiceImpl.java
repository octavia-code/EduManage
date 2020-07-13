package org.jit.sose.service.impl;

import java.util.List;

import org.jit.sose.entity.Assessment;
import org.jit.sose.mapper.AssessItemMapper;
import org.jit.sose.mapper.AssessmentMapper;
import org.jit.sose.service.AssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AssessmentServiceImpl implements AssessmentService {

	@Autowired
	private AssessmentMapper assessmentMapper;

	@Autowired
	private AssessItemMapper assessItemMapper;

	@Override
	public void insert(Assessment record) {
		assessmentMapper.insert(record);
	}

	@Override
	public void update(Assessment record) {
		assessmentMapper.update(record);
	}

	@Transactional
	@Override
	public void delete(Integer id) {
		// 删除课程考核
		assessmentMapper.delete(id);
		// 删除课程考核项
		assessItemMapper.deleteByAssessmentId(id);
	}

	@Override
	public List<Assessment> listByCourseOutlineId(Integer courseOutlineId) {
		return assessmentMapper.listByCourseOutlineId(courseOutlineId);
	}
}
