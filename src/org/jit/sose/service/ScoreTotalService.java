package org.jit.sose.service;

import java.util.List;

import org.jit.sose.entity.ScoreTotal;

import com.github.pagehelper.PageInfo;

public interface ScoreTotalService {
	/**
	 * 查询信息和id集合
	 * 
	 */
	List<ScoreTotal> selectScoreTotalList();
	
	/**
	 * 插入
	 * 
	 * @param scoreTotal 
	 */
	void insert(List<Integer> idList,List<Integer> couIdList,List<Double> totalScoreList);
	

	/**
	 * 更新
	 * 
	 * @param scoreTotal 
	 */
	void update(ScoreTotal scoreTotal);

	/**
	 * 删除
	 * 
	 * @param id 
	 */
	void delete(Integer id);

	/**
	 * 批量逻辑删除
	 * 
	 * @param idList 需要删除的id的集合
	 * @return 成功删除的记录数
	 */
	Integer deleteSelection(List<Integer> idList);

	/**
	 * 根据标识查询
	 * 
	 * @param id 课程标识
	 * @return 
	 */
	ScoreTotal selectById(Integer id);

	/**
	 * 过滤查询
	 * 
	 * @param ScoreTotal 
	 * @return scoreTotal 集合
	 */
	PageInfo<ScoreTotal> listScoreTotal(ScoreTotal scoreTotal, Integer pageNum, Integer pageSize);
	
}
