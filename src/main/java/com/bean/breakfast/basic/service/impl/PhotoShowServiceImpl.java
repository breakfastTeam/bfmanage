package com.bean.breakfast.basic.service.impl;

import com.bean.breakfast.basic.dao.*;
import com.bean.breakfast.basic.dto.CouponCustomerDTO;
import com.bean.breakfast.basic.dto.UserCustomerDTO;
import com.bean.breakfast.basic.model.*;
import com.bean.breakfast.basic.service.CouponService;
import com.bean.breakfast.basic.service.PhotoShowService;
import com.bean.breakfast.constants.IConstants;
import com.bean.core.orm.service.impl.BaseServiceImpl;
import com.bean.core.page.Page;
import com.bean.core.utils.IDateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("photoShowService")
@Transactional
public class PhotoShowServiceImpl extends BaseServiceImpl<TBfPhotoShow,String> implements PhotoShowService {
	@Autowired
	private PhotoShowDao photoShowDao;

	@Autowired
	private FileDao fileDao;

	public PhotoShowDao getPhotoShowDao() {
		return photoShowDao;
	}

	public void setPhotoShowDao(PhotoShowDao photoShowDao) {
		this.photoShowDao = photoShowDao;
		super.setBaseDao(photoShowDao);
	}

	@Override
	public void saveOrUpdate(TBfPhotoShow photoShow, TBfFile bigPic, TBfFile smallPic) {
		fileDao.save(bigPic);
		fileDao.save(smallPic);
		photoShow.setSmallFileId(smallPic.getFileId());
		photoShow.setOrginFileId(bigPic.getFileId());
		photoShowDao.saveOrUpdate(photoShow);
	}
}
