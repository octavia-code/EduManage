package org.jit.sose.enums;

/**
 * 文件类型枚举类
 * 
 * @author: 王越
 * @date: 2019-07-29 13:48:26
 */
public enum FileTypeEnum {

	/**
	 * 图片类型
	 */
	PICTURE("P", "pic"),

	/**
	 * 视频类型
	 */
	VIDEO("V", "video"),

	/**
	 * 文本类型
	 */
	TEXT("T", "text");

	/**
	 * 文件类型
	 */
	private String type;

	/**
	 * 类型名称(用于文件上传目录)
	 */
	private String typeName;

	private FileTypeEnum(String type, String typeName) {
		this.type = type;
		this.typeName = typeName;
	}

	public String getType() {
		return type;
	}

	public String getTypeName() {
		return typeName;
	}

	/**
	 * 根据type查询对应的类型名称
	 * 
	 * @param type
	 * @return
	 */
	public static FileTypeEnum getByType(String type) {
		for (FileTypeEnum fileTypeEnum : values()) {
			if (fileTypeEnum.getType().equals(type)) {
				return fileTypeEnum;
			}
		}
		// 若无对应类型，直接抛出异常
//		throw new DataFormatException();
		return null;
	}

}
