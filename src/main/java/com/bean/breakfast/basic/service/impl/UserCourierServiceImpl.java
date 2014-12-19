package com.bean.breakfast.basic.service.impl;

import com.bean.breakfast.basic.dao.FileDao;
import com.bean.breakfast.basic.dao.FoodDao;
import com.bean.breakfast.basic.dao.UserCourierDao;
import com.bean.breakfast.basic.dao.UserDao;
import com.bean.breakfast.basic.dto.FoodDTO;
import com.bean.breakfast.basic.dto.UserCourierDTO;
import com.bean.breakfast.basic.model.TBfFile;
import com.bean.breakfast.basic.model.TBfFood;
import com.bean.breakfast.basic.model.TBfUser;
import com.bean.breakfast.basic.model.TBfUserCourier;
import com.bean.breakfast.basic.service.FoodService;
import com.bean.breakfast.basic.service.UserCourierService;
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

@Service("userCourierService")
@Transactional
public class UserCourierServiceImpl extends BaseServiceImpl<TBfUserCourier,String> implements UserCourierService {
	@Autowired
	private UserCourierDao userCourierDao;

	@Autowired
	private UserDao userDao;


	public UserCourierDao getUserCourierDao() {
		return userCourierDao;
	}

	public void setUserCourierDao(UserCourierDao userCourierDao) {
		this.userCourierDao = userCourierDao;
		super.setBaseDao(userCourierDao);
	}

	@Override
	public TBfUserCourier getUserCourier(String userId) {
		try {
			return userCourierDao.get(userId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public UserCourierDTO getUserCourierDTO(String userId) {
		UserCourierDTO userCourierDTO = new UserCourierDTO();
		TBfUser user;
		TBfUserCourier userCourier;
		try {
			user = userDao.get(userId);
			userCourier = userCourierDao.get(userId);
		} catch (Exception e) {
			e.printStackTrace();
			user = new TBfUser();
			userCourier = new TBfUserCourier();
		}
		userCourierDTO.setCourier(userCourier);
		userCourierDTO.setUser(user);
		return userCourierDTO;
	}

	public void saveOrUpdate(UserCourierDTO userCourierDTO) {
		TBfUser user = userCourierDTO.getUser();
		TBfUserCourier courier = userCourierDTO.getCourier();
		userDao.saveOrUpdate(user);
		courier.setUserId(user.getUserId());
		userCourierDao.saveOrUpdate(courier);
	}

	@Override
	public Page<UserCourierDTO> findUserCourierDTO(Page<UserCourierDTO> page, UserCourierDTO userCourierDTO) {
		Page<TBfUser> pageUser = new Page<TBfUser>(page.getPageSize());
		pageUser.setPageNo(page.getPageNo());
		pageUser = userDao.findUserCourier(pageUser, userCourierDTO.getUser());
		List<TBfUser> users = pageUser.getResult();
		List<UserCourierDTO> userCourierDTOs = new ArrayList<UserCourierDTO>();
		int userSize = users.size();
		for (int i = 0; i< userSize; i++){
			UserCourierDTO uc = new UserCourierDTO();
			uc.setUser(users.get(i));
			try {
				TBfUserCourier userCourier = userCourierDao.get(users.get(i).getUserId());
				uc.setCourier(userCourier);

			} catch (Exception e) {
				uc.setCourier(new TBfUserCourier());
				e.printStackTrace();
			}
			userCourierDTOs.add(uc);
		}
		page.setResult(userCourierDTOs);
		page.setTotalCount(pageUser.getTotalCount());
		page.setPageNo(pageUser.getPageNo());
		return page;
	}

	@Override
	public List<UserCourierDTO> findUserCourierDTO(UserCourierDTO userCourierDTO) {
		List<TBfUser> users = userDao.findUserCourier(userCourierDTO.getUser());
		List<UserCourierDTO> userCourierDTOs = new ArrayList<UserCourierDTO>();
		int userSize = users.size();
		for (int i = 0; i< userSize; i++){
			UserCourierDTO uc = new UserCourierDTO();
			uc.setUser(users.get(i));
			try {
				TBfUserCourier userCourier = userCourierDao.get(users.get(i).getUserId());
				uc.setCourier(userCourier);

			} catch (Exception e) {
				uc.setCourier(new TBfUserCourier());
				e.printStackTrace();
			}
			userCourierDTOs.add(uc);
		}
		return userCourierDTOs;
	}
}
