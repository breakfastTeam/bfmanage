package com.bean.breakfast.basic.dao;

import com.bean.breakfast.basic.model.TBfExpress;
import com.bean.breakfast.basic.model.TBfProvider;
import com.bean.core.orm.dao.BaseDao;
import com.bean.core.page.Page;

import java.util.List;

/**
 * Created by qingfeilee on 2014/11/24.
 */
public interface ProviderDao extends BaseDao<TBfProvider, String> {
    public Page<TBfProvider> findProvider(Page<TBfProvider> providerPage, TBfProvider provider);
}
