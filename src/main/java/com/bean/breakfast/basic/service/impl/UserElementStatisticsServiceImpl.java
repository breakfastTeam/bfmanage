package com.bean.breakfast.basic.service.impl;

import com.bean.breakfast.basic.dao.ElementDao;
import com.bean.breakfast.basic.dao.UserElementStatisticsDao;
import com.bean.breakfast.basic.model.TBfElement;
import com.bean.breakfast.basic.model.TBfUserElementStatistics;
import com.bean.breakfast.basic.service.ElementService;
import com.bean.breakfast.basic.service.UserElementStatisticsService;
import com.bean.core.orm.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userElementStatisticsService")
@Transactional
public class UserElementStatisticsServiceImpl extends BaseServiceImpl<TBfUserElementStatistics,String> implements UserElementStatisticsService {
	@Autowired
	private UserElementStatisticsDao userElementStatisticsDao;

	public UserElementStatisticsDao getUserElementStatisticsDao() {
		return userElementStatisticsDao;
	}

	public void setUserElementStatisticsDao(UserElementStatisticsDao userElementStatisticsDao) {
		this.userElementStatisticsDao = userElementStatisticsDao;
		super.setBaseDao(userElementStatisticsDao);
	}

	@Override
	public void saveOrUpdate(TBfUserElementStatistics userElementStatistics) {
		userElementStatisticsDao.saveOrUpdate(userElementStatistics);
	}
}
