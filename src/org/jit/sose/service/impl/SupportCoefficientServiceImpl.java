package org.jit.sose.service.impl;

import java.util.List;

import org.jit.sose.entity.SupportCoefficient;
import org.jit.sose.mapper.SupportCoefficientMapper;
import org.jit.sose.service.SupportCoefficientService;
import org.jit.sose.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class SupportCoefficientServiceImpl implements SupportCoefficientService {

	@Autowired
	private SupportCoefficientMapper supportCoefficientMapper;

	@Override
	public void insert(SupportCoefficient supportCoefficient) {
		supportCoefficientMapper.insert(supportCoefficient);
	}

	@Override
	public void update(SupportCoefficient supportCoefficient) {
		if (supportCoefficient.getId() == null) {
			supportCoefficientMapper.insert(supportCoefficient);
		} else {
			supportCoefficientMapper.update(supportCoefficient);
		}

	}

	@Override
	public void delete(Integer id) {
		supportCoefficientMapper.delete(id);
	}

	@Override
	public Integer deleteSelection(List<Integer> idList) {
		return supportCoefficientMapper.deleteSelection(idList);
	}

	@Override
	public PageInfo<SupportCoefficient> selectPageInfo(SupportCoefficient supportCoefficient, Integer pageNum,
			Integer pageSize) {
		// 设置分页参数
		PageHelper.startPage(pageNum, pageSize);
		// 查询集合
		List<SupportCoefficient> SupportCoefficientList = supportCoefficientMapper
				.listBySupportCoefficient(supportCoefficient);
		PageInfo<SupportCoefficient> pageInfo = new PageInfo<SupportCoefficient>(SupportCoefficientList);
		return pageInfo;
	}

	@Override
	public SupportCoefficient selectById(Integer id) {
		return supportCoefficientMapper.selectById(id);
	}

}
