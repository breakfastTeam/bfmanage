package com.bean.breakfast.basic.dto;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * TBfFood entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class FoodDTO implements java.io.Serializable {
	private String foodId;
	private String foodName;
	private Double cost;
	private Double price;
	private String unit;
	private String briefIntro;
	private String status;
	private Integer foodCount;
	private Integer realFoodCount;
	private Integer foodNum;//订单中单品数量
	private String smallPicId;
	private String smallPicPath;
	private String orginPicId;
	private String orginPicPath;
	private boolean supportSnapUp;
	private boolean supportExchange;
	private Integer exchangeCount;
	private String saleTime;
	private Integer showOrder;

	public String getFoodId() {
		return foodId;
	}

	public void setFoodId(String foodId) {
		this.foodId = foodId;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getBriefIntro() {
		return briefIntro;
	}

	public void setBriefIntro(String briefIntro) {
		this.briefIntro = briefIntro;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getFoodCount() {
		return foodCount;
	}

	public void setFoodCount(Integer foodCount) {
		this.foodCount = foodCount;
	}

	public String getSmallPicId() {
		return smallPicId;
	}

	public void setSmallPicId(String smallPicId) {
		this.smallPicId = smallPicId;
	}

	public String getSmallPicPath() {
		return smallPicPath;
	}

	public void setSmallPicPath(String smallPicPath) {
		this.smallPicPath = smallPicPath;
	}

	public String getOrginPicId() {
		return orginPicId;
	}

	public void setOrginPicId(String orginPicId) {
		this.orginPicId = orginPicId;
	}

	public String getOrginPicPath() {
		return orginPicPath;
	}

	public void setOrginPicPath(String orginPicPath) {
		this.orginPicPath = orginPicPath;
	}

	public boolean isSupportSnapUp() {
		return supportSnapUp;
	}

	public void setSupportSnapUp(boolean supportSnapUp) {
		this.supportSnapUp = supportSnapUp;
	}

	public boolean isSupportExchange() {
		return supportExchange;
	}

	public void setSupportExchange(boolean supportExchange) {
		this.supportExchange = supportExchange;
	}

	public Integer getExchangeCount() {
		return exchangeCount;
	}

	public void setExchangeCount(Integer exchangeCount) {
		this.exchangeCount = exchangeCount;
	}

	public Integer getRealFoodCount() {
		return realFoodCount;
	}

	public void setRealFoodCount(Integer realFoodCount) {
		this.realFoodCount = realFoodCount;
	}

	public String getSaleTime() {
		return saleTime;
	}

	public void setSaleTime(String saleTime) {
		this.saleTime = saleTime;
	}

	public Integer getFoodNum() {
		return foodNum;
	}

	public void setFoodNum(Integer foodNum) {
		this.foodNum = foodNum;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public Integer getShowOrder() {
		return showOrder;
	}

	public void setShowOrder(Integer showOrder) {
		this.showOrder = showOrder;
	}
}