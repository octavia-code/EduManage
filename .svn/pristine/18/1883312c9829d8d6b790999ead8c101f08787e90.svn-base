package org.jit.sose.service.impl;

import java.util.List;

import org.jit.sose.entity.SchoolInfo;
import org.jit.sose.mapper.SchoolInfoMapper;
import org.jit.sose.service.SchoolInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class SchoolInfoServiceImpl implements SchoolInfoService {

	@Autowired
	private SchoolInfoMapper schoolInfoMapper;

	@Override
	public String insert(SchoolInfo schoolInfo) {

		int result = schoolInfoMapper.countBySchoolInfo(schoolInfo);
		if (result > 0) {
			return "exist";
		}
		schoolInfoMapper.insert(schoolInfo);
		return null;
	}

	@Override
	public String update(SchoolInfo schoolInfo) {
		int result = schoolInfoMapper.countBySchoolInfo(schoolInfo);
		if (result > 0) {
			return "exist";
		}
		schoolInfoMapper.update(schoolInfo);
		return null;
	}

	@Override
	public void delete(Integer id) {
		schoolInfoMapper.delete(id);
	}

	@Override
	public Integer deleteSelection(List<Integer> idList) {
		return schoolInfoMapper.deleteSelection(idList);

	}

	@Override
	public List<SchoolInfo> selectSchoolInfoList() {
		List<SchoolInfo> SchoolInfoList = schoolInfoMapper.selectSchoolInfoList();
		return SchoolInfoList;
	}

	@Override
	public SchoolInfo selectById(Integer id) {
		return schoolInfoMapper.selectById(id);
	}

	@Override
	public PageInfo<SchoolInfo> selectPageInfo(SchoolInfo schoolInfo, Integer pageNum, Integer pageSize) {
		// 设置分页参数
		PageHelper.startPage(pageNum, pageSize);
		// 查询集合
		List<SchoolInfo> schoolInfoList = schoolInfoMapper.listBySchoolInfo(schoolInfo);
		PageInfo<SchoolInfo> pageInfo = new PageInfo<SchoolInfo>(schoolInfoList);
		return pageInfo;
	}

}
