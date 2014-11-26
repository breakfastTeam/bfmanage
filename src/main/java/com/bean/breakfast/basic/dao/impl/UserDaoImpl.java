package com.bean.breakfast.basic.dao.impl;

import com.bean.breakfast.basic.dao.FileDao;
import com.bean.breakfast.basic.dao.UserDao;
import com.bean.breakfast.basic.model.TBfFile;
import com.bean.breakfast.basic.model.TBfUser;
import com.bean.breakfast.constants.IConstants;
import com.bean.core.orm.dao.impl.BaseDaoImpl;
import com.bean.core.utils.IStringUtil;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qingfeilee on 2014/11/24.
 */
@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<TBfUser, String> implements UserDao {
    @Override
    public TBfUser getUserByWeixin(String weixin) {
        List<String> params = new ArrayList<String>();
        String hql = "from TBfUser t where t.status=? and t.weixin=?";
        params.add(IConstants.VALID);
        List<TBfUser> users = this.find(hql, IConstants.VALID, weixin);
        if(users.size()>0){
            return users.get(0);
        }else{
            return null;
        }
    }
}
