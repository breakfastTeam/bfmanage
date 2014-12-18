package com.bean.breakfast.basic.service.impl;

import com.bean.breakfast.basic.dao.ProviderDao;
import com.bean.breakfast.basic.dao.RawMaterialDao;
import com.bean.breakfast.basic.model.TBfProvider;
import com.bean.breakfast.basic.model.TBfRawMaterial;
import com.bean.breakfast.basic.service.ProviderService;
import com.bean.breakfast.basic.service.RawMaterialService;
import com.bean.core.orm.service.impl.BaseServiceImpl;
import com.bean.core.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("rawMaterialService")
@Transactional
public class RawMaterialServiceImpl extends BaseServiceImpl<TBfRawMaterial,String> implements RawMaterialService {
	@Autowired
	private RawMaterialDao rawMaterialDao;

	public RawMaterialDao getRawMaterialDao() {
		return rawMaterialDao;
	}

	public void setProviderDao(RawMaterialDao rawMaterialDao) {
		this.rawMaterialDao = rawMaterialDao;
		super.setBaseDao(rawMaterialDao);
	}

	@Override
	public void saveOrUpdate(TBfRawMaterial rawMaterial) {
		rawMaterialDao.saveOrUpdate(rawMaterial);
	}

	@Override
	public Page<TBfRawMaterial> findRawMaterial(Page<TBfRawMaterial> page, TBfRawMaterial rawMaterial) {
		return rawMaterialDao.findRawMaterial(page, rawMaterial);
	}

	@Override
	public TBfRawMaterial getRawMaterial(String rawMaterialId) {
		try {
			TBfRawMaterial rawMaterial = rawMaterialDao.get(rawMaterialId);
			return rawMaterial;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
