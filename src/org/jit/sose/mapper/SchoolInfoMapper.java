package org.jit.sose.mapper;

import java.util.List;

import org.jit.sose.entity.SchoolInfo;

public interface SchoolInfoMapper {

	/**
	 * 查询课程信息和id集合
	 * 
	 * @author 王越
	 * @return 课程信息和id集合
	 */
	List<SchoolInfo> selectSchoolInfoList();

	/**
	 * 逻辑删除学院信息
	 * 
	 * @param id 学院信息标识
	 */
	void delete(Integer id);

	/**
	 * 批量逻辑删除学院信息
	 * 
	 * @param idList 需要删除的id的集合
	 * @return 受影响行数
	 */

	Integer deleteSelection(List<Integer> idList);

	/**
	 * 插入学院信息
	 * 
	 * @param SchoolInfo 学院信息
	 */
	void insert(SchoolInfo schoolInfo);

	/**
	 * 根据课程信息标识查询学院信息
	 * 
	 * @param id 学院信息标识
	 * @return 学院信息类
	 */

	SchoolInfo selectById(Integer id);

	/**
	 * 更新学院信息
	 * 
	 * @param SchoolInfo 学院信息类
	 */
	void update(SchoolInfo schoolInfo);

	/**
	 * 过滤查询学院信息
	 * 
	 * @param SchoolInfo 学院信息类
	 * @return 学院信息集合
	 */
	List<SchoolInfo> listBySchoolInfo(SchoolInfo schoolInfo);
	
	/**
	 * 查询学院信息的记录数
	 * @param SchoolInfo
	 * @return
	 */
	Integer countBySchoolInfo(SchoolInfo schoolInfo);

}