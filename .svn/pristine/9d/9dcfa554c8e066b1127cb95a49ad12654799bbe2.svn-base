package org.jit.sose.service;

import java.util.List;

import org.jit.sose.entity.Role;

import com.github.pagehelper.PageInfo;

public interface RoleService {
 
	/**
	 * 插入
	 * @param role 
	 */
	String insert(Role role);
	
	/**
	 * 更新
	 * @param role
	 */
	void update(Role role);
	
	/**
	 * 删除
	 * @param id
	 */
	void delete (Integer id);
	
	/**
	 * 批量删除
	 * @param idList
	 * @return
	 */
	Integer deleteSelection(List<Integer> idList);
	
	/**
	 * 倒序过滤查询
	 * @param role
	 * @param pageNum 
	 * @param pageSize 
	 * @return 
	 */
	PageInfo<Role> filter(Role role,Integer pageNum, Integer pageSize);
	
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	Role selectById(Integer id);
}
