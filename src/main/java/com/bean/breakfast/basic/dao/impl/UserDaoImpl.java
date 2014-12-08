package com.bean.breakfast.basic.dao.impl;

import com.bean.breakfast.basic.dao.FileDao;
import com.bean.breakfast.basic.dao.UserDao;
import com.bean.breakfast.basic.model.TBfFile;
import com.bean.breakfast.basic.model.TBfInformation;
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
@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<TBfUser, String> implements UserDao {

    @Override
    public TBfUser getUserByLoginName(String loginName) {
        List<String> params = new ArrayList<String>();
        String hql = "from TBfUser t where t.status=? and t.loginName=?";
        params.add(IConstants.VALID);
        List<TBfUser> users = this.find(hql, IConstants.VALID, loginName);
        if(users.size()>0){
            return users.get(0);
        }else{
            return null;
        }
    }

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

    @Override
    public TBfUser getUserByPhone(String phone) {
        List<String> params = new ArrayList<String>();
        String hql = "from TBfUser t where t.status=? and t.mobile=?";
        params.add(IConstants.VALID);
        List<TBfUser> users = this.find(hql, IConstants.VALID, phone);
        if(users.size()>0){
            return users.get(0);
        }else{
            return null;
        }
    }

    @Override
    public Page<TBfUser> findUser(Page<TBfUser> page, TBfUser user) {
            List<Object> params = new ArrayList<Object>();
            String hql = "from TBfUser t where t.status=?";
            params.add(IConstants.VALID);
            if (IStringUtil.isNotBlank(user.getMobile())) {
                hql = hql + " and t.mobile like ?";
                params.add("%" + user.getMobile() + "%");
            }
            hql = hql + " order by createTime desc";
            return this.findByHql(page, hql, params);
    }

    @Override
    public List<TBfUser> findUser(TBfUser user) {
        List<Object> params = new ArrayList<Object>();
        String hql = "from TBfUser t where t.status=?";
        params.add(IConstants.VALID);
        if (IStringUtil.isNotBlank(user.getMobile())) {
            hql = hql + " and t.mobile like ?";
            params.add("%" + user.getMobile() + "%");
        }
        hql = hql + " order by createTime desc";
        return this.find(hql, params);
    }
}
