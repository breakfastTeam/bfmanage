package com.bean.breakfast.basic.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TBfSetFoods entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity(name = "t_bf_set_foods")
public class TBfSetFoods implements java.io.Serializable {

	// Fields

	private String setFoodsId;
	private String setMealId;
	private String foodId;
	private Integer foodCount;
	private Date createTime;
	private String createBy;
	private Date lastModifyTime;
	private String lastModifyBy;
	private Integer optTime;

	// Constructors

	/** default constructor */
	public TBfSetFoods() {
	}

	/** minimal constructor */
	public TBfSetFoods(String setFoodsId) {
		this.setFoodsId = setFoodsId;
	}

	/** full constructor */
	public TBfSetFoods(String setFoodsId, String setMealId, String foodId,
			Integer foodCount, Date createTime, String createBy,
			Date lastModifyTime, String lastModifyBy, Integer optTime) {
		this.setFoodsId = setFoodsId;
		this.setMealId = setMealId;
		this.foodId = foodId;
		this.foodCount = foodCount;
		this.createTime = createTime;
		this.createBy = createBy;
		this.lastModifyTime = lastModifyTime;
		this.lastModifyBy = lastModifyBy;
		this.optTime = optTime;
	}

	// Property accessors
	@Id
	@Column(name = "set_foods_id", unique = true, nullable = false, insertable = true, updatable = true, length = 32)
	public String getSetFoodsId() {
		return this.setFoodsId;
	}

	public void setSetFoodsId(String setFoodsId) {
		this.setFoodsId = setFoodsId;
	}

	@Column(name = "set_meal_id", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getSetMealId() {
		return this.setMealId;
	}

	public void setSetMealId(String setMealId) {
		this.setMealId = setMealId;
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