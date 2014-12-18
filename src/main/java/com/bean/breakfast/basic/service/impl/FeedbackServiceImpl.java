package com.bean.breakfast.basic.service.impl;

import com.bean.breakfast.basic.dao.FeedbackDao;
import com.bean.breakfast.basic.model.TBfFeedback;
import com.bean.breakfast.basic.service.FeedbackService;
import com.bean.core.orm.service.impl.BaseServiceImpl;
import com.bean.core.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("feedBackService")
@Transactional
public class FeedbackServiceImpl extends BaseServiceImpl<TBfFeedback,String> implements FeedbackService {
	@Autowired
	private FeedbackDao feedBackDao;

	public FeedbackDao getFeedbackDao() {
		return feedBackDao;
	}

	public void setProviderDao(FeedbackDao feedBackDao) {
		this.feedBackDao = feedBackDao;
		super.setBaseDao(feedBackDao);
	}

	@Override
	public void saveOrUpdate(TBfFeedback feedBack) {
		feedBackDao.saveOrUpdate(feedBack);
	}

	@Override
	public Page<TBfFeedback> findFeedback(Page<TBfFeedback> page, TBfFeedback feedBack) {
		return feedBackDao.findFeedback(page, feedBack);
	}

	@Override
	public TBfFeedback getFeedback(String feedBackId) {
		try {
			TBfFeedback feedBack = feedBackDao.get(feedBackId);
			return feedBack;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
