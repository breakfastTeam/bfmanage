package com.bean.breakfast.basic.dao;

import com.bean.breakfast.basic.model.TBfInformation;
import com.bean.breakfast.basic.model.TBfOrderDetail;
import com.bean.breakfast.basic.model.TBfSetMeal;
import com.bean.core.orm.dao.BaseDao;
import com.bean.core.page.Page;

import java.util.List;

/**
 * Created by qingfeilee on 2014/11/24.
 */
public interface InformationDao extends BaseDao<TBfInformation, String> {
    public Page<TBfInformation> findInformation(Page<TBfInformation> page, TBfInformation information);
}
