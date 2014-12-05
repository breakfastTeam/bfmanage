package com.bean.breakfast.basic.model;

import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import javax.persistence.*;

/**
 * TBfFood entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_bf_food", catalog = "breakfast", uniqueConstraints = {})
public class TBfFood implements java.io.Serializable {

	// Fields

	private String foodId;
	private String foodName;
	private Double cost;
	private Double price;
	private String unit;
	private String briefIntro;
	private String status;
	private Integer foodCount;
	private Integer realFoodCount;
	private String smallPicId;
	private String orginPicId;
	private Byte supportSnapUp;
	private Byte supportExchange;
	private Integer exchangeCount;
	private Integer showOrder;
	private Date saleTime;
	private Date createTime;
	private String createBy;
	private Date lastModifyTime;
	private String lastModifyBy;
	private Long optTime;

	// Constructors

	/** default constructor */
	public TBfFood() {
	}

	/** minimal constructor */
	public TBfFood(String foodId) {
		this.foodId = foodId;
	}

	/** full constructor */
	public TBfFood(String foodId, String foodName, Double cost, Double price,
			String unit, String briefIntro, String status, Integer foodCount,
			Integer realFoodCount, String smallPicId, String orginPicId,
			Byte supportSnapUp, Byte supportExchange, Integer exchangeCount,
			Integer showOrder, Date createTime, String createBy,
			Date lastModifyTime, String lastModifyBy, Long optTime) {
		this.foodId = foodId;
		this.foodName = foodName;
		this.cost = cost;
		this.price = price;
		this.unit = unit;
		this.briefIntro = briefIntro;
		this.status = status;
		this.foodCount = foodCount;
		this.realFoodCount = realFoodCount;
		this.smallPicId = smallPicId;
		this.orginPicId = orginPicId;
		this.supportSnapUp = supportSnapUp;
		this.supportExchange = supportExchange;
		this.exchangeCount = exchangeCount;
		this.showOrder = showOrder;
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
	@Column(name = "food_id", unique = true, nullable = false, insertable = true, updatable = true, length = 32)
	public String getFoodId() {
		return this.foodId;
	}

	public void setFoodId(String foodId) {
		this.foodId = foodId;
	}

	@Column(name = "food_name", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getFoodName() {
		return this.foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	@Column(name = "cost", unique = false, nullable = true, insertable = true, updatable = true, precision = 5, scale = 3)
	public Double getCost() {
		return this.cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	@Column(name = "price", unique = false, nullable = true, insertable = true, updatable = true, precision = 5, scale = 3)
	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Column(name = "unit", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Column(name = "brief_intro", unique = false, nullable = true, insertable = true, updatable = true)
	public String getBriefIntro() {
		return this.briefIntro;
	}

	public void setBriefIntro(String briefIntro) {
		this.briefIntro = briefIntro;
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

	@Column(name = "support_snap_up", unique = false, nullable = true, insertable = true, updatable = true)
	public Byte getSupportSnapUp() {
		return this.supportSnapUp;
	}

	public void setSupportSnapUp(Byte supportSnapUp) {
		this.supportSnapUp = supportSnapUp;
	}

	@Column(name = "support_exchange", unique = false, nullable = true, insertable = true, updatable = true)
	public Byte getSupportExchange() {
		return this.supportExchange;
	}

	public void setSupportExchange(Byte supportExchange) {
		this.supportExchange = supportExchange;
	}

	@Column(name = "exchange_count", unique = false, nullable = true, insertable = true, updatable = true)
	public Integer getExchangeCount() {
		return this.exchangeCount;
	}

	public void setExchangeCount(Integer exchangeCount) {
		this.exchangeCount = exchangeCount;
	}

	@Column(name = "show_order", unique = false, nullable = true, insertable = true, updatable = true)
	public Integer getShowOrder() {
		return this.showOrder;
	}

	public void setShowOrder(Integer showOrder) {
		this.showOrder = showOrder;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "sale_time", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public Date getSaleTime() {
		return this.saleTime;
	}

	public void setSaleTime(Date saleTime) {
		this.saleTime = saleTime;
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