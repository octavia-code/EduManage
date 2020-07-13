//package org.jit.sose.util;
//
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.Message;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeBodyPart;
//import javax.mail.internet.MimeMessage;
//import javax.mail.internet.MimeMultipart;
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.Properties;
//
///**
// * @author dylan
// * 
// *         使用配置文件的形式
// * 
// *         发送文本邮件 JavaMail 版本: 1.6.1 JDK 版本: JDK 1.7 以上（必须） 收件人 全部被列了出来 dylan
// *         <1172474142@qq.com>; 收件人名称 <1620256124@qq.com>
// * 
// */
//public class JavaMailUtil {
//
//	// 发件人的 邮箱 和 密码（替换为自己的邮箱和密码）
//	// PS: 某些邮箱服务器为了增加邮箱本身密码的安全性，给 SMTP 客户端设置了独立密码（有的邮箱称为“授权码”）,
//	// 对于开启了独立密码的邮箱, 这里的邮箱密码必需使用这个独立密码（授权码）。
//	private String myEmailAccount = "";
//	private String myEmailPassword = "";
//
//	// 发件人邮箱的 SMTP 服务器地址, 必须准确, 不同邮件服务器地址不同, 一般(只是一般, 绝非绝对)格式为: smtp.xxx.com
//	// 网易163邮箱的 SMTP 服务器地址为: smtp.163.com
//	private String myEmailSMTPHost = "";
//
//	// 收件人邮箱（替换为自己知道的有效邮箱）
//	// public static String receiveMailAccount[] =
//	// {"1172474142@qq.com","1620256124@qq.com"};
//
//	// public static String receiveMailAccount[] = {"1620256124@qq.com"};
//
//	// SMTP 服务器的端口
//	private String smtpPort = "";
//
//	// 验证码
//	private static String code = "";
//	// 验证码长度
//	private static int codeLength = 6;
//
//	// 资源文件路径
//	private static String mailProperties = "resource/mail.properties";
//
//	static PropertiesUtil pUtil = new PropertiesUtil();
//
//	// 初始化方法
//	public JavaMailUtil() {
//		try {
//			Properties mailProp = pUtil.readProperties(mailProperties);
//			this.myEmailAccount = mailProp.getProperty("myEmailAccount");
//			this.myEmailPassword = mailProp.getProperty("myEmailPassword");
//			this.myEmailSMTPHost = mailProp.getProperty("myEmailSMTPHost");
//			this.smtpPort = mailProp.getProperty("smtpPort");
//			// this.codeLength =
//			// Integer.parseInt(mailProp.getProperty("codeLength"));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * 自定义验证码
//	 * 
//	 * @return
//	 */
//	public static String smsCode() {
//		for (int i = 0; i < codeLength; i++) {
//			code += (int) (Math.random() * 9 + 1);
//		}
//		return code;
//	}
//
//	/**
//	 * 
//	 * @param emailType          短信发送类型:registerEmail forgetPwdEmail loginEmail
//	 * 
//	 * @param receiveMailAccount 收件人列表
//	 * @throws Exception
//	 */
//	public String sendMail(String emailType, String[] receiveMailAccount) {
//		try {
//
//			// 1. 创建参数配置, 用于连接邮件服务器的参数配置
//			Properties props = new Properties(); // 参数配置
//			props.setProperty("mail.transport.protocol", "smtp"); // 使用的协议（JavaMail规范要求）
//			props.setProperty("mail.smtp.host", myEmailSMTPHost); // 发件人的邮箱的
//																	// SMTP
//																	// 服务器地址
//			props.setProperty("mail.smtp.auth", "true"); // 需要请求认证
//
//			// PS: 某些邮箱服务器要求 SMTP 连接需要使用 SSL 安全认证 (为了提高安全性, 邮箱支持SSL连接, 也可以自己开启),
//			// 如果无法连接邮件服务器, 仔细查看控制台打印的 log, 如果有有类似 “连接失败, 要求 SSL 安全连接” 等错误,
//			// 打开下面 /* ... */ 之间的注释代码, 开启 SSL 安全连接。
//
//			// SMTP 服务器的端口 (非 SSL 连接的端口一般默认为 25, 可以不添加, 如果开启了 SSL 连接,
//			// 需要改为对应邮箱的 SMTP 服务器的端口, 具体可查看对应邮箱服务的帮助,
//			// QQ邮箱的SMTP(SLL)端口为465或587, 其他邮箱自行去查看)
//			// final String smtpPort = mailProp.getProperty("smtpPort");
//			props.setProperty("mail.smtp.port", smtpPort);
//			props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//			props.setProperty("mail.smtp.socketFactory.fallback", "false");
//			props.setProperty("mail.smtp.socketFactory.port", smtpPort);
//
//			// 2. 根据配置创建会话对象, 用于和邮件服务器交互
//			Session session = Session.getInstance(props);
//			// session.setDebug(true); // 设置为debug模式, 可以查看详细的发送 log
//
//			// 3. 创建一封邮件
//
//			MimeMessage message = createMimeMessage(session, emailType, myEmailAccount, receiveMailAccount);
//			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			System.out.println("=================向" + receiveMailAccount[0] + "发送结束时间：" + df.format(new Date()));
//
//			Transport transport = session.getTransport();
//
//			transport.connect(myEmailAccount, myEmailPassword);
//
//			// transport.sendMessage(message, message.getAllRecipients());
//			// System.out.println("收件人:"+message.getRecipients(Message.RecipientType.TO));
//			transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
//
//			// 7. 关闭连接
//			transport.close();
//			return "success";
//		} catch (Exception e) {
//			e.printStackTrace();
//			return "fail";
//		}
//
//	}
//
//	private static String registerSubject = "欢迎注册";
//
//	private static String forgetPwdSubject = "重置密码";
//
//	private static String registerText = "您正在注册 江苏省人工智能公共服务平台，您的验证码为：";
//
//	private static String forgetPwdText = "江苏省人工智能公共服务平台，您的找回密码验证码为：";
//
//	/**
//	 * 创建一封只包含文本的简单邮件
//	 *
//	 * @param session     和服务器交互的会话
//	 * @param emailType   短信发送类型:registerEmail forgetPwdEmail loginEmail
//	 * @param sendMail    发件人邮箱
//	 * @param receiveMail 收件人邮箱
//	 * @return
//	 * @throws Exception
//	 */
//	public static MimeMessage createMimeMessage(Session session, String emailType, String sendMail,
//			String receiveMail[]) throws Exception {
//		Properties mailProp = pUtil.readProperties(mailProperties);
//
//		// 主题
//		String subject = "";
//
//		// 正文
//		String text = "";
//
//		// 验证码
//		String code = smsCode();
//		System.out.println("验证码:" + code);
//
//		// 失效时间
//		String mailFailureTime = "";
//
//		switch (emailType) {
//		case "registerEmail":
//			mailFailureTime = mailProp.getProperty("registerFailureTime");
//			subject = registerSubject;
//			text = registerText + code;
//			setSession("registerEmailCode", code, "registerEmailTime", mailFailureTime);
//			break;
//		case "forgetPwdEmail":
//			mailFailureTime = mailProp.getProperty("forgetPwdFailureTime");
//			subject = forgetPwdSubject;
//			text = forgetPwdText + code;
//			setSession("forgetPwdEmailCode", code, "forgetPwdEmailTime", mailFailureTime);
//			break;
//		default:
//			break;
//		}
//		System.out.println(mailFailureTime);
//
//		// 1. 创建一封邮件
//		MimeMessage message = new MimeMessage(session);
//
//		// 2. From: 发件人（昵称有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改昵称）
//		message.setFrom(new InternetAddress(sendMail, "Dylan.W", "UTF-8"));
//
//		// 3. To: 收件人（可以增加多个收件人、抄送、密送）
//		// message.addRecipient(RecipientType.TO, new
//		// InternetAddress(receiveMail, "收件人昵称", "UTF-8"));
//		if (receiveMail != null && receiveMail.length > 0) {
//			int receiveCount = receiveMail.length;
//			// System.out.println("receiveMail.length：" + receiveCount);
//			if (receiveCount > 0) {
//				InternetAddress[] toAddresses = new InternetAddress[receiveCount];
//				for (int i = 0; i < receiveCount; i++) {
//					toAddresses[i] = new InternetAddress(receiveMail[i], "收件人名称", "UTF-8");
//					message.addRecipient(Message.RecipientType.TO, toAddresses[i]);
//					// System.out.println("i:" + i);
//					System.out.println("receiveMail[i]:" + receiveMail[i]);
//					System.out.println("Constructing message- from=" + sendMail + " to=" + toAddresses[i]);
//				}
//				// message.setRecipients(Message.RecipientType.TO,
//				// toAddresses);
//
//				// 4. Subject: 邮件主题（标题有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改标题）
//				message.setSubject(subject, "UTF-8");
//
//				// 5. Content:
//				// 邮件正文（可以使用html标签）（内容有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改发送内容）
//				// 创建文本“节点”
//				MimeBodyPart mbp_text = new MimeBodyPart();
//				//
//				MimeMultipart mp_text_image = new MimeMultipart();
//				mbp_text.setText(text);
//				mp_text_image.addBodyPart(mbp_text);
//				mp_text_image.setSubType("ralated"); // 关联关系
//
//				// 将 文本+图片 的混合“节点”封装成一个普通“节点”
//				// 最终添加到邮件的 Content 是由多个 BodyPart 组成的 Multipart, 所以我们需要的是
//				// BodyPart,
//				// 上面的 mm_text_image 并非 BodyPart, 所有要把 mm_text_image 封装成一个
//				// BodyPart
//				MimeBodyPart text_image = new MimeBodyPart();
//				text_image.setContent(mp_text_image);
//
//				// 设置（文本）与（附件）的关系 合成一个大的混合“节点” / Multipart ）
//				MimeMultipart mp_all = new MimeMultipart();
//				// 向总“节点”中添加文本图片“节点”
//				mp_all.addBodyPart(text_image);
//
//				// 判断上传路径是否存在
//				// if (filepaths != null && filepaths.length > 0) {
//				// for (String filepath : filepaths) {
//				// // 创建附件“节点
//				// MimeBodyPart mbp_file = new MimeBodyPart();
//				// // 可以直接传file
//				// // DataHandler df_file=new DataHandler(new
//				// // FileDataSource(file));
//				// DataHandler df_file = new DataHandler(new
//				// FileDataSource(filepath));
//				// // 将附件数据添加到“节点”
//				// mbp_file.setDataHandler(df_file);
//				// // 设置附件的文件名（需要编码）
//				// mbp_file.setFileName(MimeUtility.encodeText(df_file.getName()));
//				// // 向总“节点”中添加附件“节点”
//				// mp_all.addBodyPart(mbp_file);
//				// mp_all.setSubType("mixed"); // 混合关系
//				// }
//				// }
//
//				// 设置整个邮件的关系（将最终的混合“节点”作为邮件的内容添加到邮件对象）
//				message.setContent(mp_all, "text/html;charset=UTF-8");
//
//				// 6. 设置发件时间
//				message.setSentDate(new Date());
//
//				// 7. 保存设置
//				message.saveChanges();
//
//			}
//		}
//
//		return message;
//	}
//
//	/**
//	 * 短信发送成功时
//	 * 
//	 * @param codeStr 验证码 key
//	 * @param code    验证码 value
//	 * @param timeStr 有效时间 key
//	 * @param time    有效时间 value
//	 */
//	private static void setSession(String codeStr, String code, String timeStr, String time) {
//		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//		HttpServletRequest request = attributes.getRequest();
//		request.getSession().setAttribute(codeStr, code);
//		request.getSession().setAttribute(timeStr, time);
//		// System.out.println(request.getSession().getAttribute(codeStr));
//	}
//
//}
