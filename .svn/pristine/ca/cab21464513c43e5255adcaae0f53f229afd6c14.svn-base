package org.jit.sose.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

import org.jit.sose.config.FileConfig;

import lombok.extern.slf4j.Slf4j;

/**
 * 日志信息存储
 * 
 * @author: dylan
 * @date: 2019年4月4日 下午10:38:14
 */
@Slf4j
public class LogUtil {

	/**
	 * 写日志
	 * 
	 * @param title 日志文件标题
	 * @param sWord 要写入日志里的文本内容
	 */
	public static void logResult(String title, String sWord) {
		FileWriter writer = null;
		try {
			writer = new FileWriter(FileConfig.LOG_EXCEPTION_PATH + File.separator + "log_"
					+ DateFormatUtil.formatCode(new Date()) + "_" + title + ".txt");
			writer.write(sWord);
		} catch (Exception e) {
			log.error("日志写入异常");
			e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					log.error("日志输出关闭异常");
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 写日志
	 * 
	 * @param sWord 要写入日志里的文本内容
	 */
	public static void logResult(String sWord) {
		FileWriter writer = null;
		try {
			writer = new FileWriter(FileConfig.LOG_EXCEPTION_PATH + File.separator + "log_"
					+ DateFormatUtil.formatCode(new Date()) + ".txt");
			writer.write(sWord);
		} catch (Exception e) {
			log.error("日志写入异常");
			e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					log.error("日志输出关闭异常");
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 将异常写日志
	 * 
	 * @param title     日志文件标题
	 * @param exception
	 */
	public static void logResult(String title, Exception exception) {
		FileWriter writer = null;
		try {
			writer = new FileWriter(FileConfig.LOG_EXCEPTION_PATH + File.separator + "exception_log_"
					+ DateFormatUtil.formatCode(new Date()) + "_" + title + ".txt");
			writer.write(getErrorInfoFromException(exception));
		} catch (Exception e) {
			log.error("日志写入异常");
			e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					log.error("日志输出关闭异常");
					e.printStackTrace();
				}
			}
		}
	}

	public static String getErrorInfoFromException(Exception exception) {
		try {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			exception.printStackTrace(pw);
			return "\r\n" + sw.toString() + "\r\n";
		} catch (Exception e) {
			return "bad getErrorInfoFromException";
		}
	}

}
