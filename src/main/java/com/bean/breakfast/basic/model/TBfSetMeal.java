package com.bean.breakfast.basic.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TBfSetMeal entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity(name = "t_bf_set_meal")
public class TBfSetMeal implements java.io.Serializable {

	// Fields

	private String setMealId;
	private String setName;
	private String introduce;
	private Date starTime;
	private Date endTime;
	private Double price;
	private Double privilege;
	private String status;
	private Integer foodCount;
	private Integer realFoodCount;
	private String smallPicId;
	private String orginPicId;
	private Integer showOrder;
	private Date createTime;
	private String createBy;
	private Date lastModifyTime;
	private String lastModifyBy;
	private Integer optTime;

	// Constructors

	/** default constructor */
	public TBfSetMeal() {
	}

	/** minimal constructor */
	public TBfSetMeal(String setMealId) {
		this.setMealId = setMealId;
	}

	/** full constructor */
	public TBfSetMeal(String setMealId, String setName, String introduce,
			Date starTime, Date endTime, Double price, Double privilege,
			String status, Integer foodCount, Integer realFoodCount,
			String smallPicId, String orginPicId, Integer showOrder,
			Date createTime, String createBy, Date lastModifyTime,
			String lastModifyBy, Integer optTime) {
		this.setMealId = setMealId;
		this.setName = setName;
		this.introduce = introduce;
		this.starTime = starTime;
		this.endTime = endTime;
		this.price = price;
		this.privilege = privilege;
		this.status = status;
		this.foodCount = foodCount;
		this.realFoodCount = realFoodCount;
		this.smallPicId = smallPicId;
		this.orginPicId = orginPicId;
		this.showOrder = showOrder;
		this.createTime = createTime;
		this.createBy = createBy;
		this.lastModifyTime = lastModifyTime;
		this.lastModifyBy = lastModifyBy;
		this.optTime = optTime;
	}

	// Property accessors
	@Id
	@Column(name = "set_meal_id", unique = true, nullable = false, insertable = true, updatable = true, length = 32)
	public String getSetMealId() {
		return this.setMealId;
	}

	public void setSetMealId(String setMealId) {
		this.setMealId = setMealId;
	}

	@Column(name = "set_name", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getSetName() {
		return this.setName;
	}

	public void setSetName(String setName) {
		this.setName = setName;
	}

	@Column(name = "introduce", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getIntroduce() {
		return this.introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "star_time", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public Date getStarTime() {
		return this.starTime;
	}

	public void setStarTime(Date starTime) {
		this.starTime = starTime;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "end_time", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Column(name = "price", unique = false, nullable = true, insertable = true, updatable = true, precision = 7, scale = 3)
	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Column(name = "privilege", unique = false, nullable = true, insertable = true, updatable = true, precision = 7, scale = 3)
	public Double getPrivilege() {
		return this.privilege;
	}

	public void setPrivilege(Double privilege) {
		this.privilege = privilege;
	}

	@Column(name = "status", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "food_count", unique = false, nullable = true, insertable = true, updatable = true)
	public Integer getFoodCount() {
		return this.foodCount;
	}

	public void setFoodCount(Integer foodCount) {
		this.foodCount = foodCount;
	}

	@Column(name = "real_food_count", unique = false, nullable = true, insertable = true, updatable = true)
	public Integer getRealFoodCount() {
		return this.realFoodCount;
	}

	public void setRealFoodCount(Integer realFoodCount) {
		this.realFoodCount = realFoodCount;
	}

	@Column(name = "small_pic_id", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getSmallPicId() {
		return this.smallPicId;
	}

	public void setSmallPicId(String smallPicId) {
		this.smallPicId = smallPicId;
	}

	@Column(name = "orgin_pic_id", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getOrginPicId() {
		return this.orginPicId;
	}

	public void setOrginPicId(String orginPicId) {
		this.orginPicId = orginPicId;
	}

	@Column(name = "show_order", unique = false, nullable = true, insertable = true, updatable = true)
	public Integer getShowOrder() {
		return this.showOrder;
	}

	public void setShowOrder(Integer showOrder) {
		this.showOrder = showOrder;
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