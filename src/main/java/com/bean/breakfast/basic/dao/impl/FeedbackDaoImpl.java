package com.bean.breakfast.basic.dao.impl;

import com.bean.breakfast.basic.dao.FeedbackDao;
import com.bean.breakfast.basic.model.TBfFeedback;
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
@Repository("feedBackDao")
public class FeedbackDaoImpl extends BaseDaoImpl<TBfFeedback, String> implements FeedbackDao {

    @Override
    public Page<TBfFeedback> findFeedback(Page<TBfFeedback> page, TBfFeedback feedBack) {
        List<Object> params = new ArrayList<Object>();
        String hql = "from TBfFeedback t where t.status=?";
        params.add(IConstants.ENABLE);
        hql = hql + " order by createTime desc";
        return this.findByHql(page, hql, params);
    }
}
