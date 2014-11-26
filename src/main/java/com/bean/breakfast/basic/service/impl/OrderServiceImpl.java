package com.bean.breakfast.basic.service.impl;

import com.bean.breakfast.basic.dao.FoodDao;
import com.bean.breakfast.basic.dao.OrderDao;
import com.bean.breakfast.basic.dao.OrderDetailDao;
import com.bean.breakfast.basic.dto.FoodDTO;
import com.bean.breakfast.basic.dto.OrderDTO;
import com.bean.breakfast.basic.model.TBfFile;
import com.bean.breakfast.basic.model.TBfFood;
import com.bean.breakfast.basic.model.TBfOrder;
import com.bean.breakfast.basic.model.TBfOrderDetail;
import com.bean.breakfast.basic.service.OrderService;
import com.bean.core.orm.service.impl.BaseServiceImpl;
import com.bean.core.utils.IDateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Order;
import java.util.ArrayList;
import java.util.List;

@Service("orderService")
@Transactional
public class OrderServiceImpl extends BaseServiceImpl<TBfOrder,String> implements OrderService {
	@Autowired
	private OrderDao orderDao;

	@Autowired
	private OrderDetailDao orderDetailDao;

	@Autowired
	private FoodDao foodDao;

	public OrderDao getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
		super.setBaseDao(orderDao);
	}

	public String save(OrderDTO orderDTO){
		TBfOrder order = new TBfOrder();
		order.setCustomerId(orderDTO.getCustomerId());
		order.setConsigneeName(orderDTO.getConsigneeName());
		order.setConsigneeMobile(orderDTO.getConsigneePhone());
		order.setConsigneeAddress(orderDTO.getConsigneeAddr());
		order.setOrderPrice(orderDTO.getMoney());
		order.setRemark(orderDTO.getRemark());
		String orderId = orderDao.save(order);
		List<FoodDTO> foods = orderDTO.getFoods();
		for (FoodDTO foodDTO : foods) {
			TBfOrderDetail orderDetail = new TBfOrderDetail();
			orderDetail.setOrderId(orderId);
			orderDetail.setFoodId(foodDTO.getFoodId());
			orderDetail.setFoodCount(foodDTO.getFoodNum());
			orderDetailDao.save(orderDetail);
		}
		return orderDao.save(order);
	}

	@Override
	public List<OrderDTO> getUserOrder(String userId) {
		List<OrderDTO> orderDTOs = new ArrayList<OrderDTO>();
		List<TBfOrder> orders = orderDao.getOrdersByUserId(userId);
		for(TBfOrder order : orders){
			OrderDTO orderDTO = new OrderDTO();
			orderDTO.setCustomerId(order.getCustomerId());
			orderDTO.setConsigneeName(order.getConsigneeName());
			orderDTO.setConsigneePhone(order.getConsigneeMobile());
			orderDTO.setConsigneeAddr(order.getConsigneeAddress());
			orderDTO.setMoney(order.getOrderPrice());
			orderDTO.setRemark(orderDTO.getRemark());
			orderDTO.setCreateTime(IDateUtil.dateToString(order.getCreateTime()));
			List<FoodDTO> foodDTOs = new ArrayList<FoodDTO>();
			List<TBfOrderDetail> orderDetails = orderDetailDao.getOrderDetailByOrderId(order.getOrderId());
			for (TBfOrderDetail orderDetail : orderDetails){
				try {
					TBfFood food = foodDao.get(orderDetail.getFoodId());
					FoodDTO foodDTO = new FoodDTO();
					foodDTO.setFoodName(food.getFoodName());
					foodDTO.setFoodId(food.getFoodId());
					foodDTO.setPrice(food.getPrice());
					foodDTO.setFoodNum(orderDetail.getFoodCount());
					foodDTOs.add(foodDTO);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			orderDTO.setFoods(foodDTOs);
			orderDTOs.add(orderDTO);
		}

		return orderDTOs;
	}
}
