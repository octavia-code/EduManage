package org.jit.sose.service.impl;

import java.util.List;

import org.jit.sose.entity.GradeForm;
import org.jit.sose.mapper.GradeFormMapper;
import org.jit.sose.service.GradeFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class GradeFormServiceImpl implements GradeFormService {

	@Autowired
	private GradeFormMapper gradeFormMapper;
	
	@Override
	public List<GradeForm> selectGradeFormList() {
		return gradeFormMapper.selectGradeFormList();
	}

	@Override
	public String insert(GradeForm gradeForm) {
		if(gradeFormMapper.countByGradeForm(gradeForm)>0){
			return "exist";
		}
		gradeFormMapper.insert(gradeForm);
		return null;
	}

	@Override
	public void update(GradeForm gradeForm) {
		gradeFormMapper.update(gradeForm);
	}

	@Override
	public void delete(Integer id) {
		gradeFormMapper.delete(id);
	}

	@Override
	public Integer deleteSelection(List<Integer> idList) {
		return gradeFormMapper.deleteSelection(idList);
	}

	@Override
	public GradeForm selectById(Integer id) {
		return gradeFormMapper.selectById(id);
	}

	@Override
	public PageInfo<GradeForm> listGradeForm(GradeForm gradeForm, Integer pageNum, Integer pageSize) {
		// 设置分页参数
		PageHelper.startPage(pageNum, pageSize);
		// 查询集合
		List<GradeForm> gradeFormList = gradeFormMapper.listByGradeForm(gradeForm);
		PageInfo<GradeForm> pageInfo = new PageInfo<GradeForm>(gradeFormList);
		return pageInfo;
	}

	@Override
	public Integer selectByGradeFromId(GradeForm gradeForm) {
		return gradeFormMapper.selectByGradeFromId(gradeForm);
	}

}
