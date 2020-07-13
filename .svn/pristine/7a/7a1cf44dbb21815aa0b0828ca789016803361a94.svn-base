package org.jit.sose.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.jit.sose.entity.ScoreTotal;

public interface ScoreTotalMapper {
	/**
	 * 查询信息和id集合
	 * 
	 */
	List<ScoreTotal> selectScoreTotalList();

	/**
	 * 通过id逻辑删除
	 * 
	 * @param id
	 * @return
	 */
	void delete(Integer id);

	/**
	 * 批量逻辑删除
	 * 
	 * @param idList 需要删除的id的集合
	 * @return 受影响行数
	 */
	Integer deleteSelection(List<Integer> idList);

	/**
	 * 插入
	 * 
	 * @param scoreTotal
	 * @return
	 */
	Integer insertCouId(List<Integer> couIdList);
	/**
	 * 插入总成绩
	 * 
	 * @param list
	 * @return
	 */
	Integer insert(@Param("list") List<ScoreTotal> list);
	/**
	 * 根据成绩登记标识查询
	 * 
	 * @param id 标识
	 * @return
	 */
	ScoreTotal selectById(Integer id);
	
	ScoreTotal selectByCouId(Integer CouId);

	/**
	 * 更新
	 * 
	 * @param scoreTotal
	 * @return
	 */
	void update(ScoreTotal scoreTotal);

	/**
	 * 过滤查询
	 * 
	 * @param  
	 * @return 信息集合
	 */
	List<ScoreTotal> listByScoreTotal(ScoreTotal scoreTotal);

	/**
	 * 修改学生状态
	 * @param scoreTotalId
	 */
	void updateState(@Param("scoreTotalId")Integer scoreTotalId,@Param("studentState")String studentState);
}