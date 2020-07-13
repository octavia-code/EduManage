package org.jit.sose.service.impl;

import java.util.List;

import org.jit.sose.entity.EduPlan;
import org.jit.sose.mapper.EduPlanMapper;
import org.jit.sose.service.EduPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class EduPlanServiceImpl implements EduPlanService {
	@Autowired
	private EduPlanMapper eduPlanMapper;

	@Override
	public void delete(Integer id) {
		eduPlanMapper.delete(id);
	}

	@Override
	public Integer deleteSelection(List<Integer> idList) {
		return eduPlanMapper.deleteSelection(idList);
	}

	@Override
	public void insert(EduPlan eduPlan) {
		eduPlanMapper.insert(eduPlan);
	}

	@Override
	public EduPlan selectById(Integer id) {
		return eduPlanMapper.selectById(id);
	}

	@Override
	public void update(EduPlan eduPlan) {
		eduPlanMapper.update(eduPlan);
	}

	@Override
	public PageInfo<EduPlan> selectPageInfo(EduPlan eduPlan, Integer pageNum, Integer pageSize) {
		// 设置分页参数
		PageHelper.startPage(pageNum, pageSize);
		// 查询集合
		List<EduPlan> courseInfoList = eduPlanMapper.listByCourseInfo(eduPlan);
		PageInfo<EduPlan> pageInfo = new PageInfo<EduPlan>(courseInfoList);
		return pageInfo;
	}

}
