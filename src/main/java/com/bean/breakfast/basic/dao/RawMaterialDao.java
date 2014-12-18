package com.bean.breakfast.basic.dao;

import com.bean.breakfast.basic.model.TBfProvider;
import com.bean.breakfast.basic.model.TBfRawMaterial;
import com.bean.core.orm.dao.BaseDao;
import com.bean.core.page.Page;

/**
 * Created by qingfeilee on 2014/11/24.
 */
public interface RawMaterialDao extends BaseDao<TBfRawMaterial, String> {
    public Page<TBfRawMaterial> findRawMaterial(Page<TBfRawMaterial> page, TBfRawMaterial rawMaterial);
}
