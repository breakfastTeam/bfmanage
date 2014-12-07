package com.bean.breakfast.basic.service;
import com.bean.breakfast.basic.dto.SetMealDTO;
import com.bean.breakfast.basic.model.TBfInformation;
import com.bean.breakfast.basic.model.TBfSetMeal;
import com.bean.breakfast.basic.model.TBfUser;
import com.bean.core.orm.service.BaseService;
import com.bean.core.page.Page;

public interface InformationService extends BaseService<TBfInformation, String> {
	public TBfInformation getInformation(String informationId);
	public Page<TBfInformation> findInformation(Page<TBfInformation> informationPage, TBfInformation information);
	public void saveOrUpdate(TBfInformation information);
}
