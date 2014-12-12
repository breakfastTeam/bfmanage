package com.bean.breakfast.basic.service.impl;

import com.bean.breakfast.basic.dao.FoodDao;
import com.bean.breakfast.basic.dao.OrderDao;
import com.bean.breakfast.basic.dao.OrderDetailDao;
import com.bean.breakfast.basic.dto.FoodDTO;
import com.bean.breakfast.basic.dto.OrderDTO;
import com.bean.breakfast.basic.dto.OrderDetailDTO;
import com.bean.breakfast.basic.model.TBfFile;
import com.bean.breakfast.basic.model.TBfFood;
import com.bean.breakfast.basic.model.TBfOrder;
import com.bean.breakfast.basic.model.TBfOrderDetail;
import com.bean.breakfast.basic.service.OrderService;
import com.bean.breakfast.constants.IConstants;
import com.bean.core.orm.service.impl.BaseServiceImpl;
import com.bean.core.page.Page;
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
	/**
	 * 保存订单及订单详细信息
	 * **/
	public String save(OrderDTO orderDTO){
		TBfOrder order = new TBfOrder();
		order.setCustomerId(orderDTO.getCustomerId());
		order.setConsigneeName(orderDTO.getConsigneeName());
		order.setConsigneeMobile(orderDTO.getConsigneePhone());
		order.setConsigneeAddress(orderDTO.getConsigneeAddr());
		order.setOrderPrice(orderDTO.getMoney());
		order.setComments(orderDTO.getRemark());
		order.setPreSendDate(orderDTO.getPreSendDate());
		order.setPreSendTime(orderDTO.getPreSendTime());
		order.setStatus(IConstants.STATUS_DRAFT);
		order.setCreateTime(IDateUtil.getCurrentTimeDate());
		String orderId = orderDao.save(order);
		List<FoodDTO> foods = orderDTO.getFoods();
		for (FoodDTO foodDTO : foods) {
			TBfOrderDetail orderDetail = new TBfOrderDetail();
			orderDetail.setOrderId(orderId);
			orderDetail.setFoodObjId(foodDTO.getFoodId());
			orderDetail.setFoodObjCount(foodDTO.getFoodNum());
			orderDetailDao.save(orderDetail);

			foodDao.minusFoodCount(foodDTO.getFoodId(), foodDTO.getFoodNum(), foodDTO.getFoodNum());
		}
		return orderDao.save(order);
	}
	/**
	 * 获取一个用户下的所有订单
	 * **/
	@Override
	 public List<OrderDTO> getUserOrder(String userId) {
		List<TBfOrder> orders = orderDao.getOrdersByUserId(userId);
		return generateOrderDTO(orders);
	}
	/**
	 * 获取用户下面最新订单信息
	 * **/
	@Override
	public TBfOrder getUserLatestOrder(String userId) {
		TBfOrder order = orderDao.getLatestOrderByUserId(userId);
		return order;
	}

	@Override
	public Page<OrderDTO> findOrders(Page<OrderDTO> page, TBfOrder order) {
		Page<TBfOrder> orderPage = new Page<TBfOrder>();
		orderPage.setPageSize(page.getPageSize());
		orderPage.setPageNo(page.getPageNo());
		orderPage = orderDao.findOrders(orderPage, order);
		List<TBfOrder> orders = new ArrayList<TBfOrder>();
		orders = orderPage.getResult();
		List<OrderDTO> orderDTOs = generateOrderDTO(orders);
		page.setTotalCount(orderPage.getTotalCount());
		page.setResult(orderDTOs);
		return page;
	}
	/**
	 * 根据订单Id获取单品详情
	 * **/
	@Override
	public List<OrderDetailDTO> getOrderDetail(String orderId) {
		List<OrderDetailDTO> orderDetailDTOs = new ArrayList<OrderDetailDTO>();
		List<TBfOrderDetail> orderDetails = orderDetailDao.getOrderDetailByOrderId(orderId);
		for (TBfOrderDetail orderDetail : orderDetails){
			try {
				OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
				TBfFood food = foodDao.get(orderDetail.getFoodObjId());
				orderDetailDTO.setFood(food);
				orderDetailDTO.setOrderDetail(orderDetail);
				orderDetailDTOs.add(orderDetailDTO);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return orderDetailDTOs;
	}
	public TBfOrder getOrder(String orderId){
		try {
			return this.orderDao.get(orderId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 将Order封装成OrderDTO对象
	 * **/
	public List<OrderDTO> generateOrderDTO(List<TBfOrder> orders){
		List<OrderDTO> orderDTOs = new ArrayList<OrderDTO>();
		for(TBfOrder order : orders){
			OrderDTO orderDTO = new OrderDTO();
			orderDTO.setOrderId(order.getOrderId());
			orderDTO.setCustomerId(order.getCustomerId());
			orderDTO.setConsigneeName(order.getConsigneeName());
			orderDTO.setConsigneePhone(order.getConsigneeMobile());
			orderDTO.setConsigneeAddr(order.getConsigneeAddress());
			orderDTO.setMoney(order.getOrderPrice());
			orderDTO.setRemark(orderDTO.getRemark());
			orderDTO.setStatus(order.getStatus());
			orderDTO.setPreSendTime(order.getPreSendTime());
			orderDTO.setPreSendDate(order.getPreSendDate());
			orderDTO.setCreateTime(IDateUtil.dateToString(order.getCreateTime()));
			List<FoodDTO> foodDTOs = new ArrayList<FoodDTO>();
			List<TBfOrderDetail> orderDetails = orderDetailDao.getOrderDetailByOrderId(order.getOrderId());
			for (TBfOrderDetail orderDetail : orderDetails){
				try {
					TBfFood food = foodDao.get(orderDetail.getFoodObjId());
					FoodDTO foodDTO = new FoodDTO();
					if(food != null) {
						foodDTO.setFoodName(food.getFoodName());
						foodDTO.setFoodId(food.getFoodId());
						foodDTO.setPrice(food.getPrice());
					}
					foodDTO.setFoodNum(orderDetail.getFoodObjCount());
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
	public void saveOrUpdate(TBfOrder order){
		orderDao.saveOrUpdate(order);
	}
}
