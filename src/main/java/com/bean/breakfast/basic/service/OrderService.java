package com.bean.breakfast.basic.service;
import com.bean.breakfast.basic.dto.OrderDTO;
import com.bean.breakfast.basic.model.TBfFood;
import com.bean.breakfast.basic.model.TBfOrder;
import com.bean.core.orm.service.BaseService;

import java.util.List;

public interface OrderService extends BaseService<TBfOrder, String> {
	public String save(OrderDTO food);
	public List<OrderDTO> getUserOrder(String userId);
}
