package org.jit.sose.service.impl;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.jit.sose.service.SendMailService;
import org.springframework.stereotype.Service;

import com.sun.mail.util.MailSSLSocketFactory;

@Service
public class SendMailServiceImpl implements SendMailService{

	

	//发件人地址
	private static String senderAddress = "yac3526641@163.com";
	//收件人地址
	private static List<Object> recipientAddress = new ArrayList<Object>();
	//发件人账户名
	private static String senderAccount = "yac3526641@163.com";
	//发件人密码
	private static String senderPassword = "yca3526641";
	//邮件名称
	private static String mailTitle = "默认标题";
	//邮件正文
	private static String mailContext = "默认内容";
	//附件路径
	private static String[] fileAddress = null;

	
	public static void sendMail() throws Exception{
	//1.参数配置
		Properties props = new Properties();
		//设置用户的认证方式
		props.setProperty("mail.smtp.auth", "true");
		//设置传输协议
		props.setProperty("mail.tansport.protocol", "smtp");
		//设置发件人的SMTP服务器地址
		String[] whichMail=senderAddress.split("@");
		switch (whichMail[1]) {
		case "qq.com":
			props.setProperty("mail.host", "smtp.qq.com");
			break;
		case "163.com":
			props.setProperty("mail.host", "smtp.163.com");
			break;
		default:
			break;
		}
		//开启SSL加密
		MailSSLSocketFactory sf = new MailSSLSocketFactory();
		sf.setTrustAllHosts(true);
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.ssl.socketFactory", sf);
		
	//2.创建Session对象
		Session session = Session.getInstance(props);
		//设置调试信息打印在控制台
		session.setDebug(true);
	
	//3.创建邮件的实例对象
		Message msg = setMimeMessage(session);
	
	//4.根据session对象获取邮件的传输对象Transport
		Transport transport = session.getTransport();
		
		//设置发件人的账户名和密码
		transport.connect(senderAccount,senderPassword);
		

		//发送邮件，并发送到所有收件人地址
		transport.sendMessage(msg,msg.getAllRecipients());
		
    //5、关闭邮件连接
        transport.close();
	}
	 /**
	    * 获得创建一封邮件的实例对象
	    * @param session
	    * @return
	    * @throws MessagingException
	    * @throws AddressException
	    */
		public static MimeMessage setMimeMessage(Session session) throws Exception{
			//创建一封邮件的实例对象
			MimeMessage msg = new MimeMessage(session);
			//设置发件人地址
			msg.setFrom(new InternetAddress(senderAddress));
	       /**
	        * 设置收件人地址（可以增加多个收件人、抄送、密送），即下面这一行代码书写多行
	        * MimeMessage.RecipientType.TO:发送
	        * MimeMessage.RecipientType.CC：抄送
	        * MimeMessage.RecipientType.BCC：密送
	        */
			InternetAddress[] Address=new InternetAddress[recipientAddress.size()];
			for(int i=0;i<recipientAddress.size();i++)
			{
				Address[i] = new InternetAddress(recipientAddress.get(i).toString());
			}
			msg.setRecipients(MimeMessage.RecipientType.TO,Address);
			//设置邮件主题
			msg.setSubject(mailTitle,"UTF-8");
		//创建文本节点
			MimeBodyPart text = new MimeBodyPart();
			//设置邮件正文
			text.setContent(mailContext,"text/html;charset=UTF-8");
		//创建附件节点
			if(fileAddress!=null)
			{
		    MimeMultipart text_file = new MimeMultipart();
	        text_file.addBodyPart(text);
	        for(int i=0;i<fileAddress.length;i++)
	        {
	        MimeBodyPart attachment = new MimeBodyPart();
		    //读取本地文件
	        DataHandler fileSource = new DataHandler(new FileDataSource(fileAddress[i]));
	        //将附件数据添加到"节点"
	        attachment.setDataHandler(fileSource);
	        //设置附件的文件名（encode避免乱码）
	        attachment.setFileName(MimeUtility.encodeText(fileSource.getName())); 
	        text_file.addBodyPart(attachment);
	        }
	        text_file.setSubType("mixed");
	        //将组合后的内容设置为邮件内容
	        msg.setContent(text_file);
			}
			else{
				MimeMultipart Text = new MimeMultipart();
				Text.addBodyPart(text);
				msg.setContent(Text);
			}
			//设置邮件的发送时间，默认立即发送
			msg.setSentDate(new Date());
			
			return msg;
		}
		

	
	/**
	  * 设置寄件方
	  * 
	  * @param TsenderAddress 寄件人邮箱地址
	  * @param TsenderAccount 寄件人账号
	  * @param TsenderPassword 寄件人密码
	  * 设置寄件方
	  */
	public static void setSender(String TsenderAddress,String TsenderAccount,String TsenderPassword)
	{
		senderAddress=TsenderAddress;
		senderAccount=TsenderAccount;
		senderPassword=TsenderPassword;
	}
	
	/**
	  * 设置收件方
	  * 
	  * @param TRecipientAddress 收件人邮箱地址集合
	  */
	public static void setRecipient(List<Object> TRecipientAddress)
	{
		recipientAddress=TRecipientAddress;
	}
	
	/**
	 * 设置邮件正文
	 * @param TmailTitle 邮件标题
	 * @param TmailContext 邮件正文
	 * @param TisFile 是否含有附件
	 */
	public static void setContext(String TmailTitle)
	{
		mailTitle=TmailTitle;
	}
	
	/**
	  *设置邮件标题 
	  * 
	  * @param TmailTitle 邮件标题
	  */
	public static void setMailTitle(String TmailTitle){
		mailTitle=TmailTitle;
	}
	
	/**
	 * 设置邮件正文
	 * 
	 * @param TmailContext 邮件正文
	 */
	public static void setMailContext(String TmailContext){
		mailContext=TmailContext;
	}
	
	/**
	 * 设置附件
	 * @param TFileAddress 附件地址集合
	 */
	public static void setFile(String[] TFileAddress)
	{
		fileAddress=TFileAddress;
	}
	
	@Override
	public void send(List<Object> recipientBox,String mailTitle,String mailContent) throws Exception{
		 List<Object> recipientAddress=new ArrayList<Object>();
		 recipientAddress=recipientBox;
		 setRecipient(recipientAddress);
		 setMailTitle(mailTitle);
		 setMailContext(mailContent);
		 sendMail();
	}

}
