package com.bean.breakfast.basic.service.impl;

import com.bean.breakfast.basic.dao.FileDao;
import com.bean.breakfast.basic.dao.FoodDao;
import com.bean.breakfast.basic.dao.OrderDao;
import com.bean.breakfast.basic.dao.SetMealDao;
import com.bean.breakfast.basic.dto.FoodDTO;
import com.bean.breakfast.basic.dto.SetMealDTO;
import com.bean.breakfast.basic.model.TBfFile;
import com.bean.breakfast.basic.model.TBfFood;
import com.bean.breakfast.basic.model.TBfSetMeal;
import com.bean.breakfast.basic.service.FoodService;
import com.bean.breakfast.basic.service.SetMealService;
import com.bean.breakfast.constants.IConstants;
import com.bean.core.orm.service.impl.BaseServiceImpl;
import com.bean.core.page.Page;
import com.bean.core.utils.IDateUtil;
import com.bean.core.utils.IStringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("setMealService")
@Transactional
public class SetMealServiceImpl extends BaseServiceImpl<TBfSetMeal,String> implements SetMealService {
	@Autowired
	private SetMealDao setMealDao;


	public SetMealDao getSetMealDao() {
		return setMealDao;
	}

	public void setOrderDao(SetMealDao setMealDao) {
		this.setMealDao = setMealDao;
		super.setBaseDao(setMealDao);
	}
	/**
	 * 获取套餐基础信息
	 * **/
	@Override
	public Page<TBfSetMeal> findSetMeal(Page<TBfSetMeal> setMealPage, TBfSetMeal setMeal) {
		return setMealDao.findSetMeal(setMealPage, setMeal);
	}

	@Override
	public SetMealDTO getSetMealDTO(String setMealId) {
		SetMealDTO setMealDTO = new SetMealDTO();
		try {
			TBfSetMeal setMeal = setMealDao.get(setMealId);
			if(setMeal != null){
				setMealDTO.setSetMeal(setMeal);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return setMealDTO;
	}

	@Override
	public int getShowOrder() {
		return setMealDao.getShowOrder();
	}

	@Override
	public TBfSetMeal getSetMeal(String setMealId) {
		try {
			return setMealDao.get(setMealId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void saveOrUpdate(SetMealDTO setMealDTO) {

	}
}
