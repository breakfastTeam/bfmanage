package com.bean.breakfast.basic.service.impl;

import com.bean.breakfast.basic.dao.UserCourierDao;
import com.bean.breakfast.basic.dao.UserCustomerDao;
import com.bean.breakfast.basic.dao.UserDao;
import com.bean.breakfast.basic.dto.UserCourierDTO;
import com.bean.breakfast.basic.dto.UserCustomerDTO;
import com.bean.breakfast.basic.model.TBfUser;
import com.bean.breakfast.basic.model.TBfUserCourier;
import com.bean.breakfast.basic.model.TBfUserCustomer;
import com.bean.breakfast.basic.service.UserCourierService;
import com.bean.breakfast.basic.service.UserCustomerService;
import com.bean.core.orm.service.impl.BaseServiceImpl;
import com.bean.core.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userCustomerService")
@Transactional
public class UserCustomerServiceImpl extends BaseServiceImpl<TBfUserCustomer,String> implements UserCustomerService {
	@Autowired
	private UserCustomerDao userCustomerDao;
	@Autowired
	private UserDao userDao;


	public UserCustomerDao getUserCustomerDao() {
		return userCustomerDao;
	}

	public void setUserCourierDao(UserCustomerDao userCustomerDao) {
		this.userCustomerDao = userCustomerDao;
		super.setBaseDao(userCustomerDao);
	}

	@Override
	public TBfUserCustomer getUserCustomer(String userId) {
		try {
			return userCustomerDao.get(userId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public UserCustomerDTO getUserCustomerDTO(String userId) {
		UserCustomerDTO userCustomerDTO = new UserCustomerDTO();
		TBfUser user;
		TBfUserCustomer userCustomer;
		try {
			user = userDao.get(userId);
			userCustomer = userCustomerDao.get(userId);
		} catch (Exception e) {
			e.printStackTrace();
			user = new TBfUser();
			userCustomer = new TBfUserCustomer();
		}
		userCustomerDTO.setCustomer(userCustomer);
		userCustomerDTO.setUser(user);
		return userCustomerDTO;
	}

	public void saveOrUpdate(UserCustomerDTO userCustomerDTO) {
		TBfUser user = userCustomerDTO.getUser();
		TBfUserCustomer customer = userCustomerDTO.getCustomer();
		userDao.saveOrUpdate(user);
		customer.setUserId(user.getUserId());
		userCustomerDao.saveOrUpdate(customer);
	}

	@Override
	public Page<UserCustomerDTO> findUserCustomerDTO(Page<UserCustomerDTO> page, UserCustomerDTO userCustomerDTO) {
		Page<TBfUser> pageUser = new Page<TBfUser>(page.getPageSize());
		pageUser.setPageNo(page.getPageNo());
		pageUser = userDao.findUserCustomer(pageUser, userCustomerDTO.getUser());
		List<TBfUser> users = pageUser.getResult();
		List<UserCustomerDTO> userCustomerDTOs = new ArrayList<UserCustomerDTO>();
		int userSize = users.size();
		for (int i = 0; i< userSize; i++){
			UserCustomerDTO uc = new UserCustomerDTO();
			uc.setUser(users.get(i));
			try {
				TBfUserCustomer userCustomer = userCustomerDao.get(users.get(i).getUserId());
				uc.setCustomer(userCustomer);

			} catch (Exception e) {
				uc.setCustomer(new TBfUserCustomer());
				e.printStackTrace();
			}
			userCustomerDTOs.add(uc);
		}
		page.setResult(userCustomerDTOs);
		page.setTotalCount(pageUser.getTotalCount());
		page.setPageNo(pageUser.getPageNo());
		return page;
	}
}
