package com.bean.breakfast.basic.model;

import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import javax.persistence.*;

/**
 * TBfOrder entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity(name = "t_bf_order")
public class TBfOrder implements java.io.Serializable {

	// Fields

	private String orderId;
	private String customerId;
	private String orderType;
	private String status;
	private String consigneeName;
	private String consigneeAddress;
	private String consigneeMobile;
	private Integer orderPrice;
	private Integer exccreaditCount;
	private String usedCoupons;
	private Date createTime;
	private String createBy;
	private Date lastModifyTime;
	private String lastModifyBy;
	private Integer optTime;
	private String remark;

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
			String consigneeMobile, Integer orderPrice,
			Integer exccreaditCount, String usedCoupons, Date createTime,
			String createBy, Date lastModifyTime, String lastModifyBy,
			Integer optTime, String remark) {
		this.orderId = orderId;
		this.customerId = customerId;
		this.orderType = orderType;
		this.status = status;
		this.consigneeName = consigneeName;
		this.consigneeAddress = consigneeAddress;
		this.consigneeMobile = consigneeMobile;
		this.orderPrice = orderPrice;
		this.exccreaditCount = exccreaditCount;
		this.usedCoupons = usedCoupons;
		this.createTime = createTime;
		this.createBy = createBy;
		this.lastModifyTime = lastModifyTime;
		this.lastModifyBy = lastModifyBy;
		this.optTime = optTime;
		this.remark = remark;
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

	@Column(name = "consignee_mobile", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public String getConsigneeMobile() {
		return this.consigneeMobile;
	}

	public void setConsigneeMobile(String consigneeMobile) {
		this.consigneeMobile = consigneeMobile;
	}

	@Column(name = "order_price", unique = false, nullable = true, insertable = true, updatable = true)
	public Integer getOrderPrice() {
		return this.orderPrice;
	}

	public void setOrderPrice(Integer orderPrice) {
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

	@Column(name = "remark", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}