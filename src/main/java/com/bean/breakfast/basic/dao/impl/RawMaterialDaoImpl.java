package com.bean.breakfast.basic.dao.impl;

import com.bean.breakfast.basic.dao.ProviderDao;
import com.bean.breakfast.basic.dao.RawMaterialDao;
import com.bean.breakfast.basic.model.TBfProvider;
import com.bean.breakfast.basic.model.TBfRawMaterial;
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
@Repository("rawMaterialDao")
public class RawMaterialDaoImpl extends BaseDaoImpl<TBfRawMaterial, String> implements RawMaterialDao {

    @Override
    public Page<TBfRawMaterial> findRawMaterial(Page<TBfRawMaterial> page, TBfRawMaterial rawMaterial) {
        List<Object> params = new ArrayList<Object>();
        String hql = "from TBfRawMaterial t where t.status=?";
        params.add(IConstants.ENABLE);
        if (IStringUtil.isNotBlank(rawMaterial.getRawMaterialName())) {
            hql = hql + " and t.rawMaterialName like ?";
            params.add("%" + rawMaterial.getRawMaterialName() + "%");
        }
        hql = hql + " order by createTime desc";
        return this.findByHql(page, hql, params);
    }
}
