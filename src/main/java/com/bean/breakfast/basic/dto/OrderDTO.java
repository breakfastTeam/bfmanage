package com.bean.breakfast.basic.dto;

import java.util.Date;
import java.util.List;

/**
 * TBfFood entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class OrderDTO implements java.io.Serializable {
	private String customerId;
	private String orderType;
	private String consigneeName;
	private String consigneeAddr;
	private String consigneePhone;
	private String preSendTime;
	private Date preSendDate;
	private double exccreaditCount;
	private double money;
	private String remark;
	private String createTime;
	private List<FoodDTO> foods;

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getConsigneeName() {
		return consigneeName;
	}

	public void setConsigneeName(String consigneeName) {
		this.consigneeName = consigneeName;
	}

	public String getConsigneeAddr() {
		return consigneeAddr;
	}

	public void setConsigneeAddr(String consigneeAddr) {
		this.consigneeAddr = consigneeAddr;
	}

	public String getConsigneePhone() {
		return consigneePhone;
	}

	public void setConsigneePhone(String consigneePhone) {
		this.consigneePhone = consigneePhone;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public List<FoodDTO> getFoods() {
		return foods;
	}

	public void setFoods(List<FoodDTO> foods) {
		this.foods = foods;
	}

	public double getExccreaditCount() {
		return exccreaditCount;
	}

	public void setExccreaditCount(double exccreaditCount) {
		this.exccreaditCount = exccreaditCount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getPreSendTime() {
		return preSendTime;
	}

	public void setPreSendTime(String preSendTime) {
		this.preSendTime = preSendTime;
	}

	public Date getPreSendDate() {
		return preSendDate;
	}

	public void setPreSendDate(Date preSendDate) {
		this.preSendDate = preSendDate;
	}
}