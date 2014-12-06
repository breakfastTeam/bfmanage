package com.bean.breakfast.basic.dao.impl;

import com.bean.breakfast.basic.dao.FoodDao;
import com.bean.breakfast.basic.model.TBfFood;
import com.bean.breakfast.constants.IConstants;
import com.bean.core.orm.dao.impl.BaseDaoImpl;
import com.bean.core.page.Page;
import com.bean.core.utils.IDateUtil;
import com.bean.core.utils.IStringUtil;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository("foodDao")
public class FoodDaoImpl extends BaseDaoImpl<TBfFood,String>  implements FoodDao {
	public int getOrderNum() {
		String hql = "select count(t.foodId) from TBfFood t where t.status=? or t.status=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, IConstants.VALID);
		query.setParameter(1, IConstants.FORBIDDEN);
		return ((Number)query.iterate().next()).intValue();
	}

	public List<TBfFood> findFood(TBfFood food) {
		List<Object> params = new ArrayList<Object>();
		String hql = "from TBfFood t where t.status=?";
		params.add(IConstants.VALID);
		if(IStringUtil.isNotBlank(food.getFoodName())){
			hql = hql + " and t.foodName like ?";
			params.add("%"+food.getFoodName()+"%");
		}
		hql = hql + " order by showOrder asc, createTime desc";
		return this.find(hql, params);
	}
	public Page<TBfFood> findFood(Page<TBfFood> page, TBfFood food) {
		List<Object> params = new ArrayList<Object>();
		String hql = "from TBfFood t where t.status=?";
		params.add(IConstants.VALID);
		if(IStringUtil.isNotBlank(food.getFoodName())){
			hql = hql + " and t.foodName like ?";
			params.add("%"+food.getFoodName()+"%");
		}
		hql = hql + " order by showOrder asc, createTime desc";
		return this.findByHql(page, hql, params);
	}
	public Page<TBfFood> findFoodWithSaleTime(Page<TBfFood> page, TBfFood food) {
		List<Object> params = new ArrayList<Object>();
		String hql = "from TBfFood t where t.status=? and t.saleTime>=?";
		params.add(IConstants.VALID);
		params.add(IDateUtil.getCurrentTimeDate());
		if(IStringUtil.isNotBlank(food.getFoodName())){
			hql = hql + " and t.foodName like ?";
			params.add("%"+food.getFoodName()+"%");
		}
		hql = hql + " order by showOrder asc, createTime desc";
		return this.findByHql(page, hql, params);
	}
	@Override
	public void minusFoodCount(String foodId, int foodCount, int realFoodCount) {
		String hql = "update TBfFood t set t.foodCount = t.foodCount -"+ foodCount +", t.realFoodCount = t.realFoodCount - "+realFoodCount +" where t.foodId='"+foodId+"'";
		Query query = getSession().createQuery(hql);
		query.executeUpdate();
	}
}