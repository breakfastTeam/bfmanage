package com.bean.breakfast.basic.service;
import com.bean.breakfast.basic.model.TBfElement;
import com.bean.breakfast.basic.model.TBfInformation;
import com.bean.core.orm.service.BaseService;
import com.bean.core.page.Page;

public interface ElementService extends BaseService<TBfElement, String> {
	public void saveOrUpdate(TBfElement element);
}
