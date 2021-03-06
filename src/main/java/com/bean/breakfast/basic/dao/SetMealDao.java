package com.bean.breakfast.basic.dao;


import com.bean.breakfast.basic.model.TBfFood;
import com.bean.breakfast.basic.model.TBfSetMeal;
import com.bean.core.orm.dao.BaseDao;
import com.bean.core.page.Page;

import java.util.List;

public interface SetMealDao extends BaseDao<TBfSetMeal, String> {
    public Page<TBfSetMeal> findSetMeal(Page<TBfSetMeal> page, TBfSetMeal setMeal);
    public List<TBfSetMeal> findSetMeal(TBfSetMeal setMeal);
    public int getShowOrder();
    public TBfSetMeal getSetMeal(String setMealId);
}