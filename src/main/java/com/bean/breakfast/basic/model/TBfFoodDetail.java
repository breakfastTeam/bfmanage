package com.bean.breakfast.basic.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TBfFoodDetail entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_bf_food_detail", catalog = "breakfast", uniqueConstraints = {})
public class TBfFoodDetail implements java.io.Serializable {

	// Fields

	private String detailId;
	private String foodId;
	private String rawMaterialId;
	private Integer rawMaterialCount;
	private Date createTime;
	private String createBy;
	private Date lastModifyTime;
	private String lastModifyBy;
	private Integer optTime;

	// Constructors

	/** default constructor */
	public TBfFoodDetail() {
	}

	/** minimal constructor */
	public TBfFoodDetail(String detailId) {
		this.detailId = detailId;
	}

	/** full constructor */
	public TBfFoodDetail(String detailId, String foodId, String rawMaterialId,
			Integer rawMaterialCount, Date createTime, String createBy,
			Date lastModifyTime, String lastModifyBy, Integer optTime) {
		this.detailId = detailId;
		this.foodId = foodId;
		this.rawMaterialId = rawMaterialId;
		this.rawMaterialCount = rawMaterialCount;
		this.createTime = createTime;
		this.createBy = createBy;
		this.lastModifyTime = lastModifyTime;
		this.lastModifyBy = lastModifyBy;
		this.optTime = optTime;
	}

	// Property accessors
	@Id
	@Column(name = "detail_id", unique = true, nullable = false, insertable = true, updatable = true, length = 32)
	public String getDetailId() {
		return this.detailId;
	}

	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}

	@Column(name = "food_id", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getFoodId() {
		return this.foodId;
	}

	public void setFoodId(String foodId) {
		this.foodId = foodId;
	}

	@Column(name = "raw_material_id", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getRawMaterialId() {
		return this.rawMaterialId;
	}

	public void setRawMaterialId(String rawMaterialId) {
		this.rawMaterialId = rawMaterialId;
	}

	@Column(name = "raw_material_count", unique = false, nullable = true, insertable = true, updatable = true)
	public Integer getRawMaterialCount() {
		return this.rawMaterialCount;
	}

	public void setRawMaterialCount(Integer rawMaterialCount) {
		this.rawMaterialCount = rawMaterialCount;
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