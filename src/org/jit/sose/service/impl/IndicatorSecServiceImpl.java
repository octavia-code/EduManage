package org.jit.sose.service.impl;

import java.util.List;

import org.jit.sose.entity.IndicatorSec;
import org.jit.sose.mapper.IndicatorSecMapper;
import org.jit.sose.service.IndicatorSecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class IndicatorSecServiceImpl implements IndicatorSecService {

	@Autowired
	private IndicatorSecMapper indicatorSecMapper;
	
	@Override
	public void delete(Integer id) {
		indicatorSecMapper.delete(id);
	}
	
	@Override
	public Integer deleteSelection(List<Integer> idList) {
		return indicatorSecMapper.deleteSelection(idList);
	}
	
	@Override
	public void insert(IndicatorSec indicatorSec) {
		indicatorSecMapper.insert(indicatorSec);
	}

	@Override
	public IndicatorSec selectById(Integer id) {
		return indicatorSecMapper.selectById(id);
	}
	
	@Override
	public void update(IndicatorSec indicatorSec) {
		// 更新原数据
		indicatorSecMapper.update(indicatorSec);
	}

	@Override
	public PageInfo<IndicatorSec> selectPageInfo(IndicatorSec indicatorSec, Integer pageNum, Integer pageSize) {
		// 设置分页参数
		PageHelper.startPage(pageNum, pageSize);
		// 查询集合
		List<IndicatorSec> indicatorSecList = indicatorSecMapper.listByIndicatorSec(indicatorSec.getYearPlanId(),indicatorSec.getIndicatorFirstId());
		PageInfo<IndicatorSec> pageInfo = new PageInfo<IndicatorSec>(indicatorSecList);
		return pageInfo;
	}

	@Override
	public List<IndicatorSec> selectIndicatorSecList() {
		return indicatorSecMapper.selectIndicatorSecList();
	}

}
