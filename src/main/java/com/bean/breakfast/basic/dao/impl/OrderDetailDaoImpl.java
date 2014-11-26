package com.bean.breakfast.basic.dao.impl;

import com.bean.breakfast.basic.dao.OrderDetailDao;
import com.bean.breakfast.basic.model.TBfOrder;
import com.bean.breakfast.basic.model.TBfOrderDetail;
import com.bean.breakfast.constants.IConstants;
import com.bean.core.orm.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qingfeilee on 2014/11/24.
 */
@Repository("orderDetailDao")
public class OrderDetailDaoImpl extends BaseDaoImpl<TBfOrderDetail,String> implements OrderDetailDao {
    @Override
    public List<TBfOrderDetail> getOrderDetailByOrderId(String orderId) {
        List<String> params = new ArrayList<String>();
        String hql = "from TBfOrderDetail t where t.orderId=?";
        List<TBfOrderDetail> orders = this.find(hql, orderId);
        return orders;
    }
}
