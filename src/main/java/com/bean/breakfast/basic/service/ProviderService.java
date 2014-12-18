package com.bean.breakfast.basic.service;
import com.bean.breakfast.basic.model.TBfExpress;
import com.bean.breakfast.basic.model.TBfInformation;
import com.bean.breakfast.basic.model.TBfProvider;
import com.bean.core.orm.service.BaseService;
import com.bean.core.page.Page;

import java.util.List;

public interface ProviderService extends BaseService<TBfProvider, String> {
	public void saveOrUpdate(TBfProvider provider);
	public Page<TBfProvider> findProvider(Page<TBfProvider> providerPage, TBfProvider provider);
	public TBfProvider getProvider(String providerId);
}
