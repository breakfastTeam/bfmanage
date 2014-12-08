package com.bean.breakfast.basic.service;
import com.bean.breakfast.basic.dto.OrderDTO;
import com.bean.breakfast.basic.model.TBfInformation;
import com.bean.breakfast.basic.model.TBfOrder;
import com.bean.breakfast.basic.model.TBfUser;
import com.bean.core.orm.service.BaseService;
import com.bean.core.page.Page;

import java.util.List;

public interface UserService extends BaseService<TBfUser, String> {
	public TBfUser getUserByWeixin(String weixin);
	public TBfUser getUserByLoginName(String loginName);
	public TBfUser getUserByPhone(String phone);
	public List<TBfUser> findUser(TBfUser user);
	public String saveUser(TBfUser user);
	public TBfUser getUser(String userId);
	public Page<TBfUser> findUser(Page<TBfUser> userPage, TBfUser user);
	public void saveOrUpdate(TBfUser user);
}
