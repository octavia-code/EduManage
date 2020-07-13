package org.jit.sose.entity;


import java.sql.Timestamp;

import java.util.Date;

import lombok.Data;

@Data
public class Eecstate {
    private Integer id;

    /**
     * 表
     */
    private String table;

    
    /**
     * 表名
     */
    private String tableName;
    
    /**
     * 列
     */
    private String colm;

    /**
     * 列名
     */
    private String colmName;

    /**
     * 编码
     */
    private String code;

    /**
     * 编码名
     */
    private String codeName;

	private Integer seq;
    
    private String remark;

    private String state;

    private Timestamp createDate;

    private Timestamp stateDate;

   
}