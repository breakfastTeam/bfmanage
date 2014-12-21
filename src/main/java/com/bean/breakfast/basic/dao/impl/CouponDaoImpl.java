package com.bean.breakfast.basic.dao.impl;

import com.bean.breakfast.basic.dao.CouponDao;
import com.bean.breakfast.basic.dao.ExpressDao;
import com.bean.breakfast.basic.model.TBfCoupon;
import com.bean.breakfast.basic.model.TBfExpress;
import com.bean.breakfast.constants.IConstants;
import com.bean.core.orm.dao.impl.BaseDaoImpl;
import com.bean.core.page.Page;
import com.bean.core.utils.IStringUtil;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qingfeilee on 2014/11/24.
 */
@Repository("couponDao")
public class CouponDaoImpl extends BaseDaoImpl<TBfCoupon,String> implements CouponDao {

    @Override
    public Page<TBfCoupon> findCoupon(Page<TBfCoupon> page, TBfCoupon coupon) {
        List<Object> params = new ArrayList<Object>();
        String hql = "from TBfCoupon t where t.status<>?";
        params.add(IConstants.DISCARD);

        hql = hql + " order by createTime desc";
        return this.findByHql(page, hql, params);
    }

    @Override
    public List<TBfCoupon> findCoupon(TBfCoupon coupon) {
        List<Object> params = new ArrayList<Object>();
        String hql = "from TBfCoupon t where t.status<>?";
        params.add(IConstants.DISCARD);

        hql = hql + " order by createTime desc";
        return this.find(hql, params);
    }
}
