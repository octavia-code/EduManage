package org.jit.sose.service.impl;

import java.util.List;

import org.jit.sose.entity.GraduationRequireIndicator;
import org.jit.sose.mapper.GraduationRequireIndicatorMapper;
import org.jit.sose.service.GraduationRequireIndicatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class GraduationRequireIndicatorServiceImpl implements GraduationRequireIndicatorService {

	@Autowired
	private GraduationRequireIndicatorMapper gIndicatorMapper;

	@Override
	public void insert(GraduationRequireIndicator gIndicator) {
		gIndicatorMapper.insert(gIndicator);
	}

	@Transactional // 事务管理
	@Override
	public void update(GraduationRequireIndicator gIndicator) {
		// 更新原数据
		gIndicatorMapper.update(gIndicator);
		// 添加新记录
		gIndicatorMapper.insert(gIndicator);
	}

	@Override
	public void delete(Integer id) {
		gIndicatorMapper.delete(id);
	}

	@Override
	public void admit(Integer id) {
		gIndicatorMapper.admit(id);		
	}
	
	@Override
	public Integer deleteSelection(List<Integer> idList) {
		return gIndicatorMapper.deleteSelection(idList);
	}

	@Override
	public PageInfo<GraduationRequireIndicator> selectPageInfo(GraduationRequireIndicator gIndicator, Integer pageNum,
			Integer pageSize) {
		// 设置分页参数
		PageHelper.startPage(pageNum, pageSize);
		// 查询集合
		List<GraduationRequireIndicator> GraduationRequireIndicatorList = gIndicatorMapper
				.listByGraduationRequireIndicator(gIndicator);
		PageInfo<GraduationRequireIndicator> pageInfo = new PageInfo<GraduationRequireIndicator>(
				GraduationRequireIndicatorList);
		return pageInfo;
	}

	@Override
	public GraduationRequireIndicator selectById(Integer id) {
		return gIndicatorMapper.selectById(id);
	}

	@Override
	public List<GraduationRequireIndicator> selectGraduationList() {
		return gIndicatorMapper.selectGraduationList();
	}

	

}
