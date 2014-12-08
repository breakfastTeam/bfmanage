package com.bean.breakfast.basic.service;
import com.bean.breakfast.basic.dto.UserCourierDTO;
import com.bean.breakfast.basic.model.TBfUser;
import com.bean.breakfast.basic.model.TBfUserCourier;
import com.bean.core.orm.service.BaseService;
import com.bean.core.page.Page;

import java.util.List;

public interface UserCourierService extends BaseService<TBfUserCourier, String> {
	public TBfUserCourier getUserCourier(String userId);
	public UserCourierDTO getUserCourierDTO(String userId);
	public void saveOrUpdate(UserCourierDTO userCourierDTO);

	public Page<UserCourierDTO> findUserCourierDTO(Page<UserCourierDTO> page, UserCourierDTO userCourierDTO);
}
