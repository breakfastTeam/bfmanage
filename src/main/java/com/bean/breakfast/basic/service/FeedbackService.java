package com.bean.breakfast.basic.service;
import com.bean.breakfast.basic.model.TBfFeedback;
import com.bean.core.orm.service.BaseService;
import com.bean.core.page.Page;

public interface FeedbackService extends BaseService<TBfFeedback, String> {
	public void saveOrUpdate(TBfFeedback rawMaterial);
	public Page<TBfFeedback> findFeedback(Page<TBfFeedback> page, TBfFeedback rawMaterial);
	public TBfFeedback getFeedback(String rawMaterialId);
}
