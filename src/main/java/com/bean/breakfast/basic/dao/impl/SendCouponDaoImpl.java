package com.bean.breakfast.basic.dao.impl;

import com.bean.breakfast.basic.dao.CouponDao;
import com.bean.breakfast.basic.dao.SendCouponDao;
import com.bean.breakfast.basic.model.TBfCoupon;
import com.bean.breakfast.basic.model.TBfSendCoupon;
import com.bean.breakfast.constants.IConstants;
import com.bean.core.orm.dao.impl.BaseDaoImpl;
import com.bean.core.page.Page;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qingfeilee on 2014/11/24.
 */
@Repository("sendCouponDao")
public class SendCouponDaoImpl extends BaseDaoImpl<TBfSendCoupon,String> implements SendCouponDao {

    @Override
    public Page<TBfSendCoupon> findSendCoupon(Page<TBfSendCoupon> page, TBfSendCoupon sendCoupon) {
        List<Object> params = new ArrayList<Object>();
        String hql = "from TBfSendCoupon t where t.status<>?";
        params.add(IConstants.DISCARD);

        hql = hql + " order by createTime desc";
        return this.findByHql(page, hql, params);
    }

    @Override
    public List<TBfSendCoupon> findSendCoupon(TBfSendCoupon sendCoupon) {
        List<Object> params = new ArrayList<Object>();
        String hql = "from TBfSendCoupon t where t.status<>?";
        params.add(IConstants.DISCARD);

        hql = hql + " order by createTime desc";
        return this.find(hql, params);
    }
}
