package org.jit.sose.service.impl;

import java.util.List;

import org.jit.sose.entity.YearTerm;
import org.jit.sose.mapper.YearTermMapper;
import org.jit.sose.service.YearTermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class YearTermServiceImpl implements YearTermService {
	@Autowired
	private YearTermMapper yearTermMapper;

	@Override
	public void insert(YearTerm yearTerm) {
		yearTermMapper.insert(yearTerm);
	}
	
	@Override
	public void update(YearTerm yearTerm) {
		yearTermMapper.update(yearTerm);
	}
	
	@Override
	public void delete(Integer id) {
		yearTermMapper.delete(id);
	}

	@Override
	public Integer deleteSelection(@RequestBody List<Integer> idList) {
		return yearTermMapper.deleteSelection(idList);
	}
	
	@Override
	public PageInfo<YearTerm> selectPageInfo(YearTerm yearTerm, Integer pageNum, Integer pageSize) {
		// 设置分页参数
		PageHelper.startPage(pageNum, pageSize);
		// 查询集合
		List<YearTerm> yearList = yearTermMapper.listByYearTerm(yearTerm);
		PageInfo<YearTerm> pageInfo = new PageInfo<YearTerm>(yearList);
		return pageInfo;
	}

	@Override
	public List<YearTerm> selectYearTermList() {
		List<YearTerm> yearTermList = yearTermMapper.selectYearTermList();
		return yearTermList;
	}
	@Override
	public YearTerm selectById(Integer id) {
		return yearTermMapper.selectById(id);
	}

}
