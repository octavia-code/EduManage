package org.jit.sose.entity;

import java.sql.Timestamp;

import lombok.Data;

/**
 * 文件对象
 * 
 * @author: 王越
 * @date: 2019-06-30 22:51:01
 */
@Data
public class FileInfo {

	public FileInfo() {
		super();
	}

	public FileInfo(String fileName, String accessUrl, String type) {
		super();
		this.fileName = fileName;
		this.accessUrl = accessUrl;
		this.type = type;
	}
	
	public FileInfo(Integer id, String fileName, String accessUrl, String type, Timestamp createdDate, String state) {
		super();
		this.id = id;
		this.fileName = fileName;
		this.accessUrl = accessUrl;
		this.type = type;
		this.createdDate = createdDate;
		this.state = state;
	}



	/**
	 * 标识
	 */
	private Integer id;

	/**
	 * 文件名称
	 */
	private String fileName;

	/**
	 * 文件访问路径
	 */
	private String accessUrl;

	/**
	 * 文件类型
	 */
	private String type;

	/**
	 * 文件上传时间
	 */
	private Timestamp createdDate;

	/**
	 * 状态：在用-'A';待用-'W';删除-'X'
	 */
	private String state;

}