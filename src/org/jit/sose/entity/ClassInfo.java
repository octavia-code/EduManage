package org.jit.sose.entity;

import java.sql.Timestamp;

public class ClassInfo {
    private Integer id;

    private String className;

    private Integer specialtyId;

    private Integer yearPlanId;

    private String remark;

    private Timestamp createdDate;

    private Timestamp stateDate;

    private String state;
    
    private String subjectName;
    
    private String yearName;

    /**
	 * Mybatis判断数据库是否存在当前数据的依据
	 */
	private Integer count;
	
    public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getYearName() {
		return yearName;
	}

	public void setYearName(String yearName) {
		this.yearName = yearName;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className == null ? null : className.trim();
    }

    public Integer getSpecialtyId() {
        return specialtyId;
    }

    public void setSpecialtyId(Integer specialtyId) {
        this.specialtyId = specialtyId;
    }

    public Integer getYearPlanId() {
        return yearPlanId;
    }

    public void setYearPlanId(Integer yearPlanId) {
        this.yearPlanId = yearPlanId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public Timestamp getStateDate() {
		return stateDate;
	}

	public void setStateDate(Timestamp stateDate) {
		this.stateDate = stateDate;
	}

	public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }
}