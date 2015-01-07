package com.bean.breakfast.basic.dao.impl;

import com.bean.breakfast.basic.dao.CouponDao;
import com.bean.breakfast.basic.dao.PhotoShowDao;
import com.bean.breakfast.basic.model.TBfCoupon;
import com.bean.breakfast.basic.model.TBfPhotoShow;
import com.bean.breakfast.constants.IConstants;
import com.bean.core.orm.dao.impl.BaseDaoImpl;
import com.bean.core.page.Page;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qingfeilee on 2014/11/24.
 */
@Repository("photoShowDao")
public class PhotoShowDaoImpl extends BaseDaoImpl<TBfPhotoShow,String> implements PhotoShowDao {

}
