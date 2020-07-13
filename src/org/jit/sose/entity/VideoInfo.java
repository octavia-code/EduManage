package org.jit.sose.entity;

import java.util.Date;

import lombok.Data;

/**
 * 
 * @author wangrui
 *
 */
@Data
public class VideoInfo {
	
	/**
	 * 标识
	 */
    private Integer id;

    private String title;

    private String path;

    private String state;

    private Date createdDate;

    
}