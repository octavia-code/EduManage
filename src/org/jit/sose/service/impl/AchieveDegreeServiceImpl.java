package org.jit.sose.service.impl;

import java.util.List;

import org.jit.sose.entity.AchieveDegree;
import org.jit.sose.mapper.AchieveDegreeMapper;
import org.jit.sose.service.AchieveDegreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class AchieveDegreeServiceImpl implements AchieveDegreeService {

	@Autowired
	private AchieveDegreeMapper achieveDegreeMapper;
	
	
	@Override
	public void delete(Integer id) {
		achieveDegreeMapper.delete(id);
	}

	@Override
	public void insert(AchieveDegree achieveDegree) {
		achieveDegreeMapper.insert(achieveDegree);
	}

	@Override
	public void update(AchieveDegree achieveDegree) {
		achieveDegreeMapper.update(achieveDegree);

	}

	@Override
	public PageInfo<AchieveDegree> selectPageInfo(AchieveDegree achieveDegree, Integer pageNum, Integer pageSize) {
		// 设置分页参数
		PageHelper.startPage(pageNum, pageSize);
		// 查询集合
		List<AchieveDegree> questionBankList = achieveDegreeMapper.listByAchieveDegree(achieveDegree);
		PageInfo<AchieveDegree> pageInfo = new PageInfo<AchieveDegree>(questionBankList);
		return pageInfo;
	}

	@Override
	public void updateScore(AchieveDegree achieveDegree) {
		achieveDegreeMapper.updateScore(achieveDegree);
	}

}
