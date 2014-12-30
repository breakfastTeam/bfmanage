package com.bean.breakfast.basic.service.impl;

import com.bean.breakfast.basic.dao.CouponDao;
import com.bean.breakfast.basic.dao.SendCouponDao;
import com.bean.breakfast.basic.dao.UserCustomerDao;
import com.bean.breakfast.basic.dao.UserDao;
import com.bean.breakfast.basic.dto.CouponCustomerDTO;
import com.bean.breakfast.basic.dto.SendCouponCustomerDTO;
import com.bean.breakfast.basic.dto.UserCustomerDTO;
import com.bean.breakfast.basic.model.TBfCoupon;
import com.bean.breakfast.basic.model.TBfSendCoupon;
import com.bean.breakfast.basic.model.TBfUser;
import com.bean.breakfast.basic.model.TBfUserCustomer;
import com.bean.breakfast.basic.service.CouponService;
import com.bean.breakfast.basic.service.SendCouponService;
import com.bean.breakfast.constants.IConstants;
import com.bean.core.orm.service.impl.BaseServiceImpl;
import com.bean.core.page.Page;
import com.bean.core.utils.IDateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("sendCouponService")
@Transactional
public class SendCouponServiceImpl extends BaseServiceImpl<TBfSendCoupon,String> implements SendCouponService {
	@Autowired
	private SendCouponDao sendCouponDao;

	@Autowired
	private UserDao userDao;
	@Autowired
	private UserCustomerDao userCustomerDao;



	public SendCouponDao getSendCouponDao() {
		return sendCouponDao;
	}

	public void setSendCouponDao(SendCouponDao sendCouponDao) {
		this.sendCouponDao = sendCouponDao;
		super.setBaseDao(sendCouponDao);
	}

	public TBfSendCoupon getSendCoupon(String informationId) {
		try {
			TBfSendCoupon sendCoupon = sendCouponDao.get(informationId);
			return sendCoupon;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Page<SendCouponCustomerDTO> findSendCoupon(Page<TBfSendCoupon> sendCouponPage, TBfSendCoupon sendCoupon) {
		Page<SendCouponCustomerDTO> sendCouponCustomerDTOPage = new Page<SendCouponCustomerDTO>(sendCouponPage.getPageSize());
		List<SendCouponCustomerDTO> sendCouponCustomerDTOs = new ArrayList<SendCouponCustomerDTO>();
		sendCouponPage = sendCouponDao.findSendCoupon(sendCouponPage, sendCoupon);
		TBfUser user = null;
		TBfUserCustomer userCustomer = null;
		TBfUser sender = null;
		for(int i = 0; i<sendCouponPage.getResult().size(); i++){
			SendCouponCustomerDTO sendCouponCustomerDTO = new SendCouponCustomerDTO();
			UserCustomerDTO userCustomerDTO = new UserCustomerDTO();
			TBfSendCoupon tempSendCoupon = sendCouponPage.getResult().get(i);
			try {
				user = userDao.get(tempSendCoupon.getSource());
				userCustomer = userCustomerDao.get(tempSendCoupon.getSource());
				sender = userDao.get(tempSendCoupon.getSource());

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
			sendCouponCustomerDTO.setSendCoupon(sendCouponPage.getResult().get(i));
			sendCouponCustomerDTO.setCustomerDTO(userCustomerDTO);
			sendCouponCustomerDTO.setSender(sender);
			sendCouponCustomerDTOs.add(sendCouponCustomerDTO);
		}
		sendCouponCustomerDTOPage.setPageNo(sendCouponPage.getPageNo());
		sendCouponCustomerDTOPage.setTotalCount(sendCouponPage.getTotalCount());
		sendCouponCustomerDTOPage.setResult(sendCouponCustomerDTOs);
		return sendCouponCustomerDTOPage;
	}

	public void updateSendCouponStatus(){
		TBfSendCoupon sendCoupon = new TBfSendCoupon();
		List<TBfSendCoupon> sendCoupons = sendCouponDao.findSendCoupon(sendCoupon);
		for(TBfSendCoupon c : sendCoupons){
			if(IDateUtil.parseDate(c.getEndTime().toString(), 1).compareTo(IDateUtil.getCurrentTimeDate()) >= 0){
				c.setStatus(IConstants.ENABLE);
			}else{
				c.setStatus(IConstants.DISCARD);
			}
			sendCouponDao.saveOrUpdate(c);
		}
	}

	@Override
	public void saveOrUpdate(TBfSendCoupon sendCoupon) {
		sendCouponDao.saveOrUpdate(sendCoupon);
	}

}
