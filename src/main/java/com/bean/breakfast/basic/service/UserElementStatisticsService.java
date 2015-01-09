package com.bean.breakfast.basic.service;
import com.bean.breakfast.basic.model.TBfElement;
import com.bean.breakfast.basic.model.TBfUserElementStatistics;
import com.bean.core.orm.service.BaseService;

public interface UserElementStatisticsService extends BaseService<TBfUserElementStatistics, String> {
	public void saveOrUpdate(TBfUserElementStatistics elementStatistics);
}
