package com.bean.breakfast.basic.service;
import com.bean.breakfast.basic.dto.CouponCustomerDTO;
import com.bean.breakfast.basic.model.TBfCoupon;
import com.bean.breakfast.basic.model.TBfExpress;
import com.bean.core.orm.service.BaseService;
import com.bean.core.page.Page;

import java.util.List;

public interface CouponService extends BaseService<TBfCoupon, String> {
    public TBfCoupon getCoupon(String couponId);
    public Page<CouponCustomerDTO> findCoupon(Page<TBfCoupon> couponPage, TBfCoupon coupon);
}
