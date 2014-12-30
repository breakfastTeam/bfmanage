package com.bean.breakfast.basic.dto;

import com.bean.breakfast.basic.model.TBfCoupon;
import com.bean.breakfast.basic.model.TBfSendCoupon;
import com.bean.breakfast.basic.model.TBfUser;

/**
 * TBfFood entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class SendCouponCustomerDTO implements java.io.Serializable {
	private TBfSendCoupon sendCoupon;
	private UserCustomerDTO customerDTO;
	private TBfUser sender;

	public TBfSendCoupon getSendCoupon() {
		return sendCoupon;
	}

	public void setSendCoupon(TBfSendCoupon sendCoupon) {
		this.sendCoupon = sendCoupon;
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