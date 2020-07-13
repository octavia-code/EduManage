package org.jit.sose.exception;

import java.io.IOException;

import org.jit.sose.util.LogUtil;
import org.jit.sose.util.ResponseUtil;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

/**
 * 全局捕获异常
 * 
 * @author: 王越
 * @date: 2019年4月4日 下午11:08:20
 */
@Slf4j
@ControllerAdvice(basePackages = "org.jit.sose.controller") // 作为全局异常处理的切面类，可以设置包的范围
@ResponseBody
public class GlobalExceptionHandler {

	/**
	 * 自定义数据格式异常
	 * 
	 * @param e 异常内容
	 * @return
	 */
	@ExceptionHandler(DataFormatException.class)
	public String DataFormatException(DataFormatException e) {
		log.info("数据格式异常：" + e.getMsg());
		LogUtil.logResult("RuntimeException", e);
		e.printStackTrace();
		return ResponseUtil.error(e.getMsg());
	}

	/**
	 * 自定义数据格式异常
	 * 
	 * @param e 异常内容
	 * @return
	 */
//	@ResponseStatus(HttpStatus.BAD_REQUEST)// 处理400异常类
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public String HttpMessageNotReadableException(HttpMessageNotReadableException e) {
		LogUtil.logResult("RuntimeException", e);
		e.printStackTrace();
		return ResponseUtil.error("参数传入异常");
	}

	/**
	 * 捕获运行时异常
	 * 
	 * @param e 异常内容
	 * @return
	 */
	@ExceptionHandler(RuntimeException.class) // 设置具体捕获异常类
	public String RuntimeException(Exception e) {
		System.out.println("*****************全局捕获运行时异常****************");
		LogUtil.logResult("RuntimeException", e);
		e.printStackTrace();
		return ResponseUtil.error("全局捕获运行时异常");
	}

	/**
	 * 没有捕获到
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler(org.springframework.jdbc.CannotGetJdbcConnectionException.class) // 设置具体捕获异常类
	public String CannotGetJdbcConnectionException(Exception e) {
		System.out.println("*****************数据库连接超时****************");
		LogUtil.logResult("RuntimeException", e);
		e.printStackTrace();
		return ResponseUtil.error("网络连接超时");
	}

	/**
	 * 捕获空指针异常
	 * 
	 * @param e 异常内容
	 * @return
	 */
	@ExceptionHandler(NullPointerException.class)
	public String NullPointException(Exception e) {
		System.out.println("*****************全局捕获空指针异常*****************");
		LogUtil.logResult("NullPointerException", e);
		e.printStackTrace();
		return ResponseUtil.error("全局捕获空指针异常");
	}

	// 类型转换异常
	@ExceptionHandler(ClassCastException.class)
	@ResponseBody
	public String classCastExceptionHandler(ClassCastException ex) {
		ex.printStackTrace();
		return null;
	}

	// IO异常
	@ExceptionHandler(IOException.class)
	@ResponseBody
	public String iOExceptionHandler(IOException ex) {
		ex.printStackTrace();
		return null;
	}

	// 未知方法异常
	@ExceptionHandler(NoSuchMethodException.class)
	@ResponseBody
	public String noSuchMethodExceptionHandler(NoSuchMethodException ex) {
		ex.printStackTrace();
		return null;
	}

	// 数组越界异常
	@ExceptionHandler(IndexOutOfBoundsException.class)
	@ResponseBody
	public String indexOutOfBoundsExceptionHandler(IndexOutOfBoundsException ex) {
		ex.printStackTrace();
		return null;
	}

	// 400错误
//	@ResponseStatus(HttpStatus.BAD_REQUEST)// 处理400异常类

	// 400错误
	@ExceptionHandler({ TypeMismatchException.class })
	@ResponseBody
	public String requestTypeMismatch(TypeMismatchException ex) {
		System.out.println("400..TypeMismatchException");
		ex.printStackTrace();
		return null;
	}

	// 400错误
	@ExceptionHandler({ MissingServletRequestParameterException.class })
	@ResponseBody
	public String requestMissingServletRequest(MissingServletRequestParameterException ex) {
		System.out.println("400..MissingServletRequest");
		ex.printStackTrace();
		return null;
	}

	// 405错误
	@ExceptionHandler({ HttpRequestMethodNotSupportedException.class })
	@ResponseBody
	public String request405() {
		System.out.println("405...");
		return null;
	}

	// 406错误
	@ExceptionHandler({ HttpMediaTypeNotAcceptableException.class })
	@ResponseBody
	public String request406() {
		System.out.println("404...");
		return null;
	}

	// 500错误
	@ExceptionHandler({ ConversionNotSupportedException.class, HttpMessageNotWritableException.class })
	@ResponseBody
	public String server500(RuntimeException runtimeException) {
		System.out.println("500...");
		return null;
	}

}
