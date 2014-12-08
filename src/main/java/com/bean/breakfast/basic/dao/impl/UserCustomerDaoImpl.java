package com.bean.breakfast.basic.dao.impl;

import com.bean.breakfast.basic.dao.UserCourierDao;
import com.bean.breakfast.basic.dao.UserCustomerDao;
import com.bean.breakfast.basic.model.TBfUserCourier;
import com.bean.breakfast.basic.model.TBfUserCustomer;
import com.bean.core.orm.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;

/**
 * Created by qingfeilee on 2014/11/24.
 */
@Repository("userCustomerDao")
public class UserCustomerDaoImpl extends BaseDaoImpl<TBfUserCustomer,String> implements UserCustomerDao {
}
