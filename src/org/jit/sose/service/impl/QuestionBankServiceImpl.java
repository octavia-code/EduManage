package org.jit.sose.service.impl;

import java.util.List;

import org.jit.sose.entity.QuestionBank;
import org.jit.sose.mapper.QuestionBankMapper;
import org.jit.sose.service.QuestionBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class QuestionBankServiceImpl implements QuestionBankService {

	@Autowired
	private QuestionBankMapper questionBankMapper;
	
	
	@Override
	public List<QuestionBank> selectQuestionBankList() {
		List<QuestionBank> list = questionBankMapper.selectQuestionBankList();
		return list;
	}

	@Override
	public void delete(Integer id) {
		questionBankMapper.delete(id);
	}

	@Override
	public Integer deleteSelection(List<Integer> idList) {
		return questionBankMapper.deleteSelection(idList);
	}

	@Override
	public void insert(QuestionBank questionBank) {
		questionBankMapper.insert(questionBank);
	}

	@Override
	public QuestionBank selectById(Integer id) {
		return questionBankMapper.selectById(id);
	}

	@Override
	public void update(QuestionBank questionBank) {
		questionBankMapper.update(questionBank);
	}

	@Override
	public PageInfo<QuestionBank> selectPageInfo(QuestionBank questionBank, Integer pageNum, Integer pageSize) {
		// 设置分页参数
		PageHelper.startPage(pageNum, pageSize);
		// 查询集合
		List<QuestionBank> questionBankList = questionBankMapper.listByQuestionBank(questionBank);
		PageInfo<QuestionBank> pageInfo = new PageInfo<QuestionBank>(questionBankList);
		return pageInfo;
		
	}

	@Override
	public List<QuestionBank> selectFiveQuestion() {
		return questionBankMapper.selectFiveQuestion();
	}

}
