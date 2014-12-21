package com.bean.breakfast.basic.dao;

import com.bean.breakfast.basic.model.TBfCoupon;
import com.bean.breakfast.basic.model.TBfExpress;
import com.bean.core.orm.dao.BaseDao;
import com.bean.core.page.Page;

import java.util.List;

/**
 * Created by qingfeilee on 2014/11/24.
 */
public interface CouponDao extends BaseDao<TBfCoupon, String> {
    public Page<TBfCoupon> findCoupon(Page<TBfCoupon> page, TBfCoupon coupon);
}
