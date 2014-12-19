package com.bean.breakfast.basic.dao.impl;

import com.bean.breakfast.basic.dao.ExpressDao;
import com.bean.breakfast.basic.dao.ProviderDao;
import com.bean.breakfast.basic.model.TBfExpress;
import com.bean.breakfast.basic.model.TBfFood;
import com.bean.breakfast.basic.model.TBfProvider;
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
@Repository("providerDao")
public class ProviderDaoImpl extends BaseDaoImpl<TBfProvider, String> implements ProviderDao {

    @Override
    public Page<TBfProvider> findProvider(Page<TBfProvider> page, TBfProvider provider) {
        List<Object> params = new ArrayList<Object>();
        String hql = "from TBfProvider t where t.status=?";
        params.add(IConstants.ENABLE);
        if (IStringUtil.isNotBlank(provider.getProviderName())) {
            hql = hql + " and t.providerName like ?";
            params.add("%" + provider.getProviderName() + "%");
        }
        if (IStringUtil.isNotBlank(provider.getPhone())) {
            hql = hql + " and t.phone like ?";
            params.add("%" + provider.getPhone() + "%");
        }
        hql = hql + " order by createTime desc";
        return this.findByHql(page, hql, params);
    }

    @Override
    public List<TBfProvider> findProvider(TBfProvider provider) {
        List<Object> params = new ArrayList<Object>();
        String hql = "from TBfProvider t where t.status=?";
        params.add(IConstants.ENABLE);
        hql = hql + " order by createTime desc";
        return this.find(hql, params);
    }
}
