package org.jit.sose.entity;

import java.util.Date;
/**
 * 学期信息实体类
 * @author Administrator
 *
 */
public class TermInfo {
	/**
	 * 学期标识
	 */
    private Integer id;
    /**
     * 学期名称
     */
    private String termName;
    /**
     * 备注
     */
    private String remark;
    /**
     * 状态时间
     */
    private Date stateDate;
    /**
     * 状态
     */
    private String state;
    
    private String postStateDate;

    public String getPostStateDate() {
		return postStateDate;
	}

	public void setPostStateDate(String postStateDate) {
		this.postStateDate = postStateDate;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTermName() {
        return termName;
    }

    public void setTermName(String termName) {
        this.termName = termName == null ? null : termName.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getStateDate() {
        return stateDate;
    }

    public void setStateDate(Date stateDate) {
        this.stateDate = stateDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }
    /**
	 * Mybatis判断数据库是否存在当前数据的依据
	 */
	private Integer count;
}