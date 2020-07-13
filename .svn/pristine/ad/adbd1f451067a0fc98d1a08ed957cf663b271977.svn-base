package org.jit.sose.util;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;

import org.jit.sose.config.QcloudSmsConfig;
import org.json.JSONException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;

/**
 * 通过腾讯云 指定模板发送短信
 * 
 * @author: 王越
 * @date: 2019-06-25 11:03:21
 */
public class QcloudSmsUtil {

	/**
	 * 获取自定义长度的验证码随机数
	 * 
	 * @return 验证码
	 */
	public static String smsCode(Integer smsCodeLength) {
		String code = "";
		for (int i = 0; i < smsCodeLength; i++) {
			code += (int) (Math.random() * 9 + 1);
		}
		return code;
	}

	/**
	 * 指定模板ID单发登录，注册，忘记密码短信
	 * 
	 * @param phoneNumbers 不带国家码的手机号列表
	 * @param phoneType    短信发送类型:registerPhone forgetPwdPhone loginPhone
	 * @return 返回错误码 返回0代表成功
	 */
	public static Integer sendQcloudSms(String[] phoneNumbers, String phoneType) {
		System.out.println("短信模板：" + phoneType);
		// 短信模板ID，需要在短信应用中申请
		// 真实的模板ID需要在短信控制台中申请
		int phoneTypeId = 0;
		// 自定义验证码
		String code = smsCode(QcloudSmsConfig.smsCodeLength);
		// 自定义短信失效时间
		String qMsgFailureTime = "";
		switch (phoneType) {
		case "registerPhone":
			phoneTypeId = QcloudSmsConfig.registerTemplateId;
			qMsgFailureTime = QcloudSmsConfig.registerFailureTime;
			break;
		case "forgetPwdPhone":
			phoneTypeId = QcloudSmsConfig.forgetPwdTemplateId;
			qMsgFailureTime = QcloudSmsConfig.forgetPwdFailureTime;
			break;
		case "loginPhone":
			phoneTypeId = QcloudSmsConfig.loginTemplateId;
			qMsgFailureTime = QcloudSmsConfig.loginFailureTime;
			break;
		default:
			break;
		}
		SmsSingleSenderResult result = null; // 短信发送返回信息
		try {
			// params 为短信模板中{1}{2}的具体内容
			String[] params = { code, qMsgFailureTime };
			SmsSingleSender msender = new SmsSingleSender(QcloudSmsConfig.appId, QcloudSmsConfig.appKey);
			/**
			 * @param nationCode  国家码，如 86 为中国
			 * @param phoneNumber 不带国家码的手机号d
			 * @param phoneTypeId 短信正文id
			 * @param params      信息内容
			 * @param params      模板参数列表，如模板 {1}...{2}...{3}，那么需要带三个参数
			 * @param smsSign     签名，如果填空，系统会使用默认签名
			 */
			result = msender.sendWithParam("86", phoneNumbers[0], phoneTypeId, params, QcloudSmsConfig.smsSign, "", "");
			System.out.println(result);
			System.out.println("***********发送结束***********");
		} catch (HTTPException e) {
			// HTTP响应码错误
			e.printStackTrace();
			return -1;
		} catch (JSONException e) {
			// json解析错误
			e.printStackTrace();
			return -2;
		} catch (IOException e) {
			// 网络IO错误
			e.printStackTrace();
			return -3;
		}
		// 获取返回值
		int a = result.result;

		if (a == 0) {
			// 发送成功时，将验证码和有效时间放入session中
			switch (phoneType) {
			case "registerPhone":
				setSession("registerSmsCode", code, "registerFailureTime", qMsgFailureTime);
				break;
			case "forgetPwdPhone":
				setSession("forgetPwdSmsCode", code, "forgetPwdFailureTime", qMsgFailureTime);
				break;
			case "loginPhone":
				setSession("loginSmsCode", code, "loginFailureTime", qMsgFailureTime);
				break;
			default:
				break;
			}
		}
		return a;
	}

	/**
	 * 短信发送成功时
	 * 
	 * @param codeStr 短信验证码 key
	 * @param code    短信验证码 value
	 * @param timeStr 短信有效时间 key
	 * @param time    短信有效时间 value
	 */
	private static void setSession(String codeStr, String code, String timeStr, String time) {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		request.getSession().setAttribute(codeStr, code);
		request.getSession().setAttribute(timeStr, time);
		System.out.println("短信验证码：" + request.getSession().getAttribute(codeStr));
	}

}