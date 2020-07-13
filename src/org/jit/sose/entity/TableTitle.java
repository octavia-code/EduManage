package org.jit.sose.entity;

import lombok.Data;

/**
 * 表头实体类<br>
 * 配置表格头部信息
 * 
 * @author: 孔维国
 * @date: 2019年8月19日 下午6:13:27
 */
@Data
public class TableTitle {
	/**
	 * 标识
	 */
	private Integer id;

	/**
	 * 数据库表名
	 */
	private String tableName;

	/**
	 * 键-字段名称,对应实体类名称
	 */
	private String tableKey;

	/**
	 * 值-表格显示名称
	 */
	private String title;

	/**
	 * 排序
	 */
	private Integer seq;

	/**
	 * 是否启用:（1-启用）（0-禁用）
	 */
	private boolean enable;
	
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableKey() {
        return tableKey;
    }

    public void setTableKey(String tableKey) {
        this.tableKey = tableKey;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}
	/**
	 * Mybatis判断数据库是否存在当前数据的依据
	 */
	private Integer count;
}