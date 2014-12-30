package com.bean.breakfast.basic.service;
import com.bean.breakfast.basic.dto.CouponCustomerDTO;
import com.bean.breakfast.basic.dto.SendCouponCustomerDTO;
import com.bean.breakfast.basic.model.TBfCoupon;
import com.bean.breakfast.basic.model.TBfSendCoupon;
import com.bean.core.orm.service.BaseService;
import com.bean.core.page.Page;

public interface SendCouponService extends BaseService<TBfSendCoupon, String> {
    public Page<SendCouponCustomerDTO> findSendCoupon(Page<TBfSendCoupon> sendCouponPage, TBfSendCoupon sendCoupon);
    public TBfSendCoupon getSendCoupon(String sendCouponId);
    public void saveOrUpdate(TBfSendCoupon sendCoupon);
}
