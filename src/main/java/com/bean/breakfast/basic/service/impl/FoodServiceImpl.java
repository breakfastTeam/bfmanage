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

	public String save(TBfFood food, String smallPicPath, String bigPicPath){
		TBfFile smallPic = new TBfFile();
		smallPic.setFilePath(smallPicPath);

		TBfFile bigPic = new TBfFile();
		bigPic.setFilePath(bigPicPath);

		String smallPicId = fileDao.save(smallPic);
		String bigPicId = fileDao.save(bigPic);

		food.setSmallPicId(smallPicId);
		food.setOrginPicId(bigPicId);
		food.setStatus(IConstants.VALID);
		return foodDao.save(food);
	}
	public Page<FoodDTO> findFood(Page<TBfFood> page, TBfFood food) {
		Page<FoodDTO> pageDTO = new Page<FoodDTO>(page.getPageSize());
		page = foodDao.findFood(page, food);
		List<FoodDTO> foodDTOList = new ArrayList<FoodDTO>();
		for (TBfFood f : page.getResult()){
			FoodDTO foodDTO = new FoodDTO();
			foodDTO.setStatus(f.getStatus());
			foodDTO.setOrginPicId(f.getOrginPicId());
			foodDTO.setSmallPicId(f.getSmallPicId());
			foodDTO.setBriefIntro(f.getBriefIntro());
			foodDTO.setExchangeCount(f.getExchangeCount());
			foodDTO.setFoodId(f.getFoodId());
			foodDTO.setFoodName(f.getFoodName());
			foodDTO.setFoodCount(f.getFoodCount());
			foodDTO.setUnit(f.getUnit());
			foodDTO.setSupportExchange(true);
			foodDTO.setSupportExchange(true);
			foodDTO.setPrice(f.getPrice());
			String saleTime = IDateUtil.dateToString(f.getSaleTime());
			foodDTO.setSaleTime(saleTime.substring(5, saleTime.length()));
			try {
				foodDTO.setOrginPicPath(fileDao.get(f.getOrginPicId()).getFilePath());
				foodDTO.setSmallPicPath(fileDao.get(f.getSmallPicId()).getFilePath());
			} catch (Exception e) {
				e.printStackTrace();
			}
			foodDTOList.add(foodDTO);
		}
		pageDTO.setPageNo(page.getPageNo());
		pageDTO.setPageSize(page.getPageSize());
		pageDTO.setResult(foodDTOList);
		pageDTO.setTotalCount(page.getTotalCount());
		return pageDTO;
	}

	public int getOrderNum() {
		return foodDao.getOrderNum();
	}

}
