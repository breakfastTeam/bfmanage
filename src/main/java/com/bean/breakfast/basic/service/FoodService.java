package com.bean.breakfast.basic.service;
import com.bean.breakfast.basic.dto.FoodDTO;
import com.bean.breakfast.basic.model.TBfFood;
import com.bean.core.orm.service.BaseService;
import com.bean.core.page.Page;

public interface FoodService extends BaseService<TBfFood, String> {
	public void saveOrUpdate(TBfFood food,String smallPicId, String smallPicPath,String orginPicId, String bigPicPath);
	public int getOrderNum();
	public Page<FoodDTO> findFood(Page<TBfFood> page, TBfFood food);
	public Page<FoodDTO> findFoodWithSaleTime(Page<TBfFood> page, TBfFood food);
	public FoodDTO getFoodDTO(String foodId);
	public TBfFood getFood(String foodId);
	public int getFoodCount(String foodId);

}
