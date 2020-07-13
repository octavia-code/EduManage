package org.jit.sose.util;

import org.jit.sose.enums.ResponseEnum;
import org.jit.sose.response.CommonResp;

import com.alibaba.fastjson.JSON;

/**
 * 统一请求返回结果封装工具类
 * 
 * @author: 王越
 * @date: 2019年6月5日 上午10:50:13
 */
public class ResponseUtil {

	/**
	 * 返回成功结果
	 * 
	 * @param obj 返回参数对象
	 * @return 成功结果集
	 */
	public static String success(Object obj) {
		CommonResp resp = new CommonResp();
		resp.setCode(ResponseEnum.SUCCESS.getCode());
		resp.setMsg(ResponseEnum.SUCCESS.getMsg());
		resp.setObj(obj);
		return JSON.toJSONString(resp);
	}

	/**
	 * 返回错误结果
	 * 
	 * @param obj 返回参数对象
	 * @return 失败结果集
	 */
	public static String error(Object obj) {
		CommonResp resp = new CommonResp();
		resp.setCode(ResponseEnum.ERROR.getCode());
		resp.setMsg(ResponseEnum.ERROR.getMsg());
		resp.setObj(obj);
		return JSON.toJSONString(resp);
	}

}
