package com.bean.breakfast.basic.dto;

import com.bean.breakfast.basic.model.TBfCoupon;
import com.bean.breakfast.basic.model.TBfUser;
import com.bean.breakfast.basic.model.TBfUserCourier;

/**
 * TBfFood entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class CouponCustomerDTO implements java.io.Serializable {
	private TBfCoupon coupon;
	private UserCustomerDTO customerDTO;
	private TBfUser sender;

	public TBfCoupon getCoupon() {
		return coupon;
	}

	public void setCoupon(TBfCoupon coupon) {
		this.coupon = coupon;
	}

	public UserCustomerDTO getCustomerDTO() {
		return customerDTO;
	}

	public void setCustomerDTO(UserCustomerDTO customerDTO) {
		this.customerDTO = customerDTO;
	}

	public TBfUser getSender() {
		return sender;
	}

	public void setSender(TBfUser sender) {
		this.sender = sender;
	}
}