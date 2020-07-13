package org.jit.sose.mapper;

import java.util.List;

import org.jit.sose.entity.Eecstate;
import org.jit.sose.entity.Role;

public interface RoleMapper {
	
	/**
	 * 查重
	 * @param role
	 * @return
	 */
	Integer countByMykey(Role role);
	
	/**
	 * 根据角色表信息标识查询信息
	 * @param id
	 * @return
	 */
    Role selectById(Integer id);
    
    /**
     * 删除
     * @param id
     */
    void delete(Integer id);

    /**
     * 批量删除
     * @param idList
     * @return
     */
    Integer deleteSelection(List<Integer> idList);
    
    /**
     * 插入
     * @param role
     */
    void insert(Role role);    
        
    /**
     * 更新信息
     * @param role
     */
    void update(Role role);
    
    /**
     * 降序过滤查询角色表信息
     * @param role
     * @return
     */
    List<Role> filter(Role role);

    
    
 
        
}