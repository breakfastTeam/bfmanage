package com.bean.breakfast.basic.dao.impl;

import com.bean.breakfast.basic.dao.FileDao;
import com.bean.breakfast.basic.dao.InformationDao;
import com.bean.breakfast.basic.model.TBfFile;
import com.bean.breakfast.basic.model.TBfInformation;
import com.bean.breakfast.basic.model.TBfSetMeal;
import com.bean.breakfast.constants.IConstants;
import com.bean.core.orm.dao.impl.BaseDaoImpl;
import com.bean.core.page.Page;
import com.bean.core.utils.IDateUtil;
import com.bean.core.utils.IStringUtil;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qingfeilee on 2014/11/24.
 */
@Repository("informationDao")
public class InformationDaoImpl extends BaseDaoImpl<TBfInformation, String> implements InformationDao {
    public Page<TBfInformation> findInformation(Page<TBfInformation> page, TBfInformation information) {
        List<Object> params = new ArrayList<Object>();
        String hql = "from TBfInformation t where t.status=?";
        params.add(IConstants.VALID);
        if (IStringUtil.isNotBlank(information.getTitle())) {
            hql = hql + " and t.title like ?";
            params.add("%" + information.getTitle() + "%");
        }

        hql = hql + " order by createTime desc";
        return this.findByHql(page, hql, params);
    }
}
