package org.jit.sose.service.impl;

import java.util.List;

import org.jit.sose.entity.IndicatorThird;
import org.jit.sose.mapper.IndicatorThirdMapper;
import org.jit.sose.service.IndicatorThirdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class IndicatorThirdServiceImpl implements IndicatorThirdService {

	@Autowired
	private IndicatorThirdMapper indicatorThirdMapper;

	@Override
	public String insert(IndicatorThird indicatorThird) {	
		int result = indicatorThirdMapper.countByIndicatorThird(indicatorThird);
		if(result > 0){
			return "exist";
		}
		indicatorThirdMapper.insert(indicatorThird);
		return null;
	}

	@Transactional
	@Override
	public String update(IndicatorThird indicatorThird) {
		
		int result = indicatorThirdMapper.countByIndicatorThird(indicatorThird);
		if(result > 0){
			return "exist";
		}
		indicatorThirdMapper.update(indicatorThird);
		indicatorThirdMapper.insert(indicatorThird);
		return null;
	}

	@Override
	public void delete(Integer id) {
		indicatorThirdMapper.delete(id);
	}

	@Override
	public Integer deleteSelection(List<Integer> idList) {
		return indicatorThirdMapper.deleteSelection(idList);

	}

	@Override
	public PageInfo<IndicatorThird> selectPageInfo(IndicatorThird indicatorThird, Integer pageNum, Integer pageSize) {
		// 设置分页参数
		PageHelper.startPage(pageNum, pageSize);
		// 查询集合
		List<IndicatorThird> indicatorThirdList = indicatorThirdMapper.listByIndicatorThird(indicatorThird);
		PageInfo<IndicatorThird> pageInfo = new PageInfo<IndicatorThird>(indicatorThirdList);
		return pageInfo;
	}

	@Override
	public IndicatorThird selectById(Integer id) {
		return indicatorThirdMapper.selectById(id);
	}

	@Override
	public List<IndicatorThird> selectIndicatorThirdList() {
		return indicatorThirdMapper.selectIndicatorThirdList();
	}

}
