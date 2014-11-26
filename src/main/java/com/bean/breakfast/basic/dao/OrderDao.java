package com.bean.breakfast.basic.dao;

import com.bean.breakfast.basic.model.TBfFile;
import com.bean.breakfast.basic.model.TBfOrder;
import com.bean.core.orm.dao.BaseDao;

import java.util.List;

/**
 * Created by qingfeilee on 2014/11/24.
 */
public interface OrderDao extends BaseDao<TBfOrder, String> {
    public List<TBfOrder> getOrdersByUserId(String userId);
}
