package com.bean.breakfast.basic.dao.impl;

import com.bean.breakfast.basic.dao.ExpressDao;
import com.bean.breakfast.basic.dao.FileDao;
import com.bean.breakfast.basic.model.TBfExpress;
import com.bean.breakfast.basic.model.TBfFile;
import com.bean.breakfast.constants.IConstants;
import com.bean.core.orm.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qingfeilee on 2014/11/24.
 */
@Repository("expressDao")
public class ExpressDaoImpl extends BaseDaoImpl<TBfExpress,String> implements ExpressDao {
    @Override
    public List<TBfExpress> getExpressByCourierId(String courierId) {
        List<Object> params = new ArrayList<Object>();
        String hql = "from TBfExpress t where t.status=? and t.courierId=?";
        params.add(IConstants.ENABLE);
        params.add(courierId);
        hql = hql + " order by createTime desc";
        return this.find(hql, params);
    }

    @Override
    public TBfExpress getExpressByOrderId(String orderId) {
        List<Object> params = new ArrayList<Object>();
        String hql = "from TBfExpress t where t.status=? and t.orderId=?";
        params.add(IConstants.ENABLE);
        params.add(orderId);
        hql = hql + " order by createTime desc";
        List<TBfExpress> express = this.find(hql, params);
        if(express!= null && express.size()>0){
            return express.get(0);
        }else {
            return null;
        }
    }
}
