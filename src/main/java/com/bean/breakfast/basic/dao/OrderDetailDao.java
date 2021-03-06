package com.bean.breakfast.basic.dao;

import com.bean.breakfast.basic.model.TBfOrder;
import com.bean.breakfast.basic.model.TBfOrderDetail;
import com.bean.core.orm.dao.BaseDao;

import java.util.List;

/**
 * Created by qingfeilee on 2014/11/24.
 */
public interface OrderDetailDao extends BaseDao<TBfOrderDetail, String> {
    public List<TBfOrderDetail> getOrderDetailByOrderId(String orderId);
}
