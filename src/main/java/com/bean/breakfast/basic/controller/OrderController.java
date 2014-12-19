package com.bean.breakfast.basic.controller;

import com.alibaba.fastjson.JSONObject;
import com.bean.breakfast.basic.dto.FoodDTO;
import com.bean.breakfast.basic.dto.OrderDTO;
import com.bean.breakfast.basic.dto.OrderDetailDTO;
import com.bean.breakfast.basic.model.TBfExpress;
import com.bean.breakfast.basic.model.TBfFood;
import com.bean.breakfast.basic.model.TBfOrder;
import com.bean.breakfast.basic.service.ExpressService;
import com.bean.breakfast.basic.service.FoodService;
import com.bean.breakfast.basic.service.OrderService;
import com.bean.breakfast.constants.IConstants;
import com.bean.breakfast.utils.MsgUtil;
import com.bean.core.page.Page;
import com.bean.core.utils.IDateUtil;
import com.bean.core.utils.IFileUtil;
import com.bean.core.utils.IStringUtil;
import com.bean.plugin.upyun.UPUPload;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/basic")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private ExpressService expressService;


    Page<TBfOrder> page = new Page<TBfOrder>(IConstants.DEFAULT_PAGE_SIZE);
    Page<OrderDTO> pageDTO = new Page<OrderDTO>(IConstants.DEFAULT_PAGE_SIZE);


    /**
     * 跳转到订单管理页面带查询条件
     *
     * @param request HttpServletRequest request请求
     * @return model ModelAndView 基本返回对象
     * @author Felix
     * @since 2014-04-19 9:59
     * 变更记录:
     */

    @RequestMapping(value = "/toOrder")
    public ModelAndView toOrder(final HttpServletRequest request) {
        ModelAndView model = new ModelAndView("basic/order");
        String pageSizeStr = request.getParameter("pageSize");
        String pageNoStr = request.getParameter("pageNo");
        String consigneeName = request.getParameter("consigneeName");
        String consigneeMobile = request.getParameter("consigneeMobile");
        String consigneeAddress = request.getParameter("consigneeAddress");
        TBfOrder order = new TBfOrder();
        order.setConsigneeAddress(consigneeAddress);
        order.setConsigneeMobile(consigneeMobile);
        order.setConsigneeName(consigneeName);
        if (IStringUtil.isNotBlank(pageSizeStr) && IStringUtil.isNotBlank(pageNoStr)) {
            pageDTO.setPageNo(Integer.parseInt(pageNoStr));
        }
        pageDTO = orderService.findOrders(pageDTO, order);
        model.addObject("page", pageDTO);
        model.addObject("consigneeName",consigneeName);
        model.addObject("consigneeMobile",consigneeMobile);
        model.addObject("consigneeAddress",consigneeAddress);

        return model;
    }


    /**
     * 跳转到订单详情页面
     * @return model ModelAndView 基本返回对象
     * @author Felix
     * @since 2014-04-19 9:59
     * 变更记录:
     */
    @RequestMapping(value = "/toOrderDetail")
    public ModelAndView toOrderDetail(final HttpServletRequest request) {
        ModelAndView model = new ModelAndView("basic/orderDetail");
        String orderId = request.getParameter("orderId");
        List<OrderDetailDTO> orderDetails = orderService.getOrderDetail(orderId);
        model.addObject("orderDetails", orderDetails);
        return model;
    }
    /**
     * 跳转到订单打印页面
     * @return model ModelAndView 基本返回对象
     * @author Felix
     * @since 2014-04-19 9:59
     * 变更记录:
     */
    @RequestMapping(value = "/toOrderPrint")
    public ModelAndView toOrderPrint(final HttpServletRequest request) {
        ModelAndView model = new ModelAndView("basic/orderPrint");
        String orderId = request.getParameter("orderId");
        TBfOrder order = null;
        if(IStringUtil.isNotBlank(orderId)){
            order = orderService.getOrder(orderId);
        }
        if(order == null){
            order = new TBfOrder();
        }

        List<OrderDetailDTO> orderDetails = orderService.getOrderDetail(orderId);
        String address = order.getConsigneeAddress();
        String addresses[] = address.split("-");
        String tempAdd = "";
        for(int i = 1; i <addresses.length; i++){
            tempAdd = tempAdd + addresses[i];
        }
        order.setConsigneeAddress(tempAdd);
        model.addObject("orderDetails", orderDetails);
        model.addObject("order", order);
        return model;
    }

    /**
     * 修改订单状态
     * @param reqData String 请求的报文字符串
     * @return model ModelAndView 基本返回对象
     * @author Felix
     * @since 2014-04-19 9:59
     * 变更记录:
     */
    @ResponseBody
    @RequestMapping(value = "/updateOrderStatus")
    public String updateOrderStatus(@RequestBody final String reqData, final HttpServletRequest request) {
        MsgUtil msgUtil = new MsgUtil();//声明报文工具类
        JSONObject json;
        JSONObject bodyObj;
        try {
            /**解析处理请求报文**/
            json = JSONObject.parseObject(reqData);
            bodyObj = json.getJSONObject("body");
            String orderId = bodyObj.getString("orderId");
            String courierId = bodyObj.getString("userId");
            String status = bodyObj.getString("status");
            TBfOrder order = orderService.getOrder(orderId);
            order.setStatus(status);
            if(IStringUtil.isNotBlank(courierId)){
                TBfExpress express = expressService.getExpressByOrderId(orderId);
                if(express == null){
                    express = new TBfExpress();
                    express.setCourierId(courierId);
                    express.setOrderId(orderId);
                    express.setStatus(IConstants.ENABLE);
                    express.setCreateTime(IDateUtil.getCurrentTimeDate());
                }else{
                    express.setCourierId(courierId);
                    express.setLastModifyTime(IDateUtil.getCurrentTimeDate());
                }
                orderService.saveOrUpdateOrderAndExpress(order, express);
            }else {
                orderService.saveOrUpdate(order);
            }
            return msgUtil.generateHeadMsg(IConstants.SUCCESS_CODE, IConstants.OPERATE_SUCCESS).generateRtnMsg();
        } catch (Exception e) {
            e.printStackTrace();
            return msgUtil.generateHeadMsg(IConstants.ERROR_CODE, IConstants.OPERATE_ERROR).generateRtnMsg();
        }
    }


}
