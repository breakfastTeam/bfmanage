package com.bean.breakfast.basic.model;

import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import javax.persistence.*;

/**
 * TBfOrderDetail entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_bf_order_detail")
public class TBfOrderDetail implements java.io.Serializable {

	// Fields

	private String detailId;
	private String orderId;
	private String foodObjId;
	private Integer foodObjCount;
	private String foodObjType;
	private Date createTime;
	private String createBy;
	private Date lastModifyTime;
	private String lastModifyBy;
	private Long optTime;

	// Constructors

	/** default constructor */
	public TBfOrderDetail() {
	}

	/** minimal constructor */
	public TBfOrderDetail(String detailId) {
		this.detailId = detailId;
	}

	/** full constructor */
	public TBfOrderDetail(String detailId, String orderId, String foodObjId,
			Integer foodObjCount, String foodObjType, Date createTime,
			String createBy, Date lastModifyTime, String lastModifyBy,
			Long optTime) {
		this.detailId = detailId;
		this.orderId = orderId;
		this.foodObjId = foodObjId;
		this.foodObjCount = foodObjCount;
		this.foodObjType = foodObjType;
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
	@Column(name = "detail_id", unique = true, nullable = false, insertable = true, updatable = true, length = 32)
	public String getDetailId() {
		return this.detailId;
	}

	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}

	@Column(name = "order_id", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getOrderId() {
		return this.orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@Column(name = "food_obj_id", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getFoodObjId() {
		return this.foodObjId;
	}

	public void setFoodObjId(String foodObjId) {
		this.foodObjId = foodObjId;
	}

	@Column(name = "food_obj_count", unique = false, nullable = true, insertable = true, updatable = true)
	public Integer getFoodObjCount() {
		return this.foodObjCount;
	}

	public void setFoodObjCount(Integer foodObjCount) {
		this.foodObjCount = foodObjCount;
	}

	@Column(name = "food_obj_type", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public String getFoodObjType() {
		return this.foodObjType;
	}

	public void setFoodObjType(String foodObjType) {
		this.foodObjType = foodObjType;
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
	public Long getOptTime() {
		return this.optTime;
	}

	public void setOptTime(Long optTime) {
		this.optTime = optTime;
	}

}