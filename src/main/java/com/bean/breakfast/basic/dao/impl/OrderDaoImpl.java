package com.bean.breakfast.basic.dao.impl;

import com.bean.breakfast.basic.dao.OrderDao;
import com.bean.breakfast.basic.model.TBfFile;
import com.bean.breakfast.basic.model.TBfOrder;
import com.bean.breakfast.basic.model.TBfUser;
import com.bean.breakfast.constants.IConstants;
import com.bean.core.orm.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qingfeilee on 2014/11/24.
 */
@Repository("orderDao")
public class OrderDaoImpl extends BaseDaoImpl<TBfOrder,String> implements OrderDao {
    @Override
    public List<TBfOrder> getOrdersByUserId(String userId) {
        List<String> params = new ArrayList<String>();
        String hql = "from TBfOrder t where t.status=? and t.customerId=?";
        List<TBfOrder> orders = this.find(hql, IConstants.VALID, userId);
        return orders;
    }
}
