package org.jit.sose.service;

import java.util.List;

public interface SendMailService {

	void send(List<Object> recipientBox,String mailTitle,String mailContent) throws Exception;
}
