package com.bean.breakfast.basic.dto;

import com.bean.breakfast.basic.model.TBfFood;
import com.bean.breakfast.basic.model.TBfSetMeal;
import com.bean.breakfast.basic.model.TBfUser;
import com.bean.breakfast.basic.model.TBfUserCourier;

import java.util.List;

/**
 * TBfFood entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class UserCourierDTO implements java.io.Serializable {
	private TBfUser user;
	private TBfUserCourier courier;

	public TBfUser getUser() {
		return user;
	}

	public void setUser(TBfUser user) {
		this.user = user;
	}

	public TBfUserCourier getCourier() {
		return courier;
	}

	public void setCourier(TBfUserCourier courier) {
		this.courier = courier;
	}
}