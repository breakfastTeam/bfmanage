package com.bean.breakfast.basic.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TBfCoupon entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_bf_coupon", catalog = "breakfast", uniqueConstraints = {})
public class TBfCoupon implements java.io.Serializable {

	// Fields

	private String couponId;
	private String userId;
	private Date startTime;
	private Date endTime;
	private Double price;
	private String source;
	private String status;
	private String briefIntro;
	private Date createTime;
	private String createBy;
	private Date lastModifyTime;
	private String lastModifyBy;
	private Long optTime;

	// Constructors

	/** default constructor */
	public TBfCoupon() {
	}

	/** minimal constructor */
	public TBfCoupon(String couponId) {
		this.couponId = couponId;
	}

	/** full constructor */
	public TBfCoupon(String couponId, String userId, Date startTime,
			Date endTime, Double price, String source, String status,
			String briefIntro, Date createTime, String createBy,
			Date lastModifyTime, String lastModifyBy, Long optTime) {
		this.couponId = couponId;
		this.userId = userId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.price = price;
		this.source = source;
		this.status = status;
		this.briefIntro = briefIntro;
		this.createTime = createTime;
		this.createBy = createBy;
		this.lastModifyTime = lastModifyTime;
		this.lastModifyBy = lastModifyBy;
		this.optTime = optTime;
	}

	// Property accessors
	@Id
	@Column(name = "coupon_id", unique = true, nullable = false, insertable = true, updatable = true, length = 32)
	public String getCouponId() {
		return this.couponId;
	}

	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}

	@Column(name = "user_id", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "start_time", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "end_time", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Column(name = "price", unique = false, nullable = true, insertable = true, updatable = true, precision = 8, scale = 3)
	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Column(name = "source", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getSource() {
		return this.source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	@Column(name = "status", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "brief_intro", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getBriefIntro() {
		return this.briefIntro;
	}

	public void setBriefIntro(String briefIntro) {
		this.briefIntro = briefIntro;
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