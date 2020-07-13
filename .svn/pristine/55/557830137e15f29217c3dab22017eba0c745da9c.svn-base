package org.jit.sose.service;

import java.util.List;

import org.jit.sose.entity.SchoolInfo;

import com.github.pagehelper.PageInfo;

public interface SchoolInfoService {

	/**
	 * 查询课程信息和id集合
	 * 
	 * @author 王越
	 * @return 课程信息和id集合
	 */
	List<SchoolInfo> selectSchoolInfoList();

	/**
	 * 插入学院信息实体
	 * 
	 * @param schoolInfo
	 *            学院信息实体类
	 */
	String insert(SchoolInfo schoolInfo);

	/**
	 * 更新学院信息实体
	 * 
	 * @param schoolInfo
	 *            学院信息实体类
	 */
	String update(SchoolInfo schoolInfo);

	/**
	 * 删除学院信息实体
	 * 
	 * @param id
	 *            学院信息实体标识
	 */
	void delete(Integer id);

	/**
	 * 批量逻辑删除学院信息实体
	 * 
	 * @param idList
	 *            需要删除的id的集合
	 * @return 成功删除的记录数
	 */
	Integer deleteSelection(List<Integer> idList);

	/**
	 * 根据课程信息标识查询学院信息实体
	 * 
	 * @param id
	 *            学院信息实体
	 * 
	 * @return 学院信息实体类
	 */
	SchoolInfo selectById(Integer id);

	/**
	 * 分页条件查询
	 * 
	 * @param schoolInfo
	 *            需要作为查询条件的学院信息类
	 * @param pageNum
	 *            当前页索引
	 * @param pageSize
	 *            设置分页参数
	 * @return PageInfo分页数据
	 */
	PageInfo<SchoolInfo> selectPageInfo(SchoolInfo schoolInfo, Integer pageNum, Integer pageSize);
}
