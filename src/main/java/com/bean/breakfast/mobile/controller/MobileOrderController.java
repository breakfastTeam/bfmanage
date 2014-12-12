package com.bean.breakfast.mobile.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bean.breakfast.basic.dto.FoodDTO;
import com.bean.breakfast.basic.dto.OrderDTO;
import com.bean.breakfast.basic.model.TBfFood;
import com.bean.breakfast.basic.model.TBfOrder;
import com.bean.breakfast.basic.model.TBfUser;
import com.bean.breakfast.basic.service.FoodService;
import com.bean.breakfast.basic.service.OrderService;
import com.bean.breakfast.basic.service.UserService;
import com.bean.breakfast.constants.IConstants;
import com.bean.breakfast.utils.MsgUtil;
import com.bean.core.page.Page;
import com.bean.core.utils.IDateUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/mobile")
public class MobileOrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private FoodService foodService;

    Page<OrderDTO> pageDTO = new Page<OrderDTO>(IConstants.DEFAULT_PAGE_SIZE);


    /**
     * 保存订单信息
     *
     * @return model ModelAndView 基本返回对象
     * @author Felix
     * @since 2014-04-19 9:59
     * 变更记录:
     */
    @ResponseBody
    @RequestMapping(value = "/saveOrder")
    public String saveOrder(@RequestBody final String reqData, final HttpServletRequest request){
        MsgUtil msgUtil = new MsgUtil();//声明报文工具类
        JSONObject json;//报文对应的JSON对象
        JSONObject bodyObj;
        try {
            /**解析处理请求报文**/
            json=JSONObject.parseObject(reqData);
            bodyObj=json.getJSONObject("body");
            String customerId = bodyObj.getString("customerId");
            String type = bodyObj.getString("type");
            String consigneeName = bodyObj.getString("consigneeName");
            String consigneeAddr = bodyObj.getString("consigneeAddr");
            String consigneeMobile = bodyObj.getString("consigneeMobile");
            String remark = bodyObj.getString("remark");
            String preSendTime = bodyObj.getString("preSendTime");
            String preSendDateStr = bodyObj.getString("preSendDate");
            Date preSendDate = IDateUtil.parseDate(preSendDateStr,1);
            Double orderPrice = StringUtils.isNotBlank(bodyObj.getString("orderPrice"))?Double.parseDouble(bodyObj.getString("orderPrice")):0;
            Double exccreaditCount = StringUtils.isNotBlank(bodyObj.getString("exccreaditCount"))?Double.parseDouble(bodyObj.getString("exccreaditCount")):0;
            JSONArray orderDetail = bodyObj.getJSONArray("orderDetail");
            List<FoodDTO> foodDTOs = new ArrayList<FoodDTO>();
            for (int i = 0; i<orderDetail.size(); i++){
                FoodDTO foodDTO = new FoodDTO();
                JSONObject orderDetailObject = (JSONObject)orderDetail.get(i);
                String foodId = orderDetailObject.getString("foodId");
                int fc = foodService.getFoodCount(foodId);
                String foodCount = orderDetailObject.getString("foodCount");
                if(fc < Integer.parseInt(foodCount)){
                    return msgUtil.generateHeadMsg(IConstants.EXCEPTION_CODE, "亲，太火爆了，粥小妹只能卖您"+fc+"份").generateRtnMsg();
                }
                foodDTO.setFoodId(foodId);
                foodDTO.setFoodNum(Integer.parseInt(foodCount));
                foodDTOs.add(foodDTO);
            }
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setConsigneeName(consigneeName);
            orderDTO.setConsigneeAddr(consigneeAddr);
            orderDTO.setConsigneePhone(consigneeMobile);
            orderDTO.setCustomerId(customerId);
            orderDTO.setOrderType(type);
            orderDTO.setMoney(orderPrice);
            orderDTO.setPreSendTime(preSendTime);
            orderDTO.setPreSendDate(preSendDate);
            orderDTO.setExccreaditCount(exccreaditCount);
            orderDTO.setFoods(foodDTOs);
            orderDTO.setRemark(remark);
            orderService.save(orderDTO);
            return msgUtil.generateHeadMsg(IConstants.SUCCESS_CODE, IConstants.OPERATE_SUCCESS).generateRtnMsg();

        }catch(Exception e){
            e.printStackTrace();
            return msgUtil.generateHeadMsg(IConstants.ERROR_CODE, IConstants.OPERATE_ERROR).generateRtnMsg();
        }
    }

    /**
     * 根据用户主键获取用户所有订单
     *
     * @return model ModelAndView 基本返回对象
     * @author Felix
     * @since 2014-04-19 9:59
     * 变更记录:
     */
    @ResponseBody
    @RequestMapping(value = "/getMyOrder")
    public String getMyOrder(@RequestBody final String reqData, final HttpServletRequest request){
        MsgUtil msgUtil = new MsgUtil();//声明报文工具类
        JSONObject json;//报文对应的JSON对象
        JSONObject bodyObj;
        try {
            /**解析处理请求报文**/
            json=JSONObject.parseObject(reqData);
            bodyObj=json.getJSONObject("body");
            String userId = bodyObj.getString("userId");

            List<OrderDTO> orderDTOs = orderService.getUserOrder(userId);
            return msgUtil.generateHeadMsg(IConstants.SUCCESS_CODE, IConstants.OPERATE_SUCCESS).generateRtnMsg(orderDTOs);
        }catch(Exception e){
            e.printStackTrace();
            return msgUtil.generateHeadMsg(IConstants.ERROR_CODE, IConstants.OPERATE_ERROR).generateRtnMsg();
        }
    }
    /**
     * 根据用户主键获取用户所有订单
     * @return model ModelAndView 基本返回对象
     * @author Felix
     * @since 2014-04-19 9:59
     * 变更记录:
     */
    @ResponseBody
    @RequestMapping(value = "/getMyLatestOrder")
    public String getMyLatestOrder(@RequestBody final String reqData, final HttpServletRequest request){
        MsgUtil msgUtil = new MsgUtil();//声明报文工具类
        JSONObject json;//报文对应的JSON对象
        JSONObject bodyObj;
        try {
            /**解析处理请求报文**/
            json=JSONObject.parseObject(reqData);
            bodyObj=json.getJSONObject("body");
            String userId = bodyObj.getString("userId");

            TBfOrder order = orderService.getUserLatestOrder(userId);
            if(order == null){
                order = new TBfOrder();
            }
            return msgUtil.generateHeadMsg(IConstants.SUCCESS_CODE, IConstants.OPERATE_SUCCESS).generateRtnMsg(order);
        }catch(Exception e){
            e.printStackTrace();
            return msgUtil.generateHeadMsg(IConstants.ERROR_CODE, IConstants.OPERATE_ERROR).generateRtnMsg();
        }
    }
    /**
     * 获取所有有效预定订单
     *
     * @param reqData String 请求的报文字符串
     * @return model ModelAndView 基本返回对象
     * @author Felix
     * @since 2014-04-19 9:59
     * 变更记录:
     */
    @ResponseBody
    @RequestMapping(value = "/getAllNewOrders")
    public String getAllNewOrders(@RequestBody final String reqData, final HttpServletRequest request) {
        MsgUtil msgUtil = new MsgUtil();//声明报文工具类
        try {
            TBfOrder order = new TBfOrder();
            order.setStatus(IConstants.STATUS_DRAFT);
            pageDTO = orderService.findOrders(pageDTO, order);
            if(pageDTO.getResult() != null && pageDTO.getResult().size() > 0){
                return msgUtil.generateHeadMsg(IConstants.SUCCESS_CODE, IConstants.OPERATE_SUCCESS).generateRtnMsg();
            }else{
                return msgUtil.generateHeadMsg(IConstants.EXCEPTION_CODE, "No New Orders").generateRtnMsg();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return msgUtil.generateHeadMsg(IConstants.ERROR_CODE, IConstants.OPERATE_ERROR).generateRtnMsg();
        }
    }
}
