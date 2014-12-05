package com.bean.breakfast.basic.dto;

import com.bean.breakfast.basic.model.TBfFood;
import com.bean.breakfast.basic.model.TBfOrderDetail;

import java.util.Date;
import java.util.List;

/**
 * TBfFood entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class OrderDetailDTO implements java.io.Serializable {
	private TBfOrderDetail orderDetail;
	private TBfFood food;

	public TBfOrderDetail getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(TBfOrderDetail orderDetail) {
		this.orderDetail = orderDetail;
	}

	public TBfFood getFood() {
		return food;
	}

	public void setFood(TBfFood food) {
		this.food = food;
	}
}