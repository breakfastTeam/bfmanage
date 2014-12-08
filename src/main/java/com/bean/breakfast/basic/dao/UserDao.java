package com.bean.breakfast.basic.dao;

import com.bean.breakfast.basic.model.TBfFile;
import com.bean.breakfast.basic.model.TBfInformation;
import com.bean.breakfast.basic.model.TBfUser;
import com.bean.core.orm.dao.BaseDao;
import com.bean.core.page.Page;

import java.util.List;

/**
 * Created by qingfeilee on 2014/11/24.
 */
public interface UserDao extends BaseDao<TBfUser, String> {
    public TBfUser getUserByWeixin(String weixin);
    public TBfUser getUserByPhone(String phone);
    public TBfUser getUserByLoginName(String loginName);
    public Page<TBfUser> findUser(Page<TBfUser> page, TBfUser user);
    public List<TBfUser> findUser(TBfUser user);
}
