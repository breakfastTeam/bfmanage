package com.bean.breakfast.basic.service.impl;

import com.bean.breakfast.basic.dao.*;
import com.bean.breakfast.basic.dto.FoodDTO;
import com.bean.breakfast.basic.dto.SetMealDTO;
import com.bean.breakfast.basic.model.TBfFile;
import com.bean.breakfast.basic.model.TBfFood;
import com.bean.breakfast.basic.model.TBfSetFoods;
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
import java.util.Date;
import java.util.List;

@Service("setMealService")
@Transactional
public class SetMealServiceImpl extends BaseServiceImpl<TBfSetMeal,String> implements SetMealService {
	@Autowired
	private SetMealDao setMealDao;

	@Autowired
	private SetFoodsDao setFoodsDao;

	@Autowired
	private FileDao fileDao;

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
			TBfFile smallPic = fileDao.get(setMeal.getSmallPicId());
			TBfFile originalPic = fileDao.get(setMeal.getOrginPicId());
			setMealDTO.setSmallPicId(smallPic.getFileId());
			setMealDTO.setSmallPicPath(smallPic.getFilePath());
			setMealDTO.setOrginPicId(originalPic.getFileId());
			setMealDTO.setOrginPicPath(originalPic.getFilePath());

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
		TBfFile smallPic, bigPic;
		if(IStringUtil.isNotBlank(setMealDTO.getSmallPicId())){
			try {
				smallPic = fileDao.get(setMealDTO.getSmallPicId());
			} catch (Exception e) {
				e.printStackTrace();
				smallPic = new TBfFile();
			}
		}else{
			smallPic = new TBfFile();
		}

		smallPic.setFilePath(setMealDTO.getSmallPicPath());

		if(IStringUtil.isNotBlank(setMealDTO.getOrginPicId())){
			try {
				bigPic = fileDao.get(setMealDTO.getOrginPicId());
			} catch (Exception e) {
				e.printStackTrace();
				bigPic = new TBfFile();
			}
		}else{
			bigPic = new TBfFile();
		}
		bigPic.setFilePath(setMealDTO.getOrginPicPath());

		fileDao.saveOrUpdate(smallPic);
		fileDao.saveOrUpdate(bigPic);

		TBfSetMeal setMeal = setMealDTO.getSetMeal();
		setMeal.setSmallPicId(smallPic.getFileId());
		setMeal.setOrginPicId(bigPic.getFileId());
		setMealDao.saveOrUpdate(setMeal);

		List<TBfFood> foods = setMealDTO.getFoods();
		String setMealId = setMeal.getSetMealId();
		for (int i = 0; i<foods.size(); i++){
			String foodId = foods.get(i).getFoodId();
			TBfSetFoods setFoods = setFoodsDao.getSetFoodsByFoodIdAndSetMealId(foodId, setMealId);
			if(setFoods == null){
				setFoods = new TBfSetFoods();
				setFoods.setSetMealId(setMealId);
				setFoods.setFoodId(foodId);
			}
			setFoodsDao.saveOrUpdate(setFoods);
		}


	}
	public void saveOrUpdate(TBfSetMeal setMeal){
		setMealDao.saveOrUpdate(setMeal);
	}

	public void updateSetMealStatus(){
		TBfSetMeal setMeal = new TBfSetMeal();
		List<TBfSetMeal> setMeals = setMealDao.findSetMeal(setMeal);
		Date now = new Date();
		for(TBfSetMeal s : setMeals) {
			if (IDateUtil.diffDays(s.getEndTime(), now)>=0 && IDateUtil.diffDays(s.getStartTime(), now)<= 0) {
				s.setStatus(IConstants.PUTAWAY);
			} else {
				s.setStatus(IConstants.SOLDOUT);
			}
			setMealDao.saveOrUpdate(s);
		}
	}
}
