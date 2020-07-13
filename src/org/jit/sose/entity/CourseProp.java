package org.jit.sose.entity;

import java.util.Date;
/**
 * 課程信息实体类
 * @author Administrator
 *
 */
public class CourseProp {
	/**
	 * 性质id
	 */
    private Integer id;
    /**
     * 性质名称
     */
    private String propName;
    /**
     * 用户表示
     */
    private Integer userId;
    /**
     * 创建时间
     */
    private Date createdDate;
    /**
     * 审核状态
     */
    private String state;
    /**
     * 审核时间
     */
    private Date stateDate;
    
    private String pcreatedDate;
    
    private String pstateDate;
    
    private String pstate;

	public String getPstate() {
		return pstate;
	}

	public void setPstate(String pstate) {
		this.pstate = pstate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPropName() {
		return propName;
	}

	public void setPropName(String propName) {
		this.propName = propName;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getStateDate() {
		return stateDate;
	}

	public void setStateDate(Date stateDate) {
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

	@Override
	public String toString() {
		return "CourseProp [id=" + id + ", propName=" + propName + ", userId=" + userId + ", createdDate=" + createdDate
				+ ", state=" + state + ", stateDate=" + stateDate + ", pcreatedDate=" + pcreatedDate + ", pstateDate="
				+ pstateDate + "]";
	}

	/**
	 * Mybatis判断数据库是否存在当前数据的依据
	 */
	private Integer count;
    
}