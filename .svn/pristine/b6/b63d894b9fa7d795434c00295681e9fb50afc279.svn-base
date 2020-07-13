package org.jit.sose.mapper;

import java.util.List;

import org.jit.sose.entity.StaffInfo;

public interface StaffInfoMapper {
	
	/**
	 *  查询教师工号和教师名称集合
	 * 
	 * @return 教师工号和教师名称集合
	 */
	List<StaffInfo> selectStaffInfoList();
	
	/**
	 * 逻辑删除教师信息
	 * 
	 * @param id 教师信息标识
	 */
    void delete(Integer id);
    
    /**
	 * 批量逻辑删除教师信息
	 * 
	 * @param idList 需要删除的id的集合
	 * @return 受影响行数
	 */
	Integer deleteSelection(List<Integer> idList);
	
	/**
	 * 插入教师信息
	 * 
	 * @param staffInfo 教师信息
	 */
    void insert(StaffInfo staffInfo);

    /**
	 * 根据教师信息标识查询教师信息
	 * 
	 * @param id 教师标识
	 * @return 教师信息类
	 */
    StaffInfo selectById(Integer id);

    /**
	 * 更新教师信息
	 * 
	 * @param staffInfo 教师信息类
	 */
    void update(StaffInfo staffInfo);
    
    /**
	 * 过滤查询教师信息
	 * 
	 * @param staffInfo 教师信息类
	 * @return 教师信息集合
	 */
	List<StaffInfo> listByStaffInfo(StaffInfo staffInfo);
	
}