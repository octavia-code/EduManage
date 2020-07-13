package org.jit.sose.service.impl;

import java.util.List;

import org.jit.sose.entity.Eecstate;
import org.jit.sose.mapper.EecstateMapper;
import org.jit.sose.service.EecstateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class EecstateServiceImpl implements EecstateService {
	@Autowired
	private EecstateMapper eecstateMapper;


	@Override
	public String insert(Eecstate eecstate) {
		int count = eecstateMapper.countByEecstate(eecstate);

		if (count > 0) {
			return "exist";
		}
		eecstateMapper.insert(eecstate);
		return null;

	}
	@Override
	public void update(Eecstate eecstate) {
		eecstateMapper.update(eecstate);
	}

	@Override
	public void delete(Integer id) {
		eecstateMapper.delete(id) ;
	}

	@Override
	public PageInfo<Eecstate> selectPageInfo(Eecstate eecstate, Integer pageNum, Integer pageSize) {
		// 设置分页参数
		PageHelper.startPage(pageNum, pageSize);
		// 查询集合
		List<Eecstate> EecstateList = eecstateMapper.listByEecstate(eecstate);
		PageInfo<Eecstate> pageInfo = new PageInfo<Eecstate>(EecstateList);
		return pageInfo;
	}
	
	@Override
	public Integer deleteSelection(List<Integer> idList) {
		return eecstateMapper.deleteSelection(idList);
	}

	@Override
	public Eecstate selectById(Integer id) {
		return eecstateMapper.selectById(id);
	}

	

}
