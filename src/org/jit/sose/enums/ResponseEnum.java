package org.jit.sose.enums;

/**
 * 统一返回对象的状态码枚举类型
 *
 * @Author Dylan.W
 * @Date 2019/7/15 14:06
 */
public enum ResponseEnum {

	/**
	 * 请求成功。一般用于GET与POST请求
	 */
	SUCCESS(200, "Success"),

	/**
	 * 请求要求用户的身份认证
	 */
	UNAUTHORIZED(401, "do not have permission"),

	/**
	 * 服务器理解请求客户端的请求，但是拒绝执行此请求
	 */
	FORBIDDEN(403, "The request is denied"),

	/**
	 * 数据校验错误
	 */
	DATA_FORMAT_ERROR(420, "Data Format Error"),

	/**
	 * 服务器内部错误
	 */
	ERROR(500, "Internal Server Error"),

	/**
	 * 支付宝相关错误
	 */
	ALIPAY_ERROR(567, "Alipay Error");

	/**
	 * 状态码
	 */
	private Integer code;

	/**
	 * 返回信息
	 */
	private String msg;

	private ResponseEnum(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public Integer getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
}
