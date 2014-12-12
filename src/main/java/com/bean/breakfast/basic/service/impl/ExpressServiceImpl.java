package com.bean.breakfast.basic.service.impl;

import com.bean.breakfast.basic.dao.ExpressDao;
import com.bean.breakfast.basic.dao.FileDao;
import com.bean.breakfast.basic.dao.FoodDao;
import com.bean.breakfast.basic.dto.FoodDTO;
import com.bean.breakfast.basic.model.TBfExpress;
import com.bean.breakfast.basic.model.TBfFile;
import com.bean.breakfast.basic.model.TBfFood;
import com.bean.breakfast.basic.service.ExpressService;
import com.bean.breakfast.basic.service.FoodService;
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

@Service("expressService")
@Transactional
public class ExpressServiceImpl extends BaseServiceImpl<TBfExpress,String> implements ExpressService {
	@Autowired
	private ExpressDao expressDao;

	public ExpressDao getExpressDao() {
		return expressDao;
	}

	public void setExpressDao(ExpressDao expressDao) {
		this.expressDao = expressDao;
		super.setBaseDao(expressDao);
	}

	public void updateCourierPostion(String courierId, String longitude, String latitude) throws Exception {
		List<TBfExpress> expressList = expressDao.getExpressByCourierId(courierId);
		for(int i = 0; i<expressList.size(); i++){
				TBfExpress express = expressDao.get(expressList.get(i).getExpressId());
				express.setLatitude(latitude);
				express.setLongitude(longitude);
				expressDao.saveOrUpdate(express);
		}
	}

	@Override
	public void saveOrUpdate(TBfExpress express) {
		expressDao.saveOrUpdate(express);
	}

	@Override
	public List<TBfExpress> getExpressByCourierId(String courierId) {
		return expressDao.getExpressByCourierId(courierId);
	}
}
