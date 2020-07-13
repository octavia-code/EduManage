package org.jit.sose.service.impl;

import java.util.List;

import org.jit.sose.entity.TblHeadList;
import org.jit.sose.mapper.TblHeadListMapper;
import org.jit.sose.service.TblHeadListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class TblHeadListServiceImpl implements TblHeadListService {

	@Autowired
	private TblHeadListMapper tblheadListMapper;
	

	@Override
	public void insert(TblHeadList tblheadList) {
		tblheadListMapper.insert(tblheadList);		
	}

	@Override
	public void update(TblHeadList tblheadList) {
		tblheadListMapper.update(tblheadList);
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		tblheadListMapper.delete(id);
		
	}

	@Override
	public Integer deleteSelection(List<Integer> idList) {
		return tblheadListMapper.deleteSelection(idList);	
	}

	@Override
	public PageInfo<TblHeadList> selectPageInfo(TblHeadList tblHeadList, Integer pageNum, Integer pageSize) {
		// 设置分页参数
		PageHelper.startPage(pageNum, pageSize);
		// 查询集合
		List<TblHeadList> TblHeadListList = tblheadListMapper.listByTblHeadList(tblHeadList);
		PageInfo<TblHeadList> pageInfo = new PageInfo<TblHeadList>(TblHeadListList);
		return pageInfo;
	}

	@Override
	public TblHeadList selectById(Integer id) {
		return tblheadListMapper.selectById(id);
	}

}
