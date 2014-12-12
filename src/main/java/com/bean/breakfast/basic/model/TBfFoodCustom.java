package com.bean.breakfast.basic.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TBfFoodCustom entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_bf_food_custom")
public class TBfFoodCustom implements java.io.Serializable {

	// Fields

	private String foodCustomId;
	private String customOrderName;
	private String customerId;
	private String foodId;
	private Integer foodCount;
	private Double price;
	private String status;
	private Date createTime;
	private String createBy;
	private Date lastModifyTime;
	private String lastModifyBy;
	private Integer optTime;

	// Constructors

	/** default constructor */
	public TBfFoodCustom() {
	}

	/** minimal constructor */
	public TBfFoodCustom(String foodCustomId) {
		this.foodCustomId = foodCustomId;
	}

	/** full constructor */
	public TBfFoodCustom(String foodCustomId, String customOrderName,
			String customerId, String foodId, Integer foodCount, Double price,
			String status, Date createTime, String createBy,
			Date lastModifyTime, String lastModifyBy, Integer optTime) {
		this.foodCustomId = foodCustomId;
		this.customOrderName = customOrderName;
		this.customerId = customerId;
		this.foodId = foodId;
		this.foodCount = foodCount;
		this.price = price;
		this.status = status;
		this.createTime = createTime;
		this.createBy = createBy;
		this.lastModifyTime = lastModifyTime;
		this.lastModifyBy = lastModifyBy;
		this.optTime = optTime;
	}

	// Property accessors
	@Id
	@Column(name = "food_custom_id", unique = true, nullable = false, insertable = true, updatable = true, length = 32)
	public String getFoodCustomId() {
		return this.foodCustomId;
	}

	public void setFoodCustomId(String foodCustomId) {
		this.foodCustomId = foodCustomId;
	}

	@Column(name = "custom_order_name", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	public String getCustomOrderName() {
		return this.customOrderName;
	}

	public void setCustomOrderName(String customOrderName) {
		this.customOrderName = customOrderName;
	}

	@Column(name = "customer_id", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	@Column(name = "food_id", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getFoodId() {
		return this.foodId;
	}

	public void setFoodId(String foodId) {
		this.foodId = foodId;
	}

	@Column(name = "food_count", unique = false, nullable = true, insertable = true, updatable = true)
	public Integer getFoodCount() {
		return this.foodCount;
	}

	public void setFoodCount(Integer foodCount) {
		this.foodCount = foodCount;
	}

	@Column(name = "price", unique = false, nullable = true, insertable = true, updatable = true, precision = 8, scale = 3)
	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
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