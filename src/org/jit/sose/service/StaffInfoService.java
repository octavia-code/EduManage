package org.jit.sose.service;

import java.util.List;

import org.jit.sose.entity.StaffInfo;

import com.github.pagehelper.PageInfo;

public interface StaffInfoService {
	
	/**
	 * 查询员工信息id和员工名称集合
	 * 
	 * @return 员工信息id和员工名称集合
	 */
	List<StaffInfo> selectStaffInfoList();
	
	/**
	 * 新增员工信息
	 * 
	 * @param staffInfo
	 */
	void insert(StaffInfo staffInfo);
	
	/**
	 * 更新员工信息
	 * 
	 * @param staffInfo
	 */
	void update(StaffInfo staffInfo);
	
	/**
	 * 删除员工信息
	 * 
	 * @param id 记录id
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
	 * 根据id查询员工信息
	 * 
	 * @param id 记录id
	 * @return StaffInfo
	 */
	StaffInfo selectById(Integer id);

	/**
	 * 分页条件查询
	 * 
	 * @param staffInfo 需要作为查询条件的员工信息
	 * @param pageNum    当前页索引
	 * @param pageSize   设置分页参数
	 * @return PageInfo分页数据
	 */
	PageInfo<StaffInfo> selectPageInfo(StaffInfo staffInfo, Integer pageNum, Integer pageSize);

}