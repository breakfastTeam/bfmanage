package com.bean.breakfast.basic.dao;

import com.bean.breakfast.basic.model.TBfFeedback;
import com.bean.core.orm.dao.BaseDao;
import com.bean.core.page.Page;

/**
 * Created by qingfeilee on 2014/11/24.
 */
public interface FeedbackDao extends BaseDao<TBfFeedback, String> {
    public Page<TBfFeedback> findFeedback(Page<TBfFeedback> page, TBfFeedback feedBack);
}
