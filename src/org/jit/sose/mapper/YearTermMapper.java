package org.jit.sose.mapper;

import java.util.List;
import org.jit.sose.entity.YearTerm;

public interface YearTermMapper {
	/**
	 * 查询任务id和任务名称集合
	 * 
	 * @return 任务id和任务名称集合
	 */
	List<YearTerm> selectYearTermList();
	
	/**
	 * 逻辑删除学年信息
	 * 
	 * @param id
	 *            学年信息标识
	 */
    void delete(Integer id);
    /**
	 * 批量逻辑删除学年信息
	 * 
	 * @param idList 需要删除的id的集合
	 * @return 受影响行数
	 */
	Integer deleteSelection(List<Integer> idList);

	/**
	 * 插入学年信息
	 * 
	 * @param yearTerm
	 *            学年类
	 * @return 受影响的行数
	 */
    void insert(YearTerm yearTerm);
	/**
	 * 根据学年标识查询学年信息
	 * 
	 * @param id
	 *            学年标识
	 * @return 学年类
	 */
    YearTerm selectById(Integer id);
	/**
	 * 更新学年信息
	 * 
	 * @param yearTerm
	 *            学年类
	 * @return 受影响的行数
	 */
    void update(YearTerm yearTerm);
	/**
	 * 过滤查询学年信息
	 * 
	 * @param yearTerm
	 *            学年类
	 * @return 教学集合
	 */
	List<YearTerm> listByYearTerm(YearTerm yearTerm);
}