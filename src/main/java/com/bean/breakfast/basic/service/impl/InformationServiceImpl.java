package com.bean.breakfast.basic.service.impl;

import com.bean.breakfast.basic.dao.FileDao;
import com.bean.breakfast.basic.dao.FoodDao;
import com.bean.breakfast.basic.dao.InformationDao;
import com.bean.breakfast.basic.dto.FoodDTO;
import com.bean.breakfast.basic.model.TBfFile;
import com.bean.breakfast.basic.model.TBfFood;
import com.bean.breakfast.basic.model.TBfInformation;
import com.bean.breakfast.basic.service.FoodService;
import com.bean.breakfast.basic.service.InformationService;
import com.bean.breakfast.constants.IConstants;
import com.bean.core.orm.service.impl.BaseServiceImpl;
import com.bean.core.page.Page;
import com.bean.core.utils.IDateUtil;
import com.bean.core.utils.IStringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("informationService")
@Transactional
public class InformationServiceImpl extends BaseServiceImpl<TBfInformation,String> implements InformationService {
	@Autowired
	private InformationDao informationDao;

	public InformationDao getInformationDao() {
		return informationDao;
	}

	public void setInformationDao(InformationDao informationDao) {
		this.informationDao = informationDao;
		super.setBaseDao(informationDao);
	}

	@Override
	public TBfInformation getInformation(String informationId) {
		try {
			TBfInformation information = informationDao.get(informationId);
			return information;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Page<TBfInformation> findInformation(Page<TBfInformation> informationPage, TBfInformation information) {
		return informationDao.findInformation(informationPage, information);
	}

	@Override
	public void saveOrUpdate(TBfInformation information) {
		informationDao.saveOrUpdate(information);
	}
}
