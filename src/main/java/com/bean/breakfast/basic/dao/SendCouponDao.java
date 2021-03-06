package com.bean.breakfast.basic.dao;

import com.bean.breakfast.basic.model.TBfCoupon;
import com.bean.breakfast.basic.model.TBfSendCoupon;
import com.bean.core.orm.dao.BaseDao;
import com.bean.core.page.Page;

import java.util.List;

/**
 * Created by qingfeilee on 2014/11/24.
 */
public interface SendCouponDao extends BaseDao<TBfSendCoupon, String> {
    public Page<TBfSendCoupon> findSendCoupon(Page<TBfSendCoupon> page, TBfSendCoupon sendCoupon);
    public List<TBfSendCoupon> findSendCoupon(TBfSendCoupon sendCoupon);
}
