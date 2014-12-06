package com.bean.breakfast.basic.service;
import com.bean.breakfast.basic.dto.FoodDTO;
import com.bean.breakfast.basic.dto.SetMealDTO;
import com.bean.breakfast.basic.model.TBfFood;
import com.bean.breakfast.basic.model.TBfSetMeal;
import com.bean.core.orm.service.BaseService;
import com.bean.core.page.Page;

public interface SetMealService extends BaseService<TBfSetMeal, String> {
    public Page<TBfSetMeal> findSetMeal(Page<TBfSetMeal> setMealPage, TBfSetMeal setMeal);
    public SetMealDTO getSetMealDTO(String setMealId);
    public int getShowOrder();
    public TBfSetMeal getSetMeal(String setMealId);
    public void saveOrUpdate(SetMealDTO setMealDTO);
}
