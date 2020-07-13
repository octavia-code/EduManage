package org.jit.sose.response;

import java.io.File;

import org.jit.sose.enums.ResponseEnum;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice(basePackages = "org.jit.sose.controller") // 两种方式
//@ControllerAdvice(annotations = {RestController.class, Controller.class})
@ResponseBody
public class MyResponseBodyAdvice implements ResponseBodyAdvice<Object> {

	/**
	 * Jackson序列化框架序列化的是增强的结果对象，而不是Actioc中接口方法返回值代表的那个对象 统一对返回对象进行数据处理
	 */
	@Override
	public Object beforeBodyWrite(Object obj, MethodParameter returnType, MediaType selectedContentType,
			Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
			ServerHttpResponse response) {
		String returnTypeName = returnType.getParameterType().getName();
		if ("void".equals(returnTypeName)) {
			log.debug("返回值类型：" + returnTypeName);
		}

		// 支付宝回调方法返回参数
		if ("success".equals(obj) || "fail".equals(obj)) {
			return obj;
		}
		// 返回对象属于自定义 或者 String
		if (obj instanceof CommonResp || obj instanceof String) {
			return obj;
		}
		// 属于文件类型
		if (obj instanceof File) {
			return obj;
		}
		log.info("MyResponseBodyAdvice==>beforeBodyWrite:" + returnType + "," + obj);
		CommonResp resp = new CommonResp();
		resp.setCode(ResponseEnum.SUCCESS.getCode());
		resp.setMsg(ResponseEnum.SUCCESS.getMsg());
		resp.setObj(obj);

		obj = (Object) resp;
		return obj;

	}

	/**
	 * 只有序列化框架是Jackson才对返回结果进行增强，具体项目中使用了哪一种序列化框架，修改该方法的实现即可。
	 * 判断支持的类型，因为我们定义的BaseResponseVo 里面的data可能是任何类型，这里就不判断统一放过
	 * 如果你想对执行的返回体进行操作，可将上方的Object换成你自己的类型
	 */
	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		log.trace("MyResponseBodyAdvice==>supports:" + converterType);
		log.trace("MyResponseBodyAdvice==>supports:" + returnType.getClass());
		log.trace("MyResponseBodyAdvice==>supports:"
				+ MappingJackson2HttpMessageConverter.class.isAssignableFrom(converterType));
		return MappingJackson2HttpMessageConverter.class.isAssignableFrom(converterType);
//        return true;
	}

}
