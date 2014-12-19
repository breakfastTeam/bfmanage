package com.bean.breakfast.basic.service.impl;

import com.bean.breakfast.basic.dao.ProviderDao;
import com.bean.breakfast.basic.dao.RawMaterialDao;
import com.bean.breakfast.basic.dto.RawMaterialDTO;
import com.bean.breakfast.basic.model.TBfProvider;
import com.bean.breakfast.basic.model.TBfRawMaterial;
import com.bean.breakfast.basic.service.ProviderService;
import com.bean.breakfast.basic.service.RawMaterialService;
import com.bean.core.orm.service.impl.BaseServiceImpl;
import com.bean.core.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("rawMaterialService")
@Transactional
public class RawMaterialServiceImpl extends BaseServiceImpl<TBfRawMaterial,String> implements RawMaterialService {
	@Autowired
	private RawMaterialDao rawMaterialDao;
	@Autowired
	private ProviderDao providerDao;


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
	public Page<RawMaterialDTO> findRawMaterial(Page<TBfRawMaterial> page, TBfRawMaterial rawMaterial) {
		Page<RawMaterialDTO> pageDTO = new Page<RawMaterialDTO>(page.getPageSize());

		page = rawMaterialDao.findRawMaterial(page, rawMaterial);
		List<TBfRawMaterial> rawMaterials = page.getResult();
		List<RawMaterialDTO> rawMaterialDTOs = new ArrayList<RawMaterialDTO>();
		for (TBfRawMaterial rm : rawMaterials){
			RawMaterialDTO rawMaterialDTO = new RawMaterialDTO();
			String providerId = rm.getProviderId();
			try {
				TBfProvider provider = providerDao.get(providerId);
				rawMaterialDTO.setProvider(provider);
			} catch (Exception e) {
				e.printStackTrace();
			}
			rawMaterialDTO.setRawMaterial(rm);
			rawMaterialDTOs.add(rawMaterialDTO);
		}
		pageDTO.setResult(rawMaterialDTOs);
		pageDTO.setPageNo(page.getPageNo());
		pageDTO.setTotalCount(page.getTotalCount());
		return pageDTO;
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
