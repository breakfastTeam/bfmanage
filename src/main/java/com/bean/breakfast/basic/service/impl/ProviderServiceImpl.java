package com.bean.breakfast.basic.service.impl;

import com.bean.breakfast.basic.dao.ExpressDao;
import com.bean.breakfast.basic.dao.ProviderDao;
import com.bean.breakfast.basic.model.TBfExpress;
import com.bean.breakfast.basic.model.TBfProvider;
import com.bean.breakfast.basic.service.ExpressService;
import com.bean.breakfast.basic.service.ProviderService;
import com.bean.core.orm.service.impl.BaseServiceImpl;
import com.bean.core.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("providerService")
@Transactional
public class ProviderServiceImpl extends BaseServiceImpl<TBfProvider,String> implements ProviderService {
	@Autowired
	private ProviderDao providerDao;

	public ProviderDao getProviderDao() {
		return providerDao;
	}

	public void setProviderDao(ProviderDao providerDao) {
		this.providerDao = providerDao;
		super.setBaseDao(providerDao);
	}

	@Override
	public void saveOrUpdate(TBfProvider provider) {
		providerDao.saveOrUpdate(provider);
	}

	@Override
	public Page<TBfProvider> findProvider(Page<TBfProvider> providerPage, TBfProvider provider) {
		return providerDao.findProvider(providerPage, provider);
	}

	@Override
	public TBfProvider getProvider(String providerId) {
		try {
			TBfProvider provider = providerDao.get(providerId);
			return provider;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
