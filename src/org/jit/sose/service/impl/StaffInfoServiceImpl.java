package org.jit.sose.service.impl;

import java.util.List;

import org.jit.sose.entity.StaffInfo;
import org.jit.sose.mapper.StaffInfoMapper;
import org.jit.sose.service.StaffInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class StaffInfoServiceImpl implements StaffInfoService {

	@Autowired
	private StaffInfoMapper staffInfoMapper;

	@Override
	public void insert(StaffInfo staffInfo) {
		staffInfoMapper.insert(staffInfo);
	}
	
	@Override
	public void update(StaffInfo staffInfo) {
		staffInfoMapper.update(staffInfo);
	}
	
	@Override
	public void delete(Integer id) {
		staffInfoMapper.delete(id);
	}
	
	@Override
	public Integer deleteSelection(List<Integer> idList) {
		return staffInfoMapper.deleteSelection(idList);
	}

	@Override
	public StaffInfo selectById(Integer id) {
		return staffInfoMapper.selectById(id);
		
	}

	@Override
	public PageInfo<StaffInfo> selectPageInfo(StaffInfo staffInfo, Integer pageNum, Integer pageSize) {
		// 设置分页参数
		PageHelper.startPage(pageNum, pageSize);
		// 查询集合
		List<StaffInfo> staffInfoList = staffInfoMapper.listByStaffInfo(staffInfo);
		PageInfo<StaffInfo> pageInfo = new PageInfo<StaffInfo>(staffInfoList);
		return pageInfo;
	}
	
	@Override
	public List<StaffInfo> selectStaffInfoList() {
		return staffInfoMapper.selectStaffInfoList();
	}
}
