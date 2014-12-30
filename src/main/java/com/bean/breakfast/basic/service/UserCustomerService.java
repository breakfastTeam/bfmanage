package com.bean.breakfast.basic.service;
import com.bean.breakfast.basic.dto.UserCourierDTO;
import com.bean.breakfast.basic.dto.UserCustomerDTO;
import com.bean.breakfast.basic.model.TBfUserCourier;
import com.bean.breakfast.basic.model.TBfUserCustomer;
import com.bean.core.orm.service.BaseService;
import com.bean.core.page.Page;

import java.util.List;

public interface UserCustomerService extends BaseService<TBfUserCustomer, String> {
	public TBfUserCustomer getUserCustomer(String userId);
	public UserCustomerDTO getUserCustomerDTO(String userId);
	public void saveOrUpdate(UserCustomerDTO userCourierDTO);
	public Page<UserCustomerDTO> findUserCustomerDTO(Page<UserCustomerDTO> page, UserCustomerDTO userCustomerDTO);
	public List<UserCustomerDTO> findUserCustomerDTO(UserCustomerDTO userCustomerDTO);
}
