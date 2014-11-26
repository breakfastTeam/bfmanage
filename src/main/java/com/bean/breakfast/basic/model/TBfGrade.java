package com.bean.breakfast.basic.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TBfGrade entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity(name = "t_bf_grade")
public class TBfGrade implements java.io.Serializable {

	// Fields

	private String gradeId;
	private String userId;
	private String targetType;
	private String targetId;
	private Integer grade;
	private String status;
	private Date createTime;
	private String createBy;
	private Date lastModifyTime;
	private String lastModifyBy;
	private Integer optTime;

	// Constructors

	/** default constructor */
	public TBfGrade() {
	}

	/** minimal constructor */
	public TBfGrade(String gradeId) {
		this.gradeId = gradeId;
	}

	/** full constructor */
	public TBfGrade(String gradeId, String userId, String targetType,
			String targetId, Integer grade, String status, Date createTime,
			String createBy, Date lastModifyTime, String lastModifyBy,
			Integer optTime) {
		this.gradeId = gradeId;
		this.userId = userId;
		this.targetType = targetType;
		this.targetId = targetId;
		this.grade = grade;
		this.status = status;
		this.createTime = createTime;
		this.createBy = createBy;
		this.lastModifyTime = lastModifyTime;
		this.lastModifyBy = lastModifyBy;
		this.optTime = optTime;
	}

	// Property accessors
	@Id
	@Column(name = "grade_id", unique = true, nullable = false, insertable = true, updatable = true, length = 32)
	public String getGradeId() {
		return this.gradeId;
	}

	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}

	@Column(name = "user_id", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "target_type", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public String getTargetType() {
		return this.targetType;
	}

	public void setTargetType(String targetType) {
		this.targetType = targetType;
	}

	@Column(name = "target_id", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getTargetId() {
		return this.targetId;
	}

	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}

	@Column(name = "grade", unique = false, nullable = true, insertable = true, updatable = true)
	public Integer getGrade() {
		return this.grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	@Column(name = "status", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "create_time", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "create_by", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getCreateBy() {
		return this.createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "last_modify_time", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public Date getLastModifyTime() {
		return this.lastModifyTime;
	}

	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	@Column(name = "last_modify_by", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getLastModifyBy() {
		return this.lastModifyBy;
	}

	public void setLastModifyBy(String lastModifyBy) {
		this.lastModifyBy = lastModifyBy;
	}

	@Column(name = "opt_time", unique = false, nullable = true, insertable = true, updatable = true)
	public Integer getOptTime() {
		return this.optTime;
	}

	public void setOptTime(Integer optTime) {
		this.optTime = optTime;
	}

}