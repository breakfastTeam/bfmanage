package com.bean.breakfast.basic.dao;

import com.bean.breakfast.basic.model.TBfExpress;
import com.bean.breakfast.basic.model.TBfFile;
import com.bean.core.orm.dao.BaseDao;

import java.util.List;

/**
 * Created by qingfeilee on 2014/11/24.
 */
public interface ExpressDao extends BaseDao<TBfExpress, String> {
    public List<TBfExpress> getExpressByCourierId(String courierId);
    public TBfExpress getExpressByOrderId(String orderId);
}
