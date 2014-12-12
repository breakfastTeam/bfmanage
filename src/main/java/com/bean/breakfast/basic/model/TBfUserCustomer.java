package com.bean.breakfast.basic.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TBfUserCustomer entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_bf_user_customer")
public class TBfUserCustomer implements java.io.Serializable {

	// Fields

	private String userId;
	private String customerLevel;
	private Integer credits;
	private String referrer;
	private Date recommendTime;
	private String address1;
	private String address2;
	private String address3;
	private Date createTime;
	private String createBy;
	private Date lastModifyTime;
	private String lastModifyBy;
	private Long optTime;

	// Constructors

	/** default constructor */
	public TBfUserCustomer() {
	}

	/** minimal constructor */
	public TBfUserCustomer(String userId) {
		this.userId = userId;
	}

	/** full constructor */
	public TBfUserCustomer(String userId, String customerLevel,
			Integer credits, String referrer, Date recommendTime,
			String address1, String address2, String address3, Date createTime,
			String createBy, Date lastModifyTime, String lastModifyBy,
			Long optTime) {
		this.userId = userId;
		this.customerLevel = customerLevel;
		this.credits = credits;
		this.referrer = referrer;
		this.recommendTime = recommendTime;
		this.address1 = address1;
		this.address2 = address2;
		this.address3 = address3;
		this.createTime = createTime;
		this.createBy = createBy;
		this.lastModifyTime = lastModifyTime;
		this.lastModifyBy = lastModifyBy;
		this.optTime = optTime;
	}

	// Property accessors
	@Id
	@Column(name = "user_id", unique = true, nullable = false, insertable = true, updatable = true, length = 32)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "customer_level", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	public String getCustomerLevel() {
		return this.customerLevel;
	}

	public void setCustomerLevel(String customerLevel) {
		this.customerLevel = customerLevel;
	}

	@Column(name = "credits", unique = false, nullable = true, insertable = true, updatable = true)
	public Integer getCredits() {
		return this.credits;
	}

	public void setCredits(Integer credits) {
		this.credits = credits;
	}

	@Column(name = "referrer", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getReferrer() {
		return this.referrer;
	}

	public void setReferrer(String referrer) {
		this.referrer = referrer;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "recommend_time", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public Date getRecommendTime() {
		return this.recommendTime;
	}

	public void setRecommendTime(Date recommendTime) {
		this.recommendTime = recommendTime;
	}

	@Column(name = "address1", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getAddress1() {
		return this.address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	@Column(name = "address2", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getAddress2() {
		return this.address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	@Column(name = "address3", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getAddress3() {
		return this.address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
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
	public Long getOptTime() {
		return this.optTime;
	}

	public void setOptTime(Long optTime) {
		this.optTime = optTime;
	}

}