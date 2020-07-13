package org.jit.sose.entity;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class CourseType {
	/**
	 * 类别ID
	 */
    private Integer id;
    /**
     * 用户标识
     */
    private Integer userId;
    /**
     * 类别名称
     */
    private String typeName;
    /**
     * 创建时间
     */
    private Timestamp createdDate;
    
    private String pcreatedDate;
    /**
     * 审核状态
     */
    private String state;
    
    private String pstate;
    /**
     * 审核时间
     */
    private Timestamp stateDate;
    
    private String pstateDate;


	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public Date getStateDate() {
        return stateDate;
    }

    public void setStateDate(Timestamp stateDate) {
        this.stateDate = stateDate;
    }

	public String getPcreatedDate() {
		return pcreatedDate;
	}

	public void setPcreatedDate(String pcreatedDate) {
		this.pcreatedDate = pcreatedDate;
	}

	public String getPstateDate() {
		return pstateDate;
	}

	public void setPstateDate(String pstateDate) {
		this.pstateDate = pstateDate;
	}

	public String getPstate() {
		return pstate;
	}

	public void setPstate(String pstate) {
		this.pstate = pstate;
	}

	/**
	 * Mybatis判断数据库是否存在当前数据的依据
	 */
	private Integer count;
}