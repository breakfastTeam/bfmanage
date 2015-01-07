package com.bean.breakfast.basic.service;
import com.bean.breakfast.basic.dto.CouponCustomerDTO;
import com.bean.breakfast.basic.model.TBfCoupon;
import com.bean.breakfast.basic.model.TBfFile;
import com.bean.breakfast.basic.model.TBfPhotoShow;
import com.bean.core.orm.service.BaseService;
import com.bean.core.page.Page;

public interface PhotoShowService extends BaseService<TBfPhotoShow, String> {
    public void saveOrUpdate(TBfPhotoShow photoShow, TBfFile bigPic, TBfFile smallPic);
}
