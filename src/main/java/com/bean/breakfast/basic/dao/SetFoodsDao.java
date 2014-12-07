package com.bean.breakfast.basic.dao;

import com.bean.breakfast.basic.model.TBfOrderDetail;
import com.bean.breakfast.basic.model.TBfSetFoods;
import com.bean.core.orm.dao.BaseDao;

import java.util.List;

/**
 * Created by qingfeilee on 2014/11/24.
 */
public interface SetFoodsDao extends BaseDao<TBfSetFoods, String> {
    public TBfSetFoods getSetFoodsByFoodIdAndSetMealId(String foodId, String setMealId);
}
