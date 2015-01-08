package com.bean.breakfast.basic.service.impl;

import com.bean.breakfast.basic.dao.ElementDao;
import com.bean.breakfast.basic.dao.InformationDao;
import com.bean.breakfast.basic.model.TBfElement;
import com.bean.breakfast.basic.model.TBfInformation;
import com.bean.breakfast.basic.service.ElementService;
import com.bean.breakfast.basic.service.InformationService;
import com.bean.core.orm.service.impl.BaseServiceImpl;
import com.bean.core.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.lang.model.element.Element;

@Service("elementService")
@Transactional
public class ElementServiceImpl extends BaseServiceImpl<TBfElement,String> implements ElementService {
	@Autowired
	private ElementDao elementDao;

	public ElementDao getElementDao() {
		return elementDao;
	}

	public void setElementDao(ElementDao elementDao) {
		this.elementDao = elementDao;
		super.setBaseDao(elementDao);
	}


	@Override
	public void saveOrUpdate(TBfElement element) {
		elementDao.saveOrUpdate(element);
	}
}
