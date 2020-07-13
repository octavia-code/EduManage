package org.jit.sose.service.impl;

import java.util.List;

import org.jit.sose.entity.IndicatorFirst;
import org.jit.sose.mapper.IndicatorFirstMapper;
import org.jit.sose.service.IndicatorFirstService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class IndicatorFirstServiceImpl implements IndicatorFirstService {
	@Autowired
	private IndicatorFirstMapper indicatorFirstMapper;

	@Override
	public void insert(IndicatorFirst indicatorFirst) {
		indicatorFirstMapper.insert(indicatorFirst);

	}

	@Override
	public void update(IndicatorFirst indicatorFirst) {
		indicatorFirstMapper.update(indicatorFirst);
	}

	@Override
	public void delete(Integer id) {
		indicatorFirstMapper.delete(id);
	}

	@Override
	public Integer deleteSelection(List<Integer> idList) {
		return indicatorFirstMapper.deleteSelection(idList);
	}

	@Override
	public PageInfo<IndicatorFirst> selectPageInfo(IndicatorFirst indicatorFirst, Integer pageNum, Integer pageSize) {
		// 设置分页参数
		PageHelper.startPage(pageNum, pageSize);
		// 查询集合
		List<IndicatorFirst> indicatorFirstList = indicatorFirstMapper.listByIndicatorFirst(indicatorFirst);
		PageInfo<IndicatorFirst> pageInfo = new PageInfo<IndicatorFirst>(indicatorFirstList);
		return pageInfo;
	}

	@Override
	public IndicatorFirst selectById(Integer id) {
		return indicatorFirstMapper.selectById(id);
	}

	@Override
	public List<IndicatorFirst> selectIndicatorFirstList() {
		return indicatorFirstMapper.selectIndicatorFirstList();
	}
	
	@Override
	public List<IndicatorFirst> selectIndicatorFirstListByYearPlanId(Integer yearPlanId) {
		return indicatorFirstMapper.selectIndicatorFirstListByYearPlanId(yearPlanId);
	}
}
