package com.bean.breakfast.basic.dao.impl;

import com.bean.breakfast.basic.dao.OrderDetailDao;
import com.bean.breakfast.basic.dao.SetFoodsDao;
import com.bean.breakfast.basic.model.TBfOrderDetail;
import com.bean.breakfast.basic.model.TBfSetFoods;
import com.bean.core.orm.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qingfeilee on 2014/11/24.
 */
@Repository("setFoodsDao")
public class SetFoodsDaoImpl extends BaseDaoImpl<TBfSetFoods,String> implements SetFoodsDao {
    @Override
    public TBfSetFoods getSetFoodsByFoodIdAndSetMealId(String foodId, String setMealId) {
        List<String> params = new ArrayList<String>();
        String hql = "from TBfSetFoods t where t.foodId=? and t.setMealId=?";
        List<TBfSetFoods> setFoodses = this.find(hql, foodId, setMealId);
        if(setFoodses != null && setFoodses.size()>0){
            return setFoodses.get(0);
        }else{
            return null;
        }
    }
}
