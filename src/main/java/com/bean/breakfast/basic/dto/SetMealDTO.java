package com.bean.breakfast.basic.dto;

import com.bean.breakfast.basic.model.*;
import org.apache.commons.fileupload.util.LimitedInputStream;

import java.util.List;

/**
 * TBfFood entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class SetMealDTO implements java.io.Serializable {
	private String smallPicId;
	private String smallPicPath;
	private String orginPicId;
	private String orginPicPath;
	private TBfSetMeal setMeal;
	private List<TBfFood> foods;

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

	public TBfSetMeal getSetMeal() {
		return setMeal;
	}

	public void setSetMeal(TBfSetMeal setMeal) {
		this.setMeal = setMeal;
	}

	public List<TBfFood> getFoods() {
		return foods;
	}

	public void setFoods(List<TBfFood> foods) {
		this.foods = foods;
	}
}