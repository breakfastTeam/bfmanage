package com.bean.breakfast.basic.dao.impl;

import com.bean.breakfast.basic.dao.FileDao;
import com.bean.breakfast.basic.dao.UserCourierDao;
import com.bean.breakfast.basic.model.TBfFile;
import com.bean.breakfast.basic.model.TBfUserCourier;
import com.bean.core.orm.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;

/**
 * Created by qingfeilee on 2014/11/24.
 */
@Repository("userCourierDao")
public class UserCourierDaoImpl extends BaseDaoImpl<TBfUserCourier,String> implements UserCourierDao {
}
