package com.bean.breakfast.basic.dao.impl;

import com.bean.breakfast.basic.dao.ElementDao;
import com.bean.breakfast.basic.dao.UserElementStatisticsDao;
import com.bean.breakfast.basic.model.TBfElement;
import com.bean.breakfast.basic.model.TBfUserElementStatistics;
import com.bean.core.orm.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;

/**
 * Created by qingfeilee on 2014/11/24.
 */
@Repository("userElementStatisticsDao")
public class UserElementStatisticsDaoImpl extends BaseDaoImpl<TBfUserElementStatistics, String> implements UserElementStatisticsDao {

}
