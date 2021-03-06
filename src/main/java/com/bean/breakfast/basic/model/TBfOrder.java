package com.bean.breakfast.basic.model;

import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import javax.persistence.*;

/**
 * TBfOrder entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_bf_order")
public class TBfOrder implements java.io.Serializable {

	// Fields

	private String orderId;
	private String customerId;
	private String orderType;
	private String status;
	private String consigneeName;
	private String consigneeAddress;
	private String consigneeMobile;
	private String orderNo;
	private Double orderPrice;
	private Integer exccreaditCount;
	private String usedCoupons;
	private String comments;
	private Date preSendDate;
	private String preSendTime;
	private Date createTime;
	private String createBy;
	private Date lastModifyTime;
	private String lastModifyBy;
	private Integer optTime;

	// Constructors

	/** default constructor */
	public TBfOrder() {
	}

	/** minimal constructor */
	public TBfOrder(String orderId) {
		this.orderId = orderId;
	}

	/** full constructor */
	public TBfOrder(String orderId, String customerId, String orderType,
			String status, String consigneeName, String consigneeAddress,
			String consigneeMobile, Double orderPrice, Integer exccreaditCount,
			String usedCoupons, String comments, Date createTime,String orderNo,
			String createBy, Date lastModifyTime, String lastModifyBy,
			Integer optTime) {
		this.orderId = orderId;
		this.customerId = customerId;
		this.orderType = orderType;
		this.status = status;
		this.consigneeName = consigneeName;
		this.consigneeAddress = consigneeAddress;
		this.consigneeMobile = consigneeMobile;
		this.orderNo = orderNo;
		this.orderPrice = orderPrice;
		this.exccreaditCount = exccreaditCount;
		this.usedCoupons = usedCoupons;
		this.comments = comments;
		this.createTime = createTime;
		this.createBy = createBy;
		this.lastModifyTime = lastModifyTime;
		this.lastModifyBy = lastModifyBy;
		this.optTime = optTime;
	}

	// Property accessors
	@Id
	@GeneratedValue(generator = "id")
	@GenericGenerator(name = "id", strategy = "uuid")
	@Column(name = "order_id", unique = true, nullable = false, insertable = true, updatable = true, length = 32)
	public String getOrderId() {
		return this.orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@Column(name = "customer_id", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	@Column(name = "order_type", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getOrderType() {
		return this.orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	@Column(name = "status", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "consignee_name", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public String getConsigneeName() {
		return this.consigneeName;
	}

	public void setConsigneeName(String consigneeName) {
		this.consigneeName = consigneeName;
	}

	@Column(name = "consignee_address", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getConsigneeAddress() {
		return this.consigneeAddress;
	}

	public void setConsigneeAddress(String consigneeAddress) {
		this.consigneeAddress = consigneeAddress;
	}

	@Column(name = "order_no", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	@Column(name = "consignee_mobile", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public String getConsigneeMobile() {
		return this.consigneeMobile;
	}

	public void setConsigneeMobile(String consigneeMobile) {
		this.consigneeMobile = consigneeMobile;
	}

	@Column(name = "order_price", unique = false, nullable = true, insertable = true, updatable = true, precision = 8, scale = 3)
	public Double getOrderPrice() {
		return this.orderPrice;
	}

	public void setOrderPrice(Double orderPrice) {
		this.orderPrice = orderPrice;
	}

	@Column(name = "exccreadit_count", unique = false, nullable = true, insertable = true, updatable = true)
	public Integer getExccreaditCount() {
		return this.exccreaditCount;
	}

	public void setExccreaditCount(Integer exccreaditCount) {
		this.exccreaditCount = exccreaditCount;
	}

	@Column(name = "used_coupons", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getUsedCoupons() {
		return this.usedCoupons;
	}

	public void setUsedCoupons(String usedCoupons) {
		this.usedCoupons = usedCoupons;
	}

	@Column(name = "comments", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Column(name = "pre_send_time", unique = false, nullable = true, insertable = true, updatable = true, length = 24)
	public String getPreSendTime() {
		return this.preSendTime;
	}

	public void setPreSendTime(String preSendTime) {
		this.preSendTime = preSendTime;
	}


	@Temporal(TemporalType.DATE)
	@Column(name = "pre_send_date", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public Date getPreSendDate() {
		return this.preSendDate;
	}

	public void setPreSendDate(Date preSendDate) {
		this.preSendDate = preSendDate;
	}




	@Temporal(TemporalType.TIMESTAMP)
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

	@Temporal(TemporalType.TIMESTAMP)
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