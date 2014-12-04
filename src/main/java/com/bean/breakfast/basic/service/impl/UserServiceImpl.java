package com.bean.breakfast.basic.service.impl;

import com.bean.breakfast.basic.dao.OrderDao;
import com.bean.breakfast.basic.dao.OrderDetailDao;
import com.bean.breakfast.basic.dao.UserDao;
import com.bean.breakfast.basic.dto.FoodDTO;
import com.bean.breakfast.basic.dto.OrderDTO;
import com.bean.breakfast.basic.model.TBfOrder;
import com.bean.breakfast.basic.model.TBfOrderDetail;
import com.bean.breakfast.basic.model.TBfUser;
import com.bean.breakfast.basic.service.OrderService;
import com.bean.breakfast.basic.service.UserService;
import com.bean.breakfast.constants.IConstants;
import com.bean.core.orm.service.impl.BaseServiceImpl;
import com.bean.core.utils.IDateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl extends BaseServiceImpl<TBfUser,String> implements UserService {
	@Autowired
	private UserDao userDao;

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
		super.setBaseDao(userDao);
	}

	@Override
	public TBfUser getUserByWeixin(String weixin) {
		return userDao.getUserByWeixin(weixin);
	}

	@Override
	public TBfUser getUserByPhone(String phone) {
		return userDao.getUserByPhone(phone);
	}
	public String saveUser(TBfUser user) {
		user.setStatus(IConstants.VALID);
		user.setCreateTime(IDateUtil.getCurrentTimeDate());
		return userDao.save(user);
	}

}
