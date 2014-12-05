package com.bean.breakfast.basic.dao.impl;

import com.bean.breakfast.basic.dao.OrderDao;
import com.bean.breakfast.basic.dto.OrderDTO;
import com.bean.breakfast.basic.model.TBfFile;
import com.bean.breakfast.basic.model.TBfOrder;
import com.bean.breakfast.basic.model.TBfUser;
import com.bean.breakfast.constants.IConstants;
import com.bean.core.orm.dao.impl.BaseDaoImpl;
import com.bean.core.page.Page;
import com.bean.core.utils.IStringUtil;
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
        String hql = "from TBfOrder t where t.status=? and t.customerId=? order by createTime desc";
        List<TBfOrder> orders = this.find(hql, IConstants.VALID, userId);
        return orders;
    }
    @Override
    public TBfOrder getLatestOrderByUserId(String userId) {
        List<String> params = new ArrayList<String>();
        String hql = "from TBfOrder t where t.status=? and t.customerId=? order by createTime desc";
        List<TBfOrder> orders = this.find(hql, IConstants.VALID, userId);
        if(orders != null && orders.size()>0){
            return orders.get(0);
        }else{
            return null;
        }
    }

    @Override
    public Page<TBfOrder> findOrders(Page<TBfOrder> page, TBfOrder order) {
        List<Object> params = new ArrayList<Object>();
        StringBuffer hql = new StringBuffer("from TBfOrder t where 1=1 ");
        if(order != null){
            if(IStringUtil.isNotBlank(order.getConsigneeMobile())){
                hql.append(" and t.consigneeMobile like ?");
                params.add("%"+order.getConsigneeMobile()+"%");
            }
            if(IStringUtil.isNotBlank(order.getConsigneeAddress())){
                hql.append(" and t.consigneeAddress like ?");
                params.add("%"+order.getConsigneeAddress()+"%");
            }
            if(IStringUtil.isNotBlank(order.getConsigneeName())){
                hql.append(" and t.consigneeName like ?");
                params.add("%"+order.getConsigneeName()+"%");
            }
        }
        hql.append(" order by t.createTime desc");
        return this.findByHql(page, hql.toString(), params);
    }

}
