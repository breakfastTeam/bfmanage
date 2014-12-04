package com.bean.breakfast.basic.service;
import com.bean.breakfast.basic.dto.OrderDTO;
import com.bean.breakfast.basic.model.TBfOrder;
import com.bean.breakfast.basic.model.TBfUser;
import com.bean.core.orm.service.BaseService;

public interface UserService extends BaseService<TBfUser, String> {
	public TBfUser getUserByWeixin(String weixin);
	public TBfUser getUserByPhone(String phone);
	public String saveUser(TBfUser user);
}
