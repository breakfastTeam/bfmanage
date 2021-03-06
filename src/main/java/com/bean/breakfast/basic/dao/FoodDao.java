package com.bean.breakfast.basic.dao;


import com.bean.breakfast.basic.model.TBfFood;
import com.bean.breakfast.basic.model.TBfSetMeal;
import com.bean.core.orm.dao.BaseDao;
import com.bean.core.page.Page;

import java.util.List;

public interface FoodDao extends BaseDao<TBfFood, String> {
	public int getOrderNum();
	public List<TBfFood> findFood(TBfFood food);
	public Page<TBfFood> findFood(Page<TBfFood> page, TBfFood food);
	public Page<TBfFood> findFoodWithSaleTime(Page<TBfFood> page, TBfFood food);
	public void minusFoodCount(String foodId, int foodCount, int realFoodCount);
	public TBfFood getFood(String foodId);
}