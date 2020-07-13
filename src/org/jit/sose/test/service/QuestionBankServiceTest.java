package org.jit.sose.test.service;

import org.jit.sose.entity.QuestionBank;
import org.jit.sose.service.QuestionBankService;
import org.jit.sose.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class QuestionBankServiceTest extends BaseTest{

	@Autowired
	private QuestionBankService bankService;
	
	@Test
	public void update(){
		QuestionBank bank = new QuestionBank();
		bank.setId(2);
		bank.setSubject("计算机网络");
		bank.setChapter("第一章");
		bankService.update(bank);
	}

}
