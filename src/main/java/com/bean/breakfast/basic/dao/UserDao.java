package com.bean.breakfast.basic.dao;

import com.bean.breakfast.basic.model.TBfFile;
import com.bean.breakfast.basic.model.TBfUser;
import com.bean.core.orm.dao.BaseDao;

/**
 * Created by qingfeilee on 2014/11/24.
 */
public interface UserDao extends BaseDao<TBfUser, String> {
    public TBfUser getUserByWeixin(String weixin);
}
