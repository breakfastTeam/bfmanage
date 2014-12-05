package com.bean.breakfast.basic.service;
import com.bean.breakfast.basic.dto.OrderDTO;
import com.bean.breakfast.basic.model.TBfFood;
import com.bean.breakfast.basic.model.TBfOrder;
import com.bean.core.orm.service.BaseService;
import com.bean.core.page.Page;

import java.util.List;

public interface OrderService extends BaseService<TBfOrder, String> {
	public String save(OrderDTO food);
	public List<OrderDTO> getUserOrder(String userId);
	public TBfOrder getUserLatestOrder(String userId);
	public Page<OrderDTO> findOrders(Page<OrderDTO> page ,TBfOrder order);
	public List<TBfFood> getOrderDetail(String orderId);
}
