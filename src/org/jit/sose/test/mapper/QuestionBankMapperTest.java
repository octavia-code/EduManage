package org.jit.sose.test.mapper;

import java.util.ArrayList;
import java.util.List;

import org.jit.sose.entity.QuestionBank;
import org.jit.sose.entity.User;
import org.jit.sose.mapper.QuestionBankMapper;
import org.jit.sose.test.BaseTest;
import org.jit.sose.util.SessionUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class QuestionBankMapperTest extends BaseTest{

	@Autowired
	private QuestionBankMapper bankMapper;
	
	@Test
	public void test(){
		List<QuestionBank> list = new ArrayList<QuestionBank>();
//		QuestionBank bank = new QuestionBank();
//		bank.setSubject("thr");
//		bank.setStem("A");
//		bank.setAnswer("A");
//		list = bankMapper.listByQuestionBank(bank);
//	    System.out.println("list-----"+list);
//		User user = SessionUtil.getUser();
//		String username = user.getUserName();
//		System.out.println("username-----"+username);
		list = bankMapper.selectFiveQuestion();
		SessionUtil sessionUtil = new SessionUtil();
		User user = sessionUtil.getUser();
		String username = user.getUserName();
		System.out.println("username--------------"+username);

	}
}
