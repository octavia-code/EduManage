package org.jit.sose.service.impl;

import java.util.List;

import org.jit.sose.entity.TermInfo;
import org.jit.sose.mapper.TermInfoMapper;
import org.jit.sose.service.TermInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class TermInfoServiceImpl implements TermInfoService {
	/**
	 * 注入依赖
	 */
	@Autowired
	private TermInfoMapper termInfoMapper;

	@Override
	public void insertTermInfo(TermInfo termInfo) {
		termInfoMapper.insert(termInfo);
	}

	@Override
	public void updateTermInfo(TermInfo termInfo) {
		termInfoMapper.update(termInfo);
	}

	@Override
	public void deleteTermInfo(Integer id) {
		termInfoMapper.delete(id);
	}

	@Override
	public Integer deleteSelection(List<Integer> idList) {
		return termInfoMapper.deleteSelection(idList);
	}

	@Override
	public TermInfo selectById(Integer id) {
		return termInfoMapper.selectById(id);
	}

	@Override
	public PageInfo<TermInfo> listTermName(TermInfo termInfo, Integer pageNum, Integer pageSize) {
		// 设置分页参数
		PageHelper.startPage(pageNum, pageSize);
		// 查询集合
		List<TermInfo> termInfoList = termInfoMapper.listByTermInfo(termInfo);
		PageInfo<TermInfo> pageInfo = new PageInfo<TermInfo>(termInfoList);
		return pageInfo;
	}

	@Override
	public List<TermInfo> selectTermInfoList() {
		return termInfoMapper.selectTermInfoList();
	}

}
