package org.jit.sose.controller;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jit.sose.service.SendMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sun.org.apache.bcel.internal.generic.NEW;



/**
 * 邮件箱
 * 
 * @author: 陈子扬
 * @date: 2019/9/17
 */
@RestController
@RequestMapping("/sendMail")
public class SendMailController {
	
	@Autowired
	private SendMailService sendMailService;
	
	/**
	 * 发送邮件
	 * @param str 邮件信息
	 * @throws Exception
	 */
	@RequestMapping(value="/send", method=RequestMethod.POST)
	public void send(@RequestBody String str) throws Exception{
		JSONObject strj= JSONObject.parseObject(str);
		List<Object> recipientBox= new ArrayList<Object>();
		recipientBox=strj.getJSONArray("recipientBox");
		sendMailService.send(recipientBox,strj.getString("mailTitle"),strj.getString("mailContent"));
	}
	
	
}
