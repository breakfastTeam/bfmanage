package com.bean.breakfast.basic.model;

import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import javax.persistence.*;

/**
 * TBfSendCoupon entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_bf_send_coupon")
public class TBfSendCoupon implements java.io.Serializable {

	// Fields
	private String sendCouponId;
	private Date createTime;
	private Date endTime;
	private Double price;
	private String source;
	private String status;
	private String briefIntro;
	private Integer num;
	private String url;
	private String createBy;
	private Date lastModifyTime;
	private String lastModifyBy;
	private Long optTime;

	// Constructors

	/** default constructor */
	public TBfSendCoupon() {
	}

	/** minimal constructor */
	public TBfSendCoupon(String sendCouponId) {
		this.sendCouponId = sendCouponId;
	}

	/** full constructor */
	public TBfSendCoupon(String sendCouponId, Date createTime, Date endTime,
			Double price, String source, String status, String briefIntro,
			Integer num, String url, String createBy, Date lastModifyTime,
			String lastModifyBy, Long optTime) {
		this.sendCouponId = sendCouponId;
		this.createTime = createTime;
		this.endTime = endTime;
		this.price = price;
		this.source = source;
		this.status = status;
		this.briefIntro = briefIntro;
		this.num = num;
		this.url = url;
		this.createBy = createBy;
		this.lastModifyTime = lastModifyTime;
		this.lastModifyBy = lastModifyBy;
		this.optTime = optTime;
	}

	// Property accessors
	@Id
	@GeneratedValue(generator = "id")
	@GenericGenerator(name = "id", strategy = "uuid")
	@Column(name = "send_coupon_id", unique = true, nullable = false, insertable = true, updatable = true, length = 32)
	public String getSendCouponId() {
		return this.sendCouponId;
	}

	public void setSendCouponId(String sendCouponId) {
		this.sendCouponId = sendCouponId;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "create_time", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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

	@Column(name = "num", unique = false, nullable = true, insertable = true, updatable = true)
	public Integer getNum() {
		return this.num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	@Column(name = "url", unique = false, nullable = true, insertable = true, updatable = true, length = 300)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
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