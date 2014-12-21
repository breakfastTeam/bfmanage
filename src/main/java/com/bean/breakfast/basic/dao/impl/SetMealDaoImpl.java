package com.bean.breakfast.basic.dao.impl;

import com.bean.breakfast.basic.dao.FoodDao;
import com.bean.breakfast.basic.dao.SetMealDao;
import com.bean.breakfast.basic.model.TBfFood;
import com.bean.breakfast.basic.model.TBfSetMeal;
import com.bean.breakfast.constants.IConstants;
import com.bean.core.orm.dao.impl.BaseDaoImpl;
import com.bean.core.page.Page;
import com.bean.core.utils.IDateUtil;
import com.bean.core.utils.IStringUtil;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("setMealDao")
public class SetMealDaoImpl extends BaseDaoImpl<TBfSetMeal,String>  implements SetMealDao {
    /**
     * 获取套餐的主要信息
     * */
    @Override
    public Page<TBfSetMeal> findSetMeal(Page<TBfSetMeal> page, TBfSetMeal setMeal) {
        List<Object> params = new ArrayList<Object>();
        String hql = "from TBfSetMeal t where t.status<>?";
        params.add(IConstants.DISCARD);
        if(IStringUtil.isNotBlank(setMeal.getSetName())){
            hql = hql + " and t.setName like ?";
            params.add("%"+setMeal.getSetName()+"%");
        }
        if(setMeal.getSaleTime() != null){
            hql = hql + " and t.saleTime>=?";
            params.add(IDateUtil.getCurrentTimeDate());
        }
        hql = hql + " order by showOrder desc, createTime desc";
        return this.findByHql(page, hql, params);
    }
    /**
     * 获取套餐的主要信息
     * */
    @Override
    public List<TBfSetMeal> findSetMeal(TBfSetMeal setMeal) {
        List<Object> params = new ArrayList<Object>();
        String hql = "from TBfSetMeal t where t.status<>?";
        params.add(IConstants.DISCARD);
        if(IStringUtil.isNotBlank(setMeal.getSetName())){
            hql = hql + " and t.setName like ?";
            params.add("%"+setMeal.getSetName()+"%");
        }

        hql = hql + " order by showOrder desc, createTime desc";
        return this.find(hql, params);
    }
    @Override
    public int getShowOrder() {
        String hql = "select count(t.setMealId) from TBfSetMeal t where t.status<>?";
        Query query = getSession().createQuery(hql);
        query.setParameter(0, IConstants.DISCARD);
        return ((Number) query.iterate().next()).intValue();
    }
}