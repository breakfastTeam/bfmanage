package com.bean.breakfast.basic.dao.impl;

import com.bean.breakfast.basic.dao.FoodDao;
import com.bean.breakfast.basic.model.TBfFood;
import com.bean.breakfast.constants.IConstants;
import com.bean.core.orm.dao.impl.BaseDaoImpl;
import com.bean.core.page.Page;
import com.bean.core.utils.IStringUtil;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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

	public Page<TBfFood> findFood(Page<TBfFood> page, TBfFood food) {
		List<String> params = new ArrayList<String>();
		String hql = "from TBfFood t where t.status=? ";
		params.add(IConstants.VALID);
		if(IStringUtil.isNotBlank(food.getFoodName())){
			hql = hql + " and t.foodName like ?";
			params.add("%"+food.getFoodName()+"%");
		}
//		hql = hql + " order by orderNum desc, createTime desc";
		return this.findByHql(page, hql, params);
	}
}