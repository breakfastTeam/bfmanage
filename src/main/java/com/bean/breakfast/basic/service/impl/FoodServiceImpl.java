package com.bean.breakfast.basic.service.impl;

import com.bean.breakfast.basic.dao.FileDao;
import com.bean.breakfast.basic.dao.FoodDao;
import com.bean.breakfast.basic.dto.FoodDTO;
import com.bean.breakfast.basic.model.TBfFile;
import com.bean.breakfast.basic.model.TBfFood;
import com.bean.breakfast.basic.service.FoodService;
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

@Service("foodService")
@Transactional
public class FoodServiceImpl extends BaseServiceImpl<TBfFood,String> implements FoodService{
	@Autowired
	private FoodDao foodDao;

	@Autowired
	private FileDao fileDao;

	public FoodDao getFoodDao() {
		return foodDao;
	}

	public void setFoodDao(FoodDao foodDao) {
		this.foodDao = foodDao;
		super.setBaseDao(foodDao);
	}
	public TBfFood getFood(String foodId){
		try {
			return this.foodDao.get(foodId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public List<TBfFood> findFood(TBfFood food){
		List<TBfFood> foods = new ArrayList<TBfFood>();
		return foodDao.findFood(food);
	}
	public void saveOrUpdate(TBfFood food){
		foodDao.saveOrUpdate(food);
	}
	public void saveOrUpdate(TBfFood food,String smallPicId, String smallPicPath,String orginPicId, String bigPicPath){
		TBfFile smallPic, bigPic;
		if(IStringUtil.isNotBlank(smallPicId)){
			try {
				smallPic = fileDao.get(smallPicId);
			} catch (Exception e) {
				e.printStackTrace();
				smallPic = new TBfFile();
			}
		}else{
			smallPic = new TBfFile();
		}

		smallPic.setFilePath(smallPicPath);

		if(IStringUtil.isNotBlank(orginPicId)){
			try {
				bigPic = fileDao.get(orginPicId);
			} catch (Exception e) {
				e.printStackTrace();
				bigPic = new TBfFile();
			}
		}else{
			bigPic = new TBfFile();
		}
		bigPic.setFilePath(bigPicPath);

		fileDao.saveOrUpdate(smallPic);
		fileDao.saveOrUpdate(bigPic);

		food.setSmallPicId(smallPic.getFileId());
		food.setOrginPicId(bigPic.getFileId());
		food.setStatus(IConstants.PUTAWAY);
		foodDao.saveOrUpdate(food);
	}
	public Page<FoodDTO> findFood(Page<TBfFood> page, TBfFood food) {
		Page<FoodDTO> pageDTO = new Page<FoodDTO>(page.getPageSize());
		page = foodDao.findFood(page, food);
		List<FoodDTO> foodDTOList = new ArrayList<FoodDTO>();
		for (TBfFood f : page.getResult()){
			FoodDTO foodDTO = generateFoodDTO(f);

			foodDTOList.add(foodDTO);
		}
		pageDTO.setPageNo(page.getPageNo());
		pageDTO.setPageSize(page.getPageSize());
		pageDTO.setResult(foodDTOList);
		pageDTO.setTotalCount(page.getTotalCount());
		return pageDTO;
	}
	public Page<FoodDTO> findFoodWithSaleTime(Page<TBfFood> page, TBfFood food) {
		Page<FoodDTO> pageDTO = new Page<FoodDTO>(page.getPageSize());
		page = foodDao.findFoodWithSaleTime(page, food);
		List<FoodDTO> foodDTOList = new ArrayList<FoodDTO>();
		for (TBfFood f : page.getResult()){
			FoodDTO foodDTO = generateFoodDTO(f);

			foodDTOList.add(foodDTO);
		}
		pageDTO.setPageNo(page.getPageNo());
		pageDTO.setPageSize(page.getPageSize());
		pageDTO.setResult(foodDTOList);
		pageDTO.setTotalCount(page.getTotalCount());
		return pageDTO;
	}
	@Override
	public FoodDTO getFoodDTO(String foodId) {
		try {
			TBfFood food = foodDao.get(foodId);
			FoodDTO foodDTO = generateFoodDTO(food);
			return foodDTO;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public int getOrderNum() {
		return foodDao.getOrderNum();
	}
	public int getFoodCount(String foodId){
		try {
			TBfFood food = this.foodDao.get(foodId);
			return food.getFoodCount();
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	public FoodDTO generateFoodDTO(TBfFood food){
		FoodDTO foodDTO = new FoodDTO();
		foodDTO.setCost(food.getCost());
		foodDTO.setStatus(food.getStatus());
		foodDTO.setOrginPicId(food.getOrginPicId());
		foodDTO.setSmallPicId(food.getSmallPicId());
		foodDTO.setBriefIntro(food.getBriefIntro());
		foodDTO.setExchangeCount(food.getExchangeCount());
		foodDTO.setFoodId(food.getFoodId());
		foodDTO.setFoodName(food.getFoodName());
		foodDTO.setFoodCount(food.getFoodCount());
		foodDTO.setRealFoodCount(food.getRealFoodCount());
		foodDTO.setShowOrder(food.getShowOrder());
		foodDTO.setUnit(food.getUnit());
//		foodDTO.setSupportExchange(true);
//		foodDTO.setSupportExchange(true);
		foodDTO.setPrice(food.getPrice());
		String saleTime = IDateUtil.dateToString(food.getSaleTime());
		foodDTO.setSaleTime(saleTime);

		try {
			foodDTO.setOrginPicPath(fileDao.get(food.getOrginPicId()).getFilePath());
			foodDTO.setSmallPicPath(fileDao.get(food.getSmallPicId()).getFilePath());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return foodDTO;
	}
	public void updateFoodStatus(){
		System.out.println("-----------------");
	}
}
