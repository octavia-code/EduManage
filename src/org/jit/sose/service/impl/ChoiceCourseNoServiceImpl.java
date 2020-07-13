package org.jit.sose.service.impl;

import java.util.List;

import org.jit.sose.entity.ChoiceCourseNo;
import org.jit.sose.mapper.ChoiceCourseNoMapper;
import org.jit.sose.service.ChoiceCourseNoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class ChoiceCourseNoServiceImpl implements ChoiceCourseNoService {

	@Autowired
	private ChoiceCourseNoMapper choiceCourseNoMapper;

	@Override
	public List<ChoiceCourseNo> selectChoiceCourseNoList() {
		return choiceCourseNoMapper.selectChoiceCourseNoList();
	}

	@Override
	public void insert(ChoiceCourseNo choiceCourseNo) {
		choiceCourseNoMapper.insert(choiceCourseNo);

	}

	@Override
	public void update(ChoiceCourseNo choiceCourseNo) {
		choiceCourseNoMapper.update(choiceCourseNo);

	}

	@Override
	public void delete(Integer id) {
		choiceCourseNoMapper.delete(id);
	}

	@Override
	public Integer deleteSelection(List<Integer> idList) {
		return choiceCourseNoMapper.deleteSelection(idList);
	}

	@Override
	public ChoiceCourseNo selectById(Integer id) {
		return choiceCourseNoMapper.selectById(id);
	}

	@Override
	public PageInfo<ChoiceCourseNo> listChoiceCourseNo(ChoiceCourseNo choiceCourseNo, Integer pageNum,
			Integer pageSize) {
		// 设置分页参数
		PageHelper.startPage(pageNum, pageSize);
		// 查询集合
		List<ChoiceCourseNo> choiceCourseNoList = choiceCourseNoMapper.listByChoiceCourseNo(choiceCourseNo);
		PageInfo<ChoiceCourseNo> pageInfo = new PageInfo<ChoiceCourseNo>(choiceCourseNoList);
		return pageInfo;
	}

	@Override
	public ChoiceCourseNo selectByChoiceCourseNo(ChoiceCourseNo choiceCourseNo) {		
		return choiceCourseNoMapper.selectByChoiceCourseNo(choiceCourseNo);
	}

	
	

}
