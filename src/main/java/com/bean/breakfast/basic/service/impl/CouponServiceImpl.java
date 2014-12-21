package com.bean.breakfast.basic.service.impl;

import com.bean.breakfast.basic.dao.CouponDao;
import com.bean.breakfast.basic.dao.ExpressDao;
import com.bean.breakfast.basic.dao.UserCustomerDao;
import com.bean.breakfast.basic.dao.UserDao;
import com.bean.breakfast.basic.dto.CouponCustomerDTO;
import com.bean.breakfast.basic.dto.UserCustomerDTO;
import com.bean.breakfast.basic.model.TBfCoupon;
import com.bean.breakfast.basic.model.TBfExpress;
import com.bean.breakfast.basic.model.TBfUser;
import com.bean.breakfast.basic.model.TBfUserCustomer;
import com.bean.breakfast.basic.service.CouponService;
import com.bean.breakfast.basic.service.ExpressService;
import com.bean.core.orm.service.impl.BaseServiceImpl;
import com.bean.core.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("couponService")
@Transactional
public class CouponServiceImpl extends BaseServiceImpl<TBfCoupon,String> implements CouponService {
	@Autowired
	private CouponDao couponDao;

	@Autowired
	private UserDao userDao;
	@Autowired
	private UserCustomerDao userCustomerDao;



	public CouponDao getCouponDao() {
		return couponDao;
	}

	public void setCouponDao(CouponDao couponDao) {
		this.couponDao = couponDao;
		super.setBaseDao(couponDao);
	}

	@Override
	public TBfCoupon getCoupon(String informationId) {
		try {
			TBfCoupon information = couponDao.get(informationId);
			return information;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Page<CouponCustomerDTO> findCoupon(Page<TBfCoupon> couponPage, TBfCoupon coupon) {
		Page<CouponCustomerDTO> couponCustomerDTOPage = new Page<CouponCustomerDTO>(couponPage.getPageSize());
		List<CouponCustomerDTO> couponCustomerDTOs = new ArrayList<CouponCustomerDTO>();
		couponPage = couponDao.findCoupon(couponPage, coupon);
		TBfUser user = null;
		TBfUserCustomer userCustomer = null;
		TBfUser sender = null;
		for(int i = 0; i<couponPage.getResult().size(); i++){
			CouponCustomerDTO couponCustomerDTO = new CouponCustomerDTO();
			UserCustomerDTO userCustomerDTO = new UserCustomerDTO();
			TBfCoupon tempCoupon = couponPage.getResult().get(i);
			try {
				user = userDao.get(tempCoupon.getUserId());
				userCustomer = userCustomerDao.get(tempCoupon.getUserId());
				sender = userDao.get(tempCoupon.getUserId());

			} catch (Exception e) {
				e.printStackTrace();
			}
			if(user == null){
				user = new TBfUser();
			}
			if(userCustomer == null){
				userCustomer = new TBfUserCustomer();
			}
			userCustomerDTO.setCustomer(userCustomer);
			userCustomerDTO.setUser(user);
			couponCustomerDTO.setCoupon(couponPage.getResult().get(i));
			couponCustomerDTO.setCustomerDTO(userCustomerDTO);
			couponCustomerDTO.setSender(sender);
			couponCustomerDTOs.add(couponCustomerDTO);
		}
		couponCustomerDTOPage.setPageNo(couponPage.getPageNo());
		couponCustomerDTOPage.setTotalCount(couponPage.getTotalCount());
		couponCustomerDTOPage.setResult(couponCustomerDTOs);
		return couponCustomerDTOPage;
	}

}
