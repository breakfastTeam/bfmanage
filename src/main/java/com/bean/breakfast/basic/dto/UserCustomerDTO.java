package com.bean.breakfast.basic.dto;

import com.bean.breakfast.basic.model.TBfUser;
import com.bean.breakfast.basic.model.TBfUserCourier;
import com.bean.breakfast.basic.model.TBfUserCustomer;

/**
 * TBfFood entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class UserCustomerDTO implements java.io.Serializable {
	private TBfUser user;
	private TBfUserCustomer customer;

	public TBfUser getUser() {
		return user;
	}

	public void setUser(TBfUser user) {
		this.user = user;
	}

	public TBfUserCustomer getCustomer() {
		return customer;
	}

	public void setCustomer(TBfUserCustomer customer) {
		this.customer = customer;
	}
}