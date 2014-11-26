package com.bean.breakfast.basic.service;
import com.bean.breakfast.basic.dto.FoodDTO;
import com.bean.breakfast.basic.model.TBfFood;
import com.bean.core.orm.service.BaseService;
import com.bean.core.page.Page;

public interface FoodService extends BaseService<TBfFood, String> {
	public String save(TBfFood food, String smallPicPath, String bigPicPath);
	public int getOrderNum();
	public Page<FoodDTO> findFood(Page<TBfFood> page, TBfFood food);
}
