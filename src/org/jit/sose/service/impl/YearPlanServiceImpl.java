package org.jit.sose.service.impl;

import java.util.List;

import org.jit.sose.entity.YearPlan;
import org.jit.sose.mapper.YearPlanMapper;
import org.jit.sose.service.YearPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class YearPlanServiceImpl implements YearPlanService {
	@Autowired
	private YearPlanMapper yearPlanMapper;

	@Override
	public void insert(YearPlan yearPlan) {
		yearPlanMapper.insert(yearPlan);
	}
	
	@Override
	public void update(YearPlan yearPlan) {
		yearPlanMapper.update(yearPlan);
	}
	
	@Override
	public void delete(Integer id) {
		yearPlanMapper.delete(id);
	}

	@Override
	public Integer deleteSelection(List<Integer> idList) {
		return yearPlanMapper.deleteSelection(idList);
	}
	
	@Override
	public PageInfo<YearPlan> selectPageInfo(YearPlan yearPlan, Integer pageNum, Integer pageSize) {
		// 设置分页参数
		PageHelper.startPage(pageNum, pageSize);
		// 查询集合
		List<YearPlan> yearList = yearPlanMapper.listByYearPlan(yearPlan);
		PageInfo<YearPlan> pageInfo = new PageInfo<YearPlan>(yearList);
		return pageInfo;
	}

	@Override
	public List<YearPlan> selectYearPlanList() {
		List<YearPlan> yearPlanList = yearPlanMapper.selectYearPlanList();
		return yearPlanList;
	}
	@Override
	public YearPlan selectById(Integer id) {
		return yearPlanMapper.selectById(id);
	}

}
