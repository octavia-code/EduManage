package org.jit.sose.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jit.sose.entity.ScoreTotal;
import org.jit.sose.mapper.ScoreTotalMapper;
import org.jit.sose.service.ScoreTotalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ScoreTotalServiceImpl implements ScoreTotalService {
	@Autowired
	private ScoreTotalMapper scoreTotalMapper;

	@Override
	public List<ScoreTotal> selectScoreTotalList() {
		return scoreTotalMapper.selectScoreTotalList();
	}

//	@Override
//	@Transactional
//	public void insert(ScoreTotal scoreTotal) {
//		
//		for(int i = 0 ;i <scoreTotal.getCouIdList().length; i ++){
//			System.out.println("数据1："+scoreTotal.getCouIdList()[i]);
//			System.out.println("数据2："+scoreTotal.getTotalScoreList()[i]);
//			ScoreTotal record =new ScoreTotal(scoreTotal.getCouIdList()[i], scoreTotal.getTotalScoreList()[i]);
//			ScoreTotal selectData = new ScoreTotal();
//			selectData=scoreTotalMapper.selectByCouId(record.getCouId());
//			if(selectData!=null){
//				scoreTotalMapper.delete(selectData.getId());
////				scoreTotalMapper.insert(record);
//			}else{
////				scoreTotalMapper.insert(record);
//			}
//			
//		}
//		
//	}

	@Override
	@Transactional
	public void insert(List<Integer> idList,List<Integer> couIdList, List<Double> totalScoreList) {
		ArrayList<ScoreTotal> arrayList = new ArrayList<ScoreTotal>();
		arrayList.add(new ScoreTotal(idList, couIdList, totalScoreList));
		Map<Object, Object> map = new HashMap<>();
		map.put("id", idList);
		map.put("couId", couIdList);
		map.put("totalScore", totalScoreList);
		System.out.println(map);
//		int result = scoreTotalMapper.insert(map);
//		log.debug("受影响的行: " + result);
	}
	
	@Override
	public void update(ScoreTotal scoreTotal) {
		scoreTotalMapper.update(scoreTotal);
	}

	@Override
	public void delete(Integer id) {
		scoreTotalMapper.delete(id);
	}

	@Override
	public Integer deleteSelection(List<Integer> idList) {
		return scoreTotalMapper.deleteSelection(idList);
	}

	@Override
	public ScoreTotal selectById(Integer id) {
		return scoreTotalMapper.selectById(id);
	}

	@Override
	public PageInfo<ScoreTotal> listScoreTotal(ScoreTotal scoreTotal, Integer pageNum, Integer pageSize) {
		// 设置分页参数
		PageHelper.startPage(pageNum, pageSize);
		// 查询集合
		List<ScoreTotal> scoreTotalList = scoreTotalMapper.listByScoreTotal(scoreTotal);
		PageInfo<ScoreTotal> pageInfo = new PageInfo<ScoreTotal>(scoreTotalList);
		return pageInfo;
	}


}
